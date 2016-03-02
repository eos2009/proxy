package com.supwisdom.service.libraryService;



/**
 * 获取通讯录信息
 * 
 * @author fei.chen
 *
 */
public interface AddressInfoService {
	/**
	 * 返回通讯录明细 service
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
	public String findAddressInfo(String type, String name, int page,
			int prePageSizes);
	
	public String findOrgAddressInfo(String type, String name,
			int page, int pageSizes);

	/**
	 * 返回查询通讯录总数量 service
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
	public String insertAddressInfo(int type,String nameStr, String email,
			String mobilePhone, String callNumber, String addressStr);
	
	public String insertOrgAddressInfo(int type,String nameStr, String orgType,
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
	public String updateAddressInfo(String id,String nameStr, String email,
			String mobilePhone, String callNumber, String addressStr);

	
	public String updateOrgAddressInfo(String id,String nameStr, String orgType,
			String orgName, String callNumber, String addressStr);
	/**
	 * 
	 * @param id
	 *            记录id
	 * @return
	 */
	public String deleteAddressInfo(String id);
}
