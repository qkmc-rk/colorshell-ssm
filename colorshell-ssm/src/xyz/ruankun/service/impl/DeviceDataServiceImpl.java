/**
* @author sherivey.Ruan  
* @date 2018年5月4日  
* @version 1.0 
* 联系方式:qkmc@outlook.com
*/ 
package xyz.ruankun.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.ruankun.dao.GasMapper;
import xyz.ruankun.dao.HumidityMapper;
import xyz.ruankun.dao.IlluminationMapper;
import xyz.ruankun.dao.PhMapper;
import xyz.ruankun.dao.TemperatrueMapper;
import xyz.ruankun.model.Gas;
import xyz.ruankun.model.Humidity;
import xyz.ruankun.model.Illumination;
import xyz.ruankun.model.Ph;
import xyz.ruankun.model.Temperatrue;
import xyz.ruankun.service.DeviceDataService;

@Service("DeviceDataService")
public class DeviceDataServiceImpl implements DeviceDataService{

	@Autowired
	GasMapper gasMapper;
	
	@Autowired
	HumidityMapper humidityMapper;
	
	@Autowired
	IlluminationMapper illuminationMapper;
	
	@Autowired
	PhMapper phMapper;
	
	@Autowired
	TemperatrueMapper temperatrueMapper;
	
	
	@Override
	public Integer setData(Illumination data) {
		Integer rs = null;
		try {
			rs = illuminationMapper.insertSelective(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public Integer setData(Gas data) {
		Integer rs = null;
		try {
			rs = gasMapper.insertSelective(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public Integer setData(Ph data) {
		Integer rs = null;
		try {
			rs = phMapper.insertSelective(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	@Transactional
	public Integer setDatas(Temperatrue data1, Humidity data2) {
		Integer rs1 = null;
		Integer rs2 = null;
		try {
			if(data1 != null && data2 != null) {
				rs1 = humidityMapper.insertSelective(data2);
				rs2 = temperatrueMapper.insertSelective(data1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(rs1 != null && rs2 != null) {
			return rs1+ rs2;
		}else {
			return null;
		}
	}

}
