
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;

public class Polynomial {
	
	double [] coefficients; // how do u make an array with any size in java?
	int [] expo;
	public Polynomial(){
		this.coefficients = new double [1];
		this.expo = new int [1];
		this.coefficients[0] = 0.0;
		this.expo[0] = 0;
	}
	public Polynomial(double [] arr, int [] expo){
		// can we assume the lengths of the parameter arrays are the same? TA said yes
		this.coefficients = new double [arr.length];
		this.expo = new int [expo.length];
		for (int i=0; i < arr.length; i++){
			this.coefficients[i] = arr[i];
			this.expo[i] = expo[i];
		}
	}
	public boolean isNumber(String str){
		try {
			Double.parseDouble(str);
			return true;
		} catch(NumberFormatException e){
			return false;
		}
	}
	public Polynomial(File content){
		try{
			Scanner input = new Scanner(content);
			String str = input.nextLine();
			String [] poly = str.split("(?=-)|\\+|((?=x)|(?<=x))");
			int c1 =0;
			for (int i=0; i < poly.length; i++){
				// enter code to make polynomial from single characters
				if (isNumber(poly[i])){
					c1++;
				}
				System.out.println(poly[i]);
			}
			double [] coeff = new double [c1];
			int [] exponent = new int [c1];
			int idx=0;
			for (int j = 0; j < poly.length -2; j++){
				if (j == 0 && isNumber(poly[j]) && isNumber(poly[j+1])){
					coeff[idx] = Double.parseDouble(poly[j]);
					exponent[idx] = 0;
					idx++;
				}
				else if (isNumber(poly[j]) && isNumber(poly[j+1]) == false){
					coeff[idx] = Double.parseDouble(poly[j]);
					exponent[idx] = Integer.parseInt(poly[j+2]);
					idx++;
				}
				
			}
			if (isNumber(poly[poly.length - 1]) && isNumber(poly[poly.length - 2])){
				coeff[idx] = Double.parseDouble(poly[poly.length - 1]);
				exponent[idx] = 0;
			}
			int c2=0;
			for (int k=0; k < coeff.length; k++){
				if (coeff[k] != 0.0){
					c2++;
				}
				
			}
			this.coefficients = new double [c2];
			this.expo = new int [c2];
			for (int m=0; m < c2; m++){
				this.coefficients[m]= coeff[m];
				this.expo[m] = exponent[m];
				//System.out.println("coeff: " + this.coefficients[m]);
				//System.out.println("expo: " + this.expo[m]);
			}


		} catch (FileNotFoundException e) {
      		System.out.println(" error occurred.");
    	}
		

		
	}
	public Polynomial remove_zero(Polynomial poly){
		int zero_count=0;
		for (int e=0; e < poly.coefficients.length; e++) {
			if (poly.coefficients[e] == 0.0){
				zero_count += 1;
			}
		}
		double [] coeff_zero = new double [poly.coefficients.length - zero_count];
		int [] expo_zero = new int [poly.expo.length - zero_count];
		int x=0;
		for (int f=0; f < poly.coefficients.length; f++) {
			if (poly.coefficients[f] != 0.0 && poly.expo[f] != -1){
				coeff_zero[x] = poly.coefficients[f];
				expo_zero[x] = poly.expo[f];
				x+=1;
			}
		}
		
		poly.coefficients = new double [coeff_zero.length];
		poly.expo = new int [expo_zero.length];
		for (int g=0; g<expo_zero.length; g++){
			
			poly.expo[g] = expo_zero[g];
			poly.coefficients[g] = coeff_zero[g];

		}

		return poly;
	}
	public Polynomial add(Polynomial poly){
		
		if (poly == null || this == null){
			return null;
		}
		// p3 = 6 -2x +5x^5 -> [6, -2, 5] [0, 1, 5]
		//p4 = 5x +2x^2  -5 + -> [5, 2, -5] [1, 2, 0]
		// combined = [1,2,0,5]
		//just want to know how to add all the exponents in an array with no redundancy
		int [] combined = new int [poly.expo.length + this.expo.length];
		int u=0;
		int count_extra=0;
		for (int k=0; k < poly.expo.length; k++){
			combined[k] = poly.expo[k];
			u+=1;
		}
		for (int l=u; l < u + this.expo.length; l++){
			combined[l] = this.expo[l - u];
		}
		// after this, combined should have all exponents from this.expo and poly.expo
		for (int i=0; i < combined.length; i++){
			for (int j=0; j < combined.length; j++){
				if ( i != j && combined[i] == combined[j] && combined[i] != -1){
					combined[j] = -1;
					count_extra +=1;
				}
			}
		}
		int [] unique = new int [combined.length - count_extra];
		int ind = 0;
		//poly.expo = new int [combined.length - count_extra];
		for (int o=0; o < combined.length; o++){
			if (combined[o] != -1){
				//System.out.println(combined[o]);
				unique[ind] = combined[o];
				ind+=1;
			}
		}
		// now the exponents are unique in array unique
		double [] add_coeff = new double [unique.length];
		Polynomial added = new Polynomial( add_coeff, unique);

		for (int a=0; a < unique.length; a++){
			for (int b=0; b < poly.expo.length; b++){
				if (added.expo[a] == poly.expo[b]){
					added.coefficients[a] += poly.coefficients[b];
				}
			}
		}
		for (int c=0; c < unique.length; c++){
			for (int d=0; d < this.expo.length; d++){
				if (added.expo[c] == this.expo[d]){
					added.coefficients[c] += this.coefficients[d];
				}
			}
		}
		added = added.remove_zero(added);
		if (added.coefficients.length == 0){
			added = new Polynomial();
		}
		return added;

		/* V1
		int len = 0;
		if (poly.coefficients.length <= this.coefficients.length){
			len = this.coefficients.length;
			for(int i=0; i < len; i++){
				if (poly.coefficients.length == i){
					break;
				}
				this.coefficients[i] += poly.coefficients[i];
				}
			return this;
			}
		else {
			len = poly.coefficients.length;
			for(int i=0; i < len; i++){
				if (this.coefficients.length == i){
					break;
				}
				poly.coefficients[i] += this.coefficients[i];
			}
			return poly;
		} */
		//System.out.println(len);

	}
	
