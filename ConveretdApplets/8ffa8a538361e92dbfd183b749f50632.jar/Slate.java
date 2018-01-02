import java.awt.Event;
import java.awt.image.ImageObserver;
import java.util.StringTokenizer;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class Slate extends Canvas
{
    int eCount;
    Element[] element;
    boolean[] preexists;
    int picki;
    PointElement pick;
    PlaneElement screen;
    static String[] elementClassName;
    static String[][] constructionName;
    public static String[][][][] constructionDataType;
    Image offscreen;
    Dimension offscreensize;
    Graphics offgraphics;
    
    Slate(int n) {
        this.picki = -1;
        this.pick = new PointElement();
        if (n < 15) {
            n = 15;
        }
        this.element = new Element[n];
        this.preexists = new boolean[n];
        this.element[0] = new FixedPoint(0.0, 0.0, 0.0);
        this.element[0].name = "screenorigin";
        this.element[1] = new FixedPoint(1.0, 0.0, 0.0);
        this.element[0].name = "screenx";
        this.element[2] = new FixedPoint(0.0, 1.0, 0.0);
        this.element[0].name = "screeny";
        this.screen = new PlaneElement((PointElement)this.element[0], (PointElement)this.element[1], (PointElement)this.element[2]);
        this.screen.name = "screen";
        this.screen.isScreen = true;
        this.element[3] = this.screen;
        this.eCount = 4;
    }
    
    void extendArrays() {
        final int length = this.element.length;
        final Element[] element = new Element[2 * length];
        final boolean[] preexists = new boolean[2 * length];
        for (int i = 0; i < length; ++i) {
            element[i] = this.element[i];
            preexists[i] = this.preexists[i];
        }
        this.element = element;
        this.preexists = preexists;
    }
    
    Element lookupElement(final String s) {
        for (int i = 0; i < this.eCount; ++i) {
            if (s.equals(this.element[i].name)) {
                return this.element[i];
            }
        }
        return null;
    }
    
    static int lookupElementClass(final String s) {
        for (int i = 0; i < Slate.elementClassName.length; ++i) {
            if (Slate.elementClassName[i].equals(s)) {
                return i;
            }
        }
        return -1;
    }
    
    public static int lookupConstructionMethod(final int n, final String s) {
        for (int i = 0; i < Slate.constructionName[n].length; ++i) {
            if (Slate.constructionName[n][i].equals(s)) {
                return i;
            }
        }
        return -1;
    }
    
    int selectDataChoice(final String s, final String[][] array, final PointElement[] array2, final Element[] array3, final int[] array4, final StringBuffer sb) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            try {
                array4[n3] = Integer.parseInt(nextToken);
                ++n3;
            }
            catch (NumberFormatException ex) {
                final Element lookupElement = this.lookupElement(nextToken);
                if (lookupElement == null) {
                    sb.append("Data element " + nextToken + " not found. ");
                    return -1;
                }
                if (lookupElement.inClass("PointElement")) {
                    array2[n++] = (PointElement)lookupElement;
                }
                else if (lookupElement.inClass("LineElement")) {
                    array2[n++] = ((LineElement)lookupElement).A;
                    array2[n++] = ((LineElement)lookupElement).B;
                }
                else {
                    array3[n2++] = lookupElement;
                }
            }
        }
        int i;
        for (i = 0; i < array.length; ++i) {
            if (array[i].length == n + n3 + n2) {
                int n4 = 0;
                int n5 = 0;
                int n6 = 0;
                int j;
                for (j = 0; j < array[i].length; ++j) {
                    if (array[i][j].equals("Integer")) {
                        if (n6 >= n3) {
                            break;
                        }
                        ++n6;
                    }
                    else if (array[i][j].equals("PointElement")) {
                        if (n4 >= n) {
                            break;
                        }
                        ++n4;
                    }
                    else {
                        if (n5 >= n2) {
                            break;
                        }
                        if (!array3[n5].inClass(array[i][j])) {
                            break;
                        }
                        ++n5;
                    }
                }
                if (j == array[i].length) {
                    break;
                }
            }
        }
        if (i == array.length) {
            sb.append("Data does not fit construction method. ");
            return -1;
        }
        return i;
    }
    
    void createElement(final int n, final int n2, final int n3, final PointElement[] array, final Element[] array2, final int[] array3) {
        Label_4182: {
            switch (n) {
                case 0: {
                    switch (n2) {
                        case 0: {
                            this.element[this.eCount] = new PlaneSlider(this.screen, array3[0], array3[1], 0.0);
                            return;
                        }
                        case 1: {
                            this.element[this.eCount] = new Midpoint(array[0], array[1]);
                            return;
                        }
                        case 2: {
                            switch (n3) {
                                case 0: {
                                    this.element[this.eCount] = new Intersection(array[0], array[1], array[2], array[3], this.screen);
                                    return;
                                }
                                case 1: {
                                    this.element[this.eCount] = new Intersection(array[0], array[1], array[2], array[3], (PlaneElement)array2[0]);
                                    return;
                                }
                                case 2: {
                                    this.element[this.eCount] = new IntersectionPL((PlaneElement)array2[0], array[0], array[1]);
                                    return;
                                }
                                default: {
                                    return;
                                }
                            }
                            break;
                        }
                        case 3: {
                            this.element[this.eCount] = array[0];
                            this.preexists[this.eCount] = true;
                            return;
                        }
                        case 4: {
                            this.element[this.eCount] = array[1];
                            this.preexists[this.eCount] = true;
                            return;
                        }
                        case 5: {
                            if (n3 == 0) {
                                this.element[this.eCount] = ((CircleElement)array2[0]).Center;
                            }
                            else {
                                this.element[this.eCount] = ((SphereElement)array2[0]).Center;
                            }
                            this.preexists[this.eCount] = true;
                            return;
                        }
                        case 6: {
                            if (n3 == 0) {
                                array3[2] = 0;
                            }
                            this.element[this.eCount] = new LineSlider(array[0], array[1], array3[0], array3[1], array3[2], false);
                            return;
                        }
                        case 7: {
                            if (n3 == 0) {
                                array3[2] = 0;
                            }
                            this.element[this.eCount] = new CircleSlider((CircleElement)array2[0], array3[0], array3[1], array3[2]);
                            return;
                        }
                        case 8: {
                            if (n3 == 0) {
                                array2[0] = this.screen;
                            }
                            final Circumcircle circumcircle = new Circumcircle(array[0], array[1], array[2], (PlaneElement)array2[0]);
                            this.element[this.eCount++] = circumcircle;
                            this.element[this.eCount] = circumcircle.Center;
                            this.preexists[this.eCount] = true;
                            return;
                        }
                        case 9: {
                            this.element[this.eCount] = ((PolygonElement)array2[0]).V[array3[0] - 1];
                            this.preexists[this.eCount] = true;
                            return;
                        }
                        case 10: {
                            if (n3 == 0) {
                                this.element[this.eCount] = new Foot(array[0], array[1], array[2]);
                                return;
                            }
                            this.element[this.eCount] = new PlaneFoot(array[0], (PlaneElement)array2[0]);
                            return;
                        }
                        case 11: {
                            this.element[this.eCount] = new Layoff(array[0], array[0], array[1], array[2], array[3]);
                            return;
                        }
                        case 12: {
                            this.element[this.eCount] = new Layoff(array[1], array[0], array[1], array[2], array[3]);
                            return;
                        }
                        case 13: {
                            this.element[this.eCount] = new Layoff(array[0], array[1], array[2], array[1], array[2]);
                            return;
                        }
                        case 14: {
                            if (n3 == 0) {
                                array2[0] = (array2[1] = this.screen);
                            }
                            this.element[this.eCount] = new Similar(array[0], array[1], (PlaneElement)array2[0], array[2], array[3], array[4], (PlaneElement)array2[1]);
                            return;
                        }
                        case 15: {
                            if (n3 == 0) {
                                this.element[this.eCount] = new Perpendicular(array[0], array[1], this.screen, array[0], array[1]);
                            }
                            else if (n3 == 1) {
                                this.element[this.eCount] = new Perpendicular(array[0], array[1], (PlaneElement)array2[0], array[0], array[1]);
                            }
                            else if (n3 == 2) {
                                this.element[this.eCount] = new Perpendicular(array[0], array[1], this.screen, array[2], array[3]);
                            }
                            else if (n3 == 3) {
                                this.element[this.eCount] = new Perpendicular(array[0], array[1], (PlaneElement)array2[0], array[2], array[3]);
                            }
                            else {
                                this.element[this.eCount] = new PlanePerpendicular(array[0], (PlaneElement)array2[0], array[1], array[2]);
                            }
                            this.element[this.eCount + 1] = ((LineElement)this.element[this.eCount]).B;
                            this.preexists[++this.eCount] = true;
                            return;
                        }
                        case 16: {
                            this.element[this.eCount] = new Proportion(array[0], array[1], array[2], array[3], array[4], array[5], array[6], array[7]);
                            return;
                        }
                        case 17: {
                            this.element[this.eCount] = new InvertPoint(array[0], (CircleElement)array2[0]);
                            return;
                        }
                        case 18: {
                            this.element[this.eCount] = new MeanProportional(array[0], array[1], array[2], array[3], array[4], array[5]);
                            return;
                        }
                        case 19: {
                            this.element[this.eCount] = new PlaneSlider((PlaneElement)array2[0], array3[0], array3[1], array3[2]);
                            return;
                        }
                        case 20: {
                            this.element[this.eCount] = new SphereSlider((SphereElement)array2[0], array3[0], array3[1], array3[2]);
                            return;
                        }
                        case 21: {
                            if (n3 == 0) {
                                array2[0] = this.screen;
                            }
                            this.element[this.eCount] = new AngleDivider(array[0], array[1], array[2], (PlaneElement)array2[0], 2);
                            return;
                        }
                        case 22: {
                            if (n3 == 0) {
                                array2[0] = this.screen;
                            }
                            this.element[this.eCount] = new AngleDivider(array[0], array[1], array[2], (PlaneElement)array2[0], array3[0]);
                            return;
                        }
                        case 23: {
                            if (n3 == 0) {
                                array3[2] = 0;
                            }
                            this.element[this.eCount] = new FixedPoint(array3[0], array3[1], array3[2]);
                            return;
                        }
                        case 24: {
                            if (n3 == 0) {
                                array3[2] = 0;
                            }
                            this.element[this.eCount] = new LineSlider(array[0], array[1], array3[0], array3[1], array3[2], true);
                            return;
                        }
                        case 25: {
                            this.element[this.eCount] = new Harmonic(array[0], array[1], array[2]);
                            return;
                        }
                        default: {
                            break Label_4182;
                        }
                    }
                    break;
                }
                case 1: {
                    switch (n2) {
                        case 0: {
                            this.element[this.eCount] = new LineElement(array[0], array[1]);
                            return;
                        }
                        case 1: {
                            if (n3 == 0) {
                                array2[0] = this.screen;
                            }
                            this.element[this.eCount] = new AngleDivider(array[0], array[1], array[2], (PlaneElement)array2[0], 2);
                            this.element[++this.eCount] = new LineElement(array[1], (PointElement)this.element[this.eCount - 1]);
                            return;
                        }
                        case 2: {
                            if (n3 == 0) {
                                array2[0] = this.screen;
                            }
                            this.element[this.eCount] = new AngleDivider(array[0], array[1], array[2], (PlaneElement)array2[0], array3[0]);
                            this.element[++this.eCount] = new LineElement(array[1], (PointElement)this.element[this.eCount - 1]);
                            return;
                        }
                        case 3: {
                            if (n3 == 0) {
                                this.element[this.eCount] = new Foot(array[0], array[1], array[2]);
                            }
                            else {
                                this.element[this.eCount] = new PlaneFoot(array[0], (PlaneElement)array2[0]);
                            }
                            this.element[++this.eCount] = new LineElement(array[0], (PointElement)this.element[this.eCount - 1]);
                            return;
                        }
                        case 4: {
                            this.element[this.eCount] = new Chord(array[0], array[1], (CircleElement)array2[0]);
                            return;
                        }
                        case 5: {
                            this.element[this.eCount] = new Bichord((CircleElement)array2[0], (CircleElement)array2[1]);
                            return;
                        }
                        case 6: {
                            if (n3 == 0) {
                                this.element[this.eCount] = new Perpendicular(array[0], array[1], this.screen, array[0], array[1]);
                                return;
                            }
                            if (n3 == 1) {
                                this.element[this.eCount] = new Perpendicular(array[0], array[1], (PlaneElement)array2[0], array[0], array[1]);
                                return;
                            }
                            if (n3 == 2) {
                                this.element[this.eCount] = new Perpendicular(array[0], array[1], this.screen, array[2], array[3]);
                                return;
                            }
                            if (n3 == 3) {
                                this.element[this.eCount] = new Perpendicular(array[0], array[1], (PlaneElement)array2[0], array[2], array[3]);
                                return;
                            }
                            this.element[this.eCount] = new PlanePerpendicular(array[0], (PlaneElement)array2[0], array[1], array[2]);
                            return;
                        }
                        case 7: {
                            this.element[this.eCount] = new Layoff(array[0], array[0], array[1], array[2], array[3]);
                            this.element[++this.eCount] = new LineElement(array[0], (PointElement)this.element[this.eCount - 1]);
                            return;
                        }
                        case 8: {
                            this.element[this.eCount] = new Layoff(array[1], array[0], array[1], array[2], array[3]);
                            this.element[++this.eCount] = new LineElement(array[1], (PointElement)this.element[this.eCount - 1]);
                            return;
                        }
                        case 9: {
                            this.element[this.eCount] = new Layoff(array[0], array[1], array[2], array[1], array[2]);
                            this.element[++this.eCount] = new LineElement(array[0], (PointElement)this.element[this.eCount - 1]);
                            return;
                        }
                        case 10: {
                            if (n3 == 0) {
                                array2[0] = (array2[1] = this.screen);
                            }
                            this.element[this.eCount] = new Similar(array[0], array[1], (PlaneElement)array2[0], array[2], array[3], array[4], (PlaneElement)array2[1]);
                            this.element[this.eCount + 1] = new LineElement(array[0], (PointElement)this.element[this.eCount]);
                            this.preexists[++this.eCount] = true;
                            return;
                        }
                        case 11: {
                            this.element[this.eCount] = new Proportion(array[0], array[1], array[2], array[3], array[4], array[5], array[6], array[7]);
                            this.element[++this.eCount] = new LineElement(array[6], (PointElement)this.element[this.eCount - 1]);
                            return;
                        }
                        case 12: {
                            this.element[this.eCount] = new MeanProportional(array[0], array[1], array[2], array[3], array[4], array[5]);
                            this.element[++this.eCount] = new LineElement(array[4], (PointElement)this.element[this.eCount - 1]);
                            return;
                        }
                        default: {
                            break Label_4182;
                        }
                    }
                    break;
                }
                case 2: {
                    Label_2978: {
                        switch (n2) {
                            case 0: {
                                switch (n3) {
                                    case 0: {
                                        this.element[this.eCount] = new CircleElement(array[0], array[1], this.screen);
                                        return;
                                    }
                                    case 1: {
                                        this.element[this.eCount] = new CircleElement(array[0], array[1], array[2], this.screen);
                                        return;
                                    }
                                    case 2: {
                                        this.element[this.eCount] = new CircleElement(array[0], array[1], (PlaneElement)array2[0]);
                                        return;
                                    }
                                    case 3: {
                                        this.element[this.eCount] = new CircleElement(array[0], array[1], array[2], (PlaneElement)array2[0]);
                                        return;
                                    }
                                    default: {
                                        break Label_2978;
                                    }
                                }
                                break;
                            }
                            case 1: {
                                if (n3 == 0) {
                                    array2[0] = this.screen;
                                }
                                this.element[this.eCount] = new Circumcircle(array[0], array[1], array[2], (PlaneElement)array2[0]);
                                return;
                            }
                            case 2: {
                                this.element[this.eCount] = new InvertCircle((CircleElement)array2[0], (CircleElement)array2[1]);
                                return;
                            }
                            case 3: {
                                this.element[this.eCount] = new IntersectionSS((SphereElement)array2[0], (SphereElement)array2[1]);
                                return;
                            }
                            default: {
                                break Label_4182;
                            }
                        }
                    }
                    break;
                }
                case 3: {
                    switch (n2) {
                        case 0: {
                            if (n3 == 0) {
                                array2[0] = this.screen;
                            }
                            this.element[this.eCount] = new RegularPolygon(array[0], array[1], (PlaneElement)array2[0], 4);
                            return;
                        }
                        case 1: {
                            this.element[this.eCount] = new PolygonElement(array[0], array[1], array[2]);
                            return;
                        }
                        case 2: {
                            this.element[this.eCount] = new PolygonElement(array[0], array[1], array[2], array[3]);
                            return;
                        }
                        case 3: {
                            this.element[this.eCount] = new PolygonElement(array[0], array[1], array[2], array[3], array[4]);
                            return;
                        }
                        case 4: {
                            this.element[this.eCount] = new PolygonElement(array[0], array[1], array[2], array[3], array[4], array[5]);
                            return;
                        }
                        case 5: {
                            if (n3 == 0) {
                                array2[0] = this.screen;
                            }
                            this.element[this.eCount] = new RegularPolygon(array[0], array[1], (PlaneElement)array2[0], 3);
                            return;
                        }
                        case 6: {
                            final Layoff layoff = new Layoff(array[0], array[1], array[2], array[1], array[2]);
                            this.element[this.eCount] = layoff;
                            this.element[++this.eCount] = new PolygonElement(array[0], array[1], array[2], layoff);
                            return;
                        }
                        case 7: {
                            if (n3 == 0) {
                                array2[0] = this.screen;
                            }
                            this.element[this.eCount] = new RegularPolygon(array[0], array[1], (PlaneElement)array2[0], array3[0]);
                            return;
                        }
                        case 8: {
                            if (n3 == 0) {
                                array2[0] = this.screen;
                            }
                            this.element[this.eCount] = new RegularPolygon(array[0], array[1], (PlaneElement)array2[0], array3[0], array3[1]);
                            return;
                        }
                        case 9: {
                            if (n3 == 0) {
                                array2[0] = (array2[1] = this.screen);
                            }
                            this.element[this.eCount] = new Similar(array[0], array[1], (PlaneElement)array2[0], array[2], array[3], array[4], (PlaneElement)array2[1]);
                            this.element[this.eCount + 1] = new PolygonElement(array[0], array[1], (PointElement)this.element[this.eCount]);
                            this.preexists[++this.eCount] = true;
                            return;
                        }
                        case 10: {
                            this.element[this.eCount] = new Application((PolygonElement)array2[0], array[0], array[1], array[2]);
                            return;
                        }
                        case 11: {
                            this.element[this.eCount] = new PolygonElement(array[0], array[1], array[2], array[3], array[4], array[5], array[6], array[7]);
                            return;
                        }
                        case 12: {
                            this.element[this.eCount] = ((PolyhedronElement)array2[0]).P[array3[0] - 1];
                            this.preexists[this.eCount] = true;
                            return;
                        }
                        default: {
                            break Label_4182;
                        }
                    }
                    break;
                }
                case 4: {
                    switch (n2) {
                        case 0: {
                            if (n3 == 0) {
                                array2[0] = this.screen;
                            }
                            this.element[this.eCount] = new SectorElement(array[0], array[1], array[2], (PlaneElement)array2[0]);
                            return;
                        }
                        case 1: {
                            if (n3 == 0) {
                                array2[0] = this.screen;
                            }
                            this.element[this.eCount] = new Arc(array[0], array[1], array[2], (PlaneElement)array2[0]);
                            return;
                        }
                        default: {
                            break Label_4182;
                        }
                    }
                    break;
                }
                case 5: {
                    switch (n2) {
                        case 0: {
                            this.element[this.eCount] = new PlaneElement(array[0], array[1], array[2]);
                            return;
                        }
                        case 1: {
                            this.element[this.eCount] = new PerpendicularPL(array[0], array[1]);
                            return;
                        }
                        case 2: {
                            this.element[this.eCount] = new ParallelP((PlaneElement)array2[0], array[0]);
                            return;
                        }
                        case 3: {
                            if (n3 == 0) {
                                this.element[this.eCount] = array[0].AP;
                            }
                            else {
                                this.element[this.eCount] = ((CircleElement)array2[0]).AP;
                            }
                            this.preexists[this.eCount] = true;
                            return;
                        }
                        default: {
                            break Label_4182;
                        }
                    }
                    break;
                }
                case 6: {
                    switch (n2) {
                        case 0: {
                            if (n3 == 0) {
                                this.element[this.eCount] = new SphereElement(array[0], array[0], array[1]);
                                return;
                            }
                            this.element[this.eCount] = new SphereElement(array[0], array[1], array[2]);
                            return;
                        }
                        default: {
                            break Label_4182;
                        }
                    }
                    break;
                }
                case 7: {
                    switch (n2) {
                        case 0: {
                            final PolygonElement polygonElement = new PolygonElement(array[0], array[1], array[2]);
                            this.element[this.eCount++] = polygonElement;
                            this.element[this.eCount] = new Pyramid(polygonElement, array[3]);
                            return;
                        }
                        case 1: {
                            final Layoff layoff2 = new Layoff(array[1], array[0], array[2], array[0], array[2]);
                            this.element[this.eCount++] = layoff2;
                            final PolygonElement polygonElement2 = new PolygonElement(array[1], array[0], array[2], layoff2);
                            this.element[this.eCount++] = polygonElement2;
                            this.element[this.eCount] = new Prism(polygonElement2, array[0], array[3]);
                            return;
                        }
                        case 2: {
                            this.element[this.eCount] = new Prism((PolygonElement)array2[0], array[0], array[1]);
                            return;
                        }
                        case 3: {
                            this.element[this.eCount] = new Pyramid((PolygonElement)array2[0], array[0]);
                            return;
                        }
                        default: {
                            break Label_4182;
                        }
                    }
                    break;
                }
            }
        }
    }
    
    Element constructElement(final String name, final String s, final String s2, final String s3, final StringBuffer sb) {
        if (this.lookupElement(name) != null) {
            sb.append("An element with the name " + name + " has already been created.");
            return null;
        }
        final int lookupElementClass = lookupElementClass(s);
        if (lookupElementClass == -1) {
            sb.append("Element class " + s + " is not known.");
            return null;
        }
        final int lookupConstructionMethod = lookupConstructionMethod(lookupElementClass, s2);
        if (lookupConstructionMethod == -1) {
            sb.append("ConstructionMethod " + s2 + " is not known for " + " element class " + s + ".");
            return null;
        }
        final PointElement[] array = new PointElement[8];
        final Element[] array2 = new Element[4];
        final int[] array3 = new int[3];
        final int selectDataChoice = this.selectDataChoice(s3, Slate.constructionDataType[lookupElementClass][lookupConstructionMethod], array, array2, array3, sb);
        if (selectDataChoice == -1) {
            sb.append("Construction method " + s2 + " for " + " element class " + s + " with data " + s3 + " requires different data.");
            return null;
        }
        if (this.element.length < this.eCount + 2) {
            this.extendArrays();
        }
        this.createElement(lookupElementClass, lookupConstructionMethod, selectDataChoice, array, array2, array3);
        this.element[this.eCount].name = name;
        return this.element[this.eCount++];
    }
    
    void setPivot(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        final Element lookupElement = this.lookupElement(stringTokenizer.nextToken());
        if (lookupElement == null || !lookupElement.inClass("PointElement")) {
            return;
        }
        if (!stringTokenizer.hasMoreTokens()) {
            ((PointElement)lookupElement).AP = this.screen;
            this.screen.pivot = (PointElement)lookupElement;
            return;
        }
        final Element lookupElement2 = this.lookupElement(stringTokenizer.nextToken());
        if (lookupElement2 == null || !lookupElement2.inClass("PlaneElement")) {
            return;
        }
        ((PlaneElement)lookupElement2).pivot = (PointElement)lookupElement;
    }
    
    void reset() {
        for (int i = 0; i < this.eCount; ++i) {
            this.element[i].reset();
        }
    }
    
    void updateCoordinates(int i) {
        ++i;
        while (i < this.eCount) {
            if (!this.element[i].defined()) {
                this.element[i].reset();
            }
            this.element[i].update();
            ++i;
        }
    }
    
    void translateCoordinates(final double n, final double n2) {
        for (int i = 0; i < this.eCount; ++i) {
            if (!this.preexists[i]) {
                this.element[i].translate(n, n2);
            }
        }
    }
    
    void rotateCoordinates(final int n, final int n2) {
        final PointElement pivot = this.pick.AP.pivot;
        final PointElement difference = PointElement.difference(this.pick, pivot);
        final double n3 = n - pivot.x;
        final double n4 = n2 - pivot.y;
        final PointElement s = this.pick.AP.S;
        final PointElement t = this.pick.AP.T;
        final double dot = PointElement.dot(difference, s);
        final double dot2 = PointElement.dot(difference, t);
        final double n5 = s.x * t.y - s.y * t.x;
        final double n6 = (n3 * t.y - n4 * t.x) / n5;
        final double n7 = (n4 * s.x - n3 * s.y) / n5;
        final double n8 = dot * dot + dot2 * dot2;
        final double n9 = (n6 * dot + n7 * dot2) / n8;
        final double n10 = (n7 * dot - n6 * dot2) / n8;
        for (int i = 0; i < this.eCount; ++i) {
            if (!this.preexists[i]) {
                this.element[i].rotate(pivot, n9, n10);
            }
        }
    }
    
    public void drawElements(final Graphics graphics) {
        graphics.setColor(this.getBackground());
        final Dimension size = this.size();
        graphics.fillRect(0, 0, size.width, size.height);
        for (int i = 0; i < this.eCount; ++i) {
            this.element[i].drawFace(graphics);
        }
        for (int j = 0; j < this.eCount; ++j) {
            this.element[j].drawEdge(graphics);
        }
        for (int k = 0; k < this.eCount; ++k) {
            this.element[k].drawVertex(graphics);
        }
        for (int l = 0; l < this.eCount; ++l) {
            this.element[l].drawName(graphics, size);
        }
    }
    
    public void update(final Graphics graphics) {
        final Dimension size = this.size();
        if (this.offscreen == null || size.width != this.offscreensize.width || size.height != this.offscreensize.height) {
            this.offscreen = this.createImage(size.width, size.height);
            this.offscreensize = size;
            (this.offgraphics = this.offscreen.getGraphics()).setFont(graphics.getFont());
        }
        this.drawElements(this.offgraphics);
        graphics.drawImage(this.offscreen, 0, 0, null);
    }
    
    public void paint(final Graphics graphics) {
        this.repaint();
    }
    
    void movePick(int n, int n2) {
        if (this.pick == null) {
            this.picki = -1;
            double n3 = Double.POSITIVE_INFINITY;
            for (int i = 0; i < this.eCount; ++i) {
                if (this.element[i].inClass("PointElement") && this.element[i].vertexColor != null) {
                    final double x = ((PointElement)this.element[i]).x;
                    final double y = ((PointElement)this.element[i]).y;
                    final double n4 = (x - n) * (x - n) + (y - n2) * (y - n2);
                    if (n4 < 100.0 && n4 < n3) {
                        this.picki = i;
                        n3 = n4;
                    }
                }
            }
        }
        if (this.picki == -1) {
            return;
        }
        this.pick = (PointElement)this.element[this.picki];
        final int width = this.size().width;
        if (n < 0) {
            n = 0;
        }
        else if (n > width) {
            n = width;
        }
        final int height = this.size().height;
        if (n2 < 0) {
            n2 = 0;
        }
        else if (n2 > height) {
            n2 = height;
        }
        if (Math.abs(n - this.pick.x) + Math.abs(n2 - this.pick.y) < 1.0) {
            return;
        }
        if (this.pick.dragable) {
            if (!this.pick.drag(n, n2)) {
                return;
            }
            this.updateCoordinates(this.picki);
        }
        else if (this.pick.AP != null && this.pick.AP.pivot != null && this.pick.AP.pivot != this.pick) {
            this.rotateCoordinates(n, n2);
        }
        else {
            this.translateCoordinates(n - this.pick.x, n2 - this.pick.y);
        }
        this.repaint();
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (n == 114 || n == 82 || n == 32) {
            this.reset();
            this.repaint();
            return true;
        }
        return false;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.pick = null;
        this.movePick(n, n2);
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        this.movePick(n, n2);
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.pick == null) {
            return true;
        }
        this.movePick(n, n2);
        this.pick = null;
        return true;
    }
    
    static {
        Slate.elementClassName = new String[] { "point", "line", "circle", "polygon", "sector", "plane", "sphere", "polyhedron" };
        Slate.constructionName = new String[][] { { "free", "midpoint", "intersection", "first", "last", "center", "lineSlider", "circleSlider", "circumcenter", "vertex", "foot", "cutoff", "extend", "parallelogram", "similar", "perpendicular", "proportion", "invert", "meanProportional", "planeSlider", "sphereSlider", "angleBisector", "angleDivider", "fixed", "lineSegmentSlider", "harmonic" }, { "connect", "angleBisector", "angleDivider", "foot", "chord", "bichord", "perpendicular", "cutoff", "extend", "parallel", "similar", "proportion", "meanProportional" }, { "radius", "circumcircle", "invert", "intersection" }, { "square", "triangle", "quadrilateral", "pentagon", "hexagon", "equilateralTriangle", "parallelogram", "regularPolygon", "starPolygon", "similar", "application", "octagon", "face" }, { "sector", "arc" }, { "3points", "perpendicular", "parallel", "ambient" }, { "radius" }, { "tetrahedron", "parallelepiped", "prism", "pyramid" } };
        Slate.constructionDataType = new String[][][][] { { { { "Integer", "Integer" } }, { { "PointElement", "PointElement" } }, { { "PointElement", "PointElement", "PointElement", "PointElement" }, { "PointElement", "PointElement", "PointElement", "PointElement", "PlaneElement" }, { "PlaneElement", "PointElement", "PointElement" } }, { { "PointElement", "PointElement" } }, { { "PointElement", "PointElement" } }, { { "CircleElement" }, { "SphereElement" } }, { { "PointElement", "PointElement", "Integer", "Integer" }, { "PointElement", "PointElement", "Integer", "Integer", "Integer" } }, { { "CircleElement", "Integer", "Integer" }, { "CircleElement", "Integer", "Integer", "Integer" } }, { { "PointElement", "PointElement", "PointElement" }, { "PointElement", "PointElement", "PointElement", "PlaneElement" } }, { { "PolygonElement", "Integer" } }, { { "PointElement", "PointElement", "PointElement" }, { "PointElement", "PlaneElement" } }, { { "PointElement", "PointElement", "PointElement", "PointElement" } }, { { "PointElement", "PointElement", "PointElement", "PointElement" } }, { { "PointElement", "PointElement", "PointElement" } }, { { "PointElement", "PointElement", "PointElement", "PointElement", "PointElement" }, { "PointElement", "PointElement", "PlaneElement", "PointElement", "PointElement", "PointElement", "PlaneElement" } }, { { "PointElement", "PointElement" }, { "PointElement", "PointElement", "PlaneElement" }, { "PointElement", "PointElement", "PointElement", "PointElement" }, { "PointElement", "PointElement", "PlaneElement", "PointElement", "PlaneElement" }, { "PointElement", "PlaneElement", "PointElement", "PointElement" } }, { { "PointElement", "PointElement", "PointElement", "PointElement", "PointElement", "PointElement", "PointElement", "PointElement" } }, { { "PointElement", "CircleElement" } }, { { "PointElement", "PointElement", "PointElement", "PointElement", "PointElement", "PointElement" } }, { { "PlaneElement", "Integer", "Integer", "Integer" } }, { { "SphereElement", "Integer", "Integer", "Integer" } }, { { "PointElement", "PointElement", "PointElement" }, { "PointElement", "PointElement", "PointElement", "PlaneElement" } }, { { "PointElement", "PointElement", "PointElement", "Integer" }, { "PointElement", "PointElement", "PointElement", "PlaneElement", "Integer" } }, { { "Integer", "Integer" }, { "Integer", "Integer", "Integer" } }, { { "PointElement", "PointElement", "Integer", "Integer" }, { "PointElement", "PointElement", "Integer", "Integer", "Integer" } }, { { "PointElement", "PointElement", "PointElement" } } }, { { { "PointElement", "PointElement" } }, { { "PointElement", "PointElement", "PointElement" }, { "PointElement", "PointElement", "PointElement", "PlaneElement" } }, { { "PointElement", "PointElement", "PointElement", "Integer" }, { "PointElement", "PointElement", "PointElement", "PlaneElement", "Integer" } }, { { "PointElement", "PointElement", "PointElement" }, { "PointElement", "PlaneElement" } }, { { "PointElement", "PointElement", "CircleElement" } }, { { "CircleElement", "CircleElement" } }, { { "PointElement", "PointElement" }, { "PointElement", "PointElement", "PlaneElement" }, { "PointElement", "PointElement", "PointElement", "PointElement" }, { "PointElement", "PointElement", "PlaneElement", "PointElement", "PlaneElement" }, { "PointElement", "PlaneElement", "PointElement", "PointElement" } }, { { "PointElement", "PointElement", "PointElement", "PointElement" } }, { { "PointElement", "PointElement", "PointElement", "PointElement" } }, { { "PointElement", "PointElement", "PointElement" } }, { { "PointElement", "PointElement", "PointElement", "PointElement", "PointElement" }, { "PointElement", "PointElement", "PlaneElement", "PointElement", "PointElement", "PointElement", "PlaneElement" } }, { { "PointElement", "PointElement", "PointElement", "PointElement", "PointElement", "PointElement", "PointElement", "PointElement" } }, { { "PointElement", "PointElement", "PointElement", "PointElement", "PointElement", "PointElement" } } }, { { { "PointElement", "PointElement" }, { "PointElement", "PointElement", "PointElement" }, { "PointElement", "PointElement", "PlaneElement" }, { "PointElement", "PointElement", "PointElement", "PlaneElement" } }, { { "PointElement", "PointElement", "PointElement" }, { "PointElement", "PointElement", "PointElement", "PlaneElement" } }, { { "CircleElement", "CircleElement" } }, { { "SphereElement", "SphereElement" } } }, { { { "PointElement", "PointElement" }, { "PointElement", "PointElement", "PlaneElement" } }, { { "PointElement", "PointElement", "PointElement" } }, { { "PointElement", "PointElement", "PointElement", "PointElement" } }, { { "PointElement", "PointElement", "PointElement", "PointElement", "PointElement" } }, { { "PointElement", "PointElement", "PointElement", "PointElement", "PointElement", "PointElement" } }, { { "PointElement", "PointElement" }, { "PointElement", "PointElement", "PlaneElement" } }, { { "PointElement", "PointElement", "PointElement" } }, { { "PointElement", "PointElement", "Integer" }, { "PointElement", "PointElement", "PlaneElement", "Integer" } }, { { "PointElement", "PointElement", "Integer", "Integer" }, { "PointElement", "PointElement", "PlaneElement", "Integer", "Integer" } }, { { "PointElement", "PointElement", "PointElement", "PointElement", "PointElement" }, { "PointElement", "PointElement", "PlaneElement", "PointElement", "PointElement", "PointElement", "PlaneElement" } }, { { "PolygonElement", "PointElement", "PointElement", "PointElement" } }, { { "PointElement", "PointElement", "PointElement", "PointElement", "PointElement", "PointElement", "PointElement", "PointElement" } }, { { "PolyhedronElement", "Integer" } } }, { { { "PointElement", "PointElement", "PointElement" }, { "PointElement", "PointElement", "PointElement", "PlaneElement" } }, { { "PointElement", "PointElement", "PointElement" }, { "PointElement", "PointElement", "PointElement", "PlaneElement" } } }, { { { "PointElement", "PointElement", "PointElement" } }, { { "PointElement", "PointElement" } }, { { "PlaneElement", "PointElement" } }, { { "PointElement" }, { "CircleElement" } } }, { { { "PointElement", "PointElement" }, { "PointElement", "PointElement", "PointElement" } } }, { { { "PointElement", "PointElement", "PointElement", "PointElement" } }, { { "PointElement", "PointElement", "PointElement", "PointElement" } }, { { "PolygonElement", "PointElement", "PointElement" } }, { { "PolygonElement", "PointElement" } } } };
    }
}
