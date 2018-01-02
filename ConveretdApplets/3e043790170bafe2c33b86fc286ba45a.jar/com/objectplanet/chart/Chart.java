// 
// Decompiled by Procyon v0.5.30
// 

package com.objectplanet.chart;

import java.awt.event.MouseEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.util.Enumeration;
import java.awt.event.ItemEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseListener;
import java.awt.Point;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ItemListener;
import java.util.Vector;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Font;
import java.awt.ItemSelectable;
import java.awt.Component;

public abstract class Chart extends Component implements ItemSelectable
{
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int TOP = 2;
    public static final int BOTTOM = 3;
    ChartSample[] samples;
    String chartTitle;
    Font titleFont;
    int sampleDecimalCount;
    boolean chartTitleOn;
    boolean legendOn;
    boolean valueLabelsOn;
    int legendPosition;
    boolean display3dOn;
    boolean hasChanged;
    boolean frameOn;
    Color chartBackground;
    Color chartForeground;
    Color[] sampleColors;
    private static Color[] DEFAULT_SAMPLE_COLORS;
    static final Color DEFAULT_BACKGROUND;
    static final Color DEFAULT_BAR_COLOR;
    static final Color SELECTED_COLOR;
    static final float titleSize = 1.2f;
    int legendWidth;
    int legendHeight;
    Object selectedKey;
    private boolean trackSelectedSample;
    Rectangle[] legendBounds;
    private Vector listeners;
    ChartSample selectedSample;
    
    public boolean isTitleOn() {
        return this.chartTitleOn;
    }
    
    public void setLegendPosition(final int legendPosition) {
        if (legendPosition == 2 || legendPosition == 3 || legendPosition == 0 || legendPosition == 1) {
            this.legendPosition = legendPosition;
            this.hasChanged = true;
            this.repaint(1L);
            return;
        }
        throw new IllegalArgumentException("Should be TOP, BOTTOM, LEFT, or RIGHT");
    }
    
    public int getLegendPosition() {
        return this.legendPosition;
    }
    
    public boolean is3DModeOn() {
        return this.display3dOn;
    }
    
    public boolean isFrameOn() {
        return this.frameOn;
    }
    
    public void clearSelection() {
        for (int i = 0; i < this.samples.length; ++i) {
            if (this.samples[i] != null) {
                this.samples[i].setSelection(false);
            }
        }
        this.hasChanged = true;
        this.repaint(1L);
    }
    
    public static String getVersion() {
        return "1.5";
    }
    
    public void setChartForeground(final Color chartForeground) {
        this.chartForeground = chartForeground;
    }
    
    public Color getChartForeground() {
        return this.chartForeground;
    }
    
    public static void main(final String[] array) {
        System.out.println("EasyCharts " + getVersion());
        System.out.println("Copyright 1998, 1999, ObjectPlanet, Inc.");
    }
    
    public void setSampleColor(final int n, final Color color) {
        if (n < 0) {
            throw new IllegalArgumentException("Negativ index");
        }
        this.sampleColors[n % this.sampleColors.length] = color;
        this.repaint(1L);
    }
    
    public Color getSampleColor(final int n) {
        return this.sampleColors[n % this.sampleColors.length];
    }
    
    public void setLegendOn(final boolean legendOn) {
        this.legendOn = legendOn;
        this.hasChanged = true;
        this.repaint(1L);
    }
    
    public void setSamples(final ChartSample[] array) {
    }
    
    public ChartSample[] getSamples() {
        return this.samples;
    }
    
    public Object[] getSelectedObjects() {
        if (this.selectedSample != null) {
            return new Object[] { this.selectedSample };
        }
        return null;
    }
    
    public double getFloatSampleValue(final int n) {
        if (this.samples != null && this.samples[n] != null) {
            return this.samples[n].getFloatValue();
        }
        return 0.0;
    }
    
    public long getMinValue() {
        return (long)this.getFloatMinValue();
    }
    
    public String toString() {
        return this.getTitle();
    }
    
    public double[] getFloatSampleValues() {
        final double[] array = new double[this.samples.length];
        for (int i = 0; i < array.length; ++i) {
            if (this.samples != null && this.samples[i] != null) {
                array[i] = this.samples[i].getFloatValue();
            }
            else {
                array[i] = 0.0;
            }
        }
        return array;
    }
    
    public void setValueLabelsOn(final boolean valueLabelsOn) {
        this.valueLabelsOn = valueLabelsOn;
        this.hasChanged = true;
        this.repaint(1L);
    }
    
    void initLegendBounds() {
        this.legendBounds = new Rectangle[this.samples.length];
    }
    
