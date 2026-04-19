package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import co.edu.unbosque.utils.Constantes;

public class PanelBotonesKits extends JPanel {

    private JButton btnRegistrar;
    private JButton btnRevisar;
    private JButton btnLimpiar;
    private JButton btnCerrar;

    public PanelBotonesKits() {
        setLayout(new GridLayout(1, 4, 10, 10));

        initComponents();
        addComponents();
    }

    private void initComponents() {
        btnRegistrar = new JButton("Registrar");
        btnRevisar = new JButton("Revisar");
        btnLimpiar = new JButton("Limpiar");
        btnCerrar = new JButton("Cerrar");

        btnRegistrar.setActionCommand(Constantes.BTN_KITS_REGISTRAR);
        btnRevisar.setActionCommand(Constantes.BTN_KITS_REVISAR);
        btnLimpiar.setActionCommand(Constantes.BTN_KIT_LIMPIAR);
        btnCerrar.setActionCommand(Constantes.BTN_KIT_CERRAR);
    }

    private void addComponents() {
        add(btnRegistrar);
        add(btnRevisar);
        add(btnLimpiar);
        add(btnCerrar);
    }

    public void setListener(ActionListener listener) {
        btnRegistrar.addActionListener(listener);
        btnRevisar.addActionListener(listener);
        btnLimpiar.addActionListener(listener);
        btnCerrar.addActionListener(listener);
    }
}