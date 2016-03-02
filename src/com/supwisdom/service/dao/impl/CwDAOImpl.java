package com.supwisdom.service.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.supwisdom.service.dao.CwDAO;
import com.supwisdom.service.util.DBUtils;

@Repository
public class CwDAOImpl implements CwDAO {
	/** */
	private Log log = LogFactory.getLog(CwDAOImpl.class);

	@Override
	public List<String> findCwInfo() {
		log.info("find CwDAOImpl begin..");
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
		String dateStr = sdf.format(date);
		String year = dateStr.substring(0,4);
		int month = Integer.parseInt(dateStr.substring(5,7));
		
		String strSelect = "select a_rydm " 
		+ "from gz_ffb_01 "
		+ "where a_nian = ?  " 
		+ " and a_yue = ? "
		//+ " and a_rydm = '0000005379'  " 
		+ "order by a_rydm ";
		log.info(strSelect);
		Connection conn = DBUtils.getCWConnection();
		List<String> list = new ArrayList<String>();
		String screenname = null;
		try {
			long beginTime = System.currentTimeMillis();
			PreparedStatement stat = conn.prepareStatement(strSelect);
			stat.setString(1, year);
			stat.setInt(2, month-2);
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				screenname = rs.getString(1);
				list.add(screenname);
			}
			long endTime = System.currentTimeMillis();
			log.info("endTime-beginTime = " + (endTime - beginTime) / 1000);
		} catch (Exception e) {
			log.debug("error .", e);
			return list;
		} finally {
			DBUtils.close(conn);
		}
		return list;
	}

}
