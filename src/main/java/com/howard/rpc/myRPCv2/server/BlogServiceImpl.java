package com.howard.rpc.myRPCv2.server;

import com.howard.rpc.myRPCv2.common.Blog;
import com.howard.rpc.myRPCv2.service.BlogService;
import org.slf4j.Logger;
import com.howard.rpc.myRPCv2.common.LoggerSingleton;

public class BlogServiceImpl implements BlogService {
    private static final Logger log = LoggerSingleton.getLogger();
    @Override
    public Blog getBlogById(Integer id) {
        Blog blog = Blog.builder()
                .id(id)
                .title("My Blog Title")
                .userId(155112201)
                .build();
        log.info("Client queries blog of id: " + id);
        return blog;
    }
}
