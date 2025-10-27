package org.easybook.booking;

import io.helidon.microprofile.server.Server;

public class Application {
    public static void main(String[] args) {
        Server server = Server.create();
        server.start();
    }
}
