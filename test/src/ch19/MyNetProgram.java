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
			System.out.println("1) ���� ��ī�� ���� : ");
			while(lan.hasMoreElements())
			{
				NetworkInterface net = lan.nextElement();
				System.out.println(net);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}	//Lanī�� ���� 
		
		
		System.out.println();
		System.out.println();	
		
		
		try
		{
			InetAddress[] address = InetAddress.getAllByName("naver.com");
			System.out.println("2) Naver �� ������");
			for(int i = 0; i < address.length; i++)
			{
				System.out.println(address[i]);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}	//Naver �� ������
		
		
		System.out.println();
		System.out.println();	
		
		
		try 
		{
			InetAddress address1 = InetAddress.getByName("naver.com");
			System.out.println(address1.getHostName()+" �� ȣ��Ʈ �̸� : " + address1.getHostName());
			System.out.println("���� IP ��ȣ : " + address1.getHostAddress());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}	//Naver ȣ��Ʈ �̸�, IP �ּ�
		
		
		System.out.println();
		System.out.println();	
		
		
		ServerSocket port = null;
		System.out.println();
		System.out.println("4) ���� ��� port��ȣ�� : ");
		for(int i=0; i<=65535; i++) {
			try
			{
				port = new ServerSocket(i);
				port.close();
			} 
			catch (Exception e)
			{
				System.out.println(i + "�� ��Ʈ�� ������Դϴ�.");
			} //Port ��ȣ 
		}
	}
}
