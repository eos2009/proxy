package com.supwisdom.service.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.supwisdom.service.libraryService.LibraryService;

/**
 * 
 * 获取图书借阅信息
 * 
 * @author fei.chen
 *
 */
@Controller
public class LibrarayInfoController {

	/** */
	@Resource
	private LibraryService libraryServiceImpl;

	/**
	 * 返回一个json字符串 当前借阅书籍数量
	 * 
	 * @param barcode
	 *            学工号
	 * @return
	 */
	@RequestMapping(value = "/findLibraryCurCnt/{barcode}")
	public @ResponseBody String findLibraryCurCnt(@PathVariable String barcode) {
		String cnt = null;
		if (barcode != null && !barcode.isEmpty()) {
			cnt = libraryServiceImpl.findLibraryCurCnt(barcode);
		}
		return cnt;
	}
	
	
	/**
	 * 返回一个json字符串 当前借阅书籍数量
	 * 
	 * @param barcode
	 *            学工号
	 * @return
	 */
	@RequestMapping(value = "/findLibraryCurBook/{barcode}")
	public @ResponseBody String findLibraryCurBook(@PathVariable String barcode) {
		String books = null;
		if (barcode != null && !barcode.isEmpty()) {
			books = libraryServiceImpl.findLibraryCurBook(barcode);
		}
		return books;
	}
	
	/**
	 * 返回一个json字符串 当前借阅书籍数量
	 * 
	 * @param barcode
	 *            学工号
	 * @return
	 */
	@RequestMapping(value = "/findLibraryExpireBook/{barcode}")
	public @ResponseBody String findLibraryExpireBook(@PathVariable String barcode) {
		String expireBooks = null;
		if (barcode != null && !barcode.isEmpty()) {
			expireBooks = libraryServiceImpl.findLibraryExpireBook(barcode);
		}
		return expireBooks;
	}

	/**
	 * 返回一个json字符串 最早应还时间
	 * 
	 * @param barcode
	 *            学工号
	 * @return
	 */
	@RequestMapping(value = "/findLibraryFirstDate/{barcode}")
	public @ResponseBody String findLibraryFirstDate(
			@PathVariable String barcode) {
		String dateStr = null;
		if (barcode != null && !barcode.isEmpty()) {
			dateStr = libraryServiceImpl.findLibraryFirstDate(barcode);
		}
		return dateStr;
	}

	/**
	 * 返回一个json字符串 当前到期数据数量
	 * 
	 * @param barcode
	 *            学工号
	 * @return
	 */
	@RequestMapping(value = "/findLibraryDateCnt/{barcode}")
	public @ResponseBody String findLibraryDateCnt(@PathVariable String barcode) {
		String cnt = null;
		if (barcode != null && !barcode.isEmpty()) {
			cnt = libraryServiceImpl.findLibraryDateCnt(barcode);
		}
		return cnt;
	}

	/**
	 * 返回一个json字符串 当前欠费信息
	 * 
	 * @param barcode
	 *            学工号
	 * @return
	 */
	@RequestMapping(value = "/findLibraryFee/{barcode}")
	public @ResponseBody String findLibraryFee(@PathVariable String barcode) {
		String reacer = null;
		if (barcode != null && !barcode.isEmpty()) {
			reacer = libraryServiceImpl.findLibraryFee(barcode);
		}
		return reacer;
	}

	
	
	
	// =============== 消息推送接口 ===================
	/**
	 * 返回一个json字符串 所有图书欠费信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findLibraryAllFee")
	public @ResponseBody String findAllFee() {
		String feeStr = libraryServiceImpl.findAllFee();
		return feeStr;
	}
	
	/**
	 * 返回一个json字符串 所有到期图书信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findLibraryAllExpire")
	public @ResponseBody String findAllExpire() {
		String expireStr = libraryServiceImpl.findAllExpire();
		return expireStr;
	}
	
	/**
	 * 返回一个json字符串 所有图书欠费信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findLibraryAllNearExpire")
	public @ResponseBody String findAllNearExpire() {
		String nearExpireStr = libraryServiceImpl.findAllNearExpire();
		return nearExpireStr;
	}
	
}
