package br.com.unipac.gerenciadoratividadesapp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tb_usuario")
public class Usuario {

    // atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    private String nomeUsuario;

    @ManyToOne
    @JoinColumn(name = "tb_tarefa")
    private Tarefa tarefa;

    // construtor

    public Usuario() {
    }
}
