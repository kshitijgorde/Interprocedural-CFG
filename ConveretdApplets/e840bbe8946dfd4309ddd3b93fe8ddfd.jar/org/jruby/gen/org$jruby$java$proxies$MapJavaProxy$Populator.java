// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.java.proxies.MapJavaProxy$i$flatten;
import org.jruby.java.proxies.MapJavaProxy$i$1$0$replace19;
import org.jruby.java.proxies.MapJavaProxy$i$0$0$dup;
import org.jruby.java.proxies.MapJavaProxy$i$1$0$initialize_copy19;
import org.jruby.java.proxies.MapJavaProxy$i$0$0$keep_if;
import org.jruby.java.proxies.MapJavaProxy$i$0$0$select_bang;
import org.jruby.java.proxies.MapJavaProxy$i$1$0$key;
import org.jruby.java.proxies.MapJavaProxy$i$1$0$set_default_proc;
import org.jruby.java.proxies.MapJavaProxy$i$0$0$select19;
import org.jruby.java.proxies.MapJavaProxy$i$0$0$hash19;
import org.jruby.java.proxies.MapJavaProxy$i$2$0$op_aset19;
import org.jruby.java.proxies.MapJavaProxy$i$0$0$to_s19;
import org.jruby.java.proxies.MapJavaProxy$i$0$0$getCompareByIdentity_p;
import org.jruby.java.proxies.MapJavaProxy$i$0$0$rbClone;
import org.jruby.java.proxies.MapJavaProxy$i$0$0$getCompareByIdentity;
import org.jruby.java.proxies.MapJavaProxy$i$1$0$assoc;
import org.jruby.java.proxies.MapJavaProxy$i$1$0$merge_bang19;
import org.jruby.java.proxies.MapJavaProxy$i$1$0$rassoc;
import org.jruby.java.proxies.MapJavaProxy$i$1$0$index19;
import org.jruby.java.proxies.MapJavaProxy$i$2$0$op_aset;
import org.jruby.java.proxies.MapJavaProxy$i$0$0$hash;
import org.jruby.java.proxies.MapJavaProxy$i$1$0$replace;
import org.jruby.java.proxies.MapJavaProxy$i$1$0$initialize_copy;
import org.jruby.java.proxies.MapJavaProxy$i$1$0$merge_bang;
import org.jruby.java.proxies.MapJavaProxy$i$1$0$index;
import org.jruby.CompatVersion;
import org.jruby.java.proxies.MapJavaProxy$i$0$0$rehash;
import org.jruby.java.proxies.MapJavaProxy$i$0$0$values_at;
import org.jruby.java.proxies.MapJavaProxy$i$0$0$default_proc;
import org.jruby.java.proxies.MapJavaProxy$i$0$0$shift;
import org.jruby.java.proxies.MapJavaProxy$i$0$0$reject;
import org.jruby.java.proxies.MapJavaProxy$i$0$0$each_key;
import org.jruby.java.proxies.MapJavaProxy$i$0$0$inspect;
import org.jruby.java.proxies.MapJavaProxy$i$0$0$select;
import org.jruby.java.proxies.MapJavaProxy$i$default_value_get;
import org.jruby.java.proxies.MapJavaProxy$i$1$0$op_aref;
import org.jruby.java.proxies.MapJavaProxy$i$1$0$delete;
import org.jruby.java.proxies.MapJavaProxy$i$0$0$to_hash;
import org.jruby.java.proxies.MapJavaProxy$i$0$0$to_a;
import org.jruby.java.proxies.MapJavaProxy$i$0$0$reject_bang;
import org.jruby.java.proxies.MapJavaProxy$i$0$0$each_value;
import org.jruby.RubyFixnum;
import org.jruby.java.proxies.MapJavaProxy$i$0$0$rb_size;
import org.jruby.java.proxies.MapJavaProxy$i$1$0$has_key_p;
import org.jruby.java.proxies.MapJavaProxy$i$0$0$indices;
import org.jruby.java.proxies.MapJavaProxy$i$0$0$rb_clear;
import org.jruby.java.proxies.MapJavaProxy$i$0$0$each_pair;
import org.jruby.java.proxies.MapJavaProxy$i$0$0$empty_p;
import org.jruby.java.proxies.MapJavaProxy$i$0$0$delete_if;
import org.jruby.java.proxies.MapJavaProxy$i$0$0$each;
import org.jruby.java.proxies.MapJavaProxy$i$1$0$op_eql19;
import org.jruby.java.proxies.MapJavaProxy$i$1$0$merge;
import org.jruby.java.proxies.MapJavaProxy$i$0$0$to_s;
import org.jruby.java.proxies.MapJavaProxy$i$1$0$op_equal;
import org.jruby.java.proxies.MapJavaProxy$i$0$1$fetch;
import org.jruby.java.proxies.MapJavaProxy$i$1$0$default_value_set;
import org.jruby.java.proxies.MapJavaProxy$i$0$0$rb_values;
import org.jruby.runtime.Block;
import org.jruby.java.proxies.MapJavaProxy$i$0$0$sort;
import org.jruby.RubyHash;
import org.jruby.java.proxies.MapJavaProxy$i$0$0$invert;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyBoolean;
import org.jruby.java.proxies.MapJavaProxy$i$1$0$has_value_p;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.RubyArray;
import org.jruby.java.proxies.MapJavaProxy;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.java.proxies.MapJavaProxy$i$0$0$keys;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$java$proxies$MapJavaProxy$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final CompatVersion compatVersion = cls.getRuntime().getInstanceConfig().getCompatVersion();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new MapJavaProxy$i$0$0$keys(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "keys", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(MapJavaProxy.class, "keys", RubyArray.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("keys", javaMethod);
        javaMethod = new MapJavaProxy$i$1$0$has_value_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "has_value_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(MapJavaProxy.class, "has_value_p", RubyBoolean.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("has_value?", javaMethod);
        cls.addMethodAtBootTimeOnly("value?", javaMethod);
        javaMethod = new MapJavaProxy$i$0$0$invert(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "invert", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(MapJavaProxy.class, "invert", RubyHash.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("invert", javaMethod);
        javaMethod = new MapJavaProxy$i$0$0$sort(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "sort", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(MapJavaProxy.class, "sort", IRubyObject.class, new Class[] { ThreadContext.class, Block.class }, false);
        cls.addMethodAtBootTimeOnly("sort", javaMethod);
        javaMethod = new MapJavaProxy$i$0$0$rb_values(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "rb_values", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(MapJavaProxy.class, "rb_values", RubyArray.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("values", javaMethod);
        javaMethod = new MapJavaProxy$i$1$0$default_value_set(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "default_value_set", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(MapJavaProxy.class, "default_value_set", IRubyObject.class, new Class[] { IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("default=", javaMethod);
        javaMethod = new MapJavaProxy$i$0$1$fetch(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "fetch", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(MapJavaProxy.class, "fetch", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject[].class, Block.class }, false);
        cls.addMethodAtBootTimeOnly("fetch", javaMethod);
        javaMethod = new MapJavaProxy$i$1$0$op_equal(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "op_equal", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(MapJavaProxy.class, "op_equal", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("==", javaMethod);
        javaMethod = new MapJavaProxy$i$0$0$to_s(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_s", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(MapJavaProxy.class, "to_s", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("to_s", javaMethod);
        javaMethod = new MapJavaProxy$i$1$0$merge(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "merge", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(MapJavaProxy.class, "merge", RubyHash.class, new Class[] { ThreadContext.class, IRubyObject.class, Block.class }, false);
        cls.addMethodAtBootTimeOnly("merge", javaMethod);
        javaMethod = new MapJavaProxy$i$1$0$op_eql19(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "op_eql19", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(MapJavaProxy.class, "op_eql19", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("eql?", javaMethod);
        javaMethod = new MapJavaProxy$i$0$0$each(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "each", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(MapJavaProxy.class, "each", IRubyObject.class, new Class[] { ThreadContext.class, Block.class }, false);
        cls.addMethodAtBootTimeOnly("each", javaMethod);
        javaMethod = new MapJavaProxy$i$0$0$delete_if(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "delete_if", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(MapJavaProxy.class, "delete_if", IRubyObject.class, new Class[] { ThreadContext.class, Block.class }, false);
        cls.addMethodAtBootTimeOnly("delete_if", javaMethod);
        javaMethod = new MapJavaProxy$i$0$0$empty_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "empty_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(MapJavaProxy.class, "empty_p", RubyBoolean.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("empty?", javaMethod);
        javaMethod = new MapJavaProxy$i$0$0$each_pair(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "each_pair", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(MapJavaProxy.class, "each_pair", IRubyObject.class, new Class[] { ThreadContext.class, Block.class }, false);
        cls.addMethodAtBootTimeOnly("each_pair", javaMethod);
        javaMethod = new MapJavaProxy$i$0$0$rb_clear(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "rb_clear", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(MapJavaProxy.class, "rb_clear", RubyHash.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("clear", javaMethod);
        javaMethod = new MapJavaProxy$i$0$0$indices(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "indices", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(MapJavaProxy.class, "indices", RubyArray.class, new Class[] { ThreadContext.class, IRubyObject[].class }, false);
        cls.addMethodAtBootTimeOnly("indexes", javaMethod);
        cls.addMethodAtBootTimeOnly("indices", javaMethod);
        javaMethod = new MapJavaProxy$i$1$0$has_key_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "has_key_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(MapJavaProxy.class, "has_key_p", RubyBoolean.class, new Class[] { IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("has_key?", javaMethod);
        cls.addMethodAtBootTimeOnly("key?", javaMethod);
        cls.addMethodAtBootTimeOnly("include?", javaMethod);
        cls.addMethodAtBootTimeOnly("member?", javaMethod);
        javaMethod = new MapJavaProxy$i$0$0$rb_size(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "rb_size", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(MapJavaProxy.class, "rb_size", RubyFixnum.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("size", javaMethod);
        cls.addMethodAtBootTimeOnly("length", javaMethod);
        javaMethod = new MapJavaProxy$i$0$0$each_value(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "each_value", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(MapJavaProxy.class, "each_value", IRubyObject.class, new Class[] { ThreadContext.class, Block.class }, false);
        cls.addMethodAtBootTimeOnly("each_value", javaMethod);
        javaMethod = new MapJavaProxy$i$0$0$reject_bang(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "reject_bang", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(MapJavaProxy.class, "reject_bang", IRubyObject.class, new Class[] { ThreadContext.class, Block.class }, false);
        cls.addMethodAtBootTimeOnly("reject!", javaMethod);
        javaMethod = new MapJavaProxy$i$0$0$to_a(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_a", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(MapJavaProxy.class, "to_a", RubyArray.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("to_a", javaMethod);
        javaMethod = new MapJavaProxy$i$0$0$to_hash(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_hash", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(MapJavaProxy.class, "to_hash", RubyHash.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("to_hash", javaMethod);
        javaMethod = new MapJavaProxy$i$1$0$delete(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "delete", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(MapJavaProxy.class, "delete", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, Block.class }, false);
        cls.addMethodAtBootTimeOnly("delete", javaMethod);
        javaMethod = new MapJavaProxy$i$1$0$op_aref(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "op_aref", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(MapJavaProxy.class, "op_aref", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("[]", javaMethod);
        javaMethod = new MapJavaProxy$i$default_value_get(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "default_value_get", false, CallConfiguration.FrameNoneScopeNone, false);
        cls.addMethodAtBootTimeOnly("default", javaMethod);
        javaMethod = new MapJavaProxy$i$0$0$select(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "select", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(MapJavaProxy.class, "select", IRubyObject.class, new Class[] { ThreadContext.class, Block.class }, false);
        cls.addMethodAtBootTimeOnly("select", javaMethod);
        javaMethod = new MapJavaProxy$i$0$0$inspect(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "inspect", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(MapJavaProxy.class, "inspect", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("inspect", javaMethod);
        javaMethod = new MapJavaProxy$i$0$0$each_key(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "each_key", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(MapJavaProxy.class, "each_key", IRubyObject.class, new Class[] { ThreadContext.class, Block.class }, false);
        cls.addMethodAtBootTimeOnly("each_key", javaMethod);
        javaMethod = new MapJavaProxy$i$0$0$reject(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "reject", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(MapJavaProxy.class, "reject", IRubyObject.class, new Class[] { ThreadContext.class, Block.class }, false);
        cls.addMethodAtBootTimeOnly("reject", javaMethod);
        javaMethod = new MapJavaProxy$i$0$0$shift(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "shift", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(MapJavaProxy.class, "shift", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("shift", javaMethod);
        javaMethod = new MapJavaProxy$i$0$0$default_proc(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "default_proc", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(MapJavaProxy.class, "default_proc", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("default_proc", javaMethod);
        javaMethod = new MapJavaProxy$i$0$0$values_at(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "values_at", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(MapJavaProxy.class, "values_at", RubyArray.class, new Class[] { ThreadContext.class, IRubyObject[].class }, false);
        cls.addMethodAtBootTimeOnly("values_at", javaMethod);
        javaMethod = new MapJavaProxy$i$0$0$rehash(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "rehash", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(MapJavaProxy.class, "rehash", RubyHash.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("rehash", javaMethod);
        runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.keys", "keys");
        runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.has_value_p", "has_value?");
        runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.invert", "invert");
        runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.sort", "sort");
        runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.rb_values", "values");
        runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.default_value_set", "default=");
        runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.fetch", "fetch");
        runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.op_equal", "==");
        runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.to_s", "to_s");
        runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.merge", "merge");
        runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.op_eql19", "eql?");
        runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.each", "each");
        runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.delete_if", "delete_if");
        runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.empty_p", "empty?");
        runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.each_pair", "each_pair");
        runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.rb_clear", "clear");
        runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.indices", "indexes");
        runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.has_key_p", "has_key?");
        runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.rb_size", "size");
        runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.each_value", "each_value");
        runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.reject_bang", "reject!");
        runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.to_a", "to_a");
        runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.to_hash", "to_hash");
        runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.delete", "delete");
        runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.op_aref", "[]");
        runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.default_value_get", "default");
        runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.select", "select");
        runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.inspect", "inspect");
        runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.each_key", "each_key");
        runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.reject", "reject");
        runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.shift", "shift");
        runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.default_proc", "default_proc");
        runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.values_at", "values_at");
        runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.rehash", "rehash");
        if (compatVersion == CompatVersion.RUBY1_8 || compatVersion == CompatVersion.BOTH) {
            javaMethod = new MapJavaProxy$i$1$0$index(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "index", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(MapJavaProxy.class, "index", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("index", javaMethod);
            javaMethod = new MapJavaProxy$i$1$0$merge_bang(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "merge_bang", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(MapJavaProxy.class, "merge_bang", RubyHash.class, new Class[] { ThreadContext.class, IRubyObject.class, Block.class }, false);
            cls.addMethodAtBootTimeOnly("merge!", javaMethod);
            cls.addMethodAtBootTimeOnly("update", javaMethod);
            javaMethod = new MapJavaProxy$i$1$0$initialize_copy(cls, Visibility.PRIVATE);
            TypePopulator.populateMethod(javaMethod, 1, "initialize_copy", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(MapJavaProxy.class, "initialize_copy", RubyHash.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("initialize_copy", javaMethod);
            javaMethod = new MapJavaProxy$i$1$0$replace(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "replace", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(MapJavaProxy.class, "replace", RubyHash.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("replace", javaMethod);
            javaMethod = new MapJavaProxy$i$0$0$hash(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "hash", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(MapJavaProxy.class, "hash", RubyFixnum.class, new Class[0], false);
            cls.addMethodAtBootTimeOnly("hash", javaMethod);
            javaMethod = new MapJavaProxy$i$2$0$op_aset(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 2, "op_aset", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(MapJavaProxy.class, "op_aset", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("[]=", javaMethod);
            cls.addMethodAtBootTimeOnly("store", javaMethod);
            runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.index", "index");
            runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.merge_bang", "merge!");
            runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.initialize_copy", "initialize_copy");
            runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.replace", "replace");
            runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.hash", "hash");
            runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.op_aset", "[]=");
        }
        if (compatVersion == CompatVersion.RUBY1_9 || compatVersion == CompatVersion.BOTH) {
            javaMethod = new MapJavaProxy$i$1$0$index19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "index19", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(MapJavaProxy.class, "index19", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("index", javaMethod);
            javaMethod = new MapJavaProxy$i$1$0$rassoc(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "rassoc", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(MapJavaProxy.class, "rassoc", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("rassoc", javaMethod);
            javaMethod = new MapJavaProxy$i$1$0$merge_bang19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "merge_bang19", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(MapJavaProxy.class, "merge_bang19", RubyHash.class, new Class[] { ThreadContext.class, IRubyObject.class, Block.class }, false);
            cls.addMethodAtBootTimeOnly("merge!", javaMethod);
            cls.addMethodAtBootTimeOnly("update", javaMethod);
            javaMethod = new MapJavaProxy$i$1$0$assoc(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "assoc", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(MapJavaProxy.class, "assoc", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("assoc", javaMethod);
            javaMethod = new MapJavaProxy$i$0$0$getCompareByIdentity(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "getCompareByIdentity", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(MapJavaProxy.class, "getCompareByIdentity", IRubyObject.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("compare_by_identity", javaMethod);
            javaMethod = new MapJavaProxy$i$0$0$rbClone(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "rbClone", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(MapJavaProxy.class, "rbClone", IRubyObject.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("clone", javaMethod);
            javaMethod = new MapJavaProxy$i$0$0$getCompareByIdentity_p(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "getCompareByIdentity_p", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(MapJavaProxy.class, "getCompareByIdentity_p", IRubyObject.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("compare_by_identity?", javaMethod);
            javaMethod = new MapJavaProxy$i$0$0$to_s19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "to_s19", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(MapJavaProxy.class, "to_s19", IRubyObject.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("to_s", javaMethod);
            javaMethod = new MapJavaProxy$i$2$0$op_aset19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 2, "op_aset19", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(MapJavaProxy.class, "op_aset19", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("[]=", javaMethod);
            cls.addMethodAtBootTimeOnly("store", javaMethod);
            javaMethod = new MapJavaProxy$i$0$0$hash19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "hash19", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(MapJavaProxy.class, "hash19", RubyFixnum.class, new Class[0], false);
            cls.addMethodAtBootTimeOnly("hash", javaMethod);
            javaMethod = new MapJavaProxy$i$0$0$select19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "select19", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(MapJavaProxy.class, "select19", IRubyObject.class, new Class[] { ThreadContext.class, Block.class }, false);
            cls.addMethodAtBootTimeOnly("select", javaMethod);
            javaMethod = new MapJavaProxy$i$1$0$set_default_proc(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "set_default_proc", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(MapJavaProxy.class, "set_default_proc", IRubyObject.class, new Class[] { IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("default_proc=", javaMethod);
            javaMethod = new MapJavaProxy$i$1$0$key(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "key", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(MapJavaProxy.class, "key", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("key", javaMethod);
            javaMethod = new MapJavaProxy$i$0$0$select_bang(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "select_bang", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(MapJavaProxy.class, "select_bang", IRubyObject.class, new Class[] { ThreadContext.class, Block.class }, false);
            cls.addMethodAtBootTimeOnly("select!", javaMethod);
            javaMethod = new MapJavaProxy$i$0$0$keep_if(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "keep_if", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(MapJavaProxy.class, "keep_if", IRubyObject.class, new Class[] { ThreadContext.class, Block.class }, false);
            cls.addMethodAtBootTimeOnly("keep_if", javaMethod);
            javaMethod = new MapJavaProxy$i$1$0$initialize_copy19(cls, Visibility.PRIVATE);
            TypePopulator.populateMethod(javaMethod, 1, "initialize_copy19", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(MapJavaProxy.class, "initialize_copy19", RubyHash.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("initialize_copy", javaMethod);
            javaMethod = new MapJavaProxy$i$0$0$dup(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "dup", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(MapJavaProxy.class, "dup", IRubyObject.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("dup", javaMethod);
            javaMethod = new MapJavaProxy$i$1$0$replace19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "replace19", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(MapJavaProxy.class, "replace19", RubyHash.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("replace", javaMethod);
            javaMethod = new MapJavaProxy$i$flatten(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, -1, "flatten", false, CallConfiguration.FrameNoneScopeNone, false);
            cls.addMethodAtBootTimeOnly("flatten", javaMethod);
            runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.index19", "index");
            runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.rassoc", "rassoc");
            runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.merge_bang19", "merge!");
            runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.assoc", "assoc");
            runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.getCompareByIdentity", "compare_by_identity");
            runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.rbClone", "clone");
            runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.getCompareByIdentity_p", "compare_by_identity?");
            runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.to_s19", "to_s");
            runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.op_aset19", "[]=");
            runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.hash19", "hash");
            runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.select19", "select");
            runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.set_default_proc", "default_proc=");
            runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.key", "key");
            runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.select_bang", "select!");
            runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.keep_if", "keep_if");
            runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.initialize_copy19", "initialize_copy");
            runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.dup", "dup");
            runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.replace19", "replace");
            runtime.addBoundMethod("org.jruby.java.proxies.MapJavaProxy.flatten", "flatten");
        }
    }
}