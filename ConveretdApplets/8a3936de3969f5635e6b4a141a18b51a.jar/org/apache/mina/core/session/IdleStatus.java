// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.session;

public final class IdleStatus
{
    public static final IdleStatus READER_IDLE;
    public static final IdleStatus WRITER_IDLE;
    public static final IdleStatus BOTH_IDLE;
    private final String strValue;
    
    private IdleStatus(final String strValue) {
        this.strValue = strValue;
    }
    
    @Override
    public final String toString() {
        return this.strValue;
    }
    
    static {
        READER_IDLE = new IdleStatus("reader idle");
        WRITER_IDLE = new IdleStatus("writer idle");
        BOTH_IDLE = new IdleStatus("both idle");
    }
}
