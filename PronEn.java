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

class PronEn{
	public static void main(String[] args){
        System.out.print("Selamat Datang di Indomaret, Selamat Berbelanja!");
        System.out.println();
        System.out.print("Mau Pake file atau langsung aja? (press '1' for file, '0' for langsung)");
        System.out.println();
        Scanner read = new Scanner(System.in);
        int mauApa;
        mauApa = read.nextInt();
        if (mauApa == 0) {
            while (1+1 == 2) {
                int pilihanku;
                Matriks M = new Matriks(1,1);
                
                pilihanku = M.cetakMenu();
                
                if (pilihanku == 1) {
                    M.tulisSubMenu();
                    int pilihan;
                    System.out.print("Masukan pilihan :");
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
                        Mutrex1.tulisMatriks();
                        Mutrex1.solusiSPLGauss();
                    }
                    if (pilihan == 2){
                    
                        Mutrex1.gaussJordan();
                        Mutrex1.tulisMatriks();
                        Mutrex1.solusiSPLGaussJordan();
                    }
                    if (pilihan == 3){
                        Mutrex1.tulisMatriks();
                        Mutrex1.solusiSPLCramer();
                    }
                    if (pilihan == 4){
                        Mutrex1.tulisMatriks();
                        Mutrex1.solusiSPLInvers();
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
        } else {
            while (1+1 == 2) {
                int pilihanku;
                String namaFile;
                
                Matriks M = new Matriks(1,1);
                
                pilihanku = M.cetakMenu();
                
                if (pilihanku == 1) {
                    M.tulisSubMenu();
                    int pilihan;
                    System.out.print("Masukan pilihan :");
                    pilihan = read.nextInt();

                    Matriks Mutrex1 = new Matriks(M.brs,M.kol);
                    namaFile = M.inputNamaFile();

                    Mutrex1.bacaFileMatriks(namaFile);
                    if (pilihan == 1){
                        Mutrex1.gauss();
                        Mutrex1.tulisMatriks();
                        Mutrex1.solusiSPLGauss();
                    }
                    if (pilihan == 2){
                    
                        Mutrex1.gaussJordan();
                        Mutrex1.tulisMatriks();
                        Mutrex1.solusiSPLGaussJordan();
                    }
                    if (pilihan == 3){
                        Mutrex1.tulisMatriks();
                        Mutrex1.solusiSPLCramer();
                    }
                    if (pilihan == 4){
                        Mutrex1.tulisMatriks();
                        Mutrex1.solusiSPLInvers();
                    }
                    if ((pilihan > 4) || (pilihanku < 1)) {
                        System.out.print("Masukin yang bener dong!");
                    }
                    System.out.print("Mau disave ga? (1/0) :  ");
                    int mauga = read.nextInt();
                    if (mauga == 1){
                        String temp = Mutrex1.sTulisMatriks();
                        Mutrex1.saveToFile(temp);
                    }
                }
                if (pilihanku == 2) {
                    Matriks Mutrex2 = new Matriks(M.brs,M.kol);
                    System.out.print("Masukan nama file :");
                    namaFile = read.nextLine();
                    Mutrex2.bacaFileMatriks(namaFile);
                    double hasil;
                    hasil = Mutrex2.determinan();
                    System.out.print("Solusi determinan matriks adalah : "); 
                    System.out.print(hasil);

                    System.out.print("Mau disave ga? (1/0) :  ");
                    int mauga = read.nextInt();
                    if (mauga == 1){
                        String temp = Mutrex2.doubleToString(hasil);
                        Mutrex2.saveToFile(temp);
                    }
                }

                if (pilihanku == 3) {
                    Matriks Mutrex3 = new Matriks(M.brs,M.kol);
                    System.out.print("Masukan nama file :");
                    namaFile = read.nextLine();
                    Mutrex3.bacaFileMatriks(namaFile);

                    System.out.print("Solusi Matriks hasil inverse adalah :  "); System.out.println();
                    Matriks hasilInvers = new Matriks(Mutrex3.brs,Mutrex3.kol);
                    hasilInvers.inverse().tulisMatriks();

                    System.out.print("Mau disave ga? (1/0) :  ");
                    int mauga = read.nextInt();
                    if (mauga == 1){
                        String temp = Mutrex3.sTulisMatriks();
                        Mutrex3.saveToFile(temp);
                    }
                }
                if (pilihanku == 4) {
                    Matriks Mutrex4 = new Matriks(M.brs,M.kol);
                    System.out.print("Masukan nama file :");
                    namaFile = read.nextLine();
                    Mutrex4.bacaFileMatriks(namaFile);
                    
                    System.out.print("Solusi Matriks hasil Kofaktor adalah :  "); System.out.println();
                    Matriks hasilCofactor = new Matriks(Mutrex4.brs,Mutrex4.kol);
                    hasilCofactor = Mutrex4.cofactor();
                    hasilCofactor.tulisMatriks();

                    System.out.print("Mau disave ga? (1/0) :  ");
                    int mauga = read.nextInt();
                    if (mauga == 1){
                        String temp = Mutrex4.sTulisMatriks();
                        Mutrex4.saveToFile(temp);
                    }
                }
                if (pilihanku == 5) {
                    Matriks Mutrex5 = new Matriks(M.brs,M.kol);
                    System.out.print("Masukan nama file :");
                    namaFile = read.nextLine();
                    Mutrex5.bacaFileMatriks(namaFile);
                    System.out.print("Solusi Matriks hasil Adjoin adalah :  "); System.out.println();
                    Matriks hasilAdjoint = new Matriks(Mutrex5.brs,Mutrex5.kol);
                    hasilAdjoint = Mutrex5.adjoint();
                    hasilAdjoint.tulisMatriks();

                    System.out.print("Mau disave ga? (1/0) :  ");
                    int mauga = read.nextInt();
                    if (mauga == 1){
                        String temp = Mutrex5.sTulisMatriks();
                        Mutrex5.saveToFile(temp);
                    }
                }

                if (pilihanku == 6) {
                    Matriks interporu = new Matriks(M.brs,M.kol);
                    double hasil = interporu.interpolfile();
                    System.out.print(hasil);

                    System.out.print("Mau disave ga? (1/0) :  ");
                    int mauga = read.nextInt();
                    if (mauga == 1){
                        String temp = interporu.doubleToString(hasil);
                        interporu.saveToFile(temp);
                    }
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
}
