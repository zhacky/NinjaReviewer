package com.zhacky.app.examreviewer.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Lesson implements Parcelable {
	long id;
	int code;
	int group;
	int child;
	String urlA;
	String urlB;
	String urlC;
	String urlD;
	String title;
	String details;
	public final static String LOGTAG = "QUIZAPP";
	
	
	/**      Constructor      **/

	public Lesson(){
	}
	
	public Lesson(Parcel in) {
		
		id = in.readLong();
		code = in.readInt();
		group = in.readInt();
		child = in.readInt();
		title = in.readString();
		details = in.readString();
	}
	
	// -->  getters and setters
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	public int getChild() {
		return child;
	}

	public void setChild(int child) {
		this.child = child;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetails(int page) {
		switch (page) {
		case 1:
			details = urlA;
			
			break;
		case 2:
			details = urlB;
			
			break;
		case 3:
			details = urlC;
			
			break;
		case 4:
			details = urlD;
			
			break;
		default:
			break;
		}
		return details;
	}
	public void setDetails(String details, int page) {
		switch (page) {
		case 1:
			urlA = details;
			
			break;
		case 2:
			urlB = details;
		
			break;
		case 3:
			urlC = details;
			
			break;
		case 4:
			urlD = details;
			
			break;
		default:
			break;
		}
		this.details = details;
		
	}
	//--------------------end getters and setters--------------------
	
	
	/**      Overridden methods      **/

	@Override
	public int describeContents() {
		return 0;
	}//--------------------end describeContents--------------------
	
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(id);
		dest.writeInt(code);
		dest.writeInt(group);
		dest.writeInt(child);
		dest.writeString(title);
		dest.writeString(details);
		
	}//--------------------end writeToParcel(dest, flags)--------------------
	
	
	
	public static final Parcelable.Creator<Lesson> CREATOR = new Creator<Lesson>() {
		
		@Override
		public Lesson[] newArray(int size) {
			return new Lesson[size];
		}
		
		@Override
		public Lesson createFromParcel(Parcel source) {
			return new Lesson(source);
			
		}//--------------------end createFromParcel--------------------
		
	};
	//--------------------end overrides--------------------
	

}
