package dev.overlord.aurelia.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "shop_items", schema = "overlord_aurelia")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "item_id")
    private Integer itemId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "cost_price")
    private int costPrice;

    @Column(name = "sell_price")
    private int sellPrice;

    @Column(name = "currency_tier")
    private String currencyTier;

}
