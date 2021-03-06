package net.dp.singleton.classic;

//NOTE: This is not thread safe!

public class Singleton {
	private static Singleton uniqueInstance;

	// other useful instance variables here

	private Singleton() {
	}
	
	public Singleton(int x) {
	}
	public static Singleton getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new Singleton();
		}
		return uniqueInstance;
	}

	// other useful methods here
}
