package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.FangwuOrderEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 房屋租赁订单
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("fangwu_order")
public class FangwuOrderView extends FangwuOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 订单类型的值
	*/
	@ColumnInfo(comment="订单类型的字典表值",type="varchar(200)")
	private String fangwuOrderValue;
	/**
	* 支付类型的值
	*/
	@ColumnInfo(comment="支付类型的字典表值",type="varchar(200)")
	private String fangwuOrderPaymentValue;

	//级联表 房屋信息
					 
		/**
		* 房屋信息 的 企业用户
		*/
		@ColumnInfo(comment="企业用户",type="int(11)")
		private Integer fangwuQiyeyonghuId;
		/**
		* 房屋名称
		*/

		@ColumnInfo(comment="房屋名称",type="varchar(200)")
		private String fangwuName;
		/**
		* 房屋照片
		*/

		@ColumnInfo(comment="房屋照片",type="varchar(200)")
		private String fangwuPhoto;
		/**
		* 赞
		*/

		@ColumnInfo(comment="赞",type="int(11)")
		private Integer zanNumber;
		/**
		* 踩
		*/

		@ColumnInfo(comment="踩",type="int(11)")
		private Integer caiNumber;
		/**
		* 房屋类型
		*/
		@ColumnInfo(comment="房屋类型",type="int(11)")
		private Integer fangwuTypes;
			/**
			* 房屋类型的值
			*/
			@ColumnInfo(comment="房屋类型的字典表值",type="varchar(200)")
			private String fangwuValue;
		/**
		* 房屋热度
		*/

		@ColumnInfo(comment="房屋热度",type="int(11)")
		private Integer fangwuClicknum;
		/**
		* 租金/月
		*/
		@ColumnInfo(comment="租金/月",type="decimal(10,2)")
		private Double fangwuNewMoney;
		/**
		* 房屋介绍
		*/

		@ColumnInfo(comment="房屋介绍",type="longtext")
		private String fangwuContent;
	//级联表 用户
		/**
		* 用户名称
		*/

		@ColumnInfo(comment="用户名称",type="varchar(200)")
		private String yonghuName;
		/**
		* 用户手机号
		*/

		@ColumnInfo(comment="用户手机号",type="varchar(200)")
		private String yonghuPhone;
		/**
		* 用户身份证号
		*/

		@ColumnInfo(comment="用户身份证号",type="varchar(200)")
		private String yonghuIdNumber;
		/**
		* 用户头像
		*/

		@ColumnInfo(comment="用户头像",type="varchar(200)")
		private String yonghuPhoto;
		/**
		* 用户邮箱
		*/

		@ColumnInfo(comment="用户邮箱",type="varchar(200)")
		private String yonghuEmail;
		/**
		* 现有余额
		*/
		@ColumnInfo(comment="现有余额",type="decimal(10,2)")
		private Double newMoney;



	public FangwuOrderView() {

	}

	public FangwuOrderView(FangwuOrderEntity fangwuOrderEntity) {
		try {
			BeanUtils.copyProperties(this, fangwuOrderEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 订单类型的值
	*/
	public String getFangwuOrderValue() {
		return fangwuOrderValue;
	}
	/**
	* 设置： 订单类型的值
	*/
	public void setFangwuOrderValue(String fangwuOrderValue) {
		this.fangwuOrderValue = fangwuOrderValue;
	}
	//当前表的
	/**
	* 获取： 支付类型的值
	*/
	public String getFangwuOrderPaymentValue() {
		return fangwuOrderPaymentValue;
	}
	/**
	* 设置： 支付类型的值
	*/
	public void setFangwuOrderPaymentValue(String fangwuOrderPaymentValue) {
		this.fangwuOrderPaymentValue = fangwuOrderPaymentValue;
	}


	//级联表的get和set 房屋信息
		/**
		* 获取：房屋信息 的 企业用户
		*/
		public Integer getFangwuQiyeyonghuId() {
			return fangwuQiyeyonghuId;
		}
		/**
		* 设置：房屋信息 的 企业用户
		*/
		public void setFangwuQiyeyonghuId(Integer fangwuQiyeyonghuId) {
			this.fangwuQiyeyonghuId = fangwuQiyeyonghuId;
		}

		/**
		* 获取： 房屋名称
		*/
		public String getFangwuName() {
			return fangwuName;
		}
		/**
		* 设置： 房屋名称
		*/
		public void setFangwuName(String fangwuName) {
			this.fangwuName = fangwuName;
		}

		/**
		* 获取： 房屋照片
		*/
		public String getFangwuPhoto() {
			return fangwuPhoto;
		}
		/**
		* 设置： 房屋照片
		*/
		public void setFangwuPhoto(String fangwuPhoto) {
			this.fangwuPhoto = fangwuPhoto;
		}

		/**
		* 获取： 赞
		*/
		public Integer getZanNumber() {
			return zanNumber;
		}
		/**
		* 设置： 赞
		*/
		public void setZanNumber(Integer zanNumber) {
			this.zanNumber = zanNumber;
		}

		/**
		* 获取： 踩
		*/
		public Integer getCaiNumber() {
			return caiNumber;
		}
		/**
		* 设置： 踩
		*/
		public void setCaiNumber(Integer caiNumber) {
			this.caiNumber = caiNumber;
		}
		/**
		* 获取： 房屋类型
		*/
		public Integer getFangwuTypes() {
			return fangwuTypes;
		}
		/**
		* 设置： 房屋类型
		*/
		public void setFangwuTypes(Integer fangwuTypes) {
			this.fangwuTypes = fangwuTypes;
		}


			/**
			* 获取： 房屋类型的值
			*/
			public String getFangwuValue() {
				return fangwuValue;
			}
			/**
			* 设置： 房屋类型的值
			*/
			public void setFangwuValue(String fangwuValue) {
				this.fangwuValue = fangwuValue;
			}

		/**
		* 获取： 房屋热度
		*/
		public Integer getFangwuClicknum() {
			return fangwuClicknum;
		}
		/**
		* 设置： 房屋热度
		*/
		public void setFangwuClicknum(Integer fangwuClicknum) {
			this.fangwuClicknum = fangwuClicknum;
		}

		/**
		* 获取： 租金/月
		*/
		public Double getFangwuNewMoney() {
			return fangwuNewMoney;
		}
		/**
		* 设置： 租金/月
		*/
		public void setFangwuNewMoney(Double fangwuNewMoney) {
			this.fangwuNewMoney = fangwuNewMoney;
		}

		/**
		* 获取： 房屋介绍
		*/
		public String getFangwuContent() {
			return fangwuContent;
		}
		/**
		* 设置： 房屋介绍
		*/
		public void setFangwuContent(String fangwuContent) {
			this.fangwuContent = fangwuContent;
		}


		/**
		* 获取： 用户名称
		*/
		public String getYonghuName() {
			return yonghuName;
		}
		/**
		* 设置： 用户名称
		*/
		public void setYonghuName(String yonghuName) {
			this.yonghuName = yonghuName;
		}

		/**
		* 获取： 用户手机号
		*/
		public String getYonghuPhone() {
			return yonghuPhone;
		}
		/**
		* 设置： 用户手机号
		*/
		public void setYonghuPhone(String yonghuPhone) {
			this.yonghuPhone = yonghuPhone;
		}

		/**
		* 获取： 用户身份证号
		*/
		public String getYonghuIdNumber() {
			return yonghuIdNumber;
		}
		/**
		* 设置： 用户身份证号
		*/
		public void setYonghuIdNumber(String yonghuIdNumber) {
			this.yonghuIdNumber = yonghuIdNumber;
		}

		/**
		* 获取： 用户头像
		*/
		public String getYonghuPhoto() {
			return yonghuPhoto;
		}
		/**
		* 设置： 用户头像
		*/
		public void setYonghuPhoto(String yonghuPhoto) {
			this.yonghuPhoto = yonghuPhoto;
		}

		/**
		* 获取： 用户邮箱
		*/
		public String getYonghuEmail() {
			return yonghuEmail;
		}
		/**
		* 设置： 用户邮箱
		*/
		public void setYonghuEmail(String yonghuEmail) {
			this.yonghuEmail = yonghuEmail;
		}

		/**
		* 获取： 现有余额
		*/
		public Double getNewMoney() {
			return newMoney;
		}
		/**
		* 设置： 现有余额
		*/
		public void setNewMoney(Double newMoney) {
			this.newMoney = newMoney;
		}



	@Override
	public String toString() {
		return "FangwuOrderView{" +
			", fangwuOrderValue=" + fangwuOrderValue +
			", fangwuOrderPaymentValue=" + fangwuOrderPaymentValue +
			", fangwuName=" + fangwuName +
			", fangwuPhoto=" + fangwuPhoto +
			", zanNumber=" + zanNumber +
			", caiNumber=" + caiNumber +
			", fangwuClicknum=" + fangwuClicknum +
			", fangwuNewMoney=" + fangwuNewMoney +
			", fangwuContent=" + fangwuContent +
			", yonghuName=" + yonghuName +
			", yonghuPhone=" + yonghuPhone +
			", yonghuIdNumber=" + yonghuIdNumber +
			", yonghuPhoto=" + yonghuPhoto +
			", yonghuEmail=" + yonghuEmail +
			", newMoney=" + newMoney +
			"} " + super.toString();
	}
}
