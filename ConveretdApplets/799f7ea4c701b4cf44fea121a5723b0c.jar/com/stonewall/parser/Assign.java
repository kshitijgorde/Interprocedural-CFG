// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser;

import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import org.jdom.Element;

class Assign extends SimpleAction
{
    Assign(final Element root, final Context context) {
        super(root, context);
    }
    
    @Override
    void process() throws Exception {
        final String var = this.root().getAttributeValue("var");
        if (var != null) {
            this.assignVariable(var);
            return;
        }
        final String node = this.root().getAttributeValue("node");
        if (node != null) {
            this.assignNode(node);
        }
    }
    
    private void assignVariable(final String var) throws Exception {
        String value = this.root().getText();
        final List<Element> tuples = (List<Element>)this.root().getChildren("tuple", Parser.namespace);
        if (tuples.size() > 0) {
            value = this.joinTuple(tuples);
        }
        this.context().dynamicReferences().put(var, value);
    }
    
    private void assignNode(final String node) throws Exception {
        final String path = this.root().getText();
        final Element n = this.nodeAtPath(path);
        if (n != null) {
            this.context().nodeReferences().put(node, n);
        }
    }
    
    private String joinTuple(final List<Element> parts) {
        final List<String> strings = new ArrayList<String>(parts.size());
        for (final Element e : parts) {
            strings.add(e.getText());
        }
        return Tuple.join(strings);
    }
}
