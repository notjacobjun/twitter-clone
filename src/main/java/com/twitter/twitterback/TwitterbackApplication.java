package com.twitter.twitterback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TwitterbackApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwitterbackApplication.class, args);
		testDb();
	}

	static void testDb(){
		DB db = new DB();
		Connection conn = db.connect();

		PreparedStatement preparedStatement = null;
		try {
			String query = "SELECT 'db works' as col;";
			preparedStatement = conn.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			System.out.println(resultSet.getString(1));
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
}