	public double power_calc(double num, int power){
		double result = 1;
		if(power == 0){
			return result;
		}
		if (power <0){
			return 0;
		}
		for (int i =0; i<power; i++){
			result *= num;
		}
		return result;
	}
	public double evaluate(double x){
		double result=0;
		
		if (this.coefficients.length != 0 && this.coefficients[0] !=0.0) {
			for (int i=0; i < this.coefficients.length; i++){
				result += this.coefficients[i] * this.power_calc(x, this.expo[i]);
			}	
			return result;
		}
		return -1;
	}
	public boolean hasRoot(double num){
		if (evaluate(num) == 0){
			return true;
		}
		return false;
	} 
	public Polynomial multiply(Polynomial poly){
		// to simplify polynomial add do this.add(0.0)

		// have 2 for loops, one for this and one for poly
		// loop over both ( distributive property)
		double [] result_coeff = new double [this.coefficients.length * poly.coefficients.length];
		int [] result_expo = new int [this.expo.length * poly.expo.length]; // note: length of expo and coeeff should be same
		// for the actual multiplication, we need to multiply coeff and exponents
		// need to set custom index 
		int c=0; 
		for (int i=0; i < this.coefficients.length; i++){
			
			for (int k =0; k < poly.coefficients.length; k++){
				result_coeff[k + c] = this.coefficients[i] * poly.coefficients[k];
				result_expo[k + c] = this.expo[i] + poly.expo[k];
				
				//System.out.println("coeff: " + result_coeff[k+c] + " ind" + i + k);
				//System.out.println("expo: " + result_expo[k+c]);
				
			}
			c +=poly.coefficients.length; 
			
		}
		Polynomial result = new Polynomial(result_coeff, result_expo);
		Polynomial zero = new Polynomial();
		result = result.add(zero);
		return result;
	}
	public void saveToFile(String fileName){
		try{
			FileWriter writer = new FileWriter(fileName);
			String temp_expo;
			String temp_coeff;
			for(int i=0; i <  this.expo.length; i++){
				temp_expo = Integer.toString(this.expo[i]);
				temp_coeff = Double.toString(this.coefficients[i]);
				if (this.coefficients[i] > 0 && i >0){
					writer.write("+");
				}
				if (this.expo[i] == 0){
					writer.write(temp_coeff);
				}
				else{
					writer.write(temp_coeff + "x" + temp_expo);
				}
			}
			writer.close();
			return;

		} catch (IOException e) {
      		System.out.println("An error occurred.");
      	}
}}