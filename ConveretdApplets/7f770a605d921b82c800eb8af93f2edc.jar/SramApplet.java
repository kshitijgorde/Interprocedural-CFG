import jfig.canvas.FigDrawable;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import jfig.objects.FigCompound;
import java.util.Enumeration;
import jfig.objects.FigObject;
import java.io.InputStream;
import java.awt.Component;
import java.net.URL;
import jfig.canvas.ObjectPainter;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.MouseListener;
import jfig.canvas.FigDrawableEnumerator;
import jfig.canvas.FigSwingCanvas;
import jfig.objects.FigObjectList;
import jfig.objects.FigParser;
import jfig.objects.FigAttribs;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SramApplet extends Applet
{
    public static final int DATA_IN = 8;
    public static final int WORDLINE = 4;
    public static final int WRITE_ENABLE = 2;
    public static final Color VCC;
    public static final Color GND;
    public static final Color Z;
    public static final Color OFF;
    FigAttribs attribs;
    FigParser parser;
    FigObjectList objectList;
    FigSwingCanvas objectCanvas;
    int state;
    
    public void init() {
        this.attribs = new FigAttribs();
        this.objectList = new FigObjectList();
        this.parser = new FigParser();
        (this.objectCanvas = new FigSwingCanvas()).showRulers(false);
        this.objectCanvas.getTrafo().setGridMode(0);
        this.objectCanvas.getTrafo().setSnapRelative(1);
        final FigSwingCanvas objectCanvas = this.objectCanvas;
        if (this == null) {
            throw null;
        }
        objectCanvas.setObjectEnumerator(new ObjectEnumerator());
        final Component component = this.objectCanvas.getComponent();
        if (this == null) {
            throw null;
        }
        component.addMouseListener(new MouseHandler());
        this.setLayout(new BorderLayout());
        this.add("Center", this.objectCanvas.getComponent());
        this.parser.setObjectPainter(this.objectCanvas);
        try {
            final URL url = new URL(this.getCodeBase(), "sram.fig");
            final InputStream openStream = url.openStream();
            this.parser.setFilenameAndType(url.toExternalForm(), "URL");
            this.parser.parse_fig_file_not_threaded(openStream, true, false, false, this.attribs, this.objectCanvas.getTrafo(), this.objectList);
            System.out.println("-#- parser ok, repainting");
            this.objectCanvas.doFullRedraw();
        }
        catch (Throwable t) {
            System.out.println("-F- " + t);
            t.printStackTrace();
        }
        this.state = 14;
        this.update();
        this.state ^= 0x8;
        this.update();
    }
    
    public FigObject findObjectViaComment(final String s) {
        final Enumeration elements = this.objectList.elements();
        while (elements.hasMoreElements()) {
            final FigObject figObject = elements.nextElement();
            if (s.equals(figObject.getComment())) {
                return figObject;
            }
        }
        return null;
    }
    
    public void setColor(final String s, final Color lineColor) {
        final FigObject objectViaComment = this.findObjectViaComment(s);
        if (objectViaComment == null) {
            return;
        }
        if (objectViaComment instanceof FigCompound) {
            final FigAttribs attributes = objectViaComment.getAttributes();
            attributes.lineColor = lineColor;
            attributes.fillColor = lineColor;
            FigAttribs.updateLineColorMask = true;
            FigAttribs.updateFillColorMask = true;
            objectViaComment.update(attributes);
        }
        else {
            final FigAttribs attributes2 = objectViaComment.getAttributes();
            attributes2.lineColor = lineColor;
            objectViaComment.setAttributes(attributes2);
        }
    }
    
    public void showTran(final String s, final Color color) {
        final FigObject objectViaComment = this.findObjectViaComment(s);
        if (objectViaComment == null) {
            return;
        }
        final FigAttribs attributes = objectViaComment.getAttributes();
        if (color == SramApplet.VCC) {
            attributes.lineColor = SramApplet.VCC;
            attributes.fillColor = SramApplet.VCC;
            attributes.fillStyle = 2;
        }
        else if (color == SramApplet.GND) {
            attributes.lineColor = SramApplet.GND;
            attributes.fillColor = SramApplet.GND;
            attributes.fillStyle = 2;
        }
        else if (color == SramApplet.Z) {
            attributes.lineColor = SramApplet.Z;
            attributes.fillColor = SramApplet.Z;
            attributes.fillStyle = 2;
        }
        else if (color == SramApplet.OFF) {
            attributes.lineColor = SramApplet.OFF;
            attributes.fillColor = SramApplet.OFF;
            attributes.fillStyle = 1;
        }
        objectViaComment.setAttributes(attributes);
    }
    
    public void update() {
        final boolean b = (this.state & 0x8) != 0x0;
        final boolean b2 = (this.state & 0x2) != 0x0;
        final boolean b3 = (this.state & 0x4) != 0x0;
        if (b) {
            this.setColor("datain", SramApplet.VCC);
            this.setColor("ndatain", SramApplet.GND);
        }
        else {
            this.setColor("ndatain", SramApplet.VCC);
            this.setColor("datain", SramApplet.GND);
        }
        if (b3) {
            this.setColor("wordline", SramApplet.VCC);
        }
        else {
            this.setColor("wordline", SramApplet.GND);
        }
        if (b2) {
            this.setColor("WE", SramApplet.VCC);
            this.setColor("nWE", SramApplet.GND);
            if (b) {
                this.setColor("bit", SramApplet.VCC);
                this.showTran("tranD", SramApplet.VCC);
                this.setColor("nbit", SramApplet.GND);
                this.showTran("tranND", SramApplet.GND);
                if (b3) {
                    this.state |= 0x1;
                    this.setColor("out12", SramApplet.VCC);
                    this.setColor("solder12", SramApplet.VCC);
                    this.setColor("out34", SramApplet.GND);
                    this.setColor("solder34", SramApplet.GND);
                    this.showTran("tran1", SramApplet.VCC);
                    this.showTran("tran2", SramApplet.OFF);
                    this.showTran("tran3", SramApplet.OFF);
                    this.showTran("tran4", SramApplet.GND);
                    this.showTran("tran5", SramApplet.VCC);
                    this.showTran("tran6", SramApplet.GND);
                }
                else {
                    this.showTran("tran5", SramApplet.OFF);
                    this.showTran("tran6", SramApplet.OFF);
                }
            }
            else {
                this.setColor("bit", SramApplet.GND);
                this.showTran("tranD", SramApplet.GND);
                this.setColor("nbit", SramApplet.VCC);
                this.showTran("tranND", SramApplet.VCC);
                if (b3) {
                    this.state &= 0xE;
                    this.setColor("out12", SramApplet.GND);
                    this.setColor("solder12", SramApplet.GND);
                    this.setColor("out34", SramApplet.VCC);
                    this.setColor("solder34", SramApplet.VCC);
                    this.showTran("tran1", SramApplet.OFF);
                    this.showTran("tran2", SramApplet.GND);
                    this.showTran("tran3", SramApplet.VCC);
                    this.showTran("tran4", SramApplet.OFF);
                    this.showTran("tran5", SramApplet.GND);
                    this.showTran("tran6", SramApplet.VCC);
                }
                else {
                    this.showTran("tran5", SramApplet.OFF);
                    this.showTran("tran6", SramApplet.OFF);
                }
            }
        }
        else {
            this.setColor("WE", SramApplet.GND);
            this.setColor("nWE", SramApplet.VCC);
            this.showTran("tranD", SramApplet.OFF);
            this.showTran("tranND", SramApplet.OFF);
            if (b3) {
                if ((this.state & 0x1) != 0x0) {
                    this.showTran("tran5", SramApplet.VCC);
                    this.showTran("tran6", SramApplet.GND);
                    this.setColor("bit", SramApplet.VCC);
                    this.setColor("nbit", SramApplet.GND);
                }
                else {
                    this.showTran("tran5", SramApplet.GND);
                    this.showTran("tran6", SramApplet.VCC);
                    this.setColor("bit", SramApplet.GND);
                    this.setColor("nbit", SramApplet.VCC);
                }
            }
            else {
                this.showTran("tran5", SramApplet.OFF);
                this.showTran("tran6", SramApplet.OFF);
                this.setColor("bit", SramApplet.Z);
                this.setColor("nbit", SramApplet.Z);
            }
        }
        this.objectCanvas.doFullRedraw();
    }
    
    public void toggleDataIn() {
        this.state ^= 0x8;
        this.update();
    }
    
    public void toggleWordlineEnable() {
        this.state ^= 0x4;
        this.update();
    }
    
    public void toggleWriteEnable() {
        this.state ^= 0x2;
        this.update();
    }
    
    public static void main(final String[] array) {
    }
    
    public SramApplet() {
        this.state = 0;
        System.out.println("-#- <init>");
    }
    
    static {
        VCC = Color.red;
        GND = Color.blue;
        Z = Color.orange;
        OFF = Color.black;
    }
    
    public class MouseHandler extends MouseAdapter
    {
        public void mousePressed(final MouseEvent mouseEvent) {
            final Point worldCoords = SramApplet.this.objectCanvas.getTrafo().getWorldCoords(mouseEvent.getX(), mouseEvent.getY());
            if (worldCoords.x < 0 || worldCoords.x >= 2500) {
                return;
            }
            if (worldCoords.y >= 0 && worldCoords.y <= 2800) {
                SramApplet.this.toggleDataIn();
            }
            else if (worldCoords.y >= 2800 && worldCoords.y <= 5000) {
                SramApplet.this.toggleWordlineEnable();
            }
            else {
                if (worldCoords.y < 5000 || worldCoords.y > 6600) {
                    return;
                }
                SramApplet.this.toggleWriteEnable();
            }
        }
    }
    
    public class ObjectEnumerator implements FigDrawableEnumerator
    {
        public Enumeration getDrawableObjects() {
            return SramApplet.this.objectList.elements();
        }
        
        public FigDrawable getTmpObject() {
            return null;
        }
    }
}
