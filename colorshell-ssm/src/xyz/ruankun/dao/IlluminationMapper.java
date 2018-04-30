/**
* @author sherivey.Ruan  
* @date 2018年4月22日  
* @version 1.0 
* 联系方式:qkmc@outlook.com
*/ 
package xyz.ruankun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import xyz.ruankun.model.Illumination;

public interface IlluminationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Illumination record);

    int insertSelective(Illumination record);

    Illumination selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Illumination record);

    int updateByPrimaryKey(Illumination record);

	Illumination selectOneRecently();

	List<Illumination> selectSome(@Param("from")Integer from, @Param("to")Integer to);
}