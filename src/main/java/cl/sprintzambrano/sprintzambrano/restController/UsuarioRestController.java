package cl.sprintzambrano.sprintzambrano.restController;

import cl.sprintzambrano.sprintzambrano.entity.Usuario;
import cl.sprintzambrano.sprintzambrano.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioRestController {
    @Autowired
    IUsuarioService objUsuarioService;

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) { return objUsuarioService.crearUsuario(usuario); }

    @PostMapping("/registrar")
    public Usuario registrarUsuario(@RequestBody Usuario usuario) {
        return objUsuarioService.registrarUsuario(usuario);
    }

    @GetMapping("/{idUsuario}")
    public Usuario buscarUsuarioPorId(@PathVariable int idUsuario){
        return objUsuarioService.buscarUsuarioPorId(idUsuario);
    }

    @GetMapping
    public List<Usuario> listarUsuario(){ return objUsuarioService.listarUsuarios(); }

    @PutMapping("/{idUsuario}")
    public Usuario actualizarUsuario(@RequestBody Usuario usuarioActualizar, @PathVariable int idUsuario){
        return objUsuarioService.actualizarUsuario(usuarioActualizar, idUsuario);
    }

    @DeleteMapping
    public void eliminarUsuario(@RequestBody Usuario usuario) { objUsuarioService.eliminarUsuario(usuario); }

    @DeleteMapping("/{idUsuario}")
    public void eliminarUsuario2(@PathVariable int idUsuario) { objUsuarioService.eliminarUsuario2(idUsuario); }
}
