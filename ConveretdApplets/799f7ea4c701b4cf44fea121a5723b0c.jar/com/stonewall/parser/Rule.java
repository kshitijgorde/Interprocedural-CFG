// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser;

import java.util.regex.Matcher;
import org.jdom.Element;
import org.xmodel.log.Log;
import java.util.regex.Pattern;

class Rule extends Context
{
    private final Type type;
    private final String state;
    private final Pattern pattern;
    static Log log;
    
    static {
        Rule.log = Log.getLog(Rule.class);
    }
    
    Rule(final Parser parser, final Element root) throws Exception {
        super(parser, root);
        final String ptn = root.getAttributeValue("pattern", ".+");
        this.type = Type.valueOf(root.getAttributeValue("type", Type.basic.name()));
        this.state = root.getAttributeValue("state", "");
        this.pattern = this.patternStore().get(ptn);
    }
    
    public boolean basic() {
        return this.type.equals(Type.basic);
    }
    
    public boolean pre() {
        return this.type.equals(Type.pre);
    }
    
    public boolean post() {
        return this.type.equals(Type.post);
    }
    
    @Override
    public String id() {
        return String.valueOf(this.location()) + ", pattern=\"" + this.pattern + "\"";
    }
    
    boolean match(final String text) {
        final Matcher m = this.pattern.matcher(text);
        final boolean result = this.matchState() && m.find();
        if (result) {
            Rule.log.debug(String.valueOf(this.id()) + " matched: " + m.group());
        }
        return result;
    }
    
    void apply() {
        this.reset();
        switch (this.type) {
            case basic: {
                this.parseTokens();
                this.applyActions();
                break;
            }
            case pre:
            case post: {
                this.applyActions();
                break;
            }
        }
    }
    
    String state() {
        return this.state;
    }
    
    boolean matchState() {
        return this.parserState().match(this.state);
    }
    
    enum Type
    {
        basic("basic", 0), 
        pre("pre", 1), 
        post("post", 2);
        
        private Type(final String s, final int n) {
        }
    }
}
