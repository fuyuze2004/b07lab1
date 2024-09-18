public class Polynomial {
	double[] coef;
	
	public Polynomial() {
		this.coef = new double[1];
	}
	
	public Polynomial(double arr[]) {
		int l = arr.length;
		this.coef = new double[l];
		for (int i = 0; i < l; i++) {
			this.coef[i] = arr[i];
		}
	}
	
	public Polynomial add(Polynomial p) {
		Polynomial ans;
		int l_old = this.coef.length, l_add = p.coef.length;
		if (l_old >= l_add) {
			ans = new Polynomial(this.coef);
			for (int i = 0; i < l_add; i++) {
				ans.coef[i] += p.coef[i];
			}
		} else {
			ans = new Polynomial(p.coef);
			for (int i = 0; i < l_old; i++) {
				ans.coef[i] += this.coef[i];
			}
		}
		
		return ans;
	}
	
	public double evaluate(double x) {
		double sum = 0;
		for (int i = 0; i < this.coef.length; i++) {
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