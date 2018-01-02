// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.io;

import java.awt.geom.Point2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Line2D;
import java.awt.Shape;
import java.awt.BasicStroke;
import java.awt.Stroke;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.awt.GradientPaint;
import java.awt.Color;
import java.awt.Paint;
import java.io.ObjectInputStream;

public abstract class SerialUtilities
{
    static /* synthetic */ Class class$java$io$Serializable;
    static /* synthetic */ Class class$java$awt$GradientPaint;
    static /* synthetic */ Class class$java$awt$BasicStroke;
    static /* synthetic */ Class class$java$awt$geom$Line2D;
    static /* synthetic */ Class class$java$awt$geom$Rectangle2D;
    static /* synthetic */ Class class$java$awt$geom$Ellipse2D;
    
    public static boolean isSerializable(final Class clazz) {
        boolean b = false;
        final Class[] interfaces = clazz.getInterfaces();
        for (int i = 0; i < interfaces.length; ++i) {
            if (interfaces[i].equals((SerialUtilities.class$java$io$Serializable == null) ? (SerialUtilities.class$java$io$Serializable = class$("java.io.Serializable")) : SerialUtilities.class$java$io$Serializable)) {
                b = true;
            }
        }
        return b;
    }
    
    public static Paint readPaint(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        if (objectInputStream == null) {
            throw new IllegalArgumentException("Null 'stream' argument.");
        }
        Paint paint = null;
        if (!objectInputStream.readBoolean()) {
            final Class clazz = (Class)objectInputStream.readObject();
            if (isSerializable(clazz)) {
                paint = (Paint)objectInputStream.readObject();
            }
            else if (clazz.equals((SerialUtilities.class$java$awt$GradientPaint == null) ? (SerialUtilities.class$java$awt$GradientPaint = class$("java.awt.GradientPaint")) : SerialUtilities.class$java$awt$GradientPaint)) {
                paint = new GradientPaint(objectInputStream.readFloat(), objectInputStream.readFloat(), (Color)objectInputStream.readObject(), objectInputStream.readFloat(), objectInputStream.readFloat(), (Color)objectInputStream.readObject(), objectInputStream.readBoolean());
            }
        }
        return paint;
    }
    
    public static void writePaint(final Paint paint, final ObjectOutputStream objectOutputStream) throws IOException {
        if (objectOutputStream == null) {
            throw new IllegalArgumentException("Null 'stream' argument.");
        }
        if (paint != null) {
            objectOutputStream.writeBoolean(false);
            objectOutputStream.writeObject(paint.getClass());
            if (paint instanceof Serializable) {
                objectOutputStream.writeObject(paint);
            }
            else if (paint instanceof GradientPaint) {
                final GradientPaint gradientPaint = (GradientPaint)paint;
                objectOutputStream.writeFloat((float)gradientPaint.getPoint1().getX());
                objectOutputStream.writeFloat((float)gradientPaint.getPoint1().getY());
                objectOutputStream.writeObject(gradientPaint.getColor1());
                objectOutputStream.writeFloat((float)gradientPaint.getPoint2().getX());
                objectOutputStream.writeFloat((float)gradientPaint.getPoint2().getY());
                objectOutputStream.writeObject(gradientPaint.getColor2());
                objectOutputStream.writeBoolean(gradientPaint.isCyclic());
            }
        }
        else {
            objectOutputStream.writeBoolean(true);
        }
    }
    
    public static Stroke readStroke(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        if (objectInputStream == null) {
            throw new IllegalArgumentException("Null 'stream' argument.");
        }
        Stroke stroke = null;
        if (!objectInputStream.readBoolean()) {
            if (((Class)objectInputStream.readObject()).equals((SerialUtilities.class$java$awt$BasicStroke == null) ? (SerialUtilities.class$java$awt$BasicStroke = class$("java.awt.BasicStroke")) : SerialUtilities.class$java$awt$BasicStroke)) {
                stroke = new BasicStroke(objectInputStream.readFloat(), objectInputStream.readInt(), objectInputStream.readInt(), objectInputStream.readFloat(), (float[])objectInputStream.readObject(), objectInputStream.readFloat());
            }
            else {
                stroke = (Stroke)objectInputStream.readObject();
            }
        }
        return stroke;
    }
    
    public static void writeStroke(final Stroke stroke, final ObjectOutputStream objectOutputStream) throws IOException {
        if (objectOutputStream == null) {
            throw new IllegalArgumentException("Null 'stream' argument.");
        }
        if (stroke != null) {
            objectOutputStream.writeBoolean(false);
            if (stroke instanceof BasicStroke) {
                final BasicStroke basicStroke = (BasicStroke)stroke;
                objectOutputStream.writeObject((SerialUtilities.class$java$awt$BasicStroke == null) ? (SerialUtilities.class$java$awt$BasicStroke = class$("java.awt.BasicStroke")) : SerialUtilities.class$java$awt$BasicStroke);
                objectOutputStream.writeFloat(basicStroke.getLineWidth());
                objectOutputStream.writeInt(basicStroke.getEndCap());
                objectOutputStream.writeInt(basicStroke.getLineJoin());
                objectOutputStream.writeFloat(basicStroke.getMiterLimit());
                objectOutputStream.writeObject(basicStroke.getDashArray());
                objectOutputStream.writeFloat(basicStroke.getDashPhase());
            }
            else {
                objectOutputStream.writeObject(stroke.getClass());
                objectOutputStream.writeObject(stroke);
            }
        }
        else {
            objectOutputStream.writeBoolean(true);
        }
    }
    
