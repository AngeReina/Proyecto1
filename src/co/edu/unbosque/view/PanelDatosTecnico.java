package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;

import co.edu.unbosque.model.enums.EstadoTecnico;
import co.edu.unbosque.model.enums.ZonaTecnico;

public class PanelDatosTecnico extends JPanel {

    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtEspecialidad;
    private JComboBox<EstadoTecnico> comboEstado;
    private JComboBox<ZonaTecnico> comboZona;

    public PanelDatosTecnico() {
        setLayout(new GridLayout(5, 2, 10, 10));
        setBorder(BorderFactory.createTitledBorder("Datos del Técnico"));

        initComponents();
        addComponents();
    }

    private void initComponents() {
        txtId = new JTextField();
        txtNombre = new JTextField();
        txtEspecialidad = new JTextField();
        comboEstado = new JComboBox<>(EstadoTecnico.values());
        comboZona = new JComboBox<>(ZonaTecnico.values());
    }

    private void addComponents() {
        add(new JLabel("ID:"));
        add(txtId);

        add(new JLabel("Nombre:"));
        add(txtNombre);

        add(new JLabel("Especialidad:"));
        add(txtEspecialidad);

        add(new JLabel("Estado:"));
        add(comboEstado);

        add(new JLabel("Zona:"));
        add(comboZona);
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

    public String getEspecialidad() {
        return txtEspecialidad.getText().trim();
    }

    public String getEstado() {
        return comboEstado.getSelectedItem().toString();
    }

    public String getZona() {
        return comboZona.getSelectedItem().toString();
    }

    public void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtEspecialidad.setText("");
        comboEstado.setSelectedIndex(0);
        comboZona.setSelectedIndex(0);
    }
}