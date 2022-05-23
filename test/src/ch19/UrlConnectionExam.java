package ch19;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlConnectionExam 
{
	public static void main(String[] args)
	{
		URL url = null;
		
		try
		{
			url = new URL("http://google.com");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			if(conn != null)
			{	//������ �Ǿ�������
				conn.setConnectTimeout(3000); //Ÿ�Ӿƿ� �ð� ���� 
				StringBuilder sb = new StringBuilder();
				//���������� ó���� ���
				if(conn.getResponseCode() == HttpURLConnection.HTTP_OK)
				{	//��Ʈ���� ����ؼ� html source code �� �о��
					//�Է¹��۸� ����
					BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
					while(true)
					{
						String line = br.readLine();
						if(line == null) break;	//�Էµ� ������ ������ Ż��
						sb.append(line+"\r\n"); //�ٹٲ� ���� �߰�
					}
					br.close();	//���� �ݱ�
				}
				conn.disconnect();	//http���� ����
				System.out.println(sb);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace(); 
		}
	}
}
