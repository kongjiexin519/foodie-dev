package com.kong.service;

import com.kong.pojo.Category;
import com.kong.pojo.vo.CategoryVO;

import java.util.List;

public interface CategoryService {
    // 查询所有一级分类
    public List<Category> queryAllRootLevelCat();

    // 根据一级分类id查询子分类信息
    public List<CategoryVO> getSubCatList(Integer rootCatId);
}
