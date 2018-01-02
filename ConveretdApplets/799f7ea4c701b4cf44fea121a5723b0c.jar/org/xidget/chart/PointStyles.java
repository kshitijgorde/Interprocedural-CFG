// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.chart;

import java.util.List;
import org.xmodel.Xlate;
import org.xmodel.IModelObject;

public class PointStyles
{
    private static final int[][] circle5x5;
    private static final int[][] square5x5;
    private static final int[][] triangle5x5;
    private static final int[][] diamond5x5;
    private static final int[][] plus5x5;
    private static final int[][] dash5x5;
    private static final int[][] ex5x5;
    private static final int[][] dot;
    
    static {
        circle5x5 = new int[][] { { -1, -2 }, { 1, -2 }, { 1, -2 }, { 2, -1 }, { 2, -1 }, { 2, 1 }, { 2, 1 }, { 1, 2 }, { 1, 2 }, { -1, 2 }, { -1, 2 }, { -2, 1 }, { -2, 1 }, { -2, -1 }, { -2, -1 }, { -1, -2 } };
        square5x5 = new int[][] { { -2, -2 }, { 2, -2 }, { 2, -2 }, { 2, 2 }, { 2, 2 }, { -2, 2 }, { -2, 2 }, { -2, -2 } };
        triangle5x5 = new int[][] { { 0, -2 }, { 2, 2 }, { 2, 2 }, { -2, 2 }, { -2, 2 }, { 0, -2 } };
        diamond5x5 = new int[][] { { 0, -2 }, { 2, 0 }, { 2, 0 }, { 0, 2 }, { 0, 2 }, { -2, 0 }, { -2, 0 }, { 0, -2 } };
        plus5x5 = new int[][] { { 0, -2 }, { 0, 2 }, { -2, 0 }, { 2, 0 } };
        dash5x5 = new int[][] { { -2, 0 }, { 2, 0 } };
        ex5x5 = new int[][] { { -2, -2 }, { 2, 2 }, { -2, 2 }, { 2, -2 } };
        dot = new int[][] { new int[2], new int[2] };
    }
    
    public static final int[][] getLineSegments(final IModelObject modelObject) {
        final List<IModelObject> children = modelObject.getChildren();
        final int[][] array = new int[children.size() * 2][];
        int[] parse = null;
        int i = 0;
        int n = 0;
        while (i < children.size()) {
            final int[] array2 = parse;
            final IModelObject modelObject2 = children.get(i);
            parse = parse(Xlate.get(modelObject2, "0, 0"));
            if (modelObject2.isType("move")) {
                if (array2 != null) {
                    array[n++] = array2;
                    array[n++] = array2;
                }
            }
            else {
                if (!modelObject2.isType("line")) {
                    throw new IllegalArgumentException("Illegal shape specification.");
                }
                if (array2 != null) {
                    array[n++] = array2;
                    array[n++] = parse;
                }
            }
            ++i;
        }
        return array;
    }
    
    private static int[] parse(String trim) {
        try {
            if (trim == null) {
                return null;
            }
            trim = trim.trim();
            if (trim.length() == 0) {
                return null;
            }
            final int n = 0;
            final int index = trim.indexOf(44);
            if (index > 0) {
                return new int[] { Integer.parseInt(trim.substring(n, index).trim()), Integer.parseInt(trim.substring(index + 1).trim()) };
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    public static final int[][] getLineSegments(final Style style) {
        switch (style) {
            case circle: {
                return PointStyles.circle5x5;
            }
            case square: {
                return PointStyles.square5x5;
            }
            case triangle: {
                return PointStyles.triangle5x5;
            }
            case diamond: {
                return PointStyles.diamond5x5;
            }
            case plus: {
                return PointStyles.plus5x5;
            }
            case dash: {
                return PointStyles.dash5x5;
            }
            case ex: {
                return PointStyles.ex5x5;
            }
            case dot: {
                return PointStyles.dot;
            }
            default: {
                return null;
            }
        }
    }
    
    public enum Style
    {
        circle("circle", 0), 
        square("square", 1), 
        triangle("triangle", 2), 
        diamond("diamond", 3), 
        plus("plus", 4), 
        dash("dash", 5), 
        ex("ex", 6), 
        bar("bar", 7), 
        dot("dot", 8);
        
        private Style(final String s, final int n) {
        }
    }
}
