package com.malcolmbaatjies.user.repositories;

import com.malcolmbaatjies.user.entities.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UserRespository
 * <br><br>
 * This interface extends the JpaRepository to allow for interaction with the Database.
 * <br><br>
 * This interface is annotated with Spring's {@link org.springframework.stereotype.Repository} to indicate that it is used to interact with the Database.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
	/**
	 * Check if a user exists by email.
	 *
	 * @param email The user's email.
	 * @return True if the user exists, false otherwise.
	 */
	boolean existsUserByEmail(String email);
}
