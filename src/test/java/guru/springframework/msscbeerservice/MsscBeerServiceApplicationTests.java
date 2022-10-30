package guru.springframework.msscbeerservice;


import guru.springframework.msscbeerservice.bootstrap.BeerLoader;
import guru.springframework.msscbeerservice.services.inventory.BeerInventoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class MsscBeerServiceApplicationTests {

    @Autowired
    BeerInventoryService beerInventoryService;

    @Test
    public void getOnHandInventory() {
        Integer qoh = beerInventoryService.getOnHandInventory(UUID.randomUUID());
    }

}
