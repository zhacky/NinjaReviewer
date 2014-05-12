package com.zhacky.app.examreviewer;

import com.zhacky.app.examreviewer.utils.UIHelper;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class EndQuizActivity extends Activity {

	/** variables **/

	private final String USERNAME = "UserName";
	private final String HIGHSCORE = "HighScore";
	private final String SHARED = "highscore";
	SharedPreferences highscore_settings;
	int user_score;
	String user_name;
	int current_highscore;

	/** methods **/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_endquiz);
		highscore_settings = getSharedPreferences(SHARED, MODE_PRIVATE);
		CheckHighScore();
		DisplayScores();
		DisplayRank();
		InitNameSave();

	}// --------------------end onCreate(savedInstanceState)--------------------




	private void CheckHighScore() {
		user_score = getIntent().getExtras().getInt("Score");
		current_highscore = highscore_settings.getInt(HIGHSCORE, 0);
		if (user_score > current_highscore) {
			UIHelper.enableEditText(this, R.id.etUsername, true);
		} else {
			UIHelper.setButtonVisibility(this, R.id.btnSaveName,
					Button.INVISIBLE);
			UIHelper.setEditTextVisibility(this, R.id.etUsername,
					Button.INVISIBLE);
		}
	}// --------------------end CheckHighScore()--------------------

	private void DisplayScores() {
		String userscore = String.valueOf(getIntent().getExtras().getInt(
				"Score"));
		String totalcorrect = String.valueOf(getIntent().getExtras().getInt(
				"Corrects"));
		UIHelper.displayText(this, R.id.tvCorrectAnswers, totalcorrect);
		UIHelper.displayText(this, R.id.tvUserScore, userscore);


	}

	private void DisplayRank() {
		double fraction = getIntent().getExtras().getDouble("Fraction");
		String rank = "Barbarian";
		UIHelper.setImage(this, R.id.imgRank, R.drawable.hs_barbarian);

		if (0.8 <= fraction && fraction < 0.9) {
			rank = "Swordsman";
			UIHelper.setImage(this, R.id.imgRank, R.drawable.hs_swordsman);
		} else if (0.9 <= fraction && fraction < 1){
			rank = "Ninja";
			UIHelper.setImage(this, R.id.imgRank, R.drawable.hs_ninja);
		}
		else if (fraction >= 1) {
			rank = "Sensei";
			UIHelper.setImage(this, R.id.imgRank, R.drawable.hs_sensei);
		} else {
			rank = "Unqualified";
		}
		UIHelper.displayText(this, R.id.tvRank, rank);

		
	}

	private void InitNameSave() {
		Button btnSave = (Button) findViewById(R.id.btnSaveName);
		Button btnTryAgain = (Button) findViewById(R.id.btnTryAgain);
		OnClickListener listener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.btnSaveName:
					clickedSave();
					break;
				case R.id.btnTryAgain:
					clickedTryAgain();
					break;

				default:
					break;
				}

			}

		};

		btnSave.setOnClickListener(listener);
		btnTryAgain.setOnClickListener(listener);		
	}//--------------------end InitNameSave()--------------------
	

	private void clickedSave() {
		if(user_score > current_highscore){
			user_name = UIHelper.getText(this, R.id.etUsername);
			saveSharedSettings();
			}
			//UIHelper.enableEditText(this, R.id.etUsername, false);
			//UIHelper.enableButton(this, R.id.btnSaveName, false);
			UIHelper.setButtonVisibility(this, R.id.btnSaveName, Button.INVISIBLE);
			UIHelper.setEditTextVisibility(this, R.id.etUsername, Button.INVISIBLE);
	}//--------------------end clickedSave()--------------------
	
	private void clickedTryAgain() {
		Intent intent = new Intent(this, ScoresActivity.class);
		startActivity(intent);
		this.finish();
		
	}
	
	
	
	/**      Other methods      **/
	private void saveSharedSettings() {
		if (user_score > current_highscore) {
			SharedPreferences.Editor editor = highscore_settings.edit();
			editor.putString(USERNAME, user_name);
			editor.putInt(HIGHSCORE, user_score);
			editor.commit();
		}
		

	}
}// --------------------end code--------------------

