package mauriciobelusso.com.github.rh.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    //- Nome (Entre 2 e 30 caracteres)
    @Column(length = 30)
    private String nome;

    //- Sobrenome (Entre 2 e 50 caracteres)
    @Column(length = 50)
    private String sobrenome;

    //- e-mail (Validar e-mail)
    @Column(length = 250)
    private String email;

    //- Número do NIS (PIS) (Somente números)
    @Column(length = 20)
    private String nis;
}
