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
public class GroupEntity extends BaseEntity{

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ManyToMany
	@JoinTable(name = "app_group_permission", joinColumns = @JoinColumn(name = "id_group"),
			inverseJoinColumns = @JoinColumn(name = "id_permission"))
	private Set<PermissionEntity> permissions = new HashSet<>();

	public boolean removePermission(PermissionEntity permission) {
		return getPermissions().remove(permission);
	}

	public boolean addPermission(PermissionEntity permission) {
		return getPermissions().add(permission);
	}

}