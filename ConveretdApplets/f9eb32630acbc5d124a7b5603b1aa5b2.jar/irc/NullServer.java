// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.util.Vector;
import java.util.Enumeration;

public class NullServer implements Server
{
    public void addServerListener(final ServerListener serverListener) {
    }
    
    public void removeServerListener(final ServerListener serverListener) {
    }
    
    public void say(final String s, final String s2) {
    }
    
    public void execute(final String s) {
    }
    
    public void sendStatusMessage(final String s) {
    }
    
    public String getNick() {
        return "";
    }
    
    public String getUserName() {
        return "";
    }
    
    public String getServerName() {
        return "Null";
    }
    
    public void connect() {
    }
    
    public void disconnect() {
    }
    
    public boolean isConnected() {
        return false;
    }
    
    public void setDefaultSource(final Source source) {
    }
    
    public void leave() {
    }
    
    public void enumerateSourcesAsCreated(final ServerListener serverListener) {
    }
    
    public void enumerateSourcesAsRemoved(final ServerListener serverListener) {
    }
    
    public Enumeration getSources() {
        return new Vector().elements();
    }
}
