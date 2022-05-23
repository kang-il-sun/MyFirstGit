package ch19;

import java.net.NetworkInterface;
import java.util.Enumeration;

public class LanInfo 
{
	public static void main(String[] args) 
	{
		try 
		{	//NetworkInterface : ��ī���� �������� ���� 
			Enumeration<NetworkInterface> enu = NetworkInterface.getNetworkInterfaces();
		 	//getNetworkInterfaces : ��Ʈ��ũ �������̽� (��ī��) �� �پ��� ���� ����
			while(enu.hasMoreElements())
			{
				NetworkInterface net = enu.nextElement();
				System.out.println(net);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
