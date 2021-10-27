package com.lzq.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="t_coment")
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    // 昵称
    private String nickName;
    // eamil
    private String email;
    // 评论的内容
    private String content;
    // 是否为管理员
    private boolean manage;
    //    头像
    private String avatar;
    // 创建时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    // 多对一博客
    @ManyToOne()
    private Blog blog;

    // 一对多，一个父评论对应多个子评论
    @OneToMany(mappedBy = "parentComment")
    private List<Comment> replyComments = new ArrayList<>();

    //
    @ManyToOne
    private Comment parentComment;
}
