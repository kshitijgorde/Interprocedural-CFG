// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyZlib$s$0$2$crc32;
import org.jruby.RubyZlib$s$0$0$zlib_version;
import org.jruby.RubyZlib$s$0$2$adler32;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyZlib;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyZlib$s$0$0$crc_table;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyZlib$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyZlib$s$0$0$crc_table(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 0, "crc_table", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.class, "crc_table", IRubyObject.class, new Class[] { IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("crc_table", javaMethod);
        DynamicMethod moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("crc_table", moduleMethod);
        javaMethod = new RubyZlib$s$0$2$adler32(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, -1, "adler32", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.class, "adler32", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject[].class }, true);
        cls.addMethodAtBootTimeOnly("adler32", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("adler32", moduleMethod);
        javaMethod = new RubyZlib$s$0$0$zlib_version(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 0, "zlib_version", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.class, "zlib_version", IRubyObject.class, new Class[] { IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("zlib_version", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("zlib_version", moduleMethod);
        javaMethod = new RubyZlib$s$0$2$crc32(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, -1, "crc32", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.class, "crc32", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject[].class }, true);
        cls.addMethodAtBootTimeOnly("crc32", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("crc32", moduleMethod);
        runtime.addBoundMethod("org.jruby.RubyZlib.crc_table", "crc_table");
        runtime.addBoundMethod("org.jruby.RubyZlib.adler32", "adler32");
        runtime.addBoundMethod("org.jruby.RubyZlib.zlib_version", "zlib_version");
        runtime.addBoundMethod("org.jruby.RubyZlib.crc32", "crc32");
    }
}
