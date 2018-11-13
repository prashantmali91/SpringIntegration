package demo;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

class MyInterceptor implements ClientHttpRequestInterceptor {

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		System.out.println("in myinterceptor - intercept method");
		System.out.println("Request Details" + request.getHeaders());
		return execution.execute(request, body);
	}
}

public class ReqResConsumer {

	static String url = "https://reqres.in/api/users?page=2";

	public static void getAll() {
		RestTemplate restTempalte = new RestTemplate();
		restTempalte.getInterceptors().add(new MyInterceptor());
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		// Object response = restTempalte.exchange(url,
		// HttpMethod.GET,entity,Object.class);
		try {
			ResponseEntity<String> response = restTempalte.exchange(url, HttpMethod.GET, entity, String.class);
			System.out.println(response.toString());
		} catch (HttpClientErrorException e) {
			System.out.println("Client Error" + e.getRawStatusCode());
		} catch (HttpServerErrorException e) {
			System.out.println("Server Error" + e.getRawStatusCode());
		}

	}

	public static void main(String[] args) {
		getAll();
	}
}
