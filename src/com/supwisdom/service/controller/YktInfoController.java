package com.supwisdom.service.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.supwisdom.service.libraryService.YktService;

/**
 * 
 * 获取一卡通余额信息和消费/充值明细
 * 
 * @author fei.chen
 *
 */
@Controller
public class YktInfoController {

	/** */
	@Resource
	private YktService YktServiceImpl;

	/**
	 * 返回一个json字符串 当前一卡通余额那些
	 * 
	 * @param barcode
	 *            学工号
	 * @return
	 */
	@RequestMapping(value = "/findYktBalance/{barcode}")
	public @ResponseBody String findYktBalance(@PathVariable String barcode) {
		String balance = null;
		if (barcode != null && !barcode.isEmpty()) {
			balance = YktServiceImpl.findYktBalance(barcode);
		}
		return balance;
	}

	/**
	 * 返回查询时间内的消费/充值明细
	 * 
	 * @param barcode
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
	@RequestMapping(value = "/findYktInfoDetail/{barcode}/{startDate}/{endDate}/{page}/{prePageSizes}")
	public @ResponseBody String findYktInfoDetail(@PathVariable String barcode,
			@PathVariable String startDate, @PathVariable String endDate,
			@PathVariable int page, @PathVariable int prePageSizes) {
		String yktIndoDetail = YktServiceImpl.findYktInfoDetail(barcode,
				startDate, endDate, page, prePageSizes);
		return yktIndoDetail;
	}

	/**
	 * 返回查询时间内的消费/充值明细总页数
	 * 
	 * @param barcode
	 *            学工号
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @param prePageSizes
	 *            每页展示条数
	 * @return
	 */
	@RequestMapping(value = "/findYktInfoTotals/{barcode}/{startDate}/{endDate}/{prePageSizes}")
	public @ResponseBody String findYktInfoTotals(@PathVariable String barcode,
			@PathVariable String startDate, @PathVariable String endDate,
			@PathVariable int prePageSizes) {
		String totals = YktServiceImpl.findYktInfoTotals(barcode, startDate,
				endDate, prePageSizes);
		return totals;
	}

	// ============ 消息推送 =======================
	/**
	 * 获取充值信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findYktInfoSave")
	public @ResponseBody String findYktInfoSave() {
		String save = YktServiceImpl.findSaveInfo();
		return save;
	}

	/**
	 * 获取状态变更
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findYktInfoStateChange")
	public @ResponseBody String findYktInfoStateChange() {
		String stateChange = YktServiceImpl.findStateChanges();
		return stateChange;
	}

}
