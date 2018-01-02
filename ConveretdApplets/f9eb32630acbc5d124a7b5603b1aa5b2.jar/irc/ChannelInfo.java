// 
// Decompiled by Procyon v0.5.30
// 

package irc;

public class ChannelInfo
{
    public String name;
    public String topic;
    public int userCount;
    
    public ChannelInfo(final String name, final String topic, final int userCount) {
        this.name = name;
        this.topic = topic;
        this.userCount = userCount;
    }
}
