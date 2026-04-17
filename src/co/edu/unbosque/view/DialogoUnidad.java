package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DialogoUnidad extends JDialog {

    private PanelDatosUnidad panelDatos;
    private PanelBotonesUnidad panelBotones;

    public DialogoUnidad() {
        setTitle("Gestión de Unidad");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setModal(true);
        setLayout(new BorderLayout());

        initComponents();
        addComponents();
    }

    private void initComponents() {
        panelDatos = new PanelDatosUnidad();
        panelBotones = new PanelBotonesUnidad();
    }

    private void addComponents() {
        add(panelDatos, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    // ---- LISTENER ----
    public void setListener(ActionListener listener) {
        panelBotones.setListener(listener);
    }

    // ---- GETTERS (para el controller) ----
    public String getZona() {
        return panelDatos.getZona();
    }

    public Object getTipo() {
        return panelDatos.getTipo();
    }

    public Object getEstado() {
        return panelDatos.getEstado();
    }

    // ---- ACCIONES ----
    public void limpiarCampos() {
        panelDatos.limpiarCampos();
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}