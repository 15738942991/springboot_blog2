package com.zp.service.Impl;

import com.zp.NoFoundException;
import com.zp.dao.TypeRepository;
import com.zp.pojo.Type;
import com.zp.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;
@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    TypeRepository repository;
    @Transactional
    @Override
    public Type saveType(Type type) {
        return repository.save(type);
    }
    @Transactional
    @Override
    public Type getType(Long id) {
        return repository.getOne(id);
    }
    @Transactional
    @Override
    public Type getTypeByName(String name) {
        return repository.findByName(name);
    }
    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return repository.findAll(pageable);
    }
    @Transactional
    @Override
    public List<Type> listType() {
        return repository.findAll();
    }

    //首页分类推荐
    @Override
    public List<Type> listTypeTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = PageRequest.of(0,size,sort);
        return repository.findByTop(pageable);
    }

   @Transient
    @Override
    public Type updateType(Long id, Type type) {
        Type t = repository.getOne(id);
        if (t==null){
            throw new NoFoundException("不存在该类型！");
        }
        BeanUtils.copyProperties(type,t);
        return repository.save(t);
    }
    @Transient
    @Override
    public void deleteType(Long id) {
         repository.deleteById(id);
    }
}
