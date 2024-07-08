package app;

import P2P.Log;
import material.component.MaterialIconButton;
import material.component.MaterialTextBox;
import material.containers.MaterialPanel;
import material.listeners.MouseClickListener;
import net.miginfocom.swing.MigLayout;
import org.kordamp.ikonli.materialdesign2.MaterialDesignS;

import java.awt.event.InputEvent;
import java.sql.Timestamp;
import java.time.Instant;

public class ChatPanel extends MaterialPanel {
    private MaterialTextBox textBox = new MaterialTextBox();
    private MaterialIconButton sendButton = new MaterialIconButton(MaterialDesignS.SEND).setCornerRadius(10);
    private MaterialPanel textBoxPanel = new MaterialPanel(new MigLayout("nogrid,fill"));
    private ChatDisplay chatDisplay = new ChatDisplay();

    public ChatPanel() {
        setLayout(new MigLayout("nogrid,fill"));
        add(chatDisplay, "grow");
        add(textBoxPanel, "south,h 50!");

        textBoxPanel.add(textBox, "grow");
        textBoxPanel.add(sendButton, "east,h 50!, w 50!,grow");

        sendButton.addLeftClickListener(e -> {
            try {
                chatDisplay.getActiveChatHandler().sendMessage(textBox.getText());
                repaint();
            }catch (Exception err){
                Log.error(err);
            }
        });
    }

    public void setChatHandler(PeerChatHandler chatHandler) {
        chatDisplay.setPeerChatHandler(chatHandler);
    }
}
