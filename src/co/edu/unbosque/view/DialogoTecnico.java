package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DialogoTecnico extends JDialog {

    private PanelDatosTecnico panelDatos;
    private PanelBotonesTecnico panelBotones;

    public DialogoTecnico() {
        setTitle("Gestión de Técnico");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setModal(true);
        setLayout(new BorderLayout());

        initComponents();
        addComponents();
    }

    private void initComponents() {
        panelDatos = new PanelDatosTecnico();
        panelBotones = new PanelBotonesTecnico();
    }

    private void addComponents() {
        add(panelDatos, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    public void setListener(ActionListener listener) {
        panelBotones.setListener(listener);
    }

    // GETTERS
    public int getId() {
        return panelDatos.getId();
    }

    public String getNombre() {
        return panelDatos.getNombre();
    }

    public String getEspecialidad() {
        return panelDatos.getEspecialidad();
    }

    public String getEstado() {
        return panelDatos.getEstado();
    }

    public String getZona() {
        return panelDatos.getZona();
    }

    public void limpiarCampos() {
        panelDatos.limpiarCampos();
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
