/**
* @author sherivey.Ruan  
* @date 2018年4月26日  
* @version 1.0 
* 联系方式:qkmc@outlook.com
*/ 
package xyz.ruankun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.ruankun.custombean.NowDataBean;
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
import xyz.ruankun.service.DataService;

@Service("dataService")
public class DataServiceImpl implements DataService {

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
	
	/**
	 * 其中如果有一个数据为空则返回空，否则返回一个合适的数据
	 */
	@Override
	public NowDataBean getCurrentData() {
		NowDataBean nowData = new NowDataBean();
		
		Gas gas = null;
		Humidity humidity = null;
		Illumination illumination = null;
		Temperatrue temperatrue = null;
		Ph ph = null;
		try {
			gas = gasMapper.selectOneRecently();
			humidity = humidityMapper.selectOneRecently();
			illumination = illuminationMapper.selectOneRecently();
			temperatrue = temperatrueMapper.selectOneRecently();
			ph = phMapper.selectOneRecently();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(gas != null && humidity != null && illumination != null && temperatrue != null && ph != null) {
			nowData.setDate(temperatrue.getCreatime());
			nowData.setGas(gas.getGas());
			nowData.setHumidity(humidity.getHumidity());
			nowData.setIllumination(illumination.getIllumination());
			nowData.setPh(ph.getPh());
			nowData.setTemperature(temperatrue.getTemperature());
		}else {
			nowData = null;
		}
		return nowData;
	}

	@Override
	public Temperatrue getCurrentTemp() {
		Temperatrue temperatrue = null;
		try {
			temperatrue = temperatrueMapper.selectOneRecently();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temperatrue;
	}

	@Override
	public List<Temperatrue> getSomeTempRecords(Integer from, Integer to) {
		if(from == null || to == null || from < 0 || to < 0) {
			from = 0;
			to = Integer.MAX_VALUE;
		}
		List<Temperatrue> list = null;
		try {
			list = temperatrueMapper.selectSome(from,to);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Humidity getCurrentHumidity() {
		Humidity humidity = null;
		try {
			humidity = humidityMapper.selectOneRecently();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return humidity;
	}

	@Override
	public List<Humidity> getSomeHumidityRecords(Integer from, Integer to) {
		if(from == null || to == null || from < 0 || to < 0) {
			from = 0;
			to = Integer.MAX_VALUE;
		}
		List<Humidity> list = null;
		try {
			list = humidityMapper.selectSome(from,to);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Illumination getCurrentIllumination() {
		Illumination illumination = null;
		try {
			illumination = illuminationMapper.selectOneRecently();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return illumination;
	}

	@Override
	public List<Illumination> getSomeIlluminationRecords(Integer from, Integer to) {
		if(from == null || to == null || from < 0 || to < 0) {
			from = 0;
			to = Integer.MAX_VALUE;
		}
		List<Illumination> list = null;
		try {
			list = illuminationMapper.selectSome(from,to);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Gas getCurrentGas() {
		Gas gas = null;
		try {
			gas = gasMapper.selectOneRecently();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gas;
	}

	@Override
	public List<Gas> getSomeGasRecords(Integer from, Integer to) {
		if(from == null || to == null || from < 0 || to < 0) {
			from = 0;
			to = Integer.MAX_VALUE;
		}
		List<Gas> list = null;
		try {
			list = gasMapper.selectSome(from,to);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Ph getCurrentPh() {
		Ph ph = null;
		try {
			ph = phMapper.selectOneRecently();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ph;
	}

	@Override
	public List<Ph> getSomePhRecords(Integer from, Integer to) {
		if(from == null || to == null || from < 0 || to < 0) {
			from = 0;
			to = Integer.MAX_VALUE;
		}
		List<Ph> list = null;
		try {
			list = phMapper.selectSome(from,to);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
