import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Driver {
	public static void main(String [] args) {
		/*Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));
		double [] c1 = {6,0,0,5};
		Polynomial p1 = new Polynomial(c1);
		double [] c2 = {0,-2,0,0,-9};
		Polynomial p2 = new Polynomial(c2);
		Polynomial s = p1.add(p2);
		//for (int i=0; i < s.coefficients.length; i ++){
		//	System.out.println(s.coefficients[i]);
		//}
		System.out.println(s.power_calc(2,3));
		System.out.println("s(0.1) = " + s.evaluate(0.1));
		if(s.hasRoot(1))
			System.out.println("1 is a root of s");
		else
			System.out.println("1 is not a root of s");
			*/
		double [] c3 = {6.0, -2.0, 5.0, -3.0};
		int []  e3 = {0,1, 5, 2};
		// p3 = 6 -2x -3x^2 + 5x^5 
		double [] c4 = {2.0, 2.0,2.0,-5.0};
		int [] e4 = {1, 1,2,0};
		// p4 = -5 + 2x + 2x^2 
		double [] c6 = {0.0};
		int [] e6 = {0};
		Polynomial p6 = new Polynomial(c6, e6);
		Polynomial p3 = new Polynomial(c3, e3);
		Polynomial p4 = new Polynomial(c4, e4);
		Polynomial p5 = p4.add(p6);
		
		for (int i=0; i < p5.expo.length; i++){
			//System.out.println("exp: " + p5.expo[i]);
			//System.out.println(p5.coefficients[i]);
			System.out.println(p5.coefficients[i] +"x^" +p5.expo[i]);
		}
		// add() testing done
	
		System.out.println(p3.evaluate(1));

		System.out.println(p4.evaluate(2) + "answer");
		// 
		
		double [] c7 = { 3, 2, 8,7};
		int []  e7 = { 3,4,1, 7};
		// p7 = 2x + 3
		//p8 = x + x^2
		double [] c8 = {1, 3, 2};
		int [] e8 = {1,2, 4};
		Polynomial p7 = new Polynomial(c7, e7);
		Polynomial p8 = new Polynomial(c8, e8);
		Polynomial p99 = p8.multiply(p7);
		for (int i=0; i < p99.expo.length; i++){
			//System.out.println("exp: " + p99.expo[i]);
			//System.out.println(p99.coefficients[i]);
			System.out.println(p99.coefficients[i] +"x^" +p99.expo[i]);
		}
		System.out.println("now onto file testing");
		File f1 = new File("p11.txt");
		Polynomial p11 = new Polynomial(f1);


		System.out.println("now onto file saving");

		p11.saveToFile("test11.txt");
	}
}