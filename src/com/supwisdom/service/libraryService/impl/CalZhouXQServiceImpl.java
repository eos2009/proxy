package com.supwisdom.service.libraryService.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.supwisdom.service.dao.CalZhouXQDAO;
import com.supwisdom.service.libraryService.CalZhouXQService;

@Service
public class CalZhouXQServiceImpl implements CalZhouXQService{
    private Log log = LogFactory.getLog(CalZhouXQServiceImpl.class);
	@Resource
	private CalZhouXQDAO CalZhouXQDAOImpl;

	public CalZhouXQDAO getCalZhouXQDAOImpl() {
		return CalZhouXQDAOImpl;
	}
	@Override
	public String getZhouXQ(String date) {
		log.info("service begin .......");
		String zhouXq = CalZhouXQDAOImpl.getZhouXQ(date);
		log.info("zhouXq ="+zhouXq);
		JSONObject json = new JSONObject();
		try {
			json.put("zhouXq", zhouXq);
		} catch (JSONException e) {
			log.debug("service  findLibraryDateCnt error .", e);
		}
		return json.toString();
	}

}
