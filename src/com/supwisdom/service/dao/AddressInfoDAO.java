package com.supwisdom.service.dao;

import java.util.List;

import com.supwisdom.service.entity.AddressInfo;

/**
 * 获取通讯录信息明细
 * 
 * @author fei.chen
 *
 */
public interface AddressInfoDAO {

	/**
	 * 返回通讯录明细
	 * 
	 * @param type
	 *            类型
	 * @param name
	 *            姓名
	 * @param page
	 *            当前页
	 * @param prePageSizes
	 *            每页数量
	 * @return
	 */
	public List<AddressInfo> findAddressInfo(String type, String name,
			int page, int prePageSizes);
	
	public List<AddressInfo> findOrgAddressInfo(String type, String name,
			int page, int pageSizes);

	/**
	 * 返回查询通讯录总数量
	 * 
	 * @param type
	 *            类型
	 * @param name
	 *            姓名
	 * @return
	 */
	public String findAddressInfoTotals(String type, String name);
	
	public String findOrgAddressInfoTotals(String type, String name);

	/**
	 * 插入一条通讯录信息
	 * 
	 * @param nameStr
	 *            名称
	 * @param email
	 *            邮箱
	 * @param mobilePhone
	 *            手机
	 * @param callNumber
	 *            固话
	 * @param addressStr
	 *            地址
	 */
	public boolean insertAddressInfo(int type,String nameStr, String email,
			String mobilePhone, String callNumber, String addressStr);
	
	public boolean insertOrgAddressInfo(int type,String nameStr, String orgType,
			String orgName, String callNumber, String addressStr);
	
	/**
	 * 更新一条通讯录信息
	 * @param id
	 *            记录id
	 * @param nameStr
	 *            名称
	 * @param email
	 *            邮箱
	 * @param mobilePhone
	 *            手机
	 * @param callNumber
	 *            固话
	 * @param addressStr
	 *            地址
	 */
	public boolean updateAddressInfo(String id,String nameStr, String email,
			String mobilePhone, String callNumber, String addressStr);
	
	public boolean updateOrgAddressInfo(String id,String nameStr, String orgType,
			String orgName, String callNumber, String addressStr);

	/**
	 * 删除一条记录
	 * 
	 * @param id
	 *            记录id
	 * @return
	 */
	public boolean deleteAddressInfo(String id);
}
