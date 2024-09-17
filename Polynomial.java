public class Polynomial {
	double[] coef = new double[10];
	
	public Polynomial() {
	}
	
	public Polynomial(double arr[]) {
		int l = arr.length;
		for (int i = 0; i < l; i++) {
			this.coef[i] = arr[i];
		}
	}
	
	public Polynomial add(Polynomial p) {
		double[] c = new double[10];
		Polynomial ans;
		for (int i = 0; i < 10; i++) {
			c[i] = this.coef[i] + p.coef[i];
		}
		ans = new Polynomial(c);
		return ans;
	}
	
	public double evaluate(double x) {
		double sum = 0;
		for (int i = 0; i < 10; i++) {
			sum += this.coef[i] * Math.pow(x, i);
		}
		return sum;
	}
	
	public boolean hasRoot(double x) {
		if (this.evaluate(x) == 0) {
			return true;
		}
		return false;
	}
}