package app;

import P2P.*;
import material.containers.MaterialPanel;
import material.window.MousePointer;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class ChatManager implements P2PManager {
    private static ChatManager instance;
    private  final ChatPanel chatPanel = new ChatPanel();
    private  final ManagePeersPanel managePeersPanel = new ManagePeersPanel();
    private final MaterialPanel rootPane = new MaterialPanel();
    private  final Peer user = new Peer(this);
    private  final HashMap<PeerInfo, PeerChatHandler> peers = new HashMap<>();

    private ChatManager() {
        chatPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)) {
                    managePeersPanel.show(MousePointer.getPointerLocation());
                }
            }
        });
    }
    public void start(){
        user.getServer().start();
        Log.info("Server created and is now listening:" + user.getServer());
    }

    public  ManagePeersPanel getManagePeersPanel() {
        return managePeersPanel;
    }
    public  ChatPanel getChatPanel() {
        return chatPanel;
    }

    public  void removeConnection(PeerInfo peerInfo){
        peers.remove(peerInfo);
    }

    public  void createConnection(PeerInfo peerInfo) {
        if(!peers.containsKey(peerInfo))
            user.connectPeer(peerInfo);
        chatPanel.setChatHandler(peers.get(peerInfo));
    }

    public  MaterialPanel getRootPane() {
        rootPane.add(managePeersPanel,"grow");
        return rootPane;
    }

    @Override
    public void saveConnection(P2PConnection connection) {
        PeerChatHandler chatHandler = new PeerChatHandler(connection);
        peers.put(connection.getPeerInfo(), chatHandler);

    }
    public static ChatManager getInstance() {
        if(instance == null)
            instance = new ChatManager();
        return instance;
    }
}
