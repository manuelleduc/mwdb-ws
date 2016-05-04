package fr.mleduc.poc.mwdb.ws.client;

import org.mwg.Graph;
import org.mwg.GraphBuilder;
import org.mwg.Node;
import org.mwg.ws.WSStorageClient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;

import static org.mwg.Type.*;

/**
 * Created by mleduc on 04/05/16.
 */
public class Client {
    public static void main(String[] args) throws IOException, URISyntaxException {
        final WSStorageClient wsStorageClient = WSStorageClient.init("localhost", 8087);
        final Graph clientGraph = GraphBuilder.builder().withStorage(wsStorageClient).build();
        clientGraph.connect(result -> {
            System.out.println(result);
            final Node node = clientGraph.newNode(0, 0);
            node.setProperty("rnd", LONG, new Random().nextLong());
            clientGraph.index("rands", node, new String[] {"rnd"}, result1 -> {
                System.out.println("indexed");
                //node.free();
                clientGraph.save(result3 -> {
                    System.out.println("saved");
                    clientGraph.disconnect(result2 -> {
                        System.out.println(result2);
                    });
                });

            });
        });
    }
}
