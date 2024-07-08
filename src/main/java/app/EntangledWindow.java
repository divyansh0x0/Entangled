package app;

import material.window.MaterialWindow;

public class EntangledWindow extends MaterialWindow {
    public EntangledWindow() {
        super("Entangled", Settings.MINIMUM_WINDOW_SIZE, true, true);
        getRootPanel().add(ChatManager.getInstance().getChatPanel(),"east");
    }
}
