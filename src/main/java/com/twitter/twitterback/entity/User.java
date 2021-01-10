package com.twitter.twitterback.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.twitter.twitterback.utils.OptimizedTimestampDeserializer;
import com.twitter.twitterback.utils.PostgreSQLEnumType;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity(name = "users")
@DynamicInsert
@Table(name="users")
@TypeDef(
		name = "PGSQL_ENUM",
		typeClass = PostgreSQLEnumType.class
)
public class User {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "username")
	private String username;

	@Column(name = "name")
	private String name;

	@ColumnDefault("now()")
	@Column(name = "creation_date")
	private Timestamp creationDate;

	@Column(name = "country_code")
	private String countryCode;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	@Column(name = "birth_date")
	@JsonDeserialize(using= OptimizedTimestampDeserializer.class)
	private Timestamp birthDate;

	@Column(name = "path_to_profile_picture")
	private String pathToProfilePicture;

	@Column(name = "path_to_background_picture")
	private String pathToBackgroundPicture;

	@Column(name = "password")
	private String password;

	@Column(name = "is_active")
	private boolean isActive;

	@Column(name = "is_verified")
	private boolean isVerified;

	@Enumerated(EnumType.STRING)
	@Type( type = "PGSQL_ENUM" )
	@Column(name = "gender")
	private Gender gender;

	public User(String username, String name, String countryCode, String email, String phone, Timestamp birthDate, String pathToProfilePicture, String pathToBackgroundPicture, String password, Gender gender) {
		java.util.Date utilDate = new java.util.Date();
		this.username = username;
		this.name = name;
		this.creationDate = new Timestamp(utilDate.getTime());
		this.countryCode = countryCode;
		this.email = email;
		this.phone = phone;
		this.birthDate = birthDate;
		this.pathToProfilePicture = pathToProfilePicture;
		this.pathToBackgroundPicture = pathToBackgroundPicture;
		this.password = password;
		this.isActive = true;
		this.isVerified = false;
		this.gender = gender;
	}

	public User (){}

	public enum Gender {
		male,
		female,
		other
	}

}
