package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;

import co.edu.unbosque.utils.Constantes;

public class VistaPrincipal extends JFrame {

    private IComandosVista cmdListener;

    private JButton btnUnidad;
    private DialogoUnidad dialogoUnidad;
    private JButton btnTecnico;
    private DialogoTecnico dialogoTecnico;

    public VistaPrincipal(IComandosVista viewCmdListener) {
        this.cmdListener = viewCmdListener;

        setTitle("Sistema");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        initComponents();
        addComponents();
        setVisible(true);
    }

    private void initComponents() {
        btnUnidad = new JButton("Gestionar Unidad");
        btnUnidad.setActionCommand(Constantes.BTN_ABRIR_DIALOGO_UNIDAD);
        

        dialogoUnidad = new DialogoUnidad();
        dialogoUnidad.setListener(e -> cmdListener.ejecutarComando(e.getActionCommand()));
        
        btnTecnico = new JButton("Gestionar Técnico");
        btnTecnico.setActionCommand(Constantes.BTN_ABRIR_DIALOGO_TECNICO);

        dialogoTecnico = new DialogoTecnico();
        dialogoTecnico.setListener(e -> cmdListener.ejecutarComando(e.getActionCommand()));
    }

    private void addComponents() {
        add(btnUnidad);

        // Listener del botón principal
        btnUnidad.addActionListener(e -> 
            cmdListener.ejecutarComando(e.getActionCommand())
        );
        
        add(btnTecnico);

        btnTecnico.addActionListener(e -> 
            cmdListener.ejecutarComando(e.getActionCommand())
        );
    }

    // ---- MÉTODO PARA ABRIR ----
    public void abrirDialogoUnidad() {
        dialogoUnidad.setVisible(true);
    }

    // ---- GETTERS DEL DIALOGO ----
    public DialogoUnidad getDialogoUnidad() {
        return dialogoUnidad;
    }
    
    public void abrirDialogoTecnico() {
        dialogoTecnico.setVisible(true);
    }

    public DialogoTecnico getDialogoTecnico() {
        return dialogoTecnico;
    }
}