import java.util.Scanner;

class DriverMatriks{
	public static void main(String[] args){
		int n;
		double hasil;
		Scanner read = new Scanner(System.in);
		System.out.print("Masukan n :");
		n = read.nextInt();

		Matriks M = new Matriks(n+1,n+2);
		hasil = M.interpol(n);
		System.out.print(hasil);
	}
}