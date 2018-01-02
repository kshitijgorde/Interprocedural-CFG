// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.writer;

import java.io.IOException;
import org.jfree.xml.writer.coretypes.GenericWriteHandler;
import org.jfree.xml.util.MultiplexMappingDefinition;
import org.jfree.xml.util.ManualMappingDefinition;
import org.jfree.xml.util.ObjectFactory;
import org.jfree.xml.util.MultiplexMappingEntry;
import org.jfree.xml.util.SimpleObjectFactory;

public abstract class RootXmlWriteHandler
{
    private SimpleObjectFactory classToHandlerMapping;
    static /* synthetic */ Class class$java$awt$Color;
    static /* synthetic */ Class class$java$awt$GradientPaint;
    static /* synthetic */ Class class$java$awt$Paint;
    static /* synthetic */ Class class$org$jfree$xml$writer$coretypes$GradientPaintWriteHandler;
    static /* synthetic */ Class class$org$jfree$xml$writer$coretypes$ColorWriteHandler;
    static /* synthetic */ Class class$java$awt$geom$Point2D$Float;
    static /* synthetic */ Class class$java$awt$geom$Point2D$Double;
    static /* synthetic */ Class class$java$awt$geom$Point2D;
    static /* synthetic */ Class class$org$jfree$xml$writer$coretypes$Point2DWriteHandler;
    static /* synthetic */ Class class$java$awt$BasicStroke;
    static /* synthetic */ Class class$java$awt$Stroke;
    static /* synthetic */ Class class$org$jfree$xml$writer$coretypes$BasicStrokeWriteHandler;
    static /* synthetic */ Class class$java$awt$geom$Rectangle2D$Float;
    static /* synthetic */ Class class$java$awt$geom$Rectangle2D$Double;
    static /* synthetic */ Class class$java$awt$geom$Rectangle2D;
    static /* synthetic */ Class class$org$jfree$xml$writer$coretypes$Rectangle2DWriteHandler;
    static /* synthetic */ Class class$java$util$ArrayList;
    static /* synthetic */ Class class$java$util$LinkedList;
    static /* synthetic */ Class class$java$util$Vector;
    static /* synthetic */ Class class$java$util$Stack;
    static /* synthetic */ Class class$java$util$List;
    static /* synthetic */ Class class$org$jfree$xml$writer$coretypes$ListWriteHandler;
    static /* synthetic */ Class class$java$awt$RenderingHints;
    static /* synthetic */ Class class$org$jfree$xml$writer$coretypes$RenderingHintsWriteHandler;
    static /* synthetic */ Class class$java$awt$Insets;
    static /* synthetic */ Class class$org$jfree$xml$writer$coretypes$InsetsWriteHandler;
    static /* synthetic */ Class class$java$awt$Font;
    static /* synthetic */ Class class$org$jfree$xml$writer$coretypes$FontWriteHandler;
    static /* synthetic */ Class class$org$jfree$xml$writer$XmlWriteHandler;
    
