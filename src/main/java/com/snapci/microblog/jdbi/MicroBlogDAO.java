package com.snapci.microblog.jdbi;

import com.snapci.microblog.core.MicroBlog;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(MicroBlogMapper.class)
public interface MicroBlogDAO {
    @SqlUpdate("insert into microblogs (userid, content) values (:userid, :content)")
    @GetGeneratedKeys
    int create(@BindBean MicroBlog microBlog);

    @SqlQuery("select * from microblogs where userid = :userid order by id desc")
    List<MicroBlog> findAllByUserId(@Bind("userid") Integer userId);

    @SqlQuery("select * from microblogs where id = :id")
    MicroBlog findById(@Bind("id") Integer id);

}
