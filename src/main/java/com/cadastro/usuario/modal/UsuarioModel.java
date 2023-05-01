package com.cadastro.usuario.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "tb_usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioModel {

    private String nome;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codUsuario;
    private Date dataNasc;
    private byte[] foto;
}
