// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed.jsr223;

import org.jruby.embed.ScriptingContainer;
import org.jruby.embed.LocalVariableBehavior;
import org.jruby.embed.util.SystemPropertyCatcher;
import org.jruby.embed.LocalContextScope;
import javax.script.ScriptEngine;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Collections;
import java.util.Arrays;
import java.util.Map;
import java.util.List;
import javax.script.ScriptEngineFactory;

public class JRubyEngineFactory implements ScriptEngineFactory
{
    private final String engineName;
    private final String engineVersion;
    private final List<String> extensions;
    private final String languageName;
    private String languageVersion;
    private final List<String> mimeTypes;
    private final List<String> engineIds;
    private Map<String, Object> parameters;
    
    public JRubyEngineFactory() {
        this.engineName = "JSR 223 JRuby Engine";
        this.engineVersion = "1.6.2";
        this.extensions = Collections.unmodifiableList((List<? extends String>)Arrays.asList("rb"));
        this.languageName = "ruby";
        this.languageVersion = "jruby 1.6.2";
        this.mimeTypes = Collections.unmodifiableList((List<? extends String>)Arrays.asList("application/x-ruby"));
        this.engineIds = Collections.unmodifiableList((List<? extends String>)Arrays.asList("ruby", "jruby"));
    }
    
    private void initParameters() {
        (this.parameters = new HashMap<String, Object>()).put("javax.script.engine", this.getEngineName());
        this.parameters.put("javax.script.engine_version", this.getEngineVersion());
        this.parameters.put("javax.script.name", this.getEngineName());
        this.parameters.put("javax.script.language", this.getLanguageName());
        this.parameters.put("javax.script.language_version", this.getLanguageVersion());
        this.parameters.put("THREADING", "THREAD-ISOLATED");
    }
    
    public String getEngineName() {
        return this.engineName;
    }
    
    public String getEngineVersion() {
        return this.engineVersion;
    }
    
    public List getExtensions() {
        return this.extensions;
    }
    
    public String getLanguageName() {
        return this.languageName;
    }
    
    public String getLanguageVersion() {
        return this.languageVersion;
    }
    
    public String getMethodCallSyntax(final String obj, final String m, final String... args) {
        if (m == null || m.length() == 0) {
            return "";
        }
        if (args == null || args.length == 0) {
            if (obj == null || obj.length() == 0) {
                return MessageFormat.format("{0}", m);
            }
            return MessageFormat.format("{0}.{1}", obj, m);
        }
        else {
            final StringBuilder builder = new StringBuilder();
            boolean first = true;
            for (final String arg : args) {
                if (!first) {
                    builder.append(", ");
                }
                first = false;
                builder.append(arg);
            }
            if (obj == null || obj.length() == 0) {
                return MessageFormat.format("{0}({1})", m, builder.toString());
            }
            return MessageFormat.format("{0}.{1}({2})", obj, m, builder.toString());
        }
    }
    
    public List getMimeTypes() {
        return this.mimeTypes;
    }
    
    public List getNames() {
        return this.engineIds;
    }
    
    public String getOutputStatement(final String toDisplay) {
        if (toDisplay == null || toDisplay.length() == 0) {
            return "";
        }
        return "puts " + toDisplay + "\nor\nprint " + toDisplay;
    }
    
    public Object getParameter(final String key) {
        if (this.parameters == null) {
            this.initParameters();
        }
        return this.parameters.get(key);
    }
    
    public String getProgram(final String... statements) {
        if (statements == null || statements.length == 0) {
            return "";
        }
        final StringBuffer sb = new StringBuffer();
        for (final String s : statements) {
            sb.append(s);
            sb.append("\n");
        }
        return new String(sb);
    }
    
    public ScriptEngine getScriptEngine() {
        final LocalContextScope scope = SystemPropertyCatcher.getScope(LocalContextScope.SINGLETON);
        final LocalVariableBehavior behavior = SystemPropertyCatcher.getBehavior(LocalVariableBehavior.GLOBAL);
        final boolean lazy = SystemPropertyCatcher.isLazy(false);
        final ScriptingContainer container = new ScriptingContainer(scope, behavior, lazy);
        SystemPropertyCatcher.setClassLoader(container);
        SystemPropertyCatcher.setConfiguration(container);
        final JRubyEngine engine = new JRubyEngine(container, this);
        return engine;
    }
}
