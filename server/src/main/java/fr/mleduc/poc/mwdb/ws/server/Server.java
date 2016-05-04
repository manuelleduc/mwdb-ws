package fr.mleduc.poc.mwdb.ws.server;

import org.mwg.Callback;
import org.mwg.Graph;
import org.mwg.GraphBuilder;
import org.mwg.LevelDBStorage;
import org.mwg.ws.WSStorageWrapper;

/**
 * Created by mleduc on 04/05/16.
 */
public class Server {
    public static void main(String[] args) {
        final LevelDBStorage levelDBStorage = new LevelDBStorage("leveldb_data");
        final WSStorageWrapper wsStorageWrapper = new WSStorageWrapper(levelDBStorage, 8087);
        final Graph serverGraph = GraphBuilder.builder().withStorage(wsStorageWrapper).build();
        serverGraph.connect(result -> System.out.println("Started"));
    }
}
