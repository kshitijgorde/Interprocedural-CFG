// 
// Decompiled by Procyon v0.5.30
// 

package org.bm.bmtron;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.Container;

public class PlayerSetup extends Container implements MouseListener, ActionListener
{
    private final Game game;
    private final SettingsScreen settings;
    private boolean active;
    private boolean enabled;
    private int maxControl;
    private int maxColor;
    private int control;
    private int color;
    private Button btnEnabled;
    private Button btnControl;
    private Button btnColor;
    private TextField nameField;
    
    PlayerSetup(final SettingsScreen settings, final boolean enabled, final int control, final int color, final String s) {
        this.active = false;
        this.settings = settings;
        this.game = settings.game;
        this.enabled = enabled;
        this.control = control;
        this.color = color;
        this.maxControl = this.game.skin.controls.length - 1;
        this.maxColor = settings.colors.length - 1;
        this.setSize(this.getPreferredSize());
        this.setLayout(null);
        this.btnEnabled = new Button(enabled ? this.game.skin.yes : this.game.skin.no, this.game);
        this.btnControl = new Button(this.game.skin.controls[control], this.game);
        this.btnColor = new Button(settings.colorImages[color], this.game);
        this.nameField = new TextField(s, this.game);
        this.btnEnabled.setLocation(10, 10);
        this.nameField.setLocation(44, 5);
        this.btnColor.setLocation(387, 10);
        this.btnControl.setLocation(421, 5);
        this.btnEnabled.addMouseListener(this);
        this.btnControl.addMouseListener(this);
        this.btnColor.addMouseListener(this);
        this.nameField.addMouseListener(this);
        this.btnEnabled.addActionListener(this);
        this.btnControl.addActionListener(this);
        this.btnColor.addActionListener(this);
        this.add(this.btnEnabled);
        this.add(this.btnControl);
        this.add(this.btnColor);
        this.add(this.nameField);
        this.addMouseListener(this);
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        if (this.active) {
            graphics.setColor(this.game.skin.activePanelBorderColor);
            graphics.drawRect(0, 0, this.getSize().width - 1, this.getSize().height - 1);
            graphics.drawRect(1, 1, this.getSize().width - 3, this.getSize().height - 3);
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.btnEnabled) {
            this.setPlayerEnabled(!this.enabled);
            int n = 0;
            for (int i = 0; i <= this.settings.playerSetups.length - 1; ++i) {
                if (this.settings.playerSetups[i].enabled) {
                    ++n;
                }
            }
            if (n < 2) {
                for (int j = 0; j <= this.settings.playerSetups.length - 1; ++j) {
                    if (this.settings.playerSetups[j] != this & !this.settings.playerSetups[j].enabled) {
                        this.settings.playerSetups[j].setPlayerEnabled(true);
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
        this.btnEnabled.setImage(enabled ? this.game.skin.yes : this.game.skin.no);
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
            for (int i = 0; i <= this.settings.playerSetups.length - 1; ++i) {
                if (this.settings.playerSetups[i] != this & this.settings.playerSetups[i].control == this.control) {
                    b2 = true;
                    break;
                }
            }
        } while (b & b2);
        if (!b & this.control <= 3) {
            for (int j = 0; j <= this.settings.playerSetups.length - 1; ++j) {
                if (this.settings.playerSetups[j] != this & this.settings.playerSetups[j].control == this.control) {
                    this.settings.playerSetups[j].incControl(true);
                    break;
                }
            }
        }
        this.btnControl.setImage(this.game.skin.controls[this.control]);
    }
    
    void incColor(final boolean b) {
        boolean b2;
        do {
            ++this.color;
            if (this.color > this.maxColor) {
                this.color = 0;
            }
            b2 = false;
            for (int i = 0; i <= this.settings.playerSetups.length - 1; ++i) {
                if (this.settings.playerSetups[i] != this & this.settings.playerSetups[i].color == this.color) {
                    b2 = true;
                    break;
                }
            }
        } while (b & b2);
        if (!b) {
            for (int j = 0; j <= this.settings.playerSetups.length - 1; ++j) {
                if (this.settings.playerSetups[j] != this & this.settings.playerSetups[j].color == this.color) {
                    this.settings.playerSetups[j].incColor(true);
                    break;
                }
            }
        }
        this.btnColor.setImage(this.settings.colorImages[this.color]);
    }
    
    Player getPlayer() {
        switch (this.control) {
            case 4: {
                return new ComputerPlayer1(this.game.field, this.nameField.getText(), this.settings.colors[this.color]);
            }
            case 5: {
                return new ComputerPlayer2(this.game.field, this.nameField.getText(), this.settings.colors[this.color]);
            }
            case 6: {
                return new ComputerPlayer3(this.game.field, this.nameField.getText(), this.settings.colors[this.color]);
            }
            default: {
                return new HumanPlayer(this.game.field, this.nameField.getText(), this.settings.colors[this.color], this.control);
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
