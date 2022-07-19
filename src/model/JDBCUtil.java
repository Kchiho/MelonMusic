package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JDBCUtil {
	// JDBCUtill
	// 1) C R R U D
	// 2) DAO가 증가하면 또 CRUD도 증가됨 -> ...
	// => ctrl+C, ctrl+V : 코드 재사용 중복코드 최소화 모듈와 Utill클래스 생성
	// Util류 클래스
	// : static

	static final String driverName="oracle.jdbc.driver.OracleDriver";
	static final String url="jdbc:oracle:thin:@localhost:1521:xe";
	static final String user="kim";
	static final String passwd="1234";

	public static Connection connect() {
		Connection conn=null;
		try {
			Class.forName(driverName);
			conn=DriverManager.getConnection(url, user, passwd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public static void disconnect(PreparedStatement pstmt,Connection conn) {
		try {
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
