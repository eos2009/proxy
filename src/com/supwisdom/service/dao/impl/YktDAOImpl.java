package com.supwisdom.service.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.supwisdom.service.dao.YktDAO;
import com.supwisdom.service.entity.CzDataVO;
import com.supwisdom.service.entity.YktMessage;
import com.supwisdom.service.util.DBUtils;

@Repository
public class YktDAOImpl implements YktDAO {
	/** */
	private Log log = LogFactory.getLog(YktDAOImpl.class);
	/** 默认数量，当没有查找到记录或出现异常时返回 */
	private static final String CNT = "0";

	@Override
	public String findYktBalance(String READERBARCODE) {
		log.info("dao  begin......");
		String queryString = "select top 1 r.oddfare as 余额  from "
				+ "(select r.outid,r.opdt,r.oddfare,r.opfare,r.termid from dbo.m_rec_save r where r.outid = ? "
				+ "union "
				+ "select  c.outid,c.opdt,oddfare,opfare,c.termid  from dbo.m_rec_consume c where c.outid = ? ) r "
				+ "order by r.opdt desc ";
		Connection conn = DBUtils.getYKTConnection();
		// String balance = CNT;
		log.info("queryString = " + queryString);
		double balance = Integer.parseInt(CNT);
		try {
			long beginTime = System.currentTimeMillis();
			PreparedStatement stat = conn.prepareStatement(queryString);
			stat.setString(1, READERBARCODE);
			stat.setString(2, READERBARCODE);
			ResultSet rs = stat.executeQuery();
			if (rs.next()) {
				balance = rs.getDouble(1) / 100;
			}
			long endTime = System.currentTimeMillis();
			log.info("endTime-beginTime = " + (endTime - beginTime) / 1000);
		} catch (Exception e) {
			log.debug("findYktBalance error .", e);
			return String.valueOf(balance);
		} finally {
			DBUtils.close(conn);
		}
		return String.valueOf(balance);
	}

