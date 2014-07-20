package com.prach.test.VoiceRecognizer;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

public class VoiceRecognizer extends Activity {
    TextView MainTV = null;
    Intent VoiceIntent = null;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        MainTV = (TextView)findViewById(R.id.maintv);
        
        VoiceIntent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        VoiceIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.JAPAN.toString());
        startActivityForResult(VoiceIntent, 0);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    //    ...code for checking resultCode and data intent...
    	Log.i("info","onActivityResult");
    	ArrayList<String> results = data.getStringArrayListExtra(android.speech.RecognizerIntent.EXTRA_RESULTS);
    	StringBuffer temp = new StringBuffer();
    	for (int i = 0; i < results.size(); i++) {
			temp.append(results.get(i));
			temp.append("\n");
		}
    	Log.i("info","result"+temp.toString());
    	MainTV.setText(temp.toString());
    	
    }
    
    @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) { 
		if (keyCode == KeyEvent.KEYCODE_BACK) { 
			Toast.makeText(getApplicationContext(), "Quiting VoiceRecognizer", Toast.LENGTH_SHORT).show();
			finish();
	        return true;
		}else if (keyCode == KeyEvent.KEYCODE_MENU) { 
			startActivityForResult(VoiceIntent, 0);
			return true;
		}else return false;
		//return super.onKeyDown(keyCode, event);
	}
}