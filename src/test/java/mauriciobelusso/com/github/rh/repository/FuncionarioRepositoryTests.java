package mauriciobelusso.com.github.rh.repository;

import mauriciobelusso.com.github.rh.model.Funcionario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
 public class FuncionarioRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Test
    public void whenFindById_thenReturnFuncionario() {
        // given
        Funcionario funcionario = Funcionario.builder()
                .nome("João")
                .sobrenome("Belusso")
                .email("joao.belusso@email.com")
                .nis("123456789")
                .build();
        entityManager.persist(funcionario);
        entityManager.flush();

        // when
        Optional<Funcionario> found = funcionarioRepository.findById(funcionario.getId());

        // then
        assertThat(found).isPresent().hasValueSatisfying(f -> {
            assertThat(f.getNome()).isEqualTo(funcionario.getNome());
            assertThat(f.getSobrenome()).isEqualTo(funcionario.getSobrenome());
            assertThat(f.getEmail()).isEqualTo(funcionario.getEmail());
            assertThat(f.getNis()).isEqualTo(funcionario.getNis());
        });
    }
}
