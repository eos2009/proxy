package com.supwisdom.service.controller;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.supwisdom.service.libraryService.CalZhouXQService;

/**
 * 
 * 获取日程周学期
 * 
 * @author fei.chen
 *
 */
@Controller
public class CalInfoController {

	private Log log = LogFactory.getLog(CalInfoController.class);
	/** */
	@Resource
	private CalZhouXQService CalZhouXQServiceImpl;

	/**
	 * 返回一个json字符串 当前借阅书籍数量
	 * 
	 * @param date
	 *            日期
	 * @return
	 */
	@RequestMapping(value = "/findCalZhouXq/{date}")
	public @ResponseBody String GetZhouXq(@PathVariable String date) {
		String zhouXq = null;
		if (date != null && !date.isEmpty()) {
			zhouXq = CalZhouXQServiceImpl.getZhouXQ(date);
			log.info("zhouXq = "+zhouXq);
		}
		return zhouXq;
	}
}
