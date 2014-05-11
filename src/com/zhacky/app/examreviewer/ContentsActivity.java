package com.zhacky.app.examreviewer;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.zhacky.app.examreviewer.utils.ExpandableAdapter;
import com.zhacky.app.examreviewer.utils.LessonHelper;

public class ContentsActivity extends ExpandableListActivity {
	/**      Declarations      **/
	ExpandableListAdapter mAdapter;
	LessonHelper helper;
	
	
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	// -->  Set up our adapter
	mAdapter = new ExpandableAdapter(this);
	setListAdapter(mAdapter);

	// -->  initialize LessonHelper
	
}//--------------------end onCreate()--------------------

@Override
public boolean onChildClick(ExpandableListView parent, View v,
		int groupPosition, int childPosition, long id) {
//	helper = new LessonHelper() {
//	};
	Intent intent = new Intent(ContentsActivity.this,StudyActivity.class);
//	Lesson lesson = helper.getLesson(groupPosition,childPosition);
//if(lesson != null){
//	Toast.makeText(this, lesson.getTitle().toString(), Toast.LENGTH_SHORT).show();
	// -->  intent put Extra
//	intent.putExtra(".model.Lesson", lesson);
	intent.putExtra("Group", groupPosition);
	intent.putExtra("Child", childPosition);
	startActivity(intent);
	
	return true;
//}
//	return false;
}//--------------------end onChildClick()--------------------






}//--------------------end CLASS--------------------




