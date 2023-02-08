package mauriciobelusso.com.github.rh.service;

import mauriciobelusso.com.github.rh.exception.InvalidEmailException;
import mauriciobelusso.com.github.rh.model.Funcionario;
import mauriciobelusso.com.github.rh.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    @Autowired
    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public Funcionario save(Funcionario funcionario) {
        validateEmail(funcionario.getEmail());
        return funcionarioRepository.save(funcionario);
    }

    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }

    public Funcionario findById(Long id) {
        return funcionarioRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        funcionarioRepository.deleteById(id);
    }

    private void validateEmail(String email) {
        if (!email.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            throw new InvalidEmailException("Email inválido");
        }
    }
}
