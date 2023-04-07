package com.brody.userwebapi.entities;

import com.brody.userwebapi.enums.Currecy;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double balance;
    @Enumerated(EnumType.STRING)
    private Currecy currecy;

    @Transient
    @OneToOne(mappedBy = "account")
    private User user;
}
