package com.supwisdom.service.controller;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.supwisdom.service.libraryService.AddressInfoService;
import com.supwisdom.service.util.MBase64;

@Controller
public class AddressInfoController {

	/** */
	private Log log = LogFactory.getLog(AddressInfoController.class);

	@Resource
	private AddressInfoService AddressInfoServiceImpl;

	/**
	 * 返回通讯录明细信息
	 * 
	 * @param type
	 *            部门id
	 * @param name
	 *            姓名
	 * @param page
	 *            当前页
	 * @param prePageSizes
	 *            每页条数
	 * @return
	 */
	@RequestMapping(value = "/findAddressInfoDetail/{type}/{name}/{page}/{pageSizes}")
	public @ResponseBody String findAddressInfoDetail(
			@PathVariable String type, @PathVariable String name,
			@PathVariable int page, @PathVariable int pageSizes) {
		log.info("name = "+name);
		name = name.replaceAll("_", "/");
		log.info("name = "+name);
		name = new String(MBase64.decode(name.getBytes()));
		log.info("name = " + name);
		String yktIndoDetail = AddressInfoServiceImpl.findAddressInfo(type,
				name, page, pageSizes);
		return yktIndoDetail;
	}

	@RequestMapping(value = "/findOrgAddressInfoDetail/{type}/{name}/{page}/{pageSizes}")
	public @ResponseBody String findOrgAddressInfoDetail(
			@PathVariable String type, @PathVariable String name,
			@PathVariable int page, @PathVariable int pageSizes) {
		log.info("name = "+name);
		name = name.replaceAll("_", "/");
		log.info("name = "+name);
		name = new String(MBase64.decode(name.getBytes()));
		log.info("AddressInfoController name = " + name);
		String yktIndoDetail = AddressInfoServiceImpl.findOrgAddressInfo(type,
				name, page, pageSizes);
		return yktIndoDetail;
	}

	/**
	 * 返回通讯录总数
	 * 
	 * @param type
	 *            部门
	 * @param name
	 *            姓名
	 * @return
	 */
	@RequestMapping(value = "/findAddressInfoTotals/{type}/{name}")
	public @ResponseBody String findAddressInfoTotals(
			@PathVariable String type, @PathVariable String name) {
		log.info("name = "+name);
		name = name.replaceAll("_", "/");
		log.info("name = "+name);
		name = new String(MBase64.decode(name.getBytes()));
		log.info("name = " + name);
		String totals = AddressInfoServiceImpl
				.findAddressInfoTotals(type, name);
		return totals;
	}

	@RequestMapping(value = "/findOrgAddressInfoTotals/{type}/{name}")
	public @ResponseBody String findOrgAddressInfoTotals(
			@PathVariable String type, @PathVariable String name) {
		log.info("name = "+name);
		name = name.replaceAll("_", "/");
		log.info("name = "+name);
		name = new String(MBase64.decode(name.getBytes()));
		String totals = AddressInfoServiceImpl.findOrgAddressInfoTotals(type,
				name);
		return totals;
	}

	/**
	 * 新增一条通讯录信息
	 * 
	 * @param name
	 *            名称
	 * @param email
	 *            邮箱
	 * @param mobilePhone
	 *            手机
	 * @param callNumber
	 *            固话
	 * @param address
	 *            地址
	 * @return
	 */
	@RequestMapping(value = "/insertAddressInfo/{type}/{name}/{email}/{mobilePhone}/{callNumber}/{address}")
	public @ResponseBody String insertInfo(@PathVariable int type,
			@PathVariable String name, @PathVariable String email,
			@PathVariable String mobilePhone, @PathVariable String callNumber,
			@PathVariable String address) {
		log.info("name = "+name);
		name = name.replaceAll("_", "/");
		address = address.replaceAll("_", "/");
		log.info("name = "+name);
		name = new String(MBase64.decode(name.getBytes()));
		address = new String(MBase64.decode(address.getBytes()));
		String flag = AddressInfoServiceImpl.insertAddressInfo(type, name,
				email, mobilePhone, callNumber, address);
		return flag;
	}

	@RequestMapping(value = "/insertOrgAddressInfo/{type}/{name}/{orgType}/{orgName}/{callNumber}/{address}")
	public @ResponseBody String insertOrgInfo(@PathVariable int type,
			@PathVariable String name, @PathVariable String orgType,
			@PathVariable String orgName, @PathVariable String callNumber,
			@PathVariable String address) {
		log.info("name = "+name);
		name = name.replaceAll("_", "/");
		address = address.replaceAll("_", "/");
		orgType = orgType.replaceAll("_", "/");
		orgName = orgName.replaceAll("_", "/");
		log.info("name = "+name);
		name = new String(MBase64.decode(name.getBytes()));
		address = new String(MBase64.decode(address.getBytes()));
		orgType = new String(MBase64.decode(orgType.getBytes()));
		orgName = new String(MBase64.decode(orgName.getBytes()));
		log.info("name = " + name);
		String flag = AddressInfoServiceImpl.insertOrgAddressInfo(type, name,
				orgType, orgName, callNumber, address);
		return flag;
	}

	/**
	 * 更新一条通讯录信息
	 * 
	 * @param id
	 * 
	 * @param name
	 *            名称
	 * @param email
	 *            邮箱
	 * @param mobilePhone
	 *            手机
	 * @param callNumber
	 *            固话
	 * @param address
	 *            地址
	 * @return
	 */
	@RequestMapping(value = "/updateAddressInfo/{id}/{name}/{email}/{mobilePhone}/{callNumber}/{address}", method = RequestMethod.GET)
	public @ResponseBody String updateInfo(@PathVariable String id,
			@PathVariable String name, @PathVariable String email,
			@PathVariable String mobilePhone, @PathVariable String callNumber,
			@PathVariable String address) {
		log.info("name = "+name);
		name = name.replaceAll("_", "/");
		address = address.replaceAll("_", "/");
		log.info("name = "+name);
		name = new String(MBase64.decode(name.getBytes()));
		address = new String(MBase64.decode(address.getBytes()));
		log.info("name = " + name);
		String flag = AddressInfoServiceImpl.updateAddressInfo(id, name, email,
				mobilePhone, callNumber, address);
		return flag;
	}

	@RequestMapping(value = "/updateOrgAddressInfo/{id}/{name}/{orgType}/{orgName}/{callNumber}/{address}")
	public @ResponseBody String updateOrgInfo(@PathVariable String id,
			@PathVariable String name, @PathVariable String orgType,
			@PathVariable String orgName, @PathVariable String callNumber,
			@PathVariable String address) {
		log.info("name = "+name);
		name = name.replaceAll("_", "/");
		address = address.replaceAll("_", "/");
		orgType = orgType.replaceAll("_", "/");
		orgName = orgName.replaceAll("_", "/");
		log.info("name = "+name);
		name = new String(MBase64.decode(name.getBytes()));
		address = new String(MBase64.decode(address.getBytes()));
		orgType = new String(MBase64.decode(orgType.getBytes()));
		orgName = new String(MBase64.decode(orgName.getBytes()));
		log.info("name = " + name);
		String flag = AddressInfoServiceImpl.updateOrgAddressInfo(id, name,
				orgType, orgName, callNumber, address);
		return flag;
	}

	/**
	 * 删除一条通讯记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteAddressInfo/{id}")
	public @ResponseBody String deleteInfo(@PathVariable String id) {
		log.info("id = " + id);
		String flag = AddressInfoServiceImpl.deleteAddressInfo(id);
		return flag;
	}
}
