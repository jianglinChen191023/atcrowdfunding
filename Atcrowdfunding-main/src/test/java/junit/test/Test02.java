package junit.test;

public class Test02 {

	public static void main(String[] args) {
		byte b = 10;
		test(b);
		//byte 1字节 
		//short 2字节
		//int 4字节
		//long 8字节
		//不能char
		
	}
	
	public static void test(byte b) {
		System.out.println("bbbb");
		
	}
	
	public static void test(short s) {
		System.out.println("ssss");
		
	}
	
	public static void test(char c) {
		System.out.println("cccc");
	}
	
	
	public static void test(int i) {
		System.out.println("iiii");
		
	}

	
}
