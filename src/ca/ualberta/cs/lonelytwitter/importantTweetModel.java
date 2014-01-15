package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

public class importantTweetModel extends lonelyTweetModel {

	public importantTweetModel(String text, Date timestamp) {
		super(text, timestamp);
		// TODO Auto-generated constructor stub
	}

	public importantTweetModel(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}
	public void setText(String text) {
		this.text = text;
	}
	
	@Override
	public void setImportant(boolean important) throws Exception {
		if(!important){
			throw new IllegalArgumentException();
		}
		this.important = important;
	}
}
