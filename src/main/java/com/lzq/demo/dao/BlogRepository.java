package com.lzq.demo.dao;

import com.lzq.demo.pojo.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
* 如果是普通的带查询的功能，需要写到Repository里，
* 假如我们想要根据 Name 来查找 Person ，你可以这样：Optional<Person> findByName(String name);
* 如果你想要找到年龄大于某个值的人，你可以这样： List<Person> findByAgeGreaterThan(int age);
* */

public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor {
    // 可以自定义SQL语句
    @Query("select b from Blog b where b.recommend = true ")
    List<Blog> findTop(Pageable pageable);

    @Query("select b from Blog b where b.title like ?1 or b.content like ?1")
    Page<Blog> findByQuery(String query, Pageable pageable);

    @Transactional // 修改数据要加事务, 声明式事务
    @Modifying // UPDATE 或 DELETE 操作，必须使用@Query与@Modifying注解进行修饰。
    @Query("update Blog b set b.views = b.views + 1 where b.id = ?1")
    int updateViews(Long id);

    // 查询blog的年份。
    @Query("select function('date_format', b.updateTime, '%Y') as year from Blog b group by function('date_format', b.updateTime, '%Y') order by year DESC ")
    List<String> findGroupYear();

    // 根据年份查询博客信息。
    @Query("select b from Blog b where function('date_format', b.updateTime, '%Y') = ?1")
    List<Blog> findByYear(String year);

}
