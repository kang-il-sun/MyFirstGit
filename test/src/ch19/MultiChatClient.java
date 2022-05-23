package ch19;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MultiChatClient 
{
	static String nickName = null;
	static JTextArea ta;
	static JTextField tf;
	static DataOutputStream out;
	
	public static void main(String[] args) 
	{
		nickName = JOptionPane.showInputDialog("��ȭ���� �Է��ϼ��� : ");
		new ClientScreen(nickName);
		
		Socket socket;
		try {
			String serverIp = "localhost";
			socket = new Socket(serverIp,7777);
			System.out.println("������ ����Ǿ����ϴ�.");
			Thread receiveThread = new ClientReceiver(socket);
			receiveThread.start();
			Thread sender = new ClientSender(socket,nickName);
			sender.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} //main()
	
	//�޽��� ���ۿ� ������ Ŭ����
	static class ClientSender extends Thread 
	{
		Socket socket;
		String name;
		public ClientSender(Socket socket, String name) 
		{
			this.socket = socket;
			try {
				out = new DataOutputStream(socket.getOutputStream());
				this.name = name;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	//������
		
		@Override
		public void run() 
		{
			Scanner scan = new Scanner(System.in);
			try {
				if(out != null)
				{
					out.writeUTF(name); //��ȭ�� ������
				}
				while(out != null)
				{
					out.writeUTF("[ " + name + " ]" + scan.nextLine());
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		} //run
		
	} //ClientSender
	
	//�޽��� ���ſ� ������ Ŭ����
	static class ClientReceiver extends Thread
	{
		Socket socket;
		DataInputStream in;
		
		public ClientReceiver(Socket socket) 
		{
			this.socket = socket;
			try {
				in = new DataInputStream(socket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} //������
		@Override
		public void run() {
			while(in != null)
			{
				try {
					String s = in.readUTF();
					System.out.println(s);
					ta.append(s + "\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}//run
	}// clinetReceiver
	
	//ȭ�� ����
	static class ClientScreen extends JFrame implements ActionListener
	{
		public ClientScreen(String nick) 
		{
			setSize(300,500);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setTitle(nick + " �� ä�ù� Ŭ���̾�Ʈ !");
			setLayout(new BorderLayout());
			ta = new JTextArea(25,40);
			tf = new JTextField(25);
			tf.addActionListener(this);
			JPanel panel = new JPanel();
			
			panel.setLayout(new BorderLayout());
			//�г��� ��ġ������ ����
			panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			JLabel label_name = new JLabel(nick + " ��");
			panel.add(label_name,BorderLayout.WEST);
			panel.add(tf,BorderLayout.CENTER);
			
			//JTextArea ó��
			JScrollPane jspanel = new JScrollPane();
			panel.add(jspanel, BorderLayout.NORTH);
			jspanel.setViewportView(ta);
			add(panel, BorderLayout.SOUTH);
			setVisible(true);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource() == tf)
			{	//�ؽ�Ʈ�ʵ忡�� ����Ű�� �Է��ϸ� 
				try {
					//�ؽ�Ʈ�ʵ忡 �Է��� ���ڿ��� ����
					out.writeUTF("[ "+nickName+" ]" + tf.getText());
					tf.setText("");
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		}
		
	}
}
