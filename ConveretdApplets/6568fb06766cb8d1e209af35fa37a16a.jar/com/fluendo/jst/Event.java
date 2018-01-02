// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jst;

public class Event
{
    public static final int FLUSH_START = 1;
    public static final int FLUSH_STOP = 2;
    public static final int EOS = 3;
    public static final int NEWSEGMENT = 4;
    public static final int SEEK = 5;
    private static String[] typeNames;
    private int type;
    private int format;
    private boolean update;
    private long start;
    private long stop;
    private long position;
    
    private Event(final int type) {
        this.position = -1L;
        this.type = type;
    }
    
    public String toString() {
        final String s = Event.typeNames[this.type];
        switch (this.type) {
            case 5: {
                return "[Event] type: " + s + ", format: " + this.format + ", position: " + this.position;
            }
            case 4: {
                return "[Event] type: " + s + (this.update ? ", update" : ", non-update") + ", format: " + this.format + ", start: " + this.start + ", stop: " + this.stop + ", position: " + this.position;
            }
            default: {
                return "[Event] type: " + s;
            }
        }
    }
    
    public int getType() {
        return this.type;
    }
    
    public static Event newEOS() {
        return new Event(3);
    }
    
    public static Event newFlushStart() {
        return new Event(1);
    }
    
    public static Event newFlushStop() {
        return new Event(2);
    }
    
    public static Event newSeek(final int format, final long position) {
        final Event event = new Event(5);
        event.format = format;
        event.position = position;
        return event;
    }
    
    public long parseSeekPosition() {
        return this.position;
    }
    
    public int parseSeekFormat() {
        return this.format;
    }
    
    public static Event newNewsegment(final boolean update, final int format, final long start, final long stop, final long position) {
        final Event event = new Event(4);
        event.update = update;
        event.format = format;
        event.start = start;
        event.stop = stop;
        event.position = position;
        return event;
    }
    
    public boolean parseNewsegmentUpdate() {
        return this.update;
    }
    
    public int parseNewsegmentFormat() {
        return this.format;
    }
    
    public long parseNewsegmentStart() {
        return this.start;
    }
    
    public long parseNewsegmentStop() {
        return this.stop;
    }
    
    public long parseNewsegmentPosition() {
        return this.position;
    }
    
    static {
        Event.typeNames = new String[] { "NULL", "FLUSH_START", "FLUSH_STOP", "EOS", "NEWSEGMENT", "SEEK" };
    }
}
