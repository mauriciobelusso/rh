package mauriciobelusso.com.github.rh.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
public class Funcionario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    //- Nome (Entre 2 e 30 caracteres)
    @Size(min = 2, max = 30)
    @Column(length = 30)
    private String nome;

    //- Sobrenome (Entre 2 e 50 caracteres)
    @Size(min = 2, max = 50)
    @Column(length = 50)
    private String sobrenome;

    //- e-mail (Validar e-mail)
    @Email
    @Column(length = 250)
    private String email;

    //- Número do NIS (PIS) (Somente números)
    @Digits(integer = 10, fraction = 0)
    @Column(length = 20)
    private String nis;
}
