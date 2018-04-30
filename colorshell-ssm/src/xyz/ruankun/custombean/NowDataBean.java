/**
* @author sherivey.Ruan  
* @date 2018��4��26��  
* @version 1.0 
* ��ϵ��ʽ:qkmc@outlook.com
*/ 
package xyz.ruankun.custombean;

import java.util.Date;

/**
 * ���bean���ڴ洢��ʱ�˿̵Ĵ��������ʱ��Ϊ��ǰ�¶ȵ�ʱ��
 * @author Sherivey.Ruan
 *
 */
public class NowDataBean {

	private Double gas;
	private Double humidity;
	private Double ph;
	private Double temperature;
	private Integer illumination;
	private Date date;
	
	
	public NowDataBean(Double gas, Double humidity, Double ph, Double temperature, Integer illumination, Date date) {
		super();
		this.gas = gas;
		this.humidity = humidity;
		this.ph = ph;
		this.temperature = temperature;
		this.illumination = illumination;
		this.date = date;
	}
	public NowDataBean() {}
	
	public Double getGas() {
		return gas;
	}
	public void setGas(Double gas) {
		this.gas = gas;
	}
	public Double getHumidity() {
		return humidity;
	}
	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}
	public Double getPh() {
		return ph;
	}
	public void setPh(Double ph) {
		this.ph = ph;
	}
	public Double getTemperature() {
		return temperature;
	}
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
	public Integer getIllumination() {
		return illumination;
	}
	public void setIllumination(Integer illumination) {
		this.illumination = illumination;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
