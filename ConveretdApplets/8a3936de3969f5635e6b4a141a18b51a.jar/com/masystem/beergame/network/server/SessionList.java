// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.network.server;

import java.util.Collection;
import java.util.HashMap;
import org.apache.mina.core.session.IoSession;
import java.util.Map;

public class SessionList
{
    private final Map<String, IoSession> sessionList;
    
    public SessionList() {
        this.sessionList = new HashMap<String, IoSession>();
    }
    
    public final synchronized IoSession get(final String s) {
        return this.sessionList.get(s.toLowerCase());
    }
    
    public final synchronized void add(final IoSession ioSession, final String s) {
        this.sessionList.put(s.toLowerCase(), ioSession);
    }
    
    public final synchronized void remove(final String s) {
        this.sessionList.remove(s.toLowerCase());
    }
    
    public final synchronized Collection<IoSession> getSessions() {
        return this.sessionList.values();
    }
}
