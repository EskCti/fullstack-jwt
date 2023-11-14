package com.sergio.jwt.backend.entites;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "app_group")
public class Group {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ManyToMany
	@JoinTable(name = "group_permission", joinColumns = @JoinColumn(name = "id_group"),
			inverseJoinColumns = @JoinColumn(name = "id_permission"))
	private Set<Permission> permissions = new HashSet<>();

	public boolean removePermission(Permission permission) {
		return getPermissions().remove(permission);
	}

	public boolean addPermission(Permission permission) {
		return getPermissions().add(permission);
	}

}