
package ch19;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class URLEncodeExam 
{
	public static void main(String[] args)
	{	//url ���� �ѱ�, Ư�����ڰ� ���Եɼ� ����..
		//url encoding(���ڵ�) ==> �ѱ��̳� Ư�����ڸ� url �������� ��ȯ��Ŵ
		//url decoding(���ڵ�) ==> ���ڵ� �� ���ڿ��� ó���� �������� ������Ŵ
		try 
		{
			String str = "��浿";
			System.out.println(URLEncoder.encode(str,"utf-8"));
			System.out.println(URLDecoder.decode("%EA%B9%80%EA%B8%B8%EB%8F%99","utf-8"));
		} 
		catch (Exception e)
		{
			
		}
	}
}
