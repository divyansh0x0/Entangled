package P2P;

import java.util.*;

public class Peer {
    private final HashMap<PeerInfo,P2PConnection> connections = new HashMap<>();
    private Client clientSelf;
    private final Server serverSelf;
    private P2PManager manager;
    public Peer() {
        this(null);
    }

    public Peer(P2PManager manager) {
        this.manager = manager;
        serverSelf = new Server();

        clientSelf = new Client();

        serverSelf.setClientJoinHandler(connection -> {
            manager.saveConnection(connection);
            connections.put(connection.getPeerInfo(), connection);
            Log.success("Connection established with: " + connections);
        });

    }

    public void connectPeer(PeerInfo peerInfo) {
        P2PConnection connection  = clientSelf.getConnection(peerInfo);
        manager.saveConnection(connection);
        connections.put(peerInfo,connection);
    }
    public P2PConnection getConnectionWithPeer(PeerInfo peerInfo){
        if(!connections.containsKey(peerInfo))
            connectPeer(peerInfo);
        return connections.get(peerInfo);
    }

    public Client getClient() {
        return clientSelf;
    }
    public Server getServer() {
        return serverSelf;
    }

    public Collection<P2PConnection> getAllConnections() {
        return connections.values();
    }
}