package br.com.unipac.gerenciadoratividadesapp.repositories;

import br.com.unipac.gerenciadoratividadesapp.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
