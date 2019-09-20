import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

class Matriks{
	float[][] mat;
	int brs,kol;

	Matriks(int baris, int kolom){		//Konstruktor
		mat = new float[baris][kolom];
		this.brs = baris;
		this.kol = kolom;
	}

	void bacaSPL(){		//Input, beda beda tergantung persoalan
		int i, j;
		Scanner read = new Scanner(System.in);

		for ( i = 0; i<this.brs; i++){
            for ( j = 0; j<(this.kol-1); j++){
                System.out.print("Masukan a" + (i+1) + (j + 1) + ": ");
                this.mat[i][j] = read.nextFloat();
            }
            System.out.print("Masukan b" + (i+1) + ": ");
            this.mat[i][this.kol-1] = read.nextFloat();
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
}
