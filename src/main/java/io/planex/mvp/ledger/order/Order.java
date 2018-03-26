package io.planex.mvp.ledger.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Order implements Comparable<Order> {

    // id сделки
    private UUID id;

    // id пользователя
    private UUID userId;

    // объём
    private BigDecimal volume;

    // цена
    private BigDecimal price;

    // время
    private LocalDateTime timeStamp;

    // оставшийся объём
    private BigDecimal rest;

    Order(UUID id, UUID userId, BigDecimal volume, BigDecimal price) {
        this.id = id;
        this.userId = userId;
        this.volume = volume;
        this.price = price;
        this.timeStamp = LocalDateTime.now();
        this.rest = volume;
    }

    @Override
    public int compareTo(Order o) {
        if (o instanceof Bid) {
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
        } else if (o instanceof Ask) {
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
        return 0;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setRest(BigDecimal rest) {
        this.rest = rest;
    }

    public BigDecimal getRest() {
        return rest;
    }

    @Override
    public String toString() {
        return "Order{" +
                ", volume=" + volume +
                ", price=" + price +
                ", timeStamp=" + timeStamp +
                ", rest=" + rest +
                '}';
    }
}
