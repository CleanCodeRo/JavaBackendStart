package com.utcb.javaBackendStart.auth.entities;

import com.utcb.javaBackendStart.constants.RoleEnum;
import com.utcb.javaBackendStart.shared.models.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RoleEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum name;


}
