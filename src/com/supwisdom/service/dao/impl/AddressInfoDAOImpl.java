package com.supwisdom.service.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.supwisdom.service.dao.AddressInfoDAO;
import com.supwisdom.service.entity.AddressInfo;
import com.supwisdom.service.util.DBUtils;

/**
 * 获取通讯录信息明细
 * 
 * @author fei.chen
 *
 */
@Repository
public class AddressInfoDAOImpl implements AddressInfoDAO {
	/** */
	private Log log = LogFactory.getLog(AddressInfoDAOImpl.class);
	/** 默认数量，当没有查找到记录或出现异常时返回 */
	private static final String CNT = "0";

	@Override
	public List<AddressInfo> findAddressInfo(String type, String name,
			int page, int pageSizes) {
		String strSelect = "select u.dhxmc, u.type, u.dhh, u.email, u.dhxdz,u.mobilePhone,u.id,u.bmlx,u.orgName "
				+ "from (select u.id,u.dhxmc, "
				+ "u.dhxdz, "
				+ "u.dhh, "
				+ "u.filed1 mobilePhone, "
				+ "u.filed2 type, "
				+ "u.filed3 email, "
				+ "u.filed4 bmlx, "
				+ "u.filed5 orgName, "
				+ "rownum   rw "
				+ "from bg_dhb u "
				+ "where u.filed2  = ? ";

		StringBuffer strbuff = new StringBuffer(strSelect);
		if (!"null".equals(name)) {
			strbuff.append("and u.dhxmc like '%" + name + "%' ");
		}
		strbuff.append("and rownum <= ? order by dhxmc) u where u.rw > ? order by id");

		int start = (page - 1) * pageSizes;
		int end = start + pageSizes;
		log.info("start = " + start + " end = " + end);
		Connection conn = DBUtils.getAddressConnection();
		List<AddressInfo> list = new ArrayList<AddressInfo>();

		String queryString = strbuff.toString();
		log.info(queryString);
		try {
			long beginTime = System.currentTimeMillis();
			PreparedStatement stat = conn.prepareStatement(queryString);
			stat.setString(1, type);
			stat.setInt(2, end);
			stat.setInt(3, start);
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				AddressInfo addressInfo = new AddressInfo();
				addressInfo.setName(rs.getString(1));
				// log.info("name = "+rs.getString(1));
				addressInfo.setType(rs.getString(2));
				addressInfo.setCallNumber(rs.getString(3));
				addressInfo.setEmail(rs.getString(4));
				addressInfo.setAddress(rs.getString(5));
				addressInfo.setMobilePhone(rs.getString(6));
				addressInfo.setId(rs.getString(7));
				list.add(addressInfo);
			}
			long endTime = System.currentTimeMillis();
			log.info("endTime-beginTime = " + (endTime - beginTime) / 1000);
		} catch (Exception e) {
			log.debug("findAddressInfoDetail error .", e);
			return list;
		} finally {
			DBUtils.close(conn);
		}
		return list;
	}
	
	
	@Override
	public List<AddressInfo> findOrgAddressInfo(String type, String name,
			int page, int pageSizes) {
		//log.info("hello......");
		String strSelect = "select u.dhxmc, u.type, u.dhh, u.email, u.dhxdz,u.mobilePhone,u.id,u.bmlx,u.orgName "
				+ "from (select u.id,u.dhxmc, "
				+ "u.dhxdz, "
				+ "u.dhh, "
				+ "u.filed1 mobilePhone, "
				+ "u.filed2 type, "
				+ "u.filed3 email, "
				+ "u.filed4 bmlx, "
				+ "u.filed5 orgName, "
				+ "rownum   rw "
				+ "from bg_dhb u "
				+ "where u.filed2  = ? ";

		StringBuffer strbuff = new StringBuffer(strSelect);
		if (!"null".equals(name)) {
			strbuff.append("and (u.dhxmc like '%" + name + "%' or u.filed5 like '%" + name + "%') ");
		}
		strbuff.append("and rownum <= ? order by id) u where u.rw > ? order by id");

		int start = (page - 1) * pageSizes;
		int end = start + pageSizes;
		log.info("start = " + start + " end = " + end+" type = "+type);
		Connection conn = DBUtils.getAddressConnection();
		List<AddressInfo> list = new ArrayList<AddressInfo>();

		String queryString = strbuff.toString();
		log.info(queryString);
		try {
			long beginTime = System.currentTimeMillis();
			PreparedStatement stat = conn.prepareStatement(queryString);
			stat.setString(1, type);
			stat.setInt(2, end);
			stat.setInt(3, start);
			log.info("execute begin ");
			ResultSet rs = stat.executeQuery();
			log.info("execute end ");
			while (rs.next()) {
				AddressInfo addressInfo = new AddressInfo();
				addressInfo.setName(rs.getString(1));
				addressInfo.setType(rs.getString(2));
				addressInfo.setCallNumber(rs.getString(3));
				addressInfo.setEmail(rs.getString(4));
				addressInfo.setAddress(rs.getString(5));
				addressInfo.setMobilePhone(rs.getString(6));
				addressInfo.setId(rs.getString(7));
				addressInfo.setBmlx(rs.getString(8));
				addressInfo.setOrgName(rs.getString(9));
				list.add(addressInfo);
			}
			long endTime = System.currentTimeMillis();
			log.info("endTime-beginTime = " + (endTime - beginTime) / 1000);
		} catch (Exception e) {
			log.debug("findAddressInfoDetail error .", e);
			return list;
		} finally {
			DBUtils.close(conn);
		}
		return list;
	}
	

	@Override
	public String findAddressInfoTotals(String type, String name) {
		log.info("dao  begin......");
		String queryStr = "select count(*) from bg_dhb u where u.filed2  = ? ";
		StringBuffer strbuff = new StringBuffer(queryStr);

		if (!"null".equals(name)) {
			strbuff.append("and u.dhxmc like '%" + name + "%' ");
		}
		Connection conn = DBUtils.getAddressConnection();
		String totals = CNT;
		String queryString = strbuff.toString();
		try {
			long beginTime = System.currentTimeMillis();
			PreparedStatement stat = conn.prepareStatement(queryString);
			stat.setString(1, type);
			ResultSet rs = stat.executeQuery();
			if (rs.next()) {
				totals = rs.getString(1);
				log.info("totals = " + totals);
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
	
	
	@Override
	public String findOrgAddressInfoTotals(String type, String name) {
		log.info("dao  begin......");
		String queryStr = "select count(*) from bg_dhb u where u.filed2  = ? ";
		StringBuffer strbuff = new StringBuffer(queryStr);

		if (!"null".equals(name)) {
			strbuff.append("and (u.dhxmc like '%" + name + "%' or u.filed5 like '%" + name + "%') ");
		}
		Connection conn = DBUtils.getAddressConnection();
		String totals = CNT;
		String queryString = strbuff.toString();
		try {
			long beginTime = System.currentTimeMillis();
			PreparedStatement stat = conn.prepareStatement(queryString);
			stat.setString(1, type);
			ResultSet rs = stat.executeQuery();
			if (rs.next()) {
				totals = rs.getString(1);
				log.info("totals = " + totals);
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


	@Override
	public boolean insertAddressInfo(int type, String nameStr, String email,
			String mobilePhone, String callNumber, String addressStr) {
		log.info("dao  begin......");
		if(addressStr.equals("null")){
			addressStr = "";
		}
		if(mobilePhone.equals("null")){
			mobilePhone = "";
		}
		String queryStr = "insert into bg_dhb values (bg_dhb_id_seq.nextval, " + "       '" + nameStr
				+ "', " + "      '" + addressStr + "', " + "       '"
				+ callNumber + "', " + "       '', " + "       '" + mobilePhone
				+ "', " + type + ",'" + email + "','','','', "
				+ "       sysdate, " + " '0') ";
		Connection conn = DBUtils.getAddressConnection();
		log.info(queryStr);
		try {
			long beginTime = System.currentTimeMillis();
			PreparedStatement stat = conn.prepareStatement(queryStr);
			stat.executeUpdate();
			long endTime = System.currentTimeMillis();
			log.info("endTime-beginTime = " + (endTime - beginTime) / 1000);
			return true;
		} catch (Exception e) {
			log.debug("findYktInfoTotals error .", e);
			return false;
		} finally {
			DBUtils.close(conn);
		}
	}
	
	/**
	 * 部门信息
	 */
	@Override
	public boolean insertOrgAddressInfo(int type, String nameStr,
			String orgType, String orgName, String callNumber, String addressStr) {
		log.info("dao  begin......");
		if(addressStr.equals("null")){
			addressStr = "";
		}
		if(orgType.equals("null")){
			orgType = "";
		}
		String queryStr = "insert into bg_dhb values (bg_dhb_id_seq.nextval, " 
				+ "       '" + nameStr +"', " 
				+ "      '" + addressStr + "', " 
				+ "       '" + callNumber + "','', '','" 
				+ type + "','','"
				+ orgType +"','"
				+ orgName+"','', "
				+ "       sysdate, " 
				+ " '0') " ;
		Connection conn = DBUtils.getAddressConnection();
		log.info(queryStr);
		try {
			long beginTime = System.currentTimeMillis();
			PreparedStatement stat = conn.prepareStatement(queryStr);
			stat.executeUpdate();
			long endTime = System.currentTimeMillis();
			log.info("endTime-beginTime = " + (endTime - beginTime) / 1000);
			return true;
		} catch (Exception e) {
			log.debug("findYktInfoTotals error .", e);
			return false;
		} finally {
			DBUtils.close(conn);
		}
	}

	@Override
	public boolean deleteAddressInfo(String id) {
		log.info("dao  begin......");
		String queryStr = "delete from bg_dhb where id = ?";
		Connection conn = DBUtils.getAddressConnection();
		log.info(queryStr);
		try {
			log.info("delete begin");
			long beginTime = System.currentTimeMillis();
			PreparedStatement stat = conn.prepareStatement(queryStr);
			stat.setString(1, id);
			stat.executeUpdate();
			log.info("delete end....");
			long endTime = System.currentTimeMillis();
			log.info("endTime-beginTime = " + (endTime - beginTime) / 1000);
			return true;
		} catch (Exception e) {
			log.debug("findYktInfoTotals error .", e);
			return false;
		} finally {
			DBUtils.close(conn);
		}
	}

	@Override
	public boolean updateAddressInfo(String id, String nameStr, String email,
			String mobilePhone, String callNumber, String addressStr) {
		log.info("dao  begin......");
		log.info("nameStr = "+nameStr);
		if(addressStr.equals("null")){
			addressStr = "";
		}
		if(mobilePhone.equals("null")){
			mobilePhone = "";
		}
		// String queryStr = "update bg_dhb b set b.dhxmc = ?,b.dhxdz = ?,b.dhh = ?,b.filed1 = ?,b.filed3 = ? where b.id = ?";
		String queryStr = "update bg_dhb b set b.dhxmc = '" + nameStr
				+ "',b.dhxdz = '" + addressStr + "',b.dhh = '" + callNumber
				+ "',b.filed1 = '" + mobilePhone + "',b.filed3 = '" + email
				+ "' where b.id = '" + id + "'";
		Connection conn = DBUtils.getAddressConnection();
		log.info(queryStr);
		try {
			long beginTime = System.currentTimeMillis();
			PreparedStatement stat = conn.prepareStatement(queryStr);
//			stat.setString(1, nameStr);
//			stat.setString(2, addressStr);
//			stat.setString(3, callNumber);
//			stat.setString(4, mobilePhone);
//			stat.setString(5, email);
//			stat.setString(6, id);
			System.out.println("begin update");
			stat.executeUpdate();
			System.out.println("end update");
			long endTime = System.currentTimeMillis();
			log.info("endTime-beginTime = " + (endTime - beginTime) / 1000);
			return true;
		} catch (Exception e) {
			log.debug("findYktInfoTotals error .", e);
			return false;
		} finally {
			DBUtils.close(conn);
		}
	}


	@Override
	public boolean updateOrgAddressInfo(String id, String nameStr,
			String orgType, String orgName, String callNumber, String addressStr) {
		log.info("dao  begin......");
		log.info("nameStr = "+nameStr);
		if(addressStr.equals("null")){
			addressStr = "";
		}
		if(orgType.equals("null")){
			orgType = "";
		}
		// String queryStr = "update bg_dhb b set b.dhxmc = ?,b.dhxdz = ?,b.dhh = ?,b.filed1 = ?,b.filed3 = ? where b.id = ?";
		String queryStr = "update bg_dhb b set b.dhxmc = '" + nameStr
				+ "',b.dhxdz = '" + addressStr + "',b.dhh = '" + callNumber
				+ "',b.filed4 = '" + orgType + "',b.filed5 = '" + orgName
				+ "' where b.id = '" + id + "'";
		Connection conn = DBUtils.getAddressConnection();
		log.info(queryStr);
		try {
			long beginTime = System.currentTimeMillis();
			PreparedStatement stat = conn.prepareStatement(queryStr);
//			stat.setString(1, nameStr);
//			stat.setString(2, addressStr);
//			stat.setString(3, callNumber);
//			stat.setString(4, mobilePhone);
//			stat.setString(5, email);
//			stat.setString(6, id);
			System.out.println("begin update");
			stat.executeUpdate();
			System.out.println("end update");
			long endTime = System.currentTimeMillis();
			log.info("endTime-beginTime = " + (endTime - beginTime) / 1000);
			return true;
		} catch (Exception e) {
			log.debug("findYktInfoTotals error .", e);
			return false;
		} finally {
			DBUtils.close(conn);
		}
	}

}