    public void setTitleFont(final Font titleFont) {
        if (titleFont != null) {
            this.titleFont = titleFont;
            return;
        }
        throw new IllegalArgumentException("Font is NULL");
    }
    
    public Font getTitleFont() {
        return this.titleFont;
    }
    
    public void removeItemListener(final ItemListener itemListener) {
        if (itemListener != null && this.listeners != null) {
            this.listeners.removeElement(itemListener);
        }
    }
    
    protected abstract void render(final Graphics p0);
    
    public boolean isValueLabelsOn() {
        return this.valueLabelsOn;
    }
    
    public void setTrackSelectionOn(final boolean trackSelectedSample) {
        this.trackSelectedSample = trackSelectedSample;
    }
    
    protected Rectangle getGraphBounds(final String[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Labels is NULL");
        }
        final Dimension size = this.getSize();
        final Rectangle rectangle = new Rectangle(10, 10, size.width - 20, size.height - 20);
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        final int height = fontMetrics.getHeight();
        final int length = array.length;
        if (this.chartTitleOn) {
            final int n = (int)(height * 1.2f);
            final Rectangle rectangle2 = rectangle;
            rectangle2.y += n;
            final Rectangle rectangle3 = rectangle;
            rectangle3.height -= n;
        }
        if (this.legendOn) {
            this.legendWidth = 0;
            for (int i = 0; i < length; ++i) {
                if (array[i] != null) {
                    final int stringWidth = fontMetrics.stringWidth(array[i]);
                    if (stringWidth > this.legendWidth) {
                        this.legendWidth = stringWidth;
                    }
                }
            }
            this.legendWidth += 14;
            this.legendHeight = height * length;
            switch (this.legendPosition) {
                default: {
                    final Rectangle rectangle4 = rectangle;
                    rectangle4.width -= this.legendWidth;
                    break;
                }
                case 0: {
                    final Rectangle rectangle5 = rectangle;
                    rectangle5.x += this.legendWidth;
                    final Rectangle rectangle6 = rectangle;
                    rectangle6.width -= this.legendWidth;
                    break;
                }
                case 2: {
                    final Rectangle rectangle7 = rectangle;
                    rectangle7.y += 2 * height;
                    final Rectangle rectangle8 = rectangle;
                    rectangle8.height -= 2 * height;
                    this.legendWidth = size.width - 20;
                    break;
                }
                case 3: {
                    final Rectangle rectangle9 = rectangle;
                    rectangle9.height -= height + 10;
                    this.legendWidth = size.width - 20;
                    break;
                }
            }
        }
        return rectangle;
    }
    
    public void setChartBackground(final Color chartBackground) {
        this.chartBackground = chartBackground;
    }
    
    public Color getChartBackground() {
        return this.chartBackground;
    }
    
    synchronized void paintLegend(final Graphics graphics, final Rectangle rectangle, final ChartSample[] array) {
        final int n = 6;
        int n2 = 0;
        int n3 = 0;
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        final int height = fontMetrics.getHeight();
        int n4 = 0;
        int n5 = 0;
        switch (this.legendPosition) {
            default: {
                n2 = this.getSize().width - this.legendWidth - 2;
                n3 = n2 + n + 4;
                n4 = rectangle.y + rectangle.height / 2 - this.legendHeight / 2;
                n5 = n4 + n + 1;
                break;
            }
            case 0: {
                n2 = 8;
                n3 = n2 + n + 4;
                n4 = rectangle.y + rectangle.height / 2 - this.legendHeight / 2;
                n5 = n4 + n + 1;
                break;
            }
            case 2: {
                if (this.chartTitleOn) {
                    final FontMetrics fontMetrics2 = graphics.getFontMetrics();
                    n4 = fontMetrics2.getHeight() + fontMetrics2.getDescent() + 8;
                }
                else {
                    n4 = 8;
                }
                n5 = n4 + n + height;
                break;
            }
            case 3: {
                n4 = this.getSize().height - fontMetrics.getHeight() - n - 5;
                n5 = n4 + n + height;
                break;
            }
        }
        for (int i = 0; i < array.length; ++i) {
            String label;
            if (array[i] != null && array[i].label != null) {
                label = array[i].label;
            }
            else {
                label = " ";
            }
            if (this.legendPosition == 2 || this.legendPosition == 3) {
                n2 = this.legendWidth * (i / array.length) + (this.legendWidth / array.length / 2 + n);
                n3 = n2 - fontMetrics.stringWidth(label) / 2 + n / 2;
            }
            graphics.setColor(this.getSampleColor(i));
            graphics.fillRect(n2, n4, n, n);
            graphics.setColor(Color.black);
            graphics.drawRect(n2, n4, n, n);
            if (array[i] != null) {
                if (array[i].labelColor != null) {
                    graphics.setColor(array[i].labelColor);
                }
                else {
                    graphics.setColor(this.getForeground());
                }
                graphics.drawString(label, n3, n5);
            }
            try {
                if (this.legendBounds[i] == null) {
                    this.legendBounds[i] = new Rectangle();
                }
                int stringWidth;
                try {
                    stringWidth = fontMetrics.stringWidth(label);
                }
                catch (NullPointerException ex) {
                    stringWidth = 5;
                }
                final Rectangle rectangle2 = this.legendBounds[i];
                if (this.legendPosition == 1 || this.legendPosition == 0) {
                    rectangle2.setBounds(n2 - 3, n5 - height + 2, stringWidth + n * 2 + 3, height + 1);
                }
                else {
                    rectangle2.setBounds(n2 + n / 2 - stringWidth / 2 - 3, n4 - 3, stringWidth + 6, n * 2 + height);
                }
                if (array[i] != null && array[i].isSelected()) {
                    graphics.setColor(this.getForeground());
                    graphics.drawRect(rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height);
                }
            }
            catch (IndexOutOfBoundsException ex2) {}
            if (this.legendPosition == 1 || this.legendPosition == 0) {
                n4 += height + 1;
                n5 += height + 1;
            }
        }
    }
    
