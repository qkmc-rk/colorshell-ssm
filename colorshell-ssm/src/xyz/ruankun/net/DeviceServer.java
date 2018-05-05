/**
* @author sherivey.Ruan  
* @date 2018��5��4��  
* @version 1.0 
* ��ϵ��ʽ:qkmc@outlook.com
*/ 
package xyz.ruankun.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class DeviceServer {

	//server �Ķ˿�
	private static final int PORT = 9998;
	private ServerSocket serverSocket;
	public DeviceServer() throws IOException{
		serverSocket = new ServerSocket(PORT);
		System.out.println("tcp server�Ѵ������˿ڣ�" + PORT);
		//�����������߳�
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					Socket socket;
					try {
						socket = serverSocket.accept();
						System.out.println("�����ӵ�" + socket.getRemoteSocketAddress().toString());
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
