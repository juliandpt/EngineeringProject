package Poblacion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Operation {
	
	private double[][] matrixArrayParameters, matrixResult;
	static Object[][] data = new Object[22][6];
	private String[] headers = {"Año", "PJ", "PA", "PT", "pjn/pan", "Tn/Tn-1"};
	static double[] totals = new double[22];
	static String nombre;
	String[][] rankArr = new String[10][2];
	File rankFile = new File("Ranking.txt");
	
	public JTable createTable() throws IOException {
		
		//Titulos
		for(int i = 0; i < data[0].length; i++) {
			data[0][i] = headers[i];
		}
		
		//Años
		for(int i = 0; i < data.length-1; i++) {
			data[i+1][0] = i;
		}
		
		//Tn
		for (int i = 1; i <= 20; i++) {
			data[i+1][5] = totals[i+1] / totals[i];
		}
		
		JTable table = new JTable(new DefaultTableModel(data, headers) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		
		write();
		
		return table;
		
	}
	
	public Operation() {
		
	}
	
	
	public Operation(String k, String alpha, String beta, String pjn, String pan, String nombre) {
		matrixArrayParameters = new double[2][2];
		matrixArrayParameters[0][0] = 0;
		matrixArrayParameters[0][1] = Double.parseDouble(k);
		matrixArrayParameters[1][0] = Double.parseDouble(alpha);
		matrixArrayParameters[1][1] = Double.parseDouble(beta);
		
		matrixResult = new double[2][1];
		matrixResult[0][0] = Double.parseDouble(pjn);
		matrixResult[1][0] = Double.parseDouble(pan);
		
		this.nombre = nombre;
	}
	
	public void printToConsole() {
		for(int i = 0; i < 2; i++) {
			System.out.println((int) matrixResult[i][0]);
		}
		
	}
	
	public void multiply(double[][] a, double [][] b) {
		double[][] c = new double[2][1];
        int m1 = a.length;
        int n1 = a[0].length;
        int m2 = b.length;
        int n2 = b[0].length;
        if (n1 != m2) throw new RuntimeException("Illegal matrix dimensions.");
        for (int i = 0; i < m1; i++) {
            for (int j = 0; j < n2; j++) {
                for (int k = 0; k < n1; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        
        matrixResult = c;
                
    }
	
	public void calculateToIteration() {
		for (int i = 0; i <= 20; i++) {
			//Poblacion J
			data[i+1][1] = (int)matrixResult[0][0];
			//Poblacion A
			data[i+1][2] = (int)matrixResult[1][0];
			//Poblacion T
			data[i+1][3] = (int)matrixResult[0][0] + (int)matrixResult[1][0];
			totals[i+1] = (int)matrixResult[0][0] + (int)matrixResult[1][0];
			
			//pjn / pan
			double pj = (int)matrixResult[0][0];
			double pa = (int)matrixResult[1][0];
			data[i+1][4] = pj / pa;
			
			//resultados
			multiply(matrixArrayParameters, matrixResult);
		}
		
	}
	
	public void write() throws IOException {
		String[] split;
		String[] read = new String[10];
		String info, position;
		int separatorIndex;
		int counter = 0;
		
		/**
		 * Se comprueba si el archivo existe
		 * Si existe se recojen los datos
		 * Si no existe se rellena de valores de 1 al 10
		 */
		
		/**
		 * System.out.print(split[0]);
		   System.out.println(split[2]);
		   System.out.println(split[4]); ----- poblacion
		   Extraccion parametros
		 */
		
		
		if(rankFile.exists() == false) {
			rankFile.createNewFile();
			PrintWriter writer = new PrintWriter(new FileWriter(rankFile, true));
			rankArr[0][1] = nombre + "| Poblacion |" + data[21][3];
			for (int i = 0; i < 10; i++) {
				rankArr[i][0] = String.valueOf(i+1) + "|";
				
				if(i == 0) {
					writer.print(rankArr[i][0]);
					writer.println(rankArr[i][1]);
				} else {
					writer.println(rankArr[i][0]);
				}
				
			}
			writer.close();
		} else {
			Scanner sc = new Scanner(rankFile);
	
			while (sc.hasNextLine()) {				
				read[counter] = sc.nextLine();
				separatorIndex = read[counter].indexOf("|");
				info = read[counter].substring(separatorIndex+1);
				position = read[counter].substring(0, separatorIndex+1);
				rankArr[counter][0] = position;
				rankArr[counter][1] = info;
				
				counter++;
			}
			
			sc.close();
			
			checkIfFullAndRw();
		}	
	}
	
	private void checkIfFullAndRw() throws IOException {
		int counter = 0;
		boolean stop = false;
		boolean full = false;
		
		for (int i = 0; i < 10; i++) {
			if(rankArr[i][1].equals("") == false) {
				full = true;
			} else {
				full = false;
			}
		}
		
		if(!full) {
			while (stop == false) {
				if(rankArr[counter][1].equals("")) {
					stop = true;
					rankArr[counter][1] = nombre + "| Poblacion |" + data[21][3];
				} else if (counter == 9) {
					stop = true;
				}
				counter++;
				System.out.println(counter);
			}
			
			reorder();
			
		} else {
			System.out.println("Array Is Full");
			insertIntoArray();
		}
		
	
		
	}
	
	/** 
	 * El Array se reordena para ordenar el numero de poblacion en orden descendente
	 * @throws IOException
	 */
	
	private void reorder() throws IOException {
		String[] split1, split2;
		int split1I, split2I;
		
		for (int i = 0; i < rankArr.length; i++) {
			String temp = rankArr[i][1];
			if (rankArr[i][1].equals("")) {
				break;
			}
			
			for (int j = i+1; j < rankArr.length; j++) {
				if(rankArr[j][1].equals("")) {
					break;
				}
				
				if (rankArr[i][1] != rankArr[j][1]) {
					split1 = rankArr[i][1].split("\\|");
					split2 = rankArr[j][1].split("\\|");
					
					split1I = Integer.parseInt(split1[2]);
					split2I = Integer.parseInt(split2[2]);
					
					if (split2I > split1I) {
						rankArr[i][1] = rankArr[j][1];
						rankArr[j][1] = temp;
					}
					
				}
			}
		}
		PrintWriter writer = new PrintWriter(new FileWriter(rankFile, false));
		for(int i = 0; i < 10; i++) {
			writer.print(rankArr[i][0]);
			writer.println(rankArr[i][1]);
		}
		writer.close();
		
	}
	
	private void insertIntoArray() throws IOException {
		int newData = Integer.parseInt(String.valueOf(data[21][3]));
		String split[];
		int splitInt, counter = 0;
		boolean insert = false;
		
		while (counter < 10) {
			split = rankArr[counter][1].split("\\|");
			splitInt = Integer.parseInt(split[2]);
			
			if (newData > splitInt) {
				pushArrFromIndex(counter);
				insert = true;
				break;
			}
			
			counter++;
		}
		
		if(insert) {
			rankArr[counter][1] = nombre + "| Poblacion |" + data[21][3];
		} else {
			System.out.println("Cannot insert");
		}
		
		PrintWriter writer = new PrintWriter(new FileWriter(rankFile, false));
		for(int i = 0; i < 10; i++) {
			writer.print(rankArr[i][0]);
			writer.println(rankArr[i][1]);
		}
		writer.close();
		
	}
	
	private void pushArrFromIndex(int index) {
		for (int i = 8; i >= index; i--) {
			rankArr[i+1][1] = rankArr[i][1];
		}
	}
}
