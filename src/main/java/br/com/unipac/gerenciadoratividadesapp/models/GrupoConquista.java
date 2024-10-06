package br.com.unipac.gerenciadoratividadesapp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_grupo_conquista")
public class GrupoConquista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gq_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "grupo_id")
    private Grupo grupo;

    @ManyToOne
    @JoinColumn(name = "conquista_id")
    private Conquista conquista;

    @Column(name = "conquista_concluida")
    private Boolean conquistaConcluida = false;
}
