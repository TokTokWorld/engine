package io.planex.mvp.ledger.handler;

import org.json.JSONObject;

/**
 * Created by Nexenta on 03.04.2018.
 */
public class OrdersHandler {

    private OrdersHandler() {
    }

    public static String methodResolver(JSONObject message) {
        switch (message.getString("method")) {
            case "addOrder":
                return addOrder(message);
            case "cancelOrder":
                return cancelOrder(message);
            default:
                return "Such method for order doesn't exists!";
        }
    }

    private static String addOrder(JSONObject message) {
        return "fail";
    }

    private static String cancelOrder(JSONObject message) {
        return "fail";
    }

    private static String getOrdersByClient() {
        return "fail";
    }

}
