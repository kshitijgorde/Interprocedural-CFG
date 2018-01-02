// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.ui;

import org.jfree.util.Log;
import java.awt.geom.Point2D;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;
import java.awt.font.FontRenderContext;
import org.jfree.text.TextUtilities;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.Shape;
import java.awt.Graphics2D;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.table.TableColumn;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.table.TableCellRenderer;
import javax.swing.JTable;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.table.TableModel;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import org.jfree.util.LogContext;

public abstract class RefineryUtilities
{
    private static boolean useDrawRotatedStringWorkaround;
    protected static final LogContext logger;
    static /* synthetic */ Class class$org$jfree$ui$RefineryUtilities;
    static /* synthetic */ Class class$java$lang$Number;
    
    public static void setUseDrawRotatedStringWorkaround(final boolean useDrawRotatedStringWorkaround) {
        RefineryUtilities.useDrawRotatedStringWorkaround = useDrawRotatedStringWorkaround;
    }
    
    public static void centerFrameOnScreen(final Window window) {
        positionFrameOnScreen(window, 0.5, 0.5);
    }
    
    public static void positionFrameOnScreen(final Window window, final double n, final double n2) {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final Dimension size = window.getSize();
        window.setBounds((int)(n * Math.max(screenSize.width - size.width, 0)), (int)(n2 * Math.max(screenSize.height - size.height, 0)), size.width, size.height);
    }
    
    public static void positionFrameRandomly(final Window window) {
        positionFrameOnScreen(window, Math.random(), Math.random());
    }
    
    public static void centerDialogInParent(final Dialog dialog) {
        positionDialogRelativeToParent(dialog, 0.5, 0.5);
    }
    
    public static void positionDialogRelativeToParent(final Dialog dialog, final double n, final double n2) {
        final Dimension size = dialog.getSize();
        final Container parent = dialog.getParent();
        final Dimension size2 = parent.getSize();
        final int n3 = parent.getX() - size.width;
        final int n4 = parent.getY() - size.height;
        final int n5 = size.width + size2.width;
        final int n6 = size.height + size2.height;
        final int n7 = n3 + (int)(n * n5);
        final int n8 = n4 + (int)(n2 * n6);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        dialog.setBounds(Math.max(Math.min(n7, screenSize.width - size.width), 0), Math.max(Math.min(n8, screenSize.height - size.height), 0), size.width, size.height);
    }
    
    public static JPanel createTablePanel(final TableModel tableModel) {
        final JPanel panel = new JPanel(new BorderLayout());
        final JTable table = new JTable(tableModel);
        for (int i = 0; i < tableModel.getColumnCount(); ++i) {
            final TableColumn column = table.getColumnModel().getColumn(i);
            if (tableModel.getColumnClass(i).equals((RefineryUtilities.class$java$lang$Number == null) ? (RefineryUtilities.class$java$lang$Number = class$("java.lang.Number")) : RefineryUtilities.class$java$lang$Number)) {
                column.setCellRenderer(new NumberCellRenderer());
            }
        }
        panel.add(new JScrollPane(table));
        return panel;
    }
    
    public static JLabel createJLabel(final String s, final Font font) {
        final JLabel label = new JLabel(s);
        label.setFont(font);
        return label;
    }
    
    public static JLabel createJLabel(final String s, final Font font, final Color foreground) {
        final JLabel label = new JLabel(s);
        label.setFont(font);
        label.setForeground(foreground);
        return label;
    }
    
    public static JButton createJButton(final String s, final Font font) {
        final JButton button = new JButton(s);
        button.setFont(font);
        return button;
    }
    
    public static void drawAlignedString(final String s, final Graphics2D graphics2D, final float n, final float n2, final TextAnchor textAnchor) {
        final float[] deriveTextBoundsAnchorOffsets = deriveTextBoundsAnchorOffsets(graphics2D, s, textAnchor);
        graphics2D.drawString(s, n + deriveTextBoundsAnchorOffsets[0], n2 + deriveTextBoundsAnchorOffsets[1]);
    }
    
