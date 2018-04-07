package io.planex.mvp.ledger;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.planex.mvp.ledger.handler.OrdersHandler;
import io.planex.mvp.ledger.handler.StatisticsHandler;
import org.json.JSONObject;

import java.nio.charset.Charset;

/**
 * Handles a server-side channel.
 */
public class ServerHandler extends ChannelInboundHandlerAdapter { // (1)

    /**
     * Message format:
     * Get orderBook - { "type" : "stat", "method" : "getOrderBook", "limit": 100} return

                            { "status" : "ok",
                            "bids": [
                                     [
                                         "4.00000000",     // PRICE
                                         "431.00000000"   // QTY
                                         ]
                                         ],
                            "asks": [
                                         [
                                         "4.00000200",
                                         "12.00000000"
                                         ]
                                     ]
                            }
     * Get trades - { "type" : "stat", "method" : "trades", "limit" : 500 } return { "status" : "ok", "trades" : [["price", "volume"], []] }
     *
     * Make order - { "type" : "order", "method" : "addOrder", "userId": 123, "volume" : 321,  "price" : 123 } return { "status" : "ok", "orderID": 123}
     * Cancel order - { "type" "order", "method" : "cancelOrder", "orderId" : 123 } return { "status" : "ok" }
     * Get orders by client - { "side" : "buy", }
     * @param ctx
     * @param msg
     */

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
        ByteBuf in = (ByteBuf) msg;
        String message = in.toString(io.netty.util.CharsetUtil.US_ASCII);


        JSONObject jsonObj = new JSONObject(message);

        System.out.println(jsonObj.getString("type"));

        String result = "{\"status\" : \"fail\"}";


        if ("order".equals(jsonObj.getString("type"))) {
            result = OrdersHandler.methodResolver(jsonObj);
        } else if ("stat".equals(jsonObj.getString("type"))) {
            result = StatisticsHandler.methodResolver(jsonObj);
        }

        ByteBuf encoded = ctx.alloc().buffer(4);
        encoded.writeCharSequence(result.subSequence(0, result.length()), Charset.forName("ASCII"));
        ctx.write(encoded); // (1)
        ctx.flush(); // (2)
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
