// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$B8;

import java.awt.CardLayout;
import java.awt.event.KeyEvent;
import java.awt.Container;
import java.awt.Image;
import java.awt.AWTEventMulticaster;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.util.Hashtable;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.Panel;

public class $BJB extends Panel implements ActionListener, KeyListener
{
    public static final int $CJB = 0;
    public static final int $DJB = 1;
    protected Panel $EJB;
    protected Panel $FJB;
    protected $GJB $HJB;
    $PIB $IJB;
    Hashtable $JJB;
    ActionListener $LJB;
    int $MJB;
    
    public $GJB $OJB(final String s) {
        $GJB $gjb = null;
        final Component[] components = this.$EJB.getComponents();
        for (int i = 0; i < components.length; ++i) {
            if (components[i] instanceof $GJB && ((($GJB)components[i]).$QJB().equals(s) || s.equals((($GJB)components[i]).$MP))) {
                $gjb = ($GJB)components[i];
                break;
            }
        }
        return $gjb;
    }
    
    public $GJB $RJB() {
        return this.$HJB;
    }
    
    public $BJB() {
        this.$HJB = null;
        this.$LJB = null;
        this.setLayout(new BorderLayout(0, 0));
        this.$JJB = new Hashtable();
        this.add("North", this.$EJB = new Panel());
        this.add("Center", this.$FJB = new $KJB());
        this.$EJB.setLayout(this.$IJB = new $PIB());
        this.$EJB.addKeyListener(this);
        this.$FJB.addKeyListener(this);
        this.addKeyListener(this);
    }
    
    public $BJB(final Color background) {
        this();
        this.$FJB.setBackground(background);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof $GJB) {
            this.select(($GJB)actionEvent.getSource());
        }
    }
    
    public void addActionListener(final ActionListener actionListener) {
        this.$LJB = AWTEventMulticaster.add(this.$LJB, actionListener);
    }
    
    public $GJB addItem(final String s, final Component component) {
        return this.addItem(s, null, 0, 0, component);
    }
    
    public $GJB addItem(final String s, final Image image, final int n, final int n2, final Component component) {
        ++this.$MJB;
        final $GJB $gjb = new $GJB(String.valueOf(this.$MJB), s, this.$FJB.getBackground());
        $gjb.setImage(image, n, n2);
        return this.addItem($gjb, component);
    }
    
    public $GJB addItem(final $GJB $hjb, final Component component) {
        $hjb.addActionListener(this);
        $hjb.addKeyListener(this);
        this.$EJB.add($hjb);
        this.$FJB.add($hjb.$NJB, component);
        if (this.$HJB == null) {
            (this.$HJB = $hjb).setState(true);
            this.$IJB.$YIB($hjb, this.$EJB);
        }
        this.$JJB.put($hjb, component);
        return $hjb;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 9) {
            final int n = keyEvent.getModifiers() & 0x3;
            if (n == 3) {
                this.select(1);
            }
            else if (n == 2) {
                this.select(0);
            }
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void removeActionListener(final ActionListener actionListener) {
        this.$LJB = AWTEventMulticaster.remove(this.$LJB, actionListener);
    }
    
    public void removeItem(final $GJB $gjb) {
        this.$EJB.remove($gjb);
        final Component component = this.$JJB.get($gjb);
        if (component != null) {
            this.$FJB.remove(component);
        }
        this.$JJB.remove($gjb);
        $gjb.removeKeyListener(this);
        $gjb.removeActionListener(this);
        this.validate();
        if ($gjb == this.$HJB && !this.$JJB.isEmpty()) {
            this.select(this.$JJB.keys().nextElement());
        }
    }
    
    public void select(final int n) {
        final Component[] components = this.$EJB.getComponents();
        if (this.$HJB == null) {
            this.select(($GJB)components[0]);
        }
        else {
            int n2;
            for (n2 = 0; n2 < components.length && components[n2] != this.$HJB; ++n2) {}
            if (n2 >= components.length) {
                this.select(($GJB)components[0]);
            }
            else if (n == 1) {
                this.select(($GJB)components[(n2 == 0) ? (components.length - 1) : (n2 - 1)]);
            }
            else {
                this.select(($GJB)components[(n2 == components.length - 1) ? 0 : (n2 + 1)]);
            }
        }
    }
    
    public void select(final String s) {
        final $GJB $ojb;
        if (($ojb = this.$OJB(s)) != null) {
            this.select($ojb);
        }
    }
    
    protected void select(final $GJB $hjb) {
        if ($hjb == null) {
            return;
        }
        this.$IJB.$YIB($hjb, this.$EJB);
        if (this.$HJB != null && this.$HJB != $hjb) {
            this.$HJB.setState(false);
            this.$HJB.repaint();
        }
        (this.$HJB = $hjb).setState(true);
        this.$HJB.repaint();
        this.$FJB.repaint();
        ((CardLayout)this.$FJB.getLayout()).show(this.$FJB, $hjb.$QJB());
        if (this.$LJB != null) {
            this.$LJB.actionPerformed(new ActionEvent(this, 1001, $hjb.getLabel()));
        }
    }
}