    public static Shape calculateRotatedStringBounds(final String s, final Graphics2D graphics2D, final float n, final float n2, final TextAnchor textAnchor, final TextAnchor textAnchor2, final double n3) {
        if (s == null || s.equals("")) {
            return null;
        }
        final float[] deriveTextBoundsAnchorOffsets = deriveTextBoundsAnchorOffsets(graphics2D, s, textAnchor);
        if (RefineryUtilities.logger.isDebugEnabled()) {
            RefineryUtilities.logger.debug("TextBoundsAnchorOffsets = " + deriveTextBoundsAnchorOffsets[0] + ", " + deriveTextBoundsAnchorOffsets[1]);
        }
        final float[] deriveRotationAnchorOffsets = deriveRotationAnchorOffsets(graphics2D, s, textAnchor2);
        if (RefineryUtilities.logger.isDebugEnabled()) {
            RefineryUtilities.logger.debug("RotationAnchorOffsets = " + deriveRotationAnchorOffsets[0] + ", " + deriveRotationAnchorOffsets[1]);
        }
        return calculateRotatedStringBounds(s, graphics2D, n + deriveTextBoundsAnchorOffsets[0], n2 + deriveTextBoundsAnchorOffsets[1], n3, n + deriveTextBoundsAnchorOffsets[0] + deriveRotationAnchorOffsets[0], n2 + deriveTextBoundsAnchorOffsets[1] + deriveRotationAnchorOffsets[1]);
    }
    
    public static void drawRotatedString(final String s, final Graphics2D graphics2D, final float n, final float n2, final double n3) {
        drawRotatedString(s, graphics2D, n, n2, n3, n, n2);
    }
    
    public static void drawRotatedString(final String s, final Graphics2D graphics2D, final float n, final float n2, final TextAnchor textAnchor, final float n3, final float n4, final double n5) {
        if (s == null || s.equals("")) {
            return;
        }
        final float[] deriveTextBoundsAnchorOffsets = deriveTextBoundsAnchorOffsets(graphics2D, s, textAnchor);
        drawRotatedString(s, graphics2D, n + deriveTextBoundsAnchorOffsets[0], n2 + deriveTextBoundsAnchorOffsets[1], n5, n3, n4);
    }
    
    public static void drawRotatedString(final String s, final Graphics2D graphics2D, final float n, final float n2, final TextAnchor textAnchor, final TextAnchor textAnchor2, final double n3) {
        if (s == null || s.equals("")) {
            return;
        }
        final float[] deriveTextBoundsAnchorOffsets = deriveTextBoundsAnchorOffsets(graphics2D, s, textAnchor);
        final float[] deriveRotationAnchorOffsets = deriveRotationAnchorOffsets(graphics2D, s, textAnchor2);
        drawRotatedString(s, graphics2D, n + deriveTextBoundsAnchorOffsets[0], n2 + deriveTextBoundsAnchorOffsets[1], n3, n + deriveTextBoundsAnchorOffsets[0] + deriveRotationAnchorOffsets[0], n2 + deriveTextBoundsAnchorOffsets[1] + deriveRotationAnchorOffsets[1]);
    }
    
    public static void drawRotatedString(final String s, final Graphics2D graphics2D, final float n, final float n2, final double n3, final float n4, final float n5) {
        if (s == null || s.equals("")) {
            return;
        }
        final AffineTransform transform = graphics2D.getTransform();
        graphics2D.transform(AffineTransform.getRotateInstance(n3, n4, n5));
        if (RefineryUtilities.useDrawRotatedStringWorkaround) {
            new TextLayout(s, graphics2D.getFont(), graphics2D.getFontRenderContext()).draw(graphics2D, n, n2);
        }
        else {
            graphics2D.drawString(s, n, n2);
        }
        graphics2D.setTransform(transform);
    }
    
