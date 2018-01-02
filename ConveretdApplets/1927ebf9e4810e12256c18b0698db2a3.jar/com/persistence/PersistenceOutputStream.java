// 
// Decompiled by Procyon v0.5.30
// 

package com.persistence;

import java.io.IOException;
import java.io.ByteArrayOutputStream;

public class PersistenceOutputStream extends ByteArrayOutputStream
{
    private Persistence persistence;
    private String key;
    
    public PersistenceOutputStream(final Persistence persistence, final String key) {
        this.persistence = persistence;
        this.key = key;
    }
    
    public void close() throws IOException {
        super.close();
        this.persistence.putRaw(this.key, this.toByteArray());
    }
}
