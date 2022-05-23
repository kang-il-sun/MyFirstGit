package ch19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient 
{
	private String str;
	private BufferedReader file;
	private static int SERVERPORT = 3000;
	
	public UDPClient(String ip, int port) 
	{
		try {
			InetAddress ia = InetAddress.getByName(ip);
			DatagramSocket ds = new DatagramSocket(port);
			System.out.println("���������� ������ messge : ");
			file = new BufferedReader(new InputStreamReader(System.in));
			str = file.readLine();
			byte buffer[] = str.getBytes();
			//�����ͱ׷� ��Ŷ ��ü ����
			DatagramPacket dp = new DatagramPacket(buffer, buffer.length,ia,SERVERPORT);
			ds.send(dp); //������ ����
			buffer = new byte[512];
			dp = new DatagramPacket(buffer, buffer.length);
			ds.receive(dp); //�������� ���� �޽����� ����
			ds.close();
			System.out.println("server ip : " + dp.getAddress() + ", server port : " + dp.getPort());
			System.out.println("�������� ���� �޽��� : " + new String(dp.getData()).trim());
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args) 
	{
		new UDPClient("localhost",2000);
	}
}
