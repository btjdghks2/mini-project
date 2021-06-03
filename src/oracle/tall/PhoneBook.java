package oracle.tall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class PhoneBook {

	private static String id;
	private static String hp;
	private static String tell;
	private static Long Name;


	
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.println("*****************************");
			System.out.println("*      전화번호 관리 프로그램     *");
			System.out.println("*****************************");
			System.out.println("1.리스트 2.등록 3.삭제 4.검색 5.종료");
			System.out.println("-----------------------------");
			
//			Readable a;
			
			System.out.println(">메뉴번호: ");
			
			int menu = scanner.nextInt();
			
			if (menu == 1) {
				//	리스트
				System.out.println("1.<리스트>");
				showList(scanner);
				
			} else if (menu == 2) {
				// 	등록
				insertshow(scanner);
				System.out.println("등록");
			} else if (menu == 3) {
				// 	삭제
				deleteshow(scanner);
				System.out.println("삭제");
			} else if (menu == 4) {
				// 	검색
				searchshow(scanner);
				System.out.println("검색");
			} else if (menu == 5) {
				// 	종료
				System.out.println("*****************************");
				System.out.println("*	   감사합니다		*");
				System.out.println("*****************************");
				break;
			} else {
				System.out.println("다시 입력하세요");
			}
			
			
		}
	}

	private static void showList(Scanner scanner) {
		PhoneBookDAO dao = new PhoneBookDAOImpl();
		List<PhoneBookVO> list = dao.getList();
		
		
		//	루프 돌면서 출력
		
		Iterator<PhoneBookVO> iter = list.iterator();
		
		while(iter.hasNext()) {

			System.out.println(iter.next());
		}
		

		
	}
	
	private static void insertshow(Scanner scanner) {
		

		
		
		System.out.println("2.<등록>");
		
		System.out.println("이름:");
		String name = scanner.next();
		System.out.println("집전화:");
		String hp = scanner.next();
		System.out.println("휴대전화:");
		String tell = scanner.next();
		
		
		PhoneBookVO vo = new PhoneBookVO(null , name, hp,tell);
		PhoneBookDAO dao = new PhoneBookDAOImpl();
		
		boolean success = dao.insert(vo);
		
		System.out.println("INSERT " + (success ? "등록하였습니다": "실패하였습니다") );
		
		selectAll();

	}
	private static void selectAll() {
		// TODO Auto-generated method stub
		
	}

	private static void deleteshow(Scanner scanner) {
		//	Scanner에서 저자 PK 입력 -> DELETE

		selectAll();
		System.out.println("3.<삭제>");
		System.out.print("번호:");
		int id = scanner.nextInt();
		
		PhoneBookDAO dao = new PhoneBookDAOImpl();
		boolean success = dao.delete(Long.valueOf(id));
		
		System.out.println("DELETE " + (success ? "삭제하였습니다": "실패하였습니다"));
		selectAll();

	}
	private static void searchshow(Scanner scanner) {
	
		
		System.out.println("4.<검색>");
		System.out.print("이름:");
		String keyword = scanner.next();

		PhoneBookDAO dao = new PhoneBookDAOImpl();
		List<PhoneBookVO> list = dao.search(keyword);

		Iterator<PhoneBookVO> it = list.iterator();

		while(it.hasNext()) {
			PhoneBookVO vo = it.next();
			System.out.printf("%d \t%s \t%s \t%s %n", 
					vo.getId(),
					vo.getName(),
					vo.gethp(),
					vo.gettell());
		}

	
}}


