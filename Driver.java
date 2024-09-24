import java.io.File;

public class Driver {
	public static void main(String [] args) {
		System.out.println("Test the blank constructor and evaluate method:");
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));
		System.out.println();
		
		System.out.println("Test the file constructor:");
		File f = new File("input.txt");
		p = new Polynomial(f);
		for (int i = 0; i < p.coef.length; i++) {
			System.out.println(p.coef[i] + ", " + p.expo[i]);
		}
		System.out.println();
		
		System.out.println("Test saveToFile method: check 'ouput.txt'");
		f = new File("output.txt");
		p.saveToFile(f);
		System.out.println();
		
		double [] c1 = {6, 5};
		int [] e1 = {0, 3};
		Polynomial p1 = new Polynomial(c1, e1);
		double [] c2 = {-2, -9};
		int [] e2 = {1, 4};
		Polynomial p2 = new Polynomial(c2, e2);
		
		System.out.println("Test the normal constructor, add method and evaluate method:");
		Polynomial s = p1.add(p2);
		System.out.println("s(0.1) = " + s.evaluate(0.1));
		for (int i = 0; i < s.coef.length; i++) {
			System.out.println(s.coef[i] + ", " + s.expo[i]);
		}
		System.out.println();
		
		System.out.println("Test the normal constructor, multiply method and evaluate method:");		
		Polynomial m = p1.multiply(p2);
		System.out.println("m(0.1) = " + m.evaluate(0.1));
		for (int i = 0; i < m.coef.length; i++) {
			System.out.println(m.coef[i] + ", " + m.expo[i]);
		}
		System.out.println();
		
		System.out.println("Test the hasRoot method:");
		if(s.hasRoot(1))
			System.out.println("1 is a root of s");
		else
			System.out.println("1 is not a root of s");
	}
}