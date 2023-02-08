package mauriciobelusso.com.github.rh.controller;

import mauriciobelusso.com.github.rh.model.Funcionario;
import mauriciobelusso.com.github.rh.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @Autowired
    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping
    public List<Funcionario> findAll() {
        return funcionarioService.findAll();
    }

    @GetMapping("/{id}")
    public Funcionario findById(@PathVariable Long id) {
        return funcionarioService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Funcionario create(@RequestBody @Valid Funcionario funcionario) {
        return funcionarioService.save(funcionario);
    }

    @PutMapping("/{id}")
    public Funcionario update(@PathVariable Long id, @RequestBody @Valid Funcionario funcionario) {
        funcionario.setId(id);
        return funcionarioService.save(funcionario);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        funcionarioService.deleteById(id);
    }
}
