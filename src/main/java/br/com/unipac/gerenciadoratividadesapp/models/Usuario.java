    package br.com.unipac.gerenciadoratividadesapp.models;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    import java.util.List;

    @Setter
    @Getter
    @Entity
    @NoArgsConstructor
    @AllArgsConstructor
    @Table(name = "tb_usuario")
    public class Usuario {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idUsuario;

        private String nome;

        @ManyToMany(mappedBy = "usuarios")
        private List<Tarefa> tarefas;

        @ManyToOne
        @JoinColumn(name = "grupo_id")
        private Grupo grupo;

    }
