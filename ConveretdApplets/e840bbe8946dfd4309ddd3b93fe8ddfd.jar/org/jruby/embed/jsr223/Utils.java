// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed.jsr223;

import java.util.Map;
import org.jruby.embed.variable.TransientLocalVariable;
import org.jruby.embed.LocalVariableBehavior;
import org.jruby.embed.variable.VariableInterceptor;
import java.util.Iterator;
import java.util.Set;
import javax.script.Bindings;
import org.jruby.embed.ScriptingContainer;
import org.jruby.embed.AttributeName;
import javax.script.ScriptContext;

public class Utils
{
    static int getLineNumber(final ScriptContext context) {
        final Object obj = context.getAttribute(AttributeName.LINENUMBER.toString(), 100);
        if (obj instanceof Integer) {
            return (int)obj;
        }
        return 0;
    }
    
    static Object getReceiver(final ScriptContext context) {
        return context.getAttribute(AttributeName.RECEIVER.toString(), 100);
    }
    
    static String getFilename(final ScriptContext context) {
        final Object filename = context.getAttribute("javax.script.filename");
        return (String)((filename != null) ? filename : "<script>");
    }
    
    static boolean isTerminationOn(final ScriptContext context) {
        boolean termination = false;
        final Object obj = context.getAttribute(AttributeName.TERMINATION.toString());
        if (obj != null && obj instanceof Boolean && (boolean)obj) {
            termination = true;
        }
        return termination;
    }
    
    static void preEval(final ScriptingContainer container, final JRubyContext jrubyContext) {
        final Object receiver = getReceiverObject(jrubyContext);
        Bindings bindings = jrubyContext.getEngineScopeBindings();
        Set<String> keys = ((Map<String, V>)bindings).keySet();
        for (final String key : keys) {
            final Object value = bindings.get(key);
            put(container, receiver, key, value);
        }
        bindings = jrubyContext.getGlobalScopeBindings();
        if (bindings == null) {
            return;
        }
        keys = ((Map<String, V>)bindings).keySet();
        for (final String key : keys) {
            if (container.getVarMap().containsKey(key)) {
                continue;
            }
            final Object value = bindings.get(key);
            put(container, receiver, key, value);
        }
    }
    
    private static Object getReceiverObject(final JRubyContext jrubyContext) {
        if (jrubyContext == null) {
            return null;
        }
        return jrubyContext.getAttribute(AttributeName.RECEIVER.toString(), 100);
    }
    
    static void postEval(final ScriptingContainer container, final JRubyContext jrubyContext) {
        if (jrubyContext == null) {
            return;
        }
        final Object receiver = getReceiverObject(jrubyContext);
        final Bindings engineMap = jrubyContext.getEngineScopeBindings();
        final int size = ((Map<String, V>)engineMap).keySet().size();
        final String[] names = ((Map<String, V>)engineMap).keySet().toArray(new String[size]);
        for (int i = 0; i < names.length; ++i) {
            if (shouldLVarBeDeleted(container, names[i])) {
                engineMap.remove(names[i]);
            }
        }
        Set<String> keys = (Set<String>)container.getVarMap().keySet();
        if (keys != null && keys.size() > 0) {
            for (final String key : keys) {
                final Object value = container.getVarMap().get(key);
                engineMap.put(adjustKey(key), value);
            }
        }
        final Bindings globalMap = jrubyContext.getGlobalScopeBindings();
        if (globalMap == null) {
            return;
        }
        keys = ((Map<String, V>)globalMap).keySet();
        if (keys != null && keys.size() > 0) {
            for (final String key2 : keys) {
                if (engineMap.containsKey(key2)) {
                    continue;
                }
                final Object value2 = container.getVarMap().get(receiver, key2);
                globalMap.put(key2, value2);
            }
        }
    }
    
    private static Object put(final ScriptingContainer container, final Object receiver, final String key, final Object value) {
        Object oldValue = null;
        final String adjustedKey = adjustKey(key);
        if (isRubyVariable(container, adjustedKey)) {
            oldValue = container.put(receiver, adjustedKey, value);
        }
        else {
            oldValue = container.setAttribute(adjustedKey, value);
        }
        return oldValue;
    }
    
    static boolean isRubyVariable(final ScriptingContainer container, final String name) {
        return VariableInterceptor.isKindOfRubyVariable(container.getProvider().getLocalVariableBehavior(), name);
    }
    
    private static String adjustKey(final String key) {
        if (key.equals("javax.script.argv")) {
            return "ARGV";
        }
        if ("ARGV".equals(key)) {
            return "javax.script.argv";
        }
        return key;
    }
    
    private static boolean shouldLVarBeDeleted(final ScriptingContainer container, final String key) {
        final LocalVariableBehavior behavior = container.getProvider().getLocalVariableBehavior();
        return behavior == LocalVariableBehavior.TRANSIENT && TransientLocalVariable.isValidName(key);
    }
}
