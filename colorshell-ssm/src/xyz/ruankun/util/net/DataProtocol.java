/**
* @author sherivey.Ruan  
* @date 2018��5��4��  
* @version 1.0 
* ��ϵ��ʽ:qkmc@outlook.com
*/ 
package xyz.ruankun.util.net;
public interface DataProtocol {

	/**
	 * �������ݣ������dataΪ37λ16��������
	 * ���磺EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
	 * �������̣�
	 * ȡ��ǰ��32λȫ��ת��ΪСд��Ȼ�����Ϊtoken
	 * ��5λΪ�������ͺ����� Ϊ 5x4 bit
	 * ���ն�Ӧ��Э�������type������
	 */
	void analysis(String data);
	
	/**
	 * �������ݣ��������token��32x4��bit ���������ͣ�����תΪ2bit�����Լ�datas����Ϊ���ݴ�
	 * ���磺EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
	 * ���ܹ��̣�
	 * token 128bit + ����2bit + ����18bit �������Ϊ148bit.
	 * @param token ��Կ
	 * @param type ��������
	 * @param datas ���ݼ���
	 */
	void encode(String token,String type,Integer... datas);
}
