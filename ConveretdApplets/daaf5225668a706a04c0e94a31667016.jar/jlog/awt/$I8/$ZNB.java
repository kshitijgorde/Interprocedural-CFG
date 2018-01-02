// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$I8;

import java.awt.Window;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.AWTError;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.Component;
import jlog.awt.$B4;
import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.Panel;
import java.awt.Dialog;

public class $ZNB extends Dialog implements $H9
{
    private String $IOB;
    private Panel $KOB;
    private ActionListener $LOB;
    private $GOB $MOB;
    
    public Container $COB() {
        if (this.$KOB == null) {
            this.$KOB = new Panel(new FlowLayout(1, 6, 4));
            final Panel panel = new Panel(new BorderLayout(0, 3));
            panel.add("North", new $B4());
            panel.add("Center", this.$KOB);
            this.add("South", panel);
        }
        return this.$KOB;
    }
    
    public ActionListener $DOB() {
        if (this.$LOB == null) {
            this.$LOB = new ActionListener() {
                public void actionPerformed(final ActionEvent actionEvent) {
                    $ZNB.access$1($ZNB.this, actionEvent.getActionCommand());
                    $ZNB.this.setVisible(false);
                }
            };
        }
        return this.$LOB;
    }
    
    public $GOB $EOB() {
        if (this.$MOB == null) {
            this.$MOB = new $GOB();
        }
        return this.$MOB;
    }
    
    public String $JOB() {
        if (this.$MOB != null) {
            final String $job = this.$MOB.$JOB();
            return ($job.length() != 0) ? $job : this.$IOB;
        }
        return this.$IOB;
    }
    
    public void $OOB() {
        new $POB(this).start();
    }
    
    public $ZNB(final Frame frame) {
        this(frame, null, true);
    }
    
    public $ZNB(final Frame frame, final String s) {
        this(frame, s, true);
    }
    
    $ZNB(final Frame frame, final String s, final boolean b) {
        super(frame, (s != null) ? s : "", b);
        this.$IOB = "CLOSEW";
        this.$KOB = null;
        this.$LOB = null;
        this.$MOB = null;
        if (!b) {
            throw new AWTError("Dialogs must be modal!");
        }
        if (frame == null) {
            throw new AWTError("parent=null");
        }
        this.setLayout(new BorderLayout());
        this.setBackground(SystemColor.control);
        this.setForeground(SystemColor.controlText);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                $ZNB.access$1($ZNB.this, "CLOSEW");
            }
        });
    }
    
    static /* synthetic */ void access$1(final $ZNB $znb, final String $iob) {
        $znb.$IOB = $iob;
    }
    
    public void setVisible(final boolean visible) {
        if (visible && !this.isShowing()) {
            $J8.$K8(this);
        }
        super.setVisible(visible);
    }
    
    public void show() {
        if (!this.isShowing()) {
            $J8.$K8(this);
        }
        super.show();
    }
}
