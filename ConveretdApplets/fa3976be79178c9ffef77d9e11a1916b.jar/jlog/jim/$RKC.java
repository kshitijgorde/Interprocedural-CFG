// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import jlog.$BI.$M4;
import java.beans.PropertyChangeEvent;
import java.awt.AWTEvent;
import java.awt.Font;
import jlog.awt.$X2B.$H3B;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.util.Enumeration;
import jlog.awt.$W5;
import java.awt.Container;
import java.net.MalformedURLException;
import java.awt.Polygon;
import java.awt.Rectangle;
import jlog.io.$JW;
import jlog.io.$P4;
import java.awt.Component;
import jlog.awt.$Z6.$X_B;
import java.net.URL;
import java.awt.Dimension;
import java.awt.Point;
import jlog.$T5.$D7.$XDC.$YRC;
import java.awt.Cursor;
import jlog.awt.$F6;
import java.util.Hashtable;
import jlog.$H4;
import java.beans.PropertyChangeListener;
import java.awt.event.MouseListener;
import jlog.$T5.$D7.$XDC.$SRC;

public class $RKC extends $SRC implements MouseListener, PropertyChangeListener, $H4
{
    $EIC $FIC;
    $TRC $URC;
    private Hashtable $VRC;
    static $F6 $CLC;
    private static Cursor $WRC;
    private static Cursor $XRC;
    private static $YRC $ZRC;
    
    void $CSC() {
        final Point location = this.getLocation();
        final Dimension preferredSize = this.$URC.getPreferredSize();
        if (preferredSize.width < 1 || preferredSize.height < 1) {
            return;
        }
        final Dimension size = this.getSize();
        location.translate((size.width - preferredSize.width) / 2, (size.height - preferredSize.height) / 2);
        if (!location.equals(this.$URC.getLocation()) || !preferredSize.equals(this.$URC.getSize())) {
            this.$URC.setBounds(location.x, location.y, preferredSize.width, preferredSize.height);
            this.$URC.validate();
        }
    }
    
    public String $ESC(final URL url) throws MalformedURLException {
        final Rectangle $s1B = (($X_B)this.getParent().getLayout()).$L1B(this).$S1B(10.0f, 10.0f);
        final Polygon $kcb = this.$KCB($s1B.width, $s1B.height);
        final String s = "<AREA SHAPE=";
        String s2;
        if (super.$CEC) {
            final int n = ($s1B.width + $s1B.height) / 4;
            s2 = String.valueOf(new StringBuffer(String.valueOf(s)).append("CIRCLE COORDS=\"").append($s1B.x + n).append(',').append($s1B.y + n).append(',').append(n).toString()) + "\"";
        }
        else if ($kcb != null) {
            String s3 = String.valueOf(s) + "POLYGON COORDS=\"";
            for (int i = 0; i < $kcb.npoints; ++i) {
                s3 = String.valueOf(s3) + ($kcb.xpoints[i] + $s1B.x) + "," + ($kcb.ypoints[i] + $s1B.y);
                if (i != $kcb.npoints - 1) {
                    s3 = String.valueOf(s3) + ',';
                }
            }
            s2 = String.valueOf(s3) + "\"";
        }
        else {
            s2 = String.valueOf(new StringBuffer(String.valueOf(s)).append("RECT COORDS=\"").append($s1B.x).append(",").append($s1B.y).append(",").append($s1B.x + $s1B.width - 1).append(",").append($s1B.y + $s1B.height - 1).toString()) + "\"";
        }
        final String name = this.$FIC.getName();
        if (!name.equals("")) {
            s2 = String.valueOf(s2) + " ALT=\"" + name + "\"";
        }
        final String $gsc = this.$FIC.$GSC();
        if (!$gsc.equals("")) {
            s2 = String.valueOf(s2) + " ATTR=\"" + $gsc + "\"";
        }
        final String description = this.$FIC.getDescription();
        if (!description.equals("")) {
            s2 = String.valueOf(s2) + " DESCRIPTION=\"" + description + "\"";
        }
        String s4 = this.$FIC.$VOB();
        if (s4 != null && !s4.equals("")) {
            if (s4.startsWith("file:")) {
                s4 = $JW.$BY($P4.$U4(url).toExternalForm(), s4);
            }
            s2 = String.valueOf(s2) + " HREF=\"" + s4 + "\"";
            final String $ep = this.$FIC.$EP();
            if ($ep != null && !$ep.equals("")) {
                s2 = String.valueOf(s2) + " TARGET=\"" + $ep + "\"";
            }
        }
        return String.valueOf(s2) + ">";
    }
    
