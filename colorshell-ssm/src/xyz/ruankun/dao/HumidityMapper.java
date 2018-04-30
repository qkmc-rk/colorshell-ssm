/**
* @author sherivey.Ruan  
* @date 2018年4月22日  
* @version 1.0 
* 联系方式:qkmc@outlook.com
*/ 
package xyz.ruankun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import xyz.ruankun.model.Humidity;

public interface HumidityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Humidity record);

    int insertSelective(Humidity record);

    Humidity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Humidity record);

    int updateByPrimaryKey(Humidity record);

	Humidity selectOneRecently();

	List<Humidity> selectSome(@Param("from") Integer from,@Param("to") Integer to);

}