import java.util.Scanner;

public class GaussJordan {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		double[][] A = new double[N][N];
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++)
				A[i][j] = in.nextDouble();
		
		double[] b = new double[N];
		for(int i = 0; i < N; i++)
			b[i] = in.nextDouble();
		
		double[] x = gaussianElimination(N, A, b);
		
		for(double d : x)
			System.out.println(d);
		
		in.close();
	}
	
	public static double[] gaussianElimination(int N, double[][] A, double[] b) {
		double[] x = new double[N]; //Note: A and b are both changed!
		
		for(int i = 0; i < N; i++)
		{
			int row = i;
			for(int j = i+1; j < N; j++)
				if(Math.abs(A[j][j]) > Math.abs(A[row][row]))
					row = j;
			
			double[] temp = A[i];
			A[i] = A[row];
			A[row] = temp;
			
			double tempB = b[i];
			b[i] = b[row];
			b[row] = tempB;
			
			double div = A[i][i];
			for(int j = i; j < N; j++)
				A[i][j] /= div;
			b[i] /= div;
			
			for(int j = i+1; j < N; j++)
			{
				div = A[j][i];
				for(int k = i; k < N; k++)
					A[j][k] -= A[i][k] * div;
				b[j] -= b[i] * div;
			}
			
		}
		
		for(int i = N-1; i >= 0; i--)
		{
			x[i] = b[i];
			for(int j = i-1; j >= 0; j--)
				b[j] -= A[j][i] * b[i];
		}
		
		return x;
	}
	
	public static void print(double[][] A) {
		for(int i = 0; i < A.length; i++)
		{
			for(int j = 0; j < A[i].length; j++)
				System.out.printf("%.2f ", A[i][j]);
			System.out.println();
		}
		System.out.println();
	}

}
/*
2
1 3
3 -1
11
3

2
3

3
1 2 -1
2 2 2
1 -1 2
2
12
5

1
2
3

5
5 8 0 -1 2
1 1 1 1 1
-1 2 -3 -4 5
139 0 0 12 -1
6 6 -1 2 4
10
0
-100
3
100

-3.3369649531062695
11.600909667865462
-31.782314364290258
37.71973062548481
-14.20136097595375
*/