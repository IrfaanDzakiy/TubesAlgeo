import java.lang.reflect.Array;
import java.util.Scanner;

class PronEn{
	public static void main(String[] args){
        System.out.print("Selamat Datang di Indomaret, Selamat Berbelanja!");
        System.out.println();
        while (1+1 == 2) {
            int pilihanku;
            Scanner read = new Scanner(System.in);
            Matriks M = new Matriks(1,1);
            
            pilihanku = M.cetakMenu();
            
            if (pilihanku == 1) {
                M.tulisSubMenu();
                int pilihan;
                pilihan = read.nextInt();

                int bar,kol;
                System.out.print("Masukan Jumlah Baris :");
                bar = read.nextInt();
                System.out.print("Masukan Jumlah Kolom :");
                kol = read.nextInt();

                Matriks Mutrex1 = new Matriks(bar,kol+1);
                int i,j;
                for ( i = 0; i < Mutrex1.brs; i++){
                    for ( j = 0; j< (Mutrex1.kol-1); j++){
                        System.out.print("Masukan a" + (i+1) + (j + 1) + ": ");
                        Mutrex1.mat[i][j] = read.nextDouble();
                    }
                    System.out.print("Masukan b" + (i+1) + ": ");
                    Mutrex1.mat[i][Mutrex1.kol-1] = read.nextDouble();
                }

                if (pilihan == 1){
                    Mutrex1.gauss();
                    if (Mutrex1.mat[bar][kol] == 0){
                        if (Mutrex1.mat[bar][kol+1] != 0){
                            System.out.print("Solusi tidak ada");
                        }
                        else {
                            System.out.print("Solusi berupa parametrik :  ");
                        }
                    }
                    else {
                        
                    }
                }
                if (pilihan == 2){
                    
                }
            }
            if (pilihanku == 2) {
                int bar,kol;
                System.out.print("Masukan Jumlah Baris :");
                bar = read.nextInt();
                System.out.print("Masukan Jumlah Kolom :");
                kol = read.nextInt();

                Matriks Mutrex2 = new Matriks(bar,kol);
				int i,j;
                for ( i = 0; i < bar; i++){
                    for ( j = 0; j< kol; j++){
                        System.out.print("Masukan nilai matrik pada baris ke-" + (i+1) + " dan kolom ke-" + (j+1) + " : ");
                        Mutrex2.mat[i][j] = read.nextDouble();
                    }
                }
                double hasil;
                hasil = Mutrex2.determinan();
                System.out.print("Solusi determinan matriks adalah : "); 
                System.out.print(hasil);
            }

            if (pilihanku == 3) {
                int bar,kol;
                System.out.print("Masukan Jumlah Baris :");
                bar = read.nextInt();
                System.out.print("Masukan Jumlah Kolom :");
                kol = read.nextInt();

                Matriks Mutrex3 = new Matriks(bar,kol);
				int i,j;
                for ( i = 0; i < bar; i++){
                    for ( j = 0; j< kol; j++){
                        System.out.print("Masukan nilai matrik pada baris ke-" + (i+1) + " dan kolom ke-" + (j+1) + " : ");
                        Mutrex3.mat[i][j] = read.nextDouble();
                    }
                }
                System.out.print("Solusi Matriks hasil inverse adalah :  "); System.out.println();
                Matriks hasilCofactor = new Matriks(bar,kol);
                hasilCofactor = Mutrex3.inverse();
                hasilCofactor.tulisMatriks();
            }
            if (pilihanku == 4) {
                int bar,kol;
                System.out.print("Masukan Jumlah Baris :");
                bar = read.nextInt();
                System.out.print("Masukan Jumlah Kolom :");
                kol = read.nextInt();

                Matriks Mutrex4 = new Matriks(bar,kol);
				int i,j;
                for ( i = 0; i < bar; i++){
                    for ( j = 0; j< kol; j++){
                        System.out.print("Masukan nilai matrik pada baris ke-" + (i+1) + " dan kolom ke-" + (j+1) + " : ");
                        Mutrex4.mat[i][j] = read.nextDouble();
                    }
                }
                System.out.print("Solusi Matriks hasil Kofaktor adalah :  "); System.out.println();
                Matriks hasilCofactor = new Matriks(bar,kol);
                hasilCofactor = Mutrex4.cofactor();
                hasilCofactor.tulisMatriks();
            }
            if (pilihanku == 5) {
                int bar,kol;
                System.out.print("Masukan Jumlah Baris :");
                bar = read.nextInt();
                System.out.print("Masukan Jumlah Kolom :");
                kol = read.nextInt();

                Matriks Mutrex5 = new Matriks(bar,kol);
				int i,j;
                for ( i = 0; i < bar; i++){
                    for ( j = 0; j< kol; j++){
                        System.out.print("Masukan nilai matrik pada baris ke-" + (i+1) + " dan kolom ke-" + (j+1) + " : ");
                        Mutrex5.mat[i][j] = read.nextDouble();
                    }
                }
                Mutrex5.solusiSPLGaussJordan(Mutrex5);
                System.out.print("Solusi Matriks hasil Adjoin adalah :  "); System.out.println();
                Matriks hasilAdjoint = new Matriks(bar,kol);
                hasilAdjoint = Mutrex5.adjoint();
                hasilAdjoint.tulisMatriks();
            }

            if (pilihanku == 6) {
                int n;
                double hasil;
                System.out.print("Masukan n :");
                n = read.nextInt();
                Matriks interporu = new Matriks(n+1,n+2);
                hasil = interporu.interpol(n);
                System.out.print(hasil);
            }
            if (pilihanku == 7) {
                break;
            }
            if ((pilihanku > 7) || (pilihanku < 1)) {
                System.out.print("Masukin yang bener dong!");
            }
            System.out.println();
            System.out.println();
        }
        System.out.print("Terimakasih Telah Berbelanja");
    }
}
