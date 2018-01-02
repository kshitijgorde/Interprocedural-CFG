// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha;

import java.io.File;
import java.io.IOException;
import org.apache.activemq.kaha.impl.KahaStore;
import java.util.concurrent.atomic.AtomicLong;

public final class StoreFactory
{
    public static Store open(final String name, final String mode) throws IOException {
        return new KahaStore(name, mode, new AtomicLong());
    }
    
    public static Store open(final File directory, final String mode) throws IOException {
        return new KahaStore(directory, mode, new AtomicLong());
    }
    
    public static Store open(final String name, final String mode, final AtomicLong size) throws IOException {
        return new KahaStore(name, mode, size);
    }
    
    public static Store open(final File directory, final String mode, final AtomicLong size) throws IOException {
        return new KahaStore(directory, mode, size);
    }
    
    public static boolean delete(final String name) throws IOException {
        final KahaStore store = new KahaStore(name, "rw");
        return store.delete();
    }
    
    public static boolean delete(final File directory) throws IOException {
        final KahaStore store = new KahaStore(directory, "rw");
        return store.delete();
    }
}
