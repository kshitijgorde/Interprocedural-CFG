// 
// Decompiled by Procyon v0.5.30
// 

package javaviewer;

import java.awt.Graphics;
import java.net.URLConnection;
import java.io.IOException;
import java.awt.AWTError;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.MediaTracker;
import java.io.DataInputStream;
import java.net.URL;
import java.awt.image.ImageObserver;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.Canvas;

public final class PanoramaView extends Canvas implements MouseListener
{
    public Image panoramaData;
    public boolean panoramaGetFlg;
    protected boolean visible;
    protected MSRectangle axis;
    protected MSPoint panoramaAreaTL;
    protected MSPoint panoramaAreaBR;
    protected MSPoint controledAreaTL;
    protected MSPoint controledAreaBR;
    private int _$210973;
    private int _$210979;
    private int _$210984;
    private int _$210991;
    private int _$169761;
    private int _$169768;
    private int _$169774;
    private int _$169782;
    protected int pantiltSpeed;
    private Viewer _$1008;
    private Color _$169803;
    private Color _$169813;
    
    public PanoramaView(final Viewer $1008) {
        this.panoramaGetFlg = false;
        this.axis = new MSRectangle(0, 0, 320, 90);
        this._$210973 = -2400;
        this._$210979 = 900;
        this._$210984 = 2400;
        this._$210991 = -420;
        this._$169761 = -2400;
        this._$169768 = 420;
        this._$169774 = 2400;
        this._$169782 = -900;
        this.pantiltSpeed = 24;
        this._$1008 = null;
        this._$169803 = new Color(255, 0, 0);
        this._$169813 = new Color(255, 255, 255);
        this._$1008 = $1008;
        this.disable();
        this._$169825();
        this.addMouseListener(this);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
            if (this._$1008.imageThread.digitalZoom) {
                return;
            }
            final int x = mouseEvent.getPoint().x;
            final int y = mouseEvent.getPoint().y;
            final int n = (int)((this.controledAreaBR.getX() - this.controledAreaTL.getX()) / this.panoramaData.getWidth(this) * x + this.controledAreaTL.getX());
            final int n2 = (int)((this.controledAreaBR.getY() - this.controledAreaTL.getY()) / this.panoramaData.getHeight(this) * y + this.controledAreaTL.getY());
            final StringBuffer sb = new StringBuffer("AbsolutePanTilt=");
            sb.append(String.valueOf(String.valueOf(this._$1008.camera.toPTAddress(n))).concat(","));
            sb.append(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this._$1008.camera.toPTAddress(n2)))).append(",").append(this.pantiltSpeed))));
            synchronized (this._$1008.commandSend) {
                this._$1008.commandSend.setCommand("/command/ptzf.cgi", sb.toString());
                this._$1008.commandSend.notify();
            }
            // monitorexit(this._$1008.commandSend)
            this._$1008.controlGUI.setSelectedPresetPosition(0);
        }
        if (this._$1008.controler != null) {
            this._$1008.controler.toFront();
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (this._$1008.curDCur.equals(this._$1008.curD) && !this._$1008.imageThread.digitalZoom) {
            this.setCursor(this._$1008.curH);
        }
        else {
            this.setCursor(this._$1008.curDCur);
        }
        if (this._$1008.controler != null) {
            this._$1008.controler.toFront();
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    private void _$169825() {
        this.panoramaData = this._$169853();
        this.setSize(this.panoramaData.getWidth(this), this.panoramaData.getHeight(this));
    }
    
    private Image _$169853() {
        Image image = null;
        int n = 0;
        if (this._$1008.camera.getEFlipStatus().equals("on")) {
            this.panoramaAreaTL = new MSPoint(this._$210973, this._$210979);
            this.panoramaAreaBR = new MSPoint(this._$210984, this._$210991);
        }
        else {
            this.panoramaAreaTL = new MSPoint(this._$169761, this._$169768);
            this.panoramaAreaBR = new MSPoint(this._$169774, this._$169782);
        }
        try {
            final URLConnection openConnection = new URL(String.valueOf(String.valueOf(new StringBuffer("http://").append(this._$1008.host).append("/panorama/panorama.jpg")))).openConnection();
            openConnection.setRequestProperty("Pragma", "no-cache");
            if (!this._$1008.uCode.equals("")) {
                openConnection.setRequestProperty("Authorization", "Basic ".concat(String.valueOf(String.valueOf(this._$1008.uCode))));
            }
            final int i = openConnection.getContentLength();
            this._$1008.logger.print("Panorama data size: ".concat(String.valueOf(String.valueOf(i))));
            DataInputStream dataInputStream;
            byte[] array;
            for (dataInputStream = new DataInputStream(openConnection.getInputStream()), array = new byte[i + 10240]; i > n; n += dataInputStream.read(array, n, 10240)) {}
            try {
                final MediaTracker mediaTracker = new MediaTracker(this);
                image = Toolkit.getDefaultToolkit().createImage(array);
                mediaTracker.addImage(image, 0);
                mediaTracker.checkAll(true);
                try {
                    if (i != -1) {
                        while (image.getWidth(this) == -1) {}
                        mediaTracker.waitForAll();
                        this.panoramaGetFlg = true;
                        final String s = new String(array, 0, i);
                        final String substring = s.substring(s.indexOf("PanoArea:") + 10, s.indexOf("PanoArea:") + 30);
                        this.controledAreaTL = new MSPoint(this.toInt(substring.substring(0, 4)), this.toInt(substring.substring(5, 9)));
                        this.controledAreaBR = new MSPoint(this.toInt(substring.substring(10, 14)), this.toInt(substring.substring(15, 19)));
                    }
                }
                catch (Exception ex) {}
            }
            catch (AWTError awtError) {
                System.out.println("CreateImage Error!!");
            }
            this._$1008.logger.print(String.valueOf(String.valueOf(new StringBuffer("Panorama data : ").append(image.getWidth(this)).append("/").append(image.getHeight(this)))));
        }
        catch (IOException ex2) {}
        if (!this.panoramaGetFlg) {
            image = this._$1008.getImage(this._$1008.getCodeBase(), "panorama.jpg");
            while (true) {
                if (image.getWidth(this) != -1) {
                    if (image.getHeight(this) == -1) {
                        continue;
                    }
                    break;
                }
            }
            this.controledAreaTL = this.panoramaAreaTL;
            this.controledAreaBR = this.panoramaAreaBR;
        }
        return image;
    }
    
    public void enable() {
        this.setVisible(this.visible = true);
    }
    
    public void disable() {
        this.setVisible(this.visible = false);
    }
    
    public boolean panoramaVisible() {
        return this.visible;
    }
    
    public double getRatio() {
        return 0.28125;
    }
    
    public void paint(final Graphics graphics) {
        final int panPos = this._$1008.camera.getPanPos();
        final int tiltPos = this._$1008.camera.getTiltPos();
        int width = (int)((panPos - this.controledAreaTL.getX()) * this.panoramaData.getWidth(this) / (this.controledAreaBR.getX() - this.controledAreaTL.getX()));
        int n = (int)((tiltPos - this.controledAreaTL.getY()) * this.panoramaData.getHeight(this) / (this.controledAreaBR.getY() - this.controledAreaTL.getY()));
        if (width <= 0) {
            width = 1;
        }
        if (width >= this.panoramaData.getWidth(this)) {
            width = this.panoramaData.getWidth(this);
        }
        if (n < 0) {
            n = 0;
        }
        if (n >= this.panoramaData.getHeight(this)) {
            n = this.panoramaData.getHeight(this) - 1;
        }
        graphics.drawImage(this.panoramaData, 0, 0, this.getWidth(), this.getHeight(), this);
        if (this.panoramaGetFlg) {
            graphics.setColor(this._$169803);
        }
        else {
            graphics.setColor(this._$169813);
        }
        --width;
        graphics.drawRoundRect(width - 4, n - 4, 8, 8, 7, 7);
        graphics.drawLine(width, n - 7, width, n + 7);
        graphics.drawLine(width - 7, n, width + 7, n);
    }
    
    public int toInt(final String s) {
        final char[] charArray = s.toCharArray();
        int digit = Character.digit(charArray[0], 16);
        if (digit >= 8) {
            digit -= 16;
        }
        for (int i = 1; i < s.length(); ++i) {
            digit = digit * 16 + Character.digit(charArray[i], 16);
        }
        return digit;
    }
    
    public int getWidth() {
        return this.getSize().width;
    }
    
    public int getHeight() {
        return this.getSize().height;
    }
}
