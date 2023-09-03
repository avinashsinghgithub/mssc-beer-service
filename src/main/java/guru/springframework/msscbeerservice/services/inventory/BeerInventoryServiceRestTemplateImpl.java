package guru.springframework.msscbeerservice.services.inventory;

import guru.springframework.msscbeerservice.services.inventory.model.BeerInventoryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@Profile("!local-discovery")
@ConfigurationProperties(prefix = "sfg.brewery", ignoreUnknownFields = false)
public class BeerInventoryServiceRestTemplateImpl implements BeerInventoryService{


    private final RestTemplate restTemplate;

    private String beerInventoryServiceHost;

    public void setBeerInventoryServiceHost(String beerInventoryServiceHost){
        this.beerInventoryServiceHost = beerInventoryServiceHost;
    }
    public BeerInventoryServiceRestTemplateImpl(RestTemplateBuilder restTemplateBuilder,
                                                @Value("${inventory.username}") String username,
                                                        @Value("${inventory.password}") String password){
        this.restTemplate = restTemplateBuilder
                .basicAuthentication(username,password)
                .build();
    }

    @Override
    public Integer getOnHandInventory(UUID beerId) {
        log.debug("Calling Inventory Service");

        ResponseEntity<List<BeerInventoryDto>> responseEntity =
                restTemplate.exchange(beerInventoryServiceHost + INVENTORY_PATH, HttpMethod.GET
                        , null, new ParameterizedTypeReference<List<BeerInventoryDto>>(){}, (Object) beerId);

        Integer onHand = Objects.requireNonNull(responseEntity.getBody())
                .stream()
                .mapToInt(BeerInventoryDto::getQuantityOnHand)
                .sum();
        return onHand;
    }
}
