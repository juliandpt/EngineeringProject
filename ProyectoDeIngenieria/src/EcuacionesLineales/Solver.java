package EcuacionesLineales;

import javax.swing.JTable;

import Jama.Matrix;

public class Solver {
	
	private double[][] matrixFull, matrixCoeff, matrixAmp, matrixCoeffRank;
	private double[] matrixEquals;
	private double x, y, z;
	private String sysType;
	private JTable table;
	Matrix tempX, tempY, tempZ;
	static final int R = 3;
	static final int C = 3;
	boolean compatible = false;
	
	public Solver() {
		
	}
	
	public Solver(JTable table) {
		this.table = table;
		
	}
	
	private void tableToMatrix() {
		matrixFull = new double[3][4];
		
		for(int row = 0; row < table.getRowCount(); row++) {
			for(int col = 0; col < table.getColumnCount(); col++) {
				String aux = (String) table.getValueAt(row, col);
				matrixFull[row][col] = Double.parseDouble(aux);
			}
		}
	}
	
	
	private void buildOnlyCoeffMatrix(){
		matrixCoeff = new double[3][3];
		matrixCoeffRank = new double[3][3];
		
		for(int row = 0; row < 3; row++) {
			for(int col = 0; col < 3; col++) {
				matrixCoeff[row][col] = matrixFull[row][col];
			}
		}
		matrixCoeffRank = matrixCoeff;
	}
	
	private void buildAmpMatrix() {
		matrixAmp = new double[3][3];
		
		for (int row = 0; row < 3; row++) {
			for (int col = 1; col < 4; col++) {
				matrixAmp[row][col-1] = matrixFull[row][col];
			}
		}	
	}
	
	private void buildEqualsMatrix() {
		matrixEquals = new double[3];
		
		for (int row = 0; row < 3; row++) {
			matrixEquals[row] = matrixFull[row][3];
		}
	}
	
	public void buildAllMatrix() {
		tableToMatrix();
		buildOnlyCoeffMatrix();
		buildAmpMatrix();
		buildEqualsMatrix();
	}
	
	private void swapRow(double[][] mat, int row1, int row2, int col) {
		for (int i = 0; i < col; i++) {
			double temp = mat[row1][i];
			mat[row1][i] = mat[row2][i];
			mat[row2][i] = temp;
		}
		
	}
	
	private double[][] getTempMat(int colTemp) {
		double[][] temp = new double[3][3];
		
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				temp[row][col] = matrixCoeff[row][col];
			}
		}
		
		for (int row = 0; row < 3; row++) {
			temp[row][colTemp] = matrixEquals[row];
		}
		
		return temp;
	}
	
	public String getCompatibility() {
		int iniRange = new Matrix(matrixCoeff).rank();
		
		if (iniRange == 3) {
			sysType = "Sistema Compatible Determinado";
			compatible = true;
			solveVars();
			return sysType;
		} else if ((iniRange < 3) && (iniRange == new Matrix(matrixCoeffRank).rank())) {
			sysType = "Sistema Compatible Indeterminado, tiene infinitas soluciones";
			return sysType;
			
		} else if ((iniRange < 3) && (iniRange < new Matrix(matrixCoeffRank).rank())) {
			sysType = "Sistema Incompatible";
			return sysType;
			
		}
		return null;
	}
	
	public void solveVars() {
		Matrix coeffM;
		
		coeffM = new Matrix(matrixCoeff);
		tempX = new Matrix(getTempMat(0));
		tempY = new Matrix(getTempMat(1));
		tempZ = new Matrix(getTempMat(2));
		
		x = tempX.det() / coeffM.det();
		y = tempY.det() / coeffM.det();
		z = tempZ.det() / coeffM.det();
		
		
		System.out.println(x + " " + y + " " + z);
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getZ() {
		return z;
	}
	
	public String getSysType() {
		return sysType;
	}
	
	public double[][] getFullMatrix() {
		return matrixFull;
	}
	
	public double getDet() {
		Matrix coeffMat = new Matrix(matrixCoeff);
		return coeffMat.det();
	}
	
	public boolean getCompatible() {
		return compatible;
	}
	
	public double getDetX() {
		return tempX.det();
	}
	
	public double getDetY() {
		return tempY.det();
	}
	
	public double getDetZ() {
		return tempZ.det();
	}	
}
