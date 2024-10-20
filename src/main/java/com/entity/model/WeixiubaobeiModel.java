package com.entity.model;

import com.entity.WeixiubaobeiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 维修报备
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class WeixiubaobeiModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 报备标题
     */
    private String weixiubaobeiName;


    /**
     * 报备缘由
     */
    private String weixiubaobeiText;


    /**
     * 报备类型
     */
    private Integer weixiubaobeiTypes;


    /**
     * 申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 报备时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date weixiubaobeiTime;


    /**
     * 报备状态
     */
    private Integer weixiubaobeiYesnoTypes;


    /**
     * 处理结果
     */
    private String weixiubaobeiYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date weixiubaobeiShenheTime;


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
