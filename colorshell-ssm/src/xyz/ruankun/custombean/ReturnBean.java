/**
* @author sherivey.Ruan  
* @date 2018��4��22��  
* @version 1.0 
* ��ϵ��ʽ:qkmc@outlook.com
*/ 
package xyz.ruankun.custombean;

/**
 * ����ǰ̨��json�Ķ����װ.
 * @author Sherivey.Ruan
 * @param <T> Ҫ���ص�data
 */
public class ReturnBean<T> {

	private Integer code;
	private boolean success;
	private T data;
	
	/**
	 * ���سɹ���Ϣ���������Ӧ����String���͵���ʾ��Ϣ.codeΪ0,,successΪtrue
	 * @param data ����ɹ�����Ϣ
	 * @return none
	 */
	public void success(T data) {
		this.code = 0;
		this.success = true;
		this.data = data;
	}
	/**
	 * ����ʧ����Ϣ
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
