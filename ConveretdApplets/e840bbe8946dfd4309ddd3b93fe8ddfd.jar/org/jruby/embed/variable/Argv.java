// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed.variable;

import java.util.List;
import org.jruby.RubyNil;
import org.jruby.embed.internal.BiVariableMap;
import java.util.ArrayList;
import org.jruby.RubyModule;
import org.jruby.runtime.builtin.IRubyObject;
import java.util.Collection;
import org.jruby.RubyArray;
import org.jruby.RubyObject;

public class Argv extends AbstractVariable
{
    private static String pattern;
    
    public static BiVariable getInstance(final RubyObject receiver, final String name, final Object... javaObject) {
        if (name.matches(Argv.pattern)) {
            return new Argv(receiver, name, javaObject);
        }
        return null;
    }
    
    private Argv(final RubyObject receiver, final String name, final Object... javaObjects) {
        super(receiver, name, false);
        assert javaObjects != null;
        this.javaObject = javaObjects[0];
        if (this.javaObject == null) {
            this.javaType = null;
        }
        else if (javaObjects.length > 1) {
            this.javaType = (Class)javaObjects[1];
        }
        else {
            this.javaType = this.javaObject.getClass();
        }
    }
    
    private void updateArgvByJavaObject() {
        final RubyArray ary = RubyArray.newArray(this.receiver.getRuntime());
        if (this.javaObject instanceof Collection) {
            ary.addAll((Collection)this.javaObject);
        }
        else if (this.javaObject instanceof String[]) {
            for (final String s : (String[])this.javaObject) {
                ary.add(s);
            }
        }
        this.irubyObject = ary;
    }
    
    Argv(final IRubyObject receiver, final String name, final IRubyObject irubyObject) {
        super(receiver, name, true, irubyObject);
    }
    
    public BiVariable.Type getType() {
        return BiVariable.Type.Argv;
    }
    
    public static boolean isValidName(final Object name) {
        return AbstractVariable.isValidName(Argv.pattern, name);
    }
    
    public void inject() {
        this.updateArgvByJavaObject();
        RubyModule rubyModule = this.getRubyClass(this.receiver.getRuntime());
        if (rubyModule == null) {
            rubyModule = this.receiver.getRuntime().getCurrentContext().getRubyClass();
        }
        if (rubyModule == null) {
            return;
        }
        rubyModule.storeConstant(this.name, this.irubyObject);
        this.receiver.getRuntime().incrementConstantGeneration();
        this.fromRuby = true;
    }
    
    public void remove() {
        this.javaObject = new ArrayList();
        this.inject();
    }
    
    public static void retrieve(final RubyObject receiver, final BiVariableMap vars) {
        if (vars.isLazy()) {
            return;
        }
        updateARGV(receiver, vars);
    }
    
    private static void updateARGV(final IRubyObject receiver, final BiVariableMap vars) {
        final String name = "ARGV".intern();
        final IRubyObject argv = receiver.getRuntime().getTopSelf().getMetaClass().fastGetConstant(name);
        if (argv == null || argv instanceof RubyNil) {
            return;
        }
        if (vars.containsKey(name)) {
            final BiVariable var = vars.getVariable((RubyObject)receiver.getRuntime().getTopSelf(), name);
            var.setRubyObject(argv);
        }
        else {
            final BiVariable var = new Constant(receiver.getRuntime().getTopSelf(), name, argv);
            ((Constant)var).markInitialized();
            vars.update(name, var);
        }
    }
    
    public static void retrieveByKey(final RubyObject receiver, final BiVariableMap vars, final String key) {
        assert key.equals("ARGV");
        updateARGV(receiver, vars);
    }
    
    public Object getJavaObject() {
        if (this.irubyObject == null || !this.fromRuby) {
            return this.javaObject;
        }
        final RubyArray ary = (RubyArray)this.irubyObject;
        if (this.javaType == null) {
            this.javaObject = new ArrayList();
            ((ArrayList)this.javaObject).addAll(ary);
            return this.javaObject;
        }
        if (this.javaType == String[].class) {
            this.javaObject = new String[ary.size()];
            for (int i = 0; i < ary.size(); ++i) {
                ((String[])this.javaObject)[i] = (String)ary.get(i);
            }
            return this.javaObject;
        }
        if (this.javaObject instanceof List) {
            try {
                ((List)this.javaObject).clear();
                ((List)this.javaObject).addAll(ary);
            }
            catch (UnsupportedOperationException ex) {}
            return this.javaObject;
        }
        return null;
    }
    
    static {
        Argv.pattern = "ARGV";
    }
}
