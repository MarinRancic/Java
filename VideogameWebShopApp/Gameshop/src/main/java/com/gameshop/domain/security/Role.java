package com.gameshop.domain.security;

import java.util.*;
import javax.persistence.*;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role {
	@Id
	private int roleId;
	
	private String name;
	
	@OneToMany(mappedBy = "role", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<UserRole> userRoles = new HashSet<>();
	
	
}
