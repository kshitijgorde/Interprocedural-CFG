// 
// Decompiled by Procyon v0.5.30
// 

package anon.pay;

import java.net.MalformedURLException;
import logging.LogHolder;
import logging.LogType;
import java.net.URL;

public class PayMessage
{
    private String m_shortMessage;
    private String m_messageText;
    private URL m_messageLink;
    
    public PayMessage(final String shortMessage) {
        this.m_shortMessage = null;
        this.m_messageText = null;
        this.m_messageLink = null;
        this.m_shortMessage = shortMessage;
    }
    
    public PayMessage(final String shortMessage, final String messageText, final URL messageLink) {
        this.m_shortMessage = null;
        this.m_messageText = null;
        this.m_messageLink = null;
        this.m_shortMessage = shortMessage;
        this.m_messageText = messageText;
        this.m_messageLink = messageLink;
    }
    
    public boolean equals(final Object o) {
        if (!(o instanceof PayMessage)) {
            return false;
        }
        final PayMessage payMessage = (PayMessage)o;
        final boolean equals = this.m_shortMessage.equals(payMessage.getShortMessage());
        final boolean equals2 = this.m_messageText.equals(payMessage.getMessageText());
        final boolean equalsIgnoreCase = this.m_messageLink.toString().equalsIgnoreCase(payMessage.toString());
        return equals && equals2 && equalsIgnoreCase;
    }
    
    public void setShortMessage(final String shortMessage) {
        this.m_shortMessage = shortMessage;
    }
    
    public String getShortMessage() {
        return this.m_shortMessage;
    }
    
    public void setMessageText(final String messageText) {
        this.m_messageText = messageText;
    }
    
    public String getMessageText() {
        return this.m_messageText;
    }
    
    public void setMessageLink(final String s) {
        try {
            this.m_messageLink = new URL(s);
        }
        catch (MalformedURLException ex) {
            LogHolder.log(7, LogType.PAY, "Could not get valid URL from the given String, messageLink will be null");
        }
    }
    
    public void setMessageLink(final URL messageLink) {
        this.m_messageLink = messageLink;
    }
    
    public URL getMessageLink() {
        return this.m_messageLink;
    }
}
