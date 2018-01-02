// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

import org.jdom.filter.Filter;
import org.jdom.filter.ElementFilter;
import java.util.List;
import java.util.StringTokenizer;
import org.jdom.output.XMLOutputter;
import org.jdom.Text;
import org.jdom.Content;
import org.jdom.Namespace;
import org.jdom.output.Format;
import org.jdom.Element;

public class SmartElement extends Element
{
    public static final Format defaultFormat;
    static final long serialVersionUID = 0L;
    
    static {
        defaultFormat = Format.getPrettyFormat();
    }
    
    public SmartElement(final String name) {
        super(name);
    }
    
    public SmartElement(final String name, final Namespace ns) {
        super(name, ns);
    }
    
    public Element addContent(final Content child) {
        if (child instanceof Text) {
            final Text t = (Text)child;
            if (t.getTextTrim().length() == 0) {
                return this;
            }
        }
        return super.addContent(child);
    }
    
    public Element addContent(final String child, final Content c) {
        return addContent(this, child, c);
    }
    
    public Element addChild(final String name) {
        return addChild(this, name);
    }
    
    public SmartElement setText(final String s) {
        super.setText(s);
        return this;
    }
    
    public SmartElement setText(final String child, final String value) {
        this.addChild(child).setText(value);
        return this;
    }
    
    public SmartElement setAttribute(final String n, final String v) {
        super.setAttribute(n, v);
        return this;
    }
    
    public Element getChild(final String name, final boolean autoCreate) {
        return getChild(this, name, autoCreate);
    }
    
    public Element getChild(final int index) {
        return getChild(this, index);
    }
    
    public String toString() {
        return this.toString(SmartElement.defaultFormat);
    }
    
    public String toString(final Format format) {
        return toString(this, format);
    }
    
    public static String toString(final Element e) {
        return toString(e, SmartElement.defaultFormat);
    }
    
    public static String toString(final Element e, final Format format) {
        final XMLOutputter printer = new XMLOutputter(format);
        return printer.outputString(e);
    }
    
    public static Element addChild(final Element p, final String name) {
        Element node = p;
        final StringTokenizer tkn = new StringTokenizer(name, "/");
        while (tkn.hasMoreTokens()) {
            final String tag = tkn.nextToken();
            Element child = node.getChild(tag, node.getNamespace());
            if (child == null) {
                child = new Element(tag, node.getNamespace());
                node.addContent((Content)child);
            }
            node = child;
        }
        return node;
    }
    
    public static Element addContent(final Element p, final String name, final Content c) {
        Element node = p;
        final StringTokenizer tkn = new StringTokenizer(name, "/");
        while (tkn.hasMoreTokens()) {
            final String tag = tkn.nextToken();
            Element child = node.getChild(tag, node.getNamespace());
            if (child == null) {
                child = new Element(tag, node.getNamespace());
                node.addContent((Content)child);
            }
            node = child;
        }
        node.addContent(c);
        return node;
    }
    
    public static Element getChild(final Element p, final String name, final boolean autoCreate) {
        return autoCreate ? addChild(p, name) : p.getChild(name, p.getNamespace());
    }
    
    public static List<Element> getChildren(final Element p, final String name) {
        return (List<Element>)p.getChildren(name);
    }
    
    public static List<Element> getChildren(final Element p, final String name, final Namespace ns) {
        return (List<Element>)p.getChildren(name, ns);
    }
    
    public static Element getChild(final Element e, final int index) {
        return e.getContent((Filter)new ElementFilter()).get(index);
    }
    
    public static Element getFirstChild(final Element e) {
        return getChild(e, 0);
    }
}
