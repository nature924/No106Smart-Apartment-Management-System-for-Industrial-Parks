package com.entity.vo;

import com.entity.WeixiubaobeiEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 维修报备
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("weixiubaobei")
public class WeixiubaobeiVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 报备标题
     */

    @TableField(value = "weixiubaobei_name")
    private String weixiubaobeiName;


    /**
     * 报备缘由
     */

    @TableField(value = "weixiubaobei_text")
    private String weixiubaobeiText;


    /**
     * 报备类型
     */

    @TableField(value = "weixiubaobei_types")
    private Integer weixiubaobeiTypes;


    /**
     * 申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 报备时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "weixiubaobei_time")
    private Date weixiubaobeiTime;


    /**
     * 报备状态
     */

    @TableField(value = "weixiubaobei_yesno_types")
    private Integer weixiubaobeiYesnoTypes;


    /**
     * 处理结果
     */

    @TableField(value = "weixiubaobei_yesno_text")
    private String weixiubaobeiYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "weixiubaobei_shenhe_time")
    private Date weixiubaobeiShenheTime;


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
	 * 设置：报备标题
	 */
    public String getWeixiubaobeiName() {
        return weixiubaobeiName;
    }


    /**
	 * 获取：报备标题
	 */

    public void setWeixiubaobeiName(String weixiubaobeiName) {
        this.weixiubaobeiName = weixiubaobeiName;
    }
    /**
	 * 设置：报备缘由
	 */
    public String getWeixiubaobeiText() {
        return weixiubaobeiText;
    }


    /**
	 * 获取：报备缘由
	 */

    public void setWeixiubaobeiText(String weixiubaobeiText) {
        this.weixiubaobeiText = weixiubaobeiText;
    }
    /**
	 * 设置：报备类型
	 */
    public Integer getWeixiubaobeiTypes() {
        return weixiubaobeiTypes;
    }


    /**
	 * 获取：报备类型
	 */

    public void setWeixiubaobeiTypes(Integer weixiubaobeiTypes) {
        this.weixiubaobeiTypes = weixiubaobeiTypes;
    }
    /**
	 * 设置：申请时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：申请时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：报备时间
	 */
    public Date getWeixiubaobeiTime() {
        return weixiubaobeiTime;
    }


    /**
	 * 获取：报备时间
	 */

    public void setWeixiubaobeiTime(Date weixiubaobeiTime) {
        this.weixiubaobeiTime = weixiubaobeiTime;
    }
    /**
	 * 设置：报备状态
	 */
    public Integer getWeixiubaobeiYesnoTypes() {
        return weixiubaobeiYesnoTypes;
    }


    /**
	 * 获取：报备状态
	 */

    public void setWeixiubaobeiYesnoTypes(Integer weixiubaobeiYesnoTypes) {
        this.weixiubaobeiYesnoTypes = weixiubaobeiYesnoTypes;
    }
    /**
	 * 设置：处理结果
	 */
    public String getWeixiubaobeiYesnoText() {
        return weixiubaobeiYesnoText;
    }


    /**
	 * 获取：处理结果
	 */

    public void setWeixiubaobeiYesnoText(String weixiubaobeiYesnoText) {
        this.weixiubaobeiYesnoText = weixiubaobeiYesnoText;
    }
    /**
	 * 设置：审核时间
	 */
    public Date getWeixiubaobeiShenheTime() {
        return weixiubaobeiShenheTime;
    }


    /**
	 * 获取：审核时间
	 */

    public void setWeixiubaobeiShenheTime(Date weixiubaobeiShenheTime) {
        this.weixiubaobeiShenheTime = weixiubaobeiShenheTime;
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
