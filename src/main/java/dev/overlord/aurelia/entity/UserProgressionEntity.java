package dev.overlord.aurelia.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_progression", schema = "overlord_aurelia")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProgressionEntity {

    @Id
    @Column(name = "user_id")
    private int userId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserDetailsEntity userDetailsEntity;

    @Column(name = "xp")
    private int xp;

    @Column(name = "level")
    private int level;
}

