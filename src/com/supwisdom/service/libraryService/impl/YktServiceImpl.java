package com.supwisdom.service.libraryService.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.supwisdom.service.dao.YktDAO;
import com.supwisdom.service.entity.CzDataVO;
import com.supwisdom.service.entity.YktMessage;
import com.supwisdom.service.libraryService.YktService;

/**
 * 
 * 获取一卡通余额信息和消费/充值明细
 * 
 * @author fei.chen
 *
 */
@Service
public class YktServiceImpl implements YktService {
	/** */
	private Log log = LogFactory.getLog(YktServiceImpl.class);

	@Resource
	private YktDAO YktDAOImpl;

	public YktDAO getYktDAOImpl() {
		return YktDAOImpl;
	}

	public void setYktDAOImpl(YktDAO yktDAOImpl) {
		YktDAOImpl = yktDAOImpl;
	}

	@Override
	public String findYktBalance(String READERBARCODE) {
		log.info("service begin .......");
		String balance = YktDAOImpl.findYktBalance(READERBARCODE);
		JSONObject json = new JSONObject();
		try {
			json.put("balance", balance);
		} catch (JSONException e) {
			log.debug("service  findYktBalance error .", e);
		}
		return json.toString();
	}

	@Override
	public String findYktInfoDetail(String READERBARCODE, String startDate,
			String endDate, int page, int prePageSizes) {
		log.info("service begin .......");
		List<CzDataVO> yktInfos = YktDAOImpl.findYktInfoDetail(READERBARCODE,
				startDate, endDate, page, prePageSizes);
		String yktInfoDetail = JSONArray.toJSONString(yktInfos);
		JSONObject json = new JSONObject();
		try {
			json.put("details", yktInfoDetail);
		} catch (JSONException e) {
			log.debug("service  findYktBalance error .", e);
		}

		log.info("json = " + json.toString());
		return json.toString();
	}

	@Override
	public String findYktInfoTotals(String READERBARCODE, String startDate,
			String endDate, int prePageSizes) {
		log.info("service begin .......");
		String totals = YktDAOImpl.findYktInfoTotals(READERBARCODE, startDate,
				endDate, prePageSizes);
		JSONObject json = new JSONObject();
		try {
			json.put("totals", totals);
		} catch (JSONException e) {
			log.debug("service  findYktBalance error .", e);
		}
		return json.toString();
	}

	
	
	//======================      消息推送  =============================
	@Override
	public String findSaveInfo() {
		log.info("service findSaveInfo begin .......");
		List<YktMessage> yktInfos = YktDAOImpl.findSaveInfo();
		String yktInfoDetail = JSONArray.toJSONString(yktInfos);
		JSONObject json = new JSONObject();
		try {
			json.put("save", yktInfoDetail);
		} catch (JSONException e) {
			log.debug("service  findYktBalance error .", e);
		}

		log.info("json = " + json.toString());
		return json.toString();
	}

	@Override
	public String findStateChanges() {
		log.info("service findSaveInfo begin .......");
		List<YktMessage> yktInfos = YktDAOImpl.findStateChanges();
		String yktInfoDetail = JSONArray.toJSONString(yktInfos);
		JSONObject json = new JSONObject();
		try {
			json.put("state", yktInfoDetail);
		} catch (JSONException e) {
			log.debug("service  findYktBalance error .", e);
		}

		log.info("json = " + json.toString());
		return json.toString();
	}

}
