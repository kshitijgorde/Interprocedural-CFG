// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.eck.umb.palette;

import java.awt.Color;
import java.util.Iterator;
import java.util.Collection;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.util.ArrayList;

public class Palette implements Cloneable
{
    public static final int COLOR_TYPE_RGB = 0;
    public static final int COLOR_TYPE_HSB = 1;
    private int colorType;
    private boolean mirrorOutOfRangeComponents;
    private ArrayList<Double> divisionPoints;
    private ArrayList<float[]> divisionPointColors;
    private final ChangeEvent changeEvent;
    private ArrayList<ChangeListener> changeListeners;
    
    public Palette() {
        this(1);
    }
    
    public Palette(final int colorType) {
        this.changeEvent = new ChangeEvent(this);
        this.colorType = colorType;
        this.mirrorOutOfRangeComponents = true;
        this.divisionPoints = new ArrayList<Double>();
        this.divisionPointColors = new ArrayList<float[]>();
        this.divisionPoints.add(0.0);
        this.divisionPoints.add(1.0);
        if (colorType == 1) {
            this.divisionPointColors.add(new float[] { 0.0f, 1.0f, 1.0f });
            this.divisionPointColors.add(new float[] { 1.0f, 1.0f, 1.0f });
        }
        else {
            if (colorType != 0) {
                throw new IllegalArgumentException("Palette color type must be TYPE_COLOR_RGB or TYPE_COLOR_HSB");
            }
            this.divisionPointColors.add(new float[] { 1.0f, 1.0f, 1.0f });
            this.divisionPointColors.add(new float[] { 0.0f, 0.0f, 0.0f });
        }
    }
    
    Palette(final int colorType, final boolean mirrorOutOfRangeComponents, final ArrayList<Double> divisionPoints, final ArrayList<float[]> divisionPointColors) {
        this.changeEvent = new ChangeEvent(this);
        this.colorType = colorType;
        this.mirrorOutOfRangeComponents = mirrorOutOfRangeComponents;
        this.divisionPoints = divisionPoints;
        this.divisionPointColors = divisionPointColors;
    }
    
    public static Palette makeDefaultPalette(final String s) {
        Palette palette;
        if (s.equals("Spectrum")) {
            palette = new Palette();
        }
        else if (s.equals("PaleSpectrum")) {
            palette = new Palette();
            palette.setDivisionPointColorComponents(0, 0.0f, 0.5f, 1.0f);
            palette.setDivisionPointColorComponents(1, 1.0f, 0.5f, 1.0f);
        }
        else if (s.equals("Grayscale")) {
            palette = new Palette(0);
        }
        else if (s.equals("CyclicGrayscale")) {
            palette = new Palette(0);
            palette.split(0.5);
            palette.setDivisionPointColorComponents(1, 0.0f, 0.0f, 0.0f);
            palette.setDivisionPointColorComponents(2, 1.0f, 1.0f, 1.0f);
        }
        else if (s.equals("CyclicRedCyan")) {
            palette = new Palette(0);
            palette.split(0.5);
            palette.setDivisionPointColorComponents(0, 1.0f, 0.0f, 0.0f);
            palette.setDivisionPointColorComponents(1, 0.0f, 1.0f, 1.0f);
            palette.setDivisionPointColorComponents(2, 1.0f, 0.0f, 0.0f);
        }
        else if (s.equals("EarthSky")) {
            palette = new Palette(0);
            palette.split(0.15);
            palette.split(0.33);
            palette.split(0.67);
            palette.split(0.85);
            palette.setDivisionPointColorComponents(0, 1.0f, 1.0f, 1.0f);
            palette.setDivisionPointColorComponents(1, 1.0f, 0.8f, 0.0f);
            palette.setDivisionPointColorComponents(2, 0.53f, 0.12f, 0.075f);
            palette.setDivisionPointColorComponents(3, 0.0f, 0.0f, 0.6f);
            palette.setDivisionPointColorComponents(4, 0.0f, 0.4f, 1.0f);
            palette.setDivisionPointColorComponents(5, 1.0f, 1.0f, 1.0f);
        }
        else if (s.equals("HotCold")) {
            palette = new Palette(0);
            palette.split(0.16);
            palette.split(0.5);
            palette.split(0.84);
            palette.setDivisionPointColorComponents(0, 1.0f, 1.0f, 1.0f);
            palette.setDivisionPointColorComponents(1, 0.0f, 0.4f, 1.0f);
            palette.setDivisionPointColorComponents(2, 0.2f, 0.2f, 0.2f);
            palette.setDivisionPointColorComponents(3, 1.0f, 0.0f, 0.8f);
            palette.setDivisionPointColorComponents(4, 1.0f, 1.0f, 1.0f);
        }
        else {
            if (!s.equals("Fire")) {
                throw new IllegalArgumentException("Unknown palette: " + s);
            }
            palette = new Palette(0);
            palette.split(0.17);
            palette.split(0.83);
            palette.setDivisionPointColorComponents(0, 0.0f, 0.0f, 0.0f);
            palette.setDivisionPointColorComponents(1, 1.0f, 0.0f, 0.0f);
            palette.setDivisionPointColorComponents(2, 1.0f, 1.0f, 0.0f);
            palette.setDivisionPointColorComponents(3, 1.0f, 1.0f, 1.0f);
        }
        return palette;
    }
    
