
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
 * 企业用户
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/qiyeyonghu")
public class QiyeyonghuController {
    private static final Logger logger = LoggerFactory.getLogger(QiyeyonghuController.class);

    private static final String TABLE_NAME = "qiyeyonghu";

    @Autowired
    private QiyeyonghuService qiyeyonghuService;


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
        PageUtils page = qiyeyonghuService.queryPage(params);

        //字典表数据转换
        List<QiyeyonghuView> list =(List<QiyeyonghuView>)page.getList();
        for(QiyeyonghuView c:list){
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
        QiyeyonghuEntity qiyeyonghu = qiyeyonghuService.selectById(id);
        if(qiyeyonghu !=null){
            //entity转view
            QiyeyonghuView view = new QiyeyonghuView();
            BeanUtils.copyProperties( qiyeyonghu , view );//把实体数据重构到view中
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
    public R save(@RequestBody QiyeyonghuEntity qiyeyonghu, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,qiyeyonghu:{}",this.getClass().getName(),qiyeyonghu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<QiyeyonghuEntity> queryWrapper = new EntityWrapper<QiyeyonghuEntity>()
            .eq("username", qiyeyonghu.getUsername())
            .or()
            .eq("qiyeyonghu_phone", qiyeyonghu.getQiyeyonghuPhone())
            .eq("data_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        QiyeyonghuEntity qiyeyonghuEntity = qiyeyonghuService.selectOne(queryWrapper);
        if(qiyeyonghuEntity==null){
            qiyeyonghu.setDataDelete(1);
            qiyeyonghu.setInsertTime(new Date());
            qiyeyonghu.setCreateTime(new Date());
            qiyeyonghu.setPassword("123456");
            qiyeyonghuService.insert(qiyeyonghu);
            return R.ok();
        }else {
            return R.error(511,"账户或者企业用户手机号已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody QiyeyonghuEntity qiyeyonghu, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,qiyeyonghu:{}",this.getClass().getName(),qiyeyonghu.toString());
        QiyeyonghuEntity oldQiyeyonghuEntity = qiyeyonghuService.selectById(qiyeyonghu.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(qiyeyonghu.getQiyeyonghuPhoto()) || "null".equals(qiyeyonghu.getQiyeyonghuPhoto())){
                qiyeyonghu.setQiyeyonghuPhoto(null);
        }

            qiyeyonghuService.updateById(qiyeyonghu);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<QiyeyonghuEntity> oldQiyeyonghuList =qiyeyonghuService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<QiyeyonghuEntity> list = new ArrayList<>();
        for(Integer id:ids){
            QiyeyonghuEntity qiyeyonghuEntity = new QiyeyonghuEntity();
            qiyeyonghuEntity.setId(id);
            qiyeyonghuEntity.setDataDelete(2);
            list.add(qiyeyonghuEntity);
        }
        if(list != null && list.size() >0){
            qiyeyonghuService.updateBatchById(list);
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
            List<QiyeyonghuEntity> qiyeyonghuList = new ArrayList<>();//上传的东西
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
                            QiyeyonghuEntity qiyeyonghuEntity = new QiyeyonghuEntity();
//                            qiyeyonghuEntity.setUsername(data.get(0));                    //账户 要改的
//                            qiyeyonghuEntity.setPassword("123456");//密码
//                            qiyeyonghuEntity.setQiyeyonghuName(data.get(0));                    //企业用户名称 要改的
//                            qiyeyonghuEntity.setQiyeyonghuPhone(data.get(0));                    //企业用户手机号 要改的
//                            qiyeyonghuEntity.setQiyeyonghuPhoto("");//详情和图片
//                            qiyeyonghuEntity.setSexTypes(Integer.valueOf(data.get(0)));   //性别 要改的
//                            qiyeyonghuEntity.setQiyeyonghuEmail(data.get(0));                    //企业用户邮箱 要改的
//                            qiyeyonghuEntity.setDataDelete(1);//逻辑删除字段
//                            qiyeyonghuEntity.setInsertTime(date);//时间
//                            qiyeyonghuEntity.setCreateTime(date);//时间
                            qiyeyonghuList.add(qiyeyonghuEntity);


                            //把要查询是否重复的字段放入map中
                                //账户
                                if(seachFields.containsKey("username")){
                                    List<String> username = seachFields.get("username");
                                    username.add(data.get(0));//要改的
                                }else{
                                    List<String> username = new ArrayList<>();
                                    username.add(data.get(0));//要改的
                                    seachFields.put("username",username);
                                }
                                //企业用户手机号
                                if(seachFields.containsKey("qiyeyonghuPhone")){
                                    List<String> qiyeyonghuPhone = seachFields.get("qiyeyonghuPhone");
                                    qiyeyonghuPhone.add(data.get(0));//要改的
                                }else{
                                    List<String> qiyeyonghuPhone = new ArrayList<>();
                                    qiyeyonghuPhone.add(data.get(0));//要改的
                                    seachFields.put("qiyeyonghuPhone",qiyeyonghuPhone);
                                }
                        }

                        //查询是否重复
                         //账户
                        List<QiyeyonghuEntity> qiyeyonghuEntities_username = qiyeyonghuService.selectList(new EntityWrapper<QiyeyonghuEntity>().in("username", seachFields.get("username")).eq("data_delete", 1));
                        if(qiyeyonghuEntities_username.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(QiyeyonghuEntity s:qiyeyonghuEntities_username){
                                repeatFields.add(s.getUsername());
                            }
                            return R.error(511,"数据库的该表中的 [账户] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //企业用户手机号
                        List<QiyeyonghuEntity> qiyeyonghuEntities_qiyeyonghuPhone = qiyeyonghuService.selectList(new EntityWrapper<QiyeyonghuEntity>().in("qiyeyonghu_phone", seachFields.get("qiyeyonghuPhone")).eq("data_delete", 1));
                        if(qiyeyonghuEntities_qiyeyonghuPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(QiyeyonghuEntity s:qiyeyonghuEntities_qiyeyonghuPhone){
                                repeatFields.add(s.getQiyeyonghuPhone());
                            }
                            return R.error(511,"数据库的该表中的 [企业用户手机号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        qiyeyonghuService.insertBatch(qiyeyonghuList);
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
    * 登录
    */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        QiyeyonghuEntity qiyeyonghu = qiyeyonghuService.selectOne(new EntityWrapper<QiyeyonghuEntity>().eq("username", username));
        if(qiyeyonghu==null || !qiyeyonghu.getPassword().equals(password))
            return R.error("账号或密码不正确");
        else if(qiyeyonghu.getDataDelete() != 1)
            return R.error("账户已被删除");
        String token = tokenService.generateToken(qiyeyonghu.getId(),username, "qiyeyonghu", "企业用户");
        R r = R.ok();
        r.put("token", token);
        r.put("role","企业用户");
        r.put("username",qiyeyonghu.getQiyeyonghuName());
        r.put("tableName","qiyeyonghu");
        r.put("userId",qiyeyonghu.getId());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody QiyeyonghuEntity qiyeyonghu, HttpServletRequest request) {
//    	ValidatorUtils.validateEntity(user);
        Wrapper<QiyeyonghuEntity> queryWrapper = new EntityWrapper<QiyeyonghuEntity>()
            .eq("username", qiyeyonghu.getUsername())
            .or()
            .eq("qiyeyonghu_phone", qiyeyonghu.getQiyeyonghuPhone())
            .andNew()
            .eq("data_delete", 1)
            ;
        QiyeyonghuEntity qiyeyonghuEntity = qiyeyonghuService.selectOne(queryWrapper);
        if(qiyeyonghuEntity != null)
            return R.error("账户或者企业用户手机号已经被使用");
        qiyeyonghu.setDataDelete(1);
        qiyeyonghu.setInsertTime(new Date());
        qiyeyonghu.setCreateTime(new Date());
        qiyeyonghuService.insert(qiyeyonghu);

        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id, HttpServletRequest request) {
        QiyeyonghuEntity qiyeyonghu = qiyeyonghuService.selectById(id);
        qiyeyonghu.setPassword("123456");
        qiyeyonghuService.updateById(qiyeyonghu);
        return R.ok();
    }

	/**
	 * 修改密码
	 */
	@GetMapping(value = "/updatePassword")
	public R updatePassword(String  oldPassword, String  newPassword, HttpServletRequest request) {
        QiyeyonghuEntity qiyeyonghu = qiyeyonghuService.selectById((Integer)request.getSession().getAttribute("userId"));
		if(newPassword == null){
			return R.error("新密码不能为空") ;
		}
		if(!oldPassword.equals(qiyeyonghu.getPassword())){
			return R.error("原密码输入错误");
		}
		if(newPassword.equals(qiyeyonghu.getPassword())){
			return R.error("新密码不能和原密码一致") ;
		}
        qiyeyonghu.setPassword(newPassword);
		qiyeyonghuService.updateById(qiyeyonghu);
		return R.ok();
	}



    /**
     * 忘记密码
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request) {
        QiyeyonghuEntity qiyeyonghu = qiyeyonghuService.selectOne(new EntityWrapper<QiyeyonghuEntity>().eq("username", username));
        if(qiyeyonghu!=null){
            qiyeyonghu.setPassword("123456");
            qiyeyonghuService.updateById(qiyeyonghu);
            return R.ok();
        }else{
           return R.error("账号不存在");
        }
    }


    /**
    * 获取用户的session用户信息
    */
    @RequestMapping("/session")
    public R getCurrQiyeyonghu(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        QiyeyonghuEntity qiyeyonghu = qiyeyonghuService.selectById(id);
        if(qiyeyonghu !=null){
            //entity转view
            QiyeyonghuView view = new QiyeyonghuView();
            BeanUtils.copyProperties( qiyeyonghu , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }
    }


    /**
    * 退出
    */
    @GetMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
    }



    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = qiyeyonghuService.queryPage(params);

        //字典表数据转换
        List<QiyeyonghuView> list =(List<QiyeyonghuView>)page.getList();
        for(QiyeyonghuView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        QiyeyonghuEntity qiyeyonghu = qiyeyonghuService.selectById(id);
            if(qiyeyonghu !=null){


                //entity转view
                QiyeyonghuView view = new QiyeyonghuView();
                BeanUtils.copyProperties( qiyeyonghu , view );//把实体数据重构到view中

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
    public R add(@RequestBody QiyeyonghuEntity qiyeyonghu, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,qiyeyonghu:{}",this.getClass().getName(),qiyeyonghu.toString());
        Wrapper<QiyeyonghuEntity> queryWrapper = new EntityWrapper<QiyeyonghuEntity>()
            .eq("username", qiyeyonghu.getUsername())
            .or()
            .eq("qiyeyonghu_phone", qiyeyonghu.getQiyeyonghuPhone())
            .andNew()
            .eq("data_delete", 1)
//            .notIn("qiyeyonghu_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        QiyeyonghuEntity qiyeyonghuEntity = qiyeyonghuService.selectOne(queryWrapper);
        if(qiyeyonghuEntity==null){
            qiyeyonghu.setDataDelete(1);
            qiyeyonghu.setInsertTime(new Date());
            qiyeyonghu.setCreateTime(new Date());
            qiyeyonghu.setPassword("123456");
        qiyeyonghuService.insert(qiyeyonghu);

            return R.ok();
        }else {
            return R.error(511,"账户或者企业用户手机号已经被使用");
        }
    }

}

