/**
* @author sherivey.Ruan  
* @date 2018��5��5��  
* @version 1.0 
* ��ϵ��ʽ:qkmc@outlook.com
*/ 
package xyz.ruankun.util.expert;

public interface DataAnalysisI<T> {

	/**
	 * ר��ϵͳ�ӿ����
	 * <p> ����TΪ����data����
	 * @param data �������������
	 * @return �������
	 */
	<T> String analysis(T data);
}