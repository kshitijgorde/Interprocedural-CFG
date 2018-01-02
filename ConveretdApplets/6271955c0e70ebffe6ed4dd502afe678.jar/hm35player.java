import java.io.DataInputStream;
import java.applet.Applet;
import java.awt.Image;
import netscape.javascript.JSObject;
import java.awt.Event;
import java.awt.FontMetrics;
import java.util.StringTokenizer;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import java.awt.Rectangle;
import java.util.Vector;
import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class hm35player extends Panel implements Runnable
{
    public static final Font d6;
    public hm35master masterObject;
    public Color d5;
    public boolean d4;
    public byte trackID;
    public byte mediaType;
    public byte mediaSubType;
    public int d3;
    public Thread d2;
    public byte d1;
    public Vector d0;
    public Rectangle d_;
    public int dz;
    public int dy;
    public short dx;
    public Object dw;
    public Object[] dv;
    public float[] du;
    public int[] dt;
    
    static {
        d6 = new Font("default", 0, 12);
    }
    
    public hm35player() {
        this.d1 = 1;
        this.d0 = new Vector();
        this.dv = new Object[4];
        this.du = new float[2];
        this.dt = new int[7];
    }
    
    public final boolean p() {
        this.dv[0] = new Integer(259);
        this.dv[1] = this;
        final int[] dt = this.dt;
        final int n = 0;
        final int[] dt2 = this.dt;
        final int n2 = 1;
        final int[] dt3 = this.dt;
        final int n3 = 2;
        final int[] dt4 = this.dt;
        final int n4 = 3;
        final int[] dt5 = this.dt;
        final int n5 = 4;
        final int[] dt6 = this.dt;
        final int n6 = 5;
        final int[] dt7 = this.dt;
        final int n7 = 6;
        final boolean b = false;
        dt7[n7] = (b ? 1 : 0);
        dt5[n5] = (dt6[n6] = (b ? 1 : 0));
        dt3[n3] = (dt4[n4] = (b ? 1 : 0));
        dt[n] = (dt2[n2] = (b ? 1 : 0));
        this.dv[2] = this.dt;
        this.dv[3] = null;
        ((Observer)this.dw).update(null, this.dv);
        return this.dt[0] == 1;
    }
    
    public final void q(final Object o, final boolean b, final float n) {
        if (o != null) {
            this.dv[0] = new Integer(258);
            this.dv[1] = o;
            this.dt[0] = (b ? 0 : 1);
            final int[] dt = this.dt;
            final int n2 = 1;
            final int[] dt2 = this.dt;
            final int n3 = 2;
            final int[] dt3 = this.dt;
            final int n4 = 3;
            final int[] dt4 = this.dt;
            final int n5 = 4;
            final int[] dt5 = this.dt;
            final int n6 = 5;
            final int[] dt6 = this.dt;
            final int n7 = 6;
            final boolean b2 = false;
            dt5[n6] = (dt6[n7] = (b2 ? 1 : 0));
            dt3[n4] = (dt4[n5] = (b2 ? 1 : 0));
            dt[n2] = (dt2[n3] = (b2 ? 1 : 0));
            this.du[0] = n;
            this.du[1] = 0.0f;
            this.dv[2] = this.dt;
            this.dv[3] = this.du;
            ((Observer)this.dw).update(null, this.dv);
        }
    }
    
    public final long r(final Object o, final boolean b) {
        long longValue = -1L;
        if (o != null) {
            this.dv[0] = new Integer(256);
            this.dv[1] = o;
            this.dt[0] = (b ? 0 : 1);
            final int[] dt = this.dt;
            final int n = 1;
            final int[] dt2 = this.dt;
            final int n2 = 2;
            final int[] dt3 = this.dt;
            final int n3 = 3;
            final int[] dt4 = this.dt;
            final int n4 = 4;
            final int[] dt5 = this.dt;
            final int n5 = 5;
            final int[] dt6 = this.dt;
            final int n6 = 6;
            final boolean b2 = false;
            dt5[n5] = (dt6[n6] = (b2 ? 1 : 0));
            dt3[n3] = (dt4[n4] = (b2 ? 1 : 0));
            dt[n] = (dt2[n2] = (b2 ? 1 : 0));
            this.dv[2] = this.dt;
            this.dv[3] = null;
            ((Observer)this.dw).update(null, this.dv);
            longValue = (long)this.dv[1];
        }
        return longValue;
    }
    
    public final void s(final Object o) {
        if (o != null) {
            this.dv[0] = new Integer(4);
            this.dv[1] = o;
            this.dv[2] = (this.dv[3] = null);
            ((Observer)this.dw).update(null, this.dv);
        }
    }
    
    public final void t(final Object o) {
        if (o != null) {
            this.dv[0] = new Integer(3);
            this.dv[1] = o;
            this.dv[2] = (this.dv[3] = null);
            ((Observer)this.dw).update(null, this.dv);
        }
    }
    
    public final void u(final Object o) {
        if (o != null) {
            this.dv[0] = new Integer(2);
            this.dv[1] = o;
            this.dv[2] = (this.dv[3] = null);
            ((Observer)this.dw).update(null, this.dv);
        }
    }
    
    public final void v(final Object o) {
        if (o != null) {
            this.dv[0] = new Integer(1);
            this.dv[1] = o;
            this.dv[2] = (this.dv[3] = null);
            ((Observer)this.dw).update(null, this.dv);
        }
    }
    
    public final Object w(final Object o, final byte b, final byte b2, final byte b3, final float n, final int n2, final int n3, final int n4, final float n5, final boolean b4) {
        this.dt[0] = b;
        this.dt[1] = b2;
        this.dt[2] = b3;
        this.dt[3] = (b4 ? 0 : 1);
        this.dt[4] = n2;
        this.dt[5] = n3;
        this.dt[6] = n4;
        this.du[0] = n;
        this.du[1] = n5;
        this.dv[0] = new Integer(0);
        this.dv[1] = o;
        this.dv[2] = this.dt;
        this.dv[3] = this.du;
        ((Observer)this.dw).update(null, this.dv);
        return this.dv[1];
    }
    
    public final int x() {
        if (this.dx == 0) {
            try {
                Class.forName("javax.sound.sampled.AudioFormat");
                this.dw = Class.forName("hm35JavaSound").newInstance();
                this.dx = 2;
            }
            catch (Exception ex) {}
            if (this.dx == 0) {
                try {
                    Class.forName("sun.audio.AudioPlayer");
                    this.dw = Class.forName("hm35SunAudio").newInstance();
                    this.dx = 1;
                }
                catch (Exception ex2) {}
            }
        }
        return this.dx;
    }
    
    public final void y() {
        this.setSensitive(this, 1792, 0);
        System.out.println("No audio device available. Free audio device and restart the browser.");
        this.masterObject.ii.showStatus("No audio device available. Free audio device and restart the browser.");
        this.ae("noAudioDevice");
    }
    
    public final int z() {
        return this.dx;
    }
    
    public final void aa(final Graphics graphics, final Object o, final int n, final int n2) {
        if (graphics == null || o == null) {
            return;
        }
        final byte[] array = (byte[])o;
        final int n3 = ((array[10] & 0xFF) << 8 | (array[11] & 0xFF)) & 0xFFFF;
        if (n3 == 0) {
            return;
        }
        final byte b = (byte)(array[0] & 0x3);
        final byte b2 = (byte)((array[1] > 0) ? array[1] : 12);
        final Color color = new Color(array[2], array[3], array[4]);
        final Color color2 = (array.length == n3 + 12) ? Color.white : null;
        final int n4 = (array[6] << 8) + array[7];
        final int n5 = (array[8] << 8) + array[9];
        if (n4 == 0) {}
        if (n5 == 0) {}
        final byte[] array2 = new byte[n3];
        System.arraycopy(array, 12, array2, 0, n3);
        final String ac = this.ac(array2);
        Font d6 = hm35player.d6;
        if ((array[0] & 0x4) == 0x4) {
            int n6 = 0;
            final int n7 = (array[n6++] << 8) + array[n6++];
            d6 = new Font((n7 > 0) ? new String(array, n6, n7) : "default", b, b2);
        }
        this.drawToolTip(graphics, ac, this.ab(graphics, ac, n, n2, d6), d6, color, color2);
    }
    
    public final void drawToolTip(final Graphics graphics, final String s, final Rectangle d_, Font d6, final Color color, final Color color2) {
        if (graphics == null || s == null || d_ == null) {
            return;
        }
        if (color2 != null) {
            graphics.setColor(color2);
            graphics.fill3DRect(d_.x, d_.y, d_.width - 2, d_.height - 2, true);
        }
        graphics.setColor((color == null) ? Color.black : color);
        if (d6 == null) {
            d6 = hm35player.d6;
        }
        graphics.setFont(d6);
        final boolean b = false;
        this.dz = (b ? 1 : 0);
        this.dy = (b ? 1 : 0);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int n = d_.y - fontMetrics.getDescent();
        final int n2 = d_.x + 2;
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n\r");
        stringTokenizer.countTokens();
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken;
            final int stringWidth = fontMetrics.stringWidth((nextToken = stringTokenizer.nextToken()).concat("  "));
            this.dz = ((stringWidth > this.dz) ? stringWidth : this.dz);
            this.dy += fontMetrics.getHeight();
            graphics.drawString(nextToken, n2, n + this.dy);
        }
        this.d_ = d_;
    }
    
    public final Rectangle ab(final Graphics graphics, final String s, final int n, final int n2, Font d6) {
        if (graphics != null && s != null && s.length() > 0) {
            final int width = this.size().width;
            final int height = this.size().height;
            int dz = 0;
            int n3 = 0;
            if (d6 == null) {
                d6 = hm35player.d6;
            }
            graphics.setFont(d6);
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            fontMetrics.getHeight();
            final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n\r");
            while (stringTokenizer.hasMoreTokens()) {
                final int stringWidth = fontMetrics.stringWidth(stringTokenizer.nextToken().concat("  "));
                dz = ((stringWidth > dz) ? stringWidth : dz);
                ++n3;
            }
            final int dy = (fontMetrics.getHeight() + 2) * ((n3 <= 0) ? 1 : n3);
            int n4 = n - dz / 2;
            int n5 = n2 - dy + 3;
            if (n4 + dz > width) {
                n4 = width - dz;
            }
            if (n4 < 0) {
                n4 = 0;
            }
            if (n5 < 0) {
                n5 = 0;
            }
            if (n5 + dy > height) {
                n5 = height - dy;
            }
            this.dy = dy;
            this.dz = dz;
            return new Rectangle(n4, n5, dz, dy);
        }
        return null;
    }
    
    public final void redrawToolTip(final int x, final int y) {
        if (this.d_ != null) {
            final int n = this.d_.x - x;
            final int n2 = this.d_.y - y;
            if (n != 0 || n2 != 0) {
                if (n < 0) {
                    final Rectangle d_ = this.d_;
                    d_.width -= n;
                }
                else {
                    this.d_.x = x;
                    final Rectangle d_2 = this.d_;
                    d_2.width += n;
                }
                if (n2 < 0) {
                    final Rectangle d_3 = this.d_;
                    d_3.height -= n2;
                }
                else {
                    this.d_.y = y;
                    final Rectangle d_4 = this.d_;
                    d_4.height += n2;
                }
                this.repaint(this.d_.x - this.dz, this.d_.y - this.dy, this.d_.width + this.dz, this.d_.height + this.dy);
            }
        }
    }
    
    public final String ac(final byte[] array) {
        return this.masterObject.ac(array);
    }
    
    private final void ad(final int n, final int n2, final byte b) {
        final Vector<Integer> vector = new Vector<Integer>();
        final Vector<Integer> vector2 = new Vector<Integer>();
        vector.addElement(new Integer(n));
        vector2.addElement(new Integer(n2));
        vector.addElement(new Integer(0));
        vector2.addElement(new Integer(n2));
        vector.addElement(new Integer(0));
        vector2.addElement(new Integer(0));
        vector.addElement(new Integer(n));
        vector2.addElement(new Integer(0));
        final Vector handleMediaEvent = this.handleMediaEvent(this, this.trackID, b, 0, 2, vector, vector2);
        for (int n3 = 0; handleMediaEvent != null && n3 < handleMediaEvent.size(); ++n3) {
            this.handleAction(handleMediaEvent.elementAt(n3));
        }
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.ad(n, n2, (byte)100);
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.ad(n, n2, (byte)5);
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.ad(n, n2, (byte)4);
        return true;
    }
    
    public final void ae(final String s) {
        final String parameter = this.masterObject.ii.getParameter(s);
        if (parameter != null) {
            final int index = parameter.indexOf(40);
            final int index2 = parameter.indexOf(41);
            String concat;
            if (index > 0 && index2 > index) {
                concat = parameter;
            }
            else {
                final StringTokenizer stringTokenizer = new StringTokenizer(parameter, "&");
                String s2 = new String(stringTokenizer.nextToken()).concat("(");
                while (stringTokenizer.hasMoreTokens()) {
                    s2 = s2.concat("\"").concat(stringTokenizer.nextToken()).concat(stringTokenizer.hasMoreTokens() ? "\"," : "\"");
                }
                concat = s2.concat(");");
            }
            JSObject.getWindow(this.masterObject.ii).eval(concat);
        }
    }
    
    public int af(final byte[] array, final int n, final int n2) {
        return 0;
    }
    
    public int af() {
        return 0;
    }
    
    public final void temporalTrigger(final byte b, final int n) {
        final Vector handleMediaEvent = this.handleMediaEvent(this, this.trackID, b, n, 0, null, null);
        for (int n2 = 0; handleMediaEvent != null && n2 < handleMediaEvent.size(); ++n2) {
            this.handleAction(handleMediaEvent.elementAt(n2));
        }
    }
    
    public boolean mediaControl(final Object[] array) {
        if (this.trackID != this.ap((String)array[1])) {
            return false;
        }
        boolean b = true;
        this.d0.removeAllElements();
        final StringTokenizer stringTokenizer = new StringTokenizer((String)array[2], "=&");
        while (stringTokenizer.hasMoreTokens()) {
            final String[] array2 = { stringTokenizer.nextToken(), stringTokenizer.nextToken() };
            if (array2[0].equalsIgnoreCase("PlayerState")) {
                if (array2[1].equalsIgnoreCase("fplay")) {
                    this.play(1);
                }
                if (array2[1].equalsIgnoreCase("bplay")) {
                    this.play(-1);
                }
                if (array2[1].equalsIgnoreCase("pause")) {
                    this.pause();
                }
                if (array2[1].equalsIgnoreCase("stop")) {
                    this.play(0);
                }
                if (array2[1].equalsIgnoreCase("mute")) {
                    this.b();
                }
                if (array2[1].equalsIgnoreCase("zoomin")) {
                    this.zoom(true, 1);
                    this.sleep(100L);
                    this.zoom(false, 1);
                }
                if (array2[1].equalsIgnoreCase("zoomout")) {
                    this.zoom(true, -1);
                    this.sleep(100L);
                    this.zoom(false, -1);
                }
                if (array2[1].equalsIgnoreCase("setSpeed") && stringTokenizer.nextToken().equalsIgnoreCase("factor")) {
                    this.al(Double.valueOf(stringTokenizer.nextToken()));
                }
                if (!array2[1].equalsIgnoreCase("setSpeedLimit") || !stringTokenizer.nextToken().equalsIgnoreCase("lower")) {
                    continue;
                }
                final Double value = Double.valueOf(stringTokenizer.nextToken());
                if (!stringTokenizer.nextToken().equalsIgnoreCase("upper")) {
                    continue;
                }
                this.ak(value, Double.valueOf(stringTokenizer.nextToken()));
            }
            else if (array2[0].equalsIgnoreCase("SeekState")) {
                try {
                    if (array2[1].equalsIgnoreCase("seekFrame")) {
                        if (!stringTokenizer.nextToken().equalsIgnoreCase("fnumber")) {
                            continue;
                        }
                        this.d(Integer.parseInt(stringTokenizer.nextToken()));
                    }
                    else {
                        if (!array2[1].equalsIgnoreCase("seekTime") || !stringTokenizer.nextToken().equalsIgnoreCase("time")) {
                            continue;
                        }
                        this.am(Integer.parseInt(stringTokenizer.nextToken()));
                    }
                }
                catch (NumberFormatException ex) {
                    b = false;
                }
            }
            else {
                this.d0.addElement(array2);
            }
        }
        return b;
    }
    
    public final Vector ag() {
        return this.d0;
    }
    
    public final void runDestroy() {
    }
    
    public void runRun() {
    }
    
    public void runPause() {
        this.sleep(200L);
    }
    
    public void runInit() {
    }
    
    public void run() {
        this.runInit();
        byte threadState;
        while ((threadState = this.getThreadState()) != 0) {
            if (threadState == 1) {
                this.runPause();
            }
            if (threadState == 2) {
                this.runRun();
            }
        }
        this.runDestroy();
        this.d2 = null;
    }
    
    public final boolean sleep(final long n) {
        for (long n2 = n; n2 > 0L; n2 -= 50L) {
            if (this.getThreadState() == 0) {
                return true;
            }
            try {
                Thread.sleep((n2 > 50L) ? 50L : n2);
            }
            catch (Exception ex) {
                return false;
            }
        }
        return true;
    }
    
    public final void destroyThread() {
        this.setThreadState((byte)0);
        while (this.d2 != null && this.d2.isAlive()) {
            try {
                this.d2.join(50L);
            }
            catch (Exception ex) {}
        }
        this.d2 = null;
    }
    
    public final void startThread(final String s) {
        if (this.d2 == null) {
            if (s == null) {
                this.d2 = new Thread(this);
            }
            else {
                this.d2 = new Thread(this, s);
            }
            this.d2.start();
        }
        this.setThreadState((byte)2);
    }
    
    public final synchronized void setThreadState(final byte d1) {
        if (this.d1 != 0) {
            this.d1 = d1;
        }
    }
    
    public final byte getThreadState() {
        return this.d1;
    }
    
    public final boolean getBoolParam(final String s, boolean b) {
        final String parameter = this.masterObject.ii.getParameter(s);
        if (parameter != null) {
            if (parameter.equalsIgnoreCase("Y")) {
                b = true;
            }
            if (parameter.equalsIgnoreCase("N")) {
                b = false;
            }
        }
        return b;
    }
    
    public final int ah(final String s, int int1) {
        final String parameter = this.masterObject.ii.getParameter(s);
        if (parameter != null) {
            try {
                int1 = Integer.parseInt(parameter);
            }
            catch (NumberFormatException ex) {}
        }
        return int1;
    }
    
    public void a(final int d3, final int n) {
        if (n == 1) {
            if (d3 != this.d3) {
                this.d(d3);
                this.d3 = d3;
            }
            this.sleep(100L);
            this.play(1);
        }
        if (n == 0) {
            this.pause();
        }
    }
    
    public void ai(final int n) {
        if (this.masterObject.ij != null) {
            this.masterObject.ij.ai(n);
        }
    }
    
    public void aj(final int n, final int n2) {
        if (this.masterObject.ij != null) {
            this.masterObject.ij.aj(n, n2);
        }
    }
    
    public void b() {
        this.d4 = !this.d4;
        this.setSensitive(this, 128, this.d4 ? 0 : 128);
    }
    
    public final double ak(final double n, final double n2) {
        return 0.0;
    }
    
    public final double al(final double n) {
        return 0.0;
    }
    
    public int am(final int n) {
        return 0;
    }
    
    public void d(final int n) {
    }
    
    public void an(final int n, final int n2, final int n3) {
        if (this.masterObject.ij != null) {
            this.masterObject.ij.an(n, n2, n3);
        }
    }
    
    public void select(final hm35player hm35player, final int n) {
        if (this.masterObject.ij != null) {
            this.masterObject.ij.select(hm35player, n);
        }
    }
    
    public void setSensitive(final hm35player hm35player, final int n, final int n2) {
        this.masterObject.ba(hm35player, n, n2);
    }
    
    public void setGUI(final hm35player hm35player, final int n, final int n2) {
        this.masterObject.bb(hm35player, n, n2);
    }
    
    public void position(final int n) {
    }
    
    public int zoom(final boolean b, final int n) {
        return 0;
    }
    
    public int pause() {
        return 0;
    }
    
    public int play(final int n) {
        return 0;
    }
    
    public Object getDisplayRecord(final int n) {
        if (this.masterObject.ik != null) {
            return this.masterObject.ik.getDisplayRecord(n);
        }
        return null;
    }
    
    public int handleAction(final int n) {
        if (this.masterObject.ik != null) {
            return this.masterObject.ik.handleAction(n);
        }
        return -1;
    }
    
    public byte[] getActionData(final int n) {
        if (this.masterObject.ik != null) {
            return this.masterObject.ik.getActionData(n);
        }
        return null;
    }
    
    public int getRangeIndexForAction(final int n) {
        if (this.masterObject.ik != null) {
            return this.masterObject.ik.getRangeIndexForAction(n);
        }
        return -1;
    }
    
    public int getActionDataLength(final int n) {
        if (this.masterObject.ik != null) {
            return this.masterObject.ik.getActionDataLength(n);
        }
        return -1;
    }
    
    public int getActionSubType(final int n) {
        if (this.masterObject.ik != null) {
            return this.masterObject.ik.getActionSubType(n);
        }
        return -1;
    }
    
    public int getActionType(final int n) {
        if (this.masterObject.ik != null) {
            return this.masterObject.ik.getActionType(n);
        }
        return -1;
    }
    
    public Vector getSpatialVector(final int n, final int n2) {
        if (this.masterObject.ik != null) {
            return this.masterObject.ik.getSpatialVector(n, n2);
        }
        return null;
    }
    
    public int getSpatialNumNodes(final int n) {
        if (this.masterObject.ik != null) {
            return this.masterObject.ik.getSpatialNumNodes(n);
        }
        return -1;
    }
    
    public Vector handleMediaEvent(final hm35player hm35player, final byte b, final byte b2, final int n, final int n2, final Vector vector, final Vector vector2) {
        if (this.masterObject.ik != null) {
            return this.masterObject.ik.handleMediaEvent(hm35player, b, b2, n, n2, vector, vector2);
        }
        return null;
    }
    
    public final int getCursorType() {
        return this.masterObject.a6();
    }
    
    public final synchronized void setCursor(final int n) {
        this.masterObject.a7(n);
    }
    
    public final Image createImage(final int n, final byte[] array) {
        return this.masterObject.a8(n, array);
    }
    
    public final Applet getMainApplet() {
        return this.masterObject.ii;
    }
    
    public final void mvrStreaming(final int n) {
        this.masterObject.bq(n);
    }
    
    public boolean endOfData(final byte b) {
        return false;
    }
    
    public abstract int consumeFrame(final byte p0, final int p1, final byte p2, final short p3, final int p4, final int p5, final DataInputStream p6);
    
    private static final byte ao(final String s) {
        if (s.equalsIgnoreCase("Ani")) {
            return 103;
        }
        if (s.equalsIgnoreCase("Pan")) {
            return 119;
        }
        if (s.equalsIgnoreCase("Scr")) {
            return 120;
        }
        if (s.equalsIgnoreCase("ipx")) {
            return 100;
        }
        if (s.equalsIgnoreCase("3Dx")) {
            return 6;
        }
        if (s.equalsIgnoreCase("Vid")) {
            return 5;
        }
        if (s.equalsIgnoreCase("Syn")) {
            return 87;
        }
        if (s.equalsIgnoreCase("Syn.Aud")) {
            return 87;
        }
        if (s.equalsIgnoreCase("Syn.Ani")) {
            return 86;
        }
        return -1;
    }
    
    public final byte ap(final String s) {
        int n = -1;
        if (s != null && !s.equalsIgnoreCase("")) {
            try {
                n = Integer.parseInt(s);
            }
            catch (NumberFormatException ex) {
                if (s.equalsIgnoreCase("Aud")) {
                    final byte trackID = this.trackID;
                    if (trackID == 68 || trackID == -120) {
                        n = trackID;
                    }
                }
                if (n == -1) {
                    n = ao(s);
                }
            }
        }
        return (byte)n;
    }
    
    public final byte getTrackID() {
        return this.trackID;
    }
    
    public int startOfData(final byte trackID, final byte b, final byte mediaType, final byte mediaSubType, final int n, final int n2, final int n3) {
        this.trackID = trackID;
        this.mediaType = mediaType;
        this.mediaSubType = mediaSubType;
        return 0;
    }
    
    public final void playerReady() {
        this.masterObject.bg(this);
    }
    
    public void destroy() {
    }
    
    public void stop() {
    }
    
    public void start() {
    }
    
    public int init(final hm35master masterObject) {
        this.masterObject = masterObject;
        this.setBackground(this.d5 = this.masterObject.ii.getBackground());
        return 0;
    }
}