    public boolean equals(final Object o) {
        if (o == null || !(o instanceof Palette)) {
            return false;
        }
        final Palette palette = (Palette)o;
        if (palette.colorType != this.colorType) {
            return false;
        }
        if (palette.mirrorOutOfRangeComponents != this.mirrorOutOfRangeComponents) {
            return false;
        }
        if (palette.divisionPoints.size() != this.divisionPoints.size()) {
            return false;
        }
        for (int i = 0; i < this.divisionPoints.size(); ++i) {
            if (palette.divisionPoints.get(i) != this.divisionPoints.get(i)) {
                return false;
            }
            final float[] array = palette.divisionPointColors.get(i);
            final float[] array2 = this.divisionPointColors.get(i);
            if (array[0] != array2[0] || array[1] != array2[1] || array[2] != array2[2]) {
                return false;
            }
        }
        return true;
    }
    
    public Palette clone() {
        final Palette palette = new Palette(this.colorType);
        palette.mirrorOutOfRangeComponents = this.mirrorOutOfRangeComponents;
        (palette.divisionPoints = new ArrayList<Double>()).addAll(this.divisionPoints);
        palette.divisionPointColors = new ArrayList<float[]>();
        final Iterator<float[]> iterator = this.divisionPointColors.iterator();
        while (iterator.hasNext()) {
            palette.divisionPointColors.add(iterator.next().clone());
        }
        palette.changed();
        return palette;
    }
    
    public void copyFrom(final Palette palette) {
        this.colorType = palette.colorType;
        this.mirrorOutOfRangeComponents = palette.mirrorOutOfRangeComponents;
        (this.divisionPoints = new ArrayList<Double>()).addAll(palette.divisionPoints);
        this.divisionPointColors = new ArrayList<float[]>();
        final Iterator<float[]> iterator = palette.divisionPointColors.iterator();
        while (iterator.hasNext()) {
            this.divisionPointColors.add(iterator.next().clone());
        }
        this.changed();
    }
    
    public void join(final int n) {
        if (n <= 0 || n >= this.divisionPoints.size() - 1) {
            throw new IllegalArgumentException("Division point index out of range: " + n);
        }
        this.divisionPoints.remove(n);
        this.divisionPointColors.remove(n);
        this.changed();
    }
    
    public int split(final double n) {
        if (n <= 0.0 || n >= 1.0 || Double.isNaN(n)) {
            throw new IllegalArgumentException("Division point out of range: " + n);
        }
        int n2;
        for (n2 = 0; n > this.divisionPoints.get(n2); ++n2) {}
        if (Math.abs(n - this.divisionPoints.get(n2)) < 1.0E-15) {
            return -1;
        }
        final float n3 = (float)((n - this.divisionPoints.get(n2 - 1)) / (this.divisionPoints.get(n2) - this.divisionPoints.get(n2 - 1)));
        final float[] array = this.divisionPointColors.get(n2 - 1);
        final float[] array2 = this.divisionPointColors.get(n2);
        final float[] array3 = { array[0] + n3 * (array2[0] - array[0]), array[1] + n3 * (array2[1] - array[1]), array[2] + n3 * (array2[2] - array[2]) };
        this.divisionPoints.add(n2, n);
        this.divisionPointColors.add(n2, array3);
        this.changed();
        return n2;
    }
    
    public Color getColor(final double n) {
        if (n < 0.0 || n > 1.0) {
            throw new IllegalArgumentException("Position " + n + " is out of range.");
        }
        int n2;
        for (n2 = 1; n > this.divisionPoints.get(n2); ++n2) {}
        final float n3 = (float)((n - this.divisionPoints.get(n2 - 1)) / (this.divisionPoints.get(n2) - this.divisionPoints.get(n2 - 1)));
        final float[] array = this.divisionPointColors.get(n2 - 1);
        final float[] array2 = this.divisionPointColors.get(n2);
        final float clamp1 = this.clamp1(array[0] + n3 * (array2[0] - array[0]));
        final float clamp2 = this.clamp2(array[1] + n3 * (array2[1] - array[1]));
        final float clamp3 = this.clamp2(array[2] + n3 * (array2[2] - array[2]));
        Color hsbColor;
        if (this.colorType == 1) {
            hsbColor = Color.getHSBColor(clamp1, clamp2, clamp3);
        }
        else {
            hsbColor = new Color(clamp1, clamp2, clamp3);
        }
        return hsbColor;
    }
    
