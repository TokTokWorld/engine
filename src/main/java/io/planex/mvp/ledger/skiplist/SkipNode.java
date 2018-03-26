package io.planex.mvp.ledger.skiplist;

import io.planex.mvp.ledger.order.Bid;
import io.planex.mvp.ledger.order.Order;

import java.math.BigDecimal;

class SkipNode {

    private Order order;
    @SuppressWarnings("unchecked")
    private SkipNode[] next = (SkipNode[]) new SkipNode[SkippableList.LEVELS];

    SkipNode(Order order) {
        this.order = order;
    }

    void refreshAfterDelete(int level) {
        SkipNode current = this;
        while (current != null && current.getNext(level) != null) {
            if (current.getNext(level).order == null) {
                SkipNode successor = current.getNext(level).getNext(level);
                current.setNext(successor, level);
                return;
            }

            current = current.getNext(level);
        }
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    private void setNext(SkipNode next, int level) {
        this.next[level] = next;
    }

    private SkipNode getNext(int level) {
        return this.next[level];
    }

    SkipNode search(Order order, int level, boolean print) {
        if (print) {
            System.out.print("Searching for: " + order + " at ");
            print(level);
        }

        SkipNode result = null;
        SkipNode current = this.getNext(level);
        while (current != null && current.order.compareTo(order) < 1) {
            if (current.order.equals(order)) {
                result = current;
                break;
            }

            current = current.getNext(level);
        }

        return result;
    }

    boolean match(Order order, int level) {
        boolean found = false;
        SkipNode current = this.getNext(level);

        while (current != null && (order instanceof Bid ? current.order.getPrice().compareTo(order.getPrice()) < 1 : current.order.getPrice().compareTo(order.getPrice()) > -1)) {
            if (current.order.getPrice().compareTo(order.getPrice()) == 0) {
                found = true;
                BigDecimal currentRest = current.order.getRest();
                BigDecimal givenRest = order.getRest();

                if (currentRest.compareTo(givenRest) > 0) {
                   current.order.setRest(currentRest.subtract(givenRest));
                   order.setRest(new BigDecimal(0.0));
                } else {
                    order.setRest(givenRest.subtract(currentRest));
                    current.order.setRest(new BigDecimal(0.0));
                }
                // TODO изменять балансы участников
                // TODO выгружать завершённые сделки
                // поиск должен идти дальше, пока позиция не закрыта
                if (order.getRest().compareTo(new BigDecimal(0)) > 0) {
                    continue;
                } else {
                    break;
                }
            }

            current = current.getNext(level);
        }

        return found;
    }

    void insert(SkipNode skipNode, int level) {
        SkipNode current = this.getNext(level);
        if (current == null) {
            this.setNext(skipNode, level);
            return;
        }

        if (skipNode.order.compareTo(current.order) < 1) {
            this.setNext(skipNode, level);
            skipNode.setNext(current, level);
            return;
        }

        while (current.getNext(level) != null && current.order.compareTo(skipNode.order) < 1 &&
                current.getNext(level).order.compareTo(skipNode.order) < 1) {

            current = current.getNext(level);
        }

        SkipNode successor = current.getNext(level);
        current.setNext(skipNode, level);
        skipNode.setNext(successor, level);
    }

    void print(int level) {
        System.out.print("level " + level + ": [ ");
        int length = 0;
        SkipNode current = this.getNext(level);
        while (current != null) {
            length++;
            System.out.print(current.order + " ");
            current = current.getNext(level);
        }

        System.out.println("], length: " + length);
    }
}