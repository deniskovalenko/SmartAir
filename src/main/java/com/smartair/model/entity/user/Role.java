package com.smartair.model.entity.user;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {

    private RoleType name;

    public Role() {
    }

    public Role(final String strName) {
       for (RoleType role : RoleType.values()) {
           if (role.getRoleViewName().equals(strName) || role.name().equals(strName)) {
               this.name = role;
               break;
           }
       }

    }

    public Role(final RoleType name) {
        this.name = name;
    }

    public RoleType getName() {
        return name;
    }

    public void setName(final RoleType name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name.name();
    }

    @Override
    public String toString() {
        return name.name();
    }
}
