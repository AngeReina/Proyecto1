package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DialogoKit extends JDialog {

    private PanelDatosKits panelDatos;
    private PanelBotonesKits panelBotones;

    public DialogoKit() {
        setTitle("Gestión de Kits");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setModal(true);
        setLayout(new BorderLayout());

        initComponents();
        addComponents();
    }

    private void initComponents() {
        panelDatos = new PanelDatosKits();
        panelBotones = new PanelBotonesKits();
    }

    private void addComponents() {
        add(panelDatos, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    public void setListener(ActionListener listener) {
        panelBotones.setListener(listener);
    }

    // GETTERS
    
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
