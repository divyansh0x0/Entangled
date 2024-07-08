package P2P;

import java.io.IOException;
import java.net.Socket;

public class Client {
    private boolean isListening;

    public P2PConnection getConnection(PeerInfo peerInfo) {
        Socket socket = null;
        try {
            socket = new Socket(peerInfo.getPeerAddress(), peerInfo.getPeerPort());
            Log.success("A socket connection established with " + peerInfo);
            return new P2PConnection(peerInfo,socket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void stop(){
        isListening = false;
    }
    public boolean isListening() {
        return isListening;
    }

}
