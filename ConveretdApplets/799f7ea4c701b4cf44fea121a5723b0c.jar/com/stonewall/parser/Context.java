// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser;

import java.util.ArrayList;
import com.stonewall.parser.interpreter.Dispatcher;
import org.jdom.Namespace;
import org.jdom.Document;
import java.util.Set;
import java.util.List;
import java.util.Iterator;
import org.jdom.xpath.XPath;
import org.jdom.output.XMLOutputter;
import org.jdom.output.Format;
import com.stonewall.parser.jdom.SAXHandler;
import org.xmodel.log.Log;
import com.stonewall.parser.interpreter.Interpreter;
import org.jdom.Element;

class Context
{
    private Parser parser;
    private Element root;
    private Interpreter interpreter;
    private TokenManager tokenManager;
    private ScriptHandler scriptHandler;
    private Dictionary<Dictionary<String>> dictionaries;
    private Dictionary<Element> procedures;
    static Log log;
    
    static {
        Context.log = Log.getLog(Context.class);
    }
    
    Context(final Parser parser, final Element root) throws Exception {
        this.dictionaries = new Dictionary<Dictionary<String>>();
        this.procedures = new Dictionary<Element>();
        this.parser = parser;
        this.root = root;
        this.tokenManager = new TokenManager(this);
        this.importDictionaries();
        this.importProcedures();
    }
    
    public String id() {
        return this.location();
    }
    
    String location() {
        return SAXHandler.location(this.root);
    }
    
    @Override
    public String toString() {
        final Format format = Format.getPrettyFormat();
        final XMLOutputter p = new XMLOutputter(format);
        return p.outputString(this.root);
    }
    
    void importDictionaries() throws Exception {
        final XPath xpath = this.pathStore().get(".//p:dictionary");
        for (final Element d : xpath.selectNodes((Object)this.root)) {
            String name = d.getAttributeValue("name");
            this.importDictionary(d, name, false);
            name = d.getAttributeValue("alt");
            if (name != null) {
                this.importDictionary(d, name, true);
            }
        }
    }
    
    void importProcedures() throws Exception {
        final XPath xpath = this.pathStore().get(".//p:procedure");
        for (final Element p : xpath.selectNodes((Object)this.root)) {
            final String name = p.getAttributeValue("name");
            if (name.indexOf(46) < 0) {
                Context.log.debug("local procedure \"" + name + "\" added");
                this.procedures.put(name, p);
            }
        }
    }
    
    void importDictionary(final Element d, final String name, final boolean reverse) throws Exception {
        Dictionary<String> dictionary = this.dictionaries.get(name);
        if (dictionary == null) {
            dictionary = new Dictionary<String>();
            this.dictionaries.put(name, dictionary);
        }
        for (final Element k : d.getChildren("key", this.namespace())) {
            final String key = k.getAttributeValue("name");
            final String value = k.getTextTrim();
            if (reverse) {
                dictionary.put(value, key);
            }
            else {
                dictionary.put(key, value);
            }
        }
    }
    
    String text() {
        return this.parser.text();
    }
    
    List<String> tokens() {
        return this.tokenManager.tokens();
    }
    
    Set<String> keywords() {
        return this.tokenManager.keywords().keySet();
    }
    
    void add(final Keyword k) {
        this.tokenManager.add(k);
    }
    
    Dictionary<String> staticReferences() {
        return this.tokenManager.references();
    }
    
    Dictionary<String> dynamicReferences() {
        return (this.interpreter != null) ? this.interpreter.references() : this.staticReferences();
    }
    
    Dictionary<Element> nodeReferences() {
        return this.interpreter.nodeReferences();
    }
    
    Interpreter interpreter() {
        return this.interpreter;
    }
    
    TokenManager tokenManager() {
        return this.tokenManager;
    }
    
    Document model() {
        return this.parser.model();
    }
    
    ParserState parserState() {
        return this.parser.state();
    }
    
    Namespace namespace() {
        return this.parser.namespace();
    }
    
    Dictionary<Dictionary<String>> dictionaries() {
        return this.dictionaries;
    }
    
    Dictionary<Element> procedures() {
        return this.procedures;
    }
    
    Dictionary<Element> globalProcedures() {
        return this.parser.procedures();
    }
    
    Dispatcher dispatcher() {
        return this.parser.dispatcher();
    }
    
    PathStore pathStore() {
        return this.parser.pathStore();
    }
    
    PatternStore patternStore() {
        return this.parser.patternStore();
    }
    
    ScriptHandler scriptHandler() {
        return this.scriptHandler;
    }
    
    List<Action> actions(final Element e, final Action parent) {
        final List<Action> actions = new ArrayList<Action>();
        for (final Element tag : e.getChildren()) {
            if (tag.getNamespace().equals((Object)this.namespace())) {
                this.addAction(tag, parent, actions);
            }
        }
        return actions;
    }
    
    void addAction(final Element tag, final Action parent, final List<Action> list) {
        try {
            switch (Action.actions.valueOf(tag.getName())) {
                case action: {
                    list.add(new Action(tag, this));
                    break;
                }
                case foreach: {
                    list.add(new Foreach(tag, this));
                    break;
                }
                case abort:
                case end: {
                    list.add(new Abort(tag, this));
                    break;
                }
                case push: {
                    list.add(new Push(tag, this));
                    break;
                }
                case pop: {
                    list.add(new Pop(tag, this));
                    break;
                }
                case tokens: {
                    list.add(new Tokens(tag, this));
                    break;
                }
                case pswitch: {
                    list.add(new Switch(tag, this));
                    break;
                }
                case pcase: {
                    list.add(new Case(tag, this, parent));
                    break;
                }
                case pwhile: {
                    list.add(new While(tag, this));
                    break;
                }
                case call: {
                    list.add(new Call(tag, this));
                    break;
                }
                case append: {
                    list.add(new Append(tag, this));
                    break;
                }
                case insert: {
                    list.add(new Insert(tag, this));
                    break;
                }
                case insertAfter: {
                    list.add(new InsertAfter(tag, this));
                    break;
                }
                case set: {
                    list.add(new SetValue(tag, this));
                    break;
                }
                case delete: {
                    list.add(new Delete(tag, this));
                    break;
                }
                case move: {
                    list.add(new Move(tag, this));
                    break;
                }
                case assign: {
                    list.add(new Assign(tag, this));
                    break;
                }
                case log: {
                    list.add(new com.stonewall.parser.Log(tag, this));
                    break;
                }
            }
        }
        catch (Exception ex) {}
    }
    
    void reset() {
        this.tokenManager.reset();
        (this.interpreter = new Interpreter()).setDictionaries(this.dictionaries());
        this.interpreter.set(this.model());
        this.interpreter.set(this.pathStore());
        this.interpreter.dispatcher().attach(this.dispatcher());
        this.scriptHandler = new ScriptHandler(this.interpreter);
    }
    
    void parseTokens() {
        this.tokenManager.parseTokens(this.text());
        this.tokenManagerChanged();
    }
    
    void tokenManagerChanged() {
        this.interpreter.clearTokensAndKeywords();
        this.interpreter.setTokens(this.tokens());
        this.interpreter.setKeywords(this.tokenManager.references());
    }
    
    void applyActions() {
        final Element clone = (Element)this.root.clone();
        for (Action a : this.actions(clone, null)) {
            final Action child = a;
            for (int i = -1; i < a.repeat(); ++i) {
                if (a.repeat() > 0) {
                    a = child.clone();
                }
                Context.log.debug("apply:\n" + a.id());
                final boolean abort = !a.apply();
                if (abort) {
                    return;
                }
            }
        }
    }
}
