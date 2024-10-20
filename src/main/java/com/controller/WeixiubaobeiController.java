
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
 * 维修报备
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/weixiubaobei")
public class WeixiubaobeiController {
    private static final Logger logger = LoggerFactory.getLogger(WeixiubaobeiController.class);

    private static final String TABLE_NAME = "weixiubaobei";

    @Autowired
    private WeixiubaobeiService weixiubaobeiService;


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
    private QiyeService qiyeService;//企业
    @Autowired
    private QiyeyonghuService qiyeyonghuService;//企业用户
    @Autowired
    private SheshiyuyueService sheshiyuyueService;//设施预约
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
        PageUtils page = weixiubaobeiService.queryPage(params);

        //字典表数据转换
        List<WeixiubaobeiView> list =(List<WeixiubaobeiView>)page.getList();
        for(WeixiubaobeiView c:list){
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
        WeixiubaobeiEntity weixiubaobei = weixiubaobeiService.selectById(id);
        if(weixiubaobei !=null){
            //entity转view
            WeixiubaobeiView view = new WeixiubaobeiView();
            BeanUtils.copyProperties( weixiubaobei , view );//把实体数据重构到view中
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(weixiubaobei.getYonghuId());
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
    public R save(@RequestBody WeixiubaobeiEntity weixiubaobei, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,weixiubaobei:{}",this.getClass().getName(),weixiubaobei.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            weixiubaobei.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<WeixiubaobeiEntity> queryWrapper = new EntityWrapper<WeixiubaobeiEntity>()
            .eq("yonghu_id", weixiubaobei.getYonghuId())
            .eq("weixiubaobei_name", weixiubaobei.getWeixiubaobeiName())
            .eq("weixiubaobei_types", weixiubaobei.getWeixiubaobeiTypes())
            .in("weixiubaobei_yesno_types", new Integer[]{1,2})
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        WeixiubaobeiEntity weixiubaobeiEntity = weixiubaobeiService.selectOne(queryWrapper);
        if(weixiubaobeiEntity==null){
            weixiubaobei.setInsertTime(new Date());
            weixiubaobei.setWeixiubaobeiYesnoTypes(1);
            weixiubaobei.setCreateTime(new Date());
            weixiubaobeiService.insert(weixiubaobei);
            return R.ok();
        }else {
            if(weixiubaobeiEntity.getWeixiubaobeiYesnoTypes()==1)
                return R.error(511,"有相同的待审核的数据");
            else if(weixiubaobeiEntity.getWeixiubaobeiYesnoTypes()==2)
                return R.error(511,"有相同的审核通过的数据");
            else
                return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody WeixiubaobeiEntity weixiubaobei, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,weixiubaobei:{}",this.getClass().getName(),weixiubaobei.toString());
        WeixiubaobeiEntity oldWeixiubaobeiEntity = weixiubaobeiService.selectById(weixiubaobei.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            weixiubaobei.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if("".equals(weixiubaobei.getWeixiubaobeiText()) || "null".equals(weixiubaobei.getWeixiubaobeiText())){
                weixiubaobei.setWeixiubaobeiText(null);
        }
        if("".equals(weixiubaobei.getWeixiubaobeiYesnoText()) || "null".equals(weixiubaobei.getWeixiubaobeiYesnoText())){
                weixiubaobei.setWeixiubaobeiYesnoText(null);
        }

            weixiubaobeiService.updateById(weixiubaobei);//根据id更新
            return R.ok();
    }


    /**
    * 审核
    */
    @RequestMapping("/shenhe")
    public R shenhe(@RequestBody WeixiubaobeiEntity weixiubaobeiEntity, HttpServletRequest request){
        logger.debug("shenhe方法:,,Controller:{},,weixiubaobeiEntity:{}",this.getClass().getName(),weixiubaobeiEntity.toString());

        WeixiubaobeiEntity oldWeixiubaobei = weixiubaobeiService.selectById(weixiubaobeiEntity.getId());//查询原先数据

//        if(weixiubaobeiEntity.getWeixiubaobeiYesnoTypes() == 2){//通过
//            weixiubaobeiEntity.setWeixiubaobeiTypes();
//        }else if(weixiubaobeiEntity.getWeixiubaobeiYesnoTypes() == 3){//拒绝
//            weixiubaobeiEntity.setWeixiubaobeiTypes();
//        }
        weixiubaobeiEntity.setWeixiubaobeiShenheTime(new Date());//审核时间
        weixiubaobeiService.updateById(weixiubaobeiEntity);//审核

        return R.ok();
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<WeixiubaobeiEntity> oldWeixiubaobeiList =weixiubaobeiService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        weixiubaobeiService.deleteBatchIds(Arrays.asList(ids));

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
            List<WeixiubaobeiEntity> weixiubaobeiList = new ArrayList<>();//上传的东西
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
                            WeixiubaobeiEntity weixiubaobeiEntity = new WeixiubaobeiEntity();
//                            weixiubaobeiEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            weixiubaobeiEntity.setWeixiubaobeiName(data.get(0));                    //报备标题 要改的
//                            weixiubaobeiEntity.setWeixiubaobeiText(data.get(0));                    //报备缘由 要改的
//                            weixiubaobeiEntity.setWeixiubaobeiTypes(Integer.valueOf(data.get(0)));   //报备类型 要改的
//                            weixiubaobeiEntity.setInsertTime(date);//时间
//                            weixiubaobeiEntity.setWeixiubaobeiTime(sdf.parse(data.get(0)));          //报备时间 要改的
//                            weixiubaobeiEntity.setWeixiubaobeiYesnoTypes(Integer.valueOf(data.get(0)));   //报备状态 要改的
//                            weixiubaobeiEntity.setWeixiubaobeiYesnoText(data.get(0));                    //处理结果 要改的
//                            weixiubaobeiEntity.setWeixiubaobeiShenheTime(sdf.parse(data.get(0)));          //审核时间 要改的
//                            weixiubaobeiEntity.setCreateTime(date);//时间
                            weixiubaobeiList.add(weixiubaobeiEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        weixiubaobeiService.insertBatch(weixiubaobeiList);
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
        PageUtils page = weixiubaobeiService.queryPage(params);

        //字典表数据转换
        List<WeixiubaobeiView> list =(List<WeixiubaobeiView>)page.getList();
        for(WeixiubaobeiView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        WeixiubaobeiEntity weixiubaobei = weixiubaobeiService.selectById(id);
            if(weixiubaobei !=null){


                //entity转view
                WeixiubaobeiView view = new WeixiubaobeiView();
                BeanUtils.copyProperties( weixiubaobei , view );//把实体数据重构到view中

                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(weixiubaobei.getYonghuId());
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
    public R add(@RequestBody WeixiubaobeiEntity weixiubaobei, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,weixiubaobei:{}",this.getClass().getName(),weixiubaobei.toString());
        Wrapper<WeixiubaobeiEntity> queryWrapper = new EntityWrapper<WeixiubaobeiEntity>()
            .eq("yonghu_id", weixiubaobei.getYonghuId())
            .eq("weixiubaobei_name", weixiubaobei.getWeixiubaobeiName())
            .eq("weixiubaobei_text", weixiubaobei.getWeixiubaobeiText())
            .eq("weixiubaobei_types", weixiubaobei.getWeixiubaobeiTypes())
            .in("weixiubaobei_yesno_types", new Integer[]{1,2})
            .eq("weixiubaobei_yesno_text", weixiubaobei.getWeixiubaobeiYesnoText())
//            .notIn("weixiubaobei_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        WeixiubaobeiEntity weixiubaobeiEntity = weixiubaobeiService.selectOne(queryWrapper);
        if(weixiubaobeiEntity==null){
            weixiubaobei.setInsertTime(new Date());
            weixiubaobei.setWeixiubaobeiYesnoTypes(1);
            weixiubaobei.setCreateTime(new Date());
        weixiubaobeiService.insert(weixiubaobei);

            return R.ok();
        }else {
            if(weixiubaobeiEntity.getWeixiubaobeiYesnoTypes()==1)
                return R.error(511,"有相同的待审核的数据");
            else if(weixiubaobeiEntity.getWeixiubaobeiYesnoTypes()==2)
                return R.error(511,"有相同的审核通过的数据");
            else
                return R.error(511,"表中有相同数据");
        }
    }

}

