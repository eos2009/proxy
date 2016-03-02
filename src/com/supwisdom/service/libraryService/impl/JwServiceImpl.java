package com.supwisdom.service.libraryService.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.supwisdom.service.dao.JwDAO;
import com.supwisdom.service.entity.JwContent;
import com.supwisdom.service.libraryService.JwService;

/**
 * 
 * 获取教务动态
 * 
 * @author fei.chen
 *
 */
@Service
public class JwServiceImpl implements JwService {
	/** */
	private Log log = LogFactory.getLog(JwServiceImpl.class);

	@Resource
	private JwDAO jwDAOImpl;
	public JwDAO getJwDAOImpl() {
		return jwDAOImpl;
	}
	public void setJwDAOImpl(JwDAO jwDAOImpl) {
		this.jwDAOImpl = jwDAOImpl;
	}

	@Override
	public String findJwInfos() {
		log.info("service begin .......");
		List<JwContent> jwInfos = jwDAOImpl.findJwInfos();
		String jwInfoDetail = JSONArray.toJSONString(jwInfos);
		JSONObject json = new JSONObject();
		try {
			json.put("details", jwInfoDetail);
		} catch (JSONException e) {
			log.debug("service  findYktBalance error .", e);
		}
		log.info("json = " + json.toString());
		return json.toString();
	}

}