	@Override
	public List<CzDataVO> findYktInfoDetail(String READERBARCODE,
			String startDate, String endDate, int page, int prePageSizes) {
		String strCase = "CASE t.typeid " + "WHEN '0' THEN '营业机（餐饮POS）' "
				+ "WHEN '1' THEN '读卡器' " + "WHEN '2' THEN '出纳机' "
				+ "WHEN '3' THEN '现金收银机' " + "WHEN '4' THEN '水控' "
				+ "WHEN '5' THEN '小钱包转款机' " + "WHEN '6' THEN '圈存机' "
				+ "WHEN '7' THEN '购水购电机' " + "WHEN '8' THEN '门禁机I1' "
				+ "WHEN '9' THEN '考勤机' " + "WHEN '10' THEN '会议签到机' "
				+ "WHEN '11' THEN '指纹报到机' " + "WHEN '12' THEN '洗衣机' "
				+ "WHEN '13' THEN '控电机' " + "WHEN '14' THEN '通用控制器' "
				+ "WHEN '15' THEN '联网水表' "
				+ "WHEN '16' THEN '领款机' END as 终端类型 ";
		String strSelect = "(select r.outid, r.opdt, r.oddfare, r.opfare, r.termid,'1' as flag "
				+ "from dbo.m_rec_save r "
				+ "where r.outid = ? "
				+ "and CONVERT(varchar(40), opdt, 23) between ? and ? "
				+ "union "
				+ "select c.outid, c.opdt, c.oddfare, c.opfare, c.termid,'0' as flag "
				+ "  from dbo.m_rec_consume c "
				+ "where c.outid = ? "
				+ "and CONVERT(varchar(40), opdt, 23) between ? and ? ) r ";

		int ends = (page - 1) * prePageSizes;
		StringBuffer strbuff = new StringBuffer(
				"SELECT TOP "
						+ prePageSizes
						+ " r.outid as 学工号,r.opdt as 时间,r.oddfare as 余额,r.opfare as 操作额,t.termname as 终端名称,r.flag as 标志,");
		strbuff.append(strCase);
		strbuff.append("from ");
		strbuff.append(strSelect);
		strbuff.append(",dbo.m_base_term t where r.termid = t.termid and (r.opdt not in (SELECT opdt from (SELECT TOP "
				+ ends + " r.opdt from ");
		strbuff.append(strSelect);
		strbuff.append(" ORDER BY r.opdt desc) ss)) ORDER BY opdt desc");

		String queryString = strbuff.toString();
		log.info(queryString);
		Connection conn = DBUtils.getYKTConnection();
		List<CzDataVO> list = new ArrayList<CzDataVO>();

		try {
			long beginTime = System.currentTimeMillis();
			PreparedStatement stat = conn.prepareStatement(queryString);
			stat.setString(1, READERBARCODE);
			stat.setString(2, startDate);
			stat.setString(3, endDate);
			stat.setString(4, READERBARCODE);
			stat.setString(5, startDate);
			stat.setString(6, endDate);
			stat.setString(7, READERBARCODE);
			stat.setString(8, startDate);
			stat.setString(9, endDate);
			stat.setString(10, READERBARCODE);
			stat.setString(11, startDate);
			stat.setString(12, endDate);
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				CzDataVO YktData = new CzDataVO();
				YktData.setTranstime(rs.getString(2));
				YktData.setAftbala(String.valueOf(rs.getDouble(3) / 100));
				YktData.setAmount(String.valueOf(rs.getDouble(4) / 100));
				YktData.setPosition(rs.getString(5));
				//log.info("Position = " + rs.getString(5));
				YktData.setFlag(rs.getString(6));
				YktData.setType(rs.getString(7));
				list.add(YktData);
			}
			long endTime = System.currentTimeMillis();
			log.info("endTime-beginTime = " + (endTime - beginTime) / 1000);
		} catch (Exception e) {
			log.debug("findYktInfoDetail error .", e);
			return list;
		} finally {
			DBUtils.close(conn);
		}
		return list;
	}

	@Override
	public String findYktInfoTotals(String READERBARCODE, String startDate,
			String endDate, int prePageSizes) {
		log.info("dao  begin......");
		String queryString = "select count(*) as totals from "
				+ "(select r.outid, r.opdt, r.oddfare, r.opfare, r.termid "
				+ "from dbo.m_rec_save r " + "where r.outid = ? "
				+ "and CONVERT(varchar(40), opdt, 23) between ? and ? "
				+ "union "
				+ "select c.outid, c.opdt, c.oddfare, c.opfare, c.termid "
				+ "  from dbo.m_rec_consume c " + "where c.outid = ? "
				+ "and CONVERT(varchar(40), opdt, 23) between ? and ?  ) r ";
		Connection conn = DBUtils.getYKTConnection();
		String totals = CNT;
		try {
			long beginTime = System.currentTimeMillis();
			PreparedStatement stat = conn.prepareStatement(queryString);
			stat.setString(1, READERBARCODE);
			stat.setString(2, startDate);
			stat.setString(3, endDate);
			stat.setString(4, READERBARCODE);
			stat.setString(5, startDate);
			stat.setString(6, endDate);
			ResultSet rs = stat.executeQuery();
			if (rs.next()) {
				totals = rs.getString(1);
			}
			long endTime = System.currentTimeMillis();
			log.info("endTime-beginTime = " + (endTime - beginTime) / 1000);
		} catch (Exception e) {
			log.debug("findYktInfoTotals error .", e);
			return totals;
		} finally {
			DBUtils.close(conn);
		}
		return totals;
	}

	
	
	
	
	
	
	
	//====================    获取推送消息信息 ======================================
	@Override
	public List<YktMessage> findSaveInfo() {
		log.info("dao findSaveInfo  begin......");
		String strSelect = "select case " + "when t.outid like '60%' then "
				+ "STUFF(t.outid, 1, 2,'') " 
				+ "else " 
				+ "t.outid "
				+ "end outid, " 
				+ "t.opdt, " 
				+"t.upDATEdt, "
				+ "t.opfare "
				+ "from dbo.m_rec_save t " 
				+ "where LEN(t.outid) = 10 "
				+ "and ISNUMERIC(t.outid) = 1 "
				+ "and DATEDIFF(HOUR, t.upDATEdt, GETDATE()) between 0 and 1 "
				//+ "and t.outid like '%0000005379' "
				+ "order by t.outid";
		Connection conn = DBUtils.getYKTConnection();
		List<YktMessage> list = new ArrayList<YktMessage>();

		try {
			long beginTime = System.currentTimeMillis();
			PreparedStatement stat = conn.prepareStatement(strSelect);
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				YktMessage YktData = new YktMessage();
				YktData.setCardId(rs.getString(1));
				YktData.setOpDate(rs.getString(2));
				YktData.setUpDate(rs.getString(3));
				YktData.setOpFee(rs.getDouble(4) / 100);
				list.add(YktData);
			}
			long endTime = System.currentTimeMillis();
			log.info("endTime-beginTime = " + (endTime - beginTime) / 1000);
		} catch (Exception e) {
			log.debug("findSaveInfo error .", e);
			return list;
		} finally {
			DBUtils.close(conn);
		}
		return list;
	}

	@Override
	public List<YktMessage> findStateChanges() {
		log.info("dao findSaveInfo  begin......");
		String strSelect = "select case "
				+"when t.outid like '60%' then "
				+"STUFF(t.outid, 1, 2,'') "
				+"else "
				+"t.outid "
				+"end outid, "
				+"t.upDATEdt, "
				+"t.status "
				+"from dbo.m_base_customerscard t "
				+"where LEN(t.outid) = 10 "
				+"and ISNUMERIC(t.outid) = 1 "
				+"and DATEDIFF(HOUR, t.upDATEdt, GETDATE()) between 0 and 1 "
				//+"and t.outid like '%0000002003' "
				+"and t.status = 3 "
				+"order by t.outid";
		Connection conn = DBUtils.getYKTConnection();
		List<YktMessage> list = new ArrayList<YktMessage>();
		try {
			long beginTime = System.currentTimeMillis();
			PreparedStatement stat = conn.prepareStatement(strSelect);
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				YktMessage YktData = new YktMessage();
				YktData.setCardId(rs.getString(1));
				YktData.setUpDate(rs.getString(2));
				YktData.setStatus(rs.getString(3));
				list.add(YktData);
			}
			long endTime = System.currentTimeMillis();
			log.info("endTime-beginTime = " + (endTime - beginTime) / 1000);
		} catch (Exception e) {
			log.debug("findSaveInfo error .", e);
			return list;
		} finally {
			DBUtils.close(conn);
		}
		return list;
	}

}
