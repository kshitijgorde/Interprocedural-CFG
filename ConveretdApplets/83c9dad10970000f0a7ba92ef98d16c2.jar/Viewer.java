import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class Viewer extends Canvas implements MouseListener, MouseMotionListener
{
    Image offImage;
    Graphics offGraphics;
    int width;
    int height;
    Color baseColor;
    Color[] colors;
    ActionListener main;
    int lastX;
    int lastY;
    int lastF;
    int keys;
    boolean moved;
    final int sensitivityDrag = 40;
    final int sensitivityMove = 12;
    
    public Viewer(final int width, final int height, final ActionListener main) {
        this.baseColor = new Color(0, 0, 0);
        this.colors = new Color[] { new Color(255, 0, 0), new Color(0, 0, 255), new Color(255, 255, 0), new Color(255, 160, 64), new Color(0, 192, 0), new Color(255, 255, 255) };
        this.lastF = -1;
        this.moved = false;
        this.width = width;
        this.height = height;
        this.addMouseListener(this);
        this.main = main;
    }
    
    public void initialise() {
        this.offImage = this.createImage(this.width, this.height);
        this.offGraphics = this.offImage.getGraphics();
    }
    
    public void reset() {
        this.repaint();
    }
    
    private void editMove(final int n, final int n2) {
        int i = 0;
        int j = 0;
        boolean b = false;
        if (Cubie.settings.lockViewer) {
            return;
        }
        if (n < 0 || n2 < 0 || n >= 54 || n2 >= 54) {
            return;
        }
        int k;
        for (k = 0; k < 3; ++k) {
            for (i = 0; i < 26; ++i) {
                if (CubePosition.cubelet2facelet[i][k] == n) {
                    b = true;
                    break;
                }
            }
            if (b) {
                break;
            }
        }
        if (!b) {
            return;
        }
        boolean b2 = false;
        int l;
        for (l = 0; l < 3; ++l) {
            for (j = 0; j < 26; ++j) {
                if (CubePosition.cubelet2facelet[j][l] == n2) {
                    b2 = true;
                    break;
                }
            }
            if (b2) {
                break;
            }
        }
        if (!b2) {
            return;
        }
        Cubie.settings.cubePos.editMove(i, k, j, l);
        this.doEvent(true);
        this.repaint();
    }
    
    public boolean showMove(final int n, final int n2) {
        Cubie.settings.cubePos.doMove(n, n2, true);
        this.repaint();
        this.doEvent(false);
        return true;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.addMouseMotionListener(this);
        this.lastX = mouseEvent.getX();
        this.lastY = mouseEvent.getY();
        this.lastF = this.getFacelet(this.lastX, this.lastY);
        this.keys = (mouseEvent.isShiftDown() ? 1 : 0);
        this.keys += (mouseEvent.isControlDown() ? 2 : 0);
        this.keys += (mouseEvent.isAltDown() ? 4 : 0);
        this.moved = false;
        mouseEvent.consume();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.removeMouseMotionListener(this);
        if (Cubie.settings.edit && this.lastF >= 0) {
            this.editMove(this.lastF, this.getFacelet(mouseEvent.getX(), mouseEvent.getY()));
        }
        else if (!this.moved) {
            this.checkMouseMove(mouseEvent.getX(), mouseEvent.getY(), 12);
        }
        mouseEvent.consume();
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if ((!Cubie.settings.edit || this.lastF < 0) && !this.moved) {
            this.checkMouseMove(mouseEvent.getX(), mouseEvent.getY(), 40);
        }
        mouseEvent.consume();
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    protected abstract void checkMouseMove(final int p0, final int p1, final int p2);
    
    protected abstract int getFacelet(final int p0, final int p1);
    
    public void tryMove(int n, int n2) {
        if (n < 9) {
            if (Cubie.settings.group == 1) {
                this.keys |= 0x1;
            }
            if (Cubie.settings.group == 2) {
                this.keys |= 0x2;
                this.keys &= 0x3;
            }
            if (Cubie.settings.group == 3) {
                this.keys |= 0x4;
                this.keys &= 0x5;
            }
            if (Cubie.settings.group == 3 && n > 5) {
                this.keys |= 0x1;
            }
        }
        if (Cubie.settings.group == 4 && n != 1 && n != 3 && n < 6) {
            return;
        }
        if ((this.keys & 0x1) != 0x0) {
            if (n2 > 0) {
                n2 = 2;
            }
            else {
                n2 = -2;
            }
        }
        if ((this.keys & 0x2) != 0x0 && Cubie.settings.group != 4) {
            if (n < 3) {
                n += 12;
            }
            else if (n < 6) {
                n += 9;
                n2 = -n2;
            }
        }
        if ((this.keys & 0x4) != 0x0 && Cubie.settings.group != 4) {
            if (n < 3) {
                n += 15;
            }
            else if (n < 6) {
                n += 12;
            }
        }
        this.doMove(n, n2);
    }
    
    void doMove(final int n, final int n2) {
        if (Cubie.settings.lockViewer) {
            return;
        }
        Cubie.settings.cubePos.doMove(n, n2, true);
        this.repaint();
        this.doEvent(true);
    }
    
    void doEvent(final boolean b) {
        this.main.actionPerformed(new ActionEvent(this, 1001, b ? "user" : "auto"));
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
