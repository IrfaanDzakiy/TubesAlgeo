import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

class Matriks{
	double[][] mat;
	int brs,kol;

	Matriks(int baris, int kolom){		//Konstruktor
		mat = new double[baris][kolom];
		this.brs = baris;
		this.kol = kolom;
	}

	void bacaSPL(){		//Input, beda beda tergantung persoalan
		int i, j;
		Scanner read = new Scanner(System.in);

		for ( i = 0; i<this.brs; i++){
            for ( j = 0; j<(this.kol-1); j++){
                System.out.print("Masukan a" + (i+1) + (j + 1) + ": ");
                this.mat[i][j] = read.nextDouble();
            }
            System.out.print("Masukan b" + (i+1) + ": ");
            this.mat[i][this.kol-1] = read.nextDouble();
        }
	}

	void tulisMatriks(){	//Output
		for (int i=0; i<this.brs; i++){
			for (int j=0; j<this.kol; j++){
				System.out.print(this.mat[i][j] + " ");
			}
			System.out.println();
		}
	}

	void tukarBaris(int baris1, int baris2){
		//menukar baris (baris1) dengan baris (baris2)
		double temp;
		for (int j = 0; j<this.kol; j++){
			temp = this.mat[baris1][j];
			this.mat[baris1][j] = this.mat[baris2][j];
			this.mat[baris2][j] = temp;
		}
	}

	void tukarKolom(int kolom1, int kolom2){
		//menukar kolom pada matriks augmented untuk metode cramer
		double temp;
		for (int i = 0; i<this.brs; i++){
			temp = this.mat[i][kolom1];
			this.mat[i][kolom1] = this.mat[i][kolom2];
			this.mat[i][kolom2] = temp;
		}
	}

	int getFirstIdx(int baris){
		//mendapatkan indeks pertama pada baris yang bukan nol, jika engga ketemu return kol.
		//baris dimulai dari 0 - (brs-1)
		boolean found = false;
		int j = 0;
		while (!found && (j<this.kol)){
			if (this.mat[baris][j] != 0){
				found = true;
			}
			else{
				j++;
			}
		}
		return j;
	}

	void makeSatu(int baris){
		//membuat koef pertama menjadi satu dan koefisien selanjutnya menyesuaikan;
		int firstidx = this.getFirstIdx(baris);
		if (firstidx<this.brs){
			double pembagi = this.mat[baris][firstidx];
			for (int j = firstidx; j<this.kol; j++){
				this.mat[baris][j] = this.mat[baris][j] / pembagi;
			}
		}
	}

	void makeNol(int baris){
		//membuat koefisien dibawah baris menjadi nol dan sisanya menyesuaikan
		int firstidx = this.getFirstIdx(baris);	
		if (firstidx<this.brs){	
			for (int i = baris+1; i<this.brs; i++){
				while (this.mat[i][firstidx] != 0){
					double faktor = this.mat[i][firstidx] / this.mat[baris][firstidx];
					for (int j = firstidx; j<this.kol; j++){
						this.mat[i][j] -=  this.mat[baris][j] * faktor;
					}
				}
			}
		}
	}

	void sortMatriks(){
		//bubble sort matriks
		////baris dimulai dari 0 - (brs-1)
		int maks,idxmaks;
		for (int i = 0; i<this.brs; i++){
			idxmaks = this.brs-1;
			maks = this.getFirstIdx(idxmaks);
			for (int j = this.brs-2; j>-1+i; j--){
				if (maks < this.getFirstIdx(j)){
					this.tukarBaris(idxmaks,j);
					idxmaks = j;
					maks = this.getFirstIdx(idxmaks);
				}
			}
		}
	}

	void rSortMatriks(){
		//reverse bubble sort matriks
		////baris dimulai dari 0 - (brs-1)
		int maks,idxmaks;
		for (int i = 0; i<this.brs; i++){
			idxmaks = this.brs-1;
			maks = this.getFirstIdx(idxmaks);
			for (int j = this.brs-2; j>-1+i; j--){
				if (maks > this.getFirstIdx(j)){
					this.tukarBaris(idxmaks,j);
					idxmaks = j;
					maks = this.getFirstIdx(idxmaks);
				}
			}
		}
	}

	void gauss(){
		//mengubah matriks kedalam bentuk gauss
		for (int i = 0; i<this.brs; i++ ){
			this.makeNol(i);
		}
	}

	void gaussJordan(){
		//mengubah matriks kedalam bentuk gauss jordan
		this.gauss();
		for (int i = 0; i<this.brs; i++){
			this.makeSatu(i);
		}
		this.rSortMatriks();
		this.gauss();
		this.sortMatriks();
	}