    public void $HSC() {
        final $H0B $vjc = this.$FIC.$VJC();
        if ($vjc == null) {
            return;
        }
        String s = this.$FIC.getName();
        String string = "";
        if (this.$FIC.$UJC().getMode() == 1) {
            string = String.valueOf(string) + " (" + this.$FIC.$GSC() + ")";
        }
        String s2 = String.valueOf(string) + "\n \n";
        if (!"".equals(this.$FIC.getDescription())) {
            s2 = String.valueOf(s2) + this.$FIC.getDescription() + "\n";
        }
        final String $tob = this.$FIC.$TOB;
        if (!"".equals($tob) && $tob.indexOf(58) != -1) {
            s2 = String.valueOf(s2) + " \n" + this.$FIC.$TOB + "\n";
        }
        else if ($tob.startsWith("$")) {
            s = "!A! " + this.$FIC.$UJC().vars.$P($tob, this.$FIC.getName()) + " " + s + " !A! ";
        }
        $vjc.$YJC(String.valueOf(s) + s2);
    }
    
    void $JI(final Container container) {
        container.add(this.$URC, 0);
        container.add(this);
    }
    
    void $JPC(final $F8B $f8B, final boolean b) {
        if (b) {
            final $VDC $lsc = this.$LSC($f8B);
            this.$URC.add($lsc);
            this.$VRC.put($f8B, $lsc);
        }
        else {
            final $VDC $vdc = this.$VRC.get($f8B);
            $vdc.setVisible(false);
            this.$URC.remove($vdc);
            $vdc.setImage(null);
            this.$VRC.remove($f8B);
        }
    }
    
    void $KSC() {
        final Enumeration $hk = this.$FIC.$HK();
        while ($hk.hasMoreElements()) {
            final $F8B $f8B = $hk.nextElement();
            if (this.$FIC.getName().equals($f8B.getSelectedEntry()) && $f8B.$XOC != null) {
                super.$JSC = $f8B.$XOC;
                return;
            }
        }
        super.$JSC = this.$FIC.$CBC.$XOC;
    }
    
    $VDC $LSC(final $F8B $f8B) {
        final $VDC $vdc = new $VDC($f8B);
        $vdc.setFont(this.$FIC.$VJC().$VMC.getFont());
        if (this.isClickable()) {
            $vdc.setCursor(Cursor.getPredefinedCursor(12));
        }
        $vdc.addMouseListener(this);
        return $vdc;
    }
    
    public void $TKC(final $EIC $fic) {
        if (this.$FIC != null) {
            this.$FIC.removePropertyChangeListener(this);
            this.removeMouseListener(this);
            final Enumeration elements = this.$VRC.elements();
            final Container parent = this.getParent();
            while (parent != null && elements.hasMoreElements()) {
                final $VDC $vdc = elements.nextElement();
                $vdc.$AEC(null);
                parent.remove($vdc);
            }
            this.$VRC.clear();
        }
        this.$FIC = $fic;
        if (this.$FIC == null) {
            return;
        }
        this.$JCB(this.$FIC.p);
        this.setBounds(this.$FIC.getBounds());
        final Enumeration<$F8B> elements2 = this.$FIC.$EKC.elements();
        while (elements2.hasMoreElements()) {
            this.$JPC(elements2.nextElement(), true);
        }
        this.$CSC();
        if (this.$FIC.selected != 0) {
            this.$DSC(false);
            $RKC.$CLC.add(this);
            $RKC.$CLC.add(this.$URC);
        }
        else {
            $RKC.$CLC.remove(this);
            this.$DSC(this.isEnabled());
            $RKC.$CLC.remove(this.$URC);
        }
        this.$FIC.addPropertyChangeListener(this, false);
        this.addMouseListener(this);
    }
    
    void $YKC(final Container container) {
        container.remove(this.$URC);
        container.remove(this);
    }
    
    static {
        ($RKC.$CLC = new $F6(1200)).start();
        $RKC.$WRC = Cursor.getPredefinedCursor(12);
        $RKC.$XRC = Cursor.getPredefinedCursor(0);
        $RKC.$ZRC = null;
    }
    
    public $RKC(final $EIC $eic) {
        super(null, $eic.$ASC());
        this.$VRC = new Hashtable();
        this.$URC = new $TRC();
        super.$BSC = true;
        this.setForeground(Color.red);
        this.setBackground(Color.blue);
        this.$TKC($eic);
        this.enableEvents(1L);
    }
    
