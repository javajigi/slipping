package net.slipp.repository.user;

import net.slipp.domain.user.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String>{

}
