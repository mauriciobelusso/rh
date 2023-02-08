package mauriciobelusso.com.github.rh.service;

import mauriciobelusso.com.github.rh.exception.InvalidEmailException;
import mauriciobelusso.com.github.rh.model.Funcionario;
import mauriciobelusso.com.github.rh.repository.FuncionarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FuncionarioServiceTests {

    @InjectMocks
    private FuncionarioService funcionarioService;

    @Mock
    private FuncionarioRepository funcionarioRepository;

    private List<Funcionario> funcionarios;

    @BeforeEach
    public void setUp() {
        funcionarios = Arrays.asList(
                Funcionario.builder()
                        .id(1L)
                        .nome("João")
                        .sobrenome("Belusso")
                        .email("joao.belusso@email.com")
                        .nis("123456789")
                        .build(),
                Funcionario.builder()
                        .id(2L)
                        .nome("Maria")
                        .sobrenome("Belusso")
                        .email("maria.belusso@email.com")
                        .nis("987654321")
                        .build(),
                Funcionario.builder()
                        .id(3L)
                        .nome("Pedro")
                        .sobrenome("Belusso")
                        .email("pedro.belusso@email.com")
                        .nis("654987321")
                        .build()
        );

        lenient().when(funcionarioRepository.findAll()).thenReturn(funcionarios);
        lenient().when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(funcionarios.get(0)));
        lenient().when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(funcionarios.get(0));
        lenient().doNothing().when(funcionarioRepository).deleteById(1L);
    }

    @Test
    public void whenFindAll_thenReturnFuncionarios() {
        List<Funcionario> found = funcionarioService.findAll();

        assertThat(found).isNotNull().hasSize(3).containsAll(funcionarios);
        verify(funcionarioRepository, times(1)).findAll();
    }

    @Test
    public void whenFindById_thenReturnFuncionario() {
        Funcionario found = funcionarioService.findById(1L);

        assertThat(found).isNotNull().isEqualTo(funcionarios.get(0));
        verify(funcionarioRepository, times(1)).findById(1L);
    }

    @Test
    public void whenSave_thenReturnSavedFuncionario() {
        // given
        Funcionario funcionario = Funcionario.builder()
                .nome("João")
                .sobrenome("Belusso")
                .email("joao.belusso@email.com")
                .nis("123456789")
                .build();

        // when
        Funcionario savedFuncionario = funcionarioService.save(funcionario);

        // then
        assertThat(savedFuncionario).isNotNull();
        assertThat(savedFuncionario.getId()).isNotNull();
        assertThat(savedFuncionario.getNome()).isEqualTo(funcionario.getNome());
        assertThat(savedFuncionario.getSobrenome()).isEqualTo(funcionario.getSobrenome());
        assertThat(savedFuncionario.getEmail()).isEqualTo(funcionario.getEmail());
        assertThat(savedFuncionario.getNis()).isEqualTo(funcionario.getNis());
    }

    @Test
    public void whenSaveFuncionarioWithInvalidEmail_thenThrowException() {
        // given
        Funcionario funcionario = Funcionario.builder()
                .nome("João")
                .sobrenome("Belusso")
                .email("invalid-email")
                .nis("123456789")
                .build();

        // then
        assertThatThrownBy(() -> funcionarioService.save(funcionario))
                .isInstanceOf(InvalidEmailException.class)
                .hasMessage("Email inválido");
    }

    @Test
    public void whenDelete_thenNotReturnFuncionario() {
        // given
        Long id = 1L;

        // when
        funcionarioService.deleteById(id);

        // then
        verify(funcionarioRepository, times(1)).deleteById(id);
    }
}