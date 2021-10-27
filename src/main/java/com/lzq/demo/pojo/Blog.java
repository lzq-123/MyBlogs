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
@Entity // 声明一个类对应一个数据库实体。
@Table(name="t_blog") // 设置表名
public class Blog {

    /**
     *把主键生成策略交给持久化引擎(persistence engine),
     *持久化引擎会根据数据库在三种主键生成 策略中选择其中一种 TABLE， SEQUENCE，IDENTITY
     * @GeneratedValue注解默认使用的策略是GenerationType.AUTO
     *
     * GenerationType.IDENTITY 主键自增
     */
    @Id // 声明一个字段为主键。
    @GeneratedValue
    // id唯一主键
    private Long id;
    // 文章标题
    private String title;
    @Lob // 声明为大字段
    @Basic(fetch = FetchType.LAZY ) // FetchType.EAGER 表示非延迟 加载，而 FetchType. LAZY 表示延迟加载 ；
    // 文章内容
    private String content;
    // 文章首图地址
    private String firstPicture;
    // 文章类型
    private String flag;
    // 浏览次数
    private Integer views;
    // 是否赞赏
    private boolean appreciation;
    // 是否可分享
    private boolean shareStatement;
    // 评论的开关
    private boolean commentabled;
    // 可转载
    private boolean published;
    // 是否推荐
    private boolean recommend;
    // 文章介绍
    private String introduce;

    @Transient // 是在给某个javabean上需要添加个属性，但是这个属性你又不希望给存到数据库中去，非持久化
    private String tagIds;

    @Temporal(TemporalType.TIMESTAMP) // @Temporal(TemporalType.DATE) 会得到形如'yyyy-MM-dd' 格式的日期。
    // 创建时间
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    // 更新时间
    private Date updateTime;

    @ManyToOne()
    private Type type;

    @ManyToMany(cascade = {CascadeType.PERSIST}) // 级联保存
    private List<Tag> tags = new ArrayList<>();

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "blog") // 将维护权交由多的一方来维护；
    // 那为什么不让多的一方交出维护权，让一的一方来维护呢？上面的实验也表明了如果让一的一方来维护，始终都会多出两条update语句，因为外键是在多的这一方的，所以维护权应该交由多的一方。
    private List<Comment> comments;

    public void init(){
        this.tagIds = tagsToIds(this.getTags());
    }

    // 将[tag] 列表转换成 tagId 以,相间隔的字符串
    private String tagsToIds(List<Tag> tags){
        StringBuilder sb = new StringBuilder();
        if (!tags.isEmpty()){
            for (int i = 0; i < tags.size(); ++i){
                sb.append(tags.get(i).getId());
                if (i != tags.size() - 1){
                    sb.append(",");
                }
            }
            return sb.toString();
        }
        return tagIds;
    }
}
