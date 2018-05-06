/**
* @author sherivey.Ruan  
* @date 2018年5月5日  
* @version 1.0 
* 联系方式:qkmc@outlook.com
*/ 
package xyz.ruankun.util.expert;

public interface DataAnalysisI<T> {

	/**
	 * 专家系统接口设计
	 * <p> 泛型T为传入data类型
	 * @param data 传入待分析数据
	 * @return 分析结果
	 */
	<T> String analysis(T data);
}