package oracle.tall;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PhoneBookDAOImpl implements PhoneBookDAO {

	private Connection getConnection() throws SQLException{
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(dburl,
					"C##bituser",
					"bituser");
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
		
	}

	@Override
	public List<PhoneBookVO> getList() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<PhoneBookVO> list = new ArrayList<>();

		try {
			conn = getConnection();
			stmt = conn.createStatement();

//			String sql = "SELECT INTO PHONE_BOOK" +"(NAME,HP,TALL)" +"VALUES"+"(?,?,?,sysdate)";
			String sql = "SELECT id, Name, hp, tell, trgdate FROM PHONE_BOOK";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Long id = rs.getLong(1);
				String Name = rs.getString(2);
				String hp = rs.getString(3);
				String tell = rs.getString(4);
				

				PhoneBookVO vo = new PhoneBookVO(id, Name, hp,tell);
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
			}
		}
		return list;
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub

			Connection conn = null;
			PreparedStatement pstmt = null;
			int deletedCount = 0;
			
			try {
				conn = getConnection();
				String sql = "DELETE FROM PHONE_BOOK WHERE ID=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setLong(1, id);
				
				deletedCount = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					pstmt.close();
					conn.close();
				} catch (Exception e) {
					
				}
			}
			
			return 1 == deletedCount;
			
		}
	

	@Override
	public boolean insert(PhoneBookVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertedCount = 0;
		
		try {
			conn = getConnection();
			//	실행 계획
			String sql = "INSERT INTO PHONE_BOOK (id, name, hp, tell) VALUES(SEQ_PHONE_BOOK_PK.NEXTVAL, ?, ?,?)";
			pstmt = conn.prepareStatement(sql);
			//	파리미터 바인딩
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.gethp());
			pstmt.setString(3, vo.gettell());
			
			//	쿼리 수행
			insertedCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				
			}
		}
		
		return 1 == insertedCount;	//	삽입된 레코드가 1개면 성공
	}

	

	@Override
	public List<PhoneBookVO> search(String keyword) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<PhoneBookVO> list = new ArrayList<>();

		String sql = "SELECT id,name,hp,tell FROM PHONE_BOOK " +
					" WHERE name LIKE ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, "%" + keyword + "%");

			
			rs = pstmt.executeQuery();

			
			while(rs.next()) {
				long id = rs.getLong("id");
				String Name = rs.getString("Name");
				String hp = rs.getString("hp");
				String tell = rs.getString("tell");

				
				PhoneBookVO vo = new PhoneBookVO(id,Name, hp,tell);
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {

			}
		}
		return list;
	}

	@Override
	public boolean update(PhoneBookVO vo) {
		// TODO Auto-generated method stub
		return false;
	}

}
