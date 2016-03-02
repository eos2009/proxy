package com.supwisdom.service.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.supwisdom.service.libraryService.CwService;

/**
 * 
 * 获取工资信息
 * 
 * @author fei.chen
 *
 */
@Controller
public class CwInfoController {

	/** */
	@Resource
	private CwService cwServiceImpl;

	/**
	 * 返回一个json字符串 教务动态
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findCwInfos")
	public @ResponseBody String findIwInfos() {
		String cwInfos = cwServiceImpl.findCwInfos();
		return cwInfos;
	}

}
