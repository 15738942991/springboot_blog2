package com.zp.service.Impl;

import com.zp.NoFoundException;
import com.zp.dao.TagsRepository;
import com.zp.pojo.Tag;
import com.zp.service.TagsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;


@Service
public class TagsServiceImpl  implements TagsService {
    @Autowired
    TagsRepository tagsRepository;
    @Transactional
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagsRepository.findAll(pageable);
    }
    @Transactional
    @Override
    public Tag getTag(Long id) {
        return tagsRepository.getOne(id);
    }
    @Transactional
    @Override
    public Tag getByName(String name) {
        return tagsRepository.findByName(name);
    }
    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag t = tagsRepository.getOne(id);
        if (t==null){
            throw new NoFoundException("不存在该标签！");
        }
        BeanUtils.copyProperties("tag",t);
        return tagsRepository.save(t);
    }
    @Transactional
    @Override
    public void deleteTag(Long id) {
        return ;
    }
    @Transient
    @Override
    public void deleteById(Long id) {
        tagsRepository.deleteById(id);
    }
    @Transient
    @Override
    public Tag saveTag(Tag tag) {
        return tagsRepository.save(tag);
    }

    @Override
    public List<Tag> listTag() {
        return tagsRepository.findAll();
    }

    /*
    * 思考：     @Transient @Transactional 的区别

     * */
    @Override
    public List<Tag> listTag(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return tagsRepository.findAllById(list);
    }

    //首页标签分类
    @Transactional
    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = PageRequest.of(0,size,sort);
        return tagsRepository.findTagsByTop(pageable);
    }

    // 字符串数组的tag.id 转换成  list格式的
    /*private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }*/

}
