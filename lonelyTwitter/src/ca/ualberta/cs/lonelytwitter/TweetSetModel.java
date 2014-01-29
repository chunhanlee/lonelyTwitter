package ca.ualberta.cs.lonelytwitter;

import java.util.List;
import java.util.ArrayList;


public class TweetSetModel
{
	int count = 0;
	List<LonelyTweetModel> tweets = new ArrayList<LonelyTweetModel>();
	public int countTweets(){
		return count;
	}
	public void addTweet(NormalTweetModel normalTweetModel)
	{
		
		tweets.add(normalTweetModel);
		count++;

	//		throw new IllegalArgumentException("tweet already exists");

	}
	public List<LonelyTweetModel> getTweets(){
		
		return tweets;
	}
}
