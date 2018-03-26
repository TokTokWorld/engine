package io.planex.mvp.ledger.order;

import java.math.BigDecimal;
import java.util.UUID;

// ПОКУПКИ
public class Bid extends Order implements Comparable<Order> {

    public Bid(UUID id, UUID userId, BigDecimal volume, BigDecimal price) {
        super(id, userId, volume, price);
    }

    // сравнение сначала по цене, потом по таймштампу
    @Override
    public int compareTo(Order o) {
        if (this.getPrice().compareTo(o.getPrice()) < 0) {
            return 1;
        } else if (this.getPrice().compareTo(o.getPrice()) > 0) {
            return -1;
        } else if (this.getTimeStamp().compareTo(o.getTimeStamp()) < 0) {
            return 1;
        } else if (this.getTimeStamp().compareTo(o.getTimeStamp()) > 0) {
            return -1;
        } else {
            return 0;
        }
    }
}
