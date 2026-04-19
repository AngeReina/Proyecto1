package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;

import co.edu.unbosque.model.enums.TIPO_VEHICULO;
import co.edu.unbosque.model.enums.ESTADO_UNIDAD;
import co.edu.unbosque.model.enums.Zona;

public class PanelDatosUnidad extends JPanel {

    private JLabel lblTipo;
    private JLabel lblEstado;
    private JLabel lblZona;



    private JComboBox<TIPO_VEHICULO> comboTipo;
    private JComboBox<ESTADO_UNIDAD> comboEstado;
    private JComboBox<Zona> comboZona;

    public PanelDatosUnidad() {
        setLayout(new GridLayout(4, 2, 10, 10));
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
        comboZona = new JComboBox<>(Zona.values());
    }

    private void addComponents() {
        add(lblTipo);
        add(comboTipo);

        add(lblEstado);
        add(comboEstado);

        add(lblZona);
        add(comboZona);
        

    }

    // ---- GETTERS ----

    public TIPO_VEHICULO getTipo() {
        return (TIPO_VEHICULO) comboTipo.getSelectedItem();
    }

    public ESTADO_UNIDAD getEstado() {
        return (ESTADO_UNIDAD) comboEstado.getSelectedItem();
    }

    public Zona getZona() {
        return (Zona) comboZona.getSelectedItem();
    }


    // ---- LIMPIAR ----

    public void limpiarCampos() {
        comboTipo.setSelectedIndex(0);
        comboEstado.setSelectedIndex(0);
        comboZona.setSelectedIndex(0);
    }
}