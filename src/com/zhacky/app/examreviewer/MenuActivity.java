package com.zhacky.app.examreviewer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Window;
import android.widget.Button;





// advertisement's imports
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MenuActivity extends Activity {
	/** DECLARATIONS **/
	Button btnStudy;
	Button btnQuiz;
	Button btnScore;
	OnClickListener l;
	public static final String LOGTAG = "QUIZAPP";

	/**  ADVERTISE   **/
	AdView adView;
	public static final String AD_UNIT_ID = "ca-app-pub-6338321126542219/6281126895";

	/** START OF APP **/
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_menu);
		setUpListener();
		// --> init buttons
		btnStudy = (Button) findViewById(R.id.btn_study);
		btnQuiz = (Button) findViewById(R.id.btn_quiz);
		btnScore = (Button) findViewById(R.id.btn_score);
		btnStudy.setOnClickListener(l);
		btnQuiz.setOnClickListener(l);
		btnScore.setOnClickListener(l);
		// --> adView
		adView = (AdView) findViewById(R.id.adView);
		AdRequest.Builder builder = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).addTestDevice("B3EEABB8EE11C2BE770B684D95219ECB");
		AdRequest adRequest = builder.build();
		// --> test adView and adRequest	
					try {
						if(adView == null){
							Log.i(LOGTAG, "@MenuActivity-onCreate(): adView is null");
						} else {
							Log.i(LOGTAG, "@MenuActivity-onCreate(): adRequest is null");
						}
						adView.loadAd(adRequest);
					} catch (Exception e) {

						e.printStackTrace();
						Log.i(LOGTAG,"Error: " + e.getMessage() + "\n@MenuActivity-onCreate()");
					}

	}// --------------------end onCreate()--------------------



/**      Listener Details      **/

	private void setUpListener() {
		
		l = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent;
				v.playSoundEffect(android.view.SoundEffectConstants.CLICK);
				switch (v.getId()) {
				case R.id.btn_study:
					intent = new Intent(MenuActivity.this,ContentsActivity.class);
					startActivity(intent);
					break;
				case R.id.btn_quiz:
					intent = new Intent(MenuActivity.this,QuizActivity.class);
					startActivity(intent);
					break;

				case R.id.btn_score:
					intent = new Intent(MenuActivity.this,ScoresActivity.class);
					startActivity(intent);
					break;
				default:
					break;
				}
			}
		};
		
	}//--------------------end setUpListener--------------------
	



	/** OVERRIDDEN METHODS **/
	@Override
	public void onResume() {
		super.onResume();
		if (adView != null) {
			adView.resume();
		}
	}

	@Override
	public void onPause() {
		if (adView != null) {
			adView.pause();
		}
		super.onPause();
	}

	/** Called before the activity is destroyed. */
	@Override
	public void onDestroy() {
		// Destroy the AdView.
		if (adView != null) {
			adView.destroy();
		}
		super.onDestroy();
	}
	
	
//--------------------end Overrides--------------------

	
}
