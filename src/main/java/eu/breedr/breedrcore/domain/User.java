package eu.breedr.breedrcore.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Exclude
    private Long id;
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    private String password;
}