    public static Shape calculateRotatedStringBounds(final String s, final Graphics2D graphics2D, final float n, final float n2, final double n3, final float n4, final float n5) {
        if (s == null || s.equals("")) {
            return null;
        }
        return AffineTransform.getRotateInstance(n3, n4, n5).createTransformedShape(AffineTransform.getTranslateInstance(n, n2).createTransformedShape(TextUtilities.getTextBounds(s, graphics2D, graphics2D.getFontMetrics())));
    }
    
    private static float[] deriveTextBoundsAnchorOffsets(final Graphics2D graphics2D, final String s, final TextAnchor textAnchor) {
        final float[] array = new float[2];
        final FontRenderContext fontRenderContext = graphics2D.getFontRenderContext();
        final Font font = graphics2D.getFont();
        final Rectangle2D textBounds = TextUtilities.getTextBounds(s, graphics2D, graphics2D.getFontMetrics(font));
        final LineMetrics lineMetrics = font.getLineMetrics(s, fontRenderContext);
        final float n = lineMetrics.getAscent() / 2.0f;
        final float descent = lineMetrics.getDescent();
        final float leading = lineMetrics.getLeading();
        float n2 = 0.0f;
        float n3 = 0.0f;
        if (textAnchor == TextAnchor.TOP_CENTER || textAnchor == TextAnchor.CENTER || textAnchor == TextAnchor.BOTTOM_CENTER || textAnchor == TextAnchor.BASELINE_CENTER || textAnchor == TextAnchor.HALF_ASCENT_CENTER) {
            n2 = (float)(-textBounds.getWidth()) / 2.0f;
        }
        else if (textAnchor == TextAnchor.TOP_RIGHT || textAnchor == TextAnchor.CENTER_RIGHT || textAnchor == TextAnchor.BOTTOM_RIGHT || textAnchor == TextAnchor.BASELINE_RIGHT || textAnchor == TextAnchor.HALF_ASCENT_RIGHT) {
            n2 = (float)(-textBounds.getWidth());
        }
        if (textAnchor == TextAnchor.TOP_LEFT || textAnchor == TextAnchor.TOP_CENTER || textAnchor == TextAnchor.TOP_RIGHT) {
            n3 = -descent - leading + (float)textBounds.getHeight();
        }
        else if (textAnchor == TextAnchor.HALF_ASCENT_LEFT || textAnchor == TextAnchor.HALF_ASCENT_CENTER || textAnchor == TextAnchor.HALF_ASCENT_RIGHT) {
            n3 = n;
        }
        else if (textAnchor == TextAnchor.CENTER_LEFT || textAnchor == TextAnchor.CENTER || textAnchor == TextAnchor.CENTER_RIGHT) {
            n3 = -descent - leading + (float)(textBounds.getHeight() / 2.0);
        }
        else if (textAnchor == TextAnchor.BASELINE_LEFT || textAnchor == TextAnchor.BASELINE_CENTER || textAnchor == TextAnchor.BASELINE_RIGHT) {
            n3 = 0.0f;
        }
        else if (textAnchor == TextAnchor.BOTTOM_LEFT || textAnchor == TextAnchor.BOTTOM_CENTER || textAnchor == TextAnchor.BOTTOM_RIGHT) {
            n3 = -lineMetrics.getDescent() - lineMetrics.getLeading();
        }
        array[0] = n2;
        array[1] = n3;
        return array;
    }
    
