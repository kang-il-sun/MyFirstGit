package ch19;

import java.net.NetworkInterface;
import java.util.Enumeration;

public class LanInfo 
{
	public static void main(String[] args) 
	{
		try 
		{	//NetworkInterface : 랜카드의 정보값을 리턴 
			Enumeration<NetworkInterface> enu = NetworkInterface.getNetworkInterfaces();
		 	//getNetworkInterfaces : 네트워크 인터페이스 (랜카드) 의 다양한 정보 제공
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
