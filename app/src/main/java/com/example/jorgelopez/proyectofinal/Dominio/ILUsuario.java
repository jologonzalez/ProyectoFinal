package com.example.jorgelopez.proyectofinal.Dominio;

import com.example.jorgelopez.proyectofinal.Modelo.Usuario;

/**
 * Created by Jorge Lopez on 24/10/2017.
 */

public interface ILUsuario {

    void crearUsuario(Usuario usuario, String password, CallBackInteractor<String> callBackInteractor);

    void authUsuario(String email, String password, CallBackInteractor<String> callBackInteractor);

    void cargarDatos(String nombre, int edad, long celular, CallBackInteractor<String> callBackInteractor);

    void guardarDatos(String nombre, int edad, long celular, CallBackInteractor<String> callBackInteractor);

}
