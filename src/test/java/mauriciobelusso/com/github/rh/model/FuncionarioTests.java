package mauriciobelusso.com.github.rh.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        assertTrue(funcionario.getNome().equals("João"), "O nome do funcionário deve ser 'João'");
    }

    @Test
    public void testFuncionarioSobrenome() {
        assertTrue(funcionario.getSobrenome().equals("Belusso"), "O sobrenome do funcionário deve ser 'Belusso'");
    }

    @Test
    public void testFuncionarioEmail() {
        assertTrue(funcionario.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"), "O email do funcionário é inválido");
    }

    @Test
    public void testFuncionarioNis() {
        assertTrue(funcionario.getNis().matches("^[0-9]+$"), "O NIS do funcionário deve conter apenas números");
    }
}
