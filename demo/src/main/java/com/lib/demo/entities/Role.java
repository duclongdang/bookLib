package com.lib.demo.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Table(name="role")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;
    @Column
    private String roleName;
    @OneToMany(mappedBy = "role",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> users;

    public Role(Long roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleName='" + roleName + '\'' +
                ", roleId=" + roleId +
                '}';
    }



}