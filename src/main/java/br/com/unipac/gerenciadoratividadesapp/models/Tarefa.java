package br.com.unipac.gerenciadoratividadesapp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data_inicio", length = 255)
    private Date dataInicio;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data_final", length = 255)
    private Date dataFinal;

    @Column(name = "concluida")
    private Boolean isConcluida;

    @Column(name = "removida")
    private Boolean isRemovida;

    @ManyToOne
    @JoinColumn(name = "grupo_id")
    private Grupo grupo;

}

