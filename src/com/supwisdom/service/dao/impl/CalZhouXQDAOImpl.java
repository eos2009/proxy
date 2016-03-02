package com.supwisdom.service.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.supwisdom.service.dao.CalZhouXQDAO;
import com.supwisdom.service.util.DBUtils;
@Repository
public class CalZhouXQDAOImpl implements CalZhouXQDAO {

	/** */
	private Log log = LogFactory.getLog(CalZhouXQDAOImpl.class);
	@Override
	public String getZhouXQ(String date) {
		log.info("getZhouXQ dao...");
		String strSelect = "select distinct j.xq||'学期 '|| j.jxz ||'周' from sharedb.Jx_Jxrl j where j.rq = ?";
		Connection conn = DBUtils.getAddressConnection();
		String zhouXq = "";
		log.info(strSelect);
		try {
			long beginTime = System.currentTimeMillis();
			PreparedStatement stat = conn.prepareStatement(strSelect);
			stat.setString(1, date);
			log.info("execute begin ");
			ResultSet rs = stat.executeQuery();
			log.info("execute end ");
			while (rs.next()) {
				zhouXq = rs.getString(1);
			}
			long endTime = System.currentTimeMillis();
			log.info("endTime-beginTime = " + (endTime - beginTime) / 1000);
		} catch (Exception e) {
			log.debug("findAddressInfoDetail error .", e);
			return zhouXq;
		} finally {
			DBUtils.close(conn);
		}
		//zhouXq = "jjjjjj";
		return zhouXq;
	}

}
