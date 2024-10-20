package com.entity.vo;

import com.entity.JiaofeiEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 缴费
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("jiaofei")
public class JiaofeiVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 缴费编号
     */

    @TableField(value = "jiaofei_uuid_number")
    private String jiaofeiUuidNumber;


    /**
     * 缴费标题
     */

    @TableField(value = "jiaofei_name")
    private String jiaofeiName;


    /**
     * 缴费类型
     */

    @TableField(value = "jiaofei_types")
    private Integer jiaofeiTypes;


    /**
     * 缴费金额
     */

    @TableField(value = "jiaofei_new_money")
    private Double jiaofeiNewMoney;


    /**
     * 缴费状态
     */

    @TableField(value = "zhuangtai_types")
    private Integer zhuangtaiTypes;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间  show3 listShow
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
	 * 设置：缴费编号
	 */
    public String getJiaofeiUuidNumber() {
        return jiaofeiUuidNumber;
    }


    /**
	 * 获取：缴费编号
	 */

    public void setJiaofeiUuidNumber(String jiaofeiUuidNumber) {
        this.jiaofeiUuidNumber = jiaofeiUuidNumber;
    }
    /**
	 * 设置：缴费标题
	 */
    public String getJiaofeiName() {
        return jiaofeiName;
    }


    /**
	 * 获取：缴费标题
	 */

    public void setJiaofeiName(String jiaofeiName) {
        this.jiaofeiName = jiaofeiName;
    }
    /**
	 * 设置：缴费类型
	 */
    public Integer getJiaofeiTypes() {
        return jiaofeiTypes;
    }


    /**
	 * 获取：缴费类型
	 */

    public void setJiaofeiTypes(Integer jiaofeiTypes) {
        this.jiaofeiTypes = jiaofeiTypes;
    }
    /**
	 * 设置：缴费金额
	 */
    public Double getJiaofeiNewMoney() {
        return jiaofeiNewMoney;
    }


    /**
	 * 获取：缴费金额
	 */

    public void setJiaofeiNewMoney(Double jiaofeiNewMoney) {
        this.jiaofeiNewMoney = jiaofeiNewMoney;
    }
    /**
	 * 设置：缴费状态
	 */
    public Integer getZhuangtaiTypes() {
        return zhuangtaiTypes;
    }


    /**
	 * 获取：缴费状态
	 */

    public void setZhuangtaiTypes(Integer zhuangtaiTypes) {
        this.zhuangtaiTypes = zhuangtaiTypes;
    }
    /**
	 * 设置：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间  show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show3 listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
