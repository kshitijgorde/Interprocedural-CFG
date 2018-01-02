// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed.variable;

import org.jruby.javasupport.JavaEmbedUtils;
import java.util.List;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.scope.ManyVarsDynamicScope;
import org.jruby.Ruby;
import org.jruby.embed.internal.BiVariableMap;
import org.jruby.RubyObject;
import org.jruby.embed.LocalVariableBehavior;

public class VariableInterceptor
{
    public static BiVariable getVariableInstance(final LocalVariableBehavior behavior, final RubyObject receiver, final String name, final Object... value) {
        if (value == null || value.length < 1) {
            return null;
        }
        if ("ARGV".equals(name)) {
            return Argv.getInstance(receiver, name, value);
        }
        switch (behavior) {
            case GLOBAL: {
                return LocalGlobalVariable.getInstance(receiver, name, value);
            }
            case BSF: {
                final BiVariable[] bEntries = { PersistentLocalVariable.getInstance(receiver, name, value), GlobalVariable.getInstance(receiver, name, value) };
                return resolve(bEntries);
            }
            case PERSISTENT: {
                final BiVariable[] pEntries = { GlobalVariable.getInstance(receiver, name, value), InstanceVariable.getInstance(receiver, name, value), ClassVariable.getInstance(receiver, name, value), Constant.getInstance(receiver, name, value), PersistentLocalVariable.getInstance(receiver, name, value) };
                return resolve(pEntries);
            }
            default: {
                final BiVariable[] tEntries = { GlobalVariable.getInstance(receiver, name, value), InstanceVariable.getInstance(receiver, name, value), ClassVariable.getInstance(receiver, name, value), Constant.getInstance(receiver, name, value), TransientLocalVariable.getInstance(receiver, name, value) };
                return resolve(tEntries);
            }
        }
    }
    
    private static BiVariable resolve(final BiVariable[] entries) {
        for (final BiVariable e : entries) {
            if (e != null) {
                return e;
            }
        }
        return null;
    }
    
    public static void inject(final BiVariableMap map, final Ruby runtime, final ManyVarsDynamicScope scope, final int depth, final IRubyObject receiver) {
        if (scope != null && scope.getValues().length > 0) {
            final IRubyObject[] values4Injection = map.getLocalVarValues();
            if (values4Injection != null && values4Injection.length > 0) {
                for (int i = 0; i < values4Injection.length; ++i) {
                    scope.setValue(i, values4Injection[i], depth);
                }
            }
        }
        final List<BiVariable> variables = (List<BiVariable>)map.getVariables();
        if (variables == null) {
            return;
        }
        for (int i = 0; i < variables.size(); ++i) {
            variables.get(i).inject();
        }
    }
    
    public static void retrieve(final LocalVariableBehavior behavior, final BiVariableMap map, final RubyObject receiver) {
        Argv.retrieve(receiver, map);
        switch (behavior) {
            case GLOBAL: {
                LocalGlobalVariable.retrieve(receiver, map);
                return;
            }
            case BSF: {
                PersistentLocalVariable.retrieve(receiver, map);
                return;
            }
            case PERSISTENT: {
                PersistentLocalVariable.retrieve(receiver, map);
                break;
            }
        }
        InstanceVariable.retrieve(receiver, map);
        GlobalVariable.retrieve(receiver, map);
        ClassVariable.retrieve(receiver, map);
        Constant.retrieve(receiver, map);
    }
    
    public static void tryLazyRetrieval(final LocalVariableBehavior behavior, final BiVariableMap map, final IRubyObject receiver, final Object key) {
        if (Argv.isValidName(key)) {
            Argv.retrieveByKey((RubyObject)receiver, map, (String)key);
            return;
        }
        switch (behavior) {
            case GLOBAL: {
                if (LocalGlobalVariable.isValidName(key)) {
                    LocalGlobalVariable.retrieveByKey(receiver.getRuntime(), map, (String)key);
                    break;
                }
                break;
            }
            case BSF: {
                break;
            }
            default: {
                if (GlobalVariable.isValidName(key)) {
                    GlobalVariable.retrieveByKey(receiver.getRuntime(), map, (String)key);
                    break;
                }
                if (InstanceVariable.isValidName(key)) {
                    InstanceVariable.retrieveByKey((RubyObject)receiver, map, (String)key);
                    break;
                }
                if (ClassVariable.isValidName(key)) {
                    ClassVariable.retrieveByKey((RubyObject)receiver, map, (String)key);
                    break;
                }
                if (Constant.isValidName(key)) {
                    Constant.retrieveByKey((RubyObject)receiver, map, (String)key);
                    break;
                }
                break;
            }
        }
    }
    
    public static void terminateGlobalVariables(final LocalVariableBehavior behavior, final List<BiVariable> variables, final Ruby runtime) {
        if (variables == null) {
            return;
        }
        if (LocalVariableBehavior.GLOBAL == behavior) {
            for (int i = 0; i < variables.size(); ++i) {
                if (BiVariable.Type.LocalGlobalVariable == variables.get(i).getType()) {
                    final IRubyObject irobj = JavaEmbedUtils.javaToRuby(runtime, null);
                    runtime.getGlobalVariables().set("$" + variables.get(i).getName(), irobj);
                }
            }
        }
    }
    
    public static void terminateLocalVariables(final LocalVariableBehavior behavior, final List<String> varNames, final List<BiVariable> variables) {
        if (variables == null) {
            return;
        }
        if (LocalVariableBehavior.TRANSIENT == behavior) {
            for (int i = 0; i < variables.size(); ++i) {
                if (BiVariable.Type.LocalVariable == variables.get(i).getType()) {
                    varNames.remove(i);
                    variables.remove(i);
                }
            }
        }
    }
    
    public static boolean isKindOfRubyVariable(final LocalVariableBehavior behavior, final String name) {
        if ("ARGV".equals(name)) {
            return true;
        }
        switch (behavior) {
            case GLOBAL: {
                return LocalGlobalVariable.isValidName(name);
            }
            case BSF: {
                return PersistentLocalVariable.isValidName(name) || GlobalVariable.isValidName(name);
            }
            case PERSISTENT: {
                return GlobalVariable.isValidName(name) || PersistentLocalVariable.isValidName(name) || InstanceVariable.isValidName(name) || Constant.isValidName(name) || ClassVariable.isValidName(name);
            }
            default: {
                return GlobalVariable.isValidName(name) || TransientLocalVariable.isValidName(name) || InstanceVariable.isValidName(name) || Constant.isValidName(name) || ClassVariable.isValidName(name);
            }
        }
    }
}
