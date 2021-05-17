package com.zp.service;

import com.zp.pojo.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface TagsService {

    Page<Tag> listTag(Pageable pageable);

    Tag getTag(Long id);

    Tag getByName(String name);

    Tag updateTag(Long id,Tag tag);

    void deleteTag(Long id);

    void deleteById(Long id);

    Tag saveTag(Tag tag);

    List<Tag> listTag();

    List<Tag> listTag(String ids);
    //首页标签推荐
    List<Tag> listTagTop(Integer size);


}
