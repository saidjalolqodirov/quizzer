package uz.test.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.test.constants.RoleEnum;
import uz.test.users.UserEntity;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class UserDetailsImpl implements UserDetails {

    @Serial
    private static final long serialVersionUID = 5377255672157361226L;

    private final UserEntity userEntity;

    private final Collection<RoleEnum> authorities;

    public UserDetailsImpl() {
        this.userEntity = new UserEntity();
        authorities = new ArrayList<>();
    }

    public UserDetailsImpl(UserEntity userEntity) {
        this.userEntity = userEntity;
        authorities = List.of(userEntity.getRole());

    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities != null ? authorities : new ArrayList<>();
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getUsername();
    }

    @JsonProperty
    public void setPassword(String password) {
        this.userEntity.setPassword(password);
    }

    public UUID getId() {
        return this.userEntity.getId();
    }

    public Boolean getEnabled() {
        return true;
    }

}