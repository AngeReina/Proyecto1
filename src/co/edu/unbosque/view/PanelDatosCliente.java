package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;

import co.edu.unbosque.model.enums.TIPO_CLIENTE;

public class PanelDatosCliente extends JPanel {

    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtTelefono;
    private JComboBox<TIPO_CLIENTE> comboTipo;

    public PanelDatosCliente() {
        setLayout(new GridLayout(5, 2, 10, 10));
        setBorder(BorderFactory.createTitledBorder("Datos del Cliente"));

        initComponents();
        addComponents();
    }

    private void initComponents() {
        txtId = new JTextField();
        txtNombre = new JTextField();
        txtTelefono = new JTextField();
        comboTipo = new JComboBox<>(TIPO_CLIENTE.values());
    }

    private void addComponents() {
        add(new JLabel("ID:"));
        add(txtId);

        add(new JLabel("Nombre:"));
        add(txtNombre);

        add(new JLabel("Telefono:"));
        add(txtTelefono);

        add(new JLabel("Tipo:"));
        add(comboTipo);
    }

    public int getId() {
        String texto = txtId.getText().trim();

        if (texto.isEmpty()) {
            return 0; 
        }

        try {
            return Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public String getNombre() {
        return txtNombre.getText().trim();
    }

    public String getTelefono() {
        return txtTelefono.getText().trim();
    }

    public String getTipo() {
        return comboTipo.getSelectedItem().toString();
    }

    public void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtTelefono.setText("");
        comboTipo.setSelectedIndex(0);
    }
}