package net.erickcaron.mybudgetapi.services;

import net.erickcaron.mybudgetapi.models.Shop;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ShopService {
    List<Shop> findAll();

    Long create(Shop shop);

    Optional<Shop> findById(Long id);

    Shop findByName(String shopName);

    void update(Long id, Shop shop);

    void isShopInputCorrect(Shop shop);

    void delete(Shop shop);

    Shop partialUpdateChecking(Shop shopFromDB, Shop newShop);

    Boolean areInputsNotNull(Shop shop);
}
