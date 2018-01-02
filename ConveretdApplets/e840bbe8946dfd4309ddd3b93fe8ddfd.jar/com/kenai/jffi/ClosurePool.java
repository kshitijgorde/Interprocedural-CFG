// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jffi;

import java.lang.reflect.Method;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.List;

final class ClosurePool
{
    private final List<MagazineHolder> partial;
    private final List<MagazineHolder> full;
    private final Set<Magazine> magazines;
    private final CallContext callContext;
    private static final Closure NULL_CLOSURE;
    
    ClosurePool(final CallContext callContext) {
        this.partial = new LinkedList<MagazineHolder>();
        this.full = new LinkedList<MagazineHolder>();
        this.magazines = new HashSet<Magazine>();
        this.callContext = callContext;
    }
    
    synchronized void recycle(final Magazine magazine) {
        magazine.recycle();
        if (!magazine.isEmpty()) {
            final MagazineHolder h = new MagazineHolder(this, magazine);
            if (magazine.isFull()) {
                this.full.add(h);
            }
            else {
                this.partial.add(h);
            }
        }
        else {
            this.magazines.remove(magazine);
        }
    }
    
    private synchronized MagazineHolder getMagazineHolder() {
        if (!this.partial.isEmpty()) {
            return this.partial.get(0);
        }
        if (!this.full.isEmpty()) {
            final MagazineHolder h = this.full.remove(0);
            this.partial.add(h);
            return h;
        }
        final Magazine m = new Magazine(this.callContext);
        final MagazineHolder h2 = new MagazineHolder(this, m);
        this.partial.add(h2);
        this.magazines.add(m);
        return h2;
    }
    
    public synchronized Closure.Handle newClosureHandle(final Closure closure) {
        Magazine.Slot s = null;
        MagazineHolder h = null;
        do {
            h = this.getMagazineHolder();
            s = h.magazine.get();
            if (s == null) {
                this.partial.remove(0);
            }
        } while (s == null);
        s.proxy.closure = closure;
        return new Handle(s, h);
    }
    
    static {
        NULL_CLOSURE = new Closure() {
            public void invoke(final Buffer buffer) {
            }
        };
    }
    
    private static final class Handle implements Closure.Handle
    {
        private final MagazineHolder holder;
        private final Magazine.Slot slot;
        private volatile boolean disposed;
        
        Handle(final Magazine.Slot slot, final MagazineHolder holder) {
            this.disposed = false;
            this.slot = slot;
            this.holder = holder;
        }
        
        public long getAddress() {
            return this.slot.cbAddress;
        }
        
        public void setAutoRelease(final boolean autorelease) {
            this.slot.autorelease = autorelease;
        }
        
        @Deprecated
        public void free() {
            this.dispose();
        }
        
        public synchronized void dispose() {
            if (this.disposed) {
                throw new IllegalStateException("closure already disposed");
            }
            this.disposed = true;
            this.slot.autorelease = true;
        }
    }
    
    private static final class Magazine
    {
        private static final MemoryIO IO;
        private final Foreign foreign;
        private final CallContext ctx;
        private final long magazine;
        private boolean nativeEmpty;
        private final List<Slot> free;
        private final List<Slot> all;
        
        Magazine(final CallContext ctx) {
            this.foreign = Foreign.getInstance();
            this.nativeEmpty = false;
            this.free = new ArrayList<Slot>();
            this.all = new ArrayList<Slot>();
            this.ctx = ctx;
            this.magazine = this.foreign.newClosureMagazine(ctx.getAddress(), Proxy.METHOD);
        }
        
        Slot get() {
            if (!this.free.isEmpty()) {
                return this.free.remove(this.free.size() - 1);
            }
            return this.nativeEmpty ? null : this.newSlot();
        }
        
        private Slot newSlot() {
            final Proxy proxy = new Proxy(this.ctx);
            final long h = this.foreign.closureMagazineGet(this.magazine, proxy);
            if (h == 0L) {
                this.nativeEmpty = true;
                return null;
            }
            final Slot s = new Slot(h, proxy);
            this.all.add(s);
            return s;
        }
        
        boolean isFull() {
            return this.free.size() == this.all.size();
        }
        
        boolean isEmpty() {
            return this.free.isEmpty();
        }
        
        void recycle() {
            this.free.clear();
            for (final Slot s : this.all) {
                if (s.autorelease) {
                    s.proxy.closure = ClosurePool.NULL_CLOSURE;
                    this.free.add(s);
                }
            }
        }
        
        protected void finalize() throws Throwable {
            try {
                boolean release = true;
                for (final Slot s : this.all) {
                    if (!s.autorelease) {
                        release = false;
                        break;
                    }
                }
                if (this.magazine != 0L && release) {
                    this.foreign.freeClosureMagazine(this.magazine);
                }
            }
            finally {
                super.finalize();
            }
        }
        
        static {
            IO = MemoryIO.getInstance();
        }
        
        static final class Slot
        {
            final long handle;
            final long cbAddress;
            final Proxy proxy;
            volatile boolean autorelease;
            
            public Slot(final long handle, final Proxy proxy) {
                this.handle = handle;
                this.proxy = proxy;
                this.autorelease = true;
                this.cbAddress = Magazine.IO.getAddress(handle);
            }
        }
    }
    
    private static final class MagazineHolder
    {
        private final WeakReference<ClosurePool> poolref;
        private final Magazine magazine;
        
        public MagazineHolder(final ClosurePool pool, final Magazine magazine) {
            this.poolref = new WeakReference<ClosurePool>(pool);
            this.magazine = magazine;
        }
        
        protected void finalize() throws Throwable {
            try {
                final ClosurePool pool = this.poolref.get();
                if (pool != null) {
                    pool.recycle(this.magazine);
                }
            }
            finally {
                super.finalize();
            }
        }
    }
    
    static final class Proxy
    {
        static final Method METHOD;
        final CallContext callContext;
        volatile Closure closure;
        
        private static final Method getMethod() {
            try {
                return Proxy.class.getDeclaredMethod("invoke", Long.TYPE, Long.TYPE);
            }
            catch (Throwable ex) {
                throw new RuntimeException(ex);
            }
        }
        
        Proxy(final CallContext callContext) {
            this.closure = ClosurePool.NULL_CLOSURE;
            this.callContext = callContext;
        }
        
        void invoke(final long retvalAddress, final long paramAddress) {
            this.closure.invoke(new DirectClosureBuffer(this.callContext, retvalAddress, paramAddress));
        }
        
        static {
            METHOD = getMethod();
        }
    }
}
