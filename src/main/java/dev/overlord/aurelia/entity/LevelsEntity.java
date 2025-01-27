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
@Table(name = "levels", schema = "overlord_aurelia")
public class LevelsEntity {

    @Id
    @Column(name = "level_id")
    private int id;

    @Column(name = "level_name")
    private String levelName;

    @Column(name = "level_description")
    private String levelDescription;

    @Column(name = "threshold_xp")
    private int thresholdXP;
}
