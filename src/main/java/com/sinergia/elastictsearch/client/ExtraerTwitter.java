
package com.sinergia.elastictsearch.client;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.jboss.netty.logging.JBossLoggerFactory;

import com.google.gson.Gson;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class ExtraerTwitter {
	
	public static void main(String arg[]) throws TwitterException{
		//1. Extraer de datos de Twitter 		
//		List<Object> list = searchForTweets();
//		for (Object object : list) {
//			System.out.println("object: " + object);
//		}
		
		//2. 
		
		//3. 
		try {
			Settings settings = Settings.builder().put("cluster.name", "my-application").build();
			@SuppressWarnings("resource")
//			TransportClient client = new PreBuiltTransportClient(settings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.1.3"), 9300));
			TransportClient client = new PreBuiltTransportClient(settings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
			IndexResponse response = client.prepareIndex("megacorp","employee","3").setSource().get();
			
			
			System.out.println("response.getId(): " + response.getId());
			System.out.println("response.getType(): " + response.getType());
			System.out.println("response.getClass(): " + response.getClass());
			System.out.println("response.getIndex(): " + response.getIndex());
			System.out.println("response.getResult(): " + response.getResult());
			System.out.println("response.getVersion(): " + response.getVersion());
			System.out.println("response.getShardId(): " + response.getShardId());
			System.out.println("response.getShardInfo(): " + response.getShardInfo());
			System.out.println("response.getShardInfo().getSuccessful(): " + response.getShardInfo().getSuccessful());
			
			
			
			
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		

		
	}
	
	public static List<Object> searchForTweets() throws TwitterException {
        Twitter twitter = new TwitterFactory().getInstance();
        Query query = new Query("filemon escobar");
        List<Object> tweetList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            QueryResult queryResult = twitter.search(query);
            tweetList.addAll(queryResult.getTweets());
            if (!queryResult.hasNext()) {
                break;
            }
            query = queryResult.nextQuery();
        }
        Gson gson = new Gson();
        return (List) tweetList.stream().map(gson::toJson).collect(Collectors.toList());
    }
}
