package dev.overlord.aurelia.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "currency_details", schema = "overlord_aurelia")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "currency_id")
    private int currencyId;

    @Column(name = "currency_name")
    private String currencyName;  // The name of the currency, e.g., "Gold", "Silver"

    @Column(name = "currency_rarity")
    private String currencyRarity;

    @Column(name = "currency_tier")
    private String currencyTier;  // The tier associated with the currency, e.g., 'C', 'B', 'A'

}
