package com.malcolmbaatjies.user.entities.models;

import lombok.*;

import javax.persistence.*;

/**
 * User
 * <br><br>
 * This class is used to represent the user entity.
 * <br><br>
 * This class is annotated with Lombok's {@link lombok.Builder}, {@link lombok.Getter}, {@link lombok.ToString}, {@link lombok.AllArgsConstructor} and {@link lombok.NoArgsConstructor} to generate the builder, getter, toString, all-args and no-args constructor methods during compile time.
 * <br><br>
 * This class is annotated with JPA's {@link javax.persistence.Entity} and {@link javax.persistence.Table} to indicate that it is an entity and to specify the table name.
 */
@Entity
@Table(name = "users")
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User
{
	/**
	 * The user's unique identifier.
	 * <br><br>
	 * This field is annotated with JPA's {@link javax.persistence.Id} and {@link javax.persistence.GeneratedValue} to indicate that it is the primary key and to specify the generation strategy.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * The user's first name.
	 * <br><br>
	 * This field is annotated with JPA's {@link javax.persistence.Column} to specify the column name.
	 */
	@Column(name = "first_name")
	private String firstName;

	/**
	 * The user's last name.
	 * <br><br>
	 * This field is annotated with JPA's {@link javax.persistence.Column} to specify the column name.
	 */
	@Column(name = "last_name")
	private String lastName;

	/**
	 * The user's email.
	 * <br><br>
	 * This field is annotated with JPA's {@link javax.persistence.Column} to specify the column name.
	 */
	@Column(name = "email")
	private String email;
}
