package com.littlestark.jajan.model.entity;

import com.littlestark.jajan.controller.user.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


@Data
@Builder
@Entity
@Table(name = "_user_table")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "date_register")
    private LocalDateTime dateRegister;

    @Column(name = "verification_user")
    private Boolean isVerificationUser;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "userProfile")
    private ProfileEntity profileEntity;

    @OneToMany(mappedBy = "userProduct", cascade = CascadeType.ALL)
    private List<ProductEntity> productEntity;

    @OneToOne(mappedBy = "userVerificationProfile")
    private VerificationProfileEntity verificationProfileEntity;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
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
