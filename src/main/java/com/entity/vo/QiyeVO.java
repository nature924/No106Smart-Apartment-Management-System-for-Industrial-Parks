package com.entity.vo;

import com.entity.QiyeEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 企业
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("qiye")
public class QiyeVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 企业用户
     */

    @TableField(value = "qiyeyonghu_id")
    private Integer qiyeyonghuId;


    /**
     * 企业名称
     */

    @TableField(value = "qiye_name")
    private String qiyeName;


    /**
     * 企业类型
     */

    @TableField(value = "qiye_types")
    private Integer qiyeTypes;


    /**
     * 联系方式
     */

    @TableField(value = "qiye_phone")
    private String qiyePhone;


    /**
     * 邮箱
     */

    @TableField(value = "qiye_email")
    private String qiyeEmail;


    /**
     * 企业封面
     */

    @TableField(value = "qiye_photo")
    private String qiyePhoto;


    /**
     * 企业简介
     */

    @TableField(value = "qiye_content")
    private String qiyeContent;


    /**
     * 逻辑删除
     */

    @TableField(value = "qiye_delete")
    private Integer qiyeDelete;


    /**
     * 创建时间 show1 show2 photoShow
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
	 * 设置：企业用户
	 */
    public Integer getQiyeyonghuId() {
        return qiyeyonghuId;
    }


    /**
	 * 获取：企业用户
	 */

    public void setQiyeyonghuId(Integer qiyeyonghuId) {
        this.qiyeyonghuId = qiyeyonghuId;
    }
    /**
	 * 设置：企业名称
	 */
    public String getQiyeName() {
        return qiyeName;
    }


    /**
	 * 获取：企业名称
	 */

    public void setQiyeName(String qiyeName) {
        this.qiyeName = qiyeName;
    }
    /**
	 * 设置：企业类型
	 */
    public Integer getQiyeTypes() {
        return qiyeTypes;
    }


    /**
	 * 获取：企业类型
	 */

    public void setQiyeTypes(Integer qiyeTypes) {
        this.qiyeTypes = qiyeTypes;
    }
    /**
	 * 设置：联系方式
	 */
    public String getQiyePhone() {
        return qiyePhone;
    }


    /**
	 * 获取：联系方式
	 */

    public void setQiyePhone(String qiyePhone) {
        this.qiyePhone = qiyePhone;
    }
    /**
	 * 设置：邮箱
	 */
    public String getQiyeEmail() {
        return qiyeEmail;
    }


    /**
	 * 获取：邮箱
	 */

    public void setQiyeEmail(String qiyeEmail) {
        this.qiyeEmail = qiyeEmail;
    }
    /**
	 * 设置：企业封面
	 */
    public String getQiyePhoto() {
        return qiyePhoto;
    }


    /**
	 * 获取：企业封面
	 */

    public void setQiyePhoto(String qiyePhoto) {
        this.qiyePhoto = qiyePhoto;
    }
    /**
	 * 设置：企业简介
	 */
    public String getQiyeContent() {
        return qiyeContent;
    }


    /**
	 * 获取：企业简介
	 */

    public void setQiyeContent(String qiyeContent) {
        this.qiyeContent = qiyeContent;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getQiyeDelete() {
        return qiyeDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setQiyeDelete(Integer qiyeDelete) {
        this.qiyeDelete = qiyeDelete;
    }
    /**
	 * 设置：创建时间 show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
