package br.com.unipac.gerenciadoratividadesapp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_grupo")
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGrupo;

    @Column(name = "grupo_nome")
    private String nome;
    @Column(name = "grupo_email")
    private String email;
    @Column(name = "grupo_senha")
    private String senha;
    @Column(name = "grupo_role")
    private String role;

    @OneToMany(mappedBy = "grupo")
    @Column(name = "grupo_tarefas")
    private List<Tarefa> tarefas;

    @OneToMany(mappedBy = "grupo")
    @Column(name = "grupo_conquistas")
    private List<GrupoConquista> grupoConquistas;
}
