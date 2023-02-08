package mauriciobelusso.com.github.rh.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mauriciobelusso.com.github.rh.error.ApiError;
import mauriciobelusso.com.github.rh.model.Funcionario;
import mauriciobelusso.com.github.rh.service.FuncionarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(FuncionarioController.class)
@AutoConfigureMockMvc(addFilters = false)
public class FuncionarioControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FuncionarioService funcionarioService;

    @Test
    public void whenCreateFuncionario_thenReturnCreatedStatus() throws Exception {
        Funcionario funcionario = Funcionario.builder()
                .nome("João")
                .sobrenome("Belusso")
                .email("joao.belusso@email.com")
                .nis("123456789")
                .build();

        ObjectMapper mapper = new ObjectMapper();
        String funcionarioJson = mapper.writeValueAsString(funcionario);

        mvc.perform(post("/funcionarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(funcionarioJson))
                .andExpect(status().isCreated());

        verify(funcionarioService, times(1)).save(funcionario);
    }

    @Test
    public void whenGetFuncionarioById_thenReturnFuncionario() throws Exception {
        Funcionario funcionario = Funcionario.builder()
                .id(1L)
                .nome("João")
                .sobrenome("Belusso")
                .email("joao.belusso@email.com")
                .nis("123456789")
                .build();

        when(funcionarioService.findById(1L)).thenReturn(funcionario);

        mvc.perform(get("/funcionarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("João"))
                .andExpect(jsonPath("$.sobrenome").value("Belusso"))
                .andExpect(jsonPath("$.email").value("joao.belusso@email.com"))
                .andExpect(jsonPath("$.nis").value("123456789"));

        verify(funcionarioService, times(1)).findById(1L);
    }

    @Test
    public void whenGetAllFuncionarios_thenReturnFuncionariosList() throws Exception {
        Funcionario funcionario1 = Funcionario.builder()
                .id(1L)
                .nome("João")
                .sobrenome("Belusso")
                .email("joao.belusso@email.com")
                .nis("123456789")
                .build();

        Funcionario funcionario2 = Funcionario.builder()
                .id(2L)
                .nome("Maria")
                .sobrenome("Belusso")
                .email("maria.belusso@email.com")
                .nis("987654321")
                .build();
        List<Funcionario> funcionarios = Arrays.asList(funcionario1, funcionario2);

        when(funcionarioService.findAll()).thenReturn(funcionarios);

        mvc.perform(get("/funcionarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("João"))
                .andExpect(jsonPath("$[0].sobrenome").value("Belusso"))
                .andExpect(jsonPath("$[0].email").value("joao.belusso@email.com"))
                .andExpect(jsonPath("$[0].nis").value("123456789"))
                .andExpect(jsonPath("$[1].nome").value("Maria"))
                .andExpect(jsonPath("$[1].sobrenome").value("Belusso"))
                .andExpect(jsonPath("$[1].email").value("maria.belusso@email.com"))
                .andExpect(jsonPath("$[1].nis").value("987654321"));

        verify(funcionarioService, times(1)).findAll();
    }

    @Test
    public void whenUpdateFuncionario_thenReturnOkStatus() throws Exception {
        Funcionario funcionario = Funcionario.builder()
                .id(1L)
                .nome("João")
                .sobrenome("Belusso")
                .email("joao.belusso@email.com")
                .nis("123456789")
                .build();

        ObjectMapper mapper = new ObjectMapper();
        String funcionarioJson = mapper.writeValueAsString(funcionario);

        mvc.perform(put("/funcionarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(funcionarioJson))
                .andExpect(status().isOk());

        verify(funcionarioService, times(1)).save(funcionario);
    }

    @Test
    public void whenDeleteFuncionario_thenReturnNoContentStatus() throws Exception {
        mvc.perform(delete("/funcionarios/1"))
                .andExpect(status().isNoContent());

        verify(funcionarioService, times(1)).deleteById(1L);
    }

    @Test
    public void testValidationErrorResponse() throws Exception {
        Funcionario funcionario = Funcionario.builder()
                .id(1L)
                .nome("J")
                .sobrenome("Belusso")
                .email("joao.belusso")
                .nis("123456789a")
                .build();

        ObjectMapper mapper = new ObjectMapper();
        String funcionarioJson = mapper.writeValueAsString(funcionario);

        MvcResult result = mvc.perform(post("/funcionarios/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(funcionarioJson))
                .andExpect(status().isBadRequest())
                .andReturn();

        String contentAsString = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        ApiError apiError = objectMapper.readValue(contentAsString, ApiError.class);
        assertThat(apiError.getTimestamp()).isNotNull();
        assertThat(apiError.getStatus()).isEqualTo(400);
        assertThat(apiError.getMessage()).isEqualTo("validation error");
        assertThat(apiError.getValidationErrors().get("nome")).isEqualTo("size must be between 2 and 30");
        assertThat(apiError.getValidationErrors().get("email")).isEqualTo("must be a well-formed email address");
    }
}