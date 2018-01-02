import java.awt.Graphics;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class Toolbar extends Translator implements UpdateEventListener
{
    protected static final int ZOOM_IN = 0;
    protected static final int ZOOM_OUT = 1;
    protected static final int TAB = 2;
    protected static final int BUTTON_COUNT = 3;
    protected static final int DEFAULT = 0;
    protected static final int DEPRESSED = 1;
    protected static final int DISABLED = 2;
    protected static final int BUTTON_STATES = 3;
    protected Image[][] buttons;
    protected Rectangle[][] bounds;
    protected int state;
    protected int[] states;
    protected Rectangle[] client;
    protected Rectangle repaintArea;
    protected int mButton;
    protected boolean mousedown;
    protected int mXButton;
    
    Toolbar(final Controller c) {
        this.buttons = new Image[3][3];
        this.bounds = new Rectangle[2][3];
        this.state = 0;
        this.states = new int[3];
        this.client = new Rectangle[2];
        this.repaintArea = new Rectangle();
        this.mousedown = false;
        this.m_controller = c;
        this.m_controller.getHost().addEventListener(this);
        this.m_controller.getHost().setListenerPriority(this, 0);
        this.m_controller.addUpdateEventListener(this);
        for (int j = 0; j < 2; ++j) {
            for (int i = 0; i < 3; ++i) {
                this.buttons[i][j] = this.m_controller.getHost().gfx.gifParse(3 + j * 3 + i);
            }
        }
        this.buttons[0][2] = this.m_controller.getHost().gfx.gifParse(2);
        this.buttons[1][2] = this.buttons[0][2];
        this.buttons[2][2] = this.buttons[0][2];
        final Dimension displaySize = this.m_controller.getHost().size();
        int startX = 0;
        for (int k = 0; k < 3; ++k) {
            final Image button = this.buttons[0][k];
            final Dimension size = new Dimension(button.getWidth(null), button.getHeight(null));
            final Point p = new Point(startX, displaySize.height - size.height);
            startX += size.width;
            this.bounds[0][k] = new Rectangle(p, size);
            if (this.client[0] == null) {
                this.client[0] = new Rectangle(this.bounds[0][k].x, this.bounds[0][k].y, this.bounds[0][k].width, this.bounds[0][k].height);
            }
            else {
                this.client[0].add(this.bounds[0][k]);
            }
        }
        this.bounds[1][2] = new Rectangle(0, this.bounds[0][2].y, this.bounds[0][2].width, this.bounds[0][2].height);
        this.client[1] = this.bounds[1][2];
        for (int k = 0; k < 3; ++k) {
            this.states[k] = 0;
        }
    }
    
    int getToolbarState() {
        if (!this.m_controller.getHost().showToolbar) {
            return 0;
        }
        if (this.state == 0) {
            return 2;
        }
        return 1;
    }
    
    void setToolbarState(final int newstate) {
        if (newstate == 0) {
            if (this.m_controller.getHost().showToolbar) {
                this.m_controller.getHost().showToolbar = false;
            }
        }
        else {
            this.m_controller.getHost().showToolbar = true;
            if (newstate == 1) {
                this.state = 1;
            }
            else {
                this.state = 0;
            }
        }
        this.m_controller.getHost().repaint(this.client[0].x, this.client[0].y, this.client[0].width, this.client[0].height);
    }
    
    void close() {
        this.m_controller.getHost().removeEventListener(this);
        this.m_controller.removeUpdateEventListener(this);
    }
    
    boolean paint(final Graphics g) {
        if (!this.m_controller.getHost().showToolbar) {
            return false;
        }
        try {
            boolean drawComplete = true;
            for (int i = 0; i < 3; ++i) {
                if (this.bounds[this.state][i] != null && !g.drawImage(this.buttons[this.states[i]][i], this.bounds[this.state][i].x, this.bounds[this.state][i].y, null)) {
                    drawComplete = false;
                }
            }
            if (drawComplete) {
                this.repaintArea.setBounds(0, 0, 0, 0);
            }
            return drawComplete;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public void handleEvent(final Event e) {
        if (!this.m_controller.getHost().showToolbar || (!this.mousedown && !this.contains(e.x, e.y))) {
            return;
        }
        switch (e.id) {
            case 504: {
                if (!this.contains(e.x, e.y)) {
                    break;
                }
                e.consume();
                this.m_controller.getHost().setCursor(0);
                break;
            }
            case 505: {
                if (!this.mousedown) {
                    break;
                }
                if (this.m_controller.isActive(this)) {
                    this.m_controller.retireControl(this);
                }
                this.setButton(this.mButton, 0);
                this.updateButtons();
                break;
            }
            case 501: {
                if (!this.contains(e.x, e.y)) {
                    break;
                }
                e.consume();
                this.mousedown = true;
                final int b = this.getButton(e.x, e.y);
                this.mXButton = -1;
                if (b < 0) {
                    break;
                }
                if (b == 2) {
                    this.flipToolbarState();
                }
                else if (!this.m_controller.isActive(this) && !this.m_controller.requestControl(this)) {
                    break;
                }
                this.setButton(this.mButton = b, 1);
                final IpixViewer host = this.m_controller.host;
                host.state |= IpixViewer.kUserInteracting;
                break;
            }
            case 502: {
                if (!this.contains(e.x, e.y)) {
                    this.m_controller.getHost().setCursor(0);
                }
                if (!this.mousedown) {
                    break;
                }
                if (this.contains(e.x, e.y)) {
                    e.consume();
                }
                this.mousedown = false;
                this.setButton(e.x, e.y, 0);
                if (this.m_controller.isActive(this)) {
                    this.m_controller.retireControl(this);
                }
                this.mButton = -1;
                this.mXButton = -1;
                break;
            }
            case 506: {
                if (!this.mousedown) {
                    break;
                }
                e.consume();
                if (this.mXButton == -1 && this.getButton(e.x, e.y) != this.mButton) {
                    if (this.m_controller.isActive(this)) {
                        this.m_controller.retireControl(this);
                    }
                    if (this.states[this.mButton] == 1) {
                        this.setButton(this.mButton, 0);
                        break;
                    }
                    break;
                }
                else {
                    if (!this.m_controller.isActive(this) && !this.m_controller.requestControl(this)) {
                        break;
                    }
                    if (this.states[this.mButton] == 0) {
                        this.setButton(this.mButton, 1);
                        break;
                    }
                    break;
                }
                break;
            }
            case 503: {
                e.consume();
                this.m_controller.getHost().setCursor(0);
                break;
            }
        }
    }
    
    protected void setButton(final int x, final int y, final int state) {
        final int idx = this.getButton(x, y);
        this.setButton(idx, state);
        this.updateButtons();
    }
    
    protected void setButton(final int index, final int newState) {
        if (index >= 0 && this.states[index] != newState) {
            this.states[index] = newState;
            if (this.bounds[this.state][index] != null) {
                this.repaintArea.add(this.bounds[this.state][index]);
            }
        }
    }
    
    protected int getButton(final int x, final int y) {
        for (int i = 0; i < 3; ++i) {
            if (this.bounds[this.state][i] != null && this.bounds[this.state][i].inside(x, y)) {
                return i;
            }
        }
        return -1;
    }
    
    boolean interrupt(final int type) {
        return type == 0 || !this.mousedown;
    }
    
    boolean updateViewpoint(final float[] vp) {
        if (!this.m_controller.isActive(this)) {
            return false;
        }
        switch (this.mButton) {
            case 0: {
                vp[3] = this.newZoom(vp[3], true);
                break;
            }
            case 1: {
                vp[3] = this.newZoom(vp[3], false);
                break;
            }
            default: {
                return false;
            }
        }
        return true;
    }
    
    public void update(final UpdateEvent e) {
        final float[] dvp = e.getViewpointChange();
        if (dvp[3] > 1.0E-4f) {
            this.setButton(0, 1);
        }
        else {
            this.setButton(0, 0);
        }
        if (dvp[3] < -1.0E-4f) {
            this.setButton(1, 1);
        }
        else {
            this.setButton(1, 0);
        }
        this.checkZoomLimits();
    }
    
    protected void checkZoomLimits() {
        final float[] vp = this.m_controller.getHost().getViewpoint();
        final Pipeline pipeline = this.m_controller.getHost().getPipeline();
        if (vp[3] >= pipeline.getZoomMax() - 1.0E-4f) {
            this.setButton(0, 2);
        }
        else if (this.states[0] == 2) {
            this.setButton(0, 0);
        }
        if (vp[3] <= pipeline.getZoomMin() + 1.0E-4f) {
            this.setButton(1, 2);
        }
        else if (this.states[1] == 2) {
            this.setButton(1, 0);
        }
    }
    
    protected void updateButtons() {
        this.checkZoomLimits();
        if (!this.repaintArea.isEmpty()) {
            this.m_controller.getHost().repaint(this.repaintArea.x, this.repaintArea.y, this.repaintArea.width, this.repaintArea.height);
        }
    }
    
    protected void flipToolbarState() {
        this.state = (this.state + 1) % 2;
        this.m_controller.getHost().repaint(this.client[0].x, this.client[0].y, this.client[0].width, this.client[0].height);
    }
    
    boolean contains(final int x, final int y) {
        return this.client[this.state] != null && this.client[this.state].inside(x, y);
    }
}
