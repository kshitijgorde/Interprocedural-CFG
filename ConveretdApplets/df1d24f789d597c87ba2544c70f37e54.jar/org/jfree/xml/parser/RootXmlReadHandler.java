// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.parser;

import org.jfree.xml.ParseException;
import org.xml.sax.SAXException;
import org.jfree.xml.parser.coretypes.GenericReadHandler;
import org.jfree.util.Log;
import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.jfree.xml.util.MultiplexMappingDefinition;
import org.jfree.xml.util.ManualMappingDefinition;
import org.jfree.xml.util.ObjectFactory;
import org.jfree.xml.util.MultiplexMappingEntry;
import org.xml.sax.Locator;
import org.jfree.xml.util.SimpleObjectFactory;
import java.util.HashMap;
import java.util.Stack;
import org.xml.sax.helpers.DefaultHandler;

public abstract class RootXmlReadHandler extends DefaultHandler
{
    private Stack currentHandlers;
    private Stack outerScopes;
    private XmlReadHandler rootHandler;
    private HashMap objectRegistry;
    private SimpleObjectFactory classToHandlerMapping;
    private Locator locator;
    static /* synthetic */ Class class$java$awt$Color;
    static /* synthetic */ Class class$java$awt$GradientPaint;
    static /* synthetic */ Class class$java$awt$Paint;
    static /* synthetic */ Class class$org$jfree$xml$parser$coretypes$ColorReadHandler;
    static /* synthetic */ Class class$org$jfree$xml$parser$coretypes$GradientPaintReadHandler;
    static /* synthetic */ Class class$java$awt$geom$Point2D$Float;
    static /* synthetic */ Class class$java$awt$geom$Point2D$Double;
    static /* synthetic */ Class class$java$awt$geom$Point2D;
    static /* synthetic */ Class class$org$jfree$xml$parser$coretypes$Point2DReadHandler;
    static /* synthetic */ Class class$java$awt$geom$Rectangle2D$Float;
    static /* synthetic */ Class class$java$awt$geom$Rectangle2D$Double;
    static /* synthetic */ Class class$java$awt$geom$Rectangle2D;
    static /* synthetic */ Class class$org$jfree$xml$parser$coretypes$Rectangle2DReadHandler;
    static /* synthetic */ Class class$java$util$ArrayList;
    static /* synthetic */ Class class$java$util$LinkedList;
    static /* synthetic */ Class class$java$util$Vector;
    static /* synthetic */ Class class$java$util$Stack;
    static /* synthetic */ Class class$java$util$List;
    static /* synthetic */ Class class$org$jfree$xml$parser$coretypes$ListReadHandler;
    static /* synthetic */ Class class$java$awt$BasicStroke;
    static /* synthetic */ Class class$java$awt$Stroke;
    static /* synthetic */ Class class$org$jfree$xml$parser$coretypes$BasicStrokeReadHandler;
    static /* synthetic */ Class class$java$awt$Font;
    static /* synthetic */ Class class$org$jfree$xml$parser$coretypes$FontReadHandler;
    static /* synthetic */ Class class$java$awt$Insets;
    static /* synthetic */ Class class$org$jfree$xml$parser$coretypes$InsetsReadHandler;
    static /* synthetic */ Class class$java$awt$RenderingHints;
    static /* synthetic */ Class class$org$jfree$xml$parser$coretypes$RenderingHintsReadHandler;
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$org$jfree$xml$parser$coretypes$StringReadHandler;
    static /* synthetic */ Class class$org$jfree$xml$parser$XmlReadHandler;
    
