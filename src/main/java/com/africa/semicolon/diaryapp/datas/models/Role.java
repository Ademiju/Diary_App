package com.africa.semicolon.diaryapp.datas.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role {
    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "role_id_sequence", sequenceName = "role_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    public Role(RoleType roleType) {
        this.roleType = roleType;
    }

    @Enumerated(EnumType.STRING)
    private RoleType roleType;
}
