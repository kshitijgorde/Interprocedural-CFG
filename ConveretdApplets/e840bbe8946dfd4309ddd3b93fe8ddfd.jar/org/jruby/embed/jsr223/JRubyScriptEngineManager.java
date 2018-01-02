// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed.jsr223;

import java.util.Collections;
import java.util.ArrayList;
import javax.script.ScriptEngine;
import java.util.List;
import java.util.Iterator;
import javax.script.SimpleBindings;
import javax.script.ScriptException;
import javax.script.Bindings;
import java.util.HashMap;
import javax.script.ScriptEngineFactory;
import java.util.HashSet;

public class JRubyScriptEngineManager
{
    private static final String service = "META-INF/services/javax.script.ScriptEngineFactory";
    private HashSet<ScriptEngineFactory> factories;
    private HashMap<String, ScriptEngineFactory> nameMap;
    private HashMap<String, ScriptEngineFactory> extensionMap;
    private HashMap<String, ScriptEngineFactory> mimetypeMap;
    private Bindings globalMap;
    
    public JRubyScriptEngineManager() throws ScriptException {
        this(null);
    }
    
    public JRubyScriptEngineManager(final ClassLoader loader) throws ScriptException {
        this.init(loader);
    }
    
    private void init(final ClassLoader loader) throws ScriptException {
        this.nameMap = new HashMap<String, ScriptEngineFactory>();
        this.extensionMap = new HashMap<String, ScriptEngineFactory>();
        this.mimetypeMap = new HashMap<String, ScriptEngineFactory>();
        this.globalMap = new SimpleBindings();
        try {
            this.factories = (HashSet<ScriptEngineFactory>)new ServiceFinder(loader, "META-INF/services/javax.script.ScriptEngineFactory").getServices();
            if (this.factories.isEmpty()) {
                System.err.println("no factory");
            }
            this.prepareMaps();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new ScriptException(e);
        }
    }
    
    private void prepareMaps() {
        for (final ScriptEngineFactory factory : this.factories) {
            final List<String> names = factory.getNames();
            for (final String name : names) {
                this.nameMap.put(name, factory);
            }
            final List<String> extensions = factory.getExtensions();
            for (final String extension : extensions) {
                this.extensionMap.put(extension, factory);
            }
            final List<String> mimeTypes = factory.getMimeTypes();
            for (final String mimeType : mimeTypes) {
                this.mimetypeMap.put(mimeType, factory);
            }
        }
    }
    
    public void setBindings(final Bindings b) {
        if (b == null) {
            throw new IllegalArgumentException("Null bindings");
        }
        this.globalMap = b;
    }
    
    public Bindings getBindings() {
        return this.globalMap;
    }
    
    public void put(final String key, final Object value) {
        this.globalMap.put(key, value);
    }
    
    public Object get(final String key) {
        return this.globalMap.get(key);
    }
    
    public ScriptEngine getEngineByName(final String shortName) {
        if (shortName == null) {
            throw new NullPointerException("Null shortName");
        }
        final ScriptEngineFactory factory = this.nameMap.get(shortName);
        if (factory == null) {
            throw new IllegalArgumentException("No engine for " + shortName);
        }
        final ScriptEngine engine = factory.getScriptEngine();
        engine.getContext().setBindings(this.globalMap, 200);
        return engine;
    }
    
    public ScriptEngine getEngineByExtension(final String extension) {
        if (extension == null) {
            throw new NullPointerException("Null extension");
        }
        final ScriptEngineFactory factory = this.extensionMap.get(extension);
        if (factory == null) {
            throw new IllegalArgumentException("No engine for " + extension);
        }
        final ScriptEngine engine = factory.getScriptEngine();
        engine.getContext().setBindings(this.globalMap, 200);
        return engine;
    }
    
    public ScriptEngine getEngineByMimeType(final String mimeType) {
        if (mimeType == null) {
            throw new NullPointerException("Null mimeType");
        }
        final ScriptEngineFactory factory = this.mimetypeMap.get(mimeType);
        if (factory == null) {
            throw new IllegalArgumentException("No engine for " + mimeType);
        }
        final ScriptEngine engine = factory.getScriptEngine();
        engine.getContext().setBindings(this.globalMap, 200);
        return engine;
    }
    
    public List<ScriptEngineFactory> getEngineFactories() {
        final List<ScriptEngineFactory> l = new ArrayList<ScriptEngineFactory>();
        for (final ScriptEngineFactory factory : this.factories) {
            l.add(factory);
        }
        return Collections.unmodifiableList((List<? extends ScriptEngineFactory>)l);
    }
    
    public void registerEngineName(final String name, final ScriptEngineFactory factory) {
        if (name == null || factory == null) {
            throw new NullPointerException("name and/or factory is null.");
        }
        this.nameMap.put(name, factory);
    }
    
    public void registerEngineMimeType(final String type, final ScriptEngineFactory factory) {
        if (type == null || factory == null) {
            throw new NullPointerException("type and/or factory is null.");
        }
        this.mimetypeMap.put(type, factory);
    }
    
    public void registerEngineExtension(final String extension, final ScriptEngineFactory factory) {
        if (extension == null || factory == null) {
            throw new NullPointerException("extension and/or factory is null.");
        }
        this.extensionMap.put(extension, factory);
    }
}
