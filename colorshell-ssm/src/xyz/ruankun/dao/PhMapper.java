/**
* @author sherivey.Ruan  
* @date 2018年4月22日  
* @version 1.0 
* 联系方式:qkmc@outlook.com
*/ 
package xyz.ruankun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import xyz.ruankun.model.Ph;

public interface PhMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Ph record);

    int insertSelective(Ph record);

    Ph selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Ph record);

    int updateByPrimaryKey(Ph record);
    
    Ph selectOneRecently();

	List<Ph> selectSome(@Param("from")Integer from, @Param("to")Integer to);
}