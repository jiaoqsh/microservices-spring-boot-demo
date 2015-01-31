package net.jiaoqsh.backend.user.registration.repository;


import net.jiaoqsh.backend.user.registration.domain.RegisteredUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RegisteredUserRepository extends MongoRepository<RegisteredUser, String>{

    RegisteredUser findByEmail(String email);
}
