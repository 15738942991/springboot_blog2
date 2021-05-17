package com.zp.dao;

import com.zp.pojo.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagsRepository extends JpaRepository<Tag,Long> {

    Tag findByName(String name);

    void deleteById(Long id);

    //首页的标签推荐
    @Query("select t from Tag t")
    List<Tag> findTagsByTop(Pageable pageable);
}
