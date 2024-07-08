package P2P;

import java.io.*;
import java.net.Socket;

public class P2PConnection {
    private DataOutputStream outputStream;
    private final Socket socket;
    private DataInputStream inputStream;
    private  PeerInfo peerInfo;
    public P2PConnection(PeerInfo peerInfo,Socket socket) {
        try {
            this.peerInfo = peerInfo;
            this.socket = socket;
            outputStream = new DataOutputStream(socket.getOutputStream());
            inputStream = new DataInputStream(socket.getInputStream());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(String msg) throws IOException {
        Log.info("Sending message to: " + peerInfo);
        outputStream.writeUTF(msg);
    }

    public String receiveMessage() throws IOException {
        return inputStream.readUTF();
    }

    public PeerInfo getPeerInfo() {
        return peerInfo;
    }

    public void destroy() {
        try {
            socket.close();
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
