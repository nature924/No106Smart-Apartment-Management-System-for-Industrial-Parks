
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 缴费
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/jiaofei")
public class JiaofeiController {
    private static final Logger logger = LoggerFactory.getLogger(JiaofeiController.class);

    private static final String TABLE_NAME = "jiaofei";

    @Autowired
    private JiaofeiService jiaofeiService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private FangwuService fangwuService;//房屋信息
    @Autowired
    private FangwuCollectionService fangwuCollectionService;//房屋收藏
    @Autowired
    private FangwuLiuyanService fangwuLiuyanService;//房屋留言
    @Autowired
    private FangwuOrderService fangwuOrderService;//房屋租赁订单
    @Autowired
    private ForumService forumService;//交流论坛
    @Autowired
    private NewsService newsService;//公告通知
    @Autowired
    private QiyeService qiyeService;//企业
    @Autowired
    private QiyeyonghuService qiyeyonghuService;//企业用户
    @Autowired
    private SheshiyuyueService sheshiyuyueService;//设施预约
    @Autowired
    private WeixiubaobeiService weixiubaobeiService;//维修报备
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("企业用户".equals(role))
            params.put("qiyeyonghuId",request.getSession().getAttribute("userId"));
        CommonUtil.checkMap(params);
        PageUtils page = jiaofeiService.queryPage(params);

        //字典表数据转换
        List<JiaofeiView> list =(List<JiaofeiView>)page.getList();
        for(JiaofeiView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        JiaofeiEntity jiaofei = jiaofeiService.selectById(id);
        if(jiaofei !=null){
            //entity转view
            JiaofeiView view = new JiaofeiView();
            BeanUtils.copyProperties( jiaofei , view );//把实体数据重构到view中
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(jiaofei.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuId(yonghu.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody JiaofeiEntity jiaofei, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,jiaofei:{}",this.getClass().getName(),jiaofei.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            jiaofei.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<JiaofeiEntity> queryWrapper = new EntityWrapper<JiaofeiEntity>()
            .eq("yonghu_id", jiaofei.getYonghuId())
            .eq("jiaofei_name", jiaofei.getJiaofeiName())
            .eq("jiaofei_types", jiaofei.getJiaofeiTypes())
            .eq("zhuangtai_types", jiaofei.getZhuangtaiTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JiaofeiEntity jiaofeiEntity = jiaofeiService.selectOne(queryWrapper);
        if(jiaofeiEntity==null){
            jiaofei.setInsertTime(new Date());
            jiaofei.setCreateTime(new Date());
            jiaofeiService.insert(jiaofei);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody JiaofeiEntity jiaofei, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,jiaofei:{}",this.getClass().getName(),jiaofei.toString());
        JiaofeiEntity oldJiaofeiEntity = jiaofeiService.selectById(jiaofei.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            jiaofei.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            jiaofeiService.updateById(jiaofei);//根据id更新
            return R.ok();
    }


    /**
     * 缴费
     */
    @RequestMapping("/jiaofei")
    public R jiaofei(Integer id, HttpServletRequest request){
        JiaofeiEntity jiaofeiEntity = jiaofeiService.selectById(id);
        if(jiaofeiEntity == null){
            return R.error();
        }
        YonghuEntity yonghuEntity = yonghuService.selectById(jiaofeiEntity.getYonghuId());
        if(yonghuEntity.getNewMoney() < jiaofeiEntity.getJiaofeiNewMoney()){
            return R.error("余额不足请充值");
        }
        yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() - jiaofeiEntity.getJiaofeiNewMoney());
        yonghuService.updateById(yonghuEntity);
        return R.ok();
    }


    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<JiaofeiEntity> oldJiaofeiList =jiaofeiService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        jiaofeiService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //.eq("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
        try {
            List<JiaofeiEntity> jiaofeiList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            JiaofeiEntity jiaofeiEntity = new JiaofeiEntity();
//                            jiaofeiEntity.setJiaofeiUuidNumber(data.get(0));                    //缴费编号 要改的
//                            jiaofeiEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            jiaofeiEntity.setJiaofeiName(data.get(0));                    //缴费标题 要改的
//                            jiaofeiEntity.setJiaofeiTypes(Integer.valueOf(data.get(0)));   //缴费类型 要改的
//                            jiaofeiEntity.setJiaofeiNewMoney(data.get(0));                    //缴费金额 要改的
//                            jiaofeiEntity.setZhuangtaiTypes(Integer.valueOf(data.get(0)));   //缴费状态 要改的
//                            jiaofeiEntity.setInsertTime(date);//时间
//                            jiaofeiEntity.setCreateTime(date);//时间
                            jiaofeiList.add(jiaofeiEntity);


                            //把要查询是否重复的字段放入map中
                                //缴费编号
                                if(seachFields.containsKey("jiaofeiUuidNumber")){
                                    List<String> jiaofeiUuidNumber = seachFields.get("jiaofeiUuidNumber");
                                    jiaofeiUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> jiaofeiUuidNumber = new ArrayList<>();
                                    jiaofeiUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("jiaofeiUuidNumber",jiaofeiUuidNumber);
                                }
                        }

                        //查询是否重复
                         //缴费编号
                        List<JiaofeiEntity> jiaofeiEntities_jiaofeiUuidNumber = jiaofeiService.selectList(new EntityWrapper<JiaofeiEntity>().in("jiaofei_uuid_number", seachFields.get("jiaofeiUuidNumber")));
                        if(jiaofeiEntities_jiaofeiUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(JiaofeiEntity s:jiaofeiEntities_jiaofeiUuidNumber){
                                repeatFields.add(s.getJiaofeiUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [缴费编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        jiaofeiService.insertBatch(jiaofeiList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }




    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = jiaofeiService.queryPage(params);

        //字典表数据转换
        List<JiaofeiView> list =(List<JiaofeiView>)page.getList();
        for(JiaofeiView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        JiaofeiEntity jiaofei = jiaofeiService.selectById(id);
            if(jiaofei !=null){


                //entity转view
                JiaofeiView view = new JiaofeiView();
                BeanUtils.copyProperties( jiaofei , view );//把实体数据重构到view中

                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(jiaofei.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody JiaofeiEntity jiaofei, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,jiaofei:{}",this.getClass().getName(),jiaofei.toString());
        Wrapper<JiaofeiEntity> queryWrapper = new EntityWrapper<JiaofeiEntity>()
            .eq("jiaofei_uuid_number", jiaofei.getJiaofeiUuidNumber())
            .eq("yonghu_id", jiaofei.getYonghuId())
            .eq("jiaofei_name", jiaofei.getJiaofeiName())
            .eq("jiaofei_types", jiaofei.getJiaofeiTypes())
            .eq("zhuangtai_types", jiaofei.getZhuangtaiTypes())
//            .notIn("jiaofei_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JiaofeiEntity jiaofeiEntity = jiaofeiService.selectOne(queryWrapper);
        if(jiaofeiEntity==null){
            jiaofei.setInsertTime(new Date());
            jiaofei.setCreateTime(new Date());
        jiaofeiService.insert(jiaofei);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

