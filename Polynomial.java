import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Polynomial {
	double[] coef;
	int[] expo;
	
	public Polynomial() {
	}
	
	public Polynomial(double c[], int e[]) {
		int l = c.length;
		this.coef = new double[l];
		this.expo = new int[l];
		for (int i = 0; i < l; i++) {
			this.coef[i] = c[i];
			this.expo[i] = e[i];
		}
	}
	
	public Polynomial(File f) {
		try (Scanner s = new Scanner(f)) {
			if (s.hasNextLine()) {
				String line = s.nextLine();
				String[] parts = line.split("[+-]"), temp;
				int l = parts.length, j = 0;

				this.coef = new double[l];
				this.expo = new int[l];
				for (int i = 0; i < l; i++) {
					if (parts[i].indexOf('x') == -1) {
						this.coef[i] = Double.parseDouble(parts[i]);
					} else {
						temp = parts[i].split("x");
						this.coef[i] = Double.parseDouble(temp[0]);
						this.expo[i] = Integer.parseInt(temp[1]);
					}
					if (line.charAt(j) == '-') {
						this.coef[i] *= -1;
					}
					j += parts[i].length();
				}
			} 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public void saveToFile(File f) {
		String line = "";
		for (int i = 0; i < this.coef.length; i++) {
			if (this.coef[i] > 0) {
				line += "+";
			}
			if (this.expo[i] == 0) {
				line += String.valueOf(this.coef[i]).replaceAll("\\.?0*$", "");
			} else {
				line += String.valueOf(this.coef[i]).replaceAll("\\.?0*$", "") + "x" + String.valueOf(this.expo[i]);
			}
		}
		line = line.substring(1, line.length());
		try (BufferedWriter w = new BufferedWriter(new FileWriter(f))) {
			w.write(line);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Polynomial add(Polynomial p) {
		Polynomial ans;
		int l_old = this.coef.length, l_add = p.coef.length;
		double[] c = new double[l_old + l_add], ans_c;
		int[] e = new int[l_old + l_add], ans_e;
		int i = 0, j = 0, k = 0;
		
		while (i < l_old && j < l_add) {
			if (this.expo[i] == p.expo[j]) {
				c[k] = this.coef[i] + p.coef[j];
				e[k] = this.expo[i];
				i++;
				j++;
			} else if (this.expo[i] < p.expo[j]) {
				c[k] = this.coef[i];
				e[k] = this.expo[i];
				i++;
			} else {
				c[k] = p.coef[j];
				e[k] = p.expo[j];
				j++;
			}
			k++;
		}
		while (i < l_old) {
			c[k] = this.coef[i];
			e[k] = this.expo[i];
			i++;
			k++;
		}
		while (j < l_add) {
			c[k] = p.coef[j];
			e[k] = p.expo[j];
			j++;
			k++;
		}
		ans_c = new double[k];
		ans_e = new int[k];
		for (i = 0; i < k; i++) {
			ans_c[i] = c[i];
			ans_e[i] = e[i];
		}
		ans = new Polynomial(ans_c, ans_e);
		
		return ans;
	}
	
	public Polynomial multiply(Polynomial p) {
		Polynomial ans;
		int l_old = this.coef.length, l_mul = p.coef.length;
		int max_e = this.expo[l_old - 1] + p.expo[l_mul - 1];
		double[]temp = new double[max_e + 1];
		double[] ans_c;
		int[] ans_e;
		int i , j, k = 0, cnt = 0;
		
		for (i = 0; i < l_old; i++) {
			for (j = 0; j < l_mul; j++) {
				temp[this.expo[i] + p.expo[j]] += this.coef[i] * p.coef[j];
			}
		}
		for (i = 0; i < max_e + 1; i++) {
			if (temp[i] != 0) {
				cnt++;
			}
		}
		ans_c = new double[cnt];
		ans_e = new int[cnt];
		for (i = 0; i < max_e + 1; i++) {
			if (temp[i] != 0) {
				ans_c[k] = temp[i];
				ans_e[k] = i;
				k++;
			}
		}
		ans = new Polynomial(ans_c, ans_e);
		
		return ans;
	}
	
	public double evaluate(double x) {
		double sum = 0;
		if (this.coef == null) {
			return sum;
		}
		for (int i = 0; i < this.coef.length; i++) {
			sum += this.coef[i] * Math.pow(x, this.expo[i]);
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