    public synchronized ChartSample getSample(final int n) {
        return this.samples[n];
    }
    
    public synchronized ChartSample getSample(final Object o) {
        for (int i = 0; i < this.samples.length; ++i) {
            if (this.samples[i].key.equals(o)) {
                return this.samples[i];
            }
        }
        return null;
    }
    
    public synchronized void setSample(final int n, final ChartSample chartSample) {
    }
    
    private void trackSelection(final ChartSample selectedSample, final Object o) {
        if (this.trackSelectedSample && this.selectedKey != null) {
            if (this.selectedKey.equals(o)) {
                if (this.selectedSample != null) {
                    this.selectedSample.setSelection(false);
                }
                selectedSample.setSelection(true);
                this.selectedSample = selectedSample;
            }
            else {
                selectedSample.setSelection(false);
            }
        }
    }
    
    public synchronized void setSample(final int n, final double n2, final String s, final Object o) {
        if (this.samples[n] == null) {
            this.samples[n] = new ChartSample(n);
        }
        this.samples[n].set(n2, s, o);
        this.trackSelection(this.samples[n], o);
        this.hasChanged = true;
        this.repaint(1L);
    }
    
    public boolean isLegendOn() {
        return this.legendOn;
    }
    
    public long getMaxValue() {
        return (long)this.getFloatMaxValue();
    }
    
    public synchronized void setSampleValue(final int n, final double value) {
        if (this.samples[n] == null) {
            this.samples[n] = new ChartSample(n);
        }
        this.samples[n].setValue(value);
        this.hasChanged = true;
        this.repaint(1L);
    }
    
    public long getSampleValue(final int n) {
        if (this.samples != null && this.samples[n] != null) {
            return this.samples[n].getValue();
        }
        return 0L;
    }
    
