package EcuacionesLineales;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;

import java.awt.AWTException;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import MenuPrincipal.MenuPrincipal;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Robot;

import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class EcuacionesVentana {

	private JFrame frame;
	private JTable table;
	Solver s;


	/**
	 * Create the application.
	 */
	public EcuacionesVentana() {
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension acceptButtonSize = new Dimension(120,50);
		
		double windowWidth = screenSize.getWidth()/3;
		double windowHeight = screenSize.getHeight()/5;
		frame = new JFrame("Ecuaciones Lineales");
		frame.setBounds(100, 100, (int) windowWidth, (int) windowHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		//frame.setResizable(false);
		
		JLabel lblTitle = new JLabel("Introduzca los datos del Sistema de Ecuaciones");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblTitle, BorderLayout.NORTH);
		
		JPanel panelSouth = new JPanel();
		frame.getContentPane().add(panelSouth, BorderLayout.SOUTH);
		panelSouth.setLayout(new GridLayout(1, 0, 0, 0));
		panelSouth.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()/6));
		
		JButton btnBk = new JButton("Atras");
		btnBk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new MenuPrincipal();
			}
		});
		panelSouth.add(btnBk);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int row = 0; row < table.getRowCount(); row++) {
					for (int col = 0; col < table.getColumnCount(); col++) {
						table.setValueAt(null, row, col);
					}
				}
				
			}
		});
		panelSouth.add(btnClear);
		
		JPanel panelCenter = new JPanel();
		frame.getContentPane().add(panelCenter, BorderLayout.CENTER);
		
		String[] headings = {"x", "y", "z", "="};
		int rowCount = 3;
		
        DefaultTableModel model = new DefaultTableModel(rowCount, headings.length);
        model.setColumnIdentifiers(headings);
		panelCenter.setLayout(new GridLayout(0, 2, 0, 0));
		table = new JTable(model);
		
		JScrollPane scrollPane = new JScrollPane();
		JPanel panelTable = new JPanel();
		panelTable.setLayout(new BorderLayout());
		scrollPane.setViewportBorder(new CompoundBorder());
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		table.setRowHeight(40);
		//table.setSize(width, height);
		scrollPane.add(table);
		scrollPane.setViewportView(table);
		panelTable.add(scrollPane, BorderLayout.CENTER);
		panelCenter.add(panelTable);
		table.setSize(scrollPane.getSize());
		
		JButton btnAccept = new JButton("Aceptar");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				s = new Solver(table);
				s.buildAllMatrix();
				new ResultadosVentana(s.getCompatibility(), s.getX(), s.getY(), s.getZ(), s.getFullMatrix(), s.getDet(), s.getX(), s.getY(), s.getZ(), s.getCompatible());
				frame.dispose();
				
				
			}
		});
		btnAccept.setPreferredSize(acceptButtonSize);
		panelCenter.add(btnAccept);
		table.getTableHeader().setReorderingAllowed(false);
		
		frame.pack();
	}
}
