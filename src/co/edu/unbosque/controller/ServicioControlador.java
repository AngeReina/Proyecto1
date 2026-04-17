package co.edu.unbosque.controller;

import co.edu.unbosque.view.*;
import co.edu.unbosque.utils.Constantes;
import co.edu.unbosque.model.base.TIPO_VEHICULO;
import co.edu.unbosque.model.base.ESTADO_UNIDAD;
import co.edu.unbosque.model.base.unidad.UnidadDTO;

public class ServicioControlador implements IComandosVista {

    private VistaPrincipal vista;
    private UnidadServicio unidadServicio;

    public ServicioControlador() {
        unidadServicio = new UnidadServicio();
        vista = new VistaPrincipal(this); // 👈 AQUÍ conectas todo
    }

    @Override
    public void ejecutarComando(String comando) {

        switch (comando) {

            case Constantes.BTN_ABRIR_DIALOGO_UNIDAD:
                vista.abrirDialogoUnidad();
                break;

            case Constantes.BTN_UNIDAD_REGISTRAR:

                TIPO_VEHICULO tipo = (TIPO_VEHICULO) vista.getDialogoUnidad().getTipo();
                ESTADO_UNIDAD estado = (ESTADO_UNIDAD) vista.getDialogoUnidad().getEstado();
                String zona = vista.getDialogoUnidad().getZona();

                unidadServicio.registrarUnidad(tipo, estado, zona);

                vista.getDialogoUnidad().mostrarMensaje("Unidad registrada");
                vista.getDialogoUnidad().limpiarCampos();
                break;

            case Constantes.BTN_UNIDAD_BUSCAR:

                String zonaBuscar = vista.getDialogoUnidad().getZona();

                UnidadDTO u = unidadServicio.buscarDisponible(zonaBuscar);

                if (u != null) {
                    vista.getDialogoUnidad().mostrarMensaje(
                        "Encontrada: " + u.getTipo() + " - " + u.getEstado()
                    );
                } else {
                    vista.getDialogoUnidad().mostrarMensaje("No hay unidades disponibles");
                }
                break;

            case Constantes.BTN_UNIDAD_LIMPIAR:
                vista.getDialogoUnidad().limpiarCampos();
                break;

            case Constantes.BTN_UNIDAD_CERRAR:
                vista.getDialogoUnidad().setVisible(false);
                break;
        }
    }
}