package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import co.edu.unbosque.utils.Constantes;


public class PanelBotonesUnidad extends JPanel {

    private JButton btnRegistrar;
    private JButton btnBuscar;
    private JButton btnLimpiar;
    private JButton btnCerrar;

    public PanelBotonesUnidad() {
        setLayout(new GridLayout(1, 4, 10, 10));

        initComponents();
        addComponents();
    }

    private void initComponents() {
        btnRegistrar = new JButton("Registrar");
        btnBuscar = new JButton("Buscar");
        btnLimpiar = new JButton("Limpiar");
        btnCerrar = new JButton("Cerrar");

        // ---- ACTION COMMANDS ----
        btnRegistrar.setActionCommand(Constantes.BTN_UNIDAD_REGISTRAR);
        btnBuscar.setActionCommand(Constantes.BTN_UNIDAD_BUSCAR);
        btnLimpiar.setActionCommand(Constantes.BTN_UNIDAD_LIMPIAR);
        btnCerrar.setActionCommand(Constantes.BTN_UNIDAD_CERRAR);
    }

    private void addComponents() {
        add(btnRegistrar);
        add(btnBuscar);
        add(btnLimpiar);
        add(btnCerrar);
    }

    // ---- LISTENER ----
    public void setListener(ActionListener listener) {
        btnRegistrar.addActionListener(listener);
        btnBuscar.addActionListener(listener);
        btnLimpiar.addActionListener(listener);
        btnCerrar.addActionListener(listener);
    }
}