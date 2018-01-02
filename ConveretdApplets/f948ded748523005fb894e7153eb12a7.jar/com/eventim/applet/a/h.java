// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet.a;

import java.awt.Font;
import com.eventim.common.transfer.saalplan.StehplatzbereichDetails;
import com.eventim.common.transfer.saalplan.Farbverwaltung;
import com.eventim.applet.EventimApplet;
import com.eventim.common.transfer.saalplan.SitzDetails;
import com.eventim.common.transfer.saalplan.TextDetails;
import com.eventim.common.transfer.saalplan.ReiheDetails;
import java.awt.Rectangle;
import com.eventim.common.transfer.saalplan.RechteckDetails;
import java.awt.geom.GeneralPath;
import com.eventim.common.transfer.saalplan.PolylineDetails;
import java.awt.Polygon;
import com.eventim.common.transfer.saalplan.PolygonDetails;
import java.awt.geom.Rectangle2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import com.eventim.common.transfer.saalplan.EllipseDetails;
import java.awt.BasicStroke;
import java.awt.Stroke;
import java.awt.Color;

public final class h extends q
{
    private static Color n;
    private static Color o;
    public static final Stroke a;
    public static final Color b;
    public static final Color c;
    public static final Stroke d;
    public static final Color e;
    public static final Color f;
    
    static {
        h.n = new Color(0.3f, 0.9f, 0.3f, 0.9f);
        e = new Color(0.85f, 0.9f, 0.85f, 0.9f);
        b = new Color(0.9f, 0.9f, 0.9f, 0.9f);
        h.o = new Color(0.1f, 0.0f, 0.0f);
        f = new Color(0.1f, 0.0f, 0.0f);
        c = new Color(0.1f, 0.0f, 0.0f);
        new Color(0.2f, 0.1f, 0.1f, 0.9f);
        a = new BasicStroke(2.0f, 1, 1);
        new BasicStroke(2.0f, 1, 1);
        d = new BasicStroke(1.5f, 1, 1);
        new BasicStroke(1.25f, 1, 2, 10.0f, new float[] { 10.0f }, 0.0f);
    }
    
    public final k a(final EllipseDetails ellipseDetails) {
        final Rectangle2D b = com.eventim.applet.a.n.b(ellipseDetails.coords);
        final Ellipse2D.Double double1 = new Ellipse2D.Double(b.getX(), b.getY(), q.a(b.getWidth()), q.a(b.getHeight()));
        if (ellipseDetails.zielabschnittNr > 0) {
            return new p(new j(double1, h.a, ellipseDetails.prio, h.n, h.o), ellipseDetails.zielabschnittNr);
        }
        if (ellipseDetails.filled) {
            return new j(double1, q.l, ellipseDetails.prio, new Color(ellipseDetails.bgColor), new Color(ellipseDetails.lineColor));
        }
        return new o(double1, q.l, ellipseDetails.prio, new Color(ellipseDetails.bgColor), new Color(ellipseDetails.lineColor));
    }
    
    public final k a(final PolygonDetails polygonDetails) {
        final int[] array = new int[polygonDetails.coords.length];
        final int[] array2 = new int[polygonDetails.coords.length];
        for (int i = 0; i < polygonDetails.coords.length; ++i) {
            array[i] = polygonDetails.coords[i].x;
            array2[i] = polygonDetails.coords[i].y;
        }
        final Polygon polygon = new Polygon(array, array2, polygonDetails.coords.length);
        if (polygonDetails.zielabschnittNr > 0) {
            return new p(new j(polygon, h.a, polygonDetails.prio, h.n, h.o), polygonDetails.zielabschnittNr);
        }
        if (polygonDetails.filled) {
            return new j(polygon, q.l, polygonDetails.prio, new Color(polygonDetails.bgColor), new Color(polygonDetails.lineColor));
        }
        return new o(polygon, q.l, polygonDetails.prio, new Color(polygonDetails.bgColor), new Color(polygonDetails.lineColor));
    }
    
    public final k a(final PolylineDetails polylineDetails) {
        final GeneralPath generalPath;
        (generalPath = new GeneralPath(0, polylineDetails.coords.length)).moveTo(polylineDetails.coords[0].x, polylineDetails.coords[0].y);
        for (int i = 1; i < polylineDetails.coords.length; ++i) {
            generalPath.lineTo(polylineDetails.coords[i].x, polylineDetails.coords[i].y);
        }
        if (polylineDetails.zielabschnittNr >= 0) {
            return new p(new o(generalPath, h.a, polylineDetails.prio, null, new Color(polylineDetails.lineColor)), polylineDetails.zielabschnittNr);
        }
        return new o(generalPath, q.l, polylineDetails.prio, null, new Color(polylineDetails.lineColor));
    }
    
