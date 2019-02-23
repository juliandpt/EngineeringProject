package MenuPrincipal;

import java.awt.Dimension;


import java.awt.EventQueue;
import java.awt.Toolkit;

import Poblacion.*;
import Conicas.*;
import EcuacionesLineales.*;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MenuPrincipal {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal window = new MenuPrincipal();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the application.
	 */
	public MenuPrincipal() {
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension buttonSize = new Dimension(180, 60);
		
		double windowWidth = screenSize.getWidth()/3;
		double windowHeight = screenSize.getHeight()/2;
		frame = new JFrame("Menu Principal");
		frame.setBounds(100, 100, (int) windowWidth, (int) windowHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel lblTitle = new JLabel("Menu Principal");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 25));
		frame.getContentPane().add(lblTitle, BorderLayout.NORTH);
		
		JPanel panelCenter = new JPanel();
		frame.getContentPane().add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new GridLayout(3, 0, 0, 0));
		
		JButton btnEcLin = new JButton("Ecuaciones Lineales");
		btnEcLin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new EcuacionesVentana();
			}
		});
		btnEcLin.setToolTipText("Resolucion de ecuaciones lineales de 3 incognitas sin parametros");
		panelCenter.add(btnEcLin);
		
		JButton btnConicas = new JButton("Funciones Conicas");
		btnConicas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new ConicasVentana();
			}
		});
		btnConicas.setToolTipText("Resolucion y representacion grafica de funciones conicas");
		panelCenter.add(btnConicas);
		
		JButton btnPoblacion = new JButton("Poblacion");
		btnPoblacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new PoblacionVentana();
			}
		});
		btnPoblacion.setToolTipText("Simulacion del crecimiendo de la poblacion dada una serie de parametros");
		panelCenter.add(btnPoblacion);
		
		
	}
}
