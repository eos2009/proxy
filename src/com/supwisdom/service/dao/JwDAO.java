package com.supwisdom.service.dao;

import java.util.List;

import com.supwisdom.service.entity.JwContent;

/**
 * 获取教务动态
 * 
 * @author fei.chen
 *
 */
public interface JwDAO {

	/**
	 * 
	 * 返回 获取教务动态
	 * 
	 * @return
	 */
	public List<JwContent> findJwInfos();

}
