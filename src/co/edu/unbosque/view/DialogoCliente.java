package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DialogoCliente extends JDialog {

    private PanelDatosCliente panelDatos;
    private PanelBotonesCliente panelBotones;

    public DialogoCliente() {
        setTitle("Gestión de Cliente");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setModal(true);
        setLayout(new BorderLayout());

        initComponents();
        addComponents();
    }

    private void initComponents() {
        panelDatos = new PanelDatosCliente();
        panelBotones = new PanelBotonesCliente();
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

    public String getTelefono() {
        return panelDatos.getTelefono();
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
