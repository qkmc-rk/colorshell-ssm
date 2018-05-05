/**
* @author sherivey.Ruan  
* @date 2018年5月4日  
* @version 1.0 
* 联系方式:qkmc@outlook.com
*/ 
package xyz.ruankun.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class DeviceServer {

	//server 的端口
	private static final int PORT = 9998;
	private ServerSocket serverSocket;
	public DeviceServer() throws IOException{
		serverSocket = new ServerSocket(PORT);
		System.out.println("tcp server已创建，端口：" + PORT);
		//创建服务器线程
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					Socket socket;
					try {
						socket = serverSocket.accept();
						System.out.println("已连接到" + socket.getRemoteSocketAddress().toString());
						Thread thread = new Thread(new SocketHanlderThread(socket));
						thread.start();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			}
			
		}).start();
		
	}
}