    public double getFloatMinValue() {
        double min = 9.223372036854776E18;
        boolean b = false;
        for (int i = 0; i < this.samples.length; ++i) {
            if (this.samples[i] != null && this.samples[i].value != null) {
                min = Math.min(min, this.samples[i].getFloatValue());
                b = true;
            }
        }
        if (b) {
            return min;
        }
        return 0.0;
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void setTitle(final String chartTitle) {
        this.chartTitle = chartTitle;
        this.repaint(1L);
    }
    
    public String getTitle() {
        return this.chartTitle;
    }
    
    void paintFrame(final Graphics graphics, final Dimension dimension) {
        if (this.frameOn) {
            graphics.setColor(this.getBackground());
            graphics.draw3DRect(0, 0, dimension.width - 1, dimension.height - 1, false);
        }
    }
    
    public void addItemListener(final ItemListener itemListener) {
        if (itemListener != null) {
            if (this.listeners == null) {
                this.listeners = new Vector();
            }
            this.listeners.addElement(itemListener);
        }
    }
    
    public synchronized void setSampleValues(final long[] array) {
        if (array == null) {
            return;
        }
        final double[] sampleValues = new double[array.length];
        for (int i = 0; i < sampleValues.length; ++i) {
            sampleValues[i] = array[i];
        }
        this.setSampleValues(sampleValues);
    }
    
    public synchronized void setSampleValues(final double[] array) {
        if (array == null) {
            return;
        }
        final int min = Math.min(array.length, this.samples.length);
        for (int i = 0; i < min; ++i) {
            if (this.samples[i] == null) {
                this.samples[i] = new ChartSample(i);
            }
            this.samples[i].setValue(array[i]);
        }
        for (int j = min; j < this.samples.length; ++j) {
            if (this.samples[j] != null) {
                this.samples[j].value = null;
            }
        }
        this.initLegendBounds();
        this.hasChanged = true;
        this.repaint(1L);
    }
    
    public long[] getSampleValues() {
        final long[] array = new long[this.samples.length];
        for (int i = 0; i < array.length; ++i) {
            if (this.samples != null && this.samples[i] != null) {
                array[i] = this.samples[i].getValue();
            }
            else {
                array[i] = 0L;
            }
        }
        return array;
    }
    
    public synchronized void setSampleLabels(final String[] array) {
        if (array == null) {
            return;
        }
        for (int min = Math.min(array.length, this.samples.length), i = 0; i < min; ++i) {
            if (this.samples[i] == null) {
                this.samples[i] = new ChartSample(i);
            }
            this.samples[i].label = array[i];
        }
        this.hasChanged = true;
        this.repaint(1L);
    }
    
    public String[] getSampleLabels() {
        final String[] array = new String[this.getSampleCount()];
        for (int i = 0; i < array.length; ++i) {
            if (this.samples[i] != null && this.samples[i].label != null) {
                array[i] = this.samples[i].label;
            }
            else {
                array[i] = "";
            }
        }
        return array;
    }
    
    void paintTitle(final Graphics graphics, final Dimension dimension) {
        if (this.chartTitle != null) {
            final Font font = graphics.getFont();
            graphics.setFont(this.titleFont);
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            graphics.setColor(this.getForeground());
            graphics.drawString(this.chartTitle, dimension.width / 2 - fontMetrics.stringWidth(this.chartTitle) / 2, fontMetrics.getHeight() + 2);
            graphics.setFont(font);
        }
    }
    
    public void setTitleOn(final boolean chartTitleOn) {
        this.chartTitleOn = chartTitleOn;
        this.hasChanged = true;
        this.repaint(1L);
    }
    
    public void set3DModeOn(final boolean display3dOn) {
        this.display3dOn = display3dOn;
        this.hasChanged = true;
        this.repaint(1L);
    }
    
    public void setFrameOn(final boolean frameOn) {
        this.frameOn = frameOn;
        this.repaint(1L);
    }
    
    protected ChartSample checkSelection(final Point point) {
        ChartSample chartSample = null;
        for (int i = 0; i < this.legendBounds.length; ++i) {
            if (this.legendBounds[i] != null && this.legendBounds[i].contains(point)) {
                chartSample = this.samples[i];
                break;
            }
        }
        return chartSample;
    }
    
    public void setSampleColors(final Color[] sampleColors) {
        if (sampleColors != null) {
            this.sampleColors = sampleColors;
        }
        else {
            this.sampleColors = Chart.DEFAULT_SAMPLE_COLORS.clone();
        }
        this.repaint(1L);
    }
    
    public Chart(final int sampleCount) {
        this.setForeground(Color.black);
        this.setBackground(new Color(231, 221, 231));
        this.setChartBackground(Color.white);
        this.setChartForeground(Color.black);
        this.setSampleCount(sampleCount);
        this.setTitle("");
        this.setTitleFont(new Font("Dialog", 1, 14));
        this.setLegendPosition(1);
        this.setSampleColors(null);
        this.setFont(new Font("Dialog", 0, 10));
        final EventHandler eventHandler = new EventHandler();
        this.addMouseListener(eventHandler);
        this.addComponentListener(eventHandler);
    }
    
    public synchronized void setSampleCount(final int n) {
        if (n == this.getSampleCount()) {
            return;
        }
        if (this.samples == null) {
            this.samples = new ChartSample[n];
            for (int i = 0; i < n; ++i) {
                this.samples[i] = new ChartSample(i);
            }
        }
        else if (n < this.samples.length) {
            final ChartSample[] samples = new ChartSample[n];
            System.arraycopy(this.samples, 0, samples, 0, n);
            this.samples = samples;
        }
        else {
            final ChartSample[] samples2 = new ChartSample[n];
            System.arraycopy(this.samples, 0, samples2, 0, this.samples.length);
            for (int j = this.samples.length; j < n; ++j) {
                samples2[j] = new ChartSample(j);
            }
            this.samples = samples2;
        }
        this.initLegendBounds();
        this.hasChanged = true;
        this.repaint();
    }
    
    public synchronized int getSampleCount() {
        if (this.samples != null) {
            return this.samples.length;
        }
        return 0;
    }
    
    public void setSampleDecimalCount(final int sampleDecimalCount) {
        this.sampleDecimalCount = sampleDecimalCount;
        this.hasChanged = true;
        this.repaint();
    }
    
    public int getSampleDecimalCount() {
        return this.sampleDecimalCount;
    }
    
    public synchronized void setSampleLabelColor(final int n, final Color labelColor) {
        if (this.samples[n] == null) {
            this.samples[n] = new ChartSample(n);
        }
        this.samples[n].setLabelColor(labelColor);
        this.repaint(1L);
    }
    
    public Color getSampleLabelColor(final int n) {
        Color color = Color.black;
        if (this.samples[n] != null && this.samples[n].labelColor != null) {
            color = this.samples[n].labelColor;
        }
        return color;
    }
    
    public Color[] getSampleColors() {
        return this.sampleColors;
    }
    
    public final void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, size.width, size.height);
        graphics.setColor(this.getForeground());
        this.render(graphics);
    }
    
    public synchronized void setSampleLabel(final int n, final String label) {
        if (this.samples[n] == null) {
            this.samples[n] = new ChartSample(n);
        }
        this.samples[n].label = label;
        this.hasChanged = true;
        this.repaint(1L);
    }
    
    public synchronized String getSampleLabel(final int n) {
        if (this.samples[n] != null) {
            return this.samples[n].label;
        }
        return null;
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(300, 200);
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(100, 70);
    }
    
    void notifyListeners(final int n, final Object o) {
        if (this.listeners != null) {
            final ItemEvent itemEvent = new ItemEvent(this, 701, o, n);
            final Enumeration<ItemListener> elements = (Enumeration<ItemListener>)this.listeners.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().itemStateChanged(itemEvent);
            }
        }
    }
    
    public double getFloatMaxValue() {
        double max = -9.223372036854776E18;
        boolean b = false;
        for (int i = 0; i < this.samples.length; ++i) {
            if (this.samples[i] != null && this.samples[i].value != null) {
                max = Math.max(max, this.samples[i].getFloatValue());
                b = true;
            }
        }
        if (b) {
            return max;
        }
        return 0.0;
    }
    
    static {
        Chart.DEFAULT_SAMPLE_COLORS = new Color[] { new Color(132, 130, 255), new Color(132, 32, 99), new Color(255, 255, 198), new Color(165, 227, 231), new Color(99, 0, 132), new Color(255, 130, 132), new Color(0, 130, 198), new Color(198, 195, 255), new Color(0, 0, 132), new Color(255, 0, 255), new Color(255, 255, 0), new Color(0, 255, 255), new Color(132, 0, 132), new Color(132, 0, 0), new Color(0, 130, 132), new Color(0, 0, 255), new Color(0, 207, 255), new Color(107, 255, 255), new Color(206, 255, 206), new Color(255, 255, 156) };
        DEFAULT_BACKGROUND = new Color(231, 221, 231);
        DEFAULT_BAR_COLOR = new Color(132, 130, 255);
        SELECTED_COLOR = Color.white;
    }
    
    class EventHandler extends MouseAdapter implements ComponentListener
    {
        public void componentShown(final ComponentEvent componentEvent) {
        }
        
        public void componentResized(final ComponentEvent componentEvent) {
            Chart.this.hasChanged = true;
            Chart.this.repaint(1L);
        }
        
        public void componentHidden(final ComponentEvent componentEvent) {
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            final ChartSample checkSelection = Chart.this.checkSelection(new Point(mouseEvent.getX(), mouseEvent.getY()));
            if (checkSelection == null) {
                Chart.this.selectedKey = null;
                if (Chart.this.selectedSample != null) {
                    Chart.this.selectedSample.setSelection(false);
                    Chart.this.notifyListeners(2, Chart.this.selectedSample);
                    Chart.this.selectedSample = null;
                }
            }
            else if (checkSelection != null && checkSelection != Chart.this.selectedSample) {
                Chart.this.selectedKey = checkSelection.getKey();
                if (Chart.this.selectedSample != null) {
                    Chart.this.selectedSample.setSelection(false);
                    Chart.this.notifyListeners(2, Chart.this.selectedSample);
                }
                checkSelection.setSelection(true);
                Chart.this.selectedSample = checkSelection;
            }
            if (Chart.this.selectedSample != null) {
                Chart.this.notifyListeners(1, Chart.this.selectedSample);
            }
            Chart.this.hasChanged = true;
            Chart.this.repaint(1L);
        }
        
        public void componentMoved(final ComponentEvent componentEvent) {
        }
    }
}
