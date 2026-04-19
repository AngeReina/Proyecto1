package co.edu.unbosque.view;

import javax.swing.*;

import co.edu.unbosque.model.enums.ESTADO_UNIDAD;
import co.edu.unbosque.model.enums.TIPO_VEHICULO;

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

    public TIPO_VEHICULO getTipo() {
        return panelDatos.getTipo();
    }

    public ESTADO_UNIDAD getEstado() {
        return panelDatos.getEstado();
    }

    // ---- ACCIONES ----
    public void limpiarCampos() {
        panelDatos.limpiarCampos();
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public boolean datosValidos() {
        return getTipo() != null &&
            getEstado() != null &&
            getZona() != null &&
            !getZona().trim().isEmpty();
    }

    public Object[] pedirIdYEstado() {

        JTextField txtId = new JTextField();
        JComboBox<ESTADO_UNIDAD> comboEstado = new JComboBox<>(ESTADO_UNIDAD.values());

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("ID:"));
        panel.add(txtId);
        panel.add(new JLabel("Estado:"));
        panel.add(comboEstado);

        int result = JOptionPane.showConfirmDialog(
            this,
            panel,
            "Cambiar estado de unidad",
            JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {
            return new Object[] {
                txtId.getText().trim(),
                comboEstado.getSelectedItem()
            };
        }

        return null;
    }

    public String pedirZonaBusqueda() {
        return JOptionPane.showInputDialog(this, "Ingrese la zona a buscar:");
    }
}
