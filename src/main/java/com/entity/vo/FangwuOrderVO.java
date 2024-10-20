package com.entity.vo;

import com.entity.FangwuOrderEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 房屋租赁订单
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("fangwu_order")
public class FangwuOrderVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 订单编号
     */

    @TableField(value = "fangwu_order_uuid_number")
    private String fangwuOrderUuidNumber;


    /**
     * 房屋
     */

    @TableField(value = "fangwu_id")
    private Integer fangwuId;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 预约日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "fangwu_order_time")
    private Date fangwuOrderTime;


    /**
     * 租期/月
     */

    @TableField(value = "fangwu_order_numb")
    private Integer fangwuOrderNumb;


    /**
     * 实付价格
     */

    @TableField(value = "fangwu_order_true_price")
    private Double fangwuOrderTruePrice;


    /**
     * 订单类型
     */

    @TableField(value = "fangwu_order_types")
    private Integer fangwuOrderTypes;


    /**
     * 支付类型
     */

    @TableField(value = "fangwu_order_payment_types")
    private Integer fangwuOrderPaymentTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间 show3 listShow
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
	 * 设置：订单编号
	 */
    public String getFangwuOrderUuidNumber() {
        return fangwuOrderUuidNumber;
    }


    /**
	 * 获取：订单编号
	 */

    public void setFangwuOrderUuidNumber(String fangwuOrderUuidNumber) {
        this.fangwuOrderUuidNumber = fangwuOrderUuidNumber;
    }
    /**
	 * 设置：房屋
	 */
    public Integer getFangwuId() {
        return fangwuId;
    }


    /**
	 * 获取：房屋
	 */

    public void setFangwuId(Integer fangwuId) {
        this.fangwuId = fangwuId;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：预约日期
	 */
    public Date getFangwuOrderTime() {
        return fangwuOrderTime;
    }


    /**
	 * 获取：预约日期
	 */

    public void setFangwuOrderTime(Date fangwuOrderTime) {
        this.fangwuOrderTime = fangwuOrderTime;
    }
    /**
	 * 设置：租期/月
	 */
    public Integer getFangwuOrderNumb() {
        return fangwuOrderNumb;
    }


    /**
	 * 获取：租期/月
	 */

    public void setFangwuOrderNumb(Integer fangwuOrderNumb) {
        this.fangwuOrderNumb = fangwuOrderNumb;
    }
    /**
	 * 设置：实付价格
	 */
    public Double getFangwuOrderTruePrice() {
        return fangwuOrderTruePrice;
    }


    /**
	 * 获取：实付价格
	 */

    public void setFangwuOrderTruePrice(Double fangwuOrderTruePrice) {
        this.fangwuOrderTruePrice = fangwuOrderTruePrice;
    }
    /**
	 * 设置：订单类型
	 */
    public Integer getFangwuOrderTypes() {
        return fangwuOrderTypes;
    }


    /**
	 * 获取：订单类型
	 */

    public void setFangwuOrderTypes(Integer fangwuOrderTypes) {
        this.fangwuOrderTypes = fangwuOrderTypes;
    }
    /**
	 * 设置：支付类型
	 */
    public Integer getFangwuOrderPaymentTypes() {
        return fangwuOrderPaymentTypes;
    }


    /**
	 * 获取：支付类型
	 */

    public void setFangwuOrderPaymentTypes(Integer fangwuOrderPaymentTypes) {
        this.fangwuOrderPaymentTypes = fangwuOrderPaymentTypes;
    }
    /**
	 * 设置：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：订单创建时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show3 listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
