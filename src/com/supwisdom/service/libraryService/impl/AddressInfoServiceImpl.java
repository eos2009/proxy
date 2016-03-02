package com.supwisdom.service.libraryService.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.supwisdom.service.dao.AddressInfoDAO;
import com.supwisdom.service.entity.AddressInfo;
import com.supwisdom.service.libraryService.AddressInfoService;

/**
 * 获取通讯录信息
 * @author fei.chen
 *
 */
@Service
public class AddressInfoServiceImpl implements AddressInfoService {
	/** */
	private Log log = LogFactory.getLog(AddressInfoServiceImpl.class);

	@Resource
	private AddressInfoDAO addressInfoDAOImpl;


	public AddressInfoDAO getAddressInfoDAOImpl() {
		return addressInfoDAOImpl;
	}

	public void setAddressInfoDAOImpl(AddressInfoDAO addressInfoDAOImpl) {
		this.addressInfoDAOImpl = addressInfoDAOImpl;
	}

	@Override
	public String findAddressInfo(String type, String name,
			int page, int prePageSizes) {
		log.info("service begin .......");
		List<AddressInfo> addressInfos = addressInfoDAOImpl.findAddressInfo(type, name, page, prePageSizes);
		String addressInfoDetail = JSONArray.toJSONString(addressInfos);
		System.out.println("addressInfoDetail = " +addressInfoDetail);
		JSONObject json = new JSONObject();
		try {
			json.put("details", addressInfoDetail);
		} catch (JSONException e) {
			log.debug("service  findYktBalance error .", e);
		}

		log.info("json = " + json.toString());
		return json.toString();
	}
	
	
	@Override
	public String findOrgAddressInfo(String type, String name,
			int page, int prePageSizes) {
		log.info("service begin .......");
		List<AddressInfo> addressInfos = addressInfoDAOImpl.findOrgAddressInfo(type, name, page, prePageSizes);
		String addressInfoDetail = JSONArray.toJSONString(addressInfos);
		System.out.println("addressInfoDetail = " +addressInfoDetail);
		JSONObject json = new JSONObject();
		try {
			json.put("details", addressInfoDetail);
		} catch (JSONException e) {
			log.debug("service  findYktBalance error .", e);
		}

		log.info("json = " + json.toString());
		return json.toString();
	}
	
	

	@Override
	public String findAddressInfoTotals(String type, String name) {
		log.info("service begin .......");
		String totals = addressInfoDAOImpl.findAddressInfoTotals(type, name);
		JSONObject json = new JSONObject();
		try {
			json.put("totals", totals);
		} catch (JSONException e) {
			log.debug("service  findYktBalance error .", e);
		}
		return json.toString();
	}
	
	@Override
	public String findOrgAddressInfoTotals(String type, String name) {
		log.info("service begin .......");
		String totals = addressInfoDAOImpl.findOrgAddressInfoTotals(type, name);
		JSONObject json = new JSONObject();
		try {
			json.put("totals", totals);
		} catch (JSONException e) {
			log.debug("service  findYktBalance error .", e);
		}
		return json.toString();
	}

	@Override
	public String insertAddressInfo(int type,String nameStr, String email,
			String mobilePhone, String callNumber, String addressStr) {
		log.info("service begin .......");
		boolean flag = addressInfoDAOImpl.insertAddressInfo(type,nameStr, email, mobilePhone, callNumber, addressStr);
		JSONObject json = new JSONObject();
		try {
			json.put("flag", flag);
		} catch (JSONException e) {
			log.debug("service  findYktBalance error .", e);
		}
		return json.toString();
	}
	
	
	@Override
	public String insertOrgAddressInfo(int type,String nameStr, String orgType,
			String orgName, String callNumber, String addressStr) {
		log.info("service begin .......");
		boolean flag = addressInfoDAOImpl.insertOrgAddressInfo(type,nameStr, orgType, orgName, callNumber, addressStr);
		JSONObject json = new JSONObject();
		try {
			json.put("flag", flag);
		} catch (JSONException e) {
			log.debug("service  findYktBalance error .", e);
		}
		return json.toString();
	}
	
	
	
	

	@Override
	public String deleteAddressInfo(String id) {
		log.info("service begin .......");
		boolean flag = addressInfoDAOImpl.deleteAddressInfo(id);
		JSONObject json = new JSONObject();
		try {
			json.put("flag", flag);
		} catch (JSONException e) {
			log.debug("service  findYktBalance error .", e);
		}
		return json.toString();
	}

	@Override
	public String updateAddressInfo(String id, String nameStr, String email,
			String mobilePhone, String callNumber, String addressStr) {
		log.info("service begin .......");
		log.info("name = " + nameStr);
		boolean flag = addressInfoDAOImpl.updateAddressInfo(id, nameStr, email, mobilePhone, callNumber, addressStr);
		JSONObject json = new JSONObject();
		try {
			json.put("flag", flag);
		} catch (JSONException e) {
			log.debug("service  findYktBalance error .", e);
		}
		return json.toString();
	}

	@Override
	public String updateOrgAddressInfo(String id, String nameStr,
			String orgType, String orgName, String callNumber, String addressStr) {
		log.info("service begin .......");
		log.info("name = " + nameStr);
		boolean flag = addressInfoDAOImpl.updateOrgAddressInfo(id, nameStr, orgType, orgName, callNumber, addressStr);
		JSONObject json = new JSONObject();
		try {
			json.put("flag", flag);
		} catch (JSONException e) {
			log.debug("service  findYktBalance error .", e);
		}
		return json.toString();
	}

}
