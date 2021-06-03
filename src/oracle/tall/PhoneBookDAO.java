package oracle.tall;

import java.util.List;

public interface PhoneBookDAO {
	public List<PhoneBookVO> getList();
	public boolean insert(PhoneBookVO vo);
	public List<PhoneBookVO> search(String keyword);
	public boolean delete(Long id);	
	public boolean update(PhoneBookVO vo);
}
