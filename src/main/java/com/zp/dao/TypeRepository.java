package com.zp.dao;

import com.zp.pojo.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface TypeRepository extends JpaRepository<Type,Long> {
    Type findByName(String name);

    //首页的分类推荐
    @Query("select t from Type t")
    List findByTop(Pageable pageable);

}
