package guru.springframework.msscbeerservice.bootstrap;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BeerLoader implements CommandLineRunner {

    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBeerObjects();
    }
    private void loadBeerObjects(){
        if(beerRepository.count() == 0){
            beerRepository.save(Beer.builder()
                    .beerName("Mango Bobs")
                    .beerStyle("IPA")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc("0631234300019")
                    .price(new BigDecimal("12.04"))
                    .build());
            beerRepository.save(Beer.builder()
                    .beerName("Galaxy Cat")
                    .beerStyle("PALE_ALE")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc("0083783375213")
                    .price(new BigDecimal("11.24"))
                    .build());
            beerRepository.save(Beer.builder()
                    .beerName("Galaxy Cat2")
                    .beerStyle("PALE_ALE")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc("0631234200036")
                    .price(new BigDecimal("11.24"))
                    .build());
        }
        System.out.println("Loaded Beers: "+beerRepository.count());
    }
}
