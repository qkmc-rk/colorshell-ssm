/**
* @author sherivey.Ruan  
* @date 2018年4月22日  
* @version 1.0 
* 联系方式:qkmc@outlook.com
*/ 
package xyz.ruankun.custombean;

/**
 * 返回前台的json的对象封装.
 * @author Sherivey.Ruan
 * @param <T> 要返回的data
 */
public class ReturnBean<T> {

	private Integer code;
	private boolean success;
	private T data;
	
	/**
	 * 返回成功信息，传入参数应该是String类型的提示信息.code为0,,success为true
	 * @param data 传入成功的信息
	 * @return none
	 */
	public void success(T data) {
		this.code = 0;
		this.success = true;
		this.data = data;
	}
	/**
	 * 返回失败信息
	 * @param data
	 * @return none
	 */
	public void fail(T data) {
		this.code = -1;
		this.success = false;
		this.data = data;
	}
	
	public  void repeat(T data) {
		this.code = -2;
		this.success = false;
		this.data = data;
	}
	
	public void withData(T data) {
		this.code = 0;
		this.success = true;
		this.data = data;
	}

	
	
	
	//getter and setter
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
