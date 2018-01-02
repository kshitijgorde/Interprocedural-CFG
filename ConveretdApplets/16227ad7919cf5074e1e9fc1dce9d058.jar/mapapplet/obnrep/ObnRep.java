// 
// Decompiled by Procyon v0.5.30
// 

package mapapplet.obnrep;

import java.awt.event.MouseEvent;
import mapapplet.PanelButton;
import java.io.IOException;
import java.io.FileNotFoundException;
import mapapplet.obnrep.repevents.RepEvent;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.zip.GZIPInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.awt.Color;
import java.awt.Graphics;
import mapapplet.Main;
import java.util.Hashtable;
import java.util.Vector;
import mapapplet.MapProjection;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import mapapplet.Module;

public class ObnRep implements Module, MouseListener, MouseMotionListener
{
    private static final String name = "obnrep";
    Object parent;
    protected MapProjection prj;
    public boolean isInitialised;
    private boolean isEnabled;
    private Vector events;
    private Event defEvent;
    private Hashtable colors;
    
    public ObnRep(final Object par) {
        this.isEnabled = false;
        this.parent = par;
        this.defEvent = null;
        this.isInitialised = false;
        (this.colors = new Hashtable()).put("OBN", new int[] { 16571646, 16561406, 16551166, 16540926, 16530686 });
        this.colors.put("USGS", new int[] { 14483172, 12386020, 10288868, 8191716, 6094564 });
        this.colors.put("EMSC", new int[] { 16568711, 16704954, 16642264, 16774628, 16580323 });
        this.colors.put("PDE-Q", new int[] { 16776168, 16776136, 16776104, 16776088, 16776056 });
        this.colors.put("PDE-W", new int[] { 16776168, 16776136, 16776104, 16776088, 16776056 });
        this.colors.put("PDE", new int[] { 16776168, 16776136, 16776104, 16776088, 16776056 });
        ((Main)this.parent).toolbar.addButton(((Main)this.parent).getCurLocation() + "map_images/icon_pointer_2.gif", ((Main)this.parent).getCurLocation() + "map_images/icon_pointer_1.gif", "Select", "obnrep", true, "Mode");
    }
    
    public String name() {
        return "obnrep";
    }
    
    public void paint(final Graphics g) {
        if (this.isInitialised && this.events != null) {
            for (int i = 0; i < this.events.size(); ++i) {
                final Event ev = this.events.elementAt(i);
                this.drawEarthquake(ev, false, g);
            }
            if (this.defEvent != null) {
                this.drawEarthquake(this.defEvent, true, g);
            }
        }
    }
    
    private void drawEarthquake(final Event ev, final boolean isSelected, final Graphics g) {
        final int ix = ev.getX();
        final int iy = ev.getY();
        final int[] colorsAr = this.colors.get(ev.getService());
        if (null != colorsAr) {
            int circleSize = 4;
            if (ix > 0 && iy > 0 && ix < ((Main)this.parent).map.getSize().width && iy < ((Main)this.parent).map.getSize().height) {
                circleSize = 4;
                g.setColor(new Color(colorsAr[0]));
                if (ev.getMagnitude() > 3.0f && ev.getMagnitude() <= 5.0f) {
                    circleSize = 8;
                    g.setColor(new Color(colorsAr[1]));
                }
                if (ev.getMagnitude() > 5.0f && ev.getMagnitude() <= 7.0f) {
                    circleSize = 12;
                    g.setColor(new Color(colorsAr[2]));
                }
                if (ev.getMagnitude() > 7.0f && ev.getMagnitude() <= 9.0f) {
                    circleSize = 16;
                    g.setColor(new Color(colorsAr[3]));
                }
                if (ev.getMagnitude() > 9.0f) {
                    circleSize = 20;
                    g.setColor(new Color(colorsAr[4]));
                }
                if (isSelected) {
                    g.setColor(Color.red);
                }
                g.fillOval(ix - circleSize / 2, iy - circleSize / 2, circleSize, circleSize);
            }
        }
        else {
            System.err.println("Obnrep error: not fount network " + ev.getService() + " colors.");
        }
    }
    
