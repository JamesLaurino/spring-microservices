package com.hexagon.cloudgateway.dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Table("users")
@Builder
@AllArgsConstructor @NoArgsConstructor
public class UserDto implements UserDetails, Persistable<Integer>
{
    @Getter @Setter
    @Id
    private Integer id;
    @Setter
    @Column("username")
    private String username;

    @Setter
    @Column("password")
    private String password;

    @Setter
    @Column("role")
    private String role;

    public UserDto(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // This new field tells us if the record is new
    @Transient
    @Builder.Default
    private boolean isNewEntry = true;

    // This method checks if the record is new
    public boolean isNew() {
        return isNewEntry;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
