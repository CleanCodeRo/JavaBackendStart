package com.utcb.javaBackendStart.auth.entities;

import com.utcb.javaBackendStart.shared.models.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.UUID;

@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private String displayName;

    @Column
    private String password;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(unique = true, nullable = false)
    @Email
    private String email;

    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id")
    RoleEntity role;
}