    public int[] makeRGBs(final int n, final int n2) {
        final int[] array = new int[n];
        array[n2 % n] = this.getDivisionPointColor(0).getRGB();
        int i = 1;
        final double n3 = 1.0 / (n - 1);
        int n4 = 1;
        while (i < n - 1) {
            double n5;
            for (n5 = n3 * i; n5 > this.divisionPoints.get(n4); ++n4) {}
            final float n6 = (float)((n5 - this.divisionPoints.get(n4 - 1)) / (this.divisionPoints.get(n4) - this.divisionPoints.get(n4 - 1)));
            final float[] array2 = this.divisionPointColors.get(n4 - 1);
            final float[] array3 = this.divisionPointColors.get(n4);
            final float clamp1 = this.clamp1(array2[0] + n6 * (array3[0] - array2[0]));
            final float clamp2 = this.clamp2(array2[1] + n6 * (array3[1] - array2[1]));
            final float clamp3 = this.clamp2(array2[2] + n6 * (array3[2] - array2[2]));
            Color hsbColor;
            if (this.colorType == 1) {
                hsbColor = Color.getHSBColor(clamp1, clamp2, clamp3);
            }
            else {
                hsbColor = new Color(clamp1, clamp2, clamp3);
            }
            array[(i + n2) % n] = hsbColor.getRGB();
            ++i;
        }
        array[(n2 + n - 1) % n] = this.getDivisionPointColor(this.divisionPoints.size() - 1).getRGB();
        return array;
    }
    
    public int getDivisionPointCount() {
        return this.divisionPoints.size();
    }
    
    public double getDivisionPoint(final int n) {
        return this.divisionPoints.get(n);
    }
    
    public void setDivisionPoint(final int n, final double n2) {
        if (n <= 0 || n >= this.divisionPoints.size() - 1) {
            throw new IllegalArgumentException("Index out of legal range");
        }
        if (n2 <= this.divisionPoints.get(n - 1) || n2 >= this.divisionPoints.get(n + 1)) {
            throw new IllegalArgumentException("Division point position outside of legal range.");
        }
        if (n2 != this.divisionPoints.get(n)) {
            this.divisionPoints.set(n, n2);
            this.changed();
        }
    }
    
    public Color getDivisionPointColor(final int n) {
        final float[] array = this.divisionPointColors.get(n);
        final float clamp1 = this.clamp1(array[0]);
        final float clamp2 = this.clamp2(array[1]);
        final float clamp3 = this.clamp2(array[2]);
        if (this.colorType == 0) {
            return new Color(clamp1, clamp2, clamp3);
        }
        return Color.getHSBColor(clamp1, clamp2, clamp3);
    }
    
    public float[] getDivisionPointColorComponents(final int n) {
        return this.divisionPointColors.get(n).clone();
    }
    
    public void setDivisionPointColorComponents(final int n, final float n2, final float n3, final float n4) {
        final float[] array = this.divisionPointColors.get(n);
        if (n2 == array[0] && n3 == array[1] && n4 == array[2]) {
            return;
        }
        array[0] = n2;
        array[1] = n3;
        array[2] = n4;
        this.changed();
    }
    
    public int getColorType() {
        return this.colorType;
    }
    
    public boolean getMirrorOutOfRangeComponents() {
        return this.mirrorOutOfRangeComponents;
    }
    
    public void setMirrorOutOfRangeComponents(final boolean mirrorOutOfRangeComponents) {
        if (this.mirrorOutOfRangeComponents == mirrorOutOfRangeComponents) {
            return;
        }
        this.mirrorOutOfRangeComponents = mirrorOutOfRangeComponents;
        this.changed();
    }
    
    public void addChangeListener(final ChangeListener changeListener) {
        if (changeListener == null) {
            return;
        }
        if (this.changeListeners == null) {
            this.changeListeners = new ArrayList<ChangeListener>();
        }
        if (!this.changeListeners.contains(changeListener)) {
            this.changeListeners.add(changeListener);
        }
    }
    
    public void removeChangeListener(final ChangeListener changeListener) {
        if (this.changeListeners == null) {
            return;
        }
        this.changeListeners.remove(changeListener);
        if (this.changeListeners.size() == 0) {
            this.changeListeners = null;
        }
    }
    
    private float clamp1(final float n) {
        if (this.colorType == 1 || !this.mirrorOutOfRangeComponents) {
            return n - (float)Math.floor(n);
        }
        return this.clamp2(n);
    }
    
    private float clamp2(float n) {
        if (!this.mirrorOutOfRangeComponents) {
            return n - (float)Math.floor(n);
        }
        n = 2.0f * (n / 2.0f - (float)Math.floor(n / 2.0f));
        if (n > 1.0f) {
            n = 2.0f - n;
        }
        return n;
    }
    
    private void changed() {
        if (this.changeListeners != null) {
            final Iterator<ChangeListener> iterator = this.changeListeners.iterator();
            while (iterator.hasNext()) {
                iterator.next().stateChanged(this.changeEvent);
            }
        }
    }
}
