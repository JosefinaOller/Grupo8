package vistaMVC;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;

public class ventanaPagar extends JFrame implements KeyListener,MouseListener {

	private JPanel contentPane;
	private JTextField nombretextField;
	private final ButtonGroup tipoPagoGroup = new ButtonGroup();
	private JLabel nombreLabel;
	private JLabel tipoPagoLabel  ;
	private JRadioButton efectivoRadioButton;
	private JRadioButton chequeRadioButton;
	private JRadioButton tarjetaRadioButton;
	private JButton pagarButton ;
	private ActionListener actionlistener;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private JLabel mesLabel;
	private JTextField mesTextField;

	
	public ventanaPagar() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 390, 384);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(7, 1, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		
		nombreLabel = new JLabel("Nombre:");
		nombreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(nombreLabel);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
		nombretextField = new JTextField();
		panel_2.add(nombretextField);
		nombretextField.setColumns(15);
		
		panel_4 = new JPanel();
		contentPane.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		panel_5 = new JPanel();
		panel_4.add(panel_5);
		
		mesLabel = new JLabel("Mes:");
		panel_5.add(mesLabel);
		
		panel_6 = new JPanel();
		panel_4.add(panel_6);
		
		mesTextField = new JTextField();
		panel_6.add(mesTextField);
		mesTextField.setColumns(5);
		
		tipoPagoLabel = new JLabel("Seleccione el tipo de pago:");
		tipoPagoLabel.setEnabled(false);
		contentPane.add(tipoPagoLabel);
		
		efectivoRadioButton = new JRadioButton("Efectivo");
		efectivoRadioButton.setEnabled(false);
		tipoPagoGroup.add(efectivoRadioButton);
		contentPane.add(efectivoRadioButton);
		
		chequeRadioButton = new JRadioButton("Cheque");
		chequeRadioButton.setEnabled(false);
		tipoPagoGroup.add(chequeRadioButton);
		contentPane.add(chequeRadioButton);
		
		tarjetaRadioButton = new JRadioButton("Tarjeta");
		tarjetaRadioButton.setEnabled(false);
		tipoPagoGroup.add(tarjetaRadioButton);
		contentPane.add(tarjetaRadioButton);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		
		pagarButton = new JButton("Realizar Pago");
		pagarButton.setEnabled(false);
		panel_3.add(pagarButton);
		this.pagarButton.setActionCommand("PAGAR");
		
		this.nombretextField.addKeyListener(this);
		this.mesTextField.addKeyListener(this);
		this.chequeRadioButton.addMouseListener(this);
		this.efectivoRadioButton.addMouseListener(this);
		this.tarjetaRadioButton.addMouseListener(this);
		
		this.setVisible(true);
	}


	public void mouseClicked(MouseEvent arg0) {
		boolean res= this.chequeRadioButton.isSelected() || this.efectivoRadioButton.isSelected() || this.tarjetaRadioButton.isSelected();
		if (arg0.getButton()==1) {
			this.pagarButton.setEnabled(res && !this.nombretextField.getText().equals(""));
		}
	}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	
	public void keyPressed(KeyEvent arg0) {}
	public void keyReleased(KeyEvent arg0) {
		String nombre =this.nombretextField.getText();
		int mes=0;
		try {
			mes= Integer.parseInt(this.mesTextField.getText());
		}
		catch(NumberFormatException e) {}
		boolean res= !nombre.equals("") && (mes>=1 && mes<=12);
		this.tipoPagoLabel.setEnabled(res);
		this.chequeRadioButton.setEnabled(res);
		this.efectivoRadioButton.setEnabled(res);
		this.tarjetaRadioButton.setEnabled(res);
		boolean res2= this.chequeRadioButton.isSelected() || this.efectivoRadioButton.isSelected() || this.tarjetaRadioButton.isSelected();
		this.pagarButton.setEnabled(res && res2);
	}
	public void keyTyped(KeyEvent arg0) {}


	public void setActionlistener(ActionListener actionlistener) {
		this.pagarButton.addActionListener(actionlistener);
		this.actionlistener= actionlistener;
	}
	
	public String getNombre() {
		return this.nombretextField.getText();
	}
	
	public int getMes() {
		return Integer.parseInt(this.mesTextField.getText());
	}
	
	public boolean efectivoPago() {
		return this.efectivoRadioButton.isSelected();
	}
	
	public boolean chequePago() {
		return this.chequeRadioButton.isSelected();
	}
	
	public boolean tarjetaPago() {
		return this.tarjetaRadioButton.isSelected();
	}

}
