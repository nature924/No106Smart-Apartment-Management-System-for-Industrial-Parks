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
 * 房屋租赁订单
 *
 * @author 
 * @email
 */
@TableName("fangwu_order")
public class FangwuOrderEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public FangwuOrderEntity() {

	}

	public FangwuOrderEntity(T t) {
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
     * 订单编号
     */
    @ColumnInfo(comment="订单编号",type="varchar(200)")
    @TableField(value = "fangwu_order_uuid_number")

    private String fangwuOrderUuidNumber;


    /**
     * 房屋
     */
    @ColumnInfo(comment="房屋",type="int(11)")
    @TableField(value = "fangwu_id")

    private Integer fangwuId;


    /**
     * 用户
     */
    @ColumnInfo(comment="用户",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 预约日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="预约日期",type="timestamp")
    @TableField(value = "fangwu_order_time")

    private Date fangwuOrderTime;


    /**
     * 租期/月
     */
    @ColumnInfo(comment="租期/月",type="int(11)")
    @TableField(value = "fangwu_order_numb")

    private Integer fangwuOrderNumb;


    /**
     * 实付价格
     */
    @ColumnInfo(comment="实付价格",type="decimal(10,2)")
    @TableField(value = "fangwu_order_true_price")

    private Double fangwuOrderTruePrice;


    /**
     * 订单类型
     */
    @ColumnInfo(comment="订单类型",type="int(11)")
    @TableField(value = "fangwu_order_types")

    private Integer fangwuOrderTypes;


    /**
     * 支付类型
     */
    @ColumnInfo(comment="支付类型",type="int(11)")
    @TableField(value = "fangwu_order_payment_types")

    private Integer fangwuOrderPaymentTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="订单创建时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


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
	 * 获取：订单编号
	 */
    public String getFangwuOrderUuidNumber() {
        return fangwuOrderUuidNumber;
    }
    /**
	 * 设置：订单编号
	 */

    public void setFangwuOrderUuidNumber(String fangwuOrderUuidNumber) {
        this.fangwuOrderUuidNumber = fangwuOrderUuidNumber;
    }
    /**
	 * 获取：房屋
	 */
    public Integer getFangwuId() {
        return fangwuId;
    }
    /**
	 * 设置：房屋
	 */

    public void setFangwuId(Integer fangwuId) {
        this.fangwuId = fangwuId;
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
	 * 获取：预约日期
	 */
    public Date getFangwuOrderTime() {
        return fangwuOrderTime;
    }
    /**
	 * 设置：预约日期
	 */

    public void setFangwuOrderTime(Date fangwuOrderTime) {
        this.fangwuOrderTime = fangwuOrderTime;
    }
    /**
	 * 获取：租期/月
	 */
    public Integer getFangwuOrderNumb() {
        return fangwuOrderNumb;
    }
    /**
	 * 设置：租期/月
	 */

    public void setFangwuOrderNumb(Integer fangwuOrderNumb) {
        this.fangwuOrderNumb = fangwuOrderNumb;
    }
    /**
	 * 获取：实付价格
	 */
    public Double getFangwuOrderTruePrice() {
        return fangwuOrderTruePrice;
    }
    /**
	 * 设置：实付价格
	 */

    public void setFangwuOrderTruePrice(Double fangwuOrderTruePrice) {
        this.fangwuOrderTruePrice = fangwuOrderTruePrice;
    }
    /**
	 * 获取：订单类型
	 */
    public Integer getFangwuOrderTypes() {
        return fangwuOrderTypes;
    }
    /**
	 * 设置：订单类型
	 */

    public void setFangwuOrderTypes(Integer fangwuOrderTypes) {
        this.fangwuOrderTypes = fangwuOrderTypes;
    }
    /**
	 * 获取：支付类型
	 */
    public Integer getFangwuOrderPaymentTypes() {
        return fangwuOrderPaymentTypes;
    }
    /**
	 * 设置：支付类型
	 */

    public void setFangwuOrderPaymentTypes(Integer fangwuOrderPaymentTypes) {
        this.fangwuOrderPaymentTypes = fangwuOrderPaymentTypes;
    }
    /**
	 * 获取：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：订单创建时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
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
        return "FangwuOrder{" +
            ", id=" + id +
            ", fangwuOrderUuidNumber=" + fangwuOrderUuidNumber +
            ", fangwuId=" + fangwuId +
            ", yonghuId=" + yonghuId +
            ", fangwuOrderTime=" + DateUtil.convertString(fangwuOrderTime,"yyyy-MM-dd") +
            ", fangwuOrderNumb=" + fangwuOrderNumb +
            ", fangwuOrderTruePrice=" + fangwuOrderTruePrice +
            ", fangwuOrderTypes=" + fangwuOrderTypes +
            ", fangwuOrderPaymentTypes=" + fangwuOrderPaymentTypes +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