    public void newMap(final MapProjection proj) {
        this.prj = proj;
        if (this.isInitialised) {
            final Enumeration en = this.events.elements();
            while (en.hasMoreElements()) {
                final Event ev = en.nextElement();
                ev.calculateXY(this.prj);
            }
        }
        ((Main)this.parent).toolbar.pressButton("Select");
        this.readEvents();
        this.defEvent = null;
    }
    
    private void readEvents() {
        try {
            URL imgURL = null;
            try {
                imgURL = new URL(((Main)this.parent).getParameter("obnrep_objpath"));
            }
            catch (MalformedURLException e) {
                System.err.println("malformed URL exception " + e);
                return;
            }
            final InputStream strm = imgURL.openConnection().getInputStream();
            final GZIPInputStream gzis = new GZIPInputStream(strm);
            final ObjectInputStream p = new ObjectInputStream(gzis);
            final Vector inputEventVect = (Vector)p.readObject();
            p.close();
            gzis.close();
            strm.close();
            this.events = new Vector();
            for (int i = 0; i < inputEventVect.size(); ++i) {
                final RepEvent rev = inputEventVect.elementAt(i);
                final Event ev = new Event(rev);
                ev.calculateXY(this.prj);
                this.events.addElement(ev);
            }
        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return;
        }
        catch (IOException ex2) {
            ex2.printStackTrace();
            return;
        }
        catch (ClassNotFoundException ex3) {
            ex3.printStackTrace();
            return;
        }
        this.isInitialised = true;
    }
    
    public void changedButton(final Hashtable buttons) {
        if (buttons.get("Select").checked) {
            this.isEnabled = true;
        }
        else {
            this.isEnabled = false;
        }
        ((Main)this.parent).map.repaint();
    }
    
    public void pressedButton(final PanelButton button) {
    }
    
    public boolean isEnabled() {
        return this.isEnabled;
    }
    
    public boolean isPaintable() {
        return true;
    }
    
    public String queryParam(final String paramName) {
        return null;
    }
    
    public boolean setParam(final String paramName, final String value) {
        if (paramName.compareTo("ObnRep.setDefEvent") == 0) {
            final int num = Main.atoi(Main.getToken(value, 1));
            final String service = Main.getToken(value, 2);
            for (int i = 0; i < this.events.size(); ++i) {
                final Event evt = this.events.elementAt(i);
                if (evt.getNumber() == num && evt.getService().compareTo(service) == 0) {
                    this.defEvent = evt;
                }
            }
            this.repaintMap();
            return true;
        }
        return false;
    }
    
    public boolean call(final String methodName) {
        if (methodName.compareTo("ObnRep.reloadEvents") == 0) {
            this.readEvents();
            this.defEvent = null;
            this.repaintMap();
            return true;
        }
        return false;
    }
    
    public void mouseClicked(final MouseEvent e) {
        if (e.getComponent().getName().compareTo("Map") == 0 && this.isEnabled() && this.isInitialised && (e.getModifiers() & 0x10) != 0x0) {
            this.defEvent = this.selectNearestEvents(e.getX(), e.getY());
            if (this.defEvent != null) {
                this.repaintMap();
                ((Main)this.parent).win.eval("selectEvent(\"" + this.defEvent.getNumber() + " " + this.defEvent.getService() + "\");");
            }
        }
    }
    
    public void mousePressed(final MouseEvent e) {
    }
    
    public void mouseReleased(final MouseEvent e) {
    }
    
    public void mouseEntered(final MouseEvent e) {
    }
    
    public void mouseExited(final MouseEvent e) {
    }
    
    public void mouseDragged(final MouseEvent e) {
    }
    
    public void mouseMoved(final MouseEvent e) {
    }
    
    public void repaintMap() {
        ((Main)this.parent).map.repaint();
    }
    
    private Event selectNearestEvents(final int x, final int y) {
        Event nearst = null;
        final float[] ll = this.prj.xy2ll(x, y);
        float mindist = Float.MAX_VALUE;
        for (int j = 0; j < this.events.size(); ++j) {
            final Event stan = this.events.elementAt(j);
            final float dist = stan.getDistance(ll[0], ll[1]);
            if (dist < mindist) {
                mindist = dist;
                nearst = stan;
            }
        }
        return nearst;
    }
}
