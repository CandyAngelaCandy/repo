package ball.copy;

import java.awt.Point;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BowlingGame {

	public int getBowlingScore(String bowlingCode) {

		Pattern pattern = Pattern.compile("\\|{2}"); 
		Matcher matcher = pattern.matcher(bowlingCode);
		bowlingCode = matcher.replaceAll("*");

		String[] flags = bowlingCode.split("\\|");
		int[] scores = new int[10];
		int sum = 0;

		for (int i = 0, len = flags.length; i < len; i++) {

			if (i < len - 2) {
				if (flags[i].indexOf("X") != -1) {
					Point p = fn(flags[i + 1]);
					if (p.y == -1) {
						Point p1 = fn(flags[i + 2]);
						scores[i] = 10 + p.x + p1.x;
					} else {
						scores[i] = 10 + p.x + p.y;
					}
				} else if (flags[i].indexOf("/") != -1) {
					Point p = fn(flags[i + 1]);
					scores[i] = 10 + p.x;
				} else {
					Point p = fn(flags[i]);
					scores[i] = p.x + p.y;
				}
			}

			if (i == len - 2) {
				if (flags[i].indexOf("X") != -1) {
					Point p = fn(flags[i + 1]);
					if (flags[i + 1].indexOf("X") == 0) {
						int starIndex = flags[i + 1].indexOf("*");
						Point p1 = fn(flags[i + 1].substring(starIndex + 1, starIndex + 2));
						scores[i] = 10 + p.x + p1.x;
					} else {
						scores[i] = 10 + p.x + p.y;
					}
				} else if (flags[i].indexOf("/") != -1) {
					Point p = fn(flags[i + 1]);
					scores[i] = 10 + p.x;
				} else {
					Point p = fn(flags[i]);
					scores[i] = p.x + p.y;
				}
			}

			if (i == len - 1) {

				if (flags[i].indexOf("*") != flags[i].length() - 1) {
					String[] temp = flags[i].split("\\*");
					Point p = fn(temp[1]);
					if (temp[0].indexOf("X") != -1) {
						scores[i] = 10 + p.x + p.y;
					} else {
						scores[i] = 10 + p.x;
					}
				} else {
					Point p = fn(flags[i]);
					scores[i] = p.x + p.y;
				}
			}

			sum += scores[i];
		}
		;

		return sum;
	}

	public static Point fn(String str) {
		str = str.replace("-", "0");
		Point p1 = new Point();

		if (str.indexOf("X") != -1) {
			Pattern pattern = Pattern.compile("X{2}"); 
			Matcher matcher = pattern.matcher(str);
			if (matcher.find()) {
				p1.x = 10;
				p1.y = 10;
			} else {
				p1.x = 10;
				p1.y = -1;
			}
		} else if (str.indexOf("/") != -1) {
			p1.x = Integer.parseInt(str.substring(0, 1));
			p1.y = 10 - p1.x;
		} else {
			if (str.length() == 2) {
				p1.x = Integer.parseInt(str.substring(0, 1));
				p1.y = Integer.parseInt(str.substring(1, 2));
			} else {
				p1.x = Integer.parseInt(str.substring(0, 1));
				p1.y = 0;
			}

		}

		return p1;
	};

}
