package mauriciobelusso.com.github.rh.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FuncionarioTests {

    private Funcionario funcionario;

    @BeforeEach
    public void setup() {
        funcionario = Funcionario.builder()
                .id(1L)
                .nome("João")
                .sobrenome("Belusso")
                .email("joao.belusso@email.com")
                .nis("123456789")
                .build();
    }

    @Test
    public void testFuncionarioNome() {
        assertThat(funcionario.getNome()).isEqualTo("João");
    }

    @Test
    public void testFuncionarioSobrenome() {
        assertThat(funcionario.getSobrenome()).isEqualTo("Belusso");
    }

    @Test
    public void testFuncionarioEmail() {
        assertThat(funcionario.getEmail()).matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    }

    @Test
    public void testFuncionarioNis() {
        assertThat(funcionario.getNis()).matches("^[0-9]+$");
    }
}
