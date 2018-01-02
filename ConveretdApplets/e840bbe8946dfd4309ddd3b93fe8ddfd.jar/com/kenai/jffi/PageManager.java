// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jffi;

public abstract class PageManager
{
    public static final int PROT_EXEC = 4;
    public static final int PROT_READ = 1;
    public static final int PROT_WRITE = 2;
    private final Foreign foreign;
    
    public PageManager() {
        this.foreign = Foreign.getInstance();
    }
    
    final Foreign getForeign() {
        return this.foreign;
    }
    
    public static final PageManager getInstance() {
        return SingletonHolder.INSTANCE;
    }
    
    public final long pageSize() {
        return this.getForeign().pageSize();
    }
    
    public abstract long allocatePages(final int p0, final int p1);
    
    public abstract void freePages(final long p0, final int p1);
    
    public abstract void protectPages(final long p0, final int p1, final int p2);
    
    private static final class SingletonHolder
    {
        public static final PageManager INSTANCE;
        
        static {
            INSTANCE = ((Platform.getPlatform().getOS() == Platform.OS.WINDOWS) ? new Windows() : new Unix());
        }
    }
    
    static final class Unix extends PageManager
    {
        public long allocatePages(final int npages, final int protection) {
            final long sz = npages * this.pageSize();
            return this.getForeign().mmap(0L, sz, protection, 258, -1, 0L);
        }
        
        public void freePages(final long address, final int npages) {
            this.getForeign().munmap(address, npages * this.pageSize());
        }
        
        public void protectPages(final long address, final int npages, final int protection) {
            this.getForeign().mprotect(address, npages * this.pageSize(), protection);
        }
    }
    
    static final class Windows extends PageManager
    {
        public long allocatePages(final int npages, final int protection) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
        public void freePages(final long address, final int npages) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
        public void protectPages(final long address, final int npages, final int protection) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
