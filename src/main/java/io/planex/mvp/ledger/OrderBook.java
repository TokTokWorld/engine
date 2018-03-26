package io.planex.mvp.ledger;

import io.planex.mvp.ledger.order.Ask;
import io.planex.mvp.ledger.order.Bid;
import io.planex.mvp.ledger.order.Order;
import io.planex.mvp.ledger.skiplist.SkipList;

import java.math.BigDecimal;
import java.util.UUID;

class OrderBook {

    // покупки
    private SkipList bids;

    // продажи
    private SkipList asks;

    OrderBook() {
        bids = new SkipList();
        asks = new SkipList();
    }

    // TODO должно выполняться параллельно
    void matching(Order order) {
        if (order instanceof Bid) {
            asks.match(order);
        } else if (order instanceof Ask) {
            bids.match(order);
        }
    }

    public boolean addBid(UUID id, UUID userId, BigDecimal volume, BigDecimal price) {
        Bid bid = new Bid(id, userId, volume, price);
        bids.insert(bid);
        matching(bid);
        return true;
    }

    public boolean addAsk(UUID id, UUID userId, BigDecimal volume, BigDecimal price) {
        Ask ask = new Ask(id, userId, volume, price);
        asks.insert(ask);
        matching(ask);
        return true;
    }

    public SkipList getBids() {
        return bids;
    }

    public SkipList getAsks() {
        return asks;
    }
}
