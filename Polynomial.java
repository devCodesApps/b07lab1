public class Polynomial {
	
	double [] coefficients; // how do u make an array with any size in java?
	public Polynomial(){
		this.coefficients = new double [1];
		this.coefficients[0] = 0.0;
	}
	public Polynomial(double [] arr){
		this.coefficients = new double [arr.length];
		for (int i=0; i < arr.length; i++){
			this.coefficients[i] = arr[i];
		}
	}
	public Polynomial add(Polynomial poly){
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
		}
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
		for (int i=0; i < this.coefficients.length; i++){
			result += this.coefficients[i] * power_calc(x, i);
		}
		return result;
	}
	public boolean hasRoot(double num){
		if (evaluate(num) == 0){
			return true;
		}
		return false;
	}
}