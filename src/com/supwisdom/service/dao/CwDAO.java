package com.supwisdom.service.dao;

import java.util.List;


/**
 * 获取财务信息
 * 
 * @author fei.chen
 *
 */
public interface CwDAO {

	/**
	 * 
	 * 返回 工资发放信息记录数量
	 * 
	 * @return
	 */
	public List<String> findCwInfo();

}
