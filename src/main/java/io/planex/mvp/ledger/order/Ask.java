package io.planex.mvp.ledger.order;

import java.math.BigDecimal;
import java.util.UUID;

// ПРОДАЖИ
public class Ask extends Order implements Comparable<Order> {

    public Ask(UUID id, UUID userId, BigDecimal volume, BigDecimal price) {
        super(id, userId, volume, price);
    }

    // сравнение сначала по цене, потом по таймштампу
    @Override
    public int compareTo(Order o) {
        if (this.getPrice().compareTo(o.getPrice()) > 0) {
            return 1;
        } else if (this.getPrice().compareTo(o.getPrice()) < 0) {
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
