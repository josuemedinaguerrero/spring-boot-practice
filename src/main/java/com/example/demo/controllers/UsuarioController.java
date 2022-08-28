package com.example.demo.controllers;

import com.example.demo.dao.UsuarioDao;
import com.example.demo.models.Usuario;
import com.example.demo.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {
   @Autowired
   private UsuarioDao usuarioDao;

   @Autowired
   private JWTUtil jwtUtil;

   private boolean validarToken( String token ) {
      String usuarioId = jwtUtil.getKey( token );
      return usuarioId != null;
   }

   @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
   public List<Usuario> getUsuarios( @RequestHeader( value = "Authorization" ) String token ) {
      if ( validarToken( token ) ) { return  null; }
      return usuarioDao.getUsuarios();
   }

   @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.GET)
   public Usuario getUsuario( @PathVariable Long id ) {
      Usuario usuario = new Usuario();
      usuario.setId(id);
      usuario.setNombre("Vanessa");
      usuario.setApellido("Zambrano");
      usuario.setEmail("vanessa@test.com");
      usuario.setTelefono("0991528005");
      usuario.setPassword("123456");

      return usuario;
   }

   @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
   public void registrarUsuarios( @RequestBody Usuario usuario ) {
      Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
      String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
      usuario.setPassword(hash);
      usuarioDao.registrar( usuario );
   }

   @RequestMapping(value = "p2SArueba")
   public Usuario editarUsuario() {
      Usuario usuario = new Usuario();
      usuario.setNombre("Vanessa");
      usuario.setApellido("Zambrano");
      usuario.setEmail("vanessa@test.com");
      usuario.setTelefono("0991528005");
      usuario.setPassword("123456");

      return usuario;
   }

   @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
   public void eliminarUsuario( @PathVariable Long id ) {
      usuarioDao.eliminarUsuario(id);
   }

   @RequestMapping(value = "psdfsdrueba")
   public Usuario buscarUsuario() {
      Usuario usuario = new Usuario();
      usuario.setNombre("Vanessa");
      usuario.setApellido("Zambrano");
      usuario.setEmail("vanessa@test.com");
      usuario.setTelefono("0991528005");
      usuario.setPassword("123456");

      return usuario;
   }
}
