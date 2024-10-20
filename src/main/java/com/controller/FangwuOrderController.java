
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
 * 房屋租赁订单
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/fangwuOrder")
public class FangwuOrderController {
    private static final Logger logger = LoggerFactory.getLogger(FangwuOrderController.class);

    private static final String TABLE_NAME = "fangwuOrder";

    @Autowired
    private FangwuOrderService fangwuOrderService;


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
        PageUtils page = fangwuOrderService.queryPage(params);

        //字典表数据转换
        List<FangwuOrderView> list =(List<FangwuOrderView>)page.getList();
        for(FangwuOrderView c:list){
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
        FangwuOrderEntity fangwuOrder = fangwuOrderService.selectById(id);
        if(fangwuOrder !=null){
            //entity转view
            FangwuOrderView view = new FangwuOrderView();
            BeanUtils.copyProperties( fangwuOrder , view );//把实体数据重构到view中
            //级联表 房屋信息
            //级联表
            FangwuEntity fangwu = fangwuService.selectById(fangwuOrder.getFangwuId());
            if(fangwu != null){
            BeanUtils.copyProperties( fangwu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setFangwuId(fangwu.getId());
            }
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(fangwuOrder.getYonghuId());
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
    public R save(@RequestBody FangwuOrderEntity fangwuOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,fangwuOrder:{}",this.getClass().getName(),fangwuOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            fangwuOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        fangwuOrder.setCreateTime(new Date());
        fangwuOrder.setInsertTime(new Date());
        fangwuOrderService.insert(fangwuOrder);

        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody FangwuOrderEntity fangwuOrder, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,fangwuOrder:{}",this.getClass().getName(),fangwuOrder.toString());
        FangwuOrderEntity oldFangwuOrderEntity = fangwuOrderService.selectById(fangwuOrder.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            fangwuOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            fangwuOrderService.updateById(fangwuOrder);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<FangwuOrderEntity> oldFangwuOrderList =fangwuOrderService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        fangwuOrderService.deleteBatchIds(Arrays.asList(ids));

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
            List<FangwuOrderEntity> fangwuOrderList = new ArrayList<>();//上传的东西
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
                            FangwuOrderEntity fangwuOrderEntity = new FangwuOrderEntity();
//                            fangwuOrderEntity.setFangwuOrderUuidNumber(data.get(0));                    //订单编号 要改的
//                            fangwuOrderEntity.setFangwuId(Integer.valueOf(data.get(0)));   //房屋 要改的
//                            fangwuOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            fangwuOrderEntity.setFangwuOrderTime(sdf.parse(data.get(0)));          //预约日期 要改的
//                            fangwuOrderEntity.setFangwuOrderNumb(Integer.valueOf(data.get(0)));   //租期/月 要改的
//                            fangwuOrderEntity.setFangwuOrderTruePrice(data.get(0));                    //实付价格 要改的
//                            fangwuOrderEntity.setFangwuOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            fangwuOrderEntity.setFangwuOrderPaymentTypes(Integer.valueOf(data.get(0)));   //支付类型 要改的
//                            fangwuOrderEntity.setInsertTime(date);//时间
//                            fangwuOrderEntity.setCreateTime(date);//时间
                            fangwuOrderList.add(fangwuOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //订单编号
                                if(seachFields.containsKey("fangwuOrderUuidNumber")){
                                    List<String> fangwuOrderUuidNumber = seachFields.get("fangwuOrderUuidNumber");
                                    fangwuOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> fangwuOrderUuidNumber = new ArrayList<>();
                                    fangwuOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("fangwuOrderUuidNumber",fangwuOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //订单编号
                        List<FangwuOrderEntity> fangwuOrderEntities_fangwuOrderUuidNumber = fangwuOrderService.selectList(new EntityWrapper<FangwuOrderEntity>().in("fangwu_order_uuid_number", seachFields.get("fangwuOrderUuidNumber")));
                        if(fangwuOrderEntities_fangwuOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(FangwuOrderEntity s:fangwuOrderEntities_fangwuOrderUuidNumber){
                                repeatFields.add(s.getFangwuOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [订单编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        fangwuOrderService.insertBatch(fangwuOrderList);
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
        PageUtils page = fangwuOrderService.queryPage(params);

        //字典表数据转换
        List<FangwuOrderView> list =(List<FangwuOrderView>)page.getList();
        for(FangwuOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        FangwuOrderEntity fangwuOrder = fangwuOrderService.selectById(id);
            if(fangwuOrder !=null){


                //entity转view
                FangwuOrderView view = new FangwuOrderView();
                BeanUtils.copyProperties( fangwuOrder , view );//把实体数据重构到view中

                //级联表
                    FangwuEntity fangwu = fangwuService.selectById(fangwuOrder.getFangwuId());
                if(fangwu != null){
                    BeanUtils.copyProperties( fangwu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setFangwuId(fangwu.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(fangwuOrder.getYonghuId());
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
    public R add(@RequestBody FangwuOrderEntity fangwuOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,fangwuOrder:{}",this.getClass().getName(),fangwuOrder.toString());
            FangwuEntity fangwuEntity = fangwuService.selectById(fangwuOrder.getFangwuId());
            if(fangwuEntity == null){
                return R.error(511,"查不到该房屋信息");
            }
            // Double fangwuNewMoney = fangwuEntity.getFangwuNewMoney();

            if(false){
            }
            else if(fangwuEntity.getFangwuNewMoney() == null){
                return R.error(511,"租金/月不能为空");
            }

            //计算所获得积分
            Double buyJifen =0.0;
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");
            double balance = yonghuEntity.getNewMoney() - fangwuEntity.getFangwuNewMoney()*fangwuOrder.getFangwuOrderNumb();//余额
            if(balance<0)
                return R.error(511,"余额不够支付");
            fangwuOrder.setFangwuOrderTypes(101); //设置订单状态为已支付
            fangwuOrder.setFangwuOrderTruePrice(fangwuEntity.getFangwuNewMoney()*fangwuOrder.getFangwuOrderNumb()); //设置实付价格
            fangwuOrder.setYonghuId(userId); //设置订单支付人id
            fangwuOrder.setFangwuOrderUuidNumber(String.valueOf(new Date().getTime()));
            fangwuOrder.setFangwuOrderPaymentTypes(1);
            fangwuOrder.setInsertTime(new Date());
            fangwuOrder.setCreateTime(new Date());
                fangwuOrderService.insert(fangwuOrder);//新增订单
            //更新第一注册表
            yonghuEntity.setNewMoney(balance);//设置金额
            yonghuService.updateById(yonghuEntity);

            return R.ok();
    }


    /**
    * 退房
    */
    @RequestMapping("/refund")
    public R refund(Integer id, HttpServletRequest request){
        logger.debug("refund方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));

            FangwuOrderEntity fangwuOrder = fangwuOrderService.selectById(id);//当前表service
            Integer fangwuOrderPaymentTypes = fangwuOrder.getFangwuOrderPaymentTypes();
            Integer fangwuId = fangwuOrder.getFangwuId();
            if(fangwuId == null)
                return R.error(511,"查不到该房屋信息");
            FangwuEntity fangwuEntity = fangwuService.selectById(fangwuId);
            if(fangwuEntity == null)
                return R.error(511,"查不到该房屋信息");
            Double fangwuNewMoney = fangwuEntity.getFangwuNewMoney();
            if(fangwuNewMoney == null)
                return R.error(511,"房屋信息价格不能为空");

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
            return R.error(511,"用户金额不能为空");
            Double zhekou = 1.0;

            //判断是什么支付方式 1代表余额 2代表积分
            if(fangwuOrderPaymentTypes == 1){//余额支付
                //计算金额
                Double money = fangwuEntity.getFangwuNewMoney() * fangwuOrder.getFangwuOrderNumb()  * zhekou;
                //计算所获得积分
                Double buyJifen = 0.0;
                yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() + money); //设置金额


            }


            fangwuOrder.setFangwuOrderTypes(102);//设置订单状态为已退房
            fangwuOrderService.updateAllColumnById(fangwuOrder);//根据id更新
            yonghuService.updateById(yonghuEntity);//更新用户信息
            fangwuService.updateById(fangwuEntity);//更新订单中房屋信息的信息

            return R.ok();
    }

    /**
     * 完成
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id  , HttpServletRequest request){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        FangwuOrderEntity  fangwuOrderEntity = fangwuOrderService.selectById(id);
        fangwuOrderEntity.setFangwuOrderTypes(103);//设置订单状态为已完成
        fangwuOrderService.updateById( fangwuOrderEntity);

        return R.ok();
    }


}

