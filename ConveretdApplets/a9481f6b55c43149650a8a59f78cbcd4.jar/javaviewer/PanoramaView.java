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
    private int _$1606;
    private int _$1607;
    private int _$5065;
    private int _$5066;
    private int _$1610;
    private int _$5067;
    private int _$5068;
    private int _$1613;
    protected int pantiltSpeed;
    private Viewer _$4305;
    private Color _$1615;
    private Color _$1616;
    
    public PanoramaView(final Viewer $4305) {
        this.panoramaGetFlg = false;
        this.axis = new MSRectangle(0, 0, 320, 90);
        this._$1606 = -2400;
        this._$1607 = 900;
        this._$5065 = 2400;
        this._$5066 = -420;
        this._$1610 = -2400;
        this._$5067 = 420;
        this._$5068 = 2400;
        this._$1613 = -900;
        this.pantiltSpeed = 24;
        this._$4305 = null;
        this._$1615 = new Color(255, 0, 0);
        this._$1616 = new Color(255, 255, 255);
        this._$4305 = $4305;
        this.disable();
        this._$1618();
        this.addMouseListener(this);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
            if (this._$4305.imageThread.digitalZoom) {
                return;
            }
            final int x = mouseEvent.getPoint().x;
            final int y = mouseEvent.getPoint().y;
            final int n = (int)((this.controledAreaBR.getX() - this.controledAreaTL.getX()) / this.panoramaData.getWidth(this) * x + this.controledAreaTL.getX());
            final int n2 = (int)((this.controledAreaBR.getY() - this.controledAreaTL.getY()) / this.panoramaData.getHeight(this) * y + this.controledAreaTL.getY());
            final StringBuffer sb = new StringBuffer("AbsolutePanTilt=");
            sb.append(this._$4305.camera.toPTAddress(n) + ",");
            sb.append(this._$4305.camera.toPTAddress(n2) + "," + this.pantiltSpeed);
            synchronized (this._$4305.commandSend) {
                this._$4305.commandSend.setCommand("/command/ptzf.cgi", sb.toString());
                this._$4305.commandSend.notify();
            }
            this._$4305.controlGUI.setSelectedPresetPosition(0);
        }
        if (this._$4305.controler != null) {
            this._$4305.controler.toFront();
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (this._$4305.curDCur.equals(this._$4305.curD) && !this._$4305.imageThread.digitalZoom) {
            this.setCursor(this._$4305.curH);
        }
        else {
            this.setCursor(this._$4305.curDCur);
        }
        if (this._$4305.controler != null) {
            this._$4305.controler.toFront();
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    private void _$1618() {
        this.panoramaData = this._$1625();
        this.setSize(this.panoramaData.getWidth(this), this.panoramaData.getHeight(this));
    }
    
    private Image _$1625() {
        Image image = null;
        int n = 0;
        if (this._$4305.camera.getEFlipStatus().equals("on")) {
            this.panoramaAreaTL = new MSPoint(this._$1606, this._$1607);
            this.panoramaAreaBR = new MSPoint(this._$5065, this._$5066);
        }
        else {
            this.panoramaAreaTL = new MSPoint(this._$1610, this._$5067);
            this.panoramaAreaBR = new MSPoint(this._$5068, this._$1613);
        }
        try {
            final URLConnection openConnection = new URL("http://" + this._$4305.host + "/panorama/panorama.jpg").openConnection();
            openConnection.setRequestProperty("Pragma", "no-cache");
            if (!this._$4305.uCode.equals("")) {
                openConnection.setRequestProperty("Authorization", "Basic " + this._$4305.uCode);
            }
            final int i = openConnection.getContentLength();
            this._$4305.logger.print("Panorama data size: " + i);
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
            this._$4305.logger.print("Panorama data : " + image.getWidth(this) + "/" + image.getHeight(this));
        }
        catch (IOException ex2) {}
        if (!this.panoramaGetFlg) {
            image = this._$4305.getImage(this._$4305.getCodeBase(), "panorama.jpg");
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
        final int panPos = this._$4305.camera.getPanPos();
        final int tiltPos = this._$4305.camera.getTiltPos();
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
            graphics.setColor(this._$1615);
        }
        else {
            graphics.setColor(this._$1616);
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
