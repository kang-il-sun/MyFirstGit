package ch19;

import java.net.InetAddress;

public class InetAddressExam 
{
	public static void main(String[] args) 
	{	//��Ʈ��ũ ���α׷��� ����ó���� �ʼ�
		try 
		{
			InetAddress address = InetAddress.getByName("naver.com");
			System.out.println(address);	//ȣ��Ʈ�̸� , ip�ּ�
			System.out.println(address.getHostName()); //ȣ��Ʈ�̸�(�������̸�)
			System.out.println(address.getHostAddress());	//ip�ּ� 
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
