package src.p3;

import src.Main;
import src.p1.A;

public class C {
	void callMFromC() {
		new A();
		try {
			Main.m2();
		} catch (Exception ignored) {
		}
	}
}
