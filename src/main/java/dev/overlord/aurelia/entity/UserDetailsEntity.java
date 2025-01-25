package dev.overlord.aurelia.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_details", schema = "overlord_aurelia")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "user_name")
    private String userName;

    @OneToOne(mappedBy = "userDetailsEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserProgressionEntity userProgressionEntity;

    @CreationTimestamp
    @Column(name = "created_date_time")
    private LocalDateTime createdDateTime;

    @UpdateTimestamp
    @Column(name = "updated_date_time")
    private LocalDateTime updatedDateTime;
}

