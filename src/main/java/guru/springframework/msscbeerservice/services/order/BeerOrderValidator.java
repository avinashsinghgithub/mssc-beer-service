package guru.springframework.msscbeerservice.services.order;

import guru.sfg.brewery.model.events.BeerOrderDto;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RequiredArgsConstructor
@Component
public class BeerOrderValidator {
    private final BeerRepository beerRepository;
    public Boolean validateOrder(BeerOrderDto beerOrder){
        AtomicInteger beerNotFound = new AtomicInteger();
        beerOrder.getBeerOrderLines().forEach(orderLine ->{
            if(beerRepository.findByUpc(orderLine.getUpc())== null){
                beerNotFound.incrementAndGet();
            }
        });
        return beerNotFound.get() == 0;
    }
}
