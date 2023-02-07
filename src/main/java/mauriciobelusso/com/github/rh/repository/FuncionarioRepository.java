package mauriciobelusso.com.github.rh.repository;

import mauriciobelusso.com.github.rh.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
