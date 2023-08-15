package com.bookmyconsultation.doctorservice.jwt;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum ApplicationRole {

    USER(Sets.newHashSet(ApplicationPermission.USER_ACCESS)),
    ADMIN(Sets.newHashSet(ApplicationPermission.ADMIN_ACCESS));



    private Set<ApplicationPermission> permissions;

    ApplicationRole(Set<ApplicationPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationPermission> getPermissions(){
        return this.getPermissions();
    }

    public Set<SimpleGrantedAuthority> getAuthorities(){
        Set<SimpleGrantedAuthority> authorities = permissions.stream().map(
                p-> new SimpleGrantedAuthority(p.name()))
                .collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return authorities;
    }
}
