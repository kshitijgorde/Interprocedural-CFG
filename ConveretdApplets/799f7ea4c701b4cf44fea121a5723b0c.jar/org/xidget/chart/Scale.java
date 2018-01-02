// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.chart;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormatSymbols;

public class Scale
{
    private static DecimalFormatSymbols symbols;
    private Format format;
    private List<Tick> ticks;
    private List<Integer> counts;
    private int count;
    private double maxPow;
    private double scaleMin;
    private double scaleMax;
    private double scaleRange;
    private double log;
    private double logq;
    
    public Scale(final double n, final double n2, final int n3, final Format format) {
        this(n, n2, n3, 0.0, format);
    }
    
    public Scale(double n, double n2, final int count, final double log, final Format format) {
        this.format = format;
        this.log = log;
        this.count = count;
        if (n > n2) {
            final double n3 = n;
            n = n2;
            n2 = n3;
        }
        if (log != 0.0) {
            this.logq = Math.log(log);
            if (n != 0.0) {
                n = Math.log(n) / this.logq;
            }
            if (n2 != 0.0) {
                n2 = Math.log(n2) / this.logq;
            }
        }
        this.computeMajorTicks(n, n2, this.ticks = new ArrayList<Tick>());
        (this.counts = new ArrayList<Integer>()).add(this.ticks.size());
        this.subdivide();
        if (log > 0.0) {
            for (final Tick tick : this.ticks) {
                tick.value = Math.pow(log, tick.value);
            }
        }
        final Iterator<Tick> iterator2 = this.ticks.iterator();
        while (iterator2.hasNext()) {
            this.createLabel(iterator2.next());
        }
    }
    
    private int msd(final double n) {
        return (int)Math.round(n / Math.pow(10.0, Math.floor(Math.log10(n))));
    }
    
    public double getExponent() {
        return this.maxPow;
    }
    
    public List<Tick> getTicks() {
        return this.ticks;
    }
    
    public List<Integer> getTickCounts() {
        return this.counts;
    }
    
    public double plot(double n) {
        if (this.log != 0.0 && n != 0.0) {
            n = Math.log(n) / this.logq;
        }
        return (n - this.scaleMin) / this.scaleRange;
    }
    
    public double value(final double n) {
        double pow = this.scaleRange * n + this.scaleMin;
        if (this.log != 0.0) {
            pow = Math.pow(pow, this.log);
        }
        return pow;
    }
    
    public double value(final int n, final int n2) {
        final int n3 = n / n2 * this.ticks.size();
        final Tick tick = this.ticks.get(n3);
        if (n3 == this.ticks.size() - 1) {
            return tick.value;
        }
        final Tick tick2 = this.ticks.get(n3 + 1);
        final int n4 = (int)Math.round(tick.scale * n2);
        final int n5 = (int)Math.round(tick2.scale * n2);
        if (n4 == n5) {
            return tick.value;
        }
        return (tick2.value - tick.value) / (n5 - n4) * (n - n4) + tick.value;
    }
    
    private void computeMajorTicks(final double n, final double n2, final List<Tick> list) {
        this.maxPow = Math.pow(10.0, this.findExponent(n, n2));
        if (n > 0.0) {
            this.scaleMin = roundTowardZero(n / this.maxPow) * this.maxPow;
        }
        else {
            this.scaleMin = roundAwayFromZero(n / this.maxPow) * this.maxPow;
        }
        if (n2 > 0.0) {
            this.scaleMax = roundAwayFromZero(n2 / this.maxPow) * this.maxPow;
        }
        else {
            this.scaleMax = roundTowardZero(n2 / this.maxPow) * this.maxPow;
        }
        if (this.scaleMax < this.scaleMin) {
            final double scaleMin = this.scaleMin;
            this.scaleMin = this.scaleMax;
            this.scaleMax = scaleMin;
        }
        this.scaleRange = this.scaleMax - this.scaleMin;
        for (int majorDivisions = this.getMajorDivisions(this.scaleRange), i = 0; i <= majorDivisions; ++i) {
            final double value = this.scaleMin + this.scaleRange * i / majorDivisions;
            final Tick tick = new Tick();
            tick.depth = 0;
            tick.value = value;
            tick.scale = this.plot(value);
            list.add(tick);
        }
    }
    
    private double findExponent(final double n, final double n2) {
        final double abs = Math.abs(n);
        final double abs2 = Math.abs(n2);
        return Math.floor(Math.log10((abs < abs2) ? abs2 : abs));
    }
    
    private int getMajorDivisions(final double n) {
        final double n2 = n / this.maxPow;
        if (n2 > 10.0) {
            return (int)n2;
        }
        final int msd = this.msd(n);
        switch (msd) {
            case 1: {
                return 10;
            }
            case 2: {
                return 4;
            }
            case 3: {
                return 6;
            }
            default: {
                return msd;
            }
        }
    }
    
    private static double roundTowardZero(final double n) {
        if (n < 0.0) {
            return Math.ceil(n);
        }
        return Math.floor(n);
    }
    
