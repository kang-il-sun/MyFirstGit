package ch19;

import java.awt.BorderLayout;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//GUI ���� Ŭ����
class ServerScreen extends JFrame {
	JTextArea ta;
	JTextField tf;
	
	public ServerScreen() {
		setSize(300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("ä�ù� ����!");
		setLayout(new BorderLayout());
		JLabel label = new JLabel("This is a server!");
		add(label, BorderLayout.NORTH);
		setVisible(true);
	}
}

public class MultiChatServer {
	HashMap userMap;
	
	public MultiChatServer() {
		userMap = new HashMap();
		Collections.synchronizedMap(userMap);
	}
	public static void main(String[] args) {
		new ServerScreen(); //ȭ�鸸 ���
		new MultiChatServer().serviceStart(); //���� ����
	}
	public void serviceStart() {
		ServerSocket serverSocket = null;
		Socket socket = null;
		try {
			serverSocket = new ServerSocket(7777);
			System.out.println("������ ���۵Ǿ����ϴ�.");
			while(true) {
				//Ŭ���̾�Ʈ�� ������ ��ٸ��ٰ� �����ϸ� ������ �㰡��(��ſ� ������ ������)
				socket = serverSocket.accept();//���� ��� ����
				System.out.println("[ "+socket.getInetAddress()+" ]"+"���� �����ϼ̽��ϴ�.");
				
				ServerReceiver receiveThread = new ServerReceiver(socket);
				receiveThread.start();
				System.out.println("���� �������� ������ �̸� : " + receiveThread.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//end serviceStart()
	
	//���� ���� ���� ��� ����ڿ��� �޽����� ����
	void sendToAll(String msg) {
		//�޽����� �޾Ƽ� �ϰ� ����(HashMap���� key������ ������ ���Ϲ���)
		Iterator it = userMap.keySet().iterator();
		while(it.hasNext()) {
			try {
				//�޽��� ������ ���� ��½�Ʈ�� ����
				DataOutputStream out = (DataOutputStream)userMap.get(it.next());
				out.writeUTF(msg);//�޽��� ����
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}//sendToAll()
	//�޽��� ���Ű��� ���� Ŭ����
	class ServerReceiver extends Thread {
		Socket socket;
		DataInputStream in;
		DataOutputStream out;
		public ServerReceiver(Socket socket) {
			this.socket = socket;
			try {
				//������ ����� ���� ����� ��Ʈ�� ����
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				System.out.println("ServerReceiver ���� IO ����");
			}
		}//ServerReceiver()
		@Override
		public void run() {
			String name = "";
			try {
				name = in.readUTF();//�޽��� ����
				sendToAll("# " + name + " ���� �����̽��ϴ�.");//�޽��� ������
				userMap.put(name, out);//HashMap�� ����� �߰�
				System.out.println("���� ������ ���� " + userMap.size() + " �Դϴ�.");
				while(in != null) {
					//���� �޽����� ��� ����ڿ��� ����
					sendToAll(in.readUTF());
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				//��ȭ���� ���� ������� ���� ���
				sendToAll("# " + name + " ���� �����̽��ϴ�.");
				userMap.remove(name);//HashMap���� ����
				System.out.println("[ " + socket.getInetAddress() + ":" 
				+ socket.getPort() + " ] ���� ������ ������");
				System.out.println("���� ������ ���� " + userMap.size() + " �Դϴ�.");
			}
		}//run()
	}//ServerReceiver
}//MultiChatServer


