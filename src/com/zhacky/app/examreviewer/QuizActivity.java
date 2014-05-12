package com.zhacky.app.examreviewer;

import java.util.List;

import com.zhacky.app.examreviewer.data.XMLParser;
import com.zhacky.app.examreviewer.models.Question;
import com.zhacky.app.examreviewer.utils.UIHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class QuizActivity extends Activity {

/**      variables      **/
private final String LOGTAG = "QUIZAPP";
List<Question> questions;
long question_no = 1;
int total_count;
int total_correct;
int correctAns;
int userAns;
int current_points = 0;
int current_correct = 0;
int points;
int difficulty;
int userscore;
int qchar_count;


/**      methods      **/
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_quiz);
		
		InitQuestions();
	
		InitViews();
	
	
	}//--------------------end onCreate(savedInstanceState)--------------------

private void InitQuestions() {
	XMLParser parser = new XMLParser();
	questions = parser.parseXML(this);
	total_count = questions.size();
	total_correct = 0;
	userscore = 0;
	
}//--------------------end InitQuestions()--------------------

private void InitViews() {
UIHelper.setButtonText(this, R.id.buttonNext, getString(R.string.next));
Button btnConfirm = (Button) findViewById(R.id.buttonNext);
OnClickListener listener = new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		clickFunction();
		v.playSoundEffect(android.view.SoundEffectConstants.CLICK);
	}

};
btnConfirm.setOnClickListener(listener);
setQuestion(question_no);
}//--------------------end InitViews()--------------------

private void clickFunction() {
	
	userAns = UIHelper.getSelected(this, R.id.radioGroup1);
	// if user didn't pick any choice
	if(userAns == 0){
		Toast.makeText(this, R.string.please_select_one, Toast.LENGTH_LONG).show();
		return;
	}
	// if user reaches the final question
	if(question_no + 1 == total_count){
		UIHelper.setButtonText(this, R.id.buttonNext, getString(R.string.confirm));
	}
	// if user got the correct answer
	if(userAns == correctAns){
		current_points = points;
		current_correct = 1;
	} else {
		current_points = 0;
		current_correct = 0;
	}
	// add the points
	userscore += current_points;
	total_correct += current_correct;
	// if questions reach the total
	if (question_no >= total_count) {
		finalizeScore();
	}
	else {
		question_no += 1;
		setQuestion(question_no);
	}
	
	}

/**
 * Finalizes the score and passes values to the EndQuizActivity
 * Values passed: userscore, total_correct, fraction
 */
private void finalizeScore() {
	Intent intent = new Intent(this,EndQuizActivity.class);
	intent.putExtra("Score", userscore);
	intent.putExtra("Corrects", total_correct);
	double fraction = (double)total_correct / total_count;
	intent.putExtra("Fraction", fraction);
	//TODO: Temporary Toast
	Toast.makeText(
			this,
			"Fraction: " + fraction + "\nCorrect: " + total_correct
					+ "\nCount: " + total_count, Toast.LENGTH_SHORT).show();
	//start the activity and finish this one
	startActivity(intent);
	this.finish();
}//--------------------end finalizeScore()--------------------

/**
 * Sets the question and the choices based on the question number
 * @param question_number - the incremented question_no value
 */
private void setQuestion(long question_number) {
	Log.i(LOGTAG, "@QuizActivity-setQuestion(): Question No." + question_number);
	UIHelper.clearSelection(this, R.id.radioGroup1);
	if (question_number > total_count) {
		
	}
	else {
		for (Question question : questions) {
			if(question.getId()==question_number){
				UIHelper.displayText(this, R.id.textView1, question.getQuestion());
				UIHelper.setRadioText(this, R.id.radio1,
						question.getChoiceA());
				UIHelper.setRadioText(this, R.id.radio2,
						question.getChoiceB());
				UIHelper.setRadioText(this, R.id.radio3,
						question.getChoiceC());
				UIHelper.setRadioText(this, R.id.radio4,
						question.getChoiceD());
				UIHelper.setRadioText(this, R.id.radio5,
						question.getChoiceE());
				UIHelper.displayText(this, R.id.questionNumber, String.valueOf(question.getId()));
				// initialize correct answer
				correctAns = question.getAnswer();
				points = question.getPoints();
				difficulty = question.getDifficulty();
		}
	}
	
}//--------------------end setQuestion(question_number)--------------------

}
	
		
	
}//--------------------end --------------------

