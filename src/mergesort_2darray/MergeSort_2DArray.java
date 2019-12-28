/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesort_2darray;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class MergeSort_2DArray {

    public static void main(String[] args) throws FileNotFoundException {
        String mat[][]=ObterMat();
        //Boolean que permite selecionar ordenação crescente ou descrescente
        boolean isMergeSortCrescent=false;
        System.out.println("Given Matrix"); 
        printMatrix(mat); 
        sort(mat, 0, mat.length-1,isMergeSortCrescent); 
        System.out.println("\nSorted matrix"); 
        printMatrix(mat); 
    }
    
    static void startMergeSort(String[][]mat,boolean isMergeSortCrescent){
        sort(mat, 0, mat.length-1,isMergeSortCrescent);
    }
    
    static void sort(String mat[][], int low, int high, boolean isMergeSortCrescent ) 
    { 
        if (low < high) 
        { 
                // Encontrar o meio 
                int mid = (low+high)/2; 
                // Ordenar a primeira e a segunda metade 
                sort(mat, low, mid,isMergeSortCrescent); 
                sort(mat , mid+1, high,isMergeSortCrescent); 
                // Juntar as duas metades ordenadas
                merge(mat, low, mid, high,isMergeSortCrescent ); 
        } 
    } 
    /*
    *Junta duas subarrays de arr[]
    * A primeira subarray é arr[low..mid] 
    * A primeira subarray é arr[mid+1..high] 
    */
    static void merge(String mat[][], int low, int mid, int high, boolean isMergeSortCrescent ) 
    { 
        // Encontrar o tamanho das duas matrizes 
        int length1 = mid - low + 1; 
        int length2 = high - mid; 
  
        //Criar arrays temporárias para copiar os valores
        String leftMat[][] = new String [length1][3]; 
        String rightMat[][] = new String [length2][3]; 
        
        //System.out.println("Entrei no metodo merge");
        //printMatrix(mat);
        
        //Copiar informação para as matrizes temporárias
        //System.out.println("leftMat");
        //System.out.println("length1: "+ length1);
        
        for (int i=0; i<length1; i++){         
            for(int j=0;j<mat[0].length;j++){
                leftMat[i][j]=mat[i+low][j];
                //System.out.printf("leftmat[%s][%s]: ",i,j);
                //System.out.print("["+leftMat[i][j]+"] ");   
            }
            //System.out.println("");
        }
        
        //System.out.println("");
        //System.out.println("rightMat");
        //System.out.println("length2: "+ length2);
        for (int j=0; j<length2; j++){
            for(int m=0;m<mat[0].length;m++){
                rightMat[j][m]=mat[mid+1+j][m];
                //System.out.printf("rightMat[%s][%s]: ",j,m);
                //System.out.print("["+rightMat[j][m]+"] ");   
            }
            //System.out.println("");
        }
        //System.out.println("");
        /* Juntar as duas matrizes temporárias */
        // Indices iniciais (i e j) das duas subarrays e da subarray (k)
        int i = 0, j = 0, k = low;
        int powerReading1, powerReading2;
        //Selecionar se a ordenação é crescente ou decrescente
        //Crescente
            while (i < length1 && j < length2)
            {
                powerReading1=Integer.parseInt(leftMat[i][2]);
                //System.out.println("i:"+i+" power1: "+ powerReading1 );
                powerReading2=Integer.parseInt(rightMat[j][2]);
                //System.out.println("j:"+j+" power2: "+ powerReading2 );
                //System.out.println("");
                
                if(isMergeSortCrescent){
                    if (powerReading1 <= powerReading2) 
                    { 
                        for(int col=0;col<mat[0].length;col++){
                            mat[k][col]=leftMat[i][col];
                        }
                        //System.out.println("i: "+i);
                        i++;
                    } 
                    else
                    { 
                        for(int col=0;col<mat[0].length;col++){
                            mat[k][col]=rightMat[j][col];
                        }
                        //System.out.println("j: "+j);
                        j++;
                    }
                    //System.out.println("k: "+k);
                    k++;
                }
                //Decrescente
                else{
                    if (powerReading1 >= powerReading2) 
                    { 
                        for(int col=0;col<mat[0].length;col++){
                            mat[k][col]=leftMat[i][col];
                        }
                        //System.out.println("i: "+i);
                        i++;
                    } 
                    else
                    { 
                        for(int col=0;col<mat[0].length;col++){
                            mat[k][col]=rightMat[j][col];
                        }
                        //System.out.println("j: "+j);
                        j++;
                    }
                    k++;
                }
            }       
        /* Copiar elementos restantes de leftMat[][], caso existam */
        while (i < length1) 
        { 
            for(int col=0;col<mat[0].length;col++){
                mat[k][col]=leftMat[i][col];
            }
            k++;
            i++;
        } 
        /* Copiar elementos restantes de rightMat[][], caso existam */
        while (j < length2) 
        { 
            for(int col=0;col<mat[0].length;col++){
                mat[k][col]=rightMat[j][col];
            }
            k++;
            j++;
        } 
    }
    
    public static void printMatrix(String mat[][]) 
    { 
        for(int i=0;i<mat.length;i++){
            for(int j=0;j<mat[0].length;j++){
                System.out.print("["+mat[i][j]+"]");
            }
            System.out.println("");
        }
        System.out.println("");
    } 
    
    private static String[][] ObterMat() throws FileNotFoundException{
        BufferedReader file= new BufferedReader(new FileReader("DAYTON.csv"));
        Scanner sc=new Scanner(file);
        String linha;
        String[][] mat=new String[10][3];
        String[] datetime;
        String[] mw;
        sc.nextLine();
        for(int i=0;i<10;i++){
            linha=sc.nextLine();
            mw=linha.split(",");
            datetime=mw[0].split(" ");
            mat[i][0]=datetime[0];
            mat[i][1]=datetime[1];
            mat[i][2]=mw[1];
        }
        return mat;
    }
}
