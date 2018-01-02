// 
// Decompiled by Procyon v0.5.30
// 

package modules.bsx;

import java.util.Enumeration;
import java.util.Vector;
import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Polygon;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Hashtable;
import java.awt.Frame;

public class BSXDisplay extends Frame
{
    private int Scale;
    private Hashtable scenes;
    private Hashtable objects;
    private String curScene;
    private Image imageBuffer;
    private Graphics gBuffer;
    
    public BSXDisplay() {
        this("BSXModule - Visit Regenbogen rb.mud.de:4780");
    }
    
    public BSXDisplay(final String title) {
        super(title);
        this.Scale = 100;
        this.curScene = "";
        this.addNotify();
        this.imageBuffer = this.createImage(511, 255);
        if (this.imageBuffer == null) {
            System.out.println("Couldn't create an offscreen-image :(");
            System.exit(1);
        }
        this.gBuffer = this.imageBuffer.getGraphics();
        this.scenes = new Hashtable();
        this.objects = new Hashtable();
        this.setScale(100);
        this.resize(510, 255);
        this.show();
    }
    
    public Point Translate(final int x, final int y, final int centreX, final int centreY, final int scale) {
        return new Point((x - 127 + centreX) * 2 * scale / 100, (255 - (y - 127 + centreY)) * scale / 100);
    }
    
    public void addObject(final String id, final BSXGraphic o) {
        this.objects.put(id, o);
        final BSXScene scene = this.scenes.get(this.curScene);
        if (scene != null && scene.objects.containsKey(id)) {
            this.redraw();
        }
    }
    
    public void addScene(final String id, final BSXGraphic picture) {
        final BSXScene scene = new BSXScene(picture);
        final BSXScene sc = this.scenes.get(id);
        if (sc != null) {
            scene.objects = sc.objects;
            this.scenes.remove(id);
        }
        this.scenes.put(id, scene);
        if (this.curScene.equals(id)) {
            this.redraw();
        }
    }
    
    public void drawPicture(final BSXGraphic pic) {
        this.drawPicture(pic, 127, 127);
    }
    
    public void drawPicture(final BSXGraphic pic, final int position, final int layer) {
        if (pic == null) {
            return;
        }
        for (int ap = ((Vector)pic).size(), i = 0; i < ap; ++i) {
            final BSXPolygon poly = ((Vector<BSXPolygon>)pic).elementAt(i);
            final Polygon p = new Polygon();
            this.gBuffer.setColor(poly.getColor());
            for (int j = ((Polygon)poly).npoints - 1; j >= 0; --j) {
                final Point pt = this.Translate(((Polygon)poly).xpoints[j], ((Polygon)poly).ypoints[j], position, layer, this.Scale);
                p.addPoint(pt.x, pt.y);
            }
            this.gBuffer.fillPolygon(p);
        }
    }
    
    public boolean mouseEnter(final Event evt, final int x, final int y) {
        this.requestFocus();
        return true;
    }
    
    public void paint(final Graphics g) {
        g.drawImage(this.imageBuffer, 0, 0, this);
    }
    
    public void redraw() {
        final BSXScene scene = this.scenes.get(this.curScene);
        if (scene == null) {
            return;
        }
        final Vector[] layer = new Vector[8];
        final Enumeration e = scene.objects.keys();
        while (e.hasMoreElements()) {
            final String key = e.nextElement();
            final BSXObject o = scene.objects.get(key);
            if (layer[o.layer] == null) {
                layer[o.layer] = new Vector();
            }
            layer[o.layer].addElement(key);
        }
        if (scene.background != null && ((Vector)scene.background).size() > 0) {
            this.drawPicture(scene.background);
            for (int l = 7; l >= 0; --l) {
                if (layer[l] != null) {
                    for (int o2 = layer[l].size() - 1; o2 >= 0; --o2) {
                        final BSXObject obj = scene.objects.get(layer[l].elementAt(o2));
                        this.drawPicture(this.objects.get(layer[l].elementAt(o2)), 16 * obj.position, 4 * obj.layer);
                    }
                }
            }
            this.paint(this.getGraphics());
        }
    }
    
    public boolean removeObject(final String id) {
        final BSXScene scene = this.scenes.get(this.curScene);
        if (scene != null) {
            final BSXObject o = scene.objects.get(id);
            scene.objects.remove(id);
            if (o != null) {
                this.redraw();
            }
        }
        return true;
    }
    
    public void setScale(final int scale) {
        this.Scale = scale;
        this.resize(511 * this.Scale / 100, 255 * this.Scale / 100);
    }
    
    public boolean showObject(final String id) {
        return this.showObject(id, 0, 0);
    }
    
    public boolean showObject(final String id, final int position, final int layer) {
        BSXScene scene = this.scenes.get(this.curScene);
        if ((scene = this.scenes.get(this.curScene)) == null) {
            this.addScene(this.curScene, new BSXGraphic());
            scene = this.scenes.get(this.curScene);
        }
        final BSXObject o = new BSXObject(position, layer);
        scene.objects.put(id, o);
        if (!this.objects.containsKey(id)) {
            return false;
        }
        this.redraw();
        return true;
    }
    
    public boolean showScene() {
        return this.showScene(this.curScene);
    }
    
    public boolean showScene(final String id) {
        this.curScene = id;
        if (!this.scenes.containsKey(id)) {
            this.addScene(id, new BSXGraphic());
            return false;
        }
        this.redraw();
        return true;
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
}
