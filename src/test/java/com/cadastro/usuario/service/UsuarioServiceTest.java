package com.cadastro.usuario.service;

import com.cadastro.usuario.exeptions.InvalidParameterException;
import com.cadastro.usuario.exeptions.UsuarioNotFoundException;
import com.cadastro.usuario.modal.UsuarioModel;
import com.cadastro.usuario.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    private UsuarioService usuarioService;

    public static final String NOME_USUARIO_TESTE = "Jose Da Silva";

    public static final Date DATA_NASC_USUARIO_TESTE = new Date();

    private UsuarioModel usuarioMock = new UsuarioModel(NOME_USUARIO_TESTE, 1L, DATA_NASC_USUARIO_TESTE, null);


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuarioService = new UsuarioService(usuarioRepository);
    }

    @Test
    void testAddUsuario() {
        usuarioService.addUsuario(usuarioMock);
        verify(usuarioRepository, times(1)).save(usuarioMock);
    }

    @Test
    void testAddUsuarioComUsuarioNull() {
        Assertions.assertThrows(InvalidParameterException.class, () -> usuarioService.addUsuario(null));
    }

    @Test
    void testAddUsuarioComNomeVazio() {
        UsuarioModel usuario = usuarioMock;
        usuario.setNome("");
        Assertions.assertThrows(InvalidParameterException.class, () -> usuarioService.addUsuario(usuario));
    }

    @Test
    void testAddUsuarioComNomeNullo() {
        UsuarioModel usuario = usuarioMock;
        usuario.setNome(null);
        Assertions.assertThrows(InvalidParameterException.class, () -> usuarioService.addUsuario(usuario));
    }

    @Test
    void testAddUsuarioComDataNull() {
        UsuarioModel usuario = usuarioMock;
        usuario.setDataNasc(null);
        Assertions.assertThrows(InvalidParameterException.class, () -> usuarioService.addUsuario(usuario));
    }

    @Test
    void testAddUsuarioComDataFutura() {
        UsuarioModel usuario = usuarioMock;
        usuario.setDataNasc(new Date(System.currentTimeMillis() + 86400000));
        Assertions.assertThrows(InvalidParameterException.class, () -> usuarioService.addUsuario(usuario));
    }

    @Test
    void testFindUsuarioByCod() {
        Long codUsuario = 1L;
        UsuarioModel usuario = new UsuarioModel();
        usuario.setCodUsuario(codUsuario);
        when(usuarioRepository.findById(codUsuario)).thenReturn(Optional.of(usuario));
        UsuarioModel result = usuarioService.findUsuarioByCod(codUsuario);
        Assertions.assertEquals(usuario, result);
    }

    @Test
    void testFindUsuarioByCodComCodigoNull() {
        Assertions.assertThrows(InvalidParameterException.class, () -> usuarioService.findUsuarioByCod(null));
    }

    @Test
    void testFindAllUsersInvalido() {
        Assertions.assertThrows(UsuarioNotFoundException.class, () -> usuarioService.findUsuarioByCod(1L));
    }

    @Test
    void testFindAllUsersByCod() {
        Long codUsuario = 1L;
        UsuarioModel usuario = new UsuarioModel();
        usuario.setCodUsuario(codUsuario);
        List<UsuarioModel> usuarios = new ArrayList<>();
        usuarios.add(usuario);
        when(usuarioRepository.findAll()).thenReturn(usuarios);
        List<UsuarioModel> result = usuarioService.findAllUsers();
        Assertions.assertEquals(usuarios, result);
    }

    @Test
    public void attUserWithNullUsuarioThrowsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () -> usuarioService.attUser(null));
    }

    @Test
    public void attUserWithNullCodigoThrowsInvalidParameterException() {
        UsuarioModel usuario = usuarioMock;
        usuario.setNome("");
        usuario.setDataNasc(null);
        assertThrows(InvalidParameterException.class, () -> usuarioService.attUser(usuario));
    }

    @Test
    public void attUserWithValidDataSavesUsuario() {
        Long codigo = 1L;
        UsuarioModel usuario = usuarioMock;
        when(usuarioRepository.existsById(codigo)).thenReturn(true);
        usuarioService.attUser(usuario);
        verify(usuarioRepository).save(usuario);
    }
}
