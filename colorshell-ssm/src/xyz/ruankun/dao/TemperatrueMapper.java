/**
* @author sherivey.Ruan  
* @date 2018��4��22��  
* @version 1.0 
* ��ϵ��ʽ:qkmc@outlook.com
*/ 
package xyz.ruankun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import xyz.ruankun.model.Temperatrue;

public interface TemperatrueMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Temperatrue record);

    int insertSelective(Temperatrue record);

    Temperatrue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Temperatrue record);

    int updateByPrimaryKey(Temperatrue record);
    
    Temperatrue selectOneRecently();

    /**
     * ��ѯһЩ�¶ȼ�¼
     * @param from
     * @param to
     * @return
     */
	List<Temperatrue> selectSome(@Param("from") Integer from,@Param("to") Integer to);
}