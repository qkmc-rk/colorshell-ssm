/**
* @author sherivey.Ruan  
* @date 2018��5��4��  
* @version 1.0 
* ��ϵ��ʽ:qkmc@outlook.com
*/ 
package xyz.ruankun.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * socket�������ദ��󣬸����õ�һ���̰߳�ȫ�����ݿ�
 * Ȼ����������Э�鴦���ദ������ݣ������ݴ浽������
 * @author Sherivey.Ruan
 *
 */
public class SocketHanlderThread implements Runnable{

	private Socket socket;
	private StringBuilder data = new StringBuilder();
	public SocketHanlderThread(Socket socket) {
		super();
		this.socket = socket;
	}

	@Override
	public void run() {
		handleSocket();
		System.out.println(data);
	}

	public void handleSocket() {
		//���������ȡ����
		InputStream in;
		BufferedReader reader;
		String msg = null;
		try {
			in = socket.getInputStream();
			reader = new BufferedReader(new InputStreamReader(in));	
			while(!(msg = reader.readLine()).equals("#")) {
				data.append(msg);
				//System.out.println(msg);
				//����ͻ��˷�����������
			}
			in.close();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
