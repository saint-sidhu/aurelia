package dev.overlord.aurelia.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_data" , schema = "overlord_aurelia")
public class UserDataEntity {

    @Id
    @Column(name = "user_name")
    private String userName;

    @Column(name = "balance")
    private int balance;
}
