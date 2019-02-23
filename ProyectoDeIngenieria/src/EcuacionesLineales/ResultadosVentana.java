package EcuacionesLineales;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

import Jama.Matrix;
import MenuPrincipal.MenuPrincipal;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import javax.swing.ScrollPaneConstants;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class ResultadosVentana {

	private JFrame frame;
	private JTable table;
	Solver s;
	private double x, y, z, det, detX, detY, detZ;
	private double[][] matrixFull;
	private String sysType;
	private JTextField textFieldSysType;
	boolean compatible;

	/**
	 * Launch the application.
	 */
	
	public ResultadosVentana(String sysType, double x, double y, double z, double[][] matrixFull, double det, double detX, double detY, double detZ ,boolean compatible) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.sysType = sysType;
		this.matrixFull = matrixFull;
		this.det = det;
		this.detX = detX;
		this.detY = detY;
		this.detZ = detZ;
		this.compatible = compatible;
		initialize();
		this.frame.setVisible(true);
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		s = new Solver(table);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		double windowWidth = screenSize.getWidth()/2;
		double windowHeight = screenSize.getHeight()/2;
		frame = new JFrame("Resultados");
		frame.setBounds(100, 100, (int) windowWidth, (int) windowHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		JLabel lblResults = new JLabel("Resultados");
		lblResults.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblResults.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblResults, BorderLayout.NORTH);
		
		JPanel topScrollPanel = new JPanel();
		JPanel bottomScrollPanel = new JPanel();
		
		JPanel panelCenter = new JPanel();
		frame.getContentPane().add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneBottom = new JScrollPane(bottomScrollPanel);
		
		JTextPane resolucionCompDet = new JTextPane();
		resolucionCompDet.setEditable(false);
		resolucionCompDet.setContentType("text/html");
		resolucionCompDet.setText("<html>"
				+ "<p><b><u><i>Resolucion por Metodo de Cramer</i></u></b></p>"
				+ "<p>"+ matrixFull[0][0] + "&nbsp;" + matrixFull[0][1] + "&nbsp;" + matrixFull[0][2] + "&nbsp; = " + matrixFull[0][3] + "</p>" 
				+ "<p>"+ matrixFull[1][0] + "&nbsp;" + matrixFull[1][1] + "&nbsp;" + matrixFull[1][2] + "&nbsp; = " + matrixFull[1][3] + "</p>" 
				+ "<p>"+ matrixFull[2][0] + "&nbsp;" + matrixFull[2][1] + "&nbsp;" + matrixFull[2][2] + "&nbsp; = " + matrixFull[2][3] + "</p>"
				+ "<br>"
				+ "<p>Primero, debemos indentificar el tipo de la ecuacion lineal, por tanto, calculamos en rango <br>"
				+ "En este caso, el tipo de la ecuacion es " + sysType + "</p>"
				+ "<p>Como es Sistema Compatible Determinado, se puede resolver por el metodo de Cramer<br>"
				+ "Primero calculamos el determinante de la matriz principal, el cual seria: " + det + "</p>"
				+ "<br>" 
				+ "<p>A continuacion, calcularemos la X<br>"
				+ "Para ello, sustituimos la columna solucion por la de la X, de esta manera:<br>"
				+ "<p>"+ matrixFull[0][3] + "&nbsp;" + matrixFull[0][1] + "&nbsp;" + matrixFull[0][2] + "</p>" 
				+ "<p>"+ matrixFull[1][3] + "&nbsp;" + matrixFull[1][1] + "&nbsp;" + matrixFull[1][2] + "</p>" 
				+ "<p>"+ matrixFull[2][3] + "&nbsp;" + matrixFull[2][1] + "&nbsp;" + matrixFull[2][2] + "</p>"
				+ "Mas tarde, dividimos este determinante entre el determinante de la matriz principal, y sale: " + x + "</p>"
				+ "<br>"
				+ "<p>A continuacion, calcularemos la Y<br>"
				+ "Para ello, sustituimos la columna solucion por la de la Y, de esta manera:<br> "
				+ "<p>"+ matrixFull[0][0] + "&nbsp;" + matrixFull[0][3] + "&nbsp;" + matrixFull[0][2] + "</p>" 
				+ "<p>"+ matrixFull[1][0] + "&nbsp;" + matrixFull[1][3] + "&nbsp;" + matrixFull[1][2] + "</p>" 
				+ "<p>"+ matrixFull[2][0] + "&nbsp;" + matrixFull[2][3] + "&nbsp;" + matrixFull[2][2] + "</p>"
				+ "Mas tarde, dividimos ese determinante entre el determinante de la matriz principal, y sale: " + y + "</p>"
				+ "<br>"
				+ "<p>A continuacion, calcularemos la Z<br>"
				+ "Para ello, sustituimos la columna solucion por la de la Z, de esta manera:<br> "
				+ "<p>"+ matrixFull[0][0] + "&nbsp;" + matrixFull[0][1] + "&nbsp;" + matrixFull[0][3] + "</p>" 
				+ "<p>"+ matrixFull[1][0] + "&nbsp;" + matrixFull[1][1] + "&nbsp;" + matrixFull[1][3] + "</p>" 
				+ "<p>"+ matrixFull[2][0] + "&nbsp;" + matrixFull[2][1] + "&nbsp;" + matrixFull[2][3] + "</p>"
				+ "Mas tarde, dividimos ese determinante entre el determinante de la matriz principal, y sale: " + z + "</p>"
				+ "</html>");
		
		JTextPane resolucionNoCompDet = new JTextPane();
		resolucionNoCompDet.setEditable(false);
		resolucionNoCompDet.setContentType("text/html");
		resolucionNoCompDet.setText("<html>"
				+ "<p><b><u><i>Resolucion por Metodo de Cramer</i></u></b></p>"
				+ "<p>"+ matrixFull[0][0] + "&nbsp;" + matrixFull[0][1] + "&nbsp;" + matrixFull[0][2] + "&nbsp; = " + matrixFull[0][3] + "</p>" 
				+ "<p>"+ matrixFull[1][0] + "&nbsp;" + matrixFull[1][1] + "&nbsp;" + matrixFull[1][2] + "&nbsp; = " + matrixFull[1][3] + "</p>" 
				+ "<p>"+ matrixFull[2][0] + "&nbsp;" + matrixFull[2][1] + "&nbsp;" + matrixFull[2][2] + "&nbsp; = " + matrixFull[2][3] + "</p>"
				+ "<br>"
				+ "<p>Primero, debemos indentificar el tipo de la ecuacion lineal, por tanto, calculamos en rango <br>"
				+ "En este caso, el tipo de la ecuacion es " + sysType + "</p>");
		
		if(compatible) {
			bottomScrollPanel.add(resolucionCompDet);
		} else {
			bottomScrollPanel.add(resolucionNoCompDet);
		}
	
		JScrollPane scrollPaneTop = new JScrollPane(topScrollPanel);
		scrollPaneTop.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollPaneTop, scrollPaneBottom);
		topScrollPanel.setLayout(new GridLayout(3,0,0,0));
		
		JLabel lblSysType = new JLabel("Tipo de Sistema de Ecuaciones");
		lblSysType.setHorizontalAlignment(SwingConstants.CENTER);
		lblSysType.setFont(new Font("Tahoma", Font.BOLD, 18));
		topScrollPanel.add(lblSysType);
		
		
		textFieldSysType = new JTextField();
		textFieldSysType.setEditable(false);
		textFieldSysType.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldSysType.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldSysType.setText(sysType);
		topScrollPanel.add(textFieldSysType);
		
		JLabel lblSolucion = new JLabel();
		lblSolucion.setText("X = " + x + "  Y = " + y + "  Z = " + z);
		lblSolucion.setHorizontalAlignment(SwingConstants.CENTER);
		lblSolucion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		topScrollPanel.add(lblSolucion);
		
		splitPane.setContinuousLayout(true);
		splitPane.setResizeWeight(0.5);
		splitPane.setOneTouchExpandable(true);
		panelCenter.add(splitPane);
		
		JPanel buttonPanel = new JPanel();
		frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new GridLayout(0, 2, 0, 0));
		buttonPanel.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()/8));
		
		JButton btnMenu = new JButton("Menu Principal");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new MenuPrincipal();
			}
		});
		buttonPanel.add(btnMenu);
		
		JButton btnNuevoSistema = new JButton("Nuevo Sistema");
		btnNuevoSistema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new EcuacionesVentana();
			}
		});
		
		buttonPanel.add(btnNuevoSistema);
		
		frame.setVisible(true);
	}
}