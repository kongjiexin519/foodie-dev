package com.kong.service;

import com.kong.pojo.Category;

import java.util.List;

public interface CategoryService {
    // 查询所有一级分类
    public List<Category> queryAllRootLevelCat();
}
