
package ch19;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class URLEncodeExam 
{
	public static void main(String[] args)
	{	//url 에는 한글, 특수문자가 포함될수 없다..
		//url encoding(인코딩) ==> 한글이나 특수문자를 url 형식으로 변환시킴
		//url decoding(디코딩) ==> 인코딩 된 문자열을 처음ㅇ 내용으로 복원시킴
		try 
		{
			String str = "김길동";
			System.out.println(URLEncoder.encode(str,"utf-8"));
			System.out.println(URLDecoder.decode("%EA%B9%80%EA%B8%B8%EB%8F%99","utf-8"));
		} 
		catch (Exception e)
		{
			
		}
	}
}
