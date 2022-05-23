package ch19;

import java.net.URL;

public class URLInfo 
{
	public static void main(String[] args)
	{
		try 
		{
			URL url = new URL("https://sports.news.naver.com/news?oid=055&aid=0000974360");
			System.out.println("�������� : " + url.getProtocol());
			System.out.println("��Ʈ : " + url.getPort());
			System.out.println("ȣ��Ʈ : " + url.getHost());
			System.out.println("���� : " + url.getFile());
			System.out.println("��Ÿ : " + url.toExternalForm());
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