    public static Shape readShape(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        if (objectInputStream == null) {
            throw new IllegalArgumentException("Null 'stream' argument.");
        }
        Shape shape = null;
        if (!objectInputStream.readBoolean()) {
            final Class clazz = (Class)objectInputStream.readObject();
            if (clazz.equals((SerialUtilities.class$java$awt$geom$Line2D == null) ? (SerialUtilities.class$java$awt$geom$Line2D = class$("java.awt.geom.Line2D")) : SerialUtilities.class$java$awt$geom$Line2D)) {
                shape = new Line2D.Double(objectInputStream.readDouble(), objectInputStream.readDouble(), objectInputStream.readDouble(), objectInputStream.readDouble());
            }
            else if (clazz.equals((SerialUtilities.class$java$awt$geom$Rectangle2D == null) ? (SerialUtilities.class$java$awt$geom$Rectangle2D = class$("java.awt.geom.Rectangle2D")) : SerialUtilities.class$java$awt$geom$Rectangle2D)) {
                shape = new Rectangle2D.Double(objectInputStream.readDouble(), objectInputStream.readDouble(), objectInputStream.readDouble(), objectInputStream.readDouble());
            }
            else if (clazz.equals((SerialUtilities.class$java$awt$geom$Ellipse2D == null) ? (SerialUtilities.class$java$awt$geom$Ellipse2D = class$("java.awt.geom.Ellipse2D")) : SerialUtilities.class$java$awt$geom$Ellipse2D)) {
                shape = new Ellipse2D.Double(objectInputStream.readDouble(), objectInputStream.readDouble(), objectInputStream.readDouble(), objectInputStream.readDouble());
            }
            else {
                shape = (Shape)objectInputStream.readObject();
            }
        }
        return shape;
    }
    
    public static void writeShape(final Shape shape, final ObjectOutputStream objectOutputStream) throws IOException {
        if (objectOutputStream == null) {
            throw new IllegalArgumentException("Null 'stream' argument.");
        }
        if (shape != null) {
            objectOutputStream.writeBoolean(false);
            if (shape instanceof Line2D) {
                final Line2D line2D = (Line2D)shape;
                objectOutputStream.writeObject((SerialUtilities.class$java$awt$geom$Line2D == null) ? (SerialUtilities.class$java$awt$geom$Line2D = class$("java.awt.geom.Line2D")) : SerialUtilities.class$java$awt$geom$Line2D);
                objectOutputStream.writeDouble(line2D.getX1());
                objectOutputStream.writeDouble(line2D.getY1());
                objectOutputStream.writeDouble(line2D.getX2());
                objectOutputStream.writeDouble(line2D.getY2());
            }
            else if (shape instanceof Rectangle2D) {
                final Rectangle2D rectangle2D = (Rectangle2D)shape;
                objectOutputStream.writeObject((SerialUtilities.class$java$awt$geom$Rectangle2D == null) ? (SerialUtilities.class$java$awt$geom$Rectangle2D = class$("java.awt.geom.Rectangle2D")) : SerialUtilities.class$java$awt$geom$Rectangle2D);
                objectOutputStream.writeDouble(rectangle2D.getX());
                objectOutputStream.writeDouble(rectangle2D.getY());
                objectOutputStream.writeDouble(rectangle2D.getWidth());
                objectOutputStream.writeDouble(rectangle2D.getHeight());
            }
            else if (shape instanceof Ellipse2D) {
                final Ellipse2D ellipse2D = (Ellipse2D)shape;
                objectOutputStream.writeObject((SerialUtilities.class$java$awt$geom$Ellipse2D == null) ? (SerialUtilities.class$java$awt$geom$Ellipse2D = class$("java.awt.geom.Ellipse2D")) : SerialUtilities.class$java$awt$geom$Ellipse2D);
                objectOutputStream.writeDouble(ellipse2D.getX());
                objectOutputStream.writeDouble(ellipse2D.getY());
                objectOutputStream.writeDouble(ellipse2D.getWidth());
                objectOutputStream.writeDouble(ellipse2D.getHeight());
            }
            else {
                objectOutputStream.writeObject(shape.getClass());
                objectOutputStream.writeObject(shape);
            }
        }
        else {
            objectOutputStream.writeBoolean(true);
        }
    }
    
    public static Point2D readPoint2D(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        if (objectInputStream == null) {
            throw new IllegalArgumentException("Null 'stream' argument.");
        }
        Point2D point2D = null;
        if (!objectInputStream.readBoolean()) {
            point2D = new Point2D.Double(objectInputStream.readDouble(), objectInputStream.readDouble());
        }
        return point2D;
    }
    
    public static void writePoint2D(final Point2D point2D, final ObjectOutputStream objectOutputStream) throws IOException {
        if (objectOutputStream == null) {
            throw new IllegalArgumentException("Null 'stream' argument.");
        }
        if (point2D != null) {
            objectOutputStream.writeBoolean(false);
            objectOutputStream.writeDouble(point2D.getX());
            objectOutputStream.writeDouble(point2D.getY());
        }
        else {
            objectOutputStream.writeBoolean(true);
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
