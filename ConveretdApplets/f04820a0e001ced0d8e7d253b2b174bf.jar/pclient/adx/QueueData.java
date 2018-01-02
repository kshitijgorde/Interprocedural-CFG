// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import java.awt.Color;

public class QueueData
{
    public String sender;
    public String id;
    public String question;
    public Color foreground;
    public Color background;
    public boolean forwarded;
    
    public QueueData() {
        this.forwarded = false;
        this.foreground = null;
        this.background = null;
    }
}