    public RootXmlWriteHandler() {
        this.classToHandlerMapping = new SimpleObjectFactory();
        this.addMultiplexMapping((RootXmlWriteHandler.class$java$awt$Paint == null) ? (RootXmlWriteHandler.class$java$awt$Paint = class$("java.awt.Paint")) : RootXmlWriteHandler.class$java$awt$Paint, "type", new MultiplexMappingEntry[] { new MultiplexMappingEntry("color", ((RootXmlWriteHandler.class$java$awt$Color == null) ? (RootXmlWriteHandler.class$java$awt$Color = class$("java.awt.Color")) : RootXmlWriteHandler.class$java$awt$Color).getName()), new MultiplexMappingEntry("gradientPaint", ((RootXmlWriteHandler.class$java$awt$GradientPaint == null) ? (RootXmlWriteHandler.class$java$awt$GradientPaint = class$("java.awt.GradientPaint")) : RootXmlWriteHandler.class$java$awt$GradientPaint).getName()) });
        this.addManualMapping((RootXmlWriteHandler.class$java$awt$GradientPaint == null) ? (RootXmlWriteHandler.class$java$awt$GradientPaint = class$("java.awt.GradientPaint")) : RootXmlWriteHandler.class$java$awt$GradientPaint, (RootXmlWriteHandler.class$org$jfree$xml$writer$coretypes$GradientPaintWriteHandler == null) ? (RootXmlWriteHandler.class$org$jfree$xml$writer$coretypes$GradientPaintWriteHandler = class$("org.jfree.xml.writer.coretypes.GradientPaintWriteHandler")) : RootXmlWriteHandler.class$org$jfree$xml$writer$coretypes$GradientPaintWriteHandler);
        this.addManualMapping((RootXmlWriteHandler.class$java$awt$Color == null) ? (RootXmlWriteHandler.class$java$awt$Color = class$("java.awt.Color")) : RootXmlWriteHandler.class$java$awt$Color, (RootXmlWriteHandler.class$org$jfree$xml$writer$coretypes$ColorWriteHandler == null) ? (RootXmlWriteHandler.class$org$jfree$xml$writer$coretypes$ColorWriteHandler = class$("org.jfree.xml.writer.coretypes.ColorWriteHandler")) : RootXmlWriteHandler.class$org$jfree$xml$writer$coretypes$ColorWriteHandler);
        this.addMultiplexMapping((RootXmlWriteHandler.class$java$awt$geom$Point2D == null) ? (RootXmlWriteHandler.class$java$awt$geom$Point2D = class$("java.awt.geom.Point2D")) : RootXmlWriteHandler.class$java$awt$geom$Point2D, "type", new MultiplexMappingEntry[] { new MultiplexMappingEntry("float", ((RootXmlWriteHandler.class$java$awt$geom$Point2D$Float == null) ? (RootXmlWriteHandler.class$java$awt$geom$Point2D$Float = class$("java.awt.geom.Point2D$Float")) : RootXmlWriteHandler.class$java$awt$geom$Point2D$Float).getName()), new MultiplexMappingEntry("double", ((RootXmlWriteHandler.class$java$awt$geom$Point2D$Double == null) ? (RootXmlWriteHandler.class$java$awt$geom$Point2D$Double = class$("java.awt.geom.Point2D$Double")) : RootXmlWriteHandler.class$java$awt$geom$Point2D$Double).getName()) });
        this.addManualMapping((RootXmlWriteHandler.class$java$awt$geom$Point2D$Float == null) ? (RootXmlWriteHandler.class$java$awt$geom$Point2D$Float = class$("java.awt.geom.Point2D$Float")) : RootXmlWriteHandler.class$java$awt$geom$Point2D$Float, (RootXmlWriteHandler.class$org$jfree$xml$writer$coretypes$Point2DWriteHandler == null) ? (RootXmlWriteHandler.class$org$jfree$xml$writer$coretypes$Point2DWriteHandler = class$("org.jfree.xml.writer.coretypes.Point2DWriteHandler")) : RootXmlWriteHandler.class$org$jfree$xml$writer$coretypes$Point2DWriteHandler);
        this.addManualMapping((RootXmlWriteHandler.class$java$awt$geom$Point2D$Double == null) ? (RootXmlWriteHandler.class$java$awt$geom$Point2D$Double = class$("java.awt.geom.Point2D$Double")) : RootXmlWriteHandler.class$java$awt$geom$Point2D$Double, (RootXmlWriteHandler.class$org$jfree$xml$writer$coretypes$Point2DWriteHandler == null) ? (RootXmlWriteHandler.class$org$jfree$xml$writer$coretypes$Point2DWriteHandler = class$("org.jfree.xml.writer.coretypes.Point2DWriteHandler")) : RootXmlWriteHandler.class$org$jfree$xml$writer$coretypes$Point2DWriteHandler);
        this.addMultiplexMapping((RootXmlWriteHandler.class$java$awt$Stroke == null) ? (RootXmlWriteHandler.class$java$awt$Stroke = class$("java.awt.Stroke")) : RootXmlWriteHandler.class$java$awt$Stroke, "type", new MultiplexMappingEntry[] { new MultiplexMappingEntry("basic", ((RootXmlWriteHandler.class$java$awt$BasicStroke == null) ? (RootXmlWriteHandler.class$java$awt$BasicStroke = class$("java.awt.BasicStroke")) : RootXmlWriteHandler.class$java$awt$BasicStroke).getName()) });
        this.addManualMapping((RootXmlWriteHandler.class$java$awt$BasicStroke == null) ? (RootXmlWriteHandler.class$java$awt$BasicStroke = class$("java.awt.BasicStroke")) : RootXmlWriteHandler.class$java$awt$BasicStroke, (RootXmlWriteHandler.class$org$jfree$xml$writer$coretypes$BasicStrokeWriteHandler == null) ? (RootXmlWriteHandler.class$org$jfree$xml$writer$coretypes$BasicStrokeWriteHandler = class$("org.jfree.xml.writer.coretypes.BasicStrokeWriteHandler")) : RootXmlWriteHandler.class$org$jfree$xml$writer$coretypes$BasicStrokeWriteHandler);
        this.addMultiplexMapping((RootXmlWriteHandler.class$java$awt$geom$Rectangle2D == null) ? (RootXmlWriteHandler.class$java$awt$geom$Rectangle2D = class$("java.awt.geom.Rectangle2D")) : RootXmlWriteHandler.class$java$awt$geom$Rectangle2D, "type", new MultiplexMappingEntry[] { new MultiplexMappingEntry("float", ((RootXmlWriteHandler.class$java$awt$geom$Rectangle2D$Float == null) ? (RootXmlWriteHandler.class$java$awt$geom$Rectangle2D$Float = class$("java.awt.geom.Rectangle2D$Float")) : RootXmlWriteHandler.class$java$awt$geom$Rectangle2D$Float).getName()), new MultiplexMappingEntry("double", ((RootXmlWriteHandler.class$java$awt$geom$Rectangle2D$Double == null) ? (RootXmlWriteHandler.class$java$awt$geom$Rectangle2D$Double = class$("java.awt.geom.Rectangle2D$Double")) : RootXmlWriteHandler.class$java$awt$geom$Rectangle2D$Double).getName()) });
        this.addManualMapping((RootXmlWriteHandler.class$java$awt$geom$Rectangle2D$Float == null) ? (RootXmlWriteHandler.class$java$awt$geom$Rectangle2D$Float = class$("java.awt.geom.Rectangle2D$Float")) : RootXmlWriteHandler.class$java$awt$geom$Rectangle2D$Float, (RootXmlWriteHandler.class$org$jfree$xml$writer$coretypes$Rectangle2DWriteHandler == null) ? (RootXmlWriteHandler.class$org$jfree$xml$writer$coretypes$Rectangle2DWriteHandler = class$("org.jfree.xml.writer.coretypes.Rectangle2DWriteHandler")) : RootXmlWriteHandler.class$org$jfree$xml$writer$coretypes$Rectangle2DWriteHandler);
        this.addManualMapping((RootXmlWriteHandler.class$java$awt$geom$Rectangle2D$Double == null) ? (RootXmlWriteHandler.class$java$awt$geom$Rectangle2D$Double = class$("java.awt.geom.Rectangle2D$Double")) : RootXmlWriteHandler.class$java$awt$geom$Rectangle2D$Double, (RootXmlWriteHandler.class$org$jfree$xml$writer$coretypes$Rectangle2DWriteHandler == null) ? (RootXmlWriteHandler.class$org$jfree$xml$writer$coretypes$Rectangle2DWriteHandler = class$("org.jfree.xml.writer.coretypes.Rectangle2DWriteHandler")) : RootXmlWriteHandler.class$org$jfree$xml$writer$coretypes$Rectangle2DWriteHandler);
        this.addMultiplexMapping((RootXmlWriteHandler.class$java$util$List == null) ? (RootXmlWriteHandler.class$java$util$List = class$("java.util.List")) : RootXmlWriteHandler.class$java$util$List, "type", new MultiplexMappingEntry[] { new MultiplexMappingEntry("array-list", ((RootXmlWriteHandler.class$java$util$ArrayList == null) ? (RootXmlWriteHandler.class$java$util$ArrayList = class$("java.util.ArrayList")) : RootXmlWriteHandler.class$java$util$ArrayList).getName()), new MultiplexMappingEntry("linked-list", ((RootXmlWriteHandler.class$java$util$LinkedList == null) ? (RootXmlWriteHandler.class$java$util$LinkedList = class$("java.util.LinkedList")) : RootXmlWriteHandler.class$java$util$LinkedList).getName()), new MultiplexMappingEntry("vector", ((RootXmlWriteHandler.class$java$util$Vector == null) ? (RootXmlWriteHandler.class$java$util$Vector = class$("java.util.Vector")) : RootXmlWriteHandler.class$java$util$Vector).getName()), new MultiplexMappingEntry("stack", ((RootXmlWriteHandler.class$java$util$Stack == null) ? (RootXmlWriteHandler.class$java$util$Stack = class$("java.util.Stack")) : RootXmlWriteHandler.class$java$util$Stack).getName()) });
        this.addManualMapping((RootXmlWriteHandler.class$java$util$LinkedList == null) ? (RootXmlWriteHandler.class$java$util$LinkedList = class$("java.util.LinkedList")) : RootXmlWriteHandler.class$java$util$LinkedList, (RootXmlWriteHandler.class$org$jfree$xml$writer$coretypes$ListWriteHandler == null) ? (RootXmlWriteHandler.class$org$jfree$xml$writer$coretypes$ListWriteHandler = class$("org.jfree.xml.writer.coretypes.ListWriteHandler")) : RootXmlWriteHandler.class$org$jfree$xml$writer$coretypes$ListWriteHandler);
        this.addManualMapping((RootXmlWriteHandler.class$java$util$Vector == null) ? (RootXmlWriteHandler.class$java$util$Vector = class$("java.util.Vector")) : RootXmlWriteHandler.class$java$util$Vector, (RootXmlWriteHandler.class$org$jfree$xml$writer$coretypes$ListWriteHandler == null) ? (RootXmlWriteHandler.class$org$jfree$xml$writer$coretypes$ListWriteHandler = class$("org.jfree.xml.writer.coretypes.ListWriteHandler")) : RootXmlWriteHandler.class$org$jfree$xml$writer$coretypes$ListWriteHandler);
        this.addManualMapping((RootXmlWriteHandler.class$java$util$ArrayList == null) ? (RootXmlWriteHandler.class$java$util$ArrayList = class$("java.util.ArrayList")) : RootXmlWriteHandler.class$java$util$ArrayList, (RootXmlWriteHandler.class$org$jfree$xml$writer$coretypes$ListWriteHandler == null) ? (RootXmlWriteHandler.class$org$jfree$xml$writer$coretypes$ListWriteHandler = class$("org.jfree.xml.writer.coretypes.ListWriteHandler")) : RootXmlWriteHandler.class$org$jfree$xml$writer$coretypes$ListWriteHandler);
        this.addManualMapping((RootXmlWriteHandler.class$java$util$Stack == null) ? (RootXmlWriteHandler.class$java$util$Stack = class$("java.util.Stack")) : RootXmlWriteHandler.class$java$util$Stack, (RootXmlWriteHandler.class$org$jfree$xml$writer$coretypes$ListWriteHandler == null) ? (RootXmlWriteHandler.class$org$jfree$xml$writer$coretypes$ListWriteHandler = class$("org.jfree.xml.writer.coretypes.ListWriteHandler")) : RootXmlWriteHandler.class$org$jfree$xml$writer$coretypes$ListWriteHandler);
        this.addManualMapping((RootXmlWriteHandler.class$java$awt$RenderingHints == null) ? (RootXmlWriteHandler.class$java$awt$RenderingHints = class$("java.awt.RenderingHints")) : RootXmlWriteHandler.class$java$awt$RenderingHints, (RootXmlWriteHandler.class$org$jfree$xml$writer$coretypes$RenderingHintsWriteHandler == null) ? (RootXmlWriteHandler.class$org$jfree$xml$writer$coretypes$RenderingHintsWriteHandler = class$("org.jfree.xml.writer.coretypes.RenderingHintsWriteHandler")) : RootXmlWriteHandler.class$org$jfree$xml$writer$coretypes$RenderingHintsWriteHandler);
        this.addManualMapping((RootXmlWriteHandler.class$java$awt$Insets == null) ? (RootXmlWriteHandler.class$java$awt$Insets = class$("java.awt.Insets")) : RootXmlWriteHandler.class$java$awt$Insets, (RootXmlWriteHandler.class$org$jfree$xml$writer$coretypes$InsetsWriteHandler == null) ? (RootXmlWriteHandler.class$org$jfree$xml$writer$coretypes$InsetsWriteHandler = class$("org.jfree.xml.writer.coretypes.InsetsWriteHandler")) : RootXmlWriteHandler.class$org$jfree$xml$writer$coretypes$InsetsWriteHandler);
        this.addManualMapping((RootXmlWriteHandler.class$java$awt$Font == null) ? (RootXmlWriteHandler.class$java$awt$Font = class$("java.awt.Font")) : RootXmlWriteHandler.class$java$awt$Font, (RootXmlWriteHandler.class$org$jfree$xml$writer$coretypes$FontWriteHandler == null) ? (RootXmlWriteHandler.class$org$jfree$xml$writer$coretypes$FontWriteHandler = class$("org.jfree.xml.writer.coretypes.FontWriteHandler")) : RootXmlWriteHandler.class$org$jfree$xml$writer$coretypes$FontWriteHandler);
    }
    
