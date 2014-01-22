package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class LonelyTwitterActivity<NormalTweetModel> extends Activity {

	private static final String FILENAME = "file2.json";
	private EditText bodyText;
	private ListView oldTweetsList;
	private ArrayAdapter<String> adapter = null;
	private List<String> list2;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bodyText = (EditText) findViewById(R.id.body);
		Button saveButton = (Button) findViewById(R.id.save);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);

		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK);
				String text = bodyText.getText().toString();
				//saveInFile(text, new Date(System.currentTimeMillis()));
				
				NormalTweetmodel obj = new NormalTweetmodel(text);
				obj.setText(text);
				obj.setTimestamp(new Date(System.currentTimeMillis()));
				Gson gson = new Gson();
				String json = gson.toJson(obj);
				saveInFile(json);
				
				
				//------
				list2.add(text);
				adapter.notifyDataSetChanged();
			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		List<String> tweets = (List<String>) loadFromFile();
		adapter = new ArrayAdapter<String>(this,R.layout.list_item, tweets);
		oldTweetsList.setAdapter(adapter);

	}

	private List<String> loadFromFile() {
		List<String> tweets = new ArrayList<String>();
		list2 = tweets;
		//LonelyTweetModel simpleClass = null;
		try {
			FileInputStream fis = openFileInput(FILENAME);
			//BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			Gson gson = new Gson();
			tweets.add(gson.fromJson(new InputStreamReader(fis), NormalTweetmodel.class).toString());
			
			
			/*
			String line = in.readLine();
			while (line != null) {
				tweets.add(line);
				line = in.readLine();
			}
			
			*/

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (List<String>) tweets;
		//return ;
	}
	
	
	
	
	private void saveInFile(String text) {
		try {
			FileOutputStream fos = openFileOutput(FILENAME,
					Context.MODE_PRIVATE);

			//fos.write(new String(date.toString() + " | " + text).getBytes());
			fos.write(text.getBytes());
			fos.close();
			//fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}