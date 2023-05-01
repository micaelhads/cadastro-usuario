package com.cadastro.usuario.service;

import com.cadastro.usuario.exeptions.InvalidParameterException;
import com.cadastro.usuario.exeptions.UsuarioNotFoundException;
import com.cadastro.usuario.modal.UsuarioModel;
import com.cadastro.usuario.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioService  {
    @Autowired
    UsuarioRepository usuarioRepository;
    public void addUsuario(UsuarioModel usuario) {
        validaDadosUsuario(usuario);
        usuarioRepository.save(usuario);
    }

    public UsuarioModel findUsuarioByCod(Long codUsuario){
        if(codUsuario == null){
            throw new InvalidParameterException("Para realizar a consulta é necessario o paramtro do codigo do usuario");
        }
        Optional<UsuarioModel> usuario = usuarioRepository.findById(codUsuario);
        return usuario.orElseThrow(() -> new UsuarioNotFoundException("Usuario não encontrando em nossa base de dados"));
    }

    public List<UsuarioModel> findAllUsers(){
        Optional<List<UsuarioModel>> usuarios = Optional.of(usuarioRepository.findAll());
        return usuarios.orElseThrow(() -> new UsuarioNotFoundException("Nenhum usuario encontrado na base de dados"));
    }

    public void attUser(UsuarioModel usuario){
        validaDadosUsuario(usuario);
        if(usuario.getCodUsuario() == null){
            throw new InvalidParameterException("Codigo do usuario em nullo");
        }
        usuarioRepository.save(usuario);
    }

    public void validaDadosUsuario(UsuarioModel usuario) {
        if (usuario == null) {
            throw new InvalidParameterException("Usuario vazio ou nullo");
        } else if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            throw new InvalidParameterException("Nome de usuario vazio ou nullo");
        } else if (usuario.getDataNasc() == null || usuario.getDataNasc().after(new Date())) {
            throw new InvalidParameterException("Data de nascimento nulla ou invalida");
        }
    }
}
