package com.supwisdom.service.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.supwisdom.service.dao.JwDAO;
import com.supwisdom.service.entity.JwContent;
import com.supwisdom.service.util.DBUtils;

@Repository
public class JwDAOImpl implements JwDAO {
	/** */
	private Log log = LogFactory.getLog(JwDAOImpl.class);

	@Override
	public List<JwContent> findJwInfos() {
		String strSelect = "select CategoryID, "
       + "ContentTitle, "
       + "PublishTime, "
       + "CoreContent, "
       + "ContentBody, "
       + "Author "
       + "from V_C_News c "
       + "where c.contenttitle is not null "
       + "and c.contentbody is not null "
       + "and c.publishtime between DATEADD(MONTH,-2, GETDATE()) and GETDATE() "
       + "order by PublishTime desc";

		log.info(strSelect);
		Connection conn = DBUtils.getJwConnection();
		List<JwContent> list = new ArrayList<JwContent>();

		try {
			long beginTime = System.currentTimeMillis();
			PreparedStatement stat = conn.prepareStatement(strSelect);
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				JwContent jwContent = new JwContent();
				jwContent.setCategory(rs.getString(1));
				jwContent.setTitle(rs.getString(2));
				jwContent.setTime(rs.getString(3));
				jwContent.setAuthor(rs.getString(6));
				String content = rs.getString(5);
				content = content.replaceAll("&lt;", "<");
				content = content.replaceAll("&gt;", ">");
				jwContent.setText(content);
				log.info(content);
				list.add(jwContent);
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

}
