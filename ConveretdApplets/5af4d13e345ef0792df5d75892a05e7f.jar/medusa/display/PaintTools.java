// 
// Decompiled by Procyon v0.5.30
// 

package medusa.display;

import java.awt.geom.CubicCurve2D;
import java.awt.Color;
import medusa.graph.Node;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import java.awt.Shape;
import java.awt.Polygon;
import java.awt.Graphics2D;

public class PaintTools
{
    public static void drawArrow(final Graphics2D graphics2D, final int n, final int n2, int n3, int n4, final double n5, final double n6) {
        final int n7 = 9;
        final int n8 = 6;
        final double n9 = getTheta(n, n3, n2, n4) - n5 * n6;
        final int n10 = -(int)(Math.cos(n9) * n7);
        final int n11 = -(int)(Math.sin(n9) * n7);
        final int n12 = (int)(Math.sin(n9) * n8);
        final int n13 = (int)(Math.cos(n9) * n8);
        n3 -= (int)(Math.cos(n9) * n7);
        n4 -= (int)(Math.sin(n9) * n7);
        graphics2D.fillPolygon(new int[] { n3 + n10 + n12, n3, n3 + n10 - n12 }, new int[] { n4 + n11 - n13, n4, n4 + n11 + n13 }, 3);
    }
    
    public static double getTheta(final int n, final int n2, final int n3, final int n4) {
        final int n5 = n2 - n;
        double atan = Math.atan((n4 - n3) / n5);
        if (n5 < 0.0) {
            atan += 3.141592653589793;
        }
        return atan;
    }
    
    private static void drawStop(final Graphics2D graphics2D, final int n, final int n2, int n3, int n4, final double n5, final double n6) {
        final int n7 = 9;
        final int n8 = 6;
        final double n9 = getTheta(n, n3, n2, n4) - n5 * n6;
        final int n10 = -(int)(Math.cos(n9) * n7);
        final int n11 = -(int)(Math.sin(n9) * n7);
        final int n12 = (int)(Math.sin(n9) * n8);
        final int n13 = (int)(Math.cos(n9) * n8);
        n3 -= (int)(Math.cos(n9) * n7);
        n4 -= (int)(Math.sin(n9) * n7);
        graphics2D.drawLine(n3 + n10 + n12, n4 + n11 - n13, n3 + n10 - n12, n4 + n11 + n13);
    }
    
    public static Polygon rhomb(final int n, final int n2, final int n3) {
        final int n4 = 2;
        final int n5 = n + n3 / 2;
        final int n6 = n + n3 + n4;
        final int n7 = n2 + n3 / 2;
        return new Polygon(new int[] { n - n4, n5, n6, n5 }, new int[] { n7, n2 + n3, n7, n2 }, 4);
    }
    
    public static Polygon triangle(final int n, final int n2, final int n3) {
        final int n4 = n + n3 / 2;
        final int n5 = n + n3;
        final int n6 = n2 + n3;
        return new Polygon(new int[] { n, n5, n4 }, new int[] { n6, n6, n2 }, 3);
    }
    
    public static Shape getShape(final int n, final int n2, final int n3, final int n4) {
        switch (n) {
            case 0: {
                return new Ellipse2D.Double(n2, n3, n4, n4);
            }
            case 1: {
                return new Rectangle2D.Double(n2, n3, n4, n4);
            }
            case 2: {
                return triangle(n2, n3, n4);
            }
            case 3: {
                return rhomb(n2, n3, n4);
            }
            case 4: {
                return new Ellipse2D.Double(n2, n3, n4 * 2, n4 * 0.75);
            }
            default: {
                return new Ellipse2D.Double(n2, n3, n4, n4);
            }
        }
    }
    
    public static void drawCompositeNode(final Graphics2D graphics2D, final Node node, final int n) {
        final int n2 = (int)(n / 2.0);
        final int n3 = (int)node.getX();
        final int n4 = (int)node.getY();
        final Color color2 = node.getColor2();
        final Color color3 = node.getColor3();
        final Rectangle2D.Double double1 = new Rectangle2D.Double(n3 - n2, n4 - n2, n, n);
        graphics2D.setColor(node.getColor());
        graphics2D.fill(double1);
        if (color2 != null) {
            graphics2D.setColor(color2);
            graphics2D.fill(new Rectangle2D.Double(n3, n4 - n2, n - n2, n));
        }
        if (color3 != null) {
            graphics2D.setColor(color3);
            graphics2D.fill(new Rectangle2D.Double(n3 - n2, n4, n, n - n2));
        }
        if (node.isFixed()) {
            graphics2D.setColor(Color.yellow);
        }
        else {
            graphics2D.setColor(Color.black);
        }
        graphics2D.draw(double1);
    }
    
    private static void drawCircle(final Graphics2D graphics2D, final int n, final int n2, int n3, int n4, final double n5, final double n6) {
        final int n7 = 12;
        final int n8 = 6;
        final double n9 = getTheta(n, n3, n2, n4) - n5 * n6;
        final int n10 = -(int)(Math.cos(n9) * n7);
        final int n11 = -(int)(Math.sin(n9) * n7);
        n3 -= (int)(Math.cos(n9) * n7);
        n3 -= n8 / 2;
        n4 -= (int)(Math.sin(n9) * n7);
        n4 -= n8 / 2;
        graphics2D.fill(new Ellipse2D.Double(n3, n4, n8, n8));
    }
    
    public static void paintPath(final Graphics2D graphics2D, final int n, final int n2, final int n3, final int n4, final double n5, final double n6, final boolean b) {
        paintPath(graphics2D, n, n2, n3, n4, n5, n6, b, false);
    }
    
    public static void paintPath(final Graphics2D graphics2D, final int n, final int n2, final int n3, final int n4, final double n5, final double n6, final boolean b, final boolean b2) {
        final double n7 = n3 - n;
        final double n8 = n4 - n2;
        final double n9 = Math.sqrt(n7 * n7 + n8 * n8) / 4.0;
        if (n5 != 0.0) {
            final double[] controlPoints = controlPoints(n, n2, n3, n4, n5, n6);
            graphics2D.draw(new CubicCurve2D.Double(n, n2, controlPoints[0], controlPoints[1], controlPoints[2], controlPoints[3], n3, n4));
        }
        else {
            graphics2D.drawLine(n, n2, n3, n4);
        }
        if (b) {
            drawArrow(graphics2D, n, n2, n3, n4, n5, n6);
        }
    }
    
    public static void PaintPathWithConfLabel(final Graphics2D graphics2D, final int n, final int n2, final int n3, final int n4, final double n5, final double n6, final boolean b, final boolean b2) {
        graphics2D.drawLine(n, n2, n3, n4);
        final int n7 = (n + n3) / 2;
        final int n8 = (n2 + n4) / 2;
        graphics2D.setColor(Color.WHITE);
        graphics2D.drawRect(n7, n8, 10, 10);
    }
    
    public static double[] controlPoints(final int n, final int n2, final int n3, final int n4, final double n5, final double n6) {
        final double n7 = n3 - n;
        final double n8 = n4 - n2;
        final double n9 = Math.sqrt(n7 * n7 + n8 * n8) / 4.0;
        double atan = Math.atan(n8 / n7);
        if (n7 < 0.0) {
            atan += 3.141592653589793;
        }
        final double n10 = atan + 3.141592653589793;
        final double n11 = atan + n5 * n6;
        final double n12 = n10 - n5 * n6;
        return new double[] { n + Math.cos(n11) * n9, n2 + Math.sin(n11) * n9, n3 + Math.cos(n12) * n9, n4 + Math.sin(n12) * n9 };
    }
    
    public static int[] intControlPoints(final int n, final int n2, final int n3, final int n4, final double n5, final double n6) {
        final double[] controlPoints = controlPoints(n, n2, n3, n4, n5, n6);
        return new int[] { (int)controlPoints[0], (int)controlPoints[1], (int)controlPoints[2], (int)controlPoints[3] };
    }
    
    public static void paintRegulatoryPath(final Graphics2D graphics2D, final int n, final int n2, final int n3, final int n4, final double n5, final double n6, final int n7) {
        final double n8 = n3 - n;
        final double n9 = n4 - n2;
        final double n10 = Math.sqrt(n8 * n8 + n9 * n9) / 4.0;
        if (n5 != 0.0) {
            double atan = Math.atan(n9 / n8);
            if (n8 < 0.0) {
                atan += 3.141592653589793;
            }
            final double n11 = atan + 3.141592653589793;
            final double n12 = atan + n5 * n6;
            final double n13 = n11 - n5 * n6;
            graphics2D.draw(new CubicCurve2D.Double(n, n2, n + Math.cos(n12) * n10, n2 + Math.sin(n12) * n10, n3 + Math.cos(n13) * n10, n4 + Math.sin(n13) * n10, n3, n4));
        }
        else {
            graphics2D.drawLine(n, n2, n3, n4);
        }
        switch (n7) {
            case 1: {
                drawArrow(graphics2D, n, n2, n3, n4, n5, n6);
                break;
            }
            case 2: {
                drawStop(graphics2D, n, n2, n3, n4, n5, n6);
                break;
            }
            case 3: {
                drawCircle(graphics2D, n, n2, n3, n4, n5, n6);
                break;
            }
        }
    }
    
    public static String psRhomb(final int n, final int n2, final int n3) {
        final int n4 = (int)(n3 / 2.0);
        final StringBuffer sb = new StringBuffer();
        final int n5 = n - n4;
        final int n6 = n + n4;
        final int n7 = n2 + n4;
        final int n8 = n2 - n4;
        sb.append("newpath\n");
        sb.append(n5 + " " + n2 + " moveto\n");
        sb.append(n + " " + n7 + " lineto\n");
        sb.append(n6 + " " + n2 + " lineto\n");
        sb.append(n + " " + n8 + " lineto\n");
        sb.append("closepath\n");
        sb.append("gsave\n");
        sb.append("fill\n");
        sb.append("grestore\n");
        sb.append("0 0 0 setrgbcolor\n");
        sb.append("1 setlinewidth\n");
        sb.append("stroke\n");
        return sb.toString();
    }
    
    public static String psTriangle(final int n, final int n2, final int n3) {
        final StringBuffer sb = new StringBuffer();
        final int n4 = (int)(n3 / 2.0);
        final int n5 = n - n4;
        final int n6 = n + n4;
        final int n7 = n2 - n4;
        final int n8 = n2 + n4;
        sb.append("newpath\n");
        sb.append(n5 + " " + n7 + " moveto\n");
        sb.append(n + " " + n8 + " lineto\n");
        sb.append(n6 + " " + n7 + " lineto\n");
        sb.append("closepath\n");
        sb.append("gsave\n");
        sb.append("fill\n");
        sb.append("grestore\n");
        sb.append("1 setlinewidth\n");
        sb.append("0 0 0 setrgbcolor\n");
        sb.append("stroke\n");
        return sb.toString();
    }
    
    public static String psRectangle(final int n, final int n2, final int n3) {
        final StringBuffer sb = new StringBuffer();
        final int n4 = (int)(n3 / 2.0);
        final int n5 = n - n4;
        final int n6 = n + n4;
        final int n7 = n2 - n4;
        final int n8 = n2 + n4;
        sb.append("newpath\n");
        sb.append(n5 + " " + n7 + " moveto\n");
        sb.append(n5 + " " + n8 + " lineto\n");
        sb.append(n6 + " " + n8 + " lineto\n");
        sb.append(n6 + " " + n7 + " lineto\n");
        sb.append("closepath\n");
        sb.append("gsave\n");
        sb.append("fill\n");
        sb.append("grestore\n");
        sb.append("1 setlinewidth\n");
        sb.append("0 0 0 setrgbcolor\n");
        sb.append("stroke\n");
        return sb.toString();
    }
    
    public static String psCircle(final int n, final int n2, final int n3) {
        final StringBuffer sb = new StringBuffer();
        final int n4 = (int)(n3 / 2.0);
        sb.append("newpath\n");
        sb.append(n + " " + n2 + " " + n4 + " 0 360 arc closepath fill stroke\n");
        sb.append("0 0 0 setrgbcolor\n");
        sb.append("1 setlinewidth\n");
        sb.append(n + " " + n2 + " " + n4 + " 0 360 arc closepath stroke\n");
        return sb.toString();
    }
}
