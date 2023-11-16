package com.sergio.jwt.backend.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "app_user")
public class UserEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    @Size(max = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Size(max = 100)
    private String lastName;

    @Column(name = "cpf_cnpj", nullable = false)
    @Size(max = 100)
    private String cpfCnpj;

    @Column(nullable = false)
    @Size(max = 100)
    private String career;

    @Column(nullable = false)
    @Size(max = 100)
    private String password;

    @ManyToMany
    @JoinTable(name = "app_user_group", joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_group"))
    private Set<GroupEntity> groups = new HashSet<>();

    public boolean removeGroup(GroupEntity group) {
        return getGroups().remove(group);
    }

    public boolean addGroup(GroupEntity group) {
        return getGroups().add(group);
    }
}
