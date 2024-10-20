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
 * 维修报备
 *
 * @author 
 * @email
 */
@TableName("weixiubaobei")
public class WeixiubaobeiEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public WeixiubaobeiEntity() {

	}

	public WeixiubaobeiEntity(T t) {
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
     * 用户
     */
    @ColumnInfo(comment="用户",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 报备标题
     */
    @ColumnInfo(comment="报备标题",type="varchar(200)")
    @TableField(value = "weixiubaobei_name")

    private String weixiubaobeiName;


    /**
     * 报备缘由
     */
    @ColumnInfo(comment="报备缘由",type="longtext")
    @TableField(value = "weixiubaobei_text")

    private String weixiubaobeiText;


    /**
     * 报备类型
     */
    @ColumnInfo(comment="报备类型",type="int(11)")
    @TableField(value = "weixiubaobei_types")

    private Integer weixiubaobeiTypes;


    /**
     * 申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="申请时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 报备时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="报备时间",type="timestamp")
    @TableField(value = "weixiubaobei_time")

    private Date weixiubaobeiTime;


    /**
     * 报备状态
     */
    @ColumnInfo(comment="报备状态",type="int(11)")
    @TableField(value = "weixiubaobei_yesno_types")

    private Integer weixiubaobeiYesnoTypes;


    /**
     * 处理结果
     */
    @ColumnInfo(comment="处理结果",type="longtext")
    @TableField(value = "weixiubaobei_yesno_text")

    private String weixiubaobeiYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="审核时间",type="timestamp")
    @TableField(value = "weixiubaobei_shenhe_time")

    private Date weixiubaobeiShenheTime;


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
	 * 获取：报备标题
	 */
    public String getWeixiubaobeiName() {
        return weixiubaobeiName;
    }
    /**
	 * 设置：报备标题
	 */

    public void setWeixiubaobeiName(String weixiubaobeiName) {
        this.weixiubaobeiName = weixiubaobeiName;
    }
    /**
	 * 获取：报备缘由
	 */
    public String getWeixiubaobeiText() {
        return weixiubaobeiText;
    }
    /**
	 * 设置：报备缘由
	 */

    public void setWeixiubaobeiText(String weixiubaobeiText) {
        this.weixiubaobeiText = weixiubaobeiText;
    }
    /**
	 * 获取：报备类型
	 */
    public Integer getWeixiubaobeiTypes() {
        return weixiubaobeiTypes;
    }
    /**
	 * 设置：报备类型
	 */

    public void setWeixiubaobeiTypes(Integer weixiubaobeiTypes) {
        this.weixiubaobeiTypes = weixiubaobeiTypes;
    }
    /**
	 * 获取：申请时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：申请时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：报备时间
	 */
    public Date getWeixiubaobeiTime() {
        return weixiubaobeiTime;
    }
    /**
	 * 设置：报备时间
	 */

    public void setWeixiubaobeiTime(Date weixiubaobeiTime) {
        this.weixiubaobeiTime = weixiubaobeiTime;
    }
    /**
	 * 获取：报备状态
	 */
    public Integer getWeixiubaobeiYesnoTypes() {
        return weixiubaobeiYesnoTypes;
    }
    /**
	 * 设置：报备状态
	 */

    public void setWeixiubaobeiYesnoTypes(Integer weixiubaobeiYesnoTypes) {
        this.weixiubaobeiYesnoTypes = weixiubaobeiYesnoTypes;
    }
    /**
	 * 获取：处理结果
	 */
    public String getWeixiubaobeiYesnoText() {
        return weixiubaobeiYesnoText;
    }
    /**
	 * 设置：处理结果
	 */

    public void setWeixiubaobeiYesnoText(String weixiubaobeiYesnoText) {
        this.weixiubaobeiYesnoText = weixiubaobeiYesnoText;
    }
    /**
	 * 获取：审核时间
	 */
    public Date getWeixiubaobeiShenheTime() {
        return weixiubaobeiShenheTime;
    }
    /**
	 * 设置：审核时间
	 */

    public void setWeixiubaobeiShenheTime(Date weixiubaobeiShenheTime) {
        this.weixiubaobeiShenheTime = weixiubaobeiShenheTime;
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
        return "Weixiubaobei{" +
            ", id=" + id +
            ", yonghuId=" + yonghuId +
            ", weixiubaobeiName=" + weixiubaobeiName +
            ", weixiubaobeiText=" + weixiubaobeiText +
            ", weixiubaobeiTypes=" + weixiubaobeiTypes +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", weixiubaobeiTime=" + DateUtil.convertString(weixiubaobeiTime,"yyyy-MM-dd") +
            ", weixiubaobeiYesnoTypes=" + weixiubaobeiYesnoTypes +
            ", weixiubaobeiYesnoText=" + weixiubaobeiYesnoText +
            ", weixiubaobeiShenheTime=" + DateUtil.convertString(weixiubaobeiShenheTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
