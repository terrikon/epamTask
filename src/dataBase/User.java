package dataBase;

public class User {
	private int id=0;
	private String login="";
	private String pass="";
	private int access=2;
	public int getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getPass() {
		return pass;
	}

	public int getAccess() {
		return access;
	}

	public void setId(int userId) {
		id = userId;
	}

	public void setLogin(String userLogin) {
		login = userLogin;
	}

	public void setPass(String userPass) {
		pass = userPass;
	}

	public void setAccess(int UserAccess) {
		access = UserAccess;
	}

}
