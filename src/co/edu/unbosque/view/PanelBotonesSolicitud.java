package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import co.edu.unbosque.utils.Constantes;

public class PanelBotonesSolicitud extends JPanel {

    private JButton btnRegistrar;
    private JButton btnAsignar;
    private JButton btnCompletar;
    private JButton btnReporte;
    private JButton btnCerrar;

    public PanelBotonesSolicitud() {
        setLayout(new GridLayout(1, 4, 10, 10));

        initComponents();
        addComponents();
    }

    private void initComponents() {
        btnRegistrar = new JButton("Registrar");
        btnAsignar = new JButton("Asignar");
        btnCompletar = new JButton("Completar");
        btnReporte = new JButton("Reporte");
        btnCerrar = new JButton("Cerrar");

        btnRegistrar.setActionCommand(Constantes.BTN_SOLICITUD_REGISTRAR);
        btnAsignar.setActionCommand(Constantes.BTN_SOLICITUD_ASIGNAR);
        btnCompletar.setActionCommand(Constantes.BTN_SOLICITUD_COMPLETAR);
        btnReporte.setActionCommand(Constantes.BTN_SOLICITUD_REPORTE);
        btnCerrar.setActionCommand(Constantes.BTN_SOLICITUD_CERRAR);
    }

    private void addComponents() {
        add(btnRegistrar);
        add(btnAsignar);
        add(btnCompletar);
        add(btnReporte);
        add(btnCerrar);
    }

    public void setListener(ActionListener listener) {
        btnRegistrar.addActionListener(listener);
        btnAsignar.addActionListener(listener);
        btnCompletar.addActionListener(listener);
        btnReporte.addActionListener(listener);
        btnCerrar.addActionListener(listener);
    }
}