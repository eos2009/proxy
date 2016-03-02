package com.supwisdom.service.dao;

import java.util.List;

import com.supwisdom.service.entity.CzDataVO;
import com.supwisdom.service.entity.YktMessage;


/**
 * 获取一卡通余额信息和消费/充值明细
 * 
 * @author fei.chen
 *
 */
public interface YktDAO {

	/**
	 * 
	 * 返回 当前一卡通余额
	 * 
	 * @param READERBARCODE
	 *            学工号
	 * @return
	 */
	public String findYktBalance(String READERBARCODE);

	/**
	 * 返回查询时间内的消费/充值明细
	 * 
	 * @param READERBARCODE
	 *            学工号
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @param page
	 *            第几页
	 * @param prePageSizes
	 *            每页展示条数
	 * @return
	 */
	public List<CzDataVO> findYktInfoDetail(String READERBARCODE,String startDate,String endDate,int page,int prePageSizes);

	/**
	 * 返回查询时间内的消费/充值明细总页数
	 * 
	 * @param READERBARCODE
	 *            学工号
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @param prePageSizes
	 *            每页展示条数
	 * @return
	 */
	public String findYktInfoTotals(String READERBARCODE, String startDate,
			String endDate, int prePageSizes);
	
	
	/**
	 * 查找一个小时内充值信息
	 * 
	 * @return
	 */
	public List<YktMessage> findSaveInfo();
	
	/**
	 * 一个小时内卡状态由正常变为挂失状态信息
	 * 
	 * @return
	 */
	public List<YktMessage> findStateChanges();
	
}
