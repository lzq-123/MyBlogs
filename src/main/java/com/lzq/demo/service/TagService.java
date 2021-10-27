package com.lzq.demo.service;

import com.lzq.demo.pojo.Tag;
import com.sun.org.apache.bcel.internal.generic.LUSHR;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TagService {
    Tag saveTag(Tag tag);

    /*分页查询*/
    Page<Tag> listTag(Pageable pageable);

    Tag getTag(Long id);

    Tag updateTag(Long id, Tag tag);

    void deleteTag(Long id);

    Tag findByName(String name);

    List<Tag> listTag();

    List<Tag> listTagTop(Integer size);

    List<Tag> listTag(String ids);



}
