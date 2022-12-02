package dev.moralestorres.util;

public class Matrix {
	private double[][] matrix;
	private int rows, cols;
	public Matrix(int rows, int cols){
		this.matrix = new double[rows][cols];
		this.rows = rows;
		this.cols = cols;
	}
	public Matrix(double[][] matrix){
		this.setMatrix(matrix);
	}
	public Matrix(double[] matrix){
		this.matrix = new double[matrix.length][1];
		this.rows = matrix.length;
		this.cols = 1;
		for(int i=0;i<rows;++i)
			this.matrix[i][0] = matrix[i];
	}
	public void setMatrix(double[][] matrix){
		this.matrix = new double[matrix.length][matrix[0].length];
		this.rows = matrix.length;
		this.cols = matrix[0].length;
		for(int i=0;i<rows;++i)
			for(int j=0;j<cols;++j)
				this.matrix[i][j] = matrix[i][j];
	}
	public double getValue(int i, int j){
		return this.matrix[i][j];
	}
	public void setValue(int i, int j, double val){
		this.matrix[i][j] = val;
	}
	public Matrix transpose(){
		Matrix result = new Matrix(this.cols, this.rows);
		for(int i=0;i<this.rows;++i)
			for(int j=0;j<this.cols;++j)
				result.matrix[j][i] = this.matrix[i][j];
		return result;
	}
	public double determinant(){
		double result = 0;
		if(this.matrix.length == 1)
			return this.matrix[0][0];
		if(this.matrix.length == 2)
			return this.matrix[0][0]*this.matrix[1][1] - this.matrix[0][1]*this.matrix[1][0];
		for(int i=0; i<this.cols; i++){
			Matrix temp = new Matrix(this.rows - 1, this.cols - 1);
			for(int j=1; j<this.rows; j++){
				for(int k=0; k<this.cols; k++){
					if(k < i)
						temp.matrix[j - 1][k] = this.matrix[j][k];
					else if(k > i)
						temp.matrix[j - 1][k - 1] = this.matrix[j][k];
				}
			}
			result += this.matrix[0][i] * Math.pow(-1, (int) i) * temp.determinant();
		}
		return result;
	}
	public Matrix multiply(Matrix that){
        if(this.cols != that.rows)
        	throw new RuntimeException("Illegal dimensions.");
        Matrix res = new Matrix(this.rows, that.cols);
        for(int i = 0; i < res.rows; i++)
            for(int j = 0; j < res.cols; j++)
                for(int k = 0; k < this.cols; k++)
                    res.matrix[i][j] += (this.matrix[i][k] * that.matrix[k][j]);
        return res;
	}
	public static Matrix identity(int n){
        Matrix I = new Matrix(n, n);
        for(int i = 0; i < n; i++)
            I.matrix[i][i] = 1;
        return I;
    }
    public Matrix invert(){
        int n = this.rows;
        double x[][] = new double[n][n];
        double b[][] = new double[n][n];
        int index[] = new int[n];
        for(int i=0; i<n; ++i) 
        	b[i][i] = 1;
        this.gaussian(index);
        for(int i=0; i<n-1; ++i)
        	for(int j=i+1; j<n; ++j)
        		for(int k=0; k<n; ++k)
        			b[index[j]][k] -= this.matrix[index[j]][i]*b[index[i]][k];
        for(int i=0; i<n; ++i){
        	x[n-1][i] = b[index[n-1]][i]/this.matrix[index[n-1]][n-1];
        	for(int j=n-2; j>=0; --j){
        		x[j][i] = b[index[j]][i];
        		for(int k=j+1; k<n; ++k)
        			x[j][i] -= this.matrix[index[j]][k]*x[k][i];
                x[j][i] /= this.matrix[index[j]][j];
            }
        }
        return new Matrix(x);
    }
    private void gaussian(int index[]){
        int n = index.length;
        double c[] = new double[n];
        for(int i=0; i<n; ++i) 
        	index[i] = i;
        for(int i=0; i<n; ++i){
        	double c1 = 0;
        	for(int j=0; j<n; ++j){
                double c0 = Math.abs(this.matrix[i][j]);
                if(c0 > c1)
                	c1 = c0;
            }
            c[i] = c1;
        }
        int k = 0;
        for(int j=0; j<n-1; ++j){
            double pi1 = 0;
            for(int i=j; i<n; ++i){
                double pi0 = Math.abs(this.matrix[index[i]][j]);
                pi0 /= c[index[i]];
                if(pi0 > pi1){
                	pi1 = pi0;
                    k = i;
                }
            }
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for(int i=j+1; i<n; ++i){
            	double pj = this.matrix[index[i]][j]/this.matrix[index[j]][j];
            	this.matrix[index[i]][j] = pj;
            	for(int l=j+1; l<n; ++l)
            		this.matrix[index[i]][l] -= pj*this.matrix[index[j]][l];
            }
        }
    }
    public double[][] toArray(){
    	return this.matrix.clone();
    }
    public void size(){
    	System.out.printf("%d x %d\n", this.rows, this.cols);
    }
}