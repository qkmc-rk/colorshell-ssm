/**
* @author sherivey.Ruan  
* @date 2018��4��22��  
* @version 1.0 
* ��ϵ��ʽ:qkmc@outlook.com
*/ 
package xyz.ruankun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import xyz.ruankun.model.Gas;

public interface GasMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Gas record);

    int insertSelective(Gas record);

    Gas selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Gas record);

    int updateByPrimaryKey(Gas record);

	Gas selectOneRecently();

	List<Gas> selectSome(@Param("from")Integer from, @Param("to")Integer to);
}