    protected abstract ObjectFactory getFactoryLoader();
    
    protected void addManualMapping(final Class clazz, final Class clazz2) {
        if (clazz2 == null) {
            throw new NullPointerException("handler must not be null.");
        }
        if (clazz == null) {
            throw new NullPointerException("classToWrite must not be null.");
        }
        if (!((RootXmlWriteHandler.class$org$jfree$xml$writer$XmlWriteHandler == null) ? (RootXmlWriteHandler.class$org$jfree$xml$writer$XmlWriteHandler = class$("org.jfree.xml.writer.XmlWriteHandler")) : RootXmlWriteHandler.class$org$jfree$xml$writer$XmlWriteHandler).isAssignableFrom(clazz2)) {
            throw new IllegalArgumentException("The given handler is no XmlWriteHandler.");
        }
        this.classToHandlerMapping.addManualMapping(new ManualMappingDefinition(clazz, null, clazz2.getName()));
    }
    
    protected void addMultiplexMapping(final Class clazz, final String s, final MultiplexMappingEntry[] array) {
        this.classToHandlerMapping.addMultiplexMapping(new MultiplexMappingDefinition(clazz, s, array));
    }
    
    protected XmlWriteHandler getMapping(final Class clazz) throws XMLWriterException {
        if (clazz == null) {
            throw new NullPointerException("ClassToWrite is null.");
        }
        ManualMappingDefinition manualMappingDefinition = this.classToHandlerMapping.getManualMappingDefinition(clazz);
        if (manualMappingDefinition == null) {
            manualMappingDefinition = this.getFactoryLoader().getManualMappingDefinition(clazz);
        }
        if (manualMappingDefinition != null) {
            return this.loadHandlerClass(manualMappingDefinition.getWriteHandler());
        }
        if (this.classToHandlerMapping.isGenericHandler(clazz)) {
            return new GenericWriteHandler(this.classToHandlerMapping.getFactoryForClass(clazz));
        }
        if (this.getFactoryLoader().isGenericHandler(clazz)) {
            return new GenericWriteHandler(this.getFactoryLoader().getFactoryForClass(clazz));
        }
        throw new XMLWriterException("Unable to handle " + clazz);
    }
    
