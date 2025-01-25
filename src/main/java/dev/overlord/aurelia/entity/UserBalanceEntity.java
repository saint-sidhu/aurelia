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
@Table(name = "user_balance", schema = "overlord_aurelia")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserBalanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private UserDetailsEntity userDetailsEntity;

    @ManyToOne
    @JoinColumn(name = "currency_id", referencedColumnName = "currency_id", insertable = false, updatable = false)
    private CurrencyDetailsEntity currencyDetailsEntity;

    @Column(name = "balance")
    private int balance;

    @CreationTimestamp
    @Column(name = "created_date_time")
    private LocalDateTime createdDateTime;

    @UpdateTimestamp
    @Column(name = "updated_date_time")
    private LocalDateTime updatedDateTime;
}
