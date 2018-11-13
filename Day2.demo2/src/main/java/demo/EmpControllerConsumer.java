package demo;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

public class EmpControllerConsumer {

	static String url ="http://localhost:8080";
	static RestTemplate restTempalte =new RestTemplate();
	
	public static void post(){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String str = "{\"empno\":1111,\"ename\":\"Name1\",\"salary\":10001.0}";
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity(str,headers);
		try{
		//ResponseEntity<String> resp = restTempalte.postForEntity(url +"/saveEmployee", request, String.class);
		ResponseEntity<String> resp = restTempalte.exchange(url +"/saveEmployee", HttpMethod.POST, request,String.class);
		System.out.println("Success"+resp.getStatusCode());
		}catch (HttpClientErrorException e) {
			System.out.println("Client Error "+e.getStatusCode());	
		}catch (HttpServerErrorException e) {
			System.out.println("Server error"+e.getStatusCode());
		}
	}
	
	public static void getAll(){
		ResponseEntity<String> str = restTempalte.getForEntity(url, String.class);
		//ResponseEntity<List<Emp>> obj  = restTempalte.getForEntity(url, List.class<Emp.class>);
		//ResponseEntity<Object> obj  = restTempalte.getForEntity(url, Object.class);
		//List<Emp> list = (List<Emp>)obj;
		System.out.println(str);
	}
	
	public static void delete(){
		restTempalte.delete(url+"/1");
	}
	
	public static void update(){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String str = "{\"empno\":1111,\"ename\":\"Name1\",\"salary\":10001.0}";
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity(str,headers);
		try{
		//ResponseEntity<String> resp = restTempalte.postForEntity(url +"/saveEmployee", request, String.class);
		ResponseEntity<String> resp = restTempalte.exchange(url +"/saveEmployee", HttpMethod.POST, request,String.class);
		System.out.println("Success"+resp.getStatusCode());
		}catch (HttpClientErrorException e) {
			System.out.println("Client Error "+e.getStatusCode());	
		}catch (HttpServerErrorException e) {
			System.out.println("Server error"+e.getStatusCode());
		}
	}
	
	public static void main(String[] args) {
		 getAll();
		 post();
		 delete();
		 update();
	}

}
