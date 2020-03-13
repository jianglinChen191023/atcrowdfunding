package junit.test;

public class Test01 {
	
	public static void main(String[] args) {
		
		A a = new B();
		
		//多态看对象,重载看类型
		test(a);
		//aaaaaa
		
	}
	
	public static void test(A a) {
		System.out.println("aaaaaa");
		
	}
	
	public static void test(B b) {
		System.out.println("bbbbbb");
		
	}
	
}
class A {
	
}
class B extends A {
	
}