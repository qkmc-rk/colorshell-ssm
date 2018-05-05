/**
* @author sherivey.Ruan  
* @date 2018年5月4日  
* @version 1.0 
* 联系方式:qkmc@outlook.com
*/ 
package xyz.ruankun.util.net;
public interface DataProtocol {

	/**
	 * 解析数据，传入的data为37位16进制数据
	 * 例如：EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
	 * 解析过程：
	 * 取出前面32位全部转换为小写，然后解析为token
	 * 后5位为数据类型和数据 为 5x4 bit
	 * 按照对应的协议解析成type和数据
	 */
	void analysis(String data);
	
	/**
	 * 加密数据，将传入的token（32x4）bit 和数据类型（最终转为2bit），以及datas加密为数据串
	 * 例如：EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
	 * 加密过程：
	 * token 128bit + 类型2bit + 数据18bit 最终组合为148bit.
	 * @param token 秘钥
	 * @param type 数据类型
	 * @param datas 数据集合
	 */
	void encode(String token,String type,Integer... datas);
}
