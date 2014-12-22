

import java.io.IOException;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class ProductLineRemoteApi {

	private String port = "8080";
	private String host = "localhost";

	public ProductLineRemoteApi() {
		Properties props = new Properties();
		try {
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("productline.properties"));
			port = props.getProperty("port");
			host = props.getProperty("host");		
		} catch (Exception e) {
			//swallow
		}

	}

	public String checkItem(String itemid){

		String url = "http://" + host + ":" + port +
				"/ProductLineInterface/rest/inventory/check?" +
				"itemid=" + itemid;
		
		//System.out.println("*** Submit URL : " + url);


		HttpClient httpClient = new DefaultHttpClient();

		HttpGet httpGet = new HttpGet(url);
		HttpResponse httpResponse;
		String responseString="unknown";
		try {
			httpResponse = httpClient.execute(httpGet);
			HttpEntity entity = httpResponse.getEntity();
			responseString = EntityUtils.toString(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}


		return responseString;
	}

	public String reserveItem(String itemid){

		String url = "http://" + host + ":" + port +
				"/ProductLineInterface/rest/inventory/reserve?" +
				"itemid=" + itemid;
		
		//System.out.println("*** Submit URL : " + url);


		HttpClient httpClient = new DefaultHttpClient();

		HttpGet httpGet = new HttpGet(url);
		HttpResponse httpResponse;
		String responseString="unknown";
		try {
			httpResponse = httpClient.execute(httpGet);
			HttpEntity entity = httpResponse.getEntity();
			responseString = EntityUtils.toString(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}


		return responseString;
	}

	public static void main(String[] args) {
		
		ProductLineRemoteApi pl = new ProductLineRemoteApi();
		String response;
		response = pl.checkItem("111");
		System.out.println("****" + response);
		response = pl.checkItem("222");
		System.out.println("****" + response);
		response = pl.checkItem("11");
		System.out.println("****" + response);
		response = pl.reserveItem("111");
		System.out.println("****" + response);
		response = pl.reserveItem("222");
		System.out.println("****" + response);
		response = pl.reserveItem("11");
		System.out.println("****" + response);
		

	}

}
