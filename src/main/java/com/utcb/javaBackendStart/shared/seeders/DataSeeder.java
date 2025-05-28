package com.utcb.javaBackendStart.shared.seeders;



import com.utcb.javaBackendStart.auth.entities.RoleEntity;
import com.utcb.javaBackendStart.auth.entities.UserEntity;
import com.utcb.javaBackendStart.auth.repositories.RoleRepository;
import com.utcb.javaBackendStart.auth.repositories.UserRepository;
import com.utcb.javaBackendStart.constants.RoleEnum;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.UUID;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedAll(RoleRepository roleRepository,
                              UserRepository userRepository) {
        return args -> {
            // Seed Roles
            List<RoleEntity> roles = List.of(
                    new RoleEntity(UUID.fromString("9367c292-12ab-472f-bf5f-4d8e232e342d"), RoleEnum.WORKER),
                    new RoleEntity(UUID.fromString("8c3c9bbd-d195-4ea4-88d1-7ccb3263570b"), RoleEnum.DIRECTOR),
                    new RoleEntity(UUID.fromString("0e236a7c-5224-4716-a2a9-f6b0953f87ca"), RoleEnum.ACCOUNTANT),
                    new RoleEntity(UUID.fromString("07b0edbc-1ebd-4c09-bb71-2e75c5d0ad40"), RoleEnum.ADMIN)
            );
            for (RoleEntity role : roles) {
                roleRepository.findById(role.getId()).orElseGet(() -> {
                    roleRepository.save(role);
                    System.out.println("Inserted role: " + role.getName());
                    return role;
                });
            }

            // Seed Users
            List<UserEntity> users = List.of(
                    createUser("4c59e375-3efb-4546-a602-4f3e29b40a88", "PM", "Accountant",
                            "pm_accountant@cleancodeacademy.ro", "0e236a7c-5224-4716-a2a9-f6b0953f87ca", roleRepository),

                    createUser("5e5ec3c3-673a-4e4c-a029-92dd09ec3968", "PM", "Admin",
                            "pm_admin@cleancodeacademy.ro", "07b0edbc-1ebd-4c09-bb71-2e75c5d0ad40", roleRepository),

                    createUser("c1099da6-ccb5-42a2-8f3d-68bd840576ac", "PM", "Director1",
                            "pm_director1@cleancodeacademy.ro", "8c3c9bbd-d195-4ea4-88d1-7ccb3263570b", roleRepository),

                    createUser("6311fa89-ba15-415f-82c0-307f4c7cf687", "PM", "Director2",
                            "pm_director2@cleancodeacademy.ro", "8c3c9bbd-d195-4ea4-88d1-7ccb3263570b", roleRepository),

                    createUser("22de9578-f51e-430c-a883-10f0954af228", "PM", "Director3",
                            "pm_director3@cleancodeacademy.ro", "8c3c9bbd-d195-4ea4-88d1-7ccb3263570b", roleRepository),

                    createUser("593eb7de-6899-4b72-8799-66b64f5cf32d", "PM", "Worker1",
                            "pm_worker1@cleancodeacademy.ro", "9367c292-12ab-472f-bf5f-4d8e232e342d", roleRepository),

                    createUser("c5fb3c16-90a4-47eb-8b75-684431dfe884", "PM", "Worker2",
                            "pm_worker2@cleancodeacademy.ro", "9367c292-12ab-472f-bf5f-4d8e232e342d", roleRepository),

                    createUser("54c7418e-f8ad-4523-9b29-a99c3dcc5ea2", "PM", "Worker3",
                            "pm_worker3@cleancodeacademy.ro", "9367c292-12ab-472f-bf5f-4d8e232e342d", roleRepository),

                    createUser("7f58ddef-84d8-4ce3-bad1-f95c903e85ba", "Director", "Director",
                            "director@gmail.com", "8c3c9bbd-d195-4ea4-88d1-7ccb3263570b", roleRepository),

                    createUser("456f48af-14e8-4211-bc68-18a18387c526", "Admin", "Admin",
                            "admin@gmail.com", "07b0edbc-1ebd-4c09-bb71-2e75c5d0ad40", roleRepository)
            );

            for (UserEntity user : users) {
                userRepository.findByEmail(user.getEmail()).orElseGet(() -> {
                    userRepository.save(user);
                    System.out.println("Inserted user: " + user.getEmail());
                    return user;
                });
            }
        };
    }

    private UserEntity createUser(String id, String firstName, String lastName, String email,
                                  String roleId, RoleRepository roleRepository) {
        return UserEntity.builder()
                .id(UUID.fromString(id))
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(new BCryptPasswordEncoder().encode("1234567"))
                .displayName(email.split("@")[0])
                .role(roleRepository.findById(UUID.fromString(roleId))
                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleId)))
                .build();
    }
}
