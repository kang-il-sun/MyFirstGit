package ch19;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.util.Enumeration;

public class MyNetProgram 
{
	public static void main(String[] args) 
	{
		try 
		{
			Enumeration<NetworkInterface> lan = NetworkInterface.getNetworkInterfaces();
			System.out.println("1) 나의 랜카드 정보 : ");
			while(lan.hasMoreElements())
			{
				NetworkInterface net = lan.nextElement();
				System.out.println(net);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}	//Lan카드 정보 
		
		
		System.out.println();
		System.out.println();	
		
		
		try
		{
			InetAddress[] address = InetAddress.getAllByName("naver.com");
			System.out.println("2) Naver 의 서버들");
			for(int i = 0; i < address.length; i++)
			{
				System.out.println(address[i]);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}	//Naver 의 서버들
		
		
		System.out.println();
		System.out.println();	
		
		
		try 
		{
			InetAddress address1 = InetAddress.getByName("naver.com");
			System.out.println(address1.getHostName()+" 의 호스트 이름 : " + address1.getHostName());
			System.out.println("메인 IP 번호 : " + address1.getHostAddress());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}	//Naver 호스트 이름, IP 주소
		
		
		System.out.println();
		System.out.println();	
		
		
		ServerSocket port = null;
		System.out.println();
		System.out.println("4) 나의 사용 port번호들 : ");
		for(int i=0; i<=65535; i++) {
			try
			{
				port = new ServerSocket(i);
				port.close();
			} 
			catch (Exception e)
			{
				System.out.println(i + "번 포트는 사용중입니다.");
			} //Port 번호 
		}
	}
}
