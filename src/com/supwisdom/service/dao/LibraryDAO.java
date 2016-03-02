package com.supwisdom.service.dao;

import java.util.List;

import com.supwisdom.service.entity.Book;
import com.supwisdom.service.entity.Reacer;

/**
 * 获取借阅图书信息
 * 
 * @author fei.chen
 *
 */
public interface LibraryDAO {

	/**
	 * 
	 * 返回 当前借阅书籍数量
	 * 
	 * @param READERBARCODE
	 *            学工号
	 * @return
	 */
	public String findLibraryCurCnt(String READERBARCODE);
	
	/**
	 * 
	 * 返回 当前借阅书籍
	 * 
	 * @param READERBARCODE
	 *            学工号
	 * @return
	 */
	public List<Book> findLibraryCurBook(String READERBARCODE);
	
	
	/**
	 * 
	 * 返回 当前到期借阅书籍
	 * 
	 * @param READERBARCODE
	 *            学工号
	 * @return
	 */
	public List<Book> findLibraryExpireBook(String READERBARCODE);

	/**
	 * 返回当前借阅书籍中最早应还时间
	 * 
	 * @param READERBARCODE
	 *            学工号
	 * @return
	 */
	public String findLibraryFirstDate(String READERBARCODE);

	/**
	 * 
	 * 返回 当前到期数据数量
	 * 
	 * @param READERBARCODE
	 *            学工号
	 * @return
	 */
	public String findLibraryDateCnt(String READERBARCODE);

	/**
	 * 
	 * 返回 当前欠费信息
	 * 
	 * @param READERBARCODE
	 *            学工号
	 * @return
	 */
	public Reacer findLibraryFee(String READERBARCODE);

	/**
	 * 查找所有到期图书
	 * 
	 * @return
	 */
	public List<Reacer> findAllExpire();
	
	/**
	 * 查找所有图书欠费人员信息
	 * 
	 * @return
	 */
	public List<Reacer> findAllFee();
	
	/**
	 * 查找所有临近到期图书（时间范围为1天）
	 * 
	 * @return
	 */
	public List<Reacer> findAllNearExpire();

}
