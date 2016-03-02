package com.supwisdom.service.libraryService.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.supwisdom.service.dao.CwDAO;
import com.supwisdom.service.libraryService.CwService;

/**
 * 
 * 获取财务信息
 * 
 * @author fei.chen
 *
 */
@Service
public class CwServiceImpl implements CwService {
	/** */
	private Log log = LogFactory.getLog(CwServiceImpl.class);

	@Resource
	private CwDAO cwDAOImpl;

	@Override
	public String findCwInfos() {
		log.info("CwServiceImpl begin .......");
		List<String> cwInfos = cwDAOImpl.findCwInfo();
		String cwInfoDetail = JSONArray.toJSONString(cwInfos);
		JSONObject json = new JSONObject();
		try {
			json.put("details", cwInfoDetail);
		} catch (JSONException e) {
			log.debug("service  findYktBalance error .", e);
		}
		log.info("json = " + json.toString());
		return json.toString();
	}

}
