import org.jruby.embed.ScriptingContainer;

// 
// Decompiled by Procyon v0.5.30
// 

public class Launcher
{
    public static void main(final String[] array) {
        new ScriptingContainer().runScriptlet("require 'bootstrap_jar_client'");
    }
}
