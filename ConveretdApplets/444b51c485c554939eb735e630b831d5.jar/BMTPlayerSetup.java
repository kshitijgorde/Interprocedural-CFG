import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.Container;

// 
// Decompiled by Procyon v0.5.30
// 

public class BMTPlayerSetup extends Container implements MouseListener, ActionListener
{
    private BMTron bmt;
    private BMTOptions bmto;
    private boolean active;
    private boolean enabled;
    private int maxControl;
    private int maxColor;
    private int control;
    private int color;
    private BMTButton btnEnabled;
    private BMTButton btnControl;
    private BMTButton btnColor;
    private BMTTextField tfName;
    
    BMTPlayerSetup(final BMTOptions bmto, final boolean enabled, final int control, final int color, final String s) {
        this.active = false;
        this.bmto = bmto;
        this.bmt = bmto.bmt;
        this.enabled = enabled;
        this.control = control;
        this.color = color;
        this.maxControl = this.bmt.skin.controls.length - 1;
        this.maxColor = bmto.colors.length - 1;
        this.setSize(this.getPreferredSize());
        this.setLayout(null);
        this.btnEnabled = new BMTButton(enabled ? this.bmt.skin.yes : this.bmt.skin.no);
        this.btnControl = new BMTButton(this.bmt.skin.controls[control]);
        this.btnColor = new BMTButton(bmto.colorImages[color]);
        this.tfName = new BMTTextField(s);
        this.btnEnabled.setLocation(10, 10);
        this.tfName.setLocation(44, 5);
        this.btnColor.setLocation(387, 10);
        this.btnControl.setLocation(421, 5);
        this.btnEnabled.addMouseListener(this);
        this.btnControl.addMouseListener(this);
        this.btnColor.addMouseListener(this);
        this.tfName.addMouseListener(this);
        this.btnEnabled.addActionListener(this);
        this.btnControl.addActionListener(this);
        this.btnColor.addActionListener(this);
        this.add(this.btnEnabled);
        this.add(this.btnControl);
        this.add(this.btnColor);
        this.add(this.tfName);
        this.addMouseListener(this);
    }
    
    public void paint(final Graphics graphics) {
        if (this.active) {
            graphics.setColor(new Color(255, 255, 255));
            graphics.drawRect(0, 0, this.getSize().width - 1, this.getSize().height - 1);
            graphics.drawRect(1, 1, this.getSize().width - 3, this.getSize().height - 3);
        }
        super.paint(graphics);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.btnEnabled) {
            this.setPlayerEnabled(!this.enabled);
            int n = 0;
            for (int i = 0; i <= this.bmto.playerSetups.length - 1; ++i) {
                if (this.bmto.playerSetups[i].enabled) {
                    ++n;
                }
            }
            if (n < 2) {
                for (int j = 0; j <= this.bmto.playerSetups.length - 1; ++j) {
                    if (this.bmto.playerSetups[j] != this & !this.bmto.playerSetups[j].enabled) {
                        this.bmto.playerSetups[j].setPlayerEnabled(true);
                        break;
                    }
                }
            }
        }
        else if (actionEvent.getSource() == this.btnControl) {
            this.incControl(false);
        }
        else if (actionEvent.getSource() == this.btnColor) {
            this.incColor(true);
        }
    }
    
    void setPlayerEnabled(final boolean enabled) {
        this.enabled = enabled;
        this.btnEnabled.setImage(enabled ? this.bmt.skin.yes : this.bmt.skin.no);
        this.repaint();
    }
    
    void incControl(final boolean b) {
        boolean b2;
        do {
            ++this.control;
            if (this.control > this.maxControl) {
                this.control = 0;
            }
            b2 = false;
            for (int i = 0; i <= this.bmto.playerSetups.length - 1; ++i) {
                if (this.bmto.playerSetups[i] != this & this.bmto.playerSetups[i].control == this.control) {
                    b2 = true;
                    break;
                }
            }
        } while (b & b2);
        if (!b & this.control <= 3) {
            for (int j = 0; j <= this.bmto.playerSetups.length - 1; ++j) {
                if (this.bmto.playerSetups[j] != this & this.bmto.playerSetups[j].control == this.control) {
                    this.bmto.playerSetups[j].incControl(true);
                    break;
                }
            }
        }
        this.btnControl.setImage(this.bmt.skin.controls[this.control]);
    }
    
    void incColor(final boolean b) {
        boolean b2;
        do {
            ++this.color;
            if (this.color > this.maxColor) {
                this.color = 0;
            }
            b2 = false;
            for (int i = 0; i <= this.bmto.playerSetups.length - 1; ++i) {
                if (this.bmto.playerSetups[i] != this & this.bmto.playerSetups[i].color == this.color) {
                    b2 = true;
                    break;
                }
            }
        } while (b & b2);
        if (!b) {
            for (int j = 0; j <= this.bmto.playerSetups.length - 1; ++j) {
                if (this.bmto.playerSetups[j] != this & this.bmto.playerSetups[j].color == this.color) {
                    this.bmto.playerSetups[j].incColor(true);
                    break;
                }
            }
        }
        this.btnColor.setImage(this.bmto.colorImages[this.color]);
    }
    
    BMTPlayer getPlayer() {
        switch (this.control) {
            case 4: {
                return new BMTComputerPlayer1(this.bmto.field, this.tfName.getText(), this.bmto.colors[this.color]);
            }
            case 5: {
                return new BMTComputerPlayer2(this.bmto.field, this.tfName.getText(), this.bmto.colors[this.color]);
            }
            case 6: {
                return new BMTComputerPlayer3(this.bmto.field, this.tfName.getText(), this.bmto.colors[this.color]);
            }
            default: {
                return new BMTHumanPlayer(this.bmto.field, this.tfName.getText(), this.bmto.colors[this.color], this.control);
            }
        }
    }
    
    boolean isPlayerEnabled() {
        return this.enabled;
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.active = true;
        this.repaint();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.active = false;
        this.repaint();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(480, 45);
    }
}
