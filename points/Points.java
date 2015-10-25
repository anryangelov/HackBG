package points;

import java.util.Scanner;

public class Points {
	
	private int x;
	private int y;
	private String s;
	private boolean x_incr_right = true;
	private boolean y_incr_down = true;
	
	public Points(int x, int y, String s) {
		this.x = x;
		this.y = y;
		this.s = s;
	}

	private void right() {
		if (x_incr_right) x++;
		else x--;
	}

	private void left(){
		if (x_incr_right) x--;
		else x++;
	}

	private void down(){
		if (y_incr_down) y++;
		else y--;
	}
	
	private void up(){
		if (y_incr_down) y--;
		else y++;
	}
	
	private void reservedAxis() {
		if (x_incr_right) x_incr_right = false;
		else x_incr_right = true;
		if (y_incr_down) y_incr_down = false;
		else y_incr_down = true;
	}
	
	private void processString() {
		char c; 
		for (int i = 0; i < s.length(); i++) {
			c = s.charAt(i);
			if (c == '>') right();
			else if (c == '<') left();
			else if (c == 'v') down();
			else if (c == '^') up();
			else if (c == '~') reservedAxis();
		}
	}
	
	public String getPosition(){
		processString();
		return "(" + x + ", " + y + ")";
	}
	
	
	public static void main(String[] args) {	
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter potision x:");
		int x = scanner.nextInt();
		System.out.println("Enter potision y:");
		int y = scanner.nextInt();
		System.out.println("Enter string:");
		String s = scanner.next();
		Points p = new Points(x, y, s);
		System.out.println("result:\n" + p.getPosition());
	}
	
}
