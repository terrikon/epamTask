package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dataBase.dao.UserDaoLibrary;

public class TestClass {
	


	public static void main(String[] args) {
		User user=UserDaoLibrary.getUserByLogin("dima");
		System.out.println(user.getLogin());
	}
}
