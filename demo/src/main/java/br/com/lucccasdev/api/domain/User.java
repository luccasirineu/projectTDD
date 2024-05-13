package br.com.lucccasdev.api.domain;

import jakarta.persistence.*;
import lombok.*;


@Data // caso a classe tenha muitos atributos essa anotation pode deixar a api menos perfomatica
@AllArgsConstructor
@NoArgsConstructor
@Entity(tb)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @Column(unique = true)
    private String email;
    private String password;


}
