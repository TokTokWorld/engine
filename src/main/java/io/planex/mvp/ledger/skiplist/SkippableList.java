package io.planex.mvp.ledger.skiplist;

import io.planex.mvp.ledger.order.Order;

interface SkippableList {

    int LEVELS = 5;

    boolean delete(Order order);
    void print();
    void insert(Order order);
    SkipNode search(Order order);
}