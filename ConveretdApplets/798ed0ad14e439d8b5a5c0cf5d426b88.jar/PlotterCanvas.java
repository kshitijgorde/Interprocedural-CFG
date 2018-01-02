import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.awt.AWTEvent;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class PlotterCanvas extends WBackPanel
{
    public static final String[] infoArray;
    private Hashtable movedPoints;
    private Hashtable allPoints;
    private String movingPointName;
    private Hashtable movablePoints;
    private Hashtable boxes;
    private AbstractBox mouseReleasedBox;
    public String currentName;
    public String currentLabel;
    public String currentValue;
    public String currentDefinition;
    public int mouseLabelMode;
    public String mouseLabel;
    public int mouseState;
    private int group;
    public static String[] script_format;
    public static String[] omega_format;
    private String title;
    
    public PlotterCanvas() {
        this.mouseLabelMode = 1;
        this.mouseState = 0;
        this.group = -1;
        this.movedPoints = new Hashtable();
        this.allPoints = new Hashtable();
        this.movablePoints = new Hashtable();
        this.boxes = new Hashtable();
    }
    
    public final void markPointAsMoved(final String s) {
        this.movedPoints.put(s, Boolean.TRUE);
    }
    
    public final void setPoint(final String s, final float[] array) {
        this.allPoints.put(s, array);
    }
    
    public final float[] getPoint(final String s) {
        return this.allPoints.get(s);
    }
    
    public final Object getFigure(final String s) {
        return this.allPoints.get(s);
    }
    
    public final void setPointIfUndefined(final String s, final float[] array) {
        final Object value = this.allPoints.get(s);
        if (value == null || value instanceof Boolean) {
            this.setPoint(s, array);
        }
    }
    
    public final void clearAllPoints() {
        this.allPoints.clear();
        this.movedPoints.clear();
        this.movablePoints.clear();
        this.boxes.clear();
        this.unselectPoint();
    }
    
    public final void setSelectedPoint(final String movingPointName) {
        this.markPointAsMoved(this.movingPointName = movingPointName);
    }
    
    public final void setSelectedPoint(final String selectedPoint, final float[] array) {
        this.setSelectedPoint(selectedPoint);
        this.setPoint(selectedPoint, array);
    }
    
    public final void setMovablePoint(final String s, final boolean b) {
        if (b) {
            this.movablePoints.put(s, Boolean.TRUE);
        }
        else {
            this.movablePoints.remove(s);
        }
    }
    
    public final void unselectPoint() {
        this.movingPointName = null;
        if (this.mouseReleasedBox != null) {
            this.mouseReleasedBox.processEvent(null, null);
            this.mouseReleasedBox = null;
        }
    }
    
    public final boolean thereIsSelectedPoint() {
        return this.movingPointName != null;
    }
    
    public final String getSelectedPoint() {
        return this.movingPointName;
    }
    
    public final boolean isPointSelected(final String s) {
        return this.movingPointName != null && s.equals(this.movingPointName);
    }
    
    public final Enumeration getMovablePoints() {
        return this.movablePoints.keys();
    }
    
    public final Enumeration getMovedPoints() {
        return this.movedPoints.keys();
    }
    
    public void exchangeProperties(final Hashtable hashtable, final int n) {
        if (n == 1) {
            final Enumeration movedPoints = this.getMovedPoints();
            int n2 = 0;
            while (movedPoints.hasMoreElements()) {
                Attributes.exchangeString(hashtable, "point" + n2, n, movedPoints.nextElement(), "");
                ++n2;
            }
        }
        this.group = Attributes.exchangeInt(hashtable, "group", n, this.group, -1);
        if (n == 0 || n == 4097) {
            final Enumeration<String> keys = (Enumeration<String>)hashtable.keys();
            while (keys.hasMoreElements()) {
                final String s = keys.nextElement();
                if (s.startsWith("point")) {
                    this.markPointAsMoved(hashtable.get(s));
                }
            }
        }
    }
    
    public final int getGroup() {
        return this.group;
    }
    
    public final void setGroup(final int group) {
        this.group = group;
    }
    
    public final void setMouseOverLabel() {
        if (this.currentLabel == null) {
            switch (this.mouseLabelMode) {
                case 1: {
                    this.mouseLabel = this.currentName;
                    break;
                }
                case 2: {
                    this.mouseLabel = this.currentValue;
                    break;
                }
                case 3: {
                    this.mouseLabel = this.currentDefinition;
                    break;
                }
            }
            return;
        }
        if (this.currentLabel.length() == 0) {
            return;
        }
        this.mouseLabel = this.currentLabel;
    }
    
    public final String getDisplayLabel() {
        return (this.currentLabel == null) ? this.currentName : this.currentLabel;
    }
    
    public final void clearCurrent() {
        this.currentName = null;
        this.currentValue = null;
        this.currentDefinition = null;
        this.currentLabel = null;
    }
    
    public final void clearMouseLabel() {
        this.mouseLabel = null;
    }
    
    public final void setData(final InputStream inputStream, final AbstractBox abstractBox) {
        this.setData(inputStream, abstractBox, true);
    }
    
    public void setData(final InputStream inputStream, final AbstractBox abstractBox, final boolean b) {
        if (abstractBox instanceof CapsaComandes) {
            final PlotterPanel[] plotters = ((CapsaComandes)abstractBox).getPlotters();
            if (plotters != null && plotters.length > 0) {
                plotters[0].getPlotComp().copyStatusTo(this);
            }
        }
    }
    
    public final void copyStatusTo(final PlotterCanvas plotterCanvas) {
        plotterCanvas.movedPoints = this.movedPoints;
        plotterCanvas.allPoints = this.allPoints;
    }
    
    public final String scriptFigure(final String[] array, final String s, final boolean b) {
        final StringBuffer sb = new StringBuffer();
        final String s2 = array[0];
        final String s3 = array[1];
        final String s4 = array[6];
        String cadenaOmega;
        if (array == PlotterCanvas.omega_format) {
            cadenaOmega = FormulaEditorCalc.cadenaOmega(Formula.staticParse(s));
        }
        else {
            cadenaOmega = s;
        }
        Object figure = this.getFigure(s);
        if (figure instanceof Float) {
            figure = new float[] { (float)figure };
        }
        if (figure instanceof float[]) {
            final float[] array2 = (float[])figure;
            if (b) {
                sb.append(this.TRUE(array, "move") + s2 + cadenaOmega + s4);
            }
            else {
                sb.append(cadenaOmega + "=");
            }
            if (array2.length > 1) {
                sb.append(this.TRUE(array, "point") + s2);
            }
            for (int i = 0; i < array2.length; ++i) {
                if (i > 0) {
                    sb.append(s4);
                }
                sb.append(scriptDouble(array, array2[i], true));
            }
            if (array2.length > 1) {
                sb.append(s3);
            }
            if (b) {
                sb.append(s3);
            }
            return sb.toString();
        }
        return null;
    }
    
    private final String TRUE(final String[] array, final String s) {
        return array[8] + s + array[9];
    }
    
    public static final String scriptDouble(final String[] array, final double n, final boolean b) {
        String s;
        if (array == PlotterCanvas.script_format) {
            s = Z(n, b);
        }
        else {
            s = I(n, b);
        }
        return s;
    }
    
    static String Z(final double n, final boolean b) {
        final StringBuffer sb = new StringBuffer();
        if (n > 0.0) {
            sb.append(" ");
        }
        final long round = Math.round(n);
        if (Math.abs(round - n) < 1.0E-6) {
            if (round != 0L || b) {
                sb.append("<mn>" + round + "</mn>");
            }
        }
        else if (Math.abs(n) < 10000.0 && Math.abs(n) > 0.001) {
            sb.append("<mn>" + (float)n + "</mn>");
        }
        else {
            final int n2 = (int)Math.floor((float)(Math.log(Math.abs(n)) / Math.log(10.0)));
            sb.append("<mn>" + n / Math.pow(10.0, n2) + "</mn>");
            sb.append("<mo>*</mo><msup><mn>10</mn>");
            sb.append("<mn>" + n2 + "</mn>");
            sb.append("</msup>");
        }
        return sb.toString();
    }
    
    static String I(final double n, final boolean b) {
        final StringBuffer sb = new StringBuffer();
        final int n2 = (n == 0.0) ? 0 : ((int)Math.floor((float)(Math.log(Math.abs(n)) / Math.log(10.0))));
        if (n2 >= 0 && n2 < 10) {
            final long round = Math.round(n);
            if (Math.abs(round - n) < 1.0E-6) {
                if (round != 0L || b) {
                    sb.append(round);
                    return sb.toString();
                }
                return "";
            }
        }
        if (Math.abs(n) < 10000.0 && Math.abs(n) > 0.001) {
            sb.append((float)n);
        }
        else {
            sb.append(n / Math.pow(10.0, n2));
            sb.append("*10^");
            sb.append(n2);
        }
        return sb.toString();
    }
    
    public final void setBox(final String s, final AbstractBox abstractBox) {
        this.boxes.put(s, abstractBox);
    }
    
    public final AbstractBox getBox(final String s) {
        return this.boxes.get(s);
    }
    
    public final void clearBoxes() {
        this.boxes.clear();
    }
    
    public void freeResources() {
        this.mouseReleasedBox = null;
        this.clearCurrent();
        this.clearBoxes();
    }
    
    public final boolean setSelectedPoint(final String s, final int n, final int n2, final int n3, final int n4, final Formula formula) {
        final AbstractBox box = this.getBox(s);
        if (box != null) {
            AbstractBox mouseReleasedBox;
            if (box instanceof TokensBox && box.nfills == 1) {
                mouseReleasedBox = box.fill[0];
            }
            else {
                mouseReleasedBox = box;
            }
            if (mouseReleasedBox instanceof Slider) {
                final int n5 = n3 - box.x;
                final int n6 = n4 - box.y;
                final int n7 = n - box.x;
                final int n8 = n2 - box.y;
                final Slider slider = (Slider)mouseReleasedBox;
                this.mouseReleasedBox = mouseReleasedBox;
                if (!slider.getCanDrag()) {
                    mouseReleasedBox.onClick(n7, n8, formula);
                }
                if (slider.getCanDrag()) {
                    mouseReleasedBox.onDragged(n5, n6, formula);
                }
                this.setSelectedPoint(s, new float[] { (float)((Slider)mouseReleasedBox).getValue() });
                return true;
            }
        }
        return false;
    }
    
    public final String getName() {
        return this.title;
    }
    
    public final void setName(final String title) {
        this.title = title;
    }
    
    public static final String getPlotterName(final InputStream inputStream) {
        String utf = null;
        try {
            final DataInputStream dataInputStream = new DataInputStream(inputStream);
            dataInputStream.readByte();
            final byte byte1 = dataInputStream.readByte();
            dataInputStream.readByte();
            if (byte1 != 51) {
                dataInputStream.readUTF();
            }
            utf = dataInputStream.readUTF();
            dataInputStream.reset();
        }
        catch (IOException ex) {}
        return utf;
    }
    
    public final void repaintNow() {
        Graphics graphics = this.getGraphics();
        final Dimension size = this.getSize();
        if (graphics == null) {
            graphics = IsGraphics2D.getGraphics(size.width, size.height);
        }
        graphics.setClip(0, 0, size.width, size.height);
        this.paint(graphics);
    }
    
    static {
        infoArray = new String[] { "none", "name", "value", "definition" };
        PlotterCanvas.omega_format = new String[] { "(", ")", "{", "}", "[", "]", ",", "=", "", "", "\"", "\"" };
    }
}
