// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser;

import java.util.List;
import java.util.Iterator;
import org.jdom.xpath.XPath;
import java.util.Collection;
import java.util.Map;
import org.jdom.Element;

public class Foreach extends Action
{
    static final String NodeKey = "foreach.node";
    static final String ItemKey = "foreach.item";
    
    Foreach(final Element root, final Context context) {
        super(root, context);
    }
    
    @Override
    boolean condition() {
        return true;
    }
    
    @Override
    boolean apply() {
        boolean result = true;
        try {
            this.processAttributes();
            final String path = this.root().getAttributeValue("path");
            final String list = this.root().getAttributeValue("tuple");
            if (path != null) {
                result = this.applyPath(path);
            }
            else if (list != null) {
                result = this.applyTuple(list);
            }
            else {
                Foreach.log.error("attribute (path|tuple) expected and not-found at: " + this.id());
            }
        }
        catch (Exception e) {
            Foreach.log.error(e);
        }
        Foreach.log.debug(String.valueOf(this.id()) + ", result: " + result);
        return result;
    }
    
    boolean applyPath(final String path) throws Exception {
        boolean result = true;
        Element root = this.context().model().getRootElement();
        final String key = this.root().getAttributeValue("var", "foreach.node");
        final String nodeRef = this.root().getAttributeValue("node");
        final Dictionary<String> references = this.context().interpreter().references();
        final Dictionary<Element> nodeReferences = this.context().interpreter().nodeReferences();
        final Element prev = nodeReferences.get("foreach.node");
        final XPath xpath = this.context().pathStore().get(path, (Map<String, String>)references);
        if (nodeRef != null) {
            root = nodeReferences.get(nodeRef);
            if (root == null) {
                Foreach.log.error("node reference not-resolved:\nReferences:\n\t" + nodeRef + "\nLocation:\n\t" + this.location() + "\nReferences:\n\t" + nodeReferences.keySet());
                return result;
            }
        }
        for (final Element node : xpath.selectNodes((Object)root)) {
            final List content = this.root().cloneContent();
            nodeReferences.put(key, node);
            this.processContent();
            result = this.applyChildren();
            if (!result) {
                break;
            }
            this.root().removeContent();
            this.root().addContent((Collection)content);
        }
        if (prev != null) {
            nodeReferences.put(key, prev);
        }
        else {
            nodeReferences.remove(key);
        }
        return result;
    }
    
    boolean applyTuple(String list) throws Exception {
        boolean result = true;
        final String key = this.root().getAttributeValue("var", "foreach.item");
        final Dictionary<String> references = this.context().interpreter().references();
        final String prev = references.get(key);
        list = references.get(list);
        if (list == null) {
            Foreach.log.error("foreach:list argument expected as tuple reference " + this.location());
            return false;
        }
        String[] split;
        for (int length = (split = Tuple.split(list)).length, i = 0; i < length; ++i) {
            final String s = split[i];
            final List content = this.root().cloneContent();
            references.put(key, s);
            this.processContent();
            result = this.applyChildren();
            if (!result) {
                break;
            }
            this.root().removeContent();
            this.root().addContent((Collection)content);
        }
        if (prev != null) {
            references.put(key, prev);
        }
        else {
            references.remove(key);
        }
        return result;
    }
}
