// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

public class JarBootstrapMain
{
    public static final String JAR_BOOTSTRAP = "classpath:jar-bootstrap.rb";
    
    public static void main(final String[] args) {
        final String[] newArgs = new String[args.length + 1];
        newArgs[0] = "classpath:jar-bootstrap.rb";
        System.arraycopy(args, 0, newArgs, 1, args.length);
        Main.main(newArgs);
    }
}
