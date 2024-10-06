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
@Table(name = "tb_conquistas")
public class Conquista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conquista")
    private Long idConquista;

    @Column(name = "conquista_nome", length = 50)
    private String nome;

    @Column(name = "conquista_descricao")
    private String descricao;

    @OneToMany(mappedBy = "conquista")
    @Column(name = "grupo_conquistas")
    private List<GrupoConquista> grupoConquistas;
}
