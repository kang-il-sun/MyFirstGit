package ch19;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServer 
{
	public static void main(String[] args) throws Exception 
	{	//������ ���� �غ�
		ServerSocket serversocket = null;
		
		try 
		{
			//���񽺸� ���� ��Ʈ ����
			serversocket = new ServerSocket(5555); //��Ʈ��ȣ 5555�� ����
			System.out.println("���񽺰� ���۵Ǿ����ϴ�...");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("���񽺸� ������ �� �����ϴ�...");
			System.exit(1); //�������� ����
		}
		
		//�Ϲ� (Ŭ���̾�Ʈ)���� �غ�
		Socket clientSocket = null;
		try
		{	//Ŭ���̾�Ʈ�� ������ ��ٷȴٰ� �����ϸ� ���� ����
			clientSocket = serversocket.accept();
			System.out.println("Ŭ���̾�Ʈ�� ip : " + clientSocket.getInetAddress().getHostAddress());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
		//�߽ſ� ��Ʈ��
		PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(),true);
		
		//���ſ� ��Ʈ��
		BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
		String receive = "";
		String send = "Welcome !!!";
		
		writer.println(send); //�޼���������
		
		Scanner scan = new Scanner(System.in);
		while(true)
		{
			receive = reader.readLine();	//�� ������ ����
			if(receive == null || receive.equals("quit"))
			{
				break;
			}
		
		System.out.println("[client] " + receive );
		System.out.println("������ �Է��ϼ���(���� : quit) : ");
		send = scan.nextLine();
		writer.println(send);	//�޽��� ������
		if(send.equals("quit")) {break;}
		}
		
		//���ҽ� ����
		scan.close();
		writer.close();
		reader.close();
		clientSocket.close();
		serversocket.close();
		
	}
	
}
