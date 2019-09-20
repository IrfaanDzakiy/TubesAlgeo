import java.util.Scanner;

class DriverMatriks{
	public static void main(String[] args){
		int m,n;
		Scanner read = new Scanner(System.in);

		System.out.print("Masukan m :");
		m = read.nextInt();
		System.out.print("Masukan n :");
		n = read.nextInt();

		Matriks M = new Matriks(m,n+1);
		M.bacaSPL();
		M.tulisMatriks();
	}
}