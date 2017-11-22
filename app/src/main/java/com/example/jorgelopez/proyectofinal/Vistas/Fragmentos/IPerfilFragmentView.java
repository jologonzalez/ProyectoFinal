package com.example.jorgelopez.proyectofinal.Vistas.Fragmentos;


/**
 * Created by Jorge Lopez on 21/11/2017.
 */

public interface IPerfilFragmentView {

    void habilitarVistas();
    void deshabilitarVistas();
    void guardarDatos();
    void cargarDatos();
    void mostrarError(String mensaje);

}
