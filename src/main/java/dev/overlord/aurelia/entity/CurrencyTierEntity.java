package dev.overlord.aurelia.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "currency_tier", schema = "overlord_aurelia")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyTierEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "currency_id")
    private int currencyId;

    @Column(name = "currency_name")
    private String currencyName;

    @Column(name = "currency_tier_name")
    private String currencyTierName;  // 'C', 'B', 'A', 'S', 'SS', etc.

    @Column(name = "currency_description")
    private String currencyDescription;  // Description or significance of the tier, if required
}
