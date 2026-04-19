package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;

import co.edu.unbosque.model.enums.CriterioCriticidad;
import co.edu.unbosque.model.enums.Zona;

public class PanelDatosSolicitud extends JPanel {
	
    private JTextField txtIdCliente;
    private JTextField txtDescripcion;
    private JComboBox<Zona> comboUbicacion;
    private JComboBox<CriterioCriticidad> comboTipo;

    public PanelDatosSolicitud() {
        setLayout(new GridLayout(5, 2, 10, 10));
        setBorder(BorderFactory.createTitledBorder("Datos de la solicitud"));

        initComponents();
        addComponents();
    }

    private void initComponents() {
    	txtIdCliente = new JTextField();
    	txtDescripcion = new JTextField();
    	comboUbicacion =  new JComboBox<>(Zona.values());
        comboTipo = new JComboBox<>(CriterioCriticidad.values());
    }

    private void addComponents() {
        add(new JLabel("Id cliente:"));
        add(txtIdCliente);

        add(new JLabel("Descripcion Incidente:"));
        add(txtDescripcion);

        add(new JLabel("Ubicacion:"));
        add(comboUbicacion);

        add(new JLabel("Criticidad:"));
        add(comboTipo);
    }

    public int getId() {
        String texto = txtIdCliente.getText().trim();

        if (texto.isEmpty()) {
            return 0; 
        }

        try {
            return Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public String getDescripcion() {
        return txtDescripcion.getText().trim();
    }

    public String getUbicacion() {
        return comboUbicacion.getSelectedItem().toString();
    }

    public String getTipo() {
        return comboTipo.getSelectedItem().toString();
    }

    public void limpiarCampos() {
    	txtIdCliente.setText("");
    	txtDescripcion.setText("");
        comboUbicacion.setSelectedIndex(0);
        comboTipo.setSelectedIndex(0);
    }


}