package com.shermann.park_api.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_user")
public class UserModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String username;

    @Column(nullable = false, length = 200)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 25)
    private Role role = Role.CLIENT;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "modification_date")
    private LocalDate modificationDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "update_by")
    private String updateBy;

    public enum Role {
        ADMIN,
        CLIENT

    }
}
