package tests;

import java.util.Date;

public class Q3_1_1 {
	public static void main(String[] args) {
		long dt = 100;// delta time
		long time = 0;// delta time
		MyFrame window = new MyFrame().show(); // GUI
		double MAX_TIME = 50 * 1000; // 50 seconds in mili seconds.
		Path path = new Path("path.txt");
		long last_time = new Date().getTime();
		Point p = path.getPointInTime(time);
		while (time < MAX_TIME) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long now = new Date().getTime();
			// OLDER CODE :: if(now>=last_time + dt) {
			time = time + (now - last_time);
			last_time = now;
			Point p2 = path.getPointInTime(time);
			window.update(p);
			window.repaint();
			// OLDER CODE :: } //if
		} // while
	}
}

class Path {

	Path(String p) {
	}

	Point getPointInTime(long time) {
		return null;
	}
}

class MyFrame {
	void update(Point p) {
	}

	void repaint() {
	}

	MyFrame show() {
		return this;
	}
}

class Point {

}
