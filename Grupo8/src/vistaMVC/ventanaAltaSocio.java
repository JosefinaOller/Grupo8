package vistaMVC;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ventanaAltaSocio extends JFrame implements KeyListener,MouseListener {

	private JPanel contentPane;
	private JTextField nombreTextField;
	private final ButtonGroup seleccionRadioButton = new ButtonGroup();
	private JTextField dniTextField;
	private JTextField cuitTextField;
	private JRadioButton juridicaRadioButton;
	private JRadioButton fisicaRadioButton ;
	private  JLabel nombreLabel;
	private  JLabel seleccionLabel;
	private JLabel dniLabel;
	private  JLabel cuitLabel;
	private  JButton aceptarBoton;
	private ActionListener actionlistener;
	
	
	public ventanaAltaSocio() {
		setTitle("Agregar Socio");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 312, 339);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(7, 1, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		
		seleccionLabel = new JLabel("Seleccione un tipo de persona:");
		contentPane.add(seleccionLabel);
		
		fisicaRadioButton = new JRadioButton("Fisica");
		seleccionRadioButton.add(fisicaRadioButton);
		contentPane.add(fisicaRadioButton);
		
		juridicaRadioButton = new JRadioButton("Juridica");
		seleccionRadioButton.add(juridicaRadioButton);
		contentPane.add(juridicaRadioButton);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		
		nombreLabel = new JLabel("Ingrese Nombre:");
		panel_4.add(nombreLabel);
		
		JPanel panel_5 = new JPanel();
		panel.add(panel_5);
		
		nombreTextField = new JTextField();
		panel_5.add(nombreTextField);
		nombreTextField.setColumns(10);
		
		JPanel panel_6 = new JPanel();
		panel_1.add(panel_6);
		
		dniLabel = new JLabel("Ingrese DNI:");
		dniLabel.setEnabled(false);
		panel_6.add(dniLabel);
		
		JPanel panel_7 = new JPanel();
		panel_1.add(panel_7);
		
		dniTextField = new JTextField();
		dniTextField.setEnabled(false);
		panel_7.add(dniTextField);
		dniTextField.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_8 = new JPanel();
		panel_2.add(panel_8);
		
		cuitLabel = new JLabel("Ingrese CUIT:");
		cuitLabel.setEnabled(false);
		panel_8.add(cuitLabel);
		
		JPanel panel_9 = new JPanel();
		panel_2.add(panel_9);
		
		cuitTextField = new JTextField();
		cuitTextField.setEnabled(false);
		panel_9.add(cuitTextField);
		cuitTextField.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		
		aceptarBoton = new JButton("Aceptar");
		aceptarBoton.setEnabled(false);
		panel_3.add(aceptarBoton);
		this.aceptarBoton.setActionCommand("AGREGAR SOCIO");
		
		this.setVisible(true);
		
		this.fisicaRadioButton.addMouseListener(this);
		this.juridicaRadioButton.addMouseListener(this);
		this.cuitTextField.addKeyListener(this);
		this.dniTextField.addKeyListener(this);
		this.nombreTextField.addKeyListener(this);
		
		
	}


	public void keyPressed(KeyEvent arg0) {}


	public void keyReleased(KeyEvent arg0) {
		boolean res;
		int dni=-1,cuit=-1;
		String nombre= this.nombreTextField.getText();
		res=(nombre!=null && !nombre.equals("") );
		
		this.dniLabel.setEnabled(this.fisicaRadioButton.isSelected() && res );
		this.dniTextField.setEnabled(this.fisicaRadioButton.isSelected() && res);
		if ( !this.dniTextField.isEnabled())
			 this.dniTextField.setText("0");
		this.cuitLabel.setEnabled(this.juridicaRadioButton.isSelected() &&  res);
		this.cuitTextField.setEnabled(this.juridicaRadioButton.isSelected() &&  res);
		if (!this.cuitTextField.isEnabled())
			 this.cuitTextField.setText("0");
		try {
			dni=Integer.parseInt(this.dniTextField.getText());
			cuit=Integer.parseInt(this.cuitTextField.getText());
			
		}
		catch(NumberFormatException e) {}
		
		res= res && (dni>0 || cuit>0);
		this.aceptarBoton.setEnabled(res);
		
		
	}

	public void keyTyped(KeyEvent arg0) {}


	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getButton()==1) {
			boolean res1= (!this.nombreTextField.getText().equals("") && this.fisicaRadioButton.isSelected());
			boolean res2= (!this.nombreTextField.getText().equals("") && this.juridicaRadioButton.isSelected());
			this.dniTextField.setEnabled(res1);
			this.dniLabel.setEnabled(res1);
			this.cuitLabel.setEnabled(res2);
			this.cuitTextField.setEnabled(res2);
			int dni=-1,cuit=-1;
			try {
			dni=Integer.parseInt(this.dniTextField.getText());
			}
			catch(NumberFormatException e) {
				try {
				cuit= Integer.parseInt(this.cuitTextField.getText());
				}
				catch(NumberFormatException e1) {}
				this.aceptarBoton.setEnabled(res2 && cuit>0); 
			}
			
			this.aceptarBoton.setEnabled(res1 && dni>0); 
			
		}
	}


	public void mouseEntered(MouseEvent arg0) {}


	public void mouseExited(MouseEvent arg0) {}


	public void mousePressed(MouseEvent arg0) {}


	public void mouseReleased(MouseEvent arg0) {}


	public void setActionlistener(ActionListener actionlistener) {
		this.aceptarBoton.addActionListener(actionlistener);
		this.actionlistener = actionlistener;
	}
	
	public String getNombre() {
		return this.nombreTextField.getText();
	}
	
	public int getDNI() {
		return Integer.parseInt(this.dniTextField.getText());
	}
	
	public int getCUIT() {
		return Integer.parseInt(this.cuitTextField.getText());
	}
	
	public boolean getFisica() {
		return this.fisicaRadioButton.isSelected();
	}
	
	public boolean getJuridica() {
		return this.juridicaRadioButton.isSelected();
	}

}
