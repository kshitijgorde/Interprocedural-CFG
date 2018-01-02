import java.awt.Cursor;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class MouseTranslator extends Translator implements UpdateEventListener
{
    private static final int HAND = 12;
    private static final int MOVE = 13;
    private static final int EAST = 11;
    private static final int SOUTH_EAST = 5;
    private static final int SOUTH = 9;
    private static final int SOUTH_WEST = 4;
    private static final int WEST = 10;
    private static final int NORTH_WEST = 6;
    private static final int NORTH = 8;
    private static final int NORTH_EAST = 7;
    protected Point mMouse;
    protected Point mAMouse;
    private static final int RESET;
    
    static {
        RESET = (System.getProperty("os.name").startsWith("Mac") ? 2 : 4);
    }
    
    MouseTranslator(final Controller c) {
        this.mMouse = new Point(0, 0);
        this.mAMouse = new Point(0, 0);
        this.m_controller = c;
        this.m_controller.getHost().addEventListener(this);
        this.m_controller.addUpdateEventListener(this);
    }
    
    boolean interrupt(final int type) {
        return type == 0;
    }
    
    boolean updateViewpoint(final float[] vp) {
        if (!this.m_controller.isActive(this)) {
            return false;
        }
        final float dx = this.mMouse.x - this.mAMouse.x;
        final float dy = this.mMouse.y - this.mAMouse.y;
        final float theta = (float)Math.atan2(dy, dx);
        final float xpct = Util.limit(dx / this.m_controller.getHost().size().width, -0.5f, 0.5f);
        final float ypct = Util.limit(dy / this.m_controller.getHost().size().height, -0.5f, 0.5f);
        float dp = xpct * Math.abs(xpct) * 0.11f;
        float dt = ypct * Math.abs(ypct) * 0.11f;
        final float ctheta = (float)Math.cos(theta);
        if (ctheta < 1.0E-4f && ctheta > -1.0E-4f) {
            dp = 0.0f;
        }
        final float stheta = (float)Math.sin(theta);
        if (stheta < 1.0E-4f && stheta > -1.0E-4f) {
            dt = 0.0f;
        }
        final int n = 0;
        vp[n] += dp / vp[3] * this.getIntensity();
        final int n2 = 1;
        vp[n2] -= dt / vp[3] * this.getIntensity();
        return true;
    }
    
    void close() {
        this.m_controller.getHost().removeEventListener(this);
        this.m_controller.removeUpdateEventListener(this);
    }
    
    void handleEvent(final Event e) {
        if (e.isConsumed()) {
            return;
        }
        switch (e.id) {
            case 504: {
                this.setMousePos(e.x, e.y);
                break;
            }
            case 501: {
                this.setMousePos(e.x, e.y);
                final IpixViewer host = this.m_controller.host;
                host.state |= IpixViewer.kUserInteracting;
                if ((e.modifiers & MouseTranslator.RESET) == MouseTranslator.RESET) {
                    this.m_controller.requestControl(new LocationTranslator(this.m_controller));
                    break;
                }
                if ((e.modifiers & 0x8) != 0x0) {
                    break;
                }
                if ((e.modifiers & 0x4) != 0x0) {
                    break;
                }
                if (this.m_controller.getHost().hotspots != null) {
                    for (int i = 0; i < this.m_controller.getHost().hotspots.length; ++i) {
                        if (this.m_controller.getHost().hotspots[i].isDestPointInHotspot(new Point(e.x, e.y))) {
                            this.m_controller.getHost().hotspots[i].activateHotspot = true;
                        }
                        else {
                            this.m_controller.getHost().hotspots[i].activateHotspot = false;
                        }
                    }
                }
                this.mAMouse.move(e.x, e.y);
                break;
            }
            case 502: {
                this.setMousePos(e.x, e.y);
                this.ActivateHotspot(new Point(e.x, e.y));
                if (this.m_controller.isActive(this) && (e.modifiers & 0x8) == 0x0 && (e.modifiers & 0x4) == 0x0) {
                    this.m_controller.retireControl(this);
                    break;
                }
                break;
            }
            case 506: {
                this.setMousePos(e.x, e.y);
                if (!this.m_controller.isActive(this)) {
                    this.m_controller.requestControl(this);
                }
                if (this.m_controller.getHost().hotspots != null) {
                    for (int i = 0; i < this.m_controller.getHost().hotspots.length; ++i) {
                        this.m_controller.getHost().hotspots[i].activateHotspot = false;
                    }
                    break;
                }
                break;
            }
            case 503: {
                this.setMousePos(e.x, e.y);
                this.m_controller.getHost().showCrosshairs = false;
                this.m_controller.getHost().repaint();
                this.UpdateHotspots(new Point(e.x, e.y));
                break;
            }
            case 1005: {
                this.m_controller.retireControl(this);
                this.setMousePos(e.x, e.y);
                break;
            }
        }
    }
    
    void ActivateHotspot(final Point pt) {
        if (this.m_controller.getHost().hotspots != null) {
            for (int i = 0; i < this.m_controller.getHost().hotspots.length; ++i) {
                if (this.m_controller.getHost().hotspots[i].isDestPointInHotspot(pt) && this.m_controller.getHost().hotspots[i].activate(this.m_controller)) {
                    break;
                }
            }
        }
    }
    
    public void update(final UpdateEvent e) {
        if (this.m_controller.getHost().showCrosshairs) {
            return;
        }
        this.setMousePos(this.mMouse.x, this.mMouse.y);
        this.UpdateHotspots(this.mMouse);
    }
    
    protected void UpdateHotspots(final Point pt) {
        if (this.m_controller.getHost().hotspots != null) {
            for (int i = 0; i < this.m_controller.getHost().hotspots.length; ++i) {
                if (this.m_controller.getHost().hotspots[i].isDestPointInHotspot(pt)) {
                    this.m_controller.getHost().hotspots[i].setEntryPoint(pt);
                    this.m_controller.getHost().hotspots[i].paintHotspot = true;
                    this.m_controller.getHost().hotspots[i].paintPopupText = true;
                }
                else {
                    this.m_controller.getHost().hotspots[i].paintHotspot = false;
                    this.m_controller.getHost().hotspots[i].paintPopupText = false;
                }
            }
        }
    }
    
    protected void setMousePos(final int x, final int y) {
        this.mMouse.move(x, y);
        int cur = 13;
        if (this.m_controller.isActive(this)) {
            final float dx = this.mMouse.x - this.mAMouse.x;
            final float dy = this.mMouse.y - this.mAMouse.y;
            final float theta = (float)(Math.atan2(dy, dx) / 3.1415927410125732);
            if (dx == 0.0f && dy == 0.0f) {
                cur = 13;
            }
            else if (theta < -0.875f) {
                cur = 10;
            }
            else if (theta < -0.625f) {
                cur = 6;
            }
            else if (theta < -0.375f) {
                cur = 8;
            }
            else if (theta < -0.125f) {
                cur = 7;
            }
            else if (theta < 0.125f) {
                cur = 11;
            }
            else if (theta < 0.375f) {
                cur = 5;
            }
            else if (theta < 0.625f) {
                cur = 9;
            }
            else if (theta < 0.875f) {
                cur = 4;
            }
            else {
                cur = 10;
            }
        }
        else if (this.m_controller.getHost().hotspots != null) {
            for (int i = 0; i < this.m_controller.getHost().hotspots.length; ++i) {
                if (this.m_controller.getHost().hotspots[i].isDestPointInHotspot(this.mMouse)) {
                    cur = 12;
                    break;
                }
            }
        }
        this.m_controller.getHost().setCursor(null);
        this.m_controller.getHost().setCursor(cur);
    }
}