    public RootXmlReadHandler() {
        this.objectRegistry = new HashMap();
        this.classToHandlerMapping = new SimpleObjectFactory();
        this.addMultiplexMapping((RootXmlReadHandler.class$java$awt$Paint == null) ? (RootXmlReadHandler.class$java$awt$Paint = class$("java.awt.Paint")) : RootXmlReadHandler.class$java$awt$Paint, "type", new MultiplexMappingEntry[] { new MultiplexMappingEntry("color", ((RootXmlReadHandler.class$java$awt$Color == null) ? (RootXmlReadHandler.class$java$awt$Color = class$("java.awt.Color")) : RootXmlReadHandler.class$java$awt$Color).getName()), new MultiplexMappingEntry("gradientPaint", ((RootXmlReadHandler.class$java$awt$GradientPaint == null) ? (RootXmlReadHandler.class$java$awt$GradientPaint = class$("java.awt.GradientPaint")) : RootXmlReadHandler.class$java$awt$GradientPaint).getName()) });
        this.addManualMapping((RootXmlReadHandler.class$java$awt$Color == null) ? (RootXmlReadHandler.class$java$awt$Color = class$("java.awt.Color")) : RootXmlReadHandler.class$java$awt$Color, (RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$ColorReadHandler == null) ? (RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$ColorReadHandler = class$("org.jfree.xml.parser.coretypes.ColorReadHandler")) : RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$ColorReadHandler);
        this.addManualMapping((RootXmlReadHandler.class$java$awt$GradientPaint == null) ? (RootXmlReadHandler.class$java$awt$GradientPaint = class$("java.awt.GradientPaint")) : RootXmlReadHandler.class$java$awt$GradientPaint, (RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$GradientPaintReadHandler == null) ? (RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$GradientPaintReadHandler = class$("org.jfree.xml.parser.coretypes.GradientPaintReadHandler")) : RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$GradientPaintReadHandler);
        this.addMultiplexMapping((RootXmlReadHandler.class$java$awt$geom$Point2D == null) ? (RootXmlReadHandler.class$java$awt$geom$Point2D = class$("java.awt.geom.Point2D")) : RootXmlReadHandler.class$java$awt$geom$Point2D, "type", new MultiplexMappingEntry[] { new MultiplexMappingEntry("float", ((RootXmlReadHandler.class$java$awt$geom$Point2D$Float == null) ? (RootXmlReadHandler.class$java$awt$geom$Point2D$Float = class$("java.awt.geom.Point2D$Float")) : RootXmlReadHandler.class$java$awt$geom$Point2D$Float).getName()), new MultiplexMappingEntry("double", ((RootXmlReadHandler.class$java$awt$geom$Point2D$Double == null) ? (RootXmlReadHandler.class$java$awt$geom$Point2D$Double = class$("java.awt.geom.Point2D$Double")) : RootXmlReadHandler.class$java$awt$geom$Point2D$Double).getName()) });
        this.addManualMapping((RootXmlReadHandler.class$java$awt$geom$Point2D$Float == null) ? (RootXmlReadHandler.class$java$awt$geom$Point2D$Float = class$("java.awt.geom.Point2D$Float")) : RootXmlReadHandler.class$java$awt$geom$Point2D$Float, (RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$Point2DReadHandler == null) ? (RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$Point2DReadHandler = class$("org.jfree.xml.parser.coretypes.Point2DReadHandler")) : RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$Point2DReadHandler);
        this.addManualMapping((RootXmlReadHandler.class$java$awt$geom$Point2D$Double == null) ? (RootXmlReadHandler.class$java$awt$geom$Point2D$Double = class$("java.awt.geom.Point2D$Double")) : RootXmlReadHandler.class$java$awt$geom$Point2D$Double, (RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$Point2DReadHandler == null) ? (RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$Point2DReadHandler = class$("org.jfree.xml.parser.coretypes.Point2DReadHandler")) : RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$Point2DReadHandler);
        this.addMultiplexMapping((RootXmlReadHandler.class$java$awt$geom$Rectangle2D == null) ? (RootXmlReadHandler.class$java$awt$geom$Rectangle2D = class$("java.awt.geom.Rectangle2D")) : RootXmlReadHandler.class$java$awt$geom$Rectangle2D, "type", new MultiplexMappingEntry[] { new MultiplexMappingEntry("float", ((RootXmlReadHandler.class$java$awt$geom$Rectangle2D$Float == null) ? (RootXmlReadHandler.class$java$awt$geom$Rectangle2D$Float = class$("java.awt.geom.Rectangle2D$Float")) : RootXmlReadHandler.class$java$awt$geom$Rectangle2D$Float).getName()), new MultiplexMappingEntry("double", ((RootXmlReadHandler.class$java$awt$geom$Rectangle2D$Double == null) ? (RootXmlReadHandler.class$java$awt$geom$Rectangle2D$Double = class$("java.awt.geom.Rectangle2D$Double")) : RootXmlReadHandler.class$java$awt$geom$Rectangle2D$Double).getName()) });
        this.addManualMapping((RootXmlReadHandler.class$java$awt$geom$Rectangle2D$Float == null) ? (RootXmlReadHandler.class$java$awt$geom$Rectangle2D$Float = class$("java.awt.geom.Rectangle2D$Float")) : RootXmlReadHandler.class$java$awt$geom$Rectangle2D$Float, (RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$Rectangle2DReadHandler == null) ? (RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$Rectangle2DReadHandler = class$("org.jfree.xml.parser.coretypes.Rectangle2DReadHandler")) : RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$Rectangle2DReadHandler);
        this.addManualMapping((RootXmlReadHandler.class$java$awt$geom$Rectangle2D$Double == null) ? (RootXmlReadHandler.class$java$awt$geom$Rectangle2D$Double = class$("java.awt.geom.Rectangle2D$Double")) : RootXmlReadHandler.class$java$awt$geom$Rectangle2D$Double, (RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$Rectangle2DReadHandler == null) ? (RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$Rectangle2DReadHandler = class$("org.jfree.xml.parser.coretypes.Rectangle2DReadHandler")) : RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$Rectangle2DReadHandler);
        this.addMultiplexMapping((RootXmlReadHandler.class$java$util$List == null) ? (RootXmlReadHandler.class$java$util$List = class$("java.util.List")) : RootXmlReadHandler.class$java$util$List, "type", new MultiplexMappingEntry[] { new MultiplexMappingEntry("array-list", ((RootXmlReadHandler.class$java$util$ArrayList == null) ? (RootXmlReadHandler.class$java$util$ArrayList = class$("java.util.ArrayList")) : RootXmlReadHandler.class$java$util$ArrayList).getName()), new MultiplexMappingEntry("linked-list", ((RootXmlReadHandler.class$java$util$LinkedList == null) ? (RootXmlReadHandler.class$java$util$LinkedList = class$("java.util.LinkedList")) : RootXmlReadHandler.class$java$util$LinkedList).getName()), new MultiplexMappingEntry("vector", ((RootXmlReadHandler.class$java$util$Vector == null) ? (RootXmlReadHandler.class$java$util$Vector = class$("java.util.Vector")) : RootXmlReadHandler.class$java$util$Vector).getName()), new MultiplexMappingEntry("stack", ((RootXmlReadHandler.class$java$util$Stack == null) ? (RootXmlReadHandler.class$java$util$Stack = class$("java.util.Stack")) : RootXmlReadHandler.class$java$util$Stack).getName()) });
        this.addManualMapping((RootXmlReadHandler.class$java$util$LinkedList == null) ? (RootXmlReadHandler.class$java$util$LinkedList = class$("java.util.LinkedList")) : RootXmlReadHandler.class$java$util$LinkedList, (RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$ListReadHandler == null) ? (RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$ListReadHandler = class$("org.jfree.xml.parser.coretypes.ListReadHandler")) : RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$ListReadHandler);
        this.addManualMapping((RootXmlReadHandler.class$java$util$Vector == null) ? (RootXmlReadHandler.class$java$util$Vector = class$("java.util.Vector")) : RootXmlReadHandler.class$java$util$Vector, (RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$ListReadHandler == null) ? (RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$ListReadHandler = class$("org.jfree.xml.parser.coretypes.ListReadHandler")) : RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$ListReadHandler);
        this.addManualMapping((RootXmlReadHandler.class$java$util$ArrayList == null) ? (RootXmlReadHandler.class$java$util$ArrayList = class$("java.util.ArrayList")) : RootXmlReadHandler.class$java$util$ArrayList, (RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$ListReadHandler == null) ? (RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$ListReadHandler = class$("org.jfree.xml.parser.coretypes.ListReadHandler")) : RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$ListReadHandler);
        this.addManualMapping((RootXmlReadHandler.class$java$util$Stack == null) ? (RootXmlReadHandler.class$java$util$Stack = class$("java.util.Stack")) : RootXmlReadHandler.class$java$util$Stack, (RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$ListReadHandler == null) ? (RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$ListReadHandler = class$("org.jfree.xml.parser.coretypes.ListReadHandler")) : RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$ListReadHandler);
        this.addMultiplexMapping((RootXmlReadHandler.class$java$awt$Stroke == null) ? (RootXmlReadHandler.class$java$awt$Stroke = class$("java.awt.Stroke")) : RootXmlReadHandler.class$java$awt$Stroke, "type", new MultiplexMappingEntry[] { new MultiplexMappingEntry("basic", ((RootXmlReadHandler.class$java$awt$BasicStroke == null) ? (RootXmlReadHandler.class$java$awt$BasicStroke = class$("java.awt.BasicStroke")) : RootXmlReadHandler.class$java$awt$BasicStroke).getName()) });
        this.addManualMapping((RootXmlReadHandler.class$java$awt$BasicStroke == null) ? (RootXmlReadHandler.class$java$awt$BasicStroke = class$("java.awt.BasicStroke")) : RootXmlReadHandler.class$java$awt$BasicStroke, (RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$BasicStrokeReadHandler == null) ? (RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$BasicStrokeReadHandler = class$("org.jfree.xml.parser.coretypes.BasicStrokeReadHandler")) : RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$BasicStrokeReadHandler);
        this.addManualMapping((RootXmlReadHandler.class$java$awt$Font == null) ? (RootXmlReadHandler.class$java$awt$Font = class$("java.awt.Font")) : RootXmlReadHandler.class$java$awt$Font, (RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$FontReadHandler == null) ? (RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$FontReadHandler = class$("org.jfree.xml.parser.coretypes.FontReadHandler")) : RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$FontReadHandler);
        this.addManualMapping((RootXmlReadHandler.class$java$awt$Insets == null) ? (RootXmlReadHandler.class$java$awt$Insets = class$("java.awt.Insets")) : RootXmlReadHandler.class$java$awt$Insets, (RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$InsetsReadHandler == null) ? (RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$InsetsReadHandler = class$("org.jfree.xml.parser.coretypes.InsetsReadHandler")) : RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$InsetsReadHandler);
        this.addManualMapping((RootXmlReadHandler.class$java$awt$RenderingHints == null) ? (RootXmlReadHandler.class$java$awt$RenderingHints = class$("java.awt.RenderingHints")) : RootXmlReadHandler.class$java$awt$RenderingHints, (RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$RenderingHintsReadHandler == null) ? (RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$RenderingHintsReadHandler = class$("org.jfree.xml.parser.coretypes.RenderingHintsReadHandler")) : RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$RenderingHintsReadHandler);
        this.addManualMapping((RootXmlReadHandler.class$java$lang$String == null) ? (RootXmlReadHandler.class$java$lang$String = class$("java.lang.String")) : RootXmlReadHandler.class$java$lang$String, (RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$StringReadHandler == null) ? (RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$StringReadHandler = class$("org.jfree.xml.parser.coretypes.StringReadHandler")) : RootXmlReadHandler.class$org$jfree$xml$parser$coretypes$StringReadHandler);
    }
    
