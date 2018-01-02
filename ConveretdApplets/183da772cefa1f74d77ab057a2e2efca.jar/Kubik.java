import java.awt.Event;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Vector;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Kubik extends Applet implements Runnable
{
    private Thread thread;
    ParameterUtilities parameterUtilities;
    private FixedCamera camera;
    private Vector wireframeVector;
    private Dimension appletDimension;
    private Color foreground;
    private Color background;
    private int dxangle;
    private int dyangle;
    private int dzangle;
    private int delay;
    private int prev_x;
    private int prev_y;
    
    private int getAngleParameter(final String s, final int n) {
        final String parameter = this.getParameter(s);
        int int1;
        if (parameter == null) {
            int1 = n;
        }
        else if (parameter.equalsIgnoreCase("RANDOM")) {
            int1 = (int)(Math.random() * 360.0);
        }
        else {
            int1 = Integer.parseInt(parameter, 10);
        }
        return int1 * 256 / 360;
    }
    
    public void init() {
        this.appletDimension = this.size();
        this.parameterUtilities = new ParameterUtilities(this);
        String parameter;
        if ((parameter = this.getParameter("text")) == null) {
            parameter = "KUBIK";
        }
        final int length;
        this.wireframeVector = new Vector(length = parameter.length());
        int n = 0;
        int n2 = 0;
        int max = 0;
        int spacewidth = 0;
        final int integerParameter = this.parameterUtilities.getIntegerParameter("zpos", 0);
        final int integerParameter2 = this.parameterUtilities.getIntegerParameter("dzpos", 0);
        int n3 = integerParameter;
        int angleParameter = this.getAngleParameter("x0", 0);
        int angleParameter2 = this.getAngleParameter("y0", 0);
        int angleParameter3 = this.getAngleParameter("z0", 0);
        final int angleParameter4 = this.getAngleParameter("dx0", 15);
        final int angleParameter5 = this.getAngleParameter("dy0", 45);
        final int angleParameter6 = this.getAngleParameter("dz0", 0);
        for (int i = 0; i < length; ++i) {
            final char char1 = parameter.charAt(i);
            if (char1 == ' ') {
                n += (spacewidth + KubikFont.SPACEWIDTH) / 2 + KubikFont.SPACEWIDTH;
                spacewidth = KubikFont.SPACEWIDTH;
                n3 += integerParameter2;
            }
            else if (char1 == '|') {
                max = Math.max(max, n + spacewidth / 2 - KubikFont.SPACEWIDTH);
                n = 0;
                n2 += KubikFont.LINEHEIGHT;
                n3 = integerParameter;
                spacewidth = 0;
            }
            else {
                final int width = KubikFont.getWidth(char1);
                final int n4 = n + (spacewidth + width) / 2;
                spacewidth = width;
                this.wireframeVector.addElement(new Wireframe(KubikFont.getLocal(char1), KubikFont.getSurface(parameter.charAt(i)), n4, n2, n3, angleParameter, angleParameter2, angleParameter3));
                n = n4 + KubikFont.SPACEWIDTH;
                n3 += integerParameter2;
                angleParameter += angleParameter4;
                angleParameter2 += angleParameter5;
                angleParameter3 += angleParameter6;
            }
        }
        this.camera = new FixedCamera(Math.max(max, n + spacewidth / 2 - KubikFont.SPACEWIDTH) / 2, n2 / 2, -this.parameterUtilities.getIntegerParameter("camera distance", 10000), this.appletDimension.width, this.appletDimension.height, this.parameterUtilities.getIntegerParameter("screen distance", 5000));
        this.setForeground(this.foreground = this.parameterUtilities.getColorParameter("foreground", Color.green));
        this.setBackground(this.background = this.parameterUtilities.getColorParameter("background", Color.black));
        this.dxangle = this.getAngleParameter("dx", -2);
        this.dyangle = this.getAngleParameter("dy", -10);
        this.dzangle = this.getAngleParameter("dz", 0);
        this.delay = 10 * this.parameterUtilities.getIntegerParameter("delay", 10);
        for (int j = 0; j < this.wireframeVector.size(); ++j) {
            this.camera.addWireframe((Wireframe)this.wireframeVector.elementAt(j));
        }
    }
    
    public void run() {
        while (true) {
            for (int i = 0; i < this.wireframeVector.size(); ++i) {
                ((Wireframe)this.wireframeVector.elementAt(i)).rotate(this.dxangle, this.dyangle, this.dzangle);
            }
            this.repaint();
            try {
                Thread.sleep(this.delay);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void update(final Graphics graphics) {
        final Image image = this.createImage(this.appletDimension.width, this.appletDimension.height);
        final Graphics graphics2 = image.getGraphics();
        graphics2.setColor(this.background);
        graphics2.fillRect(0, 0, this.appletDimension.width, this.appletDimension.height);
        graphics2.setColor(this.foreground);
        this.camera.draw(graphics2);
        graphics.drawImage(image, 0, 0, null);
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "text", "String", "Only 'A' through 'Z' and blanks supported. Use '|' as newline." }, { "foreground", "24 bit RGB (hex. int)/RANDOM/DARK RANDOM/LIGHT RANDOM", "default: green" }, { "background", "24 bit RGB (hex. int)/RANDOM/DARK RANDOM/LIGHT RANDOM", "default: black" }, { "camera distance", "pixels (int)", "default: 10000" }, { "screen distance", "pixels (int)", "default: 5000" }, { "dx", "degrees (int)", "default: -2" }, { "dy", "degrees (int)", "default: -10" }, { "dz", "degrees (int)", "default: 0" }, { "delay", "1/100 seconds (int)", "default: 10" }, { "zpos", "pixels (int)", "default: 0" }, { "dzpos", "pixels (int)", "default: 0" }, { "x0", "degrees (int)", "default: 0" }, { "y0", "degrees (int)", "default: 0" }, { "z0", "degrees (int)", "default: 0" }, { "dx0", "degrees (int)", "default: 15" }, { "dy0", "degrees (int)", "default: 45" }, { "dz0", "degrees (int)", "default: 0" } };
    }
    
    public String getAppletInfo() {
        return "Kubik\n\nauthor: Michael Kraus\nversion: 1.2\ndate: January 1998\nenvironment: JDK 1.0.2\nemail: michael.kraus@informatik.uni-muenchen.de\nhomepage: www.informatik.uni-muenchen.de/~michael.kraus\n";
    }
    
    public void start() {
        if (this.thread == null) {
            (this.thread = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.thread != null) {
            this.thread.stop();
            this.thread = null;
        }
    }
    
    public boolean mouseEnter(final Event event, final int prev_x, final int prev_y) {
        this.stop();
        this.prev_x = prev_x;
        this.prev_y = prev_y;
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.start();
        return true;
    }
    
    public boolean mouseMove(final Event event, final int prev_x, final int prev_y) {
        for (int i = 0; i < this.wireframeVector.size(); ++i) {
            ((Wireframe)this.wireframeVector.elementAt(i)).rotate((prev_y - this.prev_y) * 256 / this.appletDimension.height, (prev_x - this.prev_x) * 256 / this.appletDimension.width, 0);
        }
        this.repaint();
        this.prev_x = prev_x;
        this.prev_y = prev_y;
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int prev_x, final int prev_y) {
        for (int i = 0; i < this.wireframeVector.size(); ++i) {
            ((Wireframe)this.wireframeVector.elementAt(i)).rotate(0, 0, (prev_y - this.prev_y) * 256 / this.appletDimension.height + (prev_x - this.prev_x) * 256 / this.appletDimension.width);
        }
        this.repaint();
        this.prev_x = prev_x;
        this.prev_y = prev_y;
        return true;
    }
}
