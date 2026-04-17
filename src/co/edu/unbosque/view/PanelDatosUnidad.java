package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;

import co.edu.unbosque.model.base.TIPO_VEHICULO;
import co.edu.unbosque.model.base.ESTADO_UNIDAD;

public class PanelDatosUnidad extends JPanel {

    private JLabel lblTipo;
    private JLabel lblEstado;
    private JLabel lblZona;

    private JComboBox<TIPO_VEHICULO> comboTipo;
    private JComboBox<ESTADO_UNIDAD> comboEstado;
    private JTextField txtZona;

    public PanelDatosUnidad() {
        setLayout(new GridLayout(3, 2, 10, 10));
        setBorder(BorderFactory.createTitledBorder("Datos de la Unidad"));

        initComponents();
        addComponents();
    }

    private void initComponents() {
        lblTipo = new JLabel("Tipo:");
        lblEstado = new JLabel("Estado:");
        lblZona = new JLabel("Zona:");

        comboTipo = new JComboBox<>(TIPO_VEHICULO.values());
        comboEstado = new JComboBox<>(ESTADO_UNIDAD.values());
        txtZona = new JTextField();
    }

    private void addComponents() {
        add(lblTipo);
        add(comboTipo);

        add(lblEstado);
        add(comboEstado);

        add(lblZona);
        add(txtZona);
    }

    // ---- GETTERS ----

    public TIPO_VEHICULO getTipo() {
        return (TIPO_VEHICULO) comboTipo.getSelectedItem();
    }

    public ESTADO_UNIDAD getEstado() {
        return (ESTADO_UNIDAD) comboEstado.getSelectedItem();
    }

    public String getZona() {
        return txtZona.getText().trim();
    }

    // ---- LIMPIAR ----

    public void limpiarCampos() {
        comboTipo.setSelectedIndex(0);
        comboEstado.setSelectedIndex(0);
        txtZona.setText("");
    }
}