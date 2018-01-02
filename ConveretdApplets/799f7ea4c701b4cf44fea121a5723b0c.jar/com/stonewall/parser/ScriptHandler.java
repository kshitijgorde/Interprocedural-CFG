// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser;

import com.stonewall.parser.interpreter.Event;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.stonewall.parser.jdom.SAXHandler;
import org.jdom.Namespace;
import java.util.Iterator;
import org.jdom.Text;
import java.util.Collection;
import org.jdom.Content;
import java.util.ArrayList;
import org.jdom.Attribute;
import java.util.List;
import org.jdom.Element;
import java.util.HashSet;
import org.jdom.filter.ContentFilter;
import org.xmodel.log.Log;
import com.stonewall.parser.interpreter.Interpreter;
import java.util.Set;
import org.jdom.filter.Filter;
import com.stonewall.parser.interpreter.EventListener;

public class ScriptHandler implements EventListener
{
    static Filter textFilter;
    static Set<String> protectedTags;
    private Script currentScript;
    private final Interpreter interpreter;
    static Log log;
    
    static {
        ScriptHandler.textFilter = (Filter)new ContentFilter(4);
        (ScriptHandler.protectedTags = new HashSet<String>()).add("rule");
        ScriptHandler.protectedTags.add("procedure");
        ScriptHandler.protectedTags.add("call");
        ScriptHandler.protectedTags.add("arg");
        ScriptHandler.protectedTags.add("action");
        ScriptHandler.protectedTags.add("foreach");
        ScriptHandler.protectedTags.add("pwhile");
        ScriptHandler.protectedTags.add("pswitch");
        ScriptHandler.protectedTags.add("pcase");
        ScriptHandler.protectedTags.add("abort");
        ScriptHandler.protectedTags.add("append");
        ScriptHandler.protectedTags.add("insert");
        ScriptHandler.protectedTags.add("insertAfter");
        ScriptHandler.protectedTags.add("set");
        ScriptHandler.protectedTags.add("delete");
        ScriptHandler.protectedTags.add("move");
        ScriptHandler.protectedTags.add("assign");
        ScriptHandler.protectedTags.add("log");
        ScriptHandler.log = Log.getLog(ScriptHandler.class);
    }
    
    ScriptHandler(final Interpreter interpreter) {
        (this.interpreter = interpreter).register(this);
    }
    
    void process(final Element e, final String... filter) {
        final Set<String> filterSet = new HashSet<String>();
        for (final String s : filter) {
            filterSet.add(s);
        }
        this.process(e, filterSet);
    }
    
    void process(final Element e, final Set<String> filter) {
        this.process(e.getAttributes());
        if (this.markedAsLiteral(e)) {
            return;
        }
        for (final Content t : new ArrayList<Content>(e.getContent(ScriptHandler.textFilter))) {
            this.evaluate((Text)t);
        }
        for (final Element c : e.getChildren()) {
            final Namespace ns = c.getNamespace();
            if (ns.equals((Object)Parser.namespace) && filter.contains(c.getName())) {
                continue;
            }
            this.process(c, filter);
        }
    }
    
    void process(final List<Attribute> list) {
        for (final Attribute a : list) {
            this.evaluate(a);
        }
    }
    
    void process(final List<Attribute> list, final List<String> filter) {
        for (final Attribute a : list) {
            if (!filter.contains(a.getName())) {
                this.evaluate(a);
            }
        }
    }
    
    private void evaluate(final Attribute a) {
        this.currentScript = new Script(a);
        if (this.reserved(a) || this.currentScript.notValid()) {
            return;
        }
        final Element tag = a.getParent();
        final Namespace pns = tag.getNamespace();
        if (pns.equals((Object)Parser.namespace) && ScriptHandler.protectedTags.contains(tag.getName())) {
            a.setValue(this.runProtected());
        }
        else {
            a.setValue(this.runScript());
        }
    }
    
    private boolean reserved(final Attribute a) {
        final Namespace ns = a.getNamespace();
        return ns.equals((Object)SAXHandler.saxns) || ns.equals((Object)Parser.namespace);
    }
    
    private void evaluate(final Text text) {
        String result = null;
        this.currentScript = new Script(text);
        if (this.currentScript.valid()) {
            try {
                result = this.runScript();
                if (Tuple.isTuple(result)) {
                    final Element p = text.getParentElement();
                    String[] split;
                    for (int length = (split = Tuple.split(result)).length, i = 0; i < length; ++i) {
                        final String s = split[i];
                        final Element t = new Element("tuple", Parser.namespace);
                        t.setText(s);
                        p.addContent((Content)t);
                    }
                }
                else {
                    text.setText(result);
                }
            }
            catch (Exception e) {
                ScriptHandler.log.error("Text='" + text + "' Result='" + result + "'" + this.currentScript + this.interpreter, e);
            }
        }
    }
    
    private String runScript() {
        String result = this.currentScript.text;
        try {
            result = this.interpreter.execute(this.currentScript.text);
        }
        catch (Exception e) {
            ScriptHandler.log.error(this.currentScript, e);
            ScriptHandler.log.error(this.interpreter);
        }
        return result;
    }
    
    private String runProtected() {
        int pos = 0;
        final StringBuilder sb = new StringBuilder(this.currentScript.text);
        final Pattern p = Pattern.compile("\\{[^\\}]+\\}");
        while (true) {
            final Matcher m = p.matcher(sb.toString());
            if (!m.find(pos)) {
                break;
            }
            try {
                this.currentScript.text = m.group(0).trim();
                final String value = this.runScript();
                sb.replace(m.start(), m.end(), value);
            }
            catch (Exception e) {
                pos += m.end();
            }
        }
        return sb.toString();
    }
    
    private boolean markedAsLiteral(final Element e) {
        boolean result = false;
        if (e.getNamespace().equals((Object)Parser.namespace)) {
            final String literal = e.getAttributeValue("literal", "0");
            result = Boolean.valueOf(literal);
        }
        return result;
    }
    
    @Override
    public void onEvent(final Event event) {
        String msg = String.valueOf(this.currentScript.location()) + " " + event.message;
        switch (event.severity) {
            case info: {
                ScriptHandler.log.info(msg);
                break;
            }
            case warning: {
                ScriptHandler.log.warn(msg);
                break;
            }
            case error: {
                msg = String.valueOf(event.message) + this.currentScript + this.interpreter;
                if (event.exception == null) {
                    ScriptHandler.log.error(msg);
                    break;
                }
                ScriptHandler.log.error(msg, event.exception);
                break;
            }
        }
    }
}
