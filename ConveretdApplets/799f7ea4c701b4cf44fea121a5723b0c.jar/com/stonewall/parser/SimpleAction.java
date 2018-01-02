// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser;

import java.util.Iterator;
import org.jdom.Attribute;
import org.jdom.output.XMLOutputter;
import org.jdom.output.Format;
import java.util.List;
import org.jdom.xpath.XPath;
import org.jdom.Document;
import java.util.Map;
import org.xmodel.log.Log;
import org.jdom.Element;

public abstract class SimpleAction extends Action
{
    private final Element trash;
    static Log log;
    
    static {
        SimpleAction.log = Log.getLog(SimpleAction.class);
    }
    
    SimpleAction(final Element root, final Context context) {
        super(root, context);
        this.trash = new Element("trash");
    }
    
    @Override
    final boolean condition() {
        return true;
    }
    
    abstract void process() throws Exception;
    
    @Override
    final boolean applyChildren() {
        try {
            this.process();
        }
        catch (Exception e) {
            final String msg = "simple-action: " + this.id() + "\nAT: " + this.location() + " failed:\n" + e;
            SimpleAction.log.error(msg);
        }
        return true;
    }
    
    Element nodeAtPath() throws Exception {
        return (Element)this.objAtPath();
    }
    
    Element nodeAtPath(final String path) throws Exception {
        return this.nodeAtPath(this.model(), path);
    }
    
    Element nodeAtPath(final Object model, final String path) throws Exception {
        return (Element)this.objAtPath(model, path);
    }
    
    Object objAtPath() throws Exception {
        return this.objAtPath(this.model(), this.pathArg());
    }
    
    Object objAtPath(final Object model, final String path) throws Exception {
        final XPath xpath = this.context().pathStore().get(path, (Map<String, String>)this.context().dynamicReferences());
        Object result = xpath.selectSingleNode(model);
        if (result == null) {
            result = this.trash;
            SimpleAction.log.error("xpath (nothing matched)\nPath\n\t" + path + "\nLocation:\n\t" + this.location() + "\nReferences:\n" + this.context().dynamicReferences() + "\nModel:\n" + this.toString((Document)model));
        }
        return result;
    }
    
    List objsAtPath(final Object model, final int min) throws Exception {
        return this.objsAtPath(model, this.pathArg(), min);
    }
    
    List objsAtPath(final Object model, final String path, final int min) throws Exception {
        final XPath xpath = this.context().pathStore().get(path, (Map<String, String>)this.context().dynamicReferences());
        final List result = xpath.selectNodes(model);
        if (result.size() < min) {
            SimpleAction.log.error("path:\n" + path + " not-found,\n" + this.context().dynamicReferences());
        }
        return result;
    }
    
    String toString(final Element e) {
        final Format f = Format.getPrettyFormat();
        final XMLOutputter p = new XMLOutputter(f);
        return p.outputString(e);
    }
    
    String toString(final Document d) {
        final Format f = Format.getPrettyFormat();
        final XMLOutputter p = new XMLOutputter(f);
        return p.outputString(d.getRootElement());
    }
    
    String shortString(final Element e) {
        final StringBuilder sb = new StringBuilder();
        sb.append('<');
        sb.append(e.getName());
        for (final Attribute a : e.getAttributes()) {
            sb.append(' ');
            sb.append(a.getName());
            sb.append("=\"");
            sb.append(a.getValue());
            sb.append('\"');
        }
        sb.append("/>");
        return sb.toString();
    }
    
    String pathArg() {
        return this.root().getAttributeValue("path");
    }
    
    String rootArg() {
        return this.root().getAttributeValue("root");
    }
    
    String contentArg() {
        return this.root().getAttributeValue("content");
    }
    
    Object model() {
        Object model = this.context().model();
        final String nodeRef = this.rootArg();
        if (nodeRef != null) {
            final Dictionary<Element> nodeReferences = this.context().interpreter().nodeReferences();
            final Element root = nodeReferences.get(nodeRef);
            if (root == null) {
                SimpleAction.log.error("node reference not-resolved\nReference:\n\t" + nodeRef + "\nLocation:\n\t" + this.location() + "\nReferences:\n\t" + nodeReferences.keySet());
            }
            else {
                model = root;
            }
        }
        return model;
    }
}
