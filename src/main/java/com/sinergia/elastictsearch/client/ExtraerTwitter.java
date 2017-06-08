
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

import com.google.gson.Gson;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class ExtraerTwitter {
	
	public static void main(String arg[]) throws TwitterException{
		
		try {
			//Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
			@SuppressWarnings("resource")
			TransportClient client = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localost"), 9301));
			IndexResponse response = client.prepareIndex("megacorp","employee","1").get();
			String id = response.getId();
			System.out.println("id: " + id);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
//		List<Object> list = searchForTweets();
//		for (Object object : list) {
//			System.out.println("object: " + object);
//		}
		
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
