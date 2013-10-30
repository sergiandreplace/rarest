package com.sergiandreplace.rarest.logger;


import android.util.Log;


public class Logger {
	public static enum Switcher {
		on, off
	};


	private Boolean show= false;



	public String toString() {
		return String.format("    Debug.start status=%s \n    Debug.end", getShow().toString());
	}

	public Boolean getShow() {
		return show;
	}

	public void setShow(Boolean show) {
		this.show = show;
	}
	
	public void log(String message) {
		if (show) {
			Log.d("RA",message);
		}
	}

	
	
	

}
