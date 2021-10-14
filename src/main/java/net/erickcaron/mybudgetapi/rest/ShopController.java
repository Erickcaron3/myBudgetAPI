package net.erickcaron.mybudgetapi.rest;

import lombok.AllArgsConstructor;
import net.erickcaron.mybudgetapi.exceptions.ResourceError;
import net.erickcaron.mybudgetapi.exceptions.ResourceException;
import net.erickcaron.mybudgetapi.models.Shop;
import net.erickcaron.mybudgetapi.services.ShopService;
import net.erickcaron.mybudgetapi.utils.Checkings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor

@Transactional
@RestController
@RequestMapping("/shops")
public class ShopController {

    @Autowired
    private final ShopService shopService;

    @GetMapping()
    public List<Shop> findAll() {
        return shopService.findAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Long create(@RequestBody Shop shop) {
        checkInputsNotNull(shop);
        IsShopExistingCheckedByShopName(shop.getName());
        return shopService.create(shop);
    }

    @GetMapping("/{id}")
    public Shop findById(@PathVariable("id") Long id) {
        Shop shop = shopService.findById(id).orElseThrow(() -> new ResourceException(ResourceError.RESOURCE_NOT_FOUND)); //TODO ta linia w controller czy w service?
        return shop;

    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void update(@PathVariable("id") Long id, @RequestBody Shop shop) {
        Checkings.checkFoundShop(shopService.findById(id));
        checkInputsNotNull(shop);
        checkShopInputEligibility(shop);
        shopService.update(id, shop);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void partialUpdate(@PathVariable("id") Long id, @RequestBody @Valid Shop shop) {
        shopService.findById(id)
                .orElseThrow(() -> new ResourceException(ResourceError.RESOURCE_NOT_FOUND));//TODO ta linia w controller czy w service?
        checkInputsNotNull(shop);
        checkShopInputEligibility(shop);
        Shop shopSuccessChecked = shopService.partialUpdateChecking(shopService.findById(id).get(), shop);
        shopService.update(id, shopSuccessChecked);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        Checkings.checkFoundShop(shopService.findById(id));
        shopService.delete(shopService.findById(id).get());
    }

    private void IsShopExistingCheckedByShopName(String shopName) {
        if (shopService.findByName(shopName) != null) {
            throw new ResourceException(ResourceError.RESOURCE_ALREADY_EXISTING);
        }
    }


    private void checkInputsNotNull(Shop shop) {
        if (shopService.areInputsNotNull(shop)) {
            throw new ResourceException(ResourceError.INCORRECT_INPUT);
        }
    }

    private void checkShopInputEligibility(Shop shop) {
        shopService.isShopInputCorrect(shop);
    }


}
