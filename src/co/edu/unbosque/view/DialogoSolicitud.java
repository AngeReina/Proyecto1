package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DialogoSolicitud extends JDialog {

    private PanelDatosSolicitud panelDatos;
    private PanelBotonesSolicitud panelBotones;

    public DialogoSolicitud() {
        setTitle("Gestión de Solicitudes");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setModal(true);
        setLayout(new BorderLayout());

        initComponents();
        addComponents();
    }

    private void initComponents() {
        panelDatos = new PanelDatosSolicitud();
        panelBotones = new PanelBotonesSolicitud();
    }

    private void addComponents() {
        add(panelDatos, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    public void setListener(ActionListener listener) {
        panelBotones.setListener(listener);
    }

    // GETTERS
    public int getClienteId() {
        return panelDatos.getId();
    }

    public String getDescripcion() {
        return panelDatos.getDescripcion();
    }

    public String getUbicacion() {
        return panelDatos.getUbicacion();
    }

    public String getTipo() {
        return panelDatos.getTipo();
    }

    public void limpiarCampos() {
        panelDatos.limpiarCampos();
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
