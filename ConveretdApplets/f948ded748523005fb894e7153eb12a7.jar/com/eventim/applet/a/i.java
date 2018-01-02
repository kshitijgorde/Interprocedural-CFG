// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet.a;

import com.eventim.applet.EventimApplet;
import java.net.URL;
import java.awt.Toolkit;
import com.eventim.common.transfer.saalplan.Farbverwaltung;
import com.eventim.applet.l;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public final class i
{
    private static Map a;
    private static Map b;
    
    static {
        i.a = new HashMap();
        i.b = new HashMap();
    }
    
    public static Icon a(final Color paint, final Color paint2, final Color paint3) {
        final BufferedImage bufferedImage;
        final Graphics2D graphics;
        (graphics = (bufferedImage = new BufferedImage(11, 11, 2)).createGraphics()).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setPaint(paint2);
        graphics.fillRoundRect(0, 0, 10, 10, 2, 2);
        graphics.setPaint(paint3);
        graphics.drawRoundRect(0, 0, 10, 10, 2, 2);
        graphics.setPaint(paint);
        graphics.drawLine(3, 3, 7, 7);
        graphics.drawLine(3, 7, 7, 3);
        return new ImageIcon(bufferedImage);
    }
    
    public static ImageIcon a(final String s, final l l) {
        if (i.a.containsKey(s)) {
            return i.a.get(s);
        }
        return b(s, l);
    }
    
    public static ImageIcon a(final Integer n) {
        if (i.b.containsKey(n)) {
            return i.b.get(n);
        }
        final BufferedImage bufferedImage;
        final Graphics2D graphics;
        (graphics = (bufferedImage = new BufferedImage(12, 12, 2)).createGraphics()).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.setPaint(new Color(0.5f, 0.5f, 0.5f, 0.5f));
        graphics.fillOval(2, 2, 10, 10);
        graphics.setPaint(new Color(Farbverwaltung.getFarbeForPkNr(n)));
        graphics.fillOval(0, 0, 10, 10);
        graphics.setPaint(Color.black);
        graphics.drawOval(0, 0, 10, 10);
        final ImageIcon imageIcon = new ImageIcon(bufferedImage);
        i.a.put(n, imageIcon);
        return imageIcon;
    }
    
    public static void a(final l l) {
        b("papierkorb_neu_transparent.gif", l);
        b("lupe_transparent.gif", l);
        b("infoZeichen.png", l);
        b("apply_promo.gif", l);
        b("clear_promo.gif", l);
        b("addtocart.gif", l);
        b("recaptcha_retry.gif", l);
    }
    
    private static ImageIcon b(final String s, final l l) {
        try {
            if (s == null || s.length() == 0) {
                return null;
            }
            final Image image;
            (image = Toolkit.getDefaultToolkit().getImage(new URL("http", l.q(), l.s(), l.r() + s))).flush();
            final ImageIcon imageIcon = new ImageIcon(image);
            i.a.put(s, imageIcon);
            return imageIcon;
        }
        catch (Exception ex) {
            EventimApplet.a("Failed to load image '" + s + "': " + ex, 0);
            return null;
        }
    }
}
