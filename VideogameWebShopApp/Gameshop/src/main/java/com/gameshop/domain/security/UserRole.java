package com.gameshop.domain.security;

import com.gameshop.domain.User;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user_role")
public class UserRole {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long userRoleId;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="role_id")
	private Role role;
	
	public UserRole (User user, Role role) {
		this.user = user;
		this.role = role;
	}
}