    private static double roundAwayFromZero(final double n) {
        if (n < 0.0) {
            return Math.floor(n);
        }
        return Math.ceil(n);
    }
    
    private void subdivide(final int n) {
        this.counts.add(this.ticks.size() + (this.ticks.size() - 1) * n);
        final double doubleValue = BigDecimal.valueOf(this.ticks.get(1).value).subtract(BigDecimal.valueOf(this.ticks.get(0).value)).divide(BigDecimal.valueOf(n + 1)).doubleValue();
        final int depth = this.ticks.get(1).depth + 1;
        for (int i = 0; i < this.ticks.size() - 1; ++i) {
            final double value = this.ticks.get(i).value;
            for (int j = 1; j <= n; ++j) {
                final Tick tick = new Tick();
                tick.depth = depth;
                tick.value = value + doubleValue * j;
                tick.scale = this.plot(tick.value);
                ++i;
                this.ticks.add(i, tick);
            }
        }
    }
    
    private void subdivide() {
        while (true) {
            final int n = this.count / this.ticks.size();
            final int msd = this.msd(this.ticks.get(1).value - this.ticks.get(0).value);
            if (msd == 1 && n >= 10) {
                this.subdivide(1);
            }
            else if (msd == 5 && n >= 5) {
                this.subdivide(4);
            }
            else if (n >= 10) {
                this.subdivide(9);
            }
            else {
                if (n < 2) {
                    break;
                }
                this.subdivide(1);
            }
        }
    }
    
    private void createLabel(final Tick tick) {
        double normalize = this.normalize(tick.value);
        switch (this.format) {
            case decimal: {
                tick.label = String.format("%f", tick.value);
                tick.label = this.trimZeros(tick.label);
                break;
            }
            case normalized: {
                tick.label = String.format("%f", normalize);
                tick.label = this.trimZeros(tick.label);
                break;
            }
            case scientific: {
                tick.label = String.format("%1.2e", tick.value);
                tick.label = this.trimZeros(tick.label);
                break;
            }
            case engineering: {
                if (tick.value == 0.0) {
                    tick.label = "0";
                    return;
                }
                final int n = (int)Math.floor(Math.log10(tick.value));
                if (n < -2) {
                    final int n2 = -n / 3 - 1;
                    final int n3 = -n % 3;
                    if (n3 == 1) {
                        normalize *= 10.0;
                    }
                    if (n3 == 2) {
                        normalize *= 100.0;
                    }
                    final String trimZeros = this.trimZeros(String.format("%1.2f", normalize));
                    if (n2 < "munpfazy".length()) {
                        tick.label = String.valueOf(trimZeros) + "munpfazy".charAt(n2);
                        break;
                    }
                    tick.label = String.valueOf(normalize) + "10E-" + n2;
                    break;
                }
                else {
                    if (n <= 2) {
                        tick.label = this.trimZeros(String.format("%1.2f", tick.value));
                        break;
                    }
                    final int n4 = n / 3 - 1;
                    final int n5 = n % 3;
                    if (n5 == 1) {
                        normalize *= 10.0;
                    }
                    if (n5 == 2) {
                        normalize *= 100.0;
                    }
                    final String trimZeros2 = this.trimZeros(String.format("%1.2f", normalize));
                    if (n4 < "KMGTPEZY".length()) {
                        tick.label = String.valueOf(trimZeros2) + "KMGTPEZY".charAt(n4);
                        break;
                    }
                    tick.label = String.valueOf(trimZeros2) + "10E" + n4;
                    break;
                }
                break;
            }
        }
    }
    
    private String trimZeros(final String s) {
        if (Scale.symbols == null) {
            Scale.symbols = new DecimalFormatSymbols();
        }
        final int index = s.indexOf(Scale.symbols.getDecimalSeparator());
        for (int i = s.length() - 1; i >= index; --i) {
            if (s.charAt(i) != '0') {
                if (s.charAt(i) != Scale.symbols.getDecimalSeparator()) {
                    ++i;
                }
                return s.substring(0, i);
            }
        }
        return s.substring(0, index);
    }
    
    private double normalize(final double n) {
        return n / Math.pow(10.0, (int)Math.floor(Math.log10(Math.abs(n))));
    }
    
    public static void main(final String[] array) throws Exception {
        for (final Tick tick : new Scale(-919.0, 102.45, 67, 0.0, Format.engineering).ticks) {
            for (int i = 0; i < tick.depth; ++i) {
                System.out.printf("    ", new Object[0]);
            }
            System.out.printf("%f | %f\n", tick.value, tick.scale);
        }
    }
    
    public enum Format
    {
        decimal("decimal", 0), 
        normalized("normalized", 1), 
        scientific("scientific", 2), 
        engineering("engineering", 3);
        
        private Format(final String s, final int n) {
        }
    }
    
    public static class Tick
    {
        public int depth;
        public double value;
        public double scale;
        public String label;
    }
}
