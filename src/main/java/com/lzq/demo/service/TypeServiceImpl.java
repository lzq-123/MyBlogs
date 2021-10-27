package com.lzq.demo.service;

import com.lzq.demo.dao.TypeRepository;
import com.lzq.demo.pojo.Type;
import javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService{

    @Autowired
    private TypeRepository typeRepository;

    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        return typeRepository.getById(id);
    }

    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type byId = typeRepository.getById(id);
        if (byId == null){
            return null;
        }
        BeanUtils.copyProperties(type,byId);
        return typeRepository.save(byId);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }

    @Override
    public Type findByName(String name) {
        return typeRepository.findByName(name);
    }

    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }

    @Override
    public List<Type> listTypeTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "blogs.size");
        Pageable pageable = PageRequest.of(0, size, sort);
        return typeRepository.findTop(pageable);
    }
}
