    package br.com.unipac.gerenciadoratividadesapp.models;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

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

        @ManyToOne
        @JoinColumn(name = "tb_tarefa")
        private Tarefa tarefa;

    }
