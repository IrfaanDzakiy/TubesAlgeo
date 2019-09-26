import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

class Matriks{
	double[][] mat;
	int brs,kol;
	Scanner read = new Scanner(System.in);
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

	void bacaFileMatriks(String namaFile){
		//I.S matriks sembarang
		//I.F matriks terdefinisi sesuai dengan file matriks
		if (Files.notExists(Paths.get(namaFile))){
			System.out.println("Maaf, file tidak ditemukan");
		}
		else{
			try{
				int i = 0;int j;
				File file = new File(namaFile);
				Scanner scanBaris = new Scanner(file);
				Matriks temp = new Matriks(100,100);
				while (scanBaris.hasNextLine()){
					String sBaris = scanBaris.nextLine();
					String[] arrString = sBaris.split(" ");
					for (j = 0; j<arrString.length; j++){
						temp.mat[i][j] = Double.parseDouble(arrString[j]);
					}
					i++;
					this.mat = new double[i][arrString.length];
					this.brs = i;
					this.kol = arrString.length;
				}
				for (int k = 0; k<this.brs; k++){
					for (int l = 0;l<this.kol; l++){
						this.mat[k][l] = temp.mat[k][l];
					}
				}
				scanBaris.close();
			}
			catch(FileNotFoundException ex){
				ex.printStackTrace();
			}
		}
	}

