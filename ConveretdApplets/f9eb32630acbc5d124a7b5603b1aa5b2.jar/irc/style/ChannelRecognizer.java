// 
// Decompiled by Procyon v0.5.30
// 

package irc.style;

public class ChannelRecognizer implements WordRecognizer
{
    public boolean recognize(final String s) {
        return s.startsWith("#") && s.length() > 1;
    }
    
    public String getType() {
        return "channel";
    }
}
