// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed.jsr223;

import java.util.Map;
import java.io.Reader;
import java.io.Writer;
import javax.script.SimpleBindings;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import javax.script.Bindings;
import java.util.List;
import org.jruby.embed.ScriptingContainer;
import javax.script.ScriptContext;

class JRubyContext implements ScriptContext
{
    private ScriptingContainer container;
    private final List<Integer> scopeList;
    private Bindings globalMap;
    private Bindings engineMap;
    
    static JRubyContext convert(final ScriptingContainer container, final ScriptContext context) {
        if (context instanceof JRubyContext) {
            return (JRubyContext)context;
        }
        final JRubyContext tmpContext = new JRubyContext(container);
        tmpContext.setWriter(context.getWriter());
        tmpContext.setErrorWriter(context.getErrorWriter());
        tmpContext.setReader(context.getReader(), false);
        tmpContext.setEngineScopeBindings(context.getBindings(100));
        tmpContext.setGlobalScopeBindings(context.getBindings(200));
        return tmpContext;
    }
    
    static void update(final JRubyContext jrubyContext, final ScriptContext context) {
        if (jrubyContext == null || context == null) {
            return;
        }
        Bindings tmpBindings = jrubyContext.getEngineScopeBindings();
        Bindings bindings = context.getBindings(100);
        updateBindings(tmpBindings, bindings);
        tmpBindings = jrubyContext.getGlobalScopeBindings();
        if (tmpBindings == null) {
            return;
        }
        bindings = context.getBindings(200);
        updateBindings(tmpBindings, bindings);
    }
    
    private static void updateBindings(final Bindings tmpBindings, final Bindings bindings) {
        if (tmpBindings == bindings) {
            return;
        }
        final Set<String> keys = ((Map<String, V>)tmpBindings).keySet();
        for (final String key : keys) {
            final Object value = tmpBindings.get(key);
            bindings.put(key, value);
        }
    }
    
    JRubyContext(final ScriptingContainer container) {
        this.globalMap = null;
        this.container = container;
        final List<Integer> list = new ArrayList<Integer>();
        for (final Scope scope : Scope.values()) {
            list.add(scope.getPriority());
        }
        this.scopeList = Collections.unmodifiableList((List<? extends Integer>)list);
        this.engineMap = new SimpleBindings();
    }
    
    private void checkName(final String name) {
        if (name == null) {
            throw new NullPointerException("name is null");
        }
        if (name.length() == 0) {
            throw new IllegalArgumentException("name is empty");
        }
    }
    
    public Object getAttribute(final String name) {
        Object ret = null;
        for (final Scope scope : Scope.values()) {
            ret = this.getAttributeFromScope(scope.getPriority(), name);
            if (ret != null) {
                return ret;
            }
        }
        return ret;
    }
    
    private Object getAttributeFromScope(final int priority, final String name) {
        this.checkName(name);
        if (priority == Scope.ENGINE.getPriority()) {
            Object value = this.engineMap.get(name);
            if (value == null && Utils.isRubyVariable(this.container, name)) {
                value = this.container.get(Utils.getReceiver(this), name);
                this.engineMap.put(name, value);
            }
            return value;
        }
        if (priority != Scope.GLOBAL.getPriority()) {
            throw new IllegalArgumentException("invalid scope");
        }
        if (this.globalMap == null) {
            return null;
        }
        return this.globalMap.get(name);
    }
    
    public Object getAttribute(final String name, final int scope) {
        return this.getAttributeFromScope(scope, name);
    }
    
    public int getAttributesScope(final String name) {
        for (final Scope scope : Scope.values()) {
            final Object ret = this.getAttributeFromScope(scope.getPriority(), name);
            if (ret != null) {
                return scope.getPriority();
            }
        }
        return -1;
    }
    
    public Bindings getBindings(final int priority) {
        if (priority == Scope.ENGINE.getPriority()) {
            return this.engineMap;
        }
        if (priority == Scope.GLOBAL.getPriority()) {
            return this.globalMap;
        }
        throw new IllegalArgumentException("invalid scope");
    }
    
    Bindings getEngineScopeBindings() {
        return this.engineMap;
    }
    
    Bindings getGlobalScopeBindings() {
        return this.globalMap;
    }
    
    public Writer getErrorWriter() {
        return this.container.getErrorWriter();
    }
    
    public Reader getReader() {
        return this.container.getReader();
    }
    
    public List<Integer> getScopes() {
        return this.scopeList;
    }
    
    public Writer getWriter() {
        return this.container.getWriter();
    }
    
    public Object removeAttribute(final String name, final int priority) {
        this.checkName(name);
        final Bindings bindings = this.getBindings(priority);
        if (bindings == null) {
            return null;
        }
        return bindings.remove(name);
    }
    
    public void setAttribute(final String key, final Object value, final int priority) {
        final Bindings bindings = this.getBindings(priority);
        if (bindings == null) {
            return;
        }
        bindings.put(key, value);
    }
    
    public void setBindings(final Bindings bindings, final int scope) {
        if (scope == Scope.ENGINE.getPriority() && bindings == null) {
            throw new NullPointerException("null bindings in ENGINE scope");
        }
        if (scope == Scope.ENGINE.getPriority()) {
            this.engineMap = bindings;
        }
        else {
            if (scope != Scope.GLOBAL.getPriority()) {
                throw new IllegalArgumentException("invalid scope");
            }
            this.globalMap = bindings;
        }
    }
    
    void setEngineScopeBindings(final Bindings bindings) {
        this.engineMap = bindings;
    }
    
    void setGlobalScopeBindings(final Bindings bindings) {
        this.globalMap = bindings;
    }
    
    public void setErrorWriter(final Writer errorWriter) {
        if (errorWriter == null) {
            return;
        }
        if (this.getErrorWriter() == errorWriter) {
            return;
        }
        this.container.setErrorWriter(errorWriter);
    }
    
    public void setReader(final Reader reader) {
        this.setReader(reader, true);
    }
    
    void setReader(final Reader reader, final boolean updateContainer) {
        if (reader == null) {
            return;
        }
        if (this.getReader() == reader) {
            return;
        }
        if (updateContainer) {
            this.container.setReader(reader);
        }
    }
    
    public void setWriter(final Writer writer) {
        if (writer == null) {
            return;
        }
        if (this.getWriter() == writer) {
            return;
        }
        this.container.setWriter(writer);
    }
    
    public enum Scope
    {
        ENGINE(100), 
        GLOBAL(200);
        
        private final int priority;
        
        private Scope(final int priority) {
            this.priority = priority;
        }
        
        int getPriority() {
            return this.priority;
        }
    }
}
