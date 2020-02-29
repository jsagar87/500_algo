public class TestJNI {   
	public native void greetings();
	public native void add(int a,int b);
	static {      
		System.loadLibrary("greet");
	}

	public static void main(String args[]) {
			new TestJNI().greetings();
	}
}