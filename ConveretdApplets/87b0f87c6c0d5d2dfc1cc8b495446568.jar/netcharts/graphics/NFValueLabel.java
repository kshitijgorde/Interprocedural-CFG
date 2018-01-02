// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import netcharts.util.NFParamException;
import netcharts.util.NFUtil;
import java.util.Vector;
import java.util.Hashtable;
import netcharts.util.NFParam;

public class NFValueLabel
{
    public static final int NONE = 0;
    public static final int EXTERNAL = 1;
    public static final int MIDDLE = 2;
    public static final int TOP = 3;
    public static final int BOTTOM = 4;
    public static final int RIGHT = 5;
    public static final int LEFT = 6;
    public static final int TOPLEFT = 7;
    public static final int TOPRIGHT = 8;
    public static final int BOTTOMLEFT = 9;
    public static final int BOTTOMRIGHT = 10;
    public static final int CENTER = 11;
    
    public static void defineRectValueLabel(final NFParam nfParam, final String s, final int n) {
        final Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();
        hashtable.put("NONE", new Integer(0));
        hashtable.put("EXTERNAL", new Integer(1));
        hashtable.put("TOP", new Integer(3));
        hashtable.put("MIDDLE", new Integer(2));
        hashtable.put("BOTTOM", new Integer(4));
        defineValueLabel(nfParam, s, n, hashtable);
    }
    
    public static void definePointValueLabel(final NFParam nfParam, final String s, final int n) {
        final Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();
        hashtable.put("NONE", new Integer(0));
        hashtable.put("RIGHT", new Integer(5));
        hashtable.put("LEFT", new Integer(6));
        hashtable.put("TOP", new Integer(3));
        hashtable.put("BOTTOM", new Integer(4));
        hashtable.put("TOPLEFT", new Integer(7));
        hashtable.put("TOPRIGHT", new Integer(8));
        hashtable.put("BOTTOMLEFT", new Integer(9));
        hashtable.put("BOTTOMRIGHT", new Integer(10));
        hashtable.put("CENTER", new Integer(11));
        defineValueLabel(nfParam, s, n, hashtable);
    }
    
    public static void defineValueLabel(final NFParam nfParam, final String s, final int n, final Hashtable hashtable) {
        nfParam.defineVector(s + "ValueLabelStyle", nfParam.defineSymbol(s + "ValueLabelType", hashtable, new Integer(n)));
        nfParam.defineLabel(s + "ValueLabel");
        nfParam.defineRegion(s + "ValueLabelBox");
    }
    
    public static NFLabel loadValueLabel(final NFGraph nfGraph, final String s, final int n, final Vector vector, NFLabel loadParams, final int n2) throws NFParamException {
        if (nfGraph.param.changed(s + "ValueLabelStyle")) {
            final Vector vector2 = (Vector)nfGraph.param.get(s + "ValueLabelStyle");
            final int size = vector.size();
            int n3 = 0;
            for (int i = 0; i < size; ++i) {
                final NFDataSeries nfDataSeries = vector.elementAt(i);
                if (nfDataSeries.type == n) {
                    nfDataSeries.valueLabelStyle = NFUtil.getNumber(vector2, n3, n2);
                    ++n3;
                }
            }
            nfGraph.graphChanged = true;
        }
        if (nfGraph.param.changed(s + "ValueLabel")) {
            loadParams = NFLabel.loadParams(nfGraph.param, nfGraph.param.get(s + "ValueLabel"));
        }
        if (loadParams != null && nfGraph.param.changed(s + "ValueLabelBox")) {
            loadParams.setRegion(NFRegion.loadParams(nfGraph.param, nfGraph.param.get(s + "ValueLabelBox")));
        }
        return loadParams;
    }
    
    public static void drawPointValueLabel(NFLabel nfLabel, final Graphics graphics, final NFDataSeries nfDataSeries, int n, int n2, final String label) {
        final Color color = graphics.getColor();
        if (nfLabel == null) {
            nfLabel = new NFLabel();
        }
        nfLabel.setGraphics(graphics);
        nfLabel.setLabel(label);
        final Dimension bounds = nfLabel.getBounds(graphics);
        final int n3 = bounds.width / 2;
        final int n4 = bounds.height / 2;
        switch (nfDataSeries.valueLabelStyle) {
            case 5: {
                n -= n3;
                break;
            }
            case 6: {
                n += n3;
                break;
            }
            case 3: {
                n2 += n4;
                break;
            }
            case 4: {
                n2 -= n4;
                break;
            }
            case 7: {
                n += n3;
                n2 += n4;
                break;
            }
            case 8: {
                n -= n3;
                n2 += n4;
                break;
            }
            case 9: {
                n += n3;
                n2 -= n4;
                break;
            }
            case 10: {
                n -= n3;
                n2 -= n4;
                break;
            }
        }
        final Rectangle parentBounds = nfLabel.getParentBounds();
        nfLabel.setParentBounds(graphics.getClipRect());
        nfLabel.draw(label, n, n2);
        nfLabel.setParentBounds(parentBounds);
        graphics.setColor(color);
    }
    
    public static void drawRectValueLabel(NFLabel nfLabel, final Graphics graphics, final NFDataSeries nfDataSeries, final int n, final int n2, final int n3, final int n4, final String label, final int n5, final double n6, final boolean b) {
        final Color color = graphics.getColor();
        if (nfLabel == null) {
            nfLabel = new NFLabel();
        }
        nfLabel.setGraphics(graphics);
        nfLabel.setLabel(label);
        final Dimension bounds = nfLabel.getBounds(graphics);
        final int valueLabelStyle = nfDataSeries.valueLabelStyle;
        int n10 = 0;
        int n11 = 0;
        if (b) {
            final int n7 = n2 + n4;
            final int abs = Math.abs(n4);
            final int n8 = (n2 > n7) ? n2 : n7;
            final int n9 = (n2 < n7) ? n2 : n7;
            n10 = n + n3 / 2;
            switch (valueLabelStyle) {
                case 1: {
                    if (n6 >= 0.0) {
                        n11 = n9 - (n5 + bounds.height / 2);
                        n10 = n + ((nfDataSeries.sym.type == 9) ? n5 : 0) + n3 / 2;
                        break;
                    }
                    n11 = n8 + bounds.height / 2;
                    break;
                }
                case 3: {
                    if (n6 >= 0.0) {
                        n11 = n9 + bounds.height / 2;
                        break;
                    }
                    n11 = n8 - bounds.height / 2;
                    break;
                }
                case 2: {
                    if (n6 >= 0.0) {
                        n11 = n9 + abs / 2;
                        break;
                    }
                    n11 = n8 - abs / 2;
                    break;
                }
                case 4: {
                    if (n6 >= 0.0) {
                        n11 = n8 - bounds.height / 2;
                        break;
                    }
                    n11 = n9 + bounds.height / 2;
                    break;
                }
                default: {
                    return;
                }
            }
        }
        else {
            final int n12 = n + n3;
            final int abs2 = Math.abs(n3);
            final int n13 = (n < n12) ? n : n12;
            final int n14 = (n > n12) ? n : n12;
            switch (valueLabelStyle) {
                case 1: {
                    if (n6 >= 0.0) {
                        n10 = n14 + n5 + bounds.width / 2 + 3;
                        break;
                    }
                    n10 = n13 - bounds.width / 2;
                    break;
                }
                case 3: {
                    if (n6 >= 0.0) {
                        n10 = n14 - bounds.width / 2;
                        break;
                    }
                    n10 = n13 + bounds.width / 2;
                    break;
                }
                case 2: {
                    if (n6 >= 0.0) {
                        n10 = n13 + abs2 / 2;
                        break;
                    }
                    n10 = n14 - abs2 / 2;
                    break;
                }
                case 4: {
                    if (n6 >= 0.0) {
                        n10 = n13 + bounds.width / 2;
                        break;
                    }
                    n10 = n14 - bounds.width / 2;
                    break;
                }
                default: {
                    return;
                }
            }
            n11 = n2 + n4 / 2;
        }
        final Rectangle parentBounds = nfLabel.getParentBounds();
        nfLabel.setParentBounds(graphics.getClipRect());
        nfLabel.draw(label, n10, n11);
        nfLabel.setParentBounds(parentBounds);
        graphics.setColor(color);
    }
}
