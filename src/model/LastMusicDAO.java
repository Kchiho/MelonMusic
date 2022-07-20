package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LastMusicDAO {
	Connection conn;
	PreparedStatement pstmt;
	final String sql_insert = "INSERT INTO LASTMUSIC VALUES((SELECT NVL(MAX(LNUM),0) +1 FROM LASTMUSIC),?,?)";
	final String sql_selectAll = "SELECT * FROM (SELECT A.*,ROWNUM AS RNUM FROM (SELECT * FROM LASTMUSIC ORDER BY LNUM DESC) A WHERE ROWNUM<=5) WHERE RNUM>=1";
	
	public boolean insert(LastMusicVO vo) { // 크롤링 할때 사용되는 메소드 pk빼고 다받아옴
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(sql_insert);
			pstmt.setInt(1, vo.getmNum());
			pstmt.setInt(2, vo.getuNum());
			int res = pstmt.executeUpdate();
			if(res>=1) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return false;
	}
	
	public ArrayList<LastMusicVO> selectAll(LastMusicVO vo){
		ArrayList<LastMusicVO> datas = new ArrayList<LastMusicVO>();
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_selectAll);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				LastMusicVO data=new LastMusicVO();
				data.setlNum(rs.getInt("LNUM"));
				data.setmNum(rs.getInt("MNUM"));
				data.setuNum(rs.getInt("UNUM"));
				datas.add(data);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		if(datas.size()==0) {
			return null;
		}
		return datas;
	}
}
