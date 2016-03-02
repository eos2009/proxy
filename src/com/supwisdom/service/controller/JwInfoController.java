package com.supwisdom.service.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.supwisdom.service.libraryService.JwService;

/**
 * 
 * 获取教务动态
 * 
 * @author fei.chen
 *
 */
@Controller
public class JwInfoController {

	/** */
	@Resource
	private JwService jwServiceImpl;

	/**
	 * 返回一个json字符串 教务动态
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findJwInfos")
	public @ResponseBody String findIwInfos() {
		String jwInfos = jwServiceImpl.findJwInfos();
		return jwInfos;
	}

}
