package io.grpc.examples.helloworld;

import io.grpc.Channel;
import io.grpc.Grpc;
import io.grpc.InsecureChannelCredentials;
import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloWorldClient {
    private static final Logger logger = Logger.getLogger(HelloWorldClient.class.getName());

    private final GreeterGrpc.GreeterBlockingStub blockingStub;

    public HelloWorldClient(Channel channel) {

        blockingStub = GreeterGrpc.newBlockingStub(channel);
    }

    public void greet(String name, String firstName, String cin) {
        logger.info("Will try to greet " + firstName + " " + name + " with CIN: " + cin + " ...");
        HelloRequest request = HelloRequest.newBuilder()
                .setName(name)
                .setFirstName(firstName)
                .setCin(cin)
                .build();

    }

    public static void main(String[] args) throws Exception {
        String user = "world";
        // Access a service running on the local machine on port 50051
        String target = "localhost:50051";
        // Allow passing in the user and target strings as command line arguments
        if (args.length > 0) {
            if ("--help".equals(args[0])) {
                System.err.println("Usage: [name [target]]");
                System.err.println("");
                System.err.println("  name    The name you wish to be greeted by. Defaults to " + user);
                System.err.println("  target  The server to connect to. Defaults to " + target);
                System.exit(1);
            }
            user = args[0];
        }
        if (args.length > 1) {
            target = args[1];
        }

        ManagedChannel channel = Grpc.newChannelBuilder(target, InsecureChannelCredentials.create())
                .build();
        try {
            HelloWorldClient client = new HelloWorldClient(channel);
            client.greet(user);
        } finally {

            channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        }
    }
}