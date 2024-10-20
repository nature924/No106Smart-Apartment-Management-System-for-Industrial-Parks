package com.entity.vo;

import com.entity.QiyeyonghuEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 企业用户
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("qiyeyonghu")
public class QiyeyonghuVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 账户
     */

    @TableField(value = "username")
    private String username;


    /**
     * 密码
     */

    @TableField(value = "password")
    private String password;


    /**
     * 企业用户名称
     */

    @TableField(value = "qiyeyonghu_name")
    private String qiyeyonghuName;


    /**
     * 企业用户手机号
     */

    @TableField(value = "qiyeyonghu_phone")
    private String qiyeyonghuPhone;


    /**
     * 企业用户头像
     */

    @TableField(value = "qiyeyonghu_photo")
    private String qiyeyonghuPhoto;


    /**
     * 性别
     */

    @TableField(value = "sex_types")
    private Integer sexTypes;


    /**
     * 企业用户邮箱
     */

    @TableField(value = "qiyeyonghu_email")
    private String qiyeyonghuEmail;


    /**
     * 逻辑删除
     */

    @TableField(value = "data_delete")
    private Integer dataDelete;


    /**
     * 添加时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 获取：账户
	 */

    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 设置：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 获取：密码
	 */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 设置：企业用户名称
	 */
    public String getQiyeyonghuName() {
        return qiyeyonghuName;
    }


    /**
	 * 获取：企业用户名称
	 */

    public void setQiyeyonghuName(String qiyeyonghuName) {
        this.qiyeyonghuName = qiyeyonghuName;
    }
    /**
	 * 设置：企业用户手机号
	 */
    public String getQiyeyonghuPhone() {
        return qiyeyonghuPhone;
    }


    /**
	 * 获取：企业用户手机号
	 */

    public void setQiyeyonghuPhone(String qiyeyonghuPhone) {
        this.qiyeyonghuPhone = qiyeyonghuPhone;
    }
    /**
	 * 设置：企业用户头像
	 */
    public String getQiyeyonghuPhoto() {
        return qiyeyonghuPhoto;
    }


    /**
	 * 获取：企业用户头像
	 */

    public void setQiyeyonghuPhoto(String qiyeyonghuPhoto) {
        this.qiyeyonghuPhoto = qiyeyonghuPhoto;
    }
    /**
	 * 设置：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 获取：性别
	 */

    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 设置：企业用户邮箱
	 */
    public String getQiyeyonghuEmail() {
        return qiyeyonghuEmail;
    }


    /**
	 * 获取：企业用户邮箱
	 */

    public void setQiyeyonghuEmail(String qiyeyonghuEmail) {
        this.qiyeyonghuEmail = qiyeyonghuEmail;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getdataDelete() {
        return dataDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setdataDelete(Integer dataDelete) {
        this.dataDelete = dataDelete;
    }
    /**
	 * 设置：添加时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：添加时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
