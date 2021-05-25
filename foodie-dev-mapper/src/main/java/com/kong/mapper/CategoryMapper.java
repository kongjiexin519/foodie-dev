package com.kong.mapper;

import com.kong.my.MyMapper;
import com.kong.pojo.Category;
import com.kong.pojo.vo.CategoryVO;

import java.util.List;

public interface CategoryMapper extends MyMapper<Category> {
    public List<CategoryVO> getSubCatList(Integer rootCatId);
}