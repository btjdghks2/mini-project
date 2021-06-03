package oracle.tall;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//JDBC 프로그램 작성 순서
// => Student 테이블 생성 - 학번(PK), 이름, 전화번호, 주소, 생년월일
public class CreateTable {
	public static void main(String[] args) {
		
		//SQL 관련 인스턴스를 저장하는 참조변수 선언
		// => finally 영역에서 사용해야 되므로 try 영역 외부에서 선언
		Connection conn = null;
		Statement stmt = null;
		
		try {
			//1.JDBC Driver를 메모리에 로드 => JDBC Driver 인스턴스 생성
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Oracle Driver 인스턴스 생성");
			
			//2.DBMS 서버 접속 - Connection 인스턴스를 반환받아 저장
			// => Connection 인스턴스 : 서버 접속 정보를 저장하고 있는 인스턴스
			// => DriverManager 클래스의 getConnection() 메소드 호출
			// => 형식) getConnection(url, username, password);
			// => url : 인터넷에 존재하는 자원을 표현하는 주소
			// => Oracle url 형식) jdbc:oracle:thin:@serverName:port:SID
			String url = "jdbc:oracle:bituser:@localhost:1521:XE";
			String user = "c##bituser";
			String password = "bituser";
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("conn = " + conn);
			
			//3.Statement 인스턴스 생성
			// => Statement 인스턴스 : SQL 명령을 저장하여 DBMS 서버에 전달
			// => Connection 인스턴스의 createStatement() 메소드를 호출하여 반환받아 저장
			stmt = conn.createStatement();
			
			//4.Statement 인스턴스에 SQL 명령 저장 후 접속된 DBMS 서버에 전달
			// => DDL 또는 DML : executeUpdate() 메소드 호출 - int 반환
			// => DQL : executeQuery() 메소드 호출 - ResultSet 인스턴스 반환(검색한 행'들')
			StringBuffer sql = new StringBuffer();
			sql.append("create table student(num number(4)");
			sql.append(", name varchar2(20), phone varchar2(15)");
			sql.append(", address varchar2(50), birthday date");
			sql.append(", constraint student_num_pk primary key(num))");
			
			stmt.executeUpdate(sql.toString()); //SQL 명령이 잘못 되었을 경우 SQLException
			System.out.println("Student 테이블을 성공적으로 생성 했습니다.");
			
		} catch (ClassNotFoundException e) {
			System.out.println("Oracle Driver 클래스를 찾을 수 없습니다.");
		} catch (SQLException e) {
			System.out.println("SQL 오류 = " + e.getMessage());
		} finally {
			//5.사용 SQL 자원 모두 반납 => 생성된 역순으로 반납
			try {
				if(stmt != null) stmt.close(); //자원반납
				if(conn != null) conn.close(); //자원반납
			} catch(SQLException e) {
				
			}
		}
	}
}