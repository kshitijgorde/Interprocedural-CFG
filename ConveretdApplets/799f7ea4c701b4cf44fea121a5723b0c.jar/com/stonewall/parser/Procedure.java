// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser;

import com.stonewall.parser.interpreter.Interpreter;
import java.util.Iterator;
import org.jdom.Element;

public class Procedure extends Action
{
    final String name;
    final Element body;
    final Dictionary<Arg> args;
    
    Procedure(final Element root, final Context context) {
        super(root, context);
        this.args = new Dictionary<Arg>();
        this.name = root.getAttributeValue("name");
        this.body = root.getChild("body", Parser.namespace);
        this.getArgs(root);
    }
    
    void getArgs(final Element root) {
        for (final Element c : root.getChildren("arg", Parser.namespace)) {
            final Arg arg = new Arg(c);
            this.args.put(arg.name, arg);
        }
    }
    
    @Override
    boolean condition() {
        return true;
    }
    
    boolean call(final Dictionary<String> args) throws Exception {
        final Interpreter interpreter = this.context().interpreter();
        final Dictionary<String> references = interpreter.references();
        final Dictionary<Element> nodeReferences = interpreter.nodeReferences();
        final Dictionary<String> argv = this.argv(args);
        interpreter.references(argv);
        interpreter.setTokens(this.context().tokens(), false);
        try {
            Procedure.log.debug("calling: " + this.name + argv);
            return this.body().apply();
        }
        finally {
            interpreter.references(references);
            interpreter.nodeReferences(nodeReferences);
        }
    }
    
    private Action body() throws Exception {
        if (this.body == null) {
            final String m = "procedure '" + this.name + "' has no body";
            throw new Exception(m);
        }
        return new Body(this.body, this.context());
    }
    
    private Dictionary<String> argv(final Dictionary<String> passed) throws Exception {
        final Dictionary<String> result = new Dictionary<String>();
        for (Arg arg : this.args.values()) {
            arg = arg.updated(this.context());
            final String value = passed.get(arg.name, arg.defaultValue);
            if (value != null) {
                result.put(arg.name, value);
            }
            else {
                if (arg.required) {
                    final String m = String.valueOf(this.name) + "() called with required argument '" + arg.name + "' not-passed";
                    throw new Exception(m);
                }
                continue;
            }
        }
        this.findExtra(passed);
        return result;
    }
    
    private void findExtra(final Dictionary<String> passed) throws Exception {
        for (final String key : passed.keySet()) {
            if (this.args.containsKey(key)) {
                continue;
            }
            final String m = String.valueOf(this.name) + "() called with extra argument '" + key + "'";
            throw new Exception(m);
        }
    }
}
