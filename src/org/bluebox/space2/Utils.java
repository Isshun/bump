package org.bluebox.space2;

public class Utils {

	private static int sUUID;
	
	public static String getFormatedTime (int time) {
		if (time < 10) {
			return "0:0" + time;
		} else if (time < 60) {
				return "0:" + time;
		} else {
			int sec = time % 60;
			return "" + (int)(time / 60) + ":" + (sec < 10 ? "0" + sec : sec);
		}
	}
	
	public static int getUUID() {
		return ++sUUID;
	}

	public static void resetUUID () {
		sUUID = 0;
	}

}
