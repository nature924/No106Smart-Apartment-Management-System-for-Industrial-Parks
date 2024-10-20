package com.entity.model;

import com.entity.SheshiyuyueEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 设施预约
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class SheshiyuyueModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 预约编号
     */
    private String sheshiyuyueUuidNumber;


    /**
     * 预约标题
     */
    private String sheshiyuyueName;


    /**
     * 设施名称
     */
    private String sheshiyuyueSheshi;


    /**
     * 设施类型
     */
    private Integer sheshiyuyueTypes;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 预约理由
     */
    private String sheshiyuyueText;


    /**
     * 房屋预约时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 预约状态
     */
    private Integer sheshiyuyueYesnoTypes;


    /**
     * 审核回复
     */
    private String sheshiyuyueYesnoText;


    /**
     * 创建时间 show3 listShow
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
	 * 获取：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3 listShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
