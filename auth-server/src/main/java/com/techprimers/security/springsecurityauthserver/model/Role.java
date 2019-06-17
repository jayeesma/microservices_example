package com.techprimers.security.springsecurityauthserver.model;



import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="role")
public class Role {
    @Id
    @Column(name="role_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int roleId;
    @Column(name = "role")
    private String role;

    @ManyToMany(mappedBy = "roles")
    private Set<Users> users;

    public Role(){
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Users> getUsers() {
        return users;
    }

    public void setUsers(Set<Users> users) {
        this.users = users;
    }
}
