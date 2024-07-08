package app;

import material.component.MaterialComponent;
import material.theme.ThemeColors;
import material.theme.enums.Elevation;

import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

public class ChatDisplay extends MaterialComponent {
    private PeerChatHandler activeChatHandler;
    public void setPeerChatHandler(PeerChatHandler chatHandler) {
        activeChatHandler = chatHandler;
        activeChatHandler.setChatModifiedListener(newMessage -> repaint());
        repaint();
    }
    @Override
    public void updateTheme() {
        setForeground(ThemeColors.getTextPrimary());
        setBackground(ThemeColors.getColorByElevation(Elevation._1));
    }

    @Override
    protected void paintComponent(Graphics g) {
        int textHeight = g.getFontMetrics().getHeight();
        int y = getHeight() - 20;
        if(activeChatHandler != null) {
            for (Message message : activeChatHandler.getAllMessagesSorted()) {
                y -= textHeight;
                g.drawString(message.text, 0, y);
            }
        }
    }

    @Override
    protected void animateMouseEnter() {

    }

    @Override
    protected void animateMouseExit() {

    }


    public PeerChatHandler getActiveChatHandler() {
        return activeChatHandler;
    }
}
