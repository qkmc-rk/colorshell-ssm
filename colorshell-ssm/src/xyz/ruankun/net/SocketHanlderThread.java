/**
* @author sherivey.Ruan  
* @date 2018年5月4日  
* @version 1.0 
* 联系方式:qkmc@outlook.com
*/ 
package xyz.ruankun.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * socket丢给该类处理后，该类会得到一个线程安全的数据块
 * 然后利用数据协议处理类处理该数据，将数据存到服务器
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
		//从这里面读取数据
		InputStream in;
		BufferedReader reader;
		String msg = null;
		try {
			in = socket.getInputStream();
			reader = new BufferedReader(new InputStreamReader(in));	
			while(!(msg = reader.readLine()).equals("#")) {
				data.append(msg);
				//System.out.println(msg);
				//处理客户端发过来的数据
			}
			in.close();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
