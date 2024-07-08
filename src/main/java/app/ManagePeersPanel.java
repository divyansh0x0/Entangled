package app;

import P2P.Log;
import P2P.PeerInfo;
import material.component.MaterialIconButton;
import material.component.MaterialTextBox;
import material.window.MaterialPopup;
import net.miginfocom.swing.MigLayout;
import org.kordamp.ikonli.materialdesign2.MaterialDesignP;

import java.awt.*;

public class ManagePeersPanel extends MaterialPopup {

    private MaterialTextBox peerName = new MaterialTextBox();
    private MaterialTextBox peerAddress = new MaterialTextBox();
    private MaterialTextBox peerPort = new MaterialTextBox();
    private MaterialIconButton addPeerButton = new MaterialIconButton(MaterialDesignP.PLUS,"Add Peer");
    public ManagePeersPanel() {
        super(new MigLayout("fill,nogrid, flowy, insets 0 0 0 0"));
        peerAddress.setHint("Peer Address");
        peerAddress.setText("0.0.0.0");
        peerPort.setHint("Peer Port");
        peerName.setHint("Peer name");
        add(peerAddress, "growx,h 50!, w 400!");
        add(peerPort, "growx, h 50!, w 400!");
        add(peerName, "growx, h 50!, w 400!");
        add(addPeerButton,"growx, h 50!, w 100!");
        addPeerButton.addLeftClickListener(e -> {
            try {
                Log.warn("Creating connection");
                ChatManager.getInstance().createConnection(new PeerInfo(peerAddress.getText(), Integer.parseInt(peerPort.getText()), peerName.getText()));
            }
            catch (Exception er){
                Log.error(er);
            }
            });
    }

    @Override
    public void show(Point location) {
        super.show(location);
        peerAddress.select(0,10);

    }
}
