/**
* @author sherivey.Ruan  
* @date 2018年4月26日  
* @version 1.0 
* 联系方式:qkmc@outlook.com
*/ 
package xyz.ruankun.custombean;
public class PrevBean {

	private Integer code;
	private Boolean success;
	private String data;
	
	private static  PrevBean prevBean;

	public static PrevBean getPrevBeanSuccess(String data) {
		prevBean = new PrevBean();
		prevBean.setCode(1);
		prevBean.setData(data);
		prevBean.setSuccess(true);
		return prevBean;
	}
	public static PrevBean getPrevBeanRoleWrong(String data) {
		prevBean = new PrevBean();
		prevBean.setCode(0);
		prevBean.setData(data);
		prevBean.setSuccess(false);
		return prevBean;
	}
	public static PrevBean getPrevBeanAuthWrong(String data) {
		prevBean = new PrevBean();
		prevBean.setCode(-1);
		prevBean.setData(data);
		prevBean.setSuccess(false);
		return prevBean;
	}

	
	public void setCode(Integer code) {
		this.code = code;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public void setData(String data) {
		this.data = data;
	}

	public static void setPrevBean(PrevBean prevBean) {
		PrevBean.prevBean = prevBean;
	}
	public Integer getCode() {
		return code;
	}
	public Boolean getSuccess() {
		return success;
	}
	public String getData() {
		return data;
	}
	public static PrevBean getPrevBean() {
		return prevBean;
	}
}
