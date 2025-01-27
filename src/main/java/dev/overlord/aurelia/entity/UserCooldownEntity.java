package dev.overlord.aurelia.entity;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_cooldown" , schema = "overlord_aurelia")
@Entity
public class UserCooldownEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    private UserDetailsEntity userDetailsEntity;

    @Column(name ="command_id")
    private String commandId;

    @Timestamp
    @Column(name = "last_command_time")
    private LocalDateTime lastCommandTime;

    @Timestamp
    @Column(name = "locked_till_time")
    private LocalDateTime lockedTillTime;
}
