package mauriciobelusso.com.github.rh.repository;

import mauriciobelusso.com.github.rh.model.Funcionario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
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

    @Test
    public void whenCreate_thenPersistFuncionario() {
        // given
        Funcionario funcionario = Funcionario.builder()
                .nome("João")
                .sobrenome("Belusso")
                .email("joao.belusso@email.com")
                .nis("123456789")
                .build();

        // when
        Funcionario created = funcionarioRepository.save(funcionario);

        // then
        assertThat(created.getId()).isNotNull();
        assertThat(funcionarioRepository.findById(created.getId()).get()).isEqualTo(created);
    }

    @Test
    public void whenUpdate_thenReturnFuncionario() {
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
        funcionario.setNome("João da Silva");
        funcionario.setSobrenome("Belusso");
        funcionario.setEmail("joao.belusso@email.com");
        funcionario.setNis("987654321");
        Funcionario updated = funcionarioRepository.save(funcionario);

        // then
        Optional<Funcionario> found = funcionarioRepository.findById(updated.getId());
        assertThat(found).isPresent().hasValueSatisfying(f -> {
            assertThat(f.getNome()).isEqualTo(updated.getNome());
            assertThat(f.getSobrenome()).isEqualTo(updated.getSobrenome());
            assertThat(f.getEmail()).isEqualTo(updated.getEmail());
            assertThat(f.getNis()).isEqualTo(updated.getNis());
        });
    }


    @Test
    public void whenDelete_thenNotReturnFuncionario() {
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
        funcionarioRepository.delete(funcionario);
        Optional<Funcionario> found = funcionarioRepository.findById(funcionario.getId());

        // then
        assertThat(found).isNotPresent();
    }

    @Test
    public void whenFindAll_thenReturnFuncionarios() {
        // given
        Funcionario funcionario1 = Funcionario.builder()
                .nome("João")
                .sobrenome("Belusso")
                .email("joao.belusso@email.com")
                .nis("123456789")
                .build();
        Funcionario funcionario2 = Funcionario.builder()
                .nome("Maria")
                .sobrenome("Belusso")
                .email("maria.belusso@email.com")
                .nis("987654321")
                .build();
        entityManager.persist(funcionario1);
        entityManager.persist(funcionario2);
        entityManager.flush();

        // when
        List<Funcionario> funcionarios = funcionarioRepository.findAll();

        // then
        assertThat(funcionarios).hasSize(2).contains(funcionario1, funcionario2);
    }
}
