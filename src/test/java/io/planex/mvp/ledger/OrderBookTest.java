package io.planex.mvp.ledger;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderBookTest {

    public static void main(String[] args) {
        OrderBook orderBook = new OrderBook();

        /*
        orderBook.addBid(UUID.randomUUID(), UUID.randomUUID(), new BigDecimal(2), new BigDecimal(2));
        orderBook.addBid(UUID.randomUUID(), UUID.randomUUID(), new BigDecimal(2), new BigDecimal(3));
        orderBook.addBid(UUID.randomUUID(), UUID.randomUUID(), new BigDecimal(2), new BigDecimal(4));
        orderBook.getBids().print();

        orderBook.addAsk(UUID.randomUUID(), UUID.randomUUID(), new BigDecimal(2), new BigDecimal(2));
        orderBook.addAsk(UUID.randomUUID(), UUID.randomUUID(), new BigDecimal(3), new BigDecimal(3));
        orderBook.addAsk(UUID.randomUUID(), UUID.randomUUID(), new BigDecimal(4), new BigDecimal(4));
        orderBook.getAsks().print();
        */

        orderBook.addBid(UUID.randomUUID(), UUID.randomUUID(), new BigDecimal(2), new BigDecimal(4));
        orderBook.addBid(UUID.randomUUID(), UUID.randomUUID(), new BigDecimal(6), new BigDecimal(3));
        orderBook.addBid(UUID.randomUUID(), UUID.randomUUID(), new BigDecimal(7), new BigDecimal(3));
        orderBook.addBid(UUID.randomUUID(), UUID.randomUUID(), new BigDecimal(2), new BigDecimal(2));
        orderBook.addBid(UUID.randomUUID(), UUID.randomUUID(), new BigDecimal(10), new BigDecimal(5));
        orderBook.addAsk(UUID.randomUUID(), UUID.randomUUID(), new BigDecimal(2), new BigDecimal(4));
        orderBook.addAsk(UUID.randomUUID(), UUID.randomUUID(), new BigDecimal(3), new BigDecimal(5));
        orderBook.addAsk(UUID.randomUUID(), UUID.randomUUID(), new BigDecimal(2), new BigDecimal(5));
        orderBook.addAsk(UUID.randomUUID(), UUID.randomUUID(), new BigDecimal(7), new BigDecimal(6));
        orderBook.getBids().print();
        orderBook.getAsks().print();
    }
}
