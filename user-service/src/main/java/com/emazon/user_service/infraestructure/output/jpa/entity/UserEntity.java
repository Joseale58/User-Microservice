package com.emazon.user_service.infraestructure.output.jpa.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String document;

    @Column
    private String name;

    @Column
    private String lastname;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @Column (unique = true)
    private String cellphone;

    @Column
    private LocalDate birthdate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private RoleEntity role;

}
