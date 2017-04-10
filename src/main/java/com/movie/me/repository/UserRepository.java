package com.movie.me.repository;

import java.util.List;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import com.movie.me.domain.User;

@Repository
public interface UserRepository extends GraphRepository<User> {
    @Query("MATCH (u:User {userid:{userid}} " +
            "RETURN u")
    User findByUserId(@Param("userid") String userid);

    @Query("CREATE (u:User {name:{name}, age:{age}, email:{email}, userid:{userid}, photo_uri:{photoURI}}) " +
            "RETURN u")
    User createUserNode(@Param("name") String name, @Param("age") String age, @Param("email") String email, @Param("userid") String userid, @Param("photoURI") String photoURI);

    @Query("MATCH (u:User) " +
            "WHERE u.name =~ ('(?i).*'+{name}+'.*')" +
            "RETURN u")
    List<User> findByNameLike(@Param("name") String name);

    @Query("MATCH (u:User {email:{email}}) " +
            "RETURN u")
    User findByEmail(@Param("email") String email);
}
