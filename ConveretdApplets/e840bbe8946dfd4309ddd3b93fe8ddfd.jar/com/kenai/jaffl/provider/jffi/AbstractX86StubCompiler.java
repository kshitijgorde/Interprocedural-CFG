// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.provider.jffi;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Collections;
import java.util.WeakHashMap;
import java.util.Map;
import java.nio.ByteBuffer;
import com.kenai.jnr.x86asm.Assembler;
import java.util.Iterator;
import com.kenai.jffi.NativeMethods;
import java.nio.ByteOrder;
import com.kenai.jffi.MemoryIO;
import com.kenai.jffi.NativeMethod;
import java.util.ArrayList;
import com.kenai.jffi.PageManager;
import java.util.LinkedList;
import java.util.List;

abstract class AbstractX86StubCompiler extends StubCompiler
{
    final List<Stub> stubs;
    
    AbstractX86StubCompiler() {
        this.stubs = new LinkedList<Stub>();
    }
    
    void attach(final Class clazz) {
        if (this.stubs.isEmpty()) {
            return;
        }
        long codeSize = 0L;
        for (final Stub stub : this.stubs) {
            codeSize += stub.assembler.codeSize() + 8;
        }
        final PageManager pm = PageManager.getInstance();
        final long npages = (codeSize + pm.pageSize() - 1L) / pm.pageSize();
        final long code = pm.allocatePages((int)npages, 3);
        if (code == 0L) {
            throw new OutOfMemoryError("allocatePages failed for codeSize=" + codeSize);
        }
        final PageHolder page = new PageHolder(pm, code, npages);
        final List<NativeMethod> methods = new ArrayList<NativeMethod>(this.stubs.size());
        long fn = code;
        for (final Stub stub2 : this.stubs) {
            final Assembler asm = stub2.assembler;
            fn = align(fn, 8L);
            final ByteBuffer buf = MemoryIO.getInstance().newDirectByteBuffer(fn, asm.codeSize()).order(ByteOrder.LITTLE_ENDIAN);
            stub2.assembler.relocCode(buf, fn);
            methods.add(new NativeMethod(fn, stub2.name, stub2.signature));
            fn += asm.codeSize();
        }
        pm.protectPages(code, (int)npages, 5);
        NativeMethods.register(clazz, methods);
        StaticDataHolder.PAGES.put(clazz, page);
    }
    
    static final int align(final int offset, final int align) {
        return align + (offset - 1 & ~(align - 1));
    }
    
    static final long align(final long offset, final long align) {
        return align + (offset - 1L & ~(align - 1L));
    }
    
    private static final class StaticDataHolder
    {
        static final Map<Class, PageHolder> PAGES;
        
        static {
            PAGES = Collections.synchronizedMap(new WeakHashMap<Class, PageHolder>());
        }
    }
    
    final class Stub
    {
        final String name;
        final String signature;
        final Assembler assembler;
        
        public Stub(final String name, final String signature, final Assembler assembler) {
            this.name = name;
            this.signature = signature;
            this.assembler = assembler;
        }
    }
    
    static final class PageHolder
    {
        final PageManager pm;
        final long memory;
        final long pageCount;
        
        public PageHolder(final PageManager pm, final long memory, final long pageCount) {
            this.pm = pm;
            this.memory = memory;
            this.pageCount = pageCount;
        }
        
        protected void finalize() throws Throwable {
            try {
                this.pm.freePages(this.memory, (int)this.pageCount);
            }
            catch (Throwable t) {
                Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Exception when freeing native pages: %s", t.getLocalizedMessage());
            }
            finally {
                super.finalize();
            }
        }
    }
}
