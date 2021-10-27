package com.lzq.demo.service;

import com.lzq.demo.dao.TypeRepository;
import com.lzq.demo.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TypeService {

    Type saveType(Type type);

    /*分页查询*/
    Page<Type> listType(Pageable pageable);

    Type getType(Long id);

    Type updateType(Long id, Type type);

    void deleteType(Long id);

    Type findByName(String name);

    List<Type> listType();

    List<Type> listTypeTop(Integer size);

}
