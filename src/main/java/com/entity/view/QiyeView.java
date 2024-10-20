package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.QiyeEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 企业
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("qiye")
public class QiyeView extends QiyeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 企业类型的值
	*/
	@ColumnInfo(comment="企业类型的字典表值",type="varchar(200)")
	private String qiyeValue;

	//级联表 企业用户
		/**
		* 企业用户名称
		*/

		@ColumnInfo(comment="企业用户名称",type="varchar(200)")
		private String qiyeyonghuName;
		/**
		* 企业用户手机号
		*/

		@ColumnInfo(comment="企业用户手机号",type="varchar(200)")
		private String qiyeyonghuPhone;
		/**
		* 企业用户头像
		*/

		@ColumnInfo(comment="企业用户头像",type="varchar(200)")
		private String qiyeyonghuPhoto;
		/**
		* 企业用户邮箱
		*/

		@ColumnInfo(comment="企业用户邮箱",type="varchar(200)")
		private String qiyeyonghuEmail;



	public QiyeView() {

	}

	public QiyeView(QiyeEntity qiyeEntity) {
		try {
			BeanUtils.copyProperties(this, qiyeEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 企业类型的值
	*/
	public String getQiyeValue() {
		return qiyeValue;
	}
	/**
	* 设置： 企业类型的值
	*/
	public void setQiyeValue(String qiyeValue) {
		this.qiyeValue = qiyeValue;
	}


	//级联表的get和set 企业用户

		/**
		* 获取： 企业用户名称
		*/
		public String getQiyeyonghuName() {
			return qiyeyonghuName;
		}
		/**
		* 设置： 企业用户名称
		*/
		public void setQiyeyonghuName(String qiyeyonghuName) {
			this.qiyeyonghuName = qiyeyonghuName;
		}

		/**
		* 获取： 企业用户手机号
		*/
		public String getQiyeyonghuPhone() {
			return qiyeyonghuPhone;
		}
		/**
		* 设置： 企业用户手机号
		*/
		public void setQiyeyonghuPhone(String qiyeyonghuPhone) {
			this.qiyeyonghuPhone = qiyeyonghuPhone;
		}

		/**
		* 获取： 企业用户头像
		*/
		public String getQiyeyonghuPhoto() {
			return qiyeyonghuPhoto;
		}
		/**
		* 设置： 企业用户头像
		*/
		public void setQiyeyonghuPhoto(String qiyeyonghuPhoto) {
			this.qiyeyonghuPhoto = qiyeyonghuPhoto;
		}

		/**
		* 获取： 企业用户邮箱
		*/
		public String getQiyeyonghuEmail() {
			return qiyeyonghuEmail;
		}
		/**
		* 设置： 企业用户邮箱
		*/
		public void setQiyeyonghuEmail(String qiyeyonghuEmail) {
			this.qiyeyonghuEmail = qiyeyonghuEmail;
		}


	@Override
	public String toString() {
		return "QiyeView{" +
			", qiyeValue=" + qiyeValue +
			", qiyeyonghuName=" + qiyeyonghuName +
			", qiyeyonghuPhone=" + qiyeyonghuPhone +
			", qiyeyonghuPhoto=" + qiyeyonghuPhoto +
			", qiyeyonghuEmail=" + qiyeyonghuEmail +
			"} " + super.toString();
	}
}
