// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.gui;

import java.awt.Shape;
import java.awt.event.MouseEvent;
import ch.randelshofer.geom3d.Transform3D;
import java.awt.Insets;
import java.awt.Color;
import ch.randelshofer.util.Arrays;
import ch.randelshofer.geom3d.Face3D;
import java.util.Vector;
import java.awt.Dimension;
import java.awt.Graphics;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Canvas3DJ2D extends Canvas3DAWT
{
    private static Boolean isGraphics2DAvailable;
    private static Class graphics2DClass;
    private static Method setRenderingHintMethod;
    private static Method setStrokeMethod;
    private static Method fillMethod;
    private static Method drawMethod;
    private static Class renderingHintsClass;
    private static Class renderingHintsKeyClass;
    private static Object keyAntialiasing;
    private static Object valueAntialiasOn;
    private static Object keyFractionalmetrics;
    private static Object valueFractionalmetricsOn;
    private static Method containsMethod;
    private static Class strokeClass;
    private static Class basicStrokeClass;
    private static Object capButt;
    private static Object joinBevel;
    private static Constructor basicStrokeConstructor;
    private static Class generalPathClass;
    private static Method moveToMethod;
    private static Method lineToMethod;
    private static Method closePathMethod;
    static /* synthetic */ Class class$java$lang$Object;
    static /* synthetic */ Class class$java$awt$Shape;
    
    private static void createGraphics2DMethods() {
        if (Canvas3DJ2D.isGraphics2DAvailable == null) {
            try {
                Canvas3DJ2D.graphics2DClass = Class.forName("java.awt.Graphics2D");
                Canvas3DJ2D.generalPathClass = Class.forName("java.awt.geom.GeneralPath");
                Canvas3DJ2D.renderingHintsClass = Class.forName("java.awt.RenderingHints");
                Canvas3DJ2D.renderingHintsKeyClass = Class.forName("java.awt.RenderingHints$Key");
                Canvas3DJ2D.strokeClass = Class.forName("java.awt.Stroke");
                Canvas3DJ2D.basicStrokeClass = Class.forName("java.awt.BasicStroke");
                Canvas3DJ2D.keyAntialiasing = Canvas3DJ2D.renderingHintsClass.getField("KEY_ANTIALIASING").get(null);
                Canvas3DJ2D.valueAntialiasOn = Canvas3DJ2D.renderingHintsClass.getField("VALUE_ANTIALIAS_ON").get(null);
                Canvas3DJ2D.keyFractionalmetrics = Canvas3DJ2D.renderingHintsClass.getField("KEY_FRACTIONALMETRICS").get(null);
                Canvas3DJ2D.valueFractionalmetricsOn = Canvas3DJ2D.renderingHintsClass.getField("VALUE_FRACTIONALMETRICS_ON").get(null);
                Canvas3DJ2D.capButt = Canvas3DJ2D.basicStrokeClass.getField("CAP_BUTT").get(null);
                Canvas3DJ2D.joinBevel = Canvas3DJ2D.basicStrokeClass.getField("JOIN_BEVEL").get(null);
                Canvas3DJ2D.setRenderingHintMethod = Canvas3DJ2D.graphics2DClass.getMethod("setRenderingHint", Canvas3DJ2D.renderingHintsKeyClass, (Canvas3DJ2D.class$java$lang$Object == null) ? (Canvas3DJ2D.class$java$lang$Object = class$("java.lang.Object")) : Canvas3DJ2D.class$java$lang$Object);
                Canvas3DJ2D.setStrokeMethod = Canvas3DJ2D.graphics2DClass.getMethod("setStroke", Canvas3DJ2D.strokeClass);
                Canvas3DJ2D.fillMethod = Canvas3DJ2D.graphics2DClass.getMethod("fill", (Canvas3DJ2D.class$java$awt$Shape == null) ? (Canvas3DJ2D.class$java$awt$Shape = class$("java.awt.Shape")) : Canvas3DJ2D.class$java$awt$Shape);
                Canvas3DJ2D.drawMethod = Canvas3DJ2D.graphics2DClass.getMethod("draw", (Canvas3DJ2D.class$java$awt$Shape == null) ? (Canvas3DJ2D.class$java$awt$Shape = class$("java.awt.Shape")) : Canvas3DJ2D.class$java$awt$Shape);
                Canvas3DJ2D.basicStrokeConstructor = Canvas3DJ2D.basicStrokeClass.getConstructor(Float.TYPE, Integer.TYPE, Integer.TYPE);
                Canvas3DJ2D.containsMethod = ((Canvas3DJ2D.class$java$awt$Shape == null) ? (Canvas3DJ2D.class$java$awt$Shape = class$("java.awt.Shape")) : Canvas3DJ2D.class$java$awt$Shape).getMethod("contains", Double.TYPE, Double.TYPE);
                Canvas3DJ2D.moveToMethod = Canvas3DJ2D.generalPathClass.getMethod("moveTo", Float.TYPE, Float.TYPE);
                Canvas3DJ2D.lineToMethod = Canvas3DJ2D.generalPathClass.getMethod("lineTo", Float.TYPE, Float.TYPE);
                Canvas3DJ2D.closePathMethod = Canvas3DJ2D.generalPathClass.getMethod("closePath", (Class[])new Class[0]);
                Canvas3DJ2D.isGraphics2DAvailable = Boolean.TRUE;
            }
            catch (Exception ex) {
                Canvas3DJ2D.isGraphics2DAvailable = Boolean.FALSE;
                ex.printStackTrace();
            }
        }
    }
    
    public static Canvas3DAWT createCanvas3D() {
        createGraphics2DMethods();
        return (Canvas3DJ2D.isGraphics2DAvailable == Boolean.TRUE) ? new Canvas3DJ2D() : new Canvas3DAWT();
    }
    
    private static void setGraphicHints(final Graphics graphics) {
        try {
            Canvas3DJ2D.setRenderingHintMethod.invoke(graphics, Canvas3DJ2D.keyAntialiasing, Canvas3DJ2D.valueAntialiasOn);
            Canvas3DJ2D.setRenderingHintMethod.invoke(graphics, Canvas3DJ2D.keyFractionalmetrics, Canvas3DJ2D.valueFractionalmetricsOn);
            Canvas3DJ2D.setStrokeMethod.invoke(graphics, Canvas3DJ2D.basicStrokeConstructor.newInstance(new Float(1.0f), Canvas3DJ2D.capButt, Canvas3DJ2D.joinBevel));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    protected void createBackGraphics(final Dimension dimension) {
        super.backImg = this.createImage(dimension.width, dimension.height);
        setGraphicHints(super.backGfx = super.backImg.getGraphics());
    }
    
    protected void paint3D(final Graphics graphics) {
        try {
            final Insets paintInsets = super.paintInsets;
            final Object[] array = new Object[2];
            final Object[] array2 = { null };
            final Object[] array3 = new Object[0];
            final Dimension size = this.getSize();
            final Transform3D transform = super.transformModel.getTransform();
            final float n = paintInsets.left + (size.width - paintInsets.left - paintInsets.right) / 2;
            final float n2 = paintInsets.top + (size.height - paintInsets.top - paintInsets.bottom) / 2;
            final float n3 = (float)(Math.min((size.width - paintInsets.left - paintInsets.right) / 2, (size.height - paintInsets.top - paintInsets.bottom) / 2) * super.scaleFactor);
            final float n4 = -n3;
            final Vector vector = new Vector();
            super.activeFaces.removeAllElements();
            super.scene.addVisibleFaces(vector, transform, super.observer);
            final Face3D[] array4 = new Face3D[vector.size()];
            vector.copyInto(array4);
            Arrays.sort(array4);
            final int[] array5 = new int[5];
            final int[] array6 = new int[5];
            final float n5 = (float)super.observer.x;
            final float n6 = (float)super.observer.y;
            final float n7 = (float)super.observer.z;
            for (int i = 0; i < array4.length; ++i) {
                final Face3D face3D = array4[i];
                if (face3D != null) {
                    final float[] coords = face3D.getCoords();
                    final int[] vertices = face3D.getVertices();
                    final Object instance = Canvas3DJ2D.generalPathClass.newInstance();
                    final int n8 = vertices[0] * 3;
                    final float n9 = coords[vertices[0] * 3 + 2] - n7;
                    if (n9 != 0.0f) {
                        array[0] = new Float(n + (n5 - (n7 * coords[n8] - n5) / n9) * n3);
                        array[1] = new Float(n2 + (n6 - (n7 * coords[n8 + 1] - n6) / n9) * n4);
                        Canvas3DJ2D.moveToMethod.invoke(instance, array);
                    }
                    else {
                        array[0] = new Float(n + n5 * n3);
                        array[1] = new Float(n2 + n6 * n4);
                        Canvas3DJ2D.moveToMethod.invoke(instance, array);
                    }
                    for (int j = 1; j < vertices.length; ++j) {
                        final int n10 = vertices[j] * 3;
                        final float n11 = coords[vertices[j] * 3 + 2] - n7;
                        if (n11 != 0.0f) {
                            array[0] = new Float(n + (n5 - (n7 * coords[n10] - n5) / n11) * n3);
                            array[1] = new Float(n2 + (n6 - (n7 * coords[n10 + 1] - n6) / n11) * n4);
                            Canvas3DJ2D.lineToMethod.invoke(instance, array);
                        }
                        else {
                            array[0] = new Float(n + n5 * n3);
                            array[1] = new Float(n2 + n6 * n4);
                            Canvas3DJ2D.lineToMethod.invoke(instance, array);
                        }
                    }
                    Canvas3DJ2D.closePathMethod.invoke(instance, array3);
                    final Color fillColor;
                    if ((fillColor = face3D.getFillColor()) != null) {
                        double brightness;
                        if (super.lightSource == null) {
                            brightness = 1.0;
                        }
                        else {
                            brightness = face3D.getBrightness(super.lightSource, super.lightSourceIntensity, super.ambientLightIntensity);
                        }
                        graphics.setColor(new Color(Math.min(255, (int)(fillColor.getRed() * brightness)), Math.min(255, (int)(fillColor.getGreen() * brightness)), Math.min(255, (int)(fillColor.getBlue() * brightness))));
                        array2[0] = instance;
                        Canvas3DJ2D.fillMethod.invoke(graphics, array2);
                    }
                    final Color borderColor;
                    if ((borderColor = face3D.getBorderColor()) != null) {
                        graphics.setColor(borderColor);
                        array2[0] = instance;
                        Canvas3DJ2D.drawMethod.invoke(graphics, array2);
                    }
                    if (!super.isAdjusting && face3D.getAction() != null) {
                        super.activeFaces.addElement(instance);
                        super.activeFaces.addElement(face3D);
                    }
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        try {
            if (this.isEnabled() && !super.isPopupTrigger) {
                final int x = mouseEvent.getX();
                final int y = mouseEvent.getY();
                super.prevx = x;
                super.prevy = y;
                final Object[] array = { new Double(x), new Double(y) };
                for (int i = super.activeFaces.size() - 2; i >= 0; i -= 2) {
                    final Shape shape = super.activeFaces.elementAt(i);
                    final Face3D face3D = super.activeFaces.elementAt(i + 1);
                    if (Canvas3DJ2D.containsMethod.invoke(shape, array).equals(Boolean.TRUE)) {
                        face3D.handleEvent(mouseEvent);
                        break;
                    }
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void initComponents() {
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        Canvas3DJ2D.isGraphics2DAvailable = null;
    }
}
