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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTarefa;

    @Column(name = "nome", length = 50)
    private String nome;

    @Column(name = "descricao", length = 255)
    private String descricao;

    @Column(name = "data_inicio", length = 255)
    private String dataInicio;

    @Column(name = "data_final", length = 255)
    private String dataFinal;

    @ManyToOne
    @JoinColumn(name = "grupo_id")
    private Grupo grupo;

}

