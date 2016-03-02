package com.supwisdom.service.libraryService.impl;


import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.supwisdom.service.dao.LibraryDAO;
import com.supwisdom.service.entity.Book;
import com.supwisdom.service.entity.Reacer;
import com.supwisdom.service.libraryService.LibraryService;

/**
 * 
 * 获取图书借阅信息
 * @author fei.chen
 *
 */
@Service
public class LibraryServiceImpl implements LibraryService {
	/** */
	private Log log = LogFactory.getLog(LibraryServiceImpl.class);
	
	@Resource
	private LibraryDAO LibraryDAOImpl;

	public LibraryDAO getLibraryDAOImpl() {
		return LibraryDAOImpl;
	}

	public void setLibraryDAOImpl(LibraryDAO libraryDAOImpl) {
		LibraryDAOImpl = libraryDAOImpl;
	}

	@Override
	public String findLibraryCurCnt(String READERBARCODE) {
		log.info("service begin .......");
		System.out.println("service begin .......");
		String cnt = LibraryDAOImpl.findLibraryCurCnt(READERBARCODE);
		JSONObject json = new JSONObject();
		try {
			json.put("cnt", cnt);
		} catch (JSONException e) {
			log.debug("service  findLibraryDateCnt error .", e);
		}
		return json.toString();
	}
	
	@Override
	public String findLibraryCurBook(String READERBARCODE) {
		log.info("service begin .......");
		System.out.println("service begin .......");
		List<Book> list = LibraryDAOImpl.findLibraryCurBook(READERBARCODE);
		String books = JSONArray.toJSONString(list);
		JSONObject json = new JSONObject();
		try {
			json.put("books", books);
		} catch (JSONException e) {
			log.debug("service  findLibraryDateCnt error .", e);
		}
		return json.toString();
	}
	
	
	@Override
	public String findLibraryExpireBook(String READERBARCODE) {
		log.info("service begin .......");
		System.out.println("service begin .......");
		List<Book> list = LibraryDAOImpl.findLibraryExpireBook(READERBARCODE);
		String expireBooks = JSONArray.toJSONString(list);
		JSONObject json = new JSONObject();
		try {
			json.put("expireBooks", expireBooks);
		} catch (JSONException e) {
			log.debug("service  findLibraryDateCnt error .", e);
		}
		return json.toString();
	}
	

	@Override
	public String findLibraryDateCnt(String READERBARCODE) {
		log.info("service begin .......");
		String cnt = LibraryDAOImpl.findLibraryDateCnt(READERBARCODE);
		log.info(cnt);
		JSONObject json = new JSONObject();
		try {
			json.put("cnt", cnt);
		} catch (JSONException e) {
			log.debug("service  findLibraryDateCnt error .", e);
		}
		return json.toString();
	}

	@Override
	public String findLibraryFee(String READERBARCODE) {
		log.info("service begin .......");
		Reacer reacer = LibraryDAOImpl.findLibraryFee(READERBARCODE);
		JSONObject json = new JSONObject();
		try {
			if (reacer != null) {
					json.put("CARDNO", reacer.getCARDNO());
					json.put("FINE", reacer.getFINE());
			} else {
				json.put("msg", "faild");
			}
		} catch (JSONException e) {
			log.debug("service findLibraryFee error .", e);
		}
		return json.toString();
	}

	@Override
	public String findLibraryFirstDate(String READERBARCODE) {
		log.info("service begin .......");
		String dateStr = LibraryDAOImpl.findLibraryFirstDate(READERBARCODE);
		JSONObject json = new JSONObject();
		log.info("dateStr = "+dateStr);
		try {
			json.put("dateStr", dateStr);
		} catch (JSONException e) {
			log.debug("service  findLibraryFirstDate error .", e);
		}
		log.info("json = "+json.toString());
		return json.toString();
	}

	@Override
	public String findAllExpire() {
		log.info("service findAllExpire begin .......");
		List<Reacer> list = LibraryDAOImpl.findAllExpire();
		String expireInfoDetail = JSONArray.toJSONString(list);
		JSONObject json = new JSONObject();
		try {
			json.put("expire", expireInfoDetail);
		} catch (JSONException e) {
			log.debug("service  findAllExpire error .", e);
		}
		log.info("json = " + json.toString());
		return json.toString();
	}

	@Override
	public String findAllFee() {
		log.info("service findAllFee begin .......");
		List<Reacer> list = LibraryDAOImpl.findAllFee();
		String feeInfoDetail = JSONArray.toJSONString(list);
		JSONObject json = new JSONObject();
		try {
			json.put("fee", feeInfoDetail);
		} catch (JSONException e) {
			log.debug("service  findAllFee error .", e);
		}
		log.info("json = " + json.toString());
		return json.toString();
	}

	@Override
	public String findAllNearExpire() {
		log.info("service findAllNearExpire begin .......");
		List<Reacer> list = LibraryDAOImpl.findAllNearExpire();
		String nearExpireInfoDetail = JSONArray.toJSONString(list);
		JSONObject json = new JSONObject();
		try {
			json.put("nearExpire", nearExpireInfoDetail);
		} catch (JSONException e) {
			log.debug("service  findAllNearExpire error .", e);
		}
		log.info("json = " + json.toString());
		return json.toString();
	}

}
