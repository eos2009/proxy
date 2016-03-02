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

import com.supwisdom.service.dao.LibraryDAO;
import com.supwisdom.service.entity.Book;
import com.supwisdom.service.entity.Reacer;
import com.supwisdom.service.util.DBUtils;

@Repository
public class LibraryDAOImpl implements LibraryDAO {
	/** */
	private Log log = LogFactory.getLog(LibraryDAOImpl.class);
	/** 默认数量，当没有查找到记录或出现异常时返回 */
	private static final String CNT = "0";

	/*
	 * //hibernate
	 * 
	 * @Resource private HibernateTemplate hibernateTemplate;
	 * 
	 * public HibernateTemplate getHibernateTemplate() { return
	 * hibernateTemplate; }
	 * 
	 * public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
	 * this.hibernateTemplate = hibernateTemplate; }
	 */
	@Override
	public String findLibraryCurCnt(String READERBARCODE) {
		log.info("dao  begin......");
		String queryString = "select count(*) from [JPzjk].[dbo].[Circulate] where READERBARCODE = ?";
		Connection conn = DBUtils.getConnection();
		String value = CNT;
		try {
			PreparedStatement stat = conn.prepareStatement(queryString);
			stat.setString(1, READERBARCODE);
			ResultSet rs = stat.executeQuery();
			if (rs.next()) {
				value = rs.getString(1);
			}
		} catch (Exception e) {
			log.debug("findLibraryCurCnt error .", e);
			return value;
		} finally {
			DBUtils.close(conn);
		}
		if(value == null){
			value = CNT;
		}
		return value;
	}
	
	@Override
	public List<Book> findLibraryCurBook(String READERBARCODE) {
		log.info("dao  begin......");
		String queryString = "SELECT B.TITLE "
				+ "FROM JPZJK.DBO.CIRCULATE     C, "
				+ "JPZJK.DBO.MAINBOOKLOCAL BL, " + "JPZJK.DBO.MAINBOOK      B "
				+ "WHERE C.BARCODE = BL.BARCODE "
				+ " AND BL.MAINKAY = B.MAINKAY "
				+ "AND C.READERBARCODE = ?";
		Connection conn = DBUtils.getConnection();
		List<Book> list = new ArrayList<Book>();
		try {
			PreparedStatement stat = conn.prepareStatement(queryString);
			stat.setString(1, READERBARCODE);
			ResultSet rs = stat.executeQuery();
			
			while (rs.next()) {
				Book book = new Book();
				book.setName(rs.getString(1));
				list.add(book);
			}
		} catch (Exception e) {
			log.debug("findLibraryCurCnt error .", e);
			return list;
		} finally {
			DBUtils.close(conn);
		}
		/*List<Book> list = new ArrayList<Book>();
		Book book = new Book();
		book.setName("计算机基础");
		list.add(book);
		Book book1 = new Book();
		book1.setName("数据结构");
		list.add(book1);*/
		return list;
	}
	

