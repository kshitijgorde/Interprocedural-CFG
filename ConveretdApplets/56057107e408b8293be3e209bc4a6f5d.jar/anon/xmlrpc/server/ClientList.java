// 
// Decompiled by Procyon v0.5.30
// 

package anon.xmlrpc.server;

import java.security.SecureRandom;
import java.util.Hashtable;

class ClientList
{
    private Hashtable m_hashtableClients;
    private SecureRandom m_Random;
    
    public ClientList() {
        this.m_hashtableClients = new Hashtable();
        this.m_Random = new SecureRandom();
    }
    
    synchronized int addNewClient() {
        int n;
        for (n = this.m_Random.nextInt(); this.m_hashtableClients.containsKey(new Integer(n)); n = this.m_Random.nextInt()) {}
        this.m_hashtableClients.put(new Integer(n), new ClientEntry(n));
        return n;
    }
    
    synchronized ClientEntry getClient(final Integer n) {
        return this.m_hashtableClients.get(n);
    }
}
