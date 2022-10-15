package com.firstserver.firstserver.repo;

import com.firstserver.firstserver.models.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {

    @Query(value = "SELECT * FROM post WHERE  userid=:userid", nativeQuery = true)
    public List<Post> findByUserId(@Param("userid") Long userid);
}
