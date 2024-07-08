package app;

import P2P.ClientJoinHandler;
import P2P.Log;
import P2P.P2PConnection;
import material.animation.MaterialFixedTimer;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.security.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Stack;

public class PeerChatHandler  {
    private P2PConnection connection;
    private final ArrayList<Message> AllMessages =new ArrayList<>();
    private ChatModificationListener chatModifiedListener;
    private MaterialFixedTimer timer = new MaterialFixedTimer(100) {
        @Override
        public void tick(float deltaMillis) {
            try {
                String text = connection.receiveMessage();
                if(!text.isEmpty()) {
                    Message msg = new Message(text, Instant.now(), Message.Type.RECEIVED);
                    AllMessages.add(msg);
                    addMessage(msg);
                    Log.msg("[from " + connection.getPeerInfo() + "]" + msg);
                }
                else {
                    Log.info("Waiting for message from: " + connection.getPeerInfo());
                }
            }catch (SocketException e){
                Log.error("Socket exception occurred! stopping connection...");
                destroy();
            }
            catch (Exception e){
                Log.error(e);
            }
        }
    };

    private void destroy() {
        timer.stop();
        timer.dispose();
        connection.destroy();
    }

    public void addMessage(Message msg) {
        AllMessages.add(msg);
        if(chatModifiedListener != null){
            chatModifiedListener.chatModified(msg);
        }
    }

    public PeerChatHandler(P2PConnection connection) {
        this.connection = connection;
        timer.start();
    }


    public ArrayList<Message> getAllMessagesSorted() {
        AllMessages.sort(Comparator.comparing(o -> o.timestamp));
        return AllMessages;
    }
    public void sendMessage(String  message) throws IOException {
        Message msg = new Message(message, Instant.now(),Message.Type.SENT);
        addMessage(msg);
        connection.sendMessage(message);
    }
    public void setChatModifiedListener(ChatModificationListener chatModifiedListener) {
        this.chatModifiedListener = chatModifiedListener;
    }
}
