package br.com.unipac.gerenciadoratividadesapp.repositories;

import br.com.unipac.gerenciadoratividadesapp.models.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {

    public boolean existsByEmail(String email);

    public Grupo findByEmail(String email);

    @Query("SELECT g.nome AS grupoNome, COUNT(gc.id) AS totalConquistasConcluidas " +
            "FROM Grupo g JOIN g.grupoConquistas gc " +
            "WHERE gc.conquistaConcluida = true " +
            "GROUP BY g.idGrupo, g.nome " +
            "ORDER BY totalConquistasConcluidas DESC")
    List<Object[]> findGruposWithMostConquistas();
}
