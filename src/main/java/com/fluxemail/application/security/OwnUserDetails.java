package com.fluxemail.application.security;

import com.fluxemail.application.security.data.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
public class OwnUserDetails extends User implements UserDetails  {
    /**
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    /**
     * @return
     */
    @Override
    public String getUsername() {
        return this.email;
    }

    /**
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return this.isActive;
    }

    /**
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    public static OwnUserDetails fromUser(User user){

        OwnUserDetails userDetails = new OwnUserDetails();

        userDetails.id = user.getId();
        userDetails.email = user.getEmail();
        userDetails.fullName = user.getFullName();
        userDetails.password = user.getPassword();
        userDetails.role = user.getRole();
        userDetails.entity = user.getEntity();
        userDetails.isActive = user.getIsActive();
        userDetails.createdAt=user.getCreatedAt();
        userDetails.updatedAt=user.getUpdatedAt();
        return userDetails ;
    }

}
