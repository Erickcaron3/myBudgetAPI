package net.erickcaron.mybudgetapi.services.impl;

import lombok.AllArgsConstructor;
import net.erickcaron.mybudgetapi.exceptions.ResourceError;
import net.erickcaron.mybudgetapi.exceptions.ResourceException;
import net.erickcaron.mybudgetapi.models.Shop;
import net.erickcaron.mybudgetapi.repositories.ShopRepository;
import net.erickcaron.mybudgetapi.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private final ShopRepository shopRepository;

    @Override
    public List<Shop> findAll() {
        return shopRepository.findAll();
    }

    @Override
    public Long create(Shop shop) {
        return shopRepository.save(shop).getId();
    }

    @Override
    public Optional<Shop> findById(Long id) {
        return shopRepository.findById(id);
    }

    @Override
    public Shop findByName(String shopName) {
        if (shopRepository.findByName(shopName).isEmpty()) {
            return null;
        } else {
            return shopRepository.findByName(shopName).get();
        }
    }

    @Override
    public void update(Long id, Shop shop) {
        shop.setId(id);
        shopRepository.save(shop);

    }

    public void isShopInputCorrect(Shop shop) {
        if (shop.getCategory() != null && shop.getName() != null) {
            for (Shop shopFromDB : shopRepository.findAll()) {
                if (shopFromDB.getName().equals(shop.getName())) {
                    throw new ResourceException(ResourceError.INCORRECT_INPUT);
                }
            }
        }
    }

    @Override
    public void delete(Shop shop) {
        shopRepository.delete(shop);
    }

    @Override
    public Shop partialUpdateChecking(Shop shopFromDB, Shop newShop) {

        if (shopFromDB.equals(newShop)) {
            return shopFromDB;
        }

        Shop shopToImpl = new Shop();

        if (!shopFromDB.getName().equals(newShop.getName())) {
            shopToImpl.setName(newShop.getName());
        }

        if (!shopFromDB.getCategory().equals(newShop.getCategory())) {
            shopToImpl.setCategory(newShop.getCategory());
        }

        return shopToImpl;

    }

    @Override
    public Boolean areInputsNotNull(Shop shop) {
        return shop.getName() == null || shop.getCategory() == null;

    }

}
