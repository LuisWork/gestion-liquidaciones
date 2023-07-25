package cl.sprintzambrano.sprintzambrano.service;

import cl.sprintzambrano.sprintzambrano.entity.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
public interface IUsuarioService {
    List<Usuario> listarUsuarios ();
    Usuario crearUsuario(Usuario usuario);
    Usuario registrarUsuario(Usuario usuario);
    Usuario buscarUsuarioPorId(int idUsuario);
    Usuario actualizarUsuario(Usuario usuario, int idUsuario);
    public void eliminarUsuario2(int idUsuario);
}
