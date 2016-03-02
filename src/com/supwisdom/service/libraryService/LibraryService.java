package com.supwisdom.service.libraryService;


/**
 * 获取借阅图书信息
 * 
 * @author fei.chen
 *
 */
public interface LibraryService {
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
	public String findLibraryCurBook(String READERBARCODE);
	
	/**
	 * 
	 * 返回 当前到期借阅书籍
	 * 
	 * @param READERBARCODE
	 *            学工号
	 * @return
	 */
	public String findLibraryExpireBook(String READERBARCODE);
	
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
	public String findLibraryFee(String READERBARCODE);
	
	
	
	
	
	/**
	 * 查找所有到期图书
	 * 
	 * @return
	 */
	public String findAllExpire();
	
	/**
	 * 查找所有图书欠费人员信息
	 * 
	 * @return
	 */
	public String findAllFee();
	
	/**
	 * 查找所有临近到期图书（时间范围为1天）
	 * 
	 * @return
	 */
	public String findAllNearExpire();
	
	
	
	
	
	
}
