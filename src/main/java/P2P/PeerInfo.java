package P2P;

import java.util.Objects;

public class PeerInfo {
    private final String peerAddress;
    private final int peerPort;
    private String peerName;

    public PeerInfo(String peerAddress, int peerPort, String peerName) {
        if(peerName.isEmpty())
            peerName = "Anonymous";
        this.peerAddress = peerAddress;
        this.peerPort = peerPort;
        this.peerName = peerName;
    }

    public PeerInfo(String peerAddress, int peerPort) {
        this(peerAddress,peerPort,"Unknown");
    }

    public String getPeerAddress() {
        return peerAddress;
    }

    public int getPeerPort() {
        return peerPort;
    }

    public String getPeerName() {
        return peerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeerInfo peerInfo = (PeerInfo) o;
        return peerPort == peerInfo.peerPort && Objects.equals(peerAddress, peerInfo.peerAddress) && Objects.equals(peerName, peerInfo.peerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(peerAddress, peerPort, peerName);
    }

    @Override
    public String toString() {
        return peerName +" at " + peerAddress + ":" + peerPort;
    }
}
