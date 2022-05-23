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
			System.out.println("서버측에서 보내는 messge : ");
			file = new BufferedReader(new InputStreamReader(System.in));
			str = file.readLine();
			byte buffer[] = str.getBytes();
			//데이터그램 패킷 객체 생성
			DatagramPacket dp = new DatagramPacket(buffer, buffer.length,ia,SERVERPORT);
			ds.send(dp); //서버에 전송
			buffer = new byte[512];
			dp = new DatagramPacket(buffer, buffer.length);
			ds.receive(dp); //서버에서 보낸 메시지를 수신
			ds.close();
			System.out.println("server ip : " + dp.getAddress() + ", server port : " + dp.getPort());
			System.out.println("서버에서 보낸 메시지 : " + new String(dp.getData()).trim());
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
