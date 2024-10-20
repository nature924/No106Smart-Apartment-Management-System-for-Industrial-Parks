package com.entity.model;

import com.entity.QiyeyonghuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 企业用户
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class QiyeyonghuModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 账户
     */
    private String username;


    /**
     * 密码
     */
    private String password;


    /**
     * 企业用户名称
     */
    private String qiyeyonghuName;


    /**
     * 企业用户手机号
     */
    private String qiyeyonghuPhone;


    /**
     * 企业用户头像
     */
    private String qiyeyonghuPhoto;


    /**
     * 性别
     */
    private Integer sexTypes;


    /**
     * 企业用户邮箱
     */
    private String qiyeyonghuEmail;


    /**
     * 逻辑删除
     */
    private Integer dataDelete;


    /**
     * 添加时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 设置：账户
	 */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 获取：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 设置：密码
	 */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 获取：企业用户名称
	 */
    public String getQiyeyonghuName() {
        return qiyeyonghuName;
    }


    /**
	 * 设置：企业用户名称
	 */
    public void setQiyeyonghuName(String qiyeyonghuName) {
        this.qiyeyonghuName = qiyeyonghuName;
    }
    /**
	 * 获取：企业用户手机号
	 */
    public String getQiyeyonghuPhone() {
        return qiyeyonghuPhone;
    }


    /**
	 * 设置：企业用户手机号
	 */
    public void setQiyeyonghuPhone(String qiyeyonghuPhone) {
        this.qiyeyonghuPhone = qiyeyonghuPhone;
    }
    /**
	 * 获取：企业用户头像
	 */
    public String getQiyeyonghuPhoto() {
        return qiyeyonghuPhoto;
    }


    /**
	 * 设置：企业用户头像
	 */
    public void setQiyeyonghuPhoto(String qiyeyonghuPhoto) {
        this.qiyeyonghuPhoto = qiyeyonghuPhoto;
    }
    /**
	 * 获取：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 设置：性别
	 */
    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 获取：企业用户邮箱
	 */
    public String getQiyeyonghuEmail() {
        return qiyeyonghuEmail;
    }


    /**
	 * 设置：企业用户邮箱
	 */
    public void setQiyeyonghuEmail(String qiyeyonghuEmail) {
        this.qiyeyonghuEmail = qiyeyonghuEmail;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getdataDelete() {
        return dataDelete;
    }


    /**
	 * 设置：逻辑删除
	 */
    public void setdataDelete(Integer dataDelete) {
        this.dataDelete = dataDelete;
    }
    /**
	 * 获取：添加时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：添加时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
