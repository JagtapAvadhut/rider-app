package com.rideshare.trackingservice;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.rideshare.trackingservice.dto.LocationMessage;
import java.lang.reflect.Type;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebSocketSmokeTest {

    @Value("${local.server.port}")
    private int port;

    @Test
    void shouldConnectSubscribeAndReceiveLocation() throws Exception {
        WebSocketStompClient stompClient = new WebSocketStompClient(new StandardWebSocketClient());
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        StompSession session = stompClient.connectAsync("ws://localhost:" + port + "/ws", new StompSessionHandlerAdapter() {}).get(5, TimeUnit.SECONDS);

        CompletableFuture<LocationMessage> future = new CompletableFuture<>();
        session.subscribe("/topic/ride/1/location", new StompFrameHandler() {
            @Override
            public Type getPayloadType(StompHeaders headers) {
                return LocationMessage.class;
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                future.complete((LocationMessage) payload);
            }
        });

        LocationMessage message = new LocationMessage();
        message.setRideId(1L);
        message.setDriverId(99L);
        message.setLat(18.5204);
        message.setLon(73.8567);

        session.send("/app/driver/location/1", message);
        LocationMessage received = future.get(5, TimeUnit.SECONDS);
        assertNotNull(received);
    }
}
