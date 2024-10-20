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
 * 缴费
 *
 * @author 
 * @email
 */
@TableName("jiaofei")
public class JiaofeiEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public JiaofeiEntity() {

	}

	public JiaofeiEntity(T t) {
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
     * 缴费编号
     */
    @ColumnInfo(comment="缴费编号",type="varchar(200)")
    @TableField(value = "jiaofei_uuid_number")

    private String jiaofeiUuidNumber;


    /**
     * 用户
     */
    @ColumnInfo(comment="用户",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 缴费标题
     */
    @ColumnInfo(comment="缴费标题",type="varchar(200)")
    @TableField(value = "jiaofei_name")

    private String jiaofeiName;


    /**
     * 缴费类型
     */
    @ColumnInfo(comment="缴费类型",type="int(11)")
    @TableField(value = "jiaofei_types")

    private Integer jiaofeiTypes;


    /**
     * 缴费金额
     */
    @ColumnInfo(comment="缴费金额",type="decimal(10,2)")
    @TableField(value = "jiaofei_new_money")

    private Double jiaofeiNewMoney;


    /**
     * 缴费状态
     */
    @ColumnInfo(comment="缴费状态",type="int(11)")
    @TableField(value = "zhuangtai_types")

    private Integer zhuangtaiTypes;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="录入时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间   listShow
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
	 * 获取：缴费编号
	 */
    public String getJiaofeiUuidNumber() {
        return jiaofeiUuidNumber;
    }
    /**
	 * 设置：缴费编号
	 */

    public void setJiaofeiUuidNumber(String jiaofeiUuidNumber) {
        this.jiaofeiUuidNumber = jiaofeiUuidNumber;
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
	 * 获取：缴费标题
	 */
    public String getJiaofeiName() {
        return jiaofeiName;
    }
    /**
	 * 设置：缴费标题
	 */

    public void setJiaofeiName(String jiaofeiName) {
        this.jiaofeiName = jiaofeiName;
    }
    /**
	 * 获取：缴费类型
	 */
    public Integer getJiaofeiTypes() {
        return jiaofeiTypes;
    }
    /**
	 * 设置：缴费类型
	 */

    public void setJiaofeiTypes(Integer jiaofeiTypes) {
        this.jiaofeiTypes = jiaofeiTypes;
    }
    /**
	 * 获取：缴费金额
	 */
    public Double getJiaofeiNewMoney() {
        return jiaofeiNewMoney;
    }
    /**
	 * 设置：缴费金额
	 */

    public void setJiaofeiNewMoney(Double jiaofeiNewMoney) {
        this.jiaofeiNewMoney = jiaofeiNewMoney;
    }
    /**
	 * 获取：缴费状态
	 */
    public Integer getZhuangtaiTypes() {
        return zhuangtaiTypes;
    }
    /**
	 * 设置：缴费状态
	 */

    public void setZhuangtaiTypes(Integer zhuangtaiTypes) {
        this.zhuangtaiTypes = zhuangtaiTypes;
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
	 * 获取：创建时间   listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间   listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Jiaofei{" +
            ", id=" + id +
            ", jiaofeiUuidNumber=" + jiaofeiUuidNumber +
            ", yonghuId=" + yonghuId +
            ", jiaofeiName=" + jiaofeiName +
            ", jiaofeiTypes=" + jiaofeiTypes +
            ", jiaofeiNewMoney=" + jiaofeiNewMoney +
            ", zhuangtaiTypes=" + zhuangtaiTypes +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
