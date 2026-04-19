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
    private JButton btnCliente;
    private DialogoCliente dialogoCliente;
    private JButton btnKit;
    private DialogoKit dialogoKit;
    
    private JButton btnSolicitud;
    private DialogoSolicitud dialogoSolicitud;

    public VistaPrincipal(IComandosVista viewCmdListener) {
        this.cmdListener = viewCmdListener;

        setTitle("Sistema");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        initComponents();
        addComponents();
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
        
        btnCliente = new JButton("Gestionar Clientes");
        btnCliente.setActionCommand(Constantes.BTN_ABRIR_DIALOGO_CLIENTE);

        dialogoCliente = new DialogoCliente();
        dialogoCliente.setListener(e -> cmdListener.ejecutarComando(e.getActionCommand()));
        
        btnKit = new JButton("Gestionar Kits");
        btnKit.setActionCommand(Constantes.BTN_ABRIR_DIALOGO_KITS);
        
        dialogoKit = new DialogoKit();
        dialogoKit.setListener(e -> cmdListener.ejecutarComando(e.getActionCommand()));
        
        btnSolicitud = new JButton("Gestionar Solicitudes");
        btnSolicitud.setActionCommand(Constantes.BTN_ABRIR_DIALOGO_KITS);
        
        dialogoSolicitud = new DialogoSolicitud();
        dialogoSolicitud.setListener(e -> cmdListener.ejecutarComando(e.getActionCommand()));
 
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
        
        add(btnCliente);

        btnCliente.addActionListener(e -> 
            cmdListener.ejecutarComando(e.getActionCommand())
        );
        
        add(btnKit);

        btnKit.addActionListener(e -> 
            cmdListener.ejecutarComando(e.getActionCommand())
        );
        
        add(btnSolicitud);

        btnSolicitud.addActionListener(e -> 
            cmdListener.ejecutarComando(e.getActionCommand())
        );
    }
    
    public void abrirVista() {
        setVisible(true);
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
    
    public void abrirDialogoCliente() {
        dialogoCliente.setVisible(true);
    }

    public DialogoCliente getDialogoCliente() {
        return dialogoCliente;
    }
    
    public void abrirDialogoKit() {
    	dialogoKit.setVisible(true);
    }

    public DialogoKit getDialogoKit() {
        return dialogoKit;
    }
    
    public void abrirDialogoSolicitud() {
    	dialogoSolicitud.setVisible(true);
    }

    public DialogoSolicitud getDialogoSolicitud() {
        return dialogoSolicitud;
    }
}