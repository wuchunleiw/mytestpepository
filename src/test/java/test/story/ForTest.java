package test.story;

public class ForTest {
	public static void main(String[] args) {
		for(int i=1;;i++) {
			if(i<50&&i%25==0) {
				System.out.println("ok!!!");
				break;
			}
			System.out.println(i);
		}
		
		
		System.out.println();
		
		
	}

}
