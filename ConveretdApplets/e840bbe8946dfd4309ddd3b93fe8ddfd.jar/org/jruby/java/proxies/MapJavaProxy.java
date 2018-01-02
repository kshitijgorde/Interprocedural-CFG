// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.java.proxies;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import org.jruby.RubyString;
import java.util.Iterator;
import java.util.Set;
import org.jruby.javasupport.JavaUtil;
import org.jruby.runtime.Visibility;
import org.jruby.runtime.Block;
import org.jruby.RubyHash;
import org.jruby.RubyArray;
import org.jruby.RubyBoolean;
import org.jruby.RubyFixnum;
import org.jruby.CompatVersion;
import org.jruby.anno.JRubyMethod;
import org.jruby.exceptions.RaiseException;
import java.util.Map;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyClass;
import org.jruby.Ruby;

public class MapJavaProxy extends ConcreteJavaProxy
{
    private RubyHashMap wrappedMap;
    
    public MapJavaProxy(final Ruby runtime, final RubyClass klazz) {
        super(runtime, klazz);
    }
    
    public static RubyClass createMapJavaProxy(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        final RubyClass mapJavaProxy = runtime.defineClass("MapJavaProxy", runtime.getJavaSupport().getConcreteProxyClass(), new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klazz) {
                return new MapJavaProxy(runtime, klazz);
            }
        });
        ConcreteJavaProxy.initialize(mapJavaProxy);
        return mapJavaProxy;
    }
    
    private RubyHashMap getOrCreateRubyHashMap() {
        if (this.wrappedMap == null) {
            this.wrappedMap = new RubyHashMap(this.getRuntime(), this);
        }
        try {
            this.wrappedMap.setSize(((Map)this.getObject()).size());
        }
        catch (RaiseException e) {
            this.wrappedMap.setSize(0);
        }
        return this.wrappedMap;
    }
    
    @JRubyMethod(name = { "default" })
    public IRubyObject default_value_get(final ThreadContext context) {
        return this.getOrCreateRubyHashMap().default_value_get(context);
    }
    
    @JRubyMethod(name = { "default" })
    public IRubyObject default_value_get(final ThreadContext context, final IRubyObject arg) {
        return this.getOrCreateRubyHashMap().default_value_get(context, arg);
    }
    
    @JRubyMethod(name = { "default=" }, required = 1)
    public IRubyObject default_value_set(final IRubyObject defaultValue) {
        return this.getOrCreateRubyHashMap().default_value_set(defaultValue);
    }
    
    @JRubyMethod(name = { "default_proc" })
    public IRubyObject default_proc() {
        return this.getOrCreateRubyHashMap().default_proc();
    }
    
    @JRubyMethod(name = { "default_proc=" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject set_default_proc(final IRubyObject proc) {
        return this.getOrCreateRubyHashMap().set_default_proc(proc);
    }
    
    @JRubyMethod(name = { "inspect" })
    public IRubyObject inspect(final ThreadContext context) {
        return this.getOrCreateRubyHashMap().inspect(context);
    }
    
    @JRubyMethod(name = { "size", "length" })
    public RubyFixnum rb_size() {
        return this.getOrCreateRubyHashMap().rb_size();
    }
    
    @JRubyMethod(name = { "empty?" })
    public RubyBoolean empty_p() {
        return this.getOrCreateRubyHashMap().empty_p();
    }
    
    @JRubyMethod(name = { "to_a" })
    public RubyArray to_a() {
        return this.getOrCreateRubyHashMap().to_a();
    }
    
    @JRubyMethod(name = { "to_s" })
    public IRubyObject to_s(final ThreadContext context) {
        return this.getOrCreateRubyHashMap().to_s(context);
    }
    
    @JRubyMethod(name = { "to_s" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject to_s19(final ThreadContext context) {
        return this.getOrCreateRubyHashMap().to_s19(context);
    }
    
    @JRubyMethod(name = { "rehash" })
    public RubyHash rehash() {
        return this.getOrCreateRubyHashMap().rehash();
    }
    
    @JRubyMethod(name = { "to_hash" })
    public RubyHash to_hash() {
        return this.getOrCreateRubyHashMap().to_hash();
    }
    
    @JRubyMethod(name = { "[]=", "store" }, required = 2, compat = CompatVersion.RUBY1_8)
    public IRubyObject op_aset(final ThreadContext context, final IRubyObject key, final IRubyObject value) {
        return this.getOrCreateRubyHashMap().op_aset(context, key, value);
    }
    
    @JRubyMethod(name = { "[]=", "store" }, required = 2, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_aset19(final ThreadContext context, final IRubyObject key, final IRubyObject value) {
        return this.getOrCreateRubyHashMap().op_aset19(context, key, value);
    }
    
    @JRubyMethod(name = { "==" })
    public IRubyObject op_equal(final ThreadContext context, final IRubyObject other) {
        return this.getOrCreateRubyHashMap().op_equal(context, other);
    }
    
    @JRubyMethod(name = { "eql?" })
    public IRubyObject op_eql19(final ThreadContext context, final IRubyObject other) {
        return this.getOrCreateRubyHashMap().op_eql19(context, other);
    }
    
    @JRubyMethod(name = { "[]" }, required = 1)
    public IRubyObject op_aref(final ThreadContext context, final IRubyObject key) {
        return this.getOrCreateRubyHashMap().op_aref(context, key);
    }
    
    @JRubyMethod(name = { "hash" }, compat = CompatVersion.RUBY1_8)
    public RubyFixnum hash() {
        return this.getOrCreateRubyHashMap().hash();
    }
    
    @JRubyMethod(name = { "hash" }, compat = CompatVersion.RUBY1_9)
    public RubyFixnum hash19() {
        return this.getOrCreateRubyHashMap().hash19();
    }
    
    @JRubyMethod(name = { "fetch" }, required = 1, optional = 1)
    public IRubyObject fetch(final ThreadContext context, final IRubyObject[] args, final Block block) {
        return this.getOrCreateRubyHashMap().fetch(context, args, block);
    }
    
    @JRubyMethod(name = { "has_key?", "key?", "include?", "member?" }, required = 1)
    public RubyBoolean has_key_p(final IRubyObject key) {
        return this.getOrCreateRubyHashMap().has_key_p(key);
    }
    
    @JRubyMethod(name = { "has_value?", "value?" }, required = 1)
    public RubyBoolean has_value_p(final ThreadContext context, final IRubyObject expected) {
        return this.getOrCreateRubyHashMap().has_value_p(context, expected);
    }
    
    @JRubyMethod(name = { "each" })
    public IRubyObject each(final ThreadContext context, final Block block) {
        return this.getOrCreateRubyHashMap().each(context, block);
    }
    
    @JRubyMethod(name = { "each_pair" })
    public IRubyObject each_pair(final ThreadContext context, final Block block) {
        return this.getOrCreateRubyHashMap().each_pair(context, block);
    }
    
    @JRubyMethod(name = { "each_value" })
    public IRubyObject each_value(final ThreadContext context, final Block block) {
        return this.getOrCreateRubyHashMap().each_value(context, block);
    }
    
    @JRubyMethod(name = { "each_key" })
    public IRubyObject each_key(final ThreadContext context, final Block block) {
        return this.getOrCreateRubyHashMap().each_key(context, block);
    }
    
    @JRubyMethod(name = { "select!" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject select_bang(final ThreadContext context, final Block block) {
        return this.getOrCreateRubyHashMap().select_bang(context, block);
    }
    
    @JRubyMethod(name = { "keep_if" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject keep_if(final ThreadContext context, final Block block) {
        return this.getOrCreateRubyHashMap().keep_if(context, block);
    }
    
    @JRubyMethod(name = { "sort" })
    public IRubyObject sort(final ThreadContext context, final Block block) {
        return this.getOrCreateRubyHashMap().sort(context, block);
    }
    
    @JRubyMethod(name = { "index" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject index(final ThreadContext context, final IRubyObject expected) {
        return this.getOrCreateRubyHashMap().index(context, expected);
    }
    
    @JRubyMethod(name = { "index" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject index19(final ThreadContext context, final IRubyObject expected) {
        return this.getOrCreateRubyHashMap().index19(context, expected);
    }
    
    @JRubyMethod(name = { "key" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject key(final ThreadContext context, final IRubyObject expected) {
        return this.getOrCreateRubyHashMap().key(context, expected);
    }
    
    @JRubyMethod(name = { "indexes", "indices" }, rest = true)
    public RubyArray indices(final ThreadContext context, final IRubyObject[] indices) {
        return this.getOrCreateRubyHashMap().indices(context, indices);
    }
    
    @JRubyMethod(name = { "keys" })
    public RubyArray keys() {
        return this.getOrCreateRubyHashMap().keys();
    }
    
    @JRubyMethod(name = { "values" })
    public RubyArray rb_values() {
        return this.getOrCreateRubyHashMap().rb_values();
    }
    
    @JRubyMethod(name = { "shift" })
    public IRubyObject shift(final ThreadContext context) {
        return this.getOrCreateRubyHashMap().shift(context);
    }
    
    @JRubyMethod(name = { "delete" })
    public IRubyObject delete(final ThreadContext context, final IRubyObject key, final Block block) {
        return this.getOrCreateRubyHashMap().delete(context, key, block);
    }
    
    @JRubyMethod(name = { "select" })
    public IRubyObject select(final ThreadContext context, final Block block) {
        return this.getOrCreateRubyHashMap().select(context, block);
    }
    
    @JRubyMethod(name = { "select" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject select19(final ThreadContext context, final Block block) {
        return this.getOrCreateRubyHashMap().select19(context, block);
    }
    
    @JRubyMethod(name = { "delete_if" })
    public IRubyObject delete_if(final ThreadContext context, final Block block) {
        return this.getOrCreateRubyHashMap().delete_if(context, block);
    }
    
    @JRubyMethod(name = { "reject" })
    public IRubyObject reject(final ThreadContext context, final Block block) {
        return this.getOrCreateRubyHashMap().reject(context, block);
    }
    
    @JRubyMethod(name = { "reject!" })
    public IRubyObject reject_bang(final ThreadContext context, final Block block) {
        return this.getOrCreateRubyHashMap().reject_bang(context, block);
    }
    
    @JRubyMethod(name = { "clear" })
    public RubyHash rb_clear() {
        return this.getOrCreateRubyHashMap().rb_clear();
    }
    
    @JRubyMethod(name = { "invert" })
    public RubyHash invert(final ThreadContext context) {
        return this.getOrCreateRubyHashMap().invert(context);
    }
    
    @JRubyMethod(name = { "merge!", "update" }, required = 1, compat = CompatVersion.RUBY1_8)
    public RubyHash merge_bang(final ThreadContext context, final IRubyObject other, final Block block) {
        return this.getOrCreateRubyHashMap().merge_bang(context, other, block);
    }
    
    @JRubyMethod(name = { "merge!", "update" }, required = 1, compat = CompatVersion.RUBY1_9)
    public RubyHash merge_bang19(final ThreadContext context, final IRubyObject other, final Block block) {
        return this.getOrCreateRubyHashMap().merge_bang19(context, other, block);
    }
    
    @JRubyMethod(name = { "merge" })
    public RubyHash merge(final ThreadContext context, final IRubyObject other, final Block block) {
        return this.getOrCreateRubyHashMap().merge(context, other, block);
    }
    
    @JRubyMethod(name = { "initialize_copy" }, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public RubyHash initialize_copy(final ThreadContext context, final IRubyObject other) {
        return this.getOrCreateRubyHashMap().initialize_copy(context, other);
    }
    
    @JRubyMethod(name = { "initialize_copy" }, required = 1, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public RubyHash initialize_copy19(final ThreadContext context, final IRubyObject other) {
        return this.getOrCreateRubyHashMap().initialize_copy19(context, other);
    }
    
    @JRubyMethod(name = { "replace" }, required = 1, compat = CompatVersion.RUBY1_8)
    public RubyHash replace(final ThreadContext context, final IRubyObject other) {
        return this.getOrCreateRubyHashMap().replace(context, other);
    }
    
    @JRubyMethod(name = { "replace" }, required = 1, compat = CompatVersion.RUBY1_9)
    public RubyHash replace19(final ThreadContext context, final IRubyObject other) {
        return this.getOrCreateRubyHashMap().replace19(context, other);
    }
    
    @JRubyMethod(name = { "values_at" }, rest = true)
    public RubyArray values_at(final ThreadContext context, final IRubyObject[] args) {
        return this.getOrCreateRubyHashMap().values_at(context, args);
    }
    
    @JRubyMethod(name = { "assoc" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject assoc(final ThreadContext context, final IRubyObject obj) {
        return this.getOrCreateRubyHashMap().assoc(context, obj);
    }
    
    @JRubyMethod(name = { "rassoc" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject rassoc(final ThreadContext context, final IRubyObject obj) {
        return this.getOrCreateRubyHashMap().rassoc(context, obj);
    }
    
    @JRubyMethod(name = { "flatten" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject flatten(final ThreadContext context) {
        return this.getOrCreateRubyHashMap().flatten(context);
    }
    
    @JRubyMethod(name = { "flatten" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject flatten(final ThreadContext context, final IRubyObject level) {
        return this.getOrCreateRubyHashMap().flatten(context, level);
    }
    
    @JRubyMethod(name = { "compare_by_identity" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject getCompareByIdentity(final ThreadContext context) {
        return this.getOrCreateRubyHashMap().getCompareByIdentity(context);
    }
    
    @JRubyMethod(name = { "compare_by_identity?" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject getCompareByIdentity_p(final ThreadContext context) {
        return this.getOrCreateRubyHashMap().getCompareByIdentity_p(context);
    }
    
    @JRubyMethod(name = { "dup" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject dup(final ThreadContext context) {
        return this.getOrCreateRubyHashMap().dup(context);
    }
    
    @JRubyMethod(name = { "clone" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject rbClone(final ThreadContext context) {
        return this.getOrCreateRubyHashMap().rbClone(context);
    }
    
    private static class RubyHashMap extends RubyHash
    {
        private IRubyObject receiver;
        
        public RubyHashMap(final Ruby runtime, final IRubyObject receiver) {
            super(runtime);
            this.receiver = receiver;
        }
        
        private void setSize(final int size) {
            this.size = size;
        }
        
        public void internalPut(final IRubyObject key, final IRubyObject value, final boolean checkForExisting) {
            if (checkForExisting) {
                final Set<Map.Entry> entries = (Set<Map.Entry>)((Map)((JavaProxy)this.receiver).getObject()).entrySet();
                for (final Map.Entry entry : entries) {
                    final IRubyObject rubyfiedKey = JavaUtil.convertJavaToUsableRubyObject(this.getRuntime(), entry.getKey());
                    if (key.eql(rubyfiedKey)) {
                        entry.setValue(value.toJava(Object.class));
                        return;
                    }
                }
            }
            ((Map)((JavaProxy)this.receiver).getObject()).put(key.toJava(Object.class), value.toJava(Object.class));
            this.size = ((Map)((JavaProxy)this.receiver).getObject()).size();
        }
        
        public IRubyObject internalGet(final IRubyObject key) {
            final Set<Map.Entry> entries = (Set<Map.Entry>)((Map)((JavaProxy)this.receiver).getObject()).entrySet();
            for (final Map.Entry entry : entries) {
                final IRubyObject rubyfiedKey = JavaUtil.convertJavaToUsableRubyObject(this.getRuntime(), entry.getKey());
                if (key.eql(rubyfiedKey)) {
                    return JavaUtil.convertJavaToUsableRubyObject(this.getRuntime(), entry.getValue());
                }
            }
            return null;
        }
        
        public RubyHashEntry internalGetEntry(final IRubyObject key) {
            final Set<Map.Entry> entries = (Set<Map.Entry>)((Map)((JavaProxy)this.receiver).getObject()).entrySet();
            for (final Map.Entry entry : entries) {
                final IRubyObject rubyfiedKey = JavaUtil.convertJavaToUsableRubyObject(this.getRuntime(), entry.getKey());
                if (key.eql(rubyfiedKey)) {
                    final IRubyObject value = JavaUtil.convertJavaToUsableRubyObject(this.getRuntime(), entry.getValue());
                    return new RubyHashEntry(entry.hashCode(), key, value, null, null);
                }
            }
            return RubyHashMap.NO_ENTRY;
        }
        
        public RubyHashEntry internalDelete(final IRubyObject key) {
            final Set<Map.Entry> entries = (Set<Map.Entry>)((Map)((JavaProxy)this.receiver).getObject()).entrySet();
            for (final Map.Entry entry : entries) {
                final IRubyObject rubyfiedKey = JavaUtil.convertJavaToUsableRubyObject(this.getRuntime(), entry.getKey());
                if (key.eql(rubyfiedKey)) {
                    final IRubyObject value = JavaUtil.convertJavaToUsableRubyObject(this.getRuntime(), entry.getValue());
                    final RubyHashEntry rubyEntry = new RubyHashEntry(entry.hashCode(), key, value, null, null);
                    ((Map)((JavaProxy)this.receiver).getObject()).remove(entry.getKey());
                    this.size = ((Map)((JavaProxy)this.receiver).getObject()).size();
                    return rubyEntry;
                }
            }
            return RubyHashMap.NO_ENTRY;
        }
        
        public RubyHashEntry internalDeleteEntry(final RubyHashEntry entry) {
            final Set<Map.Entry> entries = (Set<Map.Entry>)((Map)((JavaProxy)this.receiver).getObject()).entrySet();
            for (final Map.Entry mapEntry : entries) {
                final IRubyObject rubyfiedKey = JavaUtil.convertJavaToUsableRubyObject(this.getRuntime(), mapEntry.getKey());
                if (((IRubyObject)entry.getKey()).eql(rubyfiedKey)) {
                    ((Map)((JavaProxy)this.receiver).getObject()).remove(mapEntry.getKey());
                    this.size = ((Map)((JavaProxy)this.receiver).getObject()).size();
                    return entry;
                }
            }
            return RubyHashMap.NO_ENTRY;
        }
        
        public void visitAll(final Visitor visitor) {
            final Map.Entry[] arr$;
            final Map.Entry[] entries = arr$ = (Map.Entry[])((Map)((JavaProxy)this.receiver).getObject()).entrySet().toArray(new Map.Entry[0]);
            for (final Map.Entry entry : arr$) {
                final IRubyObject key = JavaUtil.convertJavaToUsableRubyObject(this.getRuntime(), entry.getKey());
                final IRubyObject value = JavaUtil.convertJavaToUsableRubyObject(this.getRuntime(), entry.getValue());
                visitor.visit(key, value);
            }
        }
        
        public void op_asetForString(final Ruby runtime, final RubyString key, final IRubyObject value) {
            final Set<Map.Entry> entries = (Set<Map.Entry>)((Map)((JavaProxy)this.receiver).getObject()).entrySet();
            for (final Map.Entry entry : entries) {
                final IRubyObject rubyfiedKey = JavaUtil.convertJavaToUsableRubyObject(this.getRuntime(), entry.getKey());
                if (key.eql(rubyfiedKey)) {
                    entry.setValue(value.toJava(Object.class));
                    return;
                }
            }
            ((Map)((JavaProxy)this.receiver).getObject()).put(key.toJava(String.class), value.toJava(Object.class));
            this.size = ((Map)((JavaProxy)this.receiver).getObject()).size();
        }
        
        public RubyHash rehash() {
            final Map oldMap = (Map)((JavaProxy)this.receiver).getObject();
            final Method[] methods = oldMap.getClass().getMethods();
            try {
                for (int i = 0; i < methods.length; ++i) {
                    if ("clone".equals(methods[i].getName())) {
                        final Map map = (Map)methods[i].invoke(oldMap, new Object[0]);
                        this.dataWrapStruct(map);
                        break;
                    }
                }
            }
            catch (IllegalAccessException ex) {
                throw this.getRuntime().newRuntimeError(ex.getMessage());
            }
            catch (IllegalArgumentException ex2) {
                throw this.getRuntime().newRuntimeError(ex2.getMessage());
            }
            catch (InvocationTargetException ex3) {
                throw this.getRuntime().newRuntimeError(ex3.getMessage());
            }
            return this;
        }
        
        public RubyHash rb_clear() {
            final Map map = (Map)((JavaProxy)this.receiver).getObject();
            if (map != null) {
                map.clear();
                ((JavaProxy)this.receiver).setObject(map);
                this.size = 0;
            }
            return this;
        }
        
        public IRubyObject shift(final ThreadContext context) {
            throw this.getRuntime().newNotImplementedError("shift method is not implemented in a Java Map backed object");
        }
        
        public RubyHash to_hash() {
            final RubyHash hash = new RubyHash(this.getRuntime());
            final Set<Map.Entry> entries = (Set<Map.Entry>)((Map)((JavaProxy)this.receiver).getObject()).entrySet();
            for (final Map.Entry entry : entries) {
                final IRubyObject key = JavaUtil.convertJavaToUsableRubyObject(this.getRuntime(), entry.getKey());
                final IRubyObject value = JavaUtil.convertJavaToUsableRubyObject(this.getRuntime(), entry.getValue());
                if (this.getRuntime().getInstanceConfig().getCompatVersion() == CompatVersion.RUBY1_8) {
                    hash.op_aset(this.getRuntime().getCurrentContext(), key, value);
                }
                else {
                    hash.op_aset19(this.getRuntime().getCurrentContext(), key, value);
                }
            }
            return hash;
        }
    }
}
