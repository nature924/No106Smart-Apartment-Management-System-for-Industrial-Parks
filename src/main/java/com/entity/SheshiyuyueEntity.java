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
 * 设施预约
 *
 * @author 
 * @email
 */
@TableName("sheshiyuyue")
public class SheshiyuyueEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public SheshiyuyueEntity() {

	}

	public SheshiyuyueEntity(T t) {
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
     * 预约编号
     */
    @ColumnInfo(comment="预约编号",type="varchar(200)")
    @TableField(value = "sheshiyuyue_uuid_number")

    private String sheshiyuyueUuidNumber;


    /**
     * 预约标题
     */
    @ColumnInfo(comment="预约标题",type="varchar(200)")
    @TableField(value = "sheshiyuyue_name")

    private String sheshiyuyueName;


    /**
     * 设施名称
     */
    @ColumnInfo(comment="设施名称",type="varchar(200)")
    @TableField(value = "sheshiyuyue_sheshi")

    private String sheshiyuyueSheshi;


    /**
     * 设施类型
     */
    @ColumnInfo(comment="设施类型",type="int(11)")
    @TableField(value = "sheshiyuyue_types")

    private Integer sheshiyuyueTypes;


    /**
     * 用户
     */
    @ColumnInfo(comment="用户",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 预约理由
     */
    @ColumnInfo(comment="预约理由",type="longtext")
    @TableField(value = "sheshiyuyue_text")

    private String sheshiyuyueText;


    /**
     * 房屋预约时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="房屋预约时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 预约状态
     */
    @ColumnInfo(comment="预约状态",type="int(11)")
    @TableField(value = "sheshiyuyue_yesno_types")

    private Integer sheshiyuyueYesnoTypes;


    /**
     * 审核回复
     */
    @ColumnInfo(comment="审核回复",type="longtext")
    @TableField(value = "sheshiyuyue_yesno_text")

    private String sheshiyuyueYesnoText;


    /**
     * 创建时间  listShow
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
	 * 获取：预约编号
	 */
    public String getSheshiyuyueUuidNumber() {
        return sheshiyuyueUuidNumber;
    }
    /**
	 * 设置：预约编号
	 */

    public void setSheshiyuyueUuidNumber(String sheshiyuyueUuidNumber) {
        this.sheshiyuyueUuidNumber = sheshiyuyueUuidNumber;
    }
    /**
	 * 获取：预约标题
	 */
    public String getSheshiyuyueName() {
        return sheshiyuyueName;
    }
    /**
	 * 设置：预约标题
	 */

    public void setSheshiyuyueName(String sheshiyuyueName) {
        this.sheshiyuyueName = sheshiyuyueName;
    }
    /**
	 * 获取：设施名称
	 */
    public String getSheshiyuyueSheshi() {
        return sheshiyuyueSheshi;
    }
    /**
	 * 设置：设施名称
	 */

    public void setSheshiyuyueSheshi(String sheshiyuyueSheshi) {
        this.sheshiyuyueSheshi = sheshiyuyueSheshi;
    }
    /**
	 * 获取：设施类型
	 */
    public Integer getSheshiyuyueTypes() {
        return sheshiyuyueTypes;
    }
    /**
	 * 设置：设施类型
	 */

    public void setSheshiyuyueTypes(Integer sheshiyuyueTypes) {
        this.sheshiyuyueTypes = sheshiyuyueTypes;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }
    /**
	 * 设置：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：预约理由
	 */
    public String getSheshiyuyueText() {
        return sheshiyuyueText;
    }
    /**
	 * 设置：预约理由
	 */

    public void setSheshiyuyueText(String sheshiyuyueText) {
        this.sheshiyuyueText = sheshiyuyueText;
    }
    /**
	 * 获取：房屋预约时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：房屋预约时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：预约状态
	 */
    public Integer getSheshiyuyueYesnoTypes() {
        return sheshiyuyueYesnoTypes;
    }
    /**
	 * 设置：预约状态
	 */

    public void setSheshiyuyueYesnoTypes(Integer sheshiyuyueYesnoTypes) {
        this.sheshiyuyueYesnoTypes = sheshiyuyueYesnoTypes;
    }
    /**
	 * 获取：审核回复
	 */
    public String getSheshiyuyueYesnoText() {
        return sheshiyuyueYesnoText;
    }
    /**
	 * 设置：审核回复
	 */

    public void setSheshiyuyueYesnoText(String sheshiyuyueYesnoText) {
        this.sheshiyuyueYesnoText = sheshiyuyueYesnoText;
    }
    /**
	 * 获取：创建时间  listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间  listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Sheshiyuyue{" +
            ", id=" + id +
            ", sheshiyuyueUuidNumber=" + sheshiyuyueUuidNumber +
            ", sheshiyuyueName=" + sheshiyuyueName +
            ", sheshiyuyueSheshi=" + sheshiyuyueSheshi +
            ", sheshiyuyueTypes=" + sheshiyuyueTypes +
            ", yonghuId=" + yonghuId +
            ", sheshiyuyueText=" + sheshiyuyueText +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", sheshiyuyueYesnoTypes=" + sheshiyuyueYesnoTypes +
            ", sheshiyuyueYesnoText=" + sheshiyuyueYesnoText +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
