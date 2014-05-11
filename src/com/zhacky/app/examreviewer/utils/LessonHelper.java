package com.zhacky.app.examreviewer.utils;


import android.util.Log;

import com.zhacky.app.examreviewer.models.Lesson;

public class LessonHelper {
public String LOGTAG = "QUIZAPP";
	
	
	public LessonHelper() {
		super();
	}

	public Lesson getLesson(int groupPosition, int childPosition) {
		Lesson lesson = new Lesson();
		lesson.setTitle((groupPosition) + " - " + (childPosition));
		
		/**      set url      **/
		String url = "file:///android_asset/lessons/group"+String.valueOf((groupPosition + 1))+"/child" +String.valueOf((childPosition + 1)) + "/a.html";
		lesson.setDetails(url, 1);
		Log.i(LOGTAG, "@LessonHelper-getLesson(): " + url.toString());
		url = "file:///android_asset/lessons/group"+String.valueOf((groupPosition + 1))+"/child" +String.valueOf((childPosition + 1)) + "/b.html";
		lesson.setDetails(url, 2);
		Log.i(LOGTAG, "@LessonHelper-getLesson(): " + url.toString());
		url = "file:///android_asset/lessons/group"+String.valueOf((groupPosition + 1))+"/child" +String.valueOf((childPosition + 1)) + "/c.html"; 
		lesson.setDetails(url, 3);
		Log.i(LOGTAG, "@LessonHelper-getLesson(): " + url.toString());
		return lesson;
	}

	
}
