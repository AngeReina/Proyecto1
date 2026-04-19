package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;

import co.edu.unbosque.model.enums.TIPO_CLIENTE;

public class PanelDatosKits extends JPanel {

    private JTextField txtTipo;

    public PanelDatosKits() {
        setLayout(new GridLayout(5, 2, 10, 10));
        setBorder(BorderFactory.createTitledBorder("Datos del Kit"));

        initComponents();
        addComponents();
    }

    private void initComponents() {
    	txtTipo = new JTextField();
    }

    private void addComponents() {
        add(new JLabel("TIPO:"));
        add(txtTipo);
    }
    
    public String getTipo() {
    	return txtTipo.getText();
    }

    public void limpiarCampos() {
    	txtTipo.setText("");
    }
}