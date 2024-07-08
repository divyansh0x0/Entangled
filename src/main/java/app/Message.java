package app;

import java.sql.Timestamp;
import java.time.Instant;

public class Message {
    public final Type type;
    public final String text;
    public final Instant timestamp;
    public Message(String text,Instant timestamp,Type type) {
        this.text = text;
        this.timestamp = timestamp;
        this.type = type;
    }
    public enum Type{
        RECEIVED,
        SENT,
    }

    @Override
    public String toString() {
        return "["+type  + " at "+ timestamp.toString()+"]" + text;
    }
}
