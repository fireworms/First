package mms.member.vo;

public class Member {
	private int id;
	private String name;
	private String addr;
	private String nation;
	private String email;
	private int age;
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public Member(String name, String addr, String nation, String email, int age){
		super();
		this.name = name;
		this.addr = addr;
		this.nation = nation;
		this.email = email;
		this.age = age;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getAddr(){
		return addr;
	}
	
	public void setAddr(String addr){
		this.addr = addr;
	}
	
	public String getNation(){
		return nation;
	}
	
	public void setNation(String nation){
		this.nation = nation;
	}
	
	public String getEmail(){
		return email;
	}
	
	public int getAge(){
		return age;
	}
	
	public void setAge(int age){
		this.age = age;
	}
	
	public String toString(){
		return "name = " + name + ", addr = " + addr + ", email = " + email + ", nation = " + nation + ", age = " + age;
	}
}
