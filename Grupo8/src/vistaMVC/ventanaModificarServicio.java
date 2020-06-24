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
import javax.swing.JTextField;
import javax.swing.JToolBar;
import java.awt.Choice;
import javax.swing.JButton;

public class ventanaModificarServicio extends JFrame implements KeyListener, MouseListener{

	private JPanel contentPane;
	private JTextField nombreTextField;
	private JTextField calleTextField;
	private JLabel accionLabel ;
	private JLabel packLabel ;
	private Choice opcionesAccion; 
	private Choice opcionesPack;
	private JButton aceptarBoton;
	private JTextField alturaTextField;
	private ActionListener actionlistener;
	
	
	public ventanaModificarServicio() {
		setTitle("Modificar Servicio");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 470, 317);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(6, 1, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		
		JLabel nombreLabel = new JLabel("Ingrese Nombre:");
		panel_1.add(nombreLabel);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
		nombreTextField = new JTextField();
		panel_2.add(nombreTextField);
		nombreTextField.setColumns(17);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4);
		
		JLabel calleLabel = new JLabel("Ingrese Calle:");
		panel_4.add(calleLabel);
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5);
		
		calleTextField = new JTextField();
		panel_5.add(calleTextField);
		calleTextField.setColumns(10);
		
		JPanel panel_13 = new JPanel();
		contentPane.add(panel_13);
		panel_13.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_14 = new JPanel();
		panel_13.add(panel_14);
		
		JLabel alturaLabel = new JLabel("Ingrese Altura:");
		panel_14.add(alturaLabel);
		
		JPanel panel_15 = new JPanel();
		panel_13.add(panel_15);
		
		alturaTextField = new JTextField();
		alturaTextField.setEnabled(true);
		alturaTextField.setEditable(true);
		alturaTextField.setText("");
		panel_15.add(alturaTextField);
		alturaTextField.setColumns(10);
		
		JPanel panel_6 = new JPanel();
		contentPane.add(panel_6);
		panel_6.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_6.add(panel_7);
		
		accionLabel = new JLabel("Accion:");
		accionLabel.setEnabled(false);
		panel_7.add(accionLabel);
		
		JPanel panel_8 = new JPanel();
		panel_6.add(panel_8);
		
		opcionesAccion = new Choice();
		opcionesAccion.setEnabled(false);
		panel_8.add(opcionesAccion);
		opcionesAccion.setSize(400, 400);
		
		JPanel panel_9 = new JPanel();
		contentPane.add(panel_9);
		panel_9.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_10 = new JPanel();
		panel_9.add(panel_10);
		
		packLabel = new JLabel("Pack:");
		packLabel.setEnabled(false);
		panel_10.add(packLabel);
		
		JPanel panel_11 = new JPanel();
		panel_9.add(panel_11);
		
		opcionesPack = new Choice();
		opcionesPack.setEnabled(false);
		panel_11.add(opcionesPack);
		opcionesPack.add("Seleccione una opcion");
		
		JPanel panel_12 = new JPanel();
		contentPane.add(panel_12);
		
		aceptarBoton = new JButton("Aceptar");
		aceptarBoton.setEnabled(false);
		panel_12.add(aceptarBoton);
		
		opcionesAccion.add("Seleccione una opcion");
		opcionesAccion.add("Agregar");
		opcionesAccion.add("Quitar");
		opcionesAccion.add("Cambiar");
		
		this.calleTextField.addKeyListener(this);
		this.nombreTextField.addKeyListener(this);
		this.alturaTextField.addKeyListener(this);
		
		this.opcionesAccion.addMouseListener(this);
		this.opcionesPack.addMouseListener(this);
		
		this.aceptarBoton.setActionCommand("MODIFICAR SERVICIO");
		
		this.setVisible(true);
	}


	public void mouseClicked(MouseEvent arg0) {
		
	}


	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}

	public void mousePressed(MouseEvent arg0) {
		if (arg0.getButton()==1) {
			String accionSeleccionada=this.opcionesAccion.getItem(this.opcionesAccion.getSelectedIndex());
			this.packLabel.setEnabled(!accionSeleccionada.equals("Seleccione una opcion"));
			this.opcionesPack.setEnabled(!accionSeleccionada.equals("Seleccione una opcion"));
			if (!accionSeleccionada.equals("Seleccione una opcion")) {
				String packSeleccionado= this.opcionesPack.getItem(this.opcionesPack.getSelectedIndex());
				boolean res= !packSeleccionado.equals("Seleccione una opcion");
				this.aceptarBoton.setEnabled(res);
				if (!res) {
					this.opcionesPack.removeAll();
					this.opcionesPack.add("Seleccione una opcion");
					if (accionSeleccionada.equals("Cambiar")) {
						this.opcionesPack.add("Internet100");
						this.opcionesPack.add("Internet500");
					}
					else {
						this.opcionesPack.add("TvCable");
						this.opcionesPack.add("Telefono Fijo");
						this.opcionesPack.add("Celular");
					}
				}
			}
		}
	}


	public void mouseReleased(MouseEvent arg0) {}


	public void keyPressed(KeyEvent arg0) {}


	public void keyReleased(KeyEvent arg0) {
		String nombre= this.nombreTextField.getText();
		String calle = this.calleTextField.getText();
		int altura=-1;
		try {
			altura= Integer.parseInt(this.alturaTextField.getText());
		}
		catch(NumberFormatException e) {}
		
		boolean res= (!nombre.equals("") && !calle.equals("") && altura>0);
		this.accionLabel.setEnabled(res);
		this.opcionesAccion.setEnabled(res);
	}


	public void keyTyped(KeyEvent arg0) {}


	public void setActionlistener(ActionListener actionlistener) {
		this.aceptarBoton.addActionListener(actionlistener);
		this.actionlistener = actionlistener;
	}
	
	public String getNombre() {
		return this.nombreTextField.getText();
	}
	
	public String getCalle() {
		return this.calleTextField.getText();
	}
	
	public int getAltura() {
		return Integer.parseInt(this.alturaTextField.getText());
	}
	
	public String getAccion() {
		return this.opcionesAccion.getItem(this.opcionesAccion.getSelectedIndex());
	}
	
	public String getPack() {
		return this.opcionesPack.getItem(this.opcionesPack.getSelectedIndex());
	}
	
	

}
