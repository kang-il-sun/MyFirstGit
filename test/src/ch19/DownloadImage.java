package ch19;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class DownloadImage 
{
	public static void main(String[] args) throws Exception 
	{
		String website = "https://imgnews.pstatic.net/image/139/2022/05/20/0002167316_001_20220520134301320.jpg?type=w647";
		
		System.out.println("�ٿ�ε带 �����մϴ�.");
		URL url = new URL(website);	//url ����
		
		byte[] buffer = new byte[2048];	//buffer�� ����Ʈ �迭[2mb]
		//			���Ϸ� ����  <==	���α׷� <== ������ �̹��� ����
		
		//try ~ with ��
		//try(���ҽ������) {  } catch(Ex e) {  }
		//finally �� ��� ���ҽ��� �ڵ����� �����Ŵ
		try(InputStream in = url.openStream(); OutputStream out = new FileOutputStream("c:\\test\\test.jpg"))
		{
			int length = 0;
			//���� ����Ʈ�� = ��Ʈ��.read(����)
			//���̻� ���� ������ ������ -1
			while((length = in.read(buffer)) != -1)
			{
				System.out.println(length + "����Ʈ ����.");
				//��½�Ʈ��.write(����,�����ε���,���̰�)
				out.write(buffer,0,length);
			}
			System.out.println("�ٿ�ε尡 �Ϸ�Ǿ����ϴ�...");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
