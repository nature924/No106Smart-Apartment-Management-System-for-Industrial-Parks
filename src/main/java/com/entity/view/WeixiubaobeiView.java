package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.WeixiubaobeiEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 维修报备
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("weixiubaobei")
public class WeixiubaobeiView extends WeixiubaobeiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 报备类型的值
	*/
	@ColumnInfo(comment="报备类型的字典表值",type="varchar(200)")
	private String weixiubaobeiValue;
	/**
	* 报备状态的值
	*/
	@ColumnInfo(comment="报备状态的字典表值",type="varchar(200)")
	private String weixiubaobeiYesnoValue;

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
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer dataDelete;



	public WeixiubaobeiView() {

	}

	public WeixiubaobeiView(WeixiubaobeiEntity weixiubaobeiEntity) {
		try {
			BeanUtils.copyProperties(this, weixiubaobeiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 报备类型的值
	*/
	public String getWeixiubaobeiValue() {
		return weixiubaobeiValue;
	}
	/**
	* 设置： 报备类型的值
	*/
	public void setWeixiubaobeiValue(String weixiubaobeiValue) {
		this.weixiubaobeiValue = weixiubaobeiValue;
	}
	//当前表的
	/**
	* 获取： 报备状态的值
	*/
	public String getWeixiubaobeiYesnoValue() {
		return weixiubaobeiYesnoValue;
	}
	/**
	* 设置： 报备状态的值
	*/
	public void setWeixiubaobeiYesnoValue(String weixiubaobeiYesnoValue) {
		this.weixiubaobeiYesnoValue = weixiubaobeiYesnoValue;
	}


	//级联表的get和set 用户

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

		/**
		* 获取： 逻辑删除
		*/
		public Integer getdataDelete() {
			return dataDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setdataDelete(Integer dataDelete) {
			this.dataDelete = dataDelete;
		}


	@Override
	public String toString() {
		return "WeixiubaobeiView{" +
			", weixiubaobeiValue=" + weixiubaobeiValue +
			", weixiubaobeiYesnoValue=" + weixiubaobeiYesnoValue +
			", yonghuName=" + yonghuName +
			", yonghuPhone=" + yonghuPhone +
			", yonghuIdNumber=" + yonghuIdNumber +
			", yonghuPhoto=" + yonghuPhoto +
			", yonghuEmail=" + yonghuEmail +
			", newMoney=" + newMoney +
			", dataDelete=" + dataDelete +
			"} " + super.toString();
	}
}
