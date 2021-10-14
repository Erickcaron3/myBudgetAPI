package net.erickcaron.mybudgetapi.repositories;

import net.erickcaron.mybudgetapi.models.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    Optional<Shop> findByName(String shopName);
}
