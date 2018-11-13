package demo;

import java.io.IOException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

class MyInterceptor1 implements ClientHttpRequestInterceptor {

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		System.out.println("in myinterceptor - intercept method");
		System.out.println("Request Details" + request.getHeaders());
		return execution.execute(request, body);
	}
}
public class ReqResLoginExample {

	static String url ="https://reqres.in//api/login";
	static RestTemplate restTempalte =new RestTemplate();
	static String token = "";
	
	public static void login(){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		String str = "{\"email\":\"peter@klaven\",\"password\":\"cityslicka\"}";
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity(str,headers);
		
		try{
		
		ResponseEntity<String> resp = restTempalte.exchange(url, HttpMethod.POST, request,String.class);
		System.out.println("Success"+resp.getStatusCode());
		System.out.println("Token "+resp.getBody());
		token = resp.getBody();
		}catch (HttpClientErrorException e) {
			System.out.println("Client Error "+e.getStatusCode());	
		}catch (HttpServerErrorException e) {
			System.out.println("Server error"+e.getStatusCode());
		}
	}
	
	public static void main(String[] args) {
		login();

	}

}
