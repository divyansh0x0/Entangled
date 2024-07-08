import P2P.*;
import app.EntangledWindow;
import app.ChatManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EntangledWindow entangledWindow = new EntangledWindow();
        entangledWindow.setVisible(true);
        ChatManager.getInstance().start();
//        init();
    }
    public static void init() {
        Peer user = new Peer();
        user.getServer().start();
        Thread.ofVirtual().start(() -> {
        });

        Log.info("Server created and is now listening:" + user.getServer());
        Scanner scanner = new Scanner(System.in);
        Log.info("Enter another peer's address:");
        String address = scanner.next();
        Log.info("Enter another peer's port:");
        int port = scanner.nextInt();
//
        PeerInfo peerInfo = new PeerInfo(address, port);
        user.connectPeer(peerInfo);

        P2PConnection connectionAsClient = user.getConnectionWithPeer(peerInfo);
        while (true) {
            try {
                System.out.print("Enter message:");
                connectionAsClient.sendMessage(scanner.nextLine());
            } catch (Exception e) {
                Log.error(e);
            }
        }

    }
}