    public abstract ObjectFactory getFactoryLoader();
    
    protected void addManualMapping(final Class clazz, final Class clazz2) {
        if (clazz2 == null) {
            throw new NullPointerException("handler must not be null.");
        }
        if (clazz == null) {
            throw new NullPointerException("classToRead must not be null.");
        }
        if (!((RootXmlReadHandler.class$org$jfree$xml$parser$XmlReadHandler == null) ? (RootXmlReadHandler.class$org$jfree$xml$parser$XmlReadHandler = class$("org.jfree.xml.parser.XmlReadHandler")) : RootXmlReadHandler.class$org$jfree$xml$parser$XmlReadHandler).isAssignableFrom(clazz2)) {
            throw new IllegalArgumentException("The given handler is no XmlReadHandler.");
        }
        this.classToHandlerMapping.addManualMapping(new ManualMappingDefinition(clazz, clazz2.getName(), null));
    }
    
    protected void addMultiplexMapping(final Class clazz, final String s, final MultiplexMappingEntry[] array) {
        this.classToHandlerMapping.addMultiplexMapping(new MultiplexMappingDefinition(clazz, s, array));
    }
    
    public void putObject(final String s, final Object o) {
        if (o == null) {
            this.objectRegistry.remove(s);
        }
        else {
            this.objectRegistry.put(s, o);
        }
    }
    
