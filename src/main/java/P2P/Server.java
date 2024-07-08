package P2P;

import java.io.IOException;
import java.net.*;

public class Server {

    private boolean isAcceptingConnections;
    private int port;
    private ClientJoinHandler clientJoinHandler;
    private InetAddress serverAddress;
    ServerSocket serverSocket;

    public Server() {
        try {
            this.serverSocket = new ServerSocket(0);
            this.port = serverSocket.getLocalPort();
            this.serverAddress = serverSocket.getInetAddress();
        } catch (IOException ex) {
            Log.error(ex);
        }
    }

    public void start() {
        isAcceptingConnections = true;
        Thread.ofVirtual().name("Server Thread").start(() -> {
            try {
                while (isAcceptingConnections) {
                    Socket socket = serverSocket.accept();
                    PeerInfo peerInfo = new PeerInfo(socket.getInetAddress().getHostAddress(), socket.getPort());
                    if(clientJoinHandler != null) {
                        P2PConnection connection = new P2PConnection(peerInfo, socket);
                        clientJoinHandler.onClientJoin(connection);
                    }
                }
            } catch (Exception e) {
                Log.error("Error occurred in server while listening for connections");
                Log.error(e);
            }
        });
    }


    public void stop() {
        // Method to stop the server
        isAcceptingConnections = false;
        try {
            serverSocket.close();
        } catch (IOException e) {
            Log.error(e);
        }
    }

    public void setClientJoinHandler(ClientJoinHandler clientJoinHandler) {
        this.clientJoinHandler = clientJoinHandler;
    }

    public boolean isAcceptingConnections() {
        return isAcceptingConnections;
    }

    public int getPort() {
        return port;
    }

    public String getAddress() {
        return serverAddress.getHostAddress();
    }

    @Override
    public String toString() {
        return serverAddress.getHostAddress() + ":" + port;
    }
}