    private static float[] deriveRotationAnchorOffsets(final Graphics2D graphics2D, final String s, final TextAnchor textAnchor) {
        final float[] array = new float[2];
        final LineMetrics lineMetrics = graphics2D.getFont().getLineMetrics(s, graphics2D.getFontRenderContext());
        final Rectangle2D textBounds = TextUtilities.getTextBounds(s, graphics2D, graphics2D.getFontMetrics());
        final float n = lineMetrics.getAscent() / 2.0f;
        final float descent = lineMetrics.getDescent();
        final float leading = lineMetrics.getLeading();
        float n2 = 0.0f;
        float n3 = 0.0f;
        if (textAnchor == TextAnchor.TOP_LEFT || textAnchor == TextAnchor.CENTER_LEFT || textAnchor == TextAnchor.BOTTOM_LEFT || textAnchor == TextAnchor.BASELINE_LEFT || textAnchor == TextAnchor.HALF_ASCENT_LEFT) {
            n2 = 0.0f;
        }
        else if (textAnchor == TextAnchor.TOP_CENTER || textAnchor == TextAnchor.CENTER || textAnchor == TextAnchor.BOTTOM_CENTER || textAnchor == TextAnchor.BASELINE_CENTER || textAnchor == TextAnchor.HALF_ASCENT_CENTER) {
            n2 = (float)textBounds.getWidth() / 2.0f;
        }
        else if (textAnchor == TextAnchor.TOP_RIGHT || textAnchor == TextAnchor.CENTER_RIGHT || textAnchor == TextAnchor.BOTTOM_RIGHT || textAnchor == TextAnchor.BASELINE_RIGHT || textAnchor == TextAnchor.HALF_ASCENT_RIGHT) {
            n2 = (float)textBounds.getWidth();
        }
        if (textAnchor == TextAnchor.TOP_LEFT || textAnchor == TextAnchor.TOP_CENTER || textAnchor == TextAnchor.TOP_RIGHT) {
            n3 = descent + leading - (float)textBounds.getHeight();
        }
        else if (textAnchor == TextAnchor.CENTER_LEFT || textAnchor == TextAnchor.CENTER || textAnchor == TextAnchor.CENTER_RIGHT) {
            n3 = descent + leading - (float)(textBounds.getHeight() / 2.0);
        }
        else if (textAnchor == TextAnchor.HALF_ASCENT_LEFT || textAnchor == TextAnchor.HALF_ASCENT_CENTER || textAnchor == TextAnchor.HALF_ASCENT_RIGHT) {
            n3 = -n;
        }
        else if (textAnchor == TextAnchor.BASELINE_LEFT || textAnchor == TextAnchor.BASELINE_CENTER || textAnchor == TextAnchor.BASELINE_RIGHT) {
            n3 = 0.0f;
        }
        else if (textAnchor == TextAnchor.BOTTOM_LEFT || textAnchor == TextAnchor.BOTTOM_CENTER || textAnchor == TextAnchor.BOTTOM_RIGHT) {
            n3 = lineMetrics.getDescent() + lineMetrics.getLeading();
        }
        array[0] = n2;
        array[1] = n3;
        return array;
    }
    
    public static void drawRotatedShape(final Shape shape, final Graphics2D graphics2D, final float n, final float n2, final double n3) {
        final AffineTransform transform = graphics2D.getTransform();
        graphics2D.transform(AffineTransform.getRotateInstance(n3, n, n2));
        graphics2D.draw(shape);
        graphics2D.setTransform(transform);
    }
    
    public static Point2D getPointInRectangle(double max, double max2, final Rectangle2D rectangle2D) {
        max = Math.max(rectangle2D.getMinX(), Math.min(max, rectangle2D.getMaxX()));
        max2 = Math.max(rectangle2D.getMinY(), Math.min(max2, rectangle2D.getMaxY()));
        return new Point2D.Double(max, max2);
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
        RefineryUtilities.useDrawRotatedStringWorkaround = true;
        logger = Log.createContext((RefineryUtilities.class$org$jfree$ui$RefineryUtilities == null) ? (RefineryUtilities.class$org$jfree$ui$RefineryUtilities = class$("org.jfree.ui.RefineryUtilities")) : RefineryUtilities.class$org$jfree$ui$RefineryUtilities);
    }
}
