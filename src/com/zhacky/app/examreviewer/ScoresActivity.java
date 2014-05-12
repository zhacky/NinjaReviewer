package com.zhacky.app.examreviewer;

import com.zhacky.app.examreviewer.utils.UIHelper;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ScoresActivity extends Activity {

/**      variables      **/
private final String USERNAME = "UserName";
private final String HIGHSCORE = "HighScore";
private final String SHARED = "highscore";
SharedPreferences highscore_settings;
	
	
	/**      methods      **/
@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_highscores);
		InitResetButton();
		displayHighScore();
	}//--------------------end onCreate(savedInstanceState)--------------------




	private void InitResetButton() {
		OnClickListener listener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				clickedReset();
				
			}

			private void clickedReset() {
				SharedPreferences.Editor editor = highscore_settings.edit();
				editor.putString(USERNAME, "AAA");
				editor.putInt(HIGHSCORE, 0);
				editor.commit();
				displayHighScore();
				
				
				
			}

		
		};	
		//----------
		Button btnReset = (Button) findViewById(R.id.btnResetScore);
		btnReset.setOnClickListener(listener);

		
	}//--------------------end InitResetButton()--------------------
	
		private void displayHighScore() {
			highscore_settings = getSharedPreferences(SHARED,MODE_PRIVATE);
			String username = highscore_settings.getString(USERNAME, "AAA");
			int highscore = highscore_settings.getInt(HIGHSCORE, 0);
			UIHelper.displayText(this, R.id.tvHighScoreName, username);
			UIHelper.displayText(this, R.id.tvHighScoreValue, String.valueOf(highscore));
					
	}


}//--------------------end ()--------------------

