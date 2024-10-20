package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 房屋信息
 *
 * @author 
 * @email
 */
@TableName("fangwu")
public class FangwuEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public FangwuEntity() {

	}

	public FangwuEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 企业用户
     */
    @ColumnInfo(comment="企业用户",type="int(11)")
    @TableField(value = "qiyeyonghu_id")

    private Integer qiyeyonghuId;


    /**
     * 房屋名称
     */
    @ColumnInfo(comment="房屋名称",type="varchar(200)")
    @TableField(value = "fangwu_name")

    private String fangwuName;


    /**
     * 房屋照片
     */
    @ColumnInfo(comment="房屋照片",type="varchar(200)")
    @TableField(value = "fangwu_photo")

    private String fangwuPhoto;


    /**
     * 赞
     */
    @ColumnInfo(comment="赞",type="int(11)")
    @TableField(value = "zan_number")

    private Integer zanNumber;


    /**
     * 踩
     */
    @ColumnInfo(comment="踩",type="int(11)")
    @TableField(value = "cai_number")

    private Integer caiNumber;


    /**
     * 房屋类型
     */
    @ColumnInfo(comment="房屋类型",type="int(11)")
    @TableField(value = "fangwu_types")

    private Integer fangwuTypes;


    /**
     * 房屋热度
     */
    @ColumnInfo(comment="房屋热度",type="int(11)")
    @TableField(value = "fangwu_clicknum")

    private Integer fangwuClicknum;


    /**
     * 租金/月
     */
    @ColumnInfo(comment="租金/月",type="decimal(10,2)")
    @TableField(value = "fangwu_new_money")

    private Double fangwuNewMoney;


    /**
     * 房屋介绍
     */
    @ColumnInfo(comment="房屋介绍",type="longtext")
    @TableField(value = "fangwu_content")

    private String fangwuContent;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "data_delete")

    private Integer dataDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="录入时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 获取：企业用户
	 */
    public Integer getQiyeyonghuId() {
        return qiyeyonghuId;
    }
    /**
	 * 设置：企业用户
	 */

    public void setQiyeyonghuId(Integer qiyeyonghuId) {
        this.qiyeyonghuId = qiyeyonghuId;
    }
    /**
	 * 获取：房屋名称
	 */
    public String getFangwuName() {
        return fangwuName;
    }
    /**
	 * 设置：房屋名称
	 */

    public void setFangwuName(String fangwuName) {
        this.fangwuName = fangwuName;
    }
    /**
	 * 获取：房屋照片
	 */
    public String getFangwuPhoto() {
        return fangwuPhoto;
    }
    /**
	 * 设置：房屋照片
	 */

    public void setFangwuPhoto(String fangwuPhoto) {
        this.fangwuPhoto = fangwuPhoto;
    }
    /**
	 * 获取：赞
	 */
    public Integer getZanNumber() {
        return zanNumber;
    }
    /**
	 * 设置：赞
	 */

    public void setZanNumber(Integer zanNumber) {
        this.zanNumber = zanNumber;
    }
    /**
	 * 获取：踩
	 */
    public Integer getCaiNumber() {
        return caiNumber;
    }
    /**
	 * 设置：踩
	 */

    public void setCaiNumber(Integer caiNumber) {
        this.caiNumber = caiNumber;
    }
    /**
	 * 获取：房屋类型
	 */
    public Integer getFangwuTypes() {
        return fangwuTypes;
    }
    /**
	 * 设置：房屋类型
	 */

    public void setFangwuTypes(Integer fangwuTypes) {
        this.fangwuTypes = fangwuTypes;
    }
    /**
	 * 获取：房屋热度
	 */
    public Integer getFangwuClicknum() {
        return fangwuClicknum;
    }
    /**
	 * 设置：房屋热度
	 */

    public void setFangwuClicknum(Integer fangwuClicknum) {
        this.fangwuClicknum = fangwuClicknum;
    }
    /**
	 * 获取：租金/月
	 */
    public Double getFangwuNewMoney() {
        return fangwuNewMoney;
    }
    /**
	 * 设置：租金/月
	 */

    public void setFangwuNewMoney(Double fangwuNewMoney) {
        this.fangwuNewMoney = fangwuNewMoney;
    }
    /**
	 * 获取：房屋介绍
	 */
    public String getFangwuContent() {
        return fangwuContent;
    }
    /**
	 * 设置：房屋介绍
	 */

    public void setFangwuContent(String fangwuContent) {
        this.fangwuContent = fangwuContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getDataDelete() {
        return dataDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setDataDelete(Integer dataDelete) {
        this.dataDelete = dataDelete;
    }
    /**
	 * 获取：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：录入时间
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

    @Override
    public String toString() {
        return "Fangwu{" +
            ", id=" + id +
            ", qiyeyonghuId=" + qiyeyonghuId +
            ", fangwuName=" + fangwuName +
            ", fangwuPhoto=" + fangwuPhoto +
            ", zanNumber=" + zanNumber +
            ", caiNumber=" + caiNumber +
            ", fangwuTypes=" + fangwuTypes +
            ", fangwuClicknum=" + fangwuClicknum +
            ", fangwuNewMoney=" + fangwuNewMoney +
            ", fangwuContent=" + fangwuContent +
            ", dataDelete=" + dataDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
