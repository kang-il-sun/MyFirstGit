package ch19;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressExam2 
{
	public static void main(String[] args) 
	{
		try
		{	//getAllByName("url") : �������� ip �ּҸ� �����ؼ� �迭�� ����
			InetAddress[] address = InetAddress.getAllByName("naver.com");
			for(int i = 0; i < address.length; i++)
			{	//������� (������ ��å�� 1���� ����)
				System.out.println(address[i]);
			}
		} 
		catch (UnknownHostException e)
		{	//UnknownHostException : �˷����� ���� ȣ��Ʈ (�ּ�)
			e.printStackTrace();
		}
	}
}