    public final k a(final RechteckDetails rechteckDetails) {
        final Rectangle a = com.eventim.applet.a.n.a(rechteckDetails.coords);
        if (rechteckDetails.zielabschnittNr >= 0) {
            return new p(new j(a, h.a, rechteckDetails.prio, h.n, h.o), rechteckDetails.zielabschnittNr);
        }
        if (rechteckDetails.filled) {
            return new j(a, q.l, rechteckDetails.prio, new Color(rechteckDetails.bgColor), new Color(rechteckDetails.lineColor));
        }
        return new o(a, q.l, rechteckDetails.prio, new Color(rechteckDetails.bgColor), new Color(rechteckDetails.lineColor));
    }
    
    public final k a(final ReiheDetails reiheDetails) {
        return this.a((TextDetails)reiheDetails);
    }
    
    public final k a(final SitzDetails sitzDetails) {
        final boolean b = sitzDetails.buchbar != null && sitzDetails.buchbar.length > 0;
        final Rectangle2D b2 = com.eventim.applet.a.n.b(sitzDetails.coords);
        final Ellipse2D.Double double1 = new Ellipse2D.Double(b2.getX(), b2.getY(), q.a(b2.getWidth()), q.a(b2.getHeight()));
        if (b) {
            final Integer a;
            return new f(sitzDetails.id, sitzDetails.sitzgruppeId, sitzDetails.tdlPkid, sitzDetails.tdlVgLfndNr, sitzDetails.seqNo, sitzDetails.bereich, sitzDetails.eingang, sitzDetails.reihe, sitzDetails.platz, sitzDetails.buchbar, double1, q.l, sitzDetails.prio, ((a = EventimApplet.a(sitzDetails.tdlPkid)) != null) ? new Color(Farbverwaltung.getFarbeForPkNr(a)) : Color.white, Color.black);
        }
        return new f(sitzDetails.id, sitzDetails.sitzgruppeId, sitzDetails.tdlPkid, sitzDetails.tdlVgLfndNr, sitzDetails.seqNo, sitzDetails.bereich, sitzDetails.eingang, sitzDetails.reihe, sitzDetails.platz, sitzDetails.buchbar, double1, q.m, sitzDetails.prio, Color.lightGray, Color.black);
    }
    
    public final c a(final StehplatzbereichDetails stehplatzbereichDetails) {
        if (stehplatzbereichDetails.linkObjekt != null) {
            stehplatzbereichDetails.linkObjekt.prio = stehplatzbereichDetails.prio;
        }
        final k a;
        if ((a = this.a(stehplatzbereichDetails.linkObjekt)) == null) {
            return null;
        }
        final Integer a2;
        return new c(stehplatzbereichDetails.id, stehplatzbereichDetails.tdlPkid, stehplatzbereichDetails.tdlVgLfndNr, stehplatzbereichDetails.block, stehplatzbereichDetails.eingang, stehplatzbereichDetails.anzFreiProPrId, stehplatzbereichDetails.prio, new k[] { a }, ((a2 = EventimApplet.a(stehplatzbereichDetails.tdlPkid)) != null) ? new Color(Farbverwaltung.getFarbeForPkNr(a2)) : Color.white, Color.black);
    }
    
    public final d a(final TextDetails textDetails) {
        if (textDetails.text == null) {
            return null;
        }
        if (textDetails.logicalFont == 5 && !com.eventim.applet.k.a()) {
            return null;
        }
        final int n = (textDetails.bold ? 1 : 0) | (textDetails.italic ? 2 : 0);
        String s = "SansSerif";
        if (textDetails.logicalFont == 5) {
            s = "Wingdings";
        }
        else if (textDetails.logicalFont == 4) {
            s = "Webdings";
        }
        else if (textDetails.logicalFont == 2) {
            s = "Serif";
        }
        else if (textDetails.logicalFont == 3) {
            s = "Monospaced";
        }
        final Font font = new Font(s, n, Math.round(textDetails.size * 0.7f));
        final TextDetails[] a;
        final int[] array = new int[(a = com.eventim.applet.a.n.a(textDetails)).length];
        final int[] array2 = new int[a.length];
        final String[] array3 = new String[a.length];
        for (int i = 0; i < a.length; ++i) {
            final TextDetails textDetails2 = a[i];
            array[i] = textDetails2.coords[0].x;
            array2[i] = textDetails2.coords[0].y;
            array3[i] = textDetails2.text;
        }
        return new d(array3, font, array, array2, new Color(textDetails.bgColor), new Color(textDetails.textColor));
    }
}
