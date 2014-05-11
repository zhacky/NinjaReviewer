package com.zhacky.app.examreviewer.utils;

import android.annotation.SuppressLint;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.zhacky.app.examreviewer.ContentsActivity;

public class ExpandableAdapter extends BaseExpandableListAdapter {
	private ContentsActivity activity;

	public ExpandableAdapter(ContentsActivity activity) {
		super();
		this.activity = activity;
	}
	
	/**      parent      **/

	private String categoryA = "Exam-specific";
	private String categoryB = "English";
	private String categoryC = "Math";

	
	/**      categoryA      **/

	private String Achild1 = "Exam Guide";
	private String Achild2 = "R.A. 6713";
	private String Achild3 = "Others";
	
	/**      categoryB      **/

	private String Bchild1 = "Vocabulary";
	private String Bchild2 = "Analogy";
	private String Bchild3 = "Grammar";
	
	
	/**      categoryC      **/

	private String Cchild1 = "Arithmetic";
	private String Cchild2 = "Word problems";
	private String Cchild3 = "Logical Reasoning";
	


	private String[] groups = { categoryA, categoryB, categoryC };
	private String[][] children = { { Achild1, Achild2, Achild3 },
			{ Bchild1, Bchild2, Bchild3 }, { Cchild1, Cchild2, Cchild3 }};

	/** Overrides **/

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return children[groupPosition][childPosition];
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}


	@Override
	public int getChildrenCount(int groupPosition) {
		return children[groupPosition].length;
	}

	// --> textView and getGenericView()
	@SuppressLint("NewApi")
	public TextView getGenericView() {
		// --> Layout parameters for the ExpandableListView
		AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT, 64);

		TextView textView = new TextView(activity);
		textView.setLayoutParams(lp);
		// --> Center the text vertically
		textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
		// --> Set the text starting position
		textView.setPaddingRelative(36, 0, 0, 0);
		// --> Set the text alignment
		textView.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
		return textView;
	}// --------------------end getGenericView()--------------------


	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
			ViewGroup parent) {
		TextView textView  = getGenericView();
		textView.setText(getChild(groupPosition,childPosition).toString());
		return textView;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return groups[groupPosition];
	}

	@Override
	public int getGroupCount() {
		return groups.length;
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		TextView textView = getGenericView();
		textView.setText(getGroup(groupPosition).toString());
		return textView;
	}


	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
	@Override
	public boolean hasStableIds() {
		return true;
	}

}
