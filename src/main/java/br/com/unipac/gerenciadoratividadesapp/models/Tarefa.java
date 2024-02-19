package br.com.unipac.gerenciadoratividadesapp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_tarefa")
public class Tarefa {

    // atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTarefa;

    private String nome;

    private String dataInicio;

    private String dataFinal;

    @OneToMany(mappedBy = "tarefa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Usuario> usuarioList;

}