	double cramer(int xi){
		//menghasilkan nilai xi dengan menggunakan metode cramer
		
		Matriks Mi = new Matriks(this.brs, this.kol-1);
		Matriks M = new Matriks(this.brs, this.kol-1);
		if (M.determinan() != 0){
			for (int i = 0; i<this.brs; i++){
				for (int j = 0; j<this.kol-1; j++){
					M.mat[i][j] = this.mat[i][j];
				}
			}
			this.tukarKolom(xi,this.kol-1);
			for (int i = 0; i<this.brs; i++){
				for (int j = 0; j<this.kol-1; j++){
					Mi.mat[i][j] = this.mat[i][j];
				}
			}
			this.tukarKolom(this.kol-1,xi);
			System.out.println(M.determinan());
			return (Mi.determinan()/M.determinan());
		}
		else{
			System.out.println("Maaf, determinan dari SPL adalah 0 sehingga tidak bisa menggunakan metode cramer. Silakan menggunakan metode lain\n");
			return 0;
		}
	}
	// Mendapatkan matriks untuk dicari determinannya sehingga menjadi elemen p,q pada kofaktor matriks;
	// NOTE : belom jadi cofactor ya ini
	Matriks getCofactor(int p, int q){
		Matriks temp =  new Matriks(this.brs - 1, this.kol - 1);
		int m = 0;
		int n = 0;
		for (int i = 0; i < this.brs; i++){
			for (int j = 0; j < this.kol; j++){
				if ((i != p) && (j != q)){
					temp.mat[m][n] = this.mat[i][j];
					n++;
					if ( n == this.kol - 1){
						n =  0;
						m++;
					}
				}
			}
		}
		return temp;
	}
				
						
					
	// Mendapatkan matriks transpose dari suatu matriks.
	Matriks transpose(){
		Matriks temp = new Matriks(this.kol, this.brs);
		for (int i = 0; i < this.brs; i++){
			for (int j = 0; j < this.kol; j++){
				temp.mat[j][i] = this.mat[i][j];
			}
		}
		return temp;
	}

	double determinan (){
		double pos = 0;
		double neg = 0;
		if ((this.brs == 2) && (this.kol == 2)){
			double a = this.mat[0][0];
			double b = this.mat[0][1];
			double c = this.mat[1][0];
			double d = this.mat[1][1];
			return ((a*d)-(b*c));
		}

		else {
			for (int j = 0;j<this.kol;j++){
				double x = 1;
				for (int i = 0;i<this.brs;i++){
					if (i+j>=this.kol){
						x *= this.mat[i][i+j-(this.kol)];
					}
					else{
					x *= this.mat[i][i+j];
					}
				}
				pos += x;
			}
			for (int j = this.kol - 1;j>-1;j--){
				double y = 1;
				for (int i = 0;i<this.brs;i++){
					if (j-i<=-1){
						y *= this.mat[i][j-i+this.kol];
					}
					else{
						y *= this.mat[i][j-i];
					}
				}
				neg += y;
			}
			return pos - neg;
		}
	}
	
	//Mendapatkan Matriks kofaktor
	Matriks cofactor(){
		Matriks cof = new Matriks(this.brs, this.kol);
		for (int p = 0; p < this.brs; p++){
			for (int q = 0; q < this.kol; q++){
				cof.mat[p][q] = Math.pow(-1, (p + q))*(this.getCofactor(p, q).determinan());
			}
		}
		return cof;
	}
	Matriks adjoint(){
		return this.cofactor().transpose();
	}

	Matriks kaliKons(double X){
		Matriks x = new Matriks(this.brs,this.kol);
		for (int p = 0; p < this.brs; p++){
			for (int q = 0; q < this.kol; q++){
				x.mat[p][q] = X * this.mat[p][q];
			}
		}
		return x;
	}


	//membuat matriks invers
	Matriks inverse(){
		
			Matriks in = new Matriks(this.brs, this.kol);
			in = this.adjoint().kaliKons(1/this.determinan());
	        return in;
			
	}

	double interpol(){
		int n;

		Scanner read = new Scanner(System.in);
		System.out.print("Masukan n :");
		n = read.nextInt();

		Matriks mutrex = new Matriks(this.brs, (this.kol+1));
		int i,j;
		double x,y;
		Scanner read = new Scanner(System.in);
		for ( i = 0; i < n+1; i++){
			System.out.print("Masukan x " + ": ");
			x = read.nextDouble();
			System.out.print("Masukan y " + ": ");
			y = read.nextDouble();
			for ( j = 0; j<(n+2); j++){
				mutrex.mat[i][j] = Math.pow(x, j);
			}
			mutrex.mat[i][n+2] = y;
		}
		mutrex.gaussJordan();

		double f;
		Scanner read = new Scanner(System.in);
		f = read.nextDouble();

		double res;
		res = 0;

		int k;
		for ( k = 0; k < n+1; k++){
			res += ((mutrex.mat[k][n+2])*Math.pow(f, k));
		}
		return res;
	}
}