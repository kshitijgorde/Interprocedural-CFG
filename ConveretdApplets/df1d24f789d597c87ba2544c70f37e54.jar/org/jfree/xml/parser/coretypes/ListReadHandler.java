// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.parser.coretypes;

import java.util.Vector;
import java.util.LinkedList;
import java.util.Stack;
import org.jfree.xml.parser.XmlReaderException;
import org.jfree.xml.parser.XmlReadHandler;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import java.util.ArrayList;
import java.util.List;
import org.jfree.xml.parser.AbstractXmlReadHandler;

public class ListReadHandler extends AbstractXmlReadHandler
{
    private List retval;
    private ArrayList handlers;
    private String listType;
    static /* synthetic */ Class class$java$lang$Object;
    
    protected void startParsing(final Attributes attributes) throws SAXException {
        this.listType = attributes.getValue("type");
        if (this.listType == null) {
            this.listType = "array-list";
        }
        this.handlers = new ArrayList();
    }
    
    protected XmlReadHandler getHandlerForChild(final String s, final Attributes attributes) throws XmlReaderException, SAXException {
        final XmlReadHandler handler = this.getRootHandler().createHandler((ListReadHandler.class$java$lang$Object == null) ? (ListReadHandler.class$java$lang$Object = class$("java.lang.Object")) : ListReadHandler.class$java$lang$Object, s, attributes);
        this.handlers.add(handler);
        return handler;
    }
    
    protected void doneParsing() throws SAXException, XmlReaderException {
        final XmlReadHandler[] array = this.handlers.toArray(new XmlReadHandler[this.handlers.size()]);
        this.retval = this.createList(array.length);
        for (int i = 0; i < array.length; ++i) {
            this.retval.add(array[i].getObject());
        }
        this.handlers.clear();
    }
    
    private List createList(final int n) {
        if (this.listType.equals("stack")) {
            return new Stack();
        }
        if (this.listType.equals("linked-list")) {
            return new LinkedList();
        }
        if (this.listType.equals("vector")) {
            return new Vector(n);
        }
        return new ArrayList(n);
    }
    
    public Object getObject() {
        return this.retval;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
