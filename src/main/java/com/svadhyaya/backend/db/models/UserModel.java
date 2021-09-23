package com.svadhyaya.backend.db.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.function.Function;

@Entity
@Table(name = "users")
public class UserModel implements UserDetails, CredentialsContainer {

    private static final Log logger = LogFactory.getLog(UserModel.class);

    private String password;
    @Id
    @Column(nullable = false, unique = true)
    private String username;
    @JsonInclude()
    @Transient
    private Set<GrantedAuthority> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    @ManyToMany
    private Set<RoleModel> roles;

    public UserModel() {
    }

    public UserModel(String username, String password, Collection<? extends GrantedAuthority> authorities, Set<RoleModel> roles) {
        this(username, password, true, true, true, true, authorities, roles);
    }

    public UserModel(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, Set<RoleModel> roles) {
        Assert.isTrue(username != null && !"".equals(username) && password != null, "Cannot pass null or empty values to constructor");
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
        this.roles = Collections.unmodifiableSet(roles);
    }

    public Collection<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    public void eraseCredentials() {
        this.password = null;
    }

    private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
        SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet(new UserModel.AuthorityComparator());
        Iterator var2 = authorities.iterator();

        while (var2.hasNext()) {
            GrantedAuthority grantedAuthority = (GrantedAuthority) var2.next();
            Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
            sortedAuthorities.add(grantedAuthority);
        }

        return sortedAuthorities;
    }

    public boolean equals(Object obj) {
        return obj instanceof UserModel ?
                this.username.equals(((UserModel) obj).username) : false;
    }

    public int hashCode() {
        return this.username.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getName()).append(" [");
        sb.append("Username=").append(this.username).append(", ");
        sb.append("Password=[PROTECTED], ");
        sb.append("Enabled=").append(this.enabled).append(", ");
        sb.append("AccountNonExpired=").append(this.accountNonExpired).append(", ");
        sb.append("credentialsNonExpired=").append(this.credentialsNonExpired).append(", ");
        sb.append("AccountNonLocked=").append(this.accountNonLocked).append(", ");
        sb.append("Granted Authorities=").append(this.authorities).append("]");
        return sb.toString();
    }

    public static UserModel.UserBuilder withUsername(String username) {
        return builder().username(username);
    }

    public static UserBuilder builder() {
        return new UserModel.UserBuilder();
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static UserModel.UserBuilder withDefaultPasswordEncoder() {
        logger.warn("UserModel.withDefaultPasswordEncoder() is considered unsafe for production and is only intended for sample applications.");
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        UserModel.UserBuilder var10000 = builder();
        Objects.requireNonNull(encoder);
        return var10000.passwordEncoder(encoder::encode);
    }

    public static UserModel.UserBuilder withUserDetails(UserDetails userDetails) {
        return withUsername(userDetails.getUsername()).password(userDetails.getPassword()).accountExpired(!userDetails.isAccountNonExpired()).accountLocked(!userDetails.isAccountNonLocked()).authorities(userDetails.getAuthorities()).credentialsExpired(!userDetails.isCredentialsNonExpired()).disabled(!userDetails.isEnabled());
    }

    public static final class UserBuilder {
        private String username;
        private String password;
        private List<GrantedAuthority> authorities;
        private boolean accountExpired;
        private boolean accountLocked;
        private boolean credentialsExpired;
        private boolean disabled;
        private Function<String, String> passwordEncoder;

        private UserBuilder() {
            this.passwordEncoder = (password) -> {
                return password;
            };
        }

        public UserModel.UserBuilder username(String username) {
            Assert.notNull(username, "username cannot be null");
            this.username = username;
            return this;
        }

        public UserModel.UserBuilder password(String password) {
            Assert.notNull(password, "password cannot be null");
            this.password = password;
            return this;
        }

        public UserModel.UserBuilder passwordEncoder(Function<String, String> encoder) {
            Assert.notNull(encoder, "encoder cannot be null");
            this.passwordEncoder = encoder;
            return this;
        }

        public UserModel.UserBuilder roles(String... roles) {
            List<GrantedAuthority> authorities = new ArrayList(roles.length);
            String[] var3 = roles;
            int var4 = roles.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                String role = var3[var5];
                Assert.isTrue(!role.startsWith("ROLE_"), () -> {
                    return role + " cannot start with ROLE_ (it is automatically added)";
                });
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
            }

            return this.authorities((Collection) authorities);
        }

        public UserModel.UserBuilder authorities(GrantedAuthority... authorities) {
            return this.authorities((Collection) Arrays.asList(authorities));
        }

        public UserModel.UserBuilder authorities(Collection<? extends GrantedAuthority> authorities) {
            this.authorities = new ArrayList(authorities);
            return this;
        }

        public UserModel.UserBuilder authorities(String... authorities) {
            return this.authorities((Collection) AuthorityUtils.createAuthorityList(authorities));
        }

        public UserModel.UserBuilder accountExpired(boolean accountExpired) {
            this.accountExpired = accountExpired;
            return this;
        }

        public UserModel.UserBuilder accountLocked(boolean accountLocked) {
            this.accountLocked = accountLocked;
            return this;
        }

        public UserModel.UserBuilder credentialsExpired(boolean credentialsExpired) {
            this.credentialsExpired = credentialsExpired;
            return this;
        }

        public UserModel.UserBuilder disabled(boolean disabled) {
            this.disabled = disabled;
            return this;
        }

        public UserDetails build() {
            String encodedPassword = (String) this.passwordEncoder.apply(this.password);
            return new org.springframework.security.core.userdetails.User(this.username, encodedPassword, !this.disabled, !this.accountExpired, !this.credentialsExpired, !this.accountLocked, this.authorities);
        }
    }

    private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {
        private static final long serialVersionUID = 550L;

        private AuthorityComparator() {
        }

        public int compare(GrantedAuthority g1, GrantedAuthority g2) {
            if (g2.getAuthority() == null) {
                return -1;
            } else {
                return g1.getAuthority() == null ? 1 : g1.getAuthority().compareTo(g2.getAuthority());
            }
        }
    }

    public Set<RoleModel> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleModel> roles) {
        this.roles = roles;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
