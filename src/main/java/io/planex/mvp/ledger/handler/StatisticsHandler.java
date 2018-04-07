package io.planex.mvp.ledger.handler;

import org.json.JSONObject;

public class StatisticsHandler {

    private StatisticsHandler() {
    }

    public static String methodResolver(JSONObject message) {
        switch (message.getString("method")) {
            case "getOrderBook":
                return getOrderBook(message);
            case "trades":
                return getTrades(message);
            default:
                return "Such method for statistics not found";
        }
    }

    private static String getTrades(JSONObject message) {
        return "fail";
    }

    private static String getOrderBook(JSONObject message) {
        return "fail";
    }
}