    public boolean isClickable() {
        return this.$FIC != null && this.$FIC.$VOB().length() != 0;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.$HSC();
        if (!this.isEnabled()) {
            return;
        }
        final int mouse = this.$FIC.getMouse();
        if (this.isClickable()) {
            this.setCursor($RKC.$WRC);
        }
        if ((mouse & 0x8) != 0x0) {
            synchronized (this) {
                if ($RKC.$ZRC != null) {
                    $RKC.$ZRC.setVisible(false);
                    this.getParent().remove($RKC.$ZRC);
                    $RKC.$ZRC = null;
                }
                final Point location;
                final Point point = location = this.getLocation();
                location.x += mouseEvent.getX();
                final Point point2 = point;
                point2.y += mouseEvent.getY();
                $RKC.$ZRC = new $YRC(this.$FIC.getName().replace('_', ' '));
                final $H0B $vjc = this.$FIC.$VJC();
                Font font = $vjc.$ISC.getFont();
                if (font == null) {
                    font = this.getFont();
                }
                $RKC.$ZRC.setFont(font);
                $RKC.$ZRC.setForeground($vjc.$ISC.getForeground());
                $RKC.$ZRC.setBackground($vjc.$ISC.getBackground());
                $RKC.$ZRC.addPopup(this.getParent(), new Point(point.x, point.y), (($H3B)this.getParent().getParent().getParent()).$F3B());
            }
        }
        if (!$RKC.$CLC.contains(this) && (mouse & 0x4) != 0x0) {
            super.$JSC = this.$FIC.$CBC.$XOC;
            this.$DSC(false);
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        final $H0B $vjc = this.$FIC.$VJC();
        if ($vjc != null) {
            $vjc.$YJC("");
        }
        if (!this.isEnabled()) {
            return;
        }
        if (this.$FIC.$VOB().length() != 0) {
            this.setCursor($RKC.$XRC);
        }
        synchronized (this) {
            if ($RKC.$ZRC != null) {
                $RKC.$ZRC.setVisible(false);
                this.getParent().remove($RKC.$ZRC);
                $RKC.$ZRC = null;
            }
        }
        if (!$RKC.$CLC.contains(this)) {
            this.$DSC(true);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (!this.isEnabled()) {
            return;
        }
        this.requestFocus();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (!this.isEnabled() || !mouseEvent.getComponent().contains(mouseEvent.getX(), mouseEvent.getY()) || mouseEvent.isPopupTrigger()) {
            return;
        }
        this.showDocument();
    }
    
    public void processEvent(final AWTEvent awtEvent) {
        final int id = awtEvent.getID();
        if (id == 100 || id == 101) {
            this.$CSC();
        }
        super.processEvent(awtEvent);
    }
    
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        final Object oldValue = propertyChangeEvent.getOldValue();
        final Object newValue = propertyChangeEvent.getNewValue();
        final String propertyName = propertyChangeEvent.getPropertyName();
        boolean booleanValue = false;
        if (newValue instanceof Boolean) {
            booleanValue = (boolean)newValue;
        }
        if (propertyChangeEvent.getSource() != this.$FIC) {
            return;
        }
        if (propertyName.equals("p")) {
            this.$JCB((Polygon)newValue);
        }
        else if (propertyName.equals("bounds")) {
            final Rectangle bounds = (Rectangle)newValue;
            if (bounds != null) {
                this.setBounds(bounds);
            }
        }
        else if (propertyName.equals("PROP_MARKED")) {
            this.$JPC(($F8B)oldValue, booleanValue);
            this.$CSC();
        }
        else if (propertyName.equals("selected")) {
            if (booleanValue) {
                this.$KSC();
                this.$DSC(false);
                $RKC.$CLC.add(this);
                $RKC.$CLC.add(this.$URC);
            }
            else {
                $RKC.$CLC.remove(this);
                $RKC.$CLC.remove(this.$URC);
                super.$JSC = this.$FIC.$CBC.$XOC;
                this.$DSC(this.isEnabled());
            }
        }
    }
    
    public void setEnabled(final boolean enabled) {
        if (enabled != this.isEnabled()) {
            boolean $nsc = enabled;
            if (!enabled && this.$FIC != null && this.$FIC.$CBC != null) {
                super.$JSC = this.$FIC.$CBC.$XOC;
            }
            this.$DSC(enabled);
            if ((this.$FIC.getIndex() & 0x4) == 0x0) {
                $nsc = false;
            }
            super.$NSC = $nsc;
            super.$OSC = ($nsc ^ true);
        }
        super.setEnabled(enabled);
    }
    
    void showDocument() {
        String s = this.$FIC.$VOB();
        if (s == null || s.length() == 0) {
            return;
        }
        try {
            final $BBC $ujc = this.$FIC.$UJC();
            if (s.startsWith("$")) {
                s = $ujc.vars.$P(s, this.$FIC.getName());
                if (s == null) {
                    return;
                }
            }
            final URL url = new URL($ujc.$VPC(), s);
            String s2 = this.$FIC.$CP;
            if (s2 == null || "".equals(s2)) {
                s2 = $ujc.$MPC;
            }
            $ujc.$VJC().showDocument(url, s2);
        }
        catch (MalformedURLException ex) {
            $M4.print(ex);
        }
    }
    
    public String toString() {
        return String.valueOf(this.getClass().getName()) + "[area=" + this.$FIC + " " + super.toString() + "]";
    }
}