	@Override
	public List<Book> findLibraryExpireBook(String READERBARCODE) {
		log.info("dao  begin......");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
		String dateStr = sdf.format(date);
		log.info("dateStr......"+dateStr);
		String queryString = "SELECT B.TITLE "
				+ "FROM JPZJK.DBO.CIRCULATE     C, "
				+ "JPZJK.DBO.MAINBOOKLOCAL BL, " 
				+ "JPZJK.DBO.MAINBOOK      B "
				+ "WHERE C.BARCODE = BL.BARCODE "
				+ "AND BL.MAINKAY = B.MAINKAY "
				+ "AND C.READERBARCODE = ? "
				+ "AND C.RETURNTIME <= ? ";
		Connection conn = DBUtils.getConnection();
		List<Book> list = new ArrayList<Book>();
		try {
			PreparedStatement stat = conn.prepareStatement(queryString);
			stat.setString(1, READERBARCODE);
			stat.setString(2, dateStr);
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				book.setName(rs.getString(1));
				list.add(book);
			}
		} catch (Exception e) {
			log.debug("findLibraryCurCnt error .", e);
			return list;
		} finally {
			DBUtils.close(conn);
		}
		/*List<Book> list = new ArrayList<Book>();
		Book book = new Book();
		book.setName("计算机基础");
		list.add(book);
		Book book1 = new Book();
		book1.setName("数据结构");
		list.add(book1);*/
		return list;
	}

	
	
	@Override
	public String findLibraryDateCnt(String READERBARCODE) {
		log.info("dao  begin......");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
		String dateStr = sdf.format(date);
		String queryString = "select count(*) from [JPzjk].[dbo].[Circulate] where READERBARCODE = ? and RETURNTIME <= ?";
		Connection conn = DBUtils.getConnection();
		String value = CNT;
		try {
			PreparedStatement stat = conn.prepareStatement(queryString);
			stat.setString(1, READERBARCODE);
			stat.setString(2, dateStr);
			ResultSet rs = stat.executeQuery();
			if (rs.next()) {
				value = rs.getString(1);
			}
		} catch (Exception e) {
			log.debug("findLibraryDateCnt error .", e);
			return value;
		} finally {
			DBUtils.close(conn);
		}
		if(value == null){
			value = CNT;
		}
		return value;
	}

	@Override
	public Reacer findLibraryFee(String READERBARCODE) {
		log.info("dao  begin......");
		String queryString = "select CARDNO as 借阅证号,SUM(FINE) as 欠费金额  from [JPzjk].[dbo].[Reader] where BARCODE = ? group by CARDNO";
		Connection conn = DBUtils.getConnection();
		Reacer reacer = new Reacer();
		reacer.setFINE(CNT);
		try {
			PreparedStatement stat = conn.prepareStatement(queryString);
			stat.setString(1, READERBARCODE);
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				reacer.setCARDNO(rs.getString(1));
				reacer.setFINE(rs.getString(2));
			}
		} catch (Exception e) {
			log.debug("findLibraryFee error .", e);
			return reacer;
		} finally {
			DBUtils.close(conn);
		}
		return reacer;
	}

	@Override
	public String findLibraryFirstDate(String READERBARCODE) {
		log.info("dao  begin......");
		String queryString = "select min(RETURNTIME) from [JPzjk].[dbo].[Circulate] where READERBARCODE = ?";
		Connection conn = DBUtils.getConnection();
		// String value = "1990-01-01 00:00:00";
		String value = "";
		try {
			PreparedStatement stat = conn.prepareStatement(queryString);
			stat.setString(1, READERBARCODE);
			ResultSet rs = stat.executeQuery();
			if (rs.next()) {
				log.info("value = " + value);
				value = rs.getString(1);
				log.info("value = " + value);
			}
		} catch (Exception e) {
			log.debug("findLibraryFirstDate error .", e);
			return value;
		} finally {
			DBUtils.close(conn);
		}
		if(value == null){
			value = CNT;
		}
		return value;
	}

	// =================== 消息中心数据获取 ========================
	@Override
	public List<Reacer> findAllExpire() {
		log.info("dao findAllExpire begin......");
		String queryString = "select case "
				+ "when c.READERBARCODE like '60%' then "
				+ "STUFF(c.READERBARCODE, 1, 2,'') " 
				+ "else "
				+ "c.READERBARCODE " 
				+ "end READERBARCODE, " 
				+ "c.BRROWTIME, "
				+ "c.RETURNTIME, " 
				+ "t.TITLE "
				+ "from JPzjk.dbo.Circulate c, dbo.MainBook t "
				+ "where LEN(c.READERBARCODE) = 10 "
				+ "and ISNUMERIC(c.READERBARCODE) = 1 "
				+ "and DATEDIFF(HOUR, GETDATE(), c.RETURNTIME) between 0 and 24 "
				//+ "and c.READERBARCODE like '%0000005379'  " 
				+ "and c.MAINKAY = t.MAINKAY  " 
				+ "order by c.READERBARCODE";
		Connection conn = DBUtils.getConnection();
		List<Reacer> list = new ArrayList<Reacer>();
		try {
			PreparedStatement stat = conn.prepareStatement(queryString);
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				Reacer reacer = new Reacer();
				reacer.setREADERBARCODE(rs.getString(1));
				reacer.setBRROWTIME(rs.getString(2));
				reacer.setRETURNTIME(rs.getString(3));
				reacer.setTitle(rs.getString(4));
				list.add(reacer);
			}
		} catch (Exception e) {
			log.debug("findLibraryFee error .", e);
			return list;
		} finally {
			DBUtils.close(conn);
		}
		return list;
	}

	@Override
	public List<Reacer> findAllFee() {
		log.info("dao findAllFee begin......");
		String queryString = "select r.BARCODE, fee " 
		+ "from (select case "
				+ "when r.BARCODE like '60%' then "
				+ "STUFF(r.BARCODE, 1, 2, '') " 
				+ "else " 
				+ "r.BARCODE "
				+ "end BARCODE, " + "sum(r.FINE) as fee " 
				+ "from Reader r "
				+ "where LEN(r.BARCODE) = 10 "
				+ "and ISNUMERIC(r.BARCODE) = 1 " 
				//+ "and r.BARCODE like '%0000002098'  "
				+ "group by r.BARCODE) r "
				+ "where fee > 0";
		Connection conn = DBUtils.getConnection();
		List<Reacer> list = new ArrayList<Reacer>();
		try {
			PreparedStatement stat = conn.prepareStatement(queryString);
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				Reacer reacer = new Reacer();
				reacer.setREADERBARCODE(rs.getString(1));
				reacer.setFINE(rs.getString(2));
				list.add(reacer);
			}
		} catch (Exception e) {
			log.debug("findLibraryFee error .", e);
			return list;
		} finally {
			DBUtils.close(conn);
		}
		return list;
	}

	@Override
	public List<Reacer> findAllNearExpire() {
		log.info("dao findAllNearExpire begin......");
		String queryString = "select case "
				+ "when c.READERBARCODE like '60%' then "
				+ "STUFF(c.READERBARCODE, 1, 2,'') "
				+ "else "
				+ "c.READERBARCODE "
				+ "end READERBARCODE, "
				+ "c.BRROWTIME, "
				+ "c.RETURNTIME, "
				+ "t.TITLE "
				+ "from JPzjk.dbo.Circulate c, dbo.MainBook t "
				+ "where LEN(c.READERBARCODE) = 10 "
				+ "and ISNUMERIC(c.READERBARCODE) = 1 "
				+ "and DATEDIFF(DAY, GETDATE(), c.RETURNTIME) = 1 "
				//+ "and c.READERBARCODE like '%0000005379'  " 
				+ "and c.MAINKAY = t.MAINKAY  "
				+ "order by c.READERBARCODE";
		Connection conn = DBUtils.getConnection();
		List<Reacer> list = new ArrayList<Reacer>();
		try {
			PreparedStatement stat = conn.prepareStatement(queryString);
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				Reacer reacer = new Reacer();
				reacer.setREADERBARCODE(rs.getString(1));
				reacer.setBRROWTIME(rs.getString(2));
				reacer.setRETURNTIME(rs.getString(3));
				reacer.setTitle(rs.getString(4));
				list.add(reacer);
			}
		} catch (Exception e) {
			log.debug("findAllNearExpire error .", e);
			return list;
		} finally {
			DBUtils.close(conn);
		}
		return list;
	}

}