    public Object getObject(final String s) {
        return this.objectRegistry.get(s);
    }
    
    public XmlReadHandler createHandler(final Class clazz, final String s, final Attributes attributes) throws XmlReaderException {
        final XmlReadHandler handlerForClass = this.findHandlerForClass(clazz, attributes, new ArrayList());
        if (handlerForClass == null) {
            throw new NullPointerException("Unable to find handler for class: " + clazz);
        }
        handlerForClass.init(this, s);
        return handlerForClass;
    }
    
    private XmlReadHandler findHandlerForClass(final Class clazz, final Attributes attributes, final ArrayList list) throws XmlReaderException {
        final ObjectFactory factoryLoader = this.getFactoryLoader();
        if (list.contains(clazz)) {
            throw new IllegalStateException("Circular reference detected: " + list);
        }
        list.add(clazz);
        ManualMappingDefinition manualMappingDefinition = this.classToHandlerMapping.getManualMappingDefinition(clazz);
        if (manualMappingDefinition == null) {
            manualMappingDefinition = factoryLoader.getManualMappingDefinition(clazz);
        }
        if (manualMappingDefinition != null) {
            Log.debug("Locating handler for " + manualMappingDefinition.getBaseClass());
            return this.loadHandlerClass(manualMappingDefinition.getReadHandler());
        }
        MultiplexMappingDefinition multiplexMappingDefinition = this.getFactoryLoader().getMultiplexDefinition(clazz);
        if (multiplexMappingDefinition == null) {
            multiplexMappingDefinition = this.classToHandlerMapping.getMultiplexDefinition(clazz);
        }
        if (multiplexMappingDefinition != null) {
            final String value = attributes.getValue(multiplexMappingDefinition.getAttributeName());
            if (value == null) {
                throw new XmlReaderException("Multiplexer type attribute is not defined: " + multiplexMappingDefinition.getAttributeName() + " for " + clazz);
            }
            final MultiplexMappingEntry entryForType = multiplexMappingDefinition.getEntryForType(value);
            if (entryForType == null) {
                throw new XmlReaderException("Invalid type attribute value: " + multiplexMappingDefinition.getAttributeName() + " = " + value);
            }
            final Class loadClass = this.loadClass(entryForType.getTargetClass());
            if (!loadClass.equals(multiplexMappingDefinition.getBaseClass())) {
                Log.debug("Continue search on next level : " + loadClass + " after " + multiplexMappingDefinition.getBaseClass());
                return this.findHandlerForClass(loadClass, attributes, list);
            }
            Log.debug("Muliplexer is also generic definition ...");
        }
        if (this.classToHandlerMapping.isGenericHandler(clazz)) {
            return new GenericReadHandler(this.classToHandlerMapping.getFactoryForClass(clazz));
        }
        if (this.getFactoryLoader().isGenericHandler(clazz)) {
            return new GenericReadHandler(this.getFactoryLoader().getFactoryForClass(clazz));
        }
        return null;
    }
    
