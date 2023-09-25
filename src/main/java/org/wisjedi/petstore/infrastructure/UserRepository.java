package org.wisjedi.petstore.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wisjedi.petstore.model.User;

public interface UserRepository extends JpaRepository<User, String> {
}
