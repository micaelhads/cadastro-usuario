package com.cadastro.usuario.controller;

import com.cadastro.usuario.exeptions.UsuarioNotFoundException;
import com.cadastro.usuario.modal.UsuarioModel;
import com.cadastro.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/usuario",produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @PostMapping
    public ResponseEntity<String> addUsuario(@RequestBody UsuarioModel usuario){
        usuarioService.addUsuario(usuario);
        return ResponseEntity.ok("Registro gravado com sucesso");
    }

    @GetMapping
    public ResponseEntity<UsuarioModel> findUsuarioByCod(@RequestParam Long codUsuario) throws UsuarioNotFoundException {
        return ResponseEntity.ok(usuarioService.findUsuarioByCod(codUsuario));
    }
    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioModel>> findAllUsers(){
        return ResponseEntity.ok(usuarioService.findAllUsers());
    }

    @PutMapping
    public ResponseEntity<String>  attUser(@RequestBody UsuarioModel usuario){
        usuarioService.attUser(usuario);
        return ResponseEntity.ok("Registro atualizado com sucesso");
    }
}
