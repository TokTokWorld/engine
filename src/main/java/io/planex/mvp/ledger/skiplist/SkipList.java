package io.planex.mvp.ledger.skiplist;

import io.planex.mvp.ledger.order.Order;

import java.util.Random;

public class SkipList implements SkippableList {

    private final SkipNode head = new SkipNode(null);
    private final Random rand = new Random();

    @Override
    public void insert(Order order) {
        SkipNode skipNode = new SkipNode(order);
        for (int i = 0; i < LEVELS; i++) {
            if (rand.nextInt((int) Math.pow(2, i)) == 0) {
                //insert with prob = 1/(2^i)
                insert(skipNode, i);
            }
        }
    }

    @Override
    public boolean delete(Order order) {
        System.out.println("Deleting " + order);
        SkipNode victim = search(order, true);
        if (victim == null) {
            return false;
        }
        victim.setOrder(null);

        for (int i = 0; i < LEVELS; i++) {
            head.refreshAfterDelete(i);
        }

        System.out.println("deleted...");
        return true;
    }

    @Override
    public SkipNode search(Order order) {
        return search(order, true);
    }

    @Override
    public void print() {
        for (int i = LEVELS-1; i >= 0 ; i--) {
            head.print(i);
        }
        System.out.println();
    }

    public boolean match(Order order) {
        for (int i = LEVELS - 1; i >= 0; i--) {
            if (head.match(order, i)) {
                return true;
            }
        }
        return false;
    }

    private void insert(SkipNode SkipNode, int level) {
        head.insert(SkipNode, level);
    }

    private SkipNode search(Order order, boolean print) {
        SkipNode result = null;
        for (int i = LEVELS - 1; i >= 0; i--) {
            if ((result = head.search(order, i, print)) != null) {
                if (print) {
                    System.out.println("Found " + order.toString() + " at level " + i + ", so stopped" );
                    System.out.println();
                }
                break;
            }
        }

        return result;
    }
}