    public void write(final String s, final Object o, final Class clazz, final XMLWriter xmlWriter) throws IOException, XMLWriterException {
        if (o == null) {
            return;
        }
        if (s == null) {
            throw new NullPointerException("RootXmlWriteHandler.write(..) : tagName is null");
        }
        if (xmlWriter == null) {
            throw new NullPointerException("RootXmlWriteHandler.write(..) : writer is null");
        }
        if (!clazz.isInstance(o)) {
            throw new ClassCastException("Object is no instance of " + clazz);
        }
        final Class<?> class1 = o.getClass();
        final XmlWriteHandler mapping = this.getMapping(class1);
        mapping.setRootHandler(this);
        String attributeName = null;
        String attributeValue = null;
        MultiplexMappingDefinition multiplexMappingDefinition = this.getFactoryLoader().getMultiplexDefinition(clazz);
        if (multiplexMappingDefinition == null) {
            multiplexMappingDefinition = this.classToHandlerMapping.getMultiplexDefinition(clazz);
        }
        if (multiplexMappingDefinition != null) {
            final MultiplexMappingEntry entryForClass = multiplexMappingDefinition.getEntryForClass(class1.getName());
            if (entryForClass == null) {
                throw new XMLWriterException("Unable to find child mapping for multiplexer " + clazz + " to child " + class1);
            }
            attributeName = multiplexMappingDefinition.getAttributeName();
            attributeValue = entryForClass.getAttributeValue();
        }
        mapping.write(s, o, xmlWriter, attributeName, attributeValue);
        xmlWriter.allowLineBreak();
    }
    
    protected XmlWriteHandler loadHandlerClass(final String s) throws XMLWriterException {
        if (s == null) {
            throw new XMLWriterException("LoadHanderClass: Class name not defined");
        }
        try {
            return (XmlWriteHandler)this.getClass().getClassLoader().loadClass(s).newInstance();
        }
        catch (Exception ex) {
            throw new XMLWriterException("LoadHanderClass: Unable to instantiate " + s, ex);
        }
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
