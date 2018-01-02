// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.anno;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.DumpingInvocationMethodFactory;
import org.jruby.util.JRubyClassLoader;
import java.util.ArrayList;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;

public class InvokerGenerator
{
    public static final boolean DEBUG = false;
    
    public static void main(final String[] args) throws Exception {
        final FileReader fr = new FileReader(args[0]);
        final BufferedReader br = new BufferedReader(fr);
        final List<String> classNames = new ArrayList<String>();
        try {
            String line;
            while ((line = br.readLine()) != null) {
                classNames.add(line);
            }
        }
        finally {
            br.close();
        }
        final DumpingInvocationMethodFactory dumper = new DumpingInvocationMethodFactory(args[1], new JRubyClassLoader(ClassLoader.getSystemClassLoader()));
        for (final String name : classNames) {
            final RubyModule.MethodClumper clumper = new RubyModule.MethodClumper();
            try {
                final Class cls = Class.forName(name, false, InvokerGenerator.class.getClassLoader());
                clumper.clump(cls);
                for (final Map.Entry<String, List<JavaMethodDescriptor>> entry : clumper.getStaticAnnotatedMethods().entrySet()) {
                    dumper.getAnnotatedMethodClass(entry.getValue());
                }
                for (final Map.Entry<String, List<JavaMethodDescriptor>> entry : clumper.getAnnotatedMethods().entrySet()) {
                    dumper.getAnnotatedMethodClass(entry.getValue());
                }
                for (final Map.Entry<String, List<JavaMethodDescriptor>> entry : clumper.getStaticAnnotatedMethods1_8().entrySet()) {
                    dumper.getAnnotatedMethodClass(entry.getValue());
                }
                for (final Map.Entry<String, List<JavaMethodDescriptor>> entry : clumper.getAnnotatedMethods1_8().entrySet()) {
                    dumper.getAnnotatedMethodClass(entry.getValue());
                }
                for (final Map.Entry<String, List<JavaMethodDescriptor>> entry : clumper.getStaticAnnotatedMethods1_9().entrySet()) {
                    dumper.getAnnotatedMethodClass(entry.getValue());
                }
                for (final Map.Entry<String, List<JavaMethodDescriptor>> entry : clumper.getAnnotatedMethods1_9().entrySet()) {
                    dumper.getAnnotatedMethodClass(entry.getValue());
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        }
    }
}