	void bacaFileIntrapolasi(String namaFile){
		//I.S matriks sembarang
		//I.F matriks terdefinisi sesuai dengan file matriks
		if (Files.notExists(Paths.get(namaFile))){
			System.out.println("Maaf, file tidak ditemukan");
			return;
		}
		else{
			try{
				int i = 0;int j;
				File file = new File(namaFile);
				Scanner scanBaris = new Scanner(file);
				Matriks temp = new Matriks(100,2);
				while (scanBaris.hasNextLine()){
					String sBaris = scanBaris.nextLine();
					String[] arrString = sBaris.split(" ");
					for (j = 0; j<arrString.length; j++){
						temp.mat[i][j] = Double.parseDouble(arrString[j]);
					}
					i++;
					this.mat = new double[i][i+1];
					this.brs = i;
					this.kol = i+1;
				}
				for (int k = 0; k<this.brs; k++){
					for (int l = 0;l<this.kol-1; l++){
						this.mat[k][l] = Math.pow(temp.mat[k][0], l);
					}
					this.mat[k][this.kol-1] = temp.mat[k][1];
				}
				scanBaris.close();
			}
			catch(FileNotFoundException ex){
				ex.printStackTrace();
			}
		}
	}
	void saveToFile(String input){
		try {
			//Whatever the file path is.
			System.out.print("Masukan nama file :");
            String namaFile = read.nextLine();
            File statText = new File(namaFile);
            FileOutputStream is = new FileOutputStream(statText);
            OutputStreamWriter osw = new OutputStreamWriter(is);    
            Writer w = new BufferedWriter(osw);
            w.write(input);
            w.close();
        } catch (IOException e) {
            System.err.println("Problem writing to the file statsTest.txt");
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

	String sTulisMatriks(){	//Output
		String output = "";
		for (int i=0; i<this.brs; i++){
			for (int j=0; j<this.kol; j++){
				output += String.format("%f ", this.mat[i][j]);
			}
			output += "\n";
		}
		return output;
	}

	String doubleToString(Double input){	//Output
		String output = "";
		output += String.format("%f ", input);
		output += "\n";
		return output;
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

	boolean isKolomNol(int j){
		boolean nol = true;
		for (int i = 0; i<this.brs; i++){
			if (this.mat[i][j] != 0){
				nol = false;
			}
		}
		return nol;
	}

	boolean isBarisNol(int i){
		boolean nol = true;
		for (int j = 0; j<this.kol; j++){
			if (this.mat[i][j] != 0){
				nol = false;
			}
		}
		return nol;
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
		if (firstidx<this.kol){
			double pembagi = this.mat[baris][firstidx];
			for (int j = firstidx; j<this.kol; j++){
				this.mat[baris][j] = this.mat[baris][j] * (1/pembagi);
			}
		}
	}

	void makeNol(int baris){
		//membuat koefisien dibawah baris menjadi nol dan sisanya menyesuaikan
		int firstidx = this.getFirstIdx(baris);	
		if (firstidx<this.kol){	
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
				if (maks <= this.getFirstIdx(j)){
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
				if (maks >= this.getFirstIdx(j)){
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

	Matriks inverseSPL(){
		//menghasilkan matriks variable(x1,x2,x3....xn) dengan menggunakan metode inverse. 
		//prekondisi baris matriks sama dengan kolom dan determinan M tidak sama dengan 0
		Matriks M = new Matriks(this.brs, this.kol-1);
		Matriks MHasil = new Matriks(this.brs, 1);

		for (int i = 0; i<this.brs; i++){
			MHasil.mat[i][0] = this.mat[i][this.kol-1];
			for (int j = 0; j<this.kol-1; j++){
				M.mat[i][j] = this.mat[i][j];
			}
		}
		Matriks in = M.inverse();
		return in.kaliMatriks(MHasil);
	}

	double cramer(int xi){
		//menghasilkan nilai xi dengan menggunakan metode cramer
		//prekondisi baris matriks sama dengan kolom dan determinan M tidak sama dengan 0
		Matriks Mi = new Matriks(this.brs, this.kol-1);
		Matriks M = new Matriks(this.brs, this.kol-1);
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
	// Mendapatkan matriks untuk dicari determinannya sehingga menjadi elemen p,q pada kofaktor matriks;
	// NOTE : belom jadi cofactor ya ini
	Matriks minor(int p, int q){
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
	
	double miniminor(int p, int q){
		double temp = 1;
		for (int i = 0; i < this.brs; i++){
			for (int j = 0; j < this.kol; j++){
				if ((i != p) && (j != q)){
					temp = this.mat[i][j];
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
				if ((this.brs == 2) && (this.kol == 2)){
					cof.mat[p][q] = Math.pow(-1, (p+2+q))*(this.miniminor(p, q));
				}
				if ((this.brs > 2) && (this.kol > 2)) {
					cof.mat[p][q] = Math.pow(-1, (p+2+q))*(this.minor(p, q).determinan());
				}
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
	Matriks kaliMatriks(Matriks B){
		//menghasilkan matriks hasil kali matriks this dan matriks B, pre kondisi kolom matriks (this) = baris matriks B.
		Matriks C = new Matriks(this.brs,B.kol);
		for (int i = 0; i < this.brs; i++) {
            for (int j = 0; j < B.kol; j++) {
                double x = 0;
                for (int k = 0; k < B.brs; k++) {
                    x += this.mat[i][k] * B.mat[k][j];
                }
                C.mat[i][j] = x;
            }
        }
        return C;
	}
	//membuat matriks invers
	Matriks inverse(){
		Matriks in = new Matriks(this.brs, this.kol);
		in = this.adjoint().kaliKons(1/this.determinan());
        return in;		
	}
	double interpol(int n){
		Matriks mutrex = new Matriks(n, n+1);
		int i,j;
		double x,y;
		Scanner read = new Scanner(System.in);
		for ( i = 0; i < n; i++){
			System.out.print("Masukan x " + ": ");
			x = read.nextDouble();
			System.out.print("Masukan y " + ": ");
			y = read.nextDouble();
			for ( j = 0; j<(n); j++){
				mutrex.mat[i][j] = Math.pow(x, j);
			}
			mutrex.mat[i][n] = y;
		}

		mutrex.gaussJordan();
		mutrex.tulisMatriks();
		double f;
		f = read.nextDouble();

		double res;
		res = 0;

		int k;
		for ( k = 0; k < n; k++){
			res += ((mutrex.mat[k][n])*Math.pow(f, k));
		}
		return res;
	}
	double interpolfile(){
		String namaFile;
		Matriks mutrex = new Matriks(this.brs,this.brs);
		Scanner read = new Scanner(System.in);
		System.out.print("Masukan nama file :");
    	namaFile = read.nextLine();
		mutrex.bacaFileIntrapolasi(namaFile);
		mutrex.gaussJordan();
		mutrex.tulisMatriks();
		double f;
		f = read.nextDouble();

		double res;
		res = 0;

		int k;
		for ( k = 0; k < (mutrex.kol-1); k++){
			res += ((mutrex.mat[k][mutrex.kol-1])*Math.pow(f, k));
		}
		return res;
	}

	void clearScreen() {  
		System.out.print("\033[H\033[2J");  
		System.out.flush();  
	}

	void tulisMenu(){
		System.out.print("MENU"); System.out.println();
        System.out.print("1. Sistem Persamaan Linier"); System.out.println();
        System.out.print("2. Determinan"); System.out.println();
        System.out.print("3. Matriks Balikan"); System.out.println();
        System.out.print("4. Matriks Kofaktor"); System.out.println();
        System.out.print("5. Adjoin"); System.out.println();
        System.out.print("6. Interpolasi Polinom"); System.out.println();
        System.out.print("7. Keluar"); System.out.println();
	}
	void tulisSubMenu(){
        System.out.print("1. Metode eliminasi Gauss"); System.out.println();
        System.out.print("2. Metode eliminasi Gauss-Jordan"); System.out.println();
        System.out.print("3. Metode Matriks Balikan"); System.out.println();
        System.out.print("4. Kaidah Cramer"); System.out.println();
	}
	int cetakMenu(){
		this.clearScreen();
		int pilihan;
		this.tulisMenu();
		Scanner read = new Scanner(System.in);
		System.out.print("Masukin pilihan kamu ya :");
		pilihan = read.nextInt();
		return pilihan;
	}
	void solusiSPLGaussJordan(){
		int batas, i, j, k, l;
		this.gaussJordan();
		this.sortMatriks();
		Matriks hasil = new Matriks(this.brs,this.kol);
		i=1;
		while(i<this.brs){
			if (this.getFirstIdx(i)>this.kol-1){
				batas = this.kol-1;
			}
			else{
				batas = this.getFirstIdx(i);
			}
			for(l = this.getFirstIdx(i-1)+1; l<batas; l++){
				for(k = 0; k< this.brs; k++){
					hasil.mat[k][l+1] = this.mat[k][l];
				}
			}
			hasil.mat[i-1][0]=this.mat[i-1][this.kol-1];
			hasil.mat[i][0]=this.mat[i][this.kol-1];
			i++;
		}
		for (l=this.getFirstIdx(this.brs-1)+1; l<this.kol-1; l++){
			for(k=0; k<this.brs; k++){
				hasil.mat[k][l+1] = this.mat[k][l];
			}
		}
		System.out.println();

		String output = "";
		if ((!hasil.isBarisNol(hasil.brs-1)) && (hasil.getFirstIdx(hasil.brs-1)>=hasil.kol-1)){
			output += String.format("SPL tidak memiliki solusi\n");
		}
		else{
			boolean tunggal=true;
			for(j=1;j<hasil.kol;j++){
				if (!hasil.isKolomNol(j)){
					tunggal = false;
				}
			}
			if (tunggal){
				output += String.format("SPL memiliki solusi tunggal/unik\n");
			}
			else{
				output += String.format("SPL memiliki solusi banyak\n");
			}

			for (i=0; i<hasil.brs; i++){
				if (this.getFirstIdx(i)<this.kol-1){
					output += String.format("x%d = ", this.getFirstIdx(i)+1);
					output += String.format("%.2f ", hasil.mat[i][0]);
				}
				for(j=1; j<hasil.kol; j++){
					if ((hasil.mat[i][j]!=0) && (j<hasil.kol-1)){
						output += String.format("- (%.2fa%d) ", hasil.mat[i][j], j);
					}
					else if((hasil.mat[i][j]!=0) && (j==hasil.kol-1)){
						output += String.format("- (%.2fa%d)\t", hasil.mat[i][j], j);
					}
				}
			}
			output += "\n";
			for(j=1; j<hasil.kol; j++){
				if (!hasil.isKolomNol(j)){
					output += String.format("x%d = a%d", j,j);
					output += "\n";
				}
			}
		}
		System.out.println(output);
	}
	void solusiSPLGauss(){
		this.gauss();
		Matriks ada = new Matriks(this.brs, this.kol);
		int count = 0;
		int masalah = 2;

		if (this.brs < this.kol-1){
			count += 1;
		}

		for ( int k = 0; k < (this.brs); k++){
			int found = 0;
			for ( int l = 0; l < (this.kol-2); l++){
				if ((this.mat[k][l] != 0) && (k != l)){
					count += 1;
					found = 1;
					break;
				}
				if ((this.mat[k][l] != 0) && (k == l)){
					found = 1;
					break;
				}
				else {
					continue;
				}
			}
			if ((found == 0) && ((k > (this.kol-1) || (this.brs < this.kol-1)))) {
				count += 1;
				if (this.mat[k][(this.kol-1)] != 0){
					masalah = 1;
				}
			}
		}
		if (count != 0){
			if (masalah == 1){
				System.out.print("Solusi tidak ada"); System.out.println();
			}
			else {
				System.out.print("Solusi berupa parametrik:  ");
				this.solusiSPLGaussJordan();
			}
		}
		else {
			System.out.print("Solusi :  "); System.out.println();
			int k,l;
			int h = 1;
			for ( k = this.kol-2; k >= 0; k--){
				ada.mat[k][1] = this.mat[k][this.kol-1];
				int bacod = 0;
				for ( l = this.kol-2; l > k; l--){
					bacod += ada.mat[l][1]*this.mat[k][l];
				}
				ada.mat[k][1] -= bacod;
				ada.mat[k][1] /= this.mat[k][k];
				System.out.print("x"+(h)+" = "+ada.mat[k][1]); System.out.println();
				h++;
			}
		}
	}
	void solusiSPLCramer(){
		double hasil;
		for (int i = 0; i < this.brs; i++){
			hasil = this.cramer(i);
			System.out.print("x"+(i+1)+" = "+hasil);  System.out.println();
		}
	}

	void solusiSPLInvers(){
		Matriks SolusiInvers = new Matriks(this.brs, 1);
		SolusiInvers = this.inverseSPL();
		for (int i = 0; i < this.brs; i++){
			System.out.print("x"+(i+1)+" = "+SolusiInvers.mat[i][0]); System.out.println();
		}
	}

}