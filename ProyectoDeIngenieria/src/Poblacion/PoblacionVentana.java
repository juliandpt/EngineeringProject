package Poblacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import java.awt.Font;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;

import MenuPrincipal.*;


public class PoblacionVentana {

	private JFrame frame;
	private JTextField textFieldK;
	private JTextField textFieldAlpha;
	private JTextField textFieldBeta;
	private JTextField textFieldPjn;
	private JTextField textFieldPan;
	private JTextField textFieldNombre;
	PrintWriter writer;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public PoblacionVentana() {
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		double windowWidth = screenSize.getWidth()/2;
		double windowHeight = screenSize.getHeight()/2;
		
		frame = new JFrame("Contador de Poblacion");
		frame.setBounds(100, 100, (int) windowWidth, (int) windowHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel lblIntroduzcaLosDatos = new JLabel("Introduzca los datos");
		lblIntroduzcaLosDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntroduzcaLosDatos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(lblIntroduzcaLosDatos, BorderLayout.NORTH);
		
		JPanel panelSouth = new JPanel();
		frame.getContentPane().add(panelSouth, BorderLayout.SOUTH);
		panelSouth.setLayout(new GridLayout(0, 4, 0, 0));
		panelSouth.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()/8));
		panelSouth.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldK.setText("");
				textFieldAlpha.setText("");
				textFieldBeta.setText("");
				textFieldPjn.setText("");
				textFieldPan.setText("");
			}
		});
		panelSouth.add(btnClear);
		
		JButton btnMainMenu = new JButton("Menu Principal");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				new MenuPrincipal();
				
			}
		});
		panelSouth.add(btnMainMenu);
		
		JButton btnRanking = new JButton("Ranking");
		btnRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					frame.dispose();
					new VentanaRanking();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		panelSouth.add(btnRanking);
		
		File file = new File("Ranking.txt");
		
		if (file.exists() == false) {
			btnRanking.setEnabled(false);
		}
		
		JPanel panelCenter = new JPanel();
		frame.getContentPane().add(panelCenter, BorderLayout.CENTER);
		GridBagLayout gbl_panelCenter = new GridBagLayout();
		gbl_panelCenter.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelCenter.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelCenter.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelCenter.rowWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panelCenter.setLayout(gbl_panelCenter);
		
		JLabel lblK = new JLabel("K:");
		lblK.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_lblK = new GridBagConstraints();
		gbc_lblK.gridwidth = 6;
		gbc_lblK.insets = new Insets(0, 0, 5, 5);
		gbc_lblK.gridx = 2;
		gbc_lblK.gridy = 0;
		panelCenter.add(lblK, gbc_lblK);
		
		textFieldK = new JTextField();
		GridBagConstraints gbc_textFieldK = new GridBagConstraints();
		gbc_textFieldK.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldK.gridwidth = 18;
		gbc_textFieldK.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldK.gridx = 8;
		gbc_textFieldK.gridy = 0;
		panelCenter.add(textFieldK, gbc_textFieldK);
		textFieldK.setColumns(10);
		
		JLabel lblAlpha = new JLabel("\u03B1:");
		lblAlpha.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_lblAlpha = new GridBagConstraints();
		gbc_lblAlpha.gridwidth = 6;
		gbc_lblAlpha.insets = new Insets(0, 0, 5, 5);
		gbc_lblAlpha.gridx = 2;
		gbc_lblAlpha.gridy = 3;
		panelCenter.add(lblAlpha, gbc_lblAlpha);
		
		textFieldAlpha = new JTextField();
		GridBagConstraints gbc_textFieldAlpha = new GridBagConstraints();
		gbc_textFieldAlpha.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAlpha.gridwidth = 19;
		gbc_textFieldAlpha.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldAlpha.gridx = 8;
		gbc_textFieldAlpha.gridy = 3;
		panelCenter.add(textFieldAlpha, gbc_textFieldAlpha);
		textFieldAlpha.setColumns(10);
		
		JLabel lblBeta = new JLabel("\u03B2:");
		lblBeta.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_lblBeta = new GridBagConstraints();
		gbc_lblBeta.gridwidth = 6;
		gbc_lblBeta.insets = new Insets(0, 0, 5, 5);
		gbc_lblBeta.gridx = 2;
		gbc_lblBeta.gridy = 6;
		panelCenter.add(lblBeta, gbc_lblBeta);
		
		textFieldBeta = new JTextField();
		GridBagConstraints gbc_textFieldBeta = new GridBagConstraints();
		gbc_textFieldBeta.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldBeta.gridwidth = 19;
		gbc_textFieldBeta.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldBeta.gridx = 8;
		gbc_textFieldBeta.gridy = 6;
		panelCenter.add(textFieldBeta, gbc_textFieldBeta);
		textFieldBeta.setColumns(10);
		
		JLabel lblPjn = new JLabel("Pjn");
		lblPjn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_lblPjn = new GridBagConstraints();
		gbc_lblPjn.insets = new Insets(0, 0, 5, 5);
		gbc_lblPjn.gridx = 12;
		gbc_lblPjn.gridy = 8;
		panelCenter.add(lblPjn, gbc_lblPjn);
		
		textFieldPjn = new JTextField();
		GridBagConstraints gbc_textFieldPjn = new GridBagConstraints();
		gbc_textFieldPjn.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPjn.gridwidth = 14;
		gbc_textFieldPjn.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPjn.gridx = 13;
		gbc_textFieldPjn.gridy = 8;
		panelCenter.add(textFieldPjn, gbc_textFieldPjn);
		textFieldPjn.setColumns(10);
		
		JLabel lblMatrix = new JLabel("Matriz Inicial:");
		lblMatrix.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_lblMatrix = new GridBagConstraints();
		gbc_lblMatrix.gridwidth = 6;
		gbc_lblMatrix.insets = new Insets(0, 0, 5, 5);
		gbc_lblMatrix.gridx = 2;
		gbc_lblMatrix.gridy = 9;
		panelCenter.add(lblMatrix, gbc_lblMatrix);
		
		JLabel lblPan = new JLabel("Pan");
		lblPan.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_lblPan = new GridBagConstraints();
		gbc_lblPan.insets = new Insets(0, 0, 5, 5);
		gbc_lblPan.gridx = 12;
		gbc_lblPan.gridy = 10;
		panelCenter.add(lblPan, gbc_lblPan);
		
		textFieldPan = new JTextField();
		GridBagConstraints gbc_textFieldPan = new GridBagConstraints();
		gbc_textFieldPan.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPan.gridwidth = 14;
		gbc_textFieldPan.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPan.gridx = 13;
		gbc_textFieldPan.gridy = 10;
		panelCenter.add(textFieldPan, gbc_textFieldPan);
		textFieldPan.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.gridwidth = 7;
		gbc_lblNombre.insets = new Insets(0, 0, 0, 5);
		gbc_lblNombre.gridx = 2;
		gbc_lblNombre.gridy = 13;
		panelCenter.add(lblNombre, gbc_lblNombre);
		
		textFieldNombre = new JTextField();
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldNombre.gridwidth = 15;
		gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombre.gridx = 12;
		gbc_textFieldNombre.gridy = 13;
		panelCenter.add(textFieldNombre, gbc_textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JButton btnAccept = new JButton("Aceptar");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textFieldK.getText().equals("") || textFieldAlpha.getText().equals("") || textFieldBeta.getText().equals("") ||textFieldPjn.getText().equals("") || textFieldPan.getText().equals("") || textFieldNombre.getText().equals("")) {
					
					infoBox("Rellena todos los campos", "Campos por rellenar");
					
				} else {
					
					try {
						Operation op = new Operation(textFieldK.getText(), textFieldAlpha.getText(),
							     textFieldBeta.getText(), textFieldPjn.getText(),
							     textFieldPan.getText(), textFieldNombre.getText());
						
						op.calculateToIteration();
						
						try {
						    new VentanaResultados();
						    frame.dispose();
					    } catch (Exception e1) {
					    	e1.printStackTrace();
					    }
					} catch (Exception e1) {
						infoBox("Utilize el formato correcto", "Error");
					}
				
				
				}
				
				
				
			}
		});
		panelSouth.add(btnAccept);
		
		
	}
	
	private void infoBox(String message, String title) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	}
}
