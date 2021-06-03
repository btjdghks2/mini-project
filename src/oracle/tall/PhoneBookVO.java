package oracle.tall;

public class PhoneBookVO {

	private Long id;
	private String Name;
	private String hp;
	private String tell;
	
	public PhoneBookVO(Long id, String Name, String hp, String tell) {
		this.id = id;
		this.Name = Name;
		this.hp = hp;
		this.tell = tell;
	}




	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public String gethp() {
		return hp;
	}

	public void sethp(String hp) {
		this.hp = hp;
	}

	@Override
	public String toString() {
	
		return Name  + hp + tell;
	}

	public void settell(String tell) {
		this.tell = tell;
	}
	public String gettell() {
		// TODO Auto-generated method stub
		return tell;
	}
	
	

}
