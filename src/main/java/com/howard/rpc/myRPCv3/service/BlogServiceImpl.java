package com.howard.rpc.myRPCv3.service;

import com.howard.rpc.myRPCv3.common.Blog;
import com.howard.rpc.myRPCv3.service.BlogService;

public class BlogServiceImpl implements BlogService {
    @Override
    public Blog getBlogById(Integer id) {
        return Blog.builder()
                .id(id)
                .title("My blog")
                .userId(155112201)
                .build();
    }
}
