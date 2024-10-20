
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
 * 企业
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/qiye")
public class QiyeController {
    private static final Logger logger = LoggerFactory.getLogger(QiyeController.class);

    private static final String TABLE_NAME = "qiye";

    @Autowired
    private QiyeService qiyeService;


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
    private JiaofeiService jiaofeiService;//缴费
    @Autowired
    private NewsService newsService;//公告通知
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
        params.put("dataDeleteStart",1);params.put("dataDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = qiyeService.queryPage(params);

        //字典表数据转换
        List<QiyeView> list =(List<QiyeView>)page.getList();
        for(QiyeView c:list){
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
        QiyeEntity qiye = qiyeService.selectById(id);
        if(qiye !=null){
            //entity转view
            QiyeView view = new QiyeView();
            BeanUtils.copyProperties( qiye , view );//把实体数据重构到view中
            //级联表 企业用户
            //级联表
            QiyeyonghuEntity qiyeyonghu = qiyeyonghuService.selectById(qiye.getQiyeyonghuId());
            if(qiyeyonghu != null){
            BeanUtils.copyProperties( qiyeyonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "qiyeyonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setQiyeyonghuId(qiyeyonghu.getId());
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
    public R save(@RequestBody QiyeEntity qiye, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,qiye:{}",this.getClass().getName(),qiye.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("企业用户".equals(role))
            qiye.setQiyeyonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<QiyeEntity> queryWrapper = new EntityWrapper<QiyeEntity>()
            .eq("qiyeyonghu_id", qiye.getQiyeyonghuId())
            .eq("qiye_name", qiye.getQiyeName())
            .eq("qiye_types", qiye.getQiyeTypes())
            .eq("qiye_phone", qiye.getQiyePhone())
            .eq("qiye_email", qiye.getQiyeEmail())
            .eq("data_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        QiyeEntity qiyeEntity = qiyeService.selectOne(queryWrapper);
        if(qiyeEntity==null){
            qiye.setDataDelete(1);
            qiye.setCreateTime(new Date());
            qiyeService.insert(qiye);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody QiyeEntity qiye, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,qiye:{}",this.getClass().getName(),qiye.toString());
        QiyeEntity oldQiyeEntity = qiyeService.selectById(qiye.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("企业用户".equals(role))
//            qiye.setQiyeyonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if("".equals(qiye.getQiyePhoto()) || "null".equals(qiye.getQiyePhoto())){
                qiye.setQiyePhoto(null);
        }
        if("".equals(qiye.getQiyeContent()) || "null".equals(qiye.getQiyeContent())){
                qiye.setQiyeContent(null);
        }

            qiyeService.updateById(qiye);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<QiyeEntity> oldQiyeList =qiyeService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<QiyeEntity> list = new ArrayList<>();
        for(Integer id:ids){
            QiyeEntity qiyeEntity = new QiyeEntity();
            qiyeEntity.setId(id);
            qiyeEntity.setDataDelete(2);
            list.add(qiyeEntity);
        }
        if(list != null && list.size() >0){
            qiyeService.updateBatchById(list);
        }

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
            List<QiyeEntity> qiyeList = new ArrayList<>();//上传的东西
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
                            QiyeEntity qiyeEntity = new QiyeEntity();
//                            qiyeEntity.setQiyeyonghuId(Integer.valueOf(data.get(0)));   //企业用户 要改的
//                            qiyeEntity.setQiyeName(data.get(0));                    //企业名称 要改的
//                            qiyeEntity.setQiyeTypes(Integer.valueOf(data.get(0)));   //企业类型 要改的
//                            qiyeEntity.setQiyePhone(data.get(0));                    //联系方式 要改的
//                            qiyeEntity.setQiyeEmail(data.get(0));                    //邮箱 要改的
//                            qiyeEntity.setQiyePhoto("");//详情和图片
//                            qiyeEntity.setQiyeContent("");//详情和图片
//                            qiyeEntity.setDataDelete(1);//逻辑删除字段
//                            qiyeEntity.setCreateTime(date);//时间
                            qiyeList.add(qiyeEntity);


                            //把要查询是否重复的字段放入map中
                                //联系方式
                                if(seachFields.containsKey("qiyePhone")){
                                    List<String> qiyePhone = seachFields.get("qiyePhone");
                                    qiyePhone.add(data.get(0));//要改的
                                }else{
                                    List<String> qiyePhone = new ArrayList<>();
                                    qiyePhone.add(data.get(0));//要改的
                                    seachFields.put("qiyePhone",qiyePhone);
                                }
                        }

                        //查询是否重复
                         //联系方式
                        List<QiyeEntity> qiyeEntities_qiyePhone = qiyeService.selectList(new EntityWrapper<QiyeEntity>().in("qiye_phone", seachFields.get("qiyePhone")).eq("data_delete", 1));
                        if(qiyeEntities_qiyePhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(QiyeEntity s:qiyeEntities_qiyePhone){
                                repeatFields.add(s.getQiyePhone());
                            }
                            return R.error(511,"数据库的该表中的 [联系方式] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        qiyeService.insertBatch(qiyeList);
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
        PageUtils page = qiyeService.queryPage(params);

        //字典表数据转换
        List<QiyeView> list =(List<QiyeView>)page.getList();
        for(QiyeView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        QiyeEntity qiye = qiyeService.selectById(id);
            if(qiye !=null){


                //entity转view
                QiyeView view = new QiyeView();
                BeanUtils.copyProperties( qiye , view );//把实体数据重构到view中

                //级联表
                    QiyeyonghuEntity qiyeyonghu = qiyeyonghuService.selectById(qiye.getQiyeyonghuId());
                if(qiyeyonghu != null){
                    BeanUtils.copyProperties( qiyeyonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "qiyeyonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setQiyeyonghuId(qiyeyonghu.getId());
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
    public R add(@RequestBody QiyeEntity qiye, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,qiye:{}",this.getClass().getName(),qiye.toString());
        Wrapper<QiyeEntity> queryWrapper = new EntityWrapper<QiyeEntity>()
            .eq("qiyeyonghu_id", qiye.getQiyeyonghuId())
            .eq("qiye_name", qiye.getQiyeName())
            .eq("qiye_types", qiye.getQiyeTypes())
            .eq("qiye_phone", qiye.getQiyePhone())
            .eq("qiye_email", qiye.getQiyeEmail())
            .eq("data_delete", qiye.getDataDelete())
//            .notIn("qiye_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        QiyeEntity qiyeEntity = qiyeService.selectOne(queryWrapper);
        if(qiyeEntity==null){
            qiye.setDataDelete(1);
            qiye.setCreateTime(new Date());
        qiyeService.insert(qiye);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