    protected void setRootHandler(final XmlReadHandler rootHandler) {
        this.rootHandler = rootHandler;
    }
    
    protected XmlReadHandler getRootHandler() {
        return this.rootHandler;
    }
    
    public void recurse(final XmlReadHandler xmlReadHandler, final String s, final Attributes attributes) throws XmlReaderException, SAXException {
        this.outerScopes.push(this.currentHandlers);
        (this.currentHandlers = new Stack()).push(xmlReadHandler);
        xmlReadHandler.startElement(s, attributes);
    }
    
    public void delegate(final XmlReadHandler xmlReadHandler, final String s, final Attributes attributes) throws XmlReaderException, SAXException {
        this.currentHandlers.push(xmlReadHandler);
        xmlReadHandler.startElement(s, attributes);
    }
    
    public void unwind(final String s) throws SAXException, XmlReaderException {
        this.currentHandlers.pop();
        if (this.currentHandlers.isEmpty() && !this.outerScopes.isEmpty()) {
            this.currentHandlers = this.outerScopes.pop();
        }
        if (!this.currentHandlers.isEmpty()) {
            this.getCurrentHandler().endElement(s);
        }
    }
    
    protected XmlReadHandler getCurrentHandler() {
        return this.currentHandlers.peek();
    }
    
    public void startDocument() throws SAXException {
        this.outerScopes = new Stack();
        (this.currentHandlers = new Stack()).push(this.rootHandler);
    }
    
    public void startElement(final String s, final String s2, final String s3, final Attributes attributes) throws SAXException {
        try {
            this.getCurrentHandler().startElement(s3, attributes);
        }
        catch (XmlReaderException ex) {
            throw new ParseException(ex, this.getLocator());
        }
    }
    
    public void characters(final char[] array, final int n, final int n2) throws SAXException {
        try {
            this.getCurrentHandler().characters(array, n, n2);
        }
        catch (SAXException ex) {
            throw ex;
        }
        catch (Exception ex2) {
            throw new ParseException(ex2, this.getLocator());
        }
    }
    
    public void endElement(final String s, final String s2, final String s3) throws SAXException {
        try {
            this.getCurrentHandler().endElement(s3);
        }
        catch (XmlReaderException ex) {
            throw new ParseException(ex, this.getLocator());
        }
    }
    
    protected XmlReadHandler loadHandlerClass(final String s) throws XmlReaderException {
        try {
            return this.loadClass(s).newInstance();
        }
        catch (Exception ex) {
            throw new XmlReaderException("LoadHanderClass: Unable to instantiate " + s, ex);
        }
    }
    
    protected Class loadClass(final String s) throws XmlReaderException {
        if (s == null) {
            throw new XmlReaderException("LoadHanderClass: Class name not defined");
        }
        try {
            return this.getClass().getClassLoader().loadClass(s);
        }
        catch (Exception ex) {
            throw new XmlReaderException("LoadHanderClass: Unable to load " + s, ex);
        }
    }
    
    public void setDocumentLocator(final Locator locator) {
        this.locator = locator;
    }
    
    public Locator getLocator() {
        return this.locator;
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
