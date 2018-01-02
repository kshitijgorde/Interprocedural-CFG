// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.jmdns;

import java.util.logging.Logger;

final class DNSQuestion extends DNSEntry
{
    private static Logger logger;
    
    DNSQuestion(final String name, final int type, final int clazz) {
        super(name, type, clazz);
    }
    
    boolean answeredBy(final DNSRecord rec) {
        return this.clazz == rec.clazz && (this.type == rec.type || this.type == 255) && this.name.equals(rec.name);
    }
    
    @Override
    public String toString() {
        return this.toString("question", null);
    }
    
    static {
        DNSQuestion.logger = Logger.getLogger(DNSQuestion.class.toString());
    }
}
