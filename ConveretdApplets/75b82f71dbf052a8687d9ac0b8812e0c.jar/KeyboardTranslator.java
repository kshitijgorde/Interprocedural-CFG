import java.awt.Cursor;
import java.awt.Image;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class KeyboardTranslator extends Translator implements UpdateEventListener
{
    private static final int VK_1 = 49;
    private static final int VK_2 = 50;
    private static final int VK_3 = 51;
    private static final int VK_4 = 52;
    private static final int VK_5 = 53;
    private static final int VK_6 = 54;
    private static final int VK_7 = 55;
    private static final int VK_8 = 56;
    private static final int VK_9 = 57;
    private static final int VK_ADD = 43;
    private static final int VK_SUBTRACT = 45;
    private static final int VK_EQUALS = 61;
    private static final int VK_SPACE = 32;
    private static final int VK_PERIOD = 46;
    private static final int VK_LEFT = 1006;
    private static final int VK_RIGHT = 1007;
    private static final int VK_UP = 1004;
    private static final int VK_DOWN = 1005;
    private static final int VK_PAGE_UP = 1002;
    private static final int VK_PAGE_DOWN = 1003;
    private static final int VK_HOME = 1000;
    private static final int VK_F1 = 1008;
    private static final int VK_BLANK = -1;
    private static final int VK_ENTER = 10;
    private static final int VK_HELP;
    private int aKey;
    private static final Vector navKeys;
    
    static {
        VK_HELP = (System.getProperty("java.vendor").startsWith("Apple") ? 0 : 5);
        (navKeys = new Vector()).addElement(new Integer(52));
        KeyboardTranslator.navKeys.addElement(new Integer(1006));
        KeyboardTranslator.navKeys.addElement(new Integer(56));
        KeyboardTranslator.navKeys.addElement(new Integer(1004));
        KeyboardTranslator.navKeys.addElement(new Integer(54));
        KeyboardTranslator.navKeys.addElement(new Integer(1007));
        KeyboardTranslator.navKeys.addElement(new Integer(50));
        KeyboardTranslator.navKeys.addElement(new Integer(1005));
        KeyboardTranslator.navKeys.addElement(new Integer(55));
        KeyboardTranslator.navKeys.addElement(new Integer(49));
        KeyboardTranslator.navKeys.addElement(new Integer(57));
        KeyboardTranslator.navKeys.addElement(new Integer(51));
        KeyboardTranslator.navKeys.addElement(new Integer(43));
        KeyboardTranslator.navKeys.addElement(new Integer(1002));
        KeyboardTranslator.navKeys.addElement(new Integer(61));
        KeyboardTranslator.navKeys.addElement(new Integer(45));
        KeyboardTranslator.navKeys.addElement(new Integer(1003));
        KeyboardTranslator.navKeys.addElement(new Integer(10));
    }
    
    KeyboardTranslator(final Controller c) {
        this.aKey = 0;
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
        switch (this.aKey) {
            case 52:
            case 1006: {
                final int n = 0;
                vp[n] -= this.accelerate() * 3.0f;
                break;
            }
            case 56:
            case 1004: {
                final int n2 = 1;
                vp[n2] += this.accelerate() * 3.0f;
                break;
            }
            case 54:
            case 1007: {
                final int n3 = 0;
                vp[n3] += this.accelerate() * 3.0f;
                break;
            }
            case 50:
            case 1005: {
                final int n4 = 1;
                vp[n4] -= this.accelerate() * 3.0f;
                break;
            }
            case 55: {
                final int n5 = 0;
                vp[n5] -= this.accelerate() * 3.0f;
                final int n6 = 1;
                vp[n6] += this.accelerate() * 3.0f;
                break;
            }
            case 49: {
                final int n7 = 0;
                vp[n7] -= this.accelerate() * 3.0f;
                final int n8 = 1;
                vp[n8] -= this.accelerate() * 3.0f;
                break;
            }
            case 57: {
                final int n9 = 0;
                vp[n9] += this.accelerate() * 3.0f;
                final int n10 = 1;
                vp[n10] += this.accelerate() * 3.0f;
                break;
            }
            case 51: {
                final int n11 = 0;
                vp[n11] += this.accelerate() * 3.0f;
                final int n12 = 1;
                vp[n12] -= this.accelerate() * 3.0f;
                break;
            }
            case 43:
            case 61:
            case 1002: {
                vp[3] = this.newZoom(vp[3], true);
                break;
            }
            case 45:
            case 1003: {
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
        if (!this.m_controller.getHost().showCrosshairs) {
            return;
        }
        if (this.m_controller.getHost().hotspots != null) {
            for (int i = 0; i < this.m_controller.getHost().hotspots.length; ++i) {
                if (this.m_controller.getHost().hotspots[i].isDestPointInHotspot(new Point(this.m_controller.getHost().getSize().width / 2, this.m_controller.getHost().getSize().height / 2))) {
                    this.m_controller.getHost().hotspots[i].setEntryPoint(new Point(this.m_controller.getHost().getSize().width / 2, this.m_controller.getHost().getSize().height / 2));
                    this.m_controller.getHost().hotspots[i].activateHotspot = true;
                    this.m_controller.getHost().hotspots[i].paintHotspot = true;
                    this.m_controller.getHost().hotspots[i].paintPopupText = true;
                }
                else {
                    this.m_controller.getHost().hotspots[i].activateHotspot = false;
                    this.m_controller.getHost().hotspots[i].paintHotspot = false;
                    this.m_controller.getHost().hotspots[i].paintPopupText = false;
                }
            }
        }
    }
    
    void close() {
        this.m_controller.getHost().removeEventListener(this);
        this.m_controller.removeUpdateEventListener(this);
    }
    
    boolean paint(final Graphics g) {
        final Dimension displaySize = this.m_controller.getHost().size();
        final int lineLength = 14;
        if (!this.m_controller.getHost().showCrosshairs) {
            return false;
        }
        try {
            g.setColor(Color.black);
            g.drawLine((int)displaySize.getWidth() / 2, (int)displaySize.getHeight() / 2 - lineLength / 2, (int)displaySize.getWidth() / 2, (int)displaySize.getHeight() / 2 + lineLength / 2 - 1);
            g.drawLine((int)displaySize.getWidth() / 2 - lineLength / 2, (int)displaySize.getHeight() / 2, (int)displaySize.getWidth() / 2 + lineLength / 2, (int)displaySize.getHeight() / 2);
            g.setColor(Color.white);
            g.drawLine((int)displaySize.getWidth() / 2 - 1, (int)displaySize.getHeight() / 2 - lineLength / 2, (int)displaySize.getWidth() / 2 - 1, (int)displaySize.getHeight() / 2 + lineLength / 2 - 1);
            g.drawLine((int)displaySize.getWidth() / 2 - lineLength / 2 - 1, (int)displaySize.getHeight() / 2 - 1, (int)displaySize.getWidth() / 2 + lineLength / 2 - 1, (int)displaySize.getHeight() / 2 - 1);
        }
        catch (Exception ex) {}
        return false;
    }
    
    void handleEvent(final Event e) {
        if (!this.m_controller.getHost().navigationOn) {
            return;
        }
        Label_0212: {
            switch (e.id) {
                case 401: {
                    if (e.key != 10) {
                        break Label_0212;
                    }
                    if (this.m_controller.getHost().hotspots != null) {
                        for (int i = 0; i < this.m_controller.getHost().hotspots.length; ++i) {
                            if (this.m_controller.getHost().hotspots[i].isDestPointInHotspot(new Point(this.m_controller.getHost().getSize().width / 2, this.m_controller.getHost().getSize().height / 2)) && this.m_controller.getHost().hotspots[i].activate(this.m_controller)) {
                                this.m_controller.getHost().hotspots[i].activateHotspot = true;
                                break;
                            }
                        }
                        break;
                    }
                    break;
                }
                case 403: {
                    if (this.m_controller.isActive(this)) {
                        break;
                    }
                    if (!KeyboardTranslator.navKeys.contains(new Integer(e.key))) {
                        break;
                    }
                    if (!this.m_controller.requestControl(this)) {
                        break;
                    }
                    try {
                        final int[] pixels = new int[256];
                        final Image image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(16, 16, pixels, 0, 16));
                        final Cursor transparentCursor = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(0, 0), "");
                        this.m_controller.getHost().setCursor(transparentCursor);
                    }
                    catch (Exception ex) {}
                    this.m_controller.getHost().showCrosshairs = true;
                    this.aKey = e.key;
                    break;
                }
                case 402:
                case 404: {
                    if (this.m_controller.isActive(this)) {
                        if (e.key == this.aKey) {
                            this.m_controller.retireControl(this);
                            break;
                        }
                        break;
                    }
                    else {
                        if (e.key == 1000 || e.key == 53 || e.key == -1) {
                            this.m_controller.requestControl(new LocationTranslator(this.m_controller));
                            break;
                        }
                        if (e.key == 46 && (e.modifiers & 0x2) == 0x2) {
                            this.m_controller.requestControl(null);
                            break;
                        }
                        if (e.key == 32 && (e.modifiers & 0x2) == 0x2) {
                            this.m_controller.requestControl(new SpinTranslator(this.m_controller, 5));
                            break;
                        }
                        if (e.key == 1008 || e.key == KeyboardTranslator.VK_HELP) {
                            this.m_controller.getHost().showHelp();
                            break;
                        }
                        break;
                    }
                    break;
                }
                case 1005: {
                    this.m_controller.retireControl(this);
                    break;
                }
            }
        }
    }
}
