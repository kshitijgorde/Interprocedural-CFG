// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import java.beans.PropertyChangeEvent;
import jlog.$BI.$M4;
import java.awt.event.ActionEvent;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import jlog.util.$MB;
import java.util.ResourceBundle;
import java.util.Enumeration;
import java.awt.Component;
import java.text.Collator;
import jlog.util.$N;
import java.awt.Checkbox;
import jlog.$T5.$D7.$PJC;
import java.awt.event.ActionListener;
import jlog.$H4;
import jlog.util.$F;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeListener;
import java.awt.Panel;

public class $MJC extends Panel implements PropertyChangeListener, ItemListener, $X5B, $F, $H4, ActionListener
{
    static boolean $NJC;
    static boolean $OJC;
    $PJC list;
    $F8B $ZDC;
    Panel $QJC;
    Checkbox $RJC;
    String selected;
    
    public void $AEC(final $F8B $zdc) {
        if (this.$ZDC != null) {
            this.$ZDC.removePropertyChangeListener(this);
        }
        this.$ZDC = $zdc;
        if (this.$ZDC != null) {
            this.list.removeItemListener(this);
            final int size = this.$ZDC.$WJC.size();
            final String[] array = new String[size];
            final Enumeration keys = this.$ZDC.$WJC.getKeys();
            int n = 0;
            while (keys.hasMoreElements()) {
                array[n++] = keys.nextElement();
            }
            $N.$O(array, null);
            int n2 = -1;
            this.list.$AKC = false;
            for (int i = 0; i < size; ++i) {
                this.list.add(array[i]);
                if (array[i].equals(this.$ZDC.$BKC)) {
                    n2 = i;
                }
            }
            this.list.$AKC = true;
            if (n2 != -1) {
                this.list.select(n2);
            }
            this.$ZDC.addPropertyChangeListener(this, false);
            this.list.addItemListener(this);
            if ((this.$ZDC.$CKC() & 0x1) != 0x0) {
                if (this.$RJC.getParent() != this) {
                    this.add("South", this.$RJC);
                }
            }
            else {
                this.remove(this.$RJC);
            }
            this.$RJC.setState(this.$ZDC.$EKC);
        }
    }
    
    public void $G(final ResourceBundle resourceBundle) {
        if (resourceBundle == null) {
            return;
        }
        final $MB $mb = new $MB(resourceBundle);
        this.$RJC.setLabel($mb.getString("MARK_ON_MAP"));
        final String string = $mb.getString("NO_SELECTION");
        final int selectedIndex = this.list.getSelectedIndex();
        this.list.remove(0);
        this.list.$AKC = false;
        this.list.add(string, 0);
        this.list.$AKC = true;
        if (selectedIndex != -1) {
            this.list.select(selectedIndex);
        }
    }
    
    Checkbox $SJC() {
        final Checkbox checkbox = new Checkbox("Marker");
        checkbox.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                final boolean b = itemEvent.getStateChange() == 1;
                if ($MJC.this.$ZDC != null) {
                    $MJC.this.$ZDC.$TJC(b);
                }
            }
        });
        return checkbox;
    }
    
    static {
        $MJC.$NJC = false;
        $MJC.$OJC = false;
    }
    
    public $MJC(final $F8B $f8B) {
        super(new BorderLayout());
        this.list = new $PJC(5);
        this.$ZDC = null;
        this.selected = "";
        this.list.add("NO_SELECTION");
        this.$RJC = this.$SJC();
        this.add("Center", this.list);
        this.add("South", this.$RJC);
        this.$AEC($f8B);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (this.$ZDC == null || this.$ZDC.$CBC == null || this.$ZDC.$CBC.$VJC() == null || this.list == null || !actionEvent.getActionCommand().equals(this.$ZDC.$CBC.$VJC().$TEC.getString(this.$ZDC.getName()))) {
            return;
        }
        final $BBC $cbc = this.$ZDC.$CBC;
        final String item = this.list.getItem(0);
        final Enumeration $hk = $cbc.$LKC.$HK();
        while ($hk.hasMoreElements()) {
            final $F8B $f8B = $hk.nextElement();
            if ($f8B != this.$ZDC && $f8B != null) {
                $f8B.setSelectedEntry(item);
            }
        }
        this.$ZDC.setSelectedEntry(this.selected);
        this.list.select(this.selected);
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getStateChange() == 2) {
            return;
        }
        try {
            final Object[] selectedObjects = itemEvent.getItemSelectable().getSelectedObjects();
            if (selectedObjects == null || selectedObjects.length == 0) {
                return;
            }
            final String s = "";
            this.selected = (String)selectedObjects[0];
            String selectedEntry = this.selected;
            this.$ZDC.setSelectedEntry(selectedEntry);
            final $H0B $vjc = this.$ZDC.$UJC().$VJC();
            if ($vjc != null) {
                final Enumeration $xjc = this.$ZDC.$WJC.$XJC(selectedEntry);
                String s2 = String.valueOf(s) + "\n \n";
                String $cp = null;
                if ($xjc.hasMoreElements()) {
                    final $EIC $eic = $xjc.nextElement();
                    if (!"".equals($eic.getDescription())) {
                        s2 = String.valueOf(s2) + $eic.getDescription() + "\n";
                    }
                    final String $tob = $eic.$TOB;
                    if ($tob != null && $tob.length() != 0) {
                        if ($tob.indexOf(58) != -1) {
                            s2 = String.valueOf(s2) + " \n" + $tob + "\n";
                        }
                        if ($tob.startsWith("$") && !selectedEntry.startsWith("!A!")) {
                            selectedEntry = "!A! " + $eic.$UJC().vars.$P($tob, $eic.getName()) + " " + selectedEntry + " !A! ";
                        }
                        if ($cp == null) {
                            $cp = $eic.$CP;
                        }
                    }
                    s2 = String.valueOf(s2) + " \n";
                }
                $vjc.$YJC(String.valueOf(selectedEntry) + s2, $cp);
            }
        }
        catch (Throwable t) {
            $M4.print(t);
        }
    }
    
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        final Object newValue = propertyChangeEvent.getNewValue();
        final String propertyName = propertyChangeEvent.getPropertyName();
        boolean booleanValue = false;
        if (newValue instanceof Boolean) {
            booleanValue = (boolean)newValue;
        }
        if (propertyChangeEvent.getSource() != this.$ZDC) {
            return;
        }
        if (propertyName.equals("R_ADD_NAME")) {
            this.list.add((String)newValue);
        }
        else if (propertyName.equals("R_REMOVE_NAME")) {
            this.list.remove((String)newValue);
        }
        else if (propertyName.equals("PROP_MARKED")) {
            this.$RJC.setState(booleanValue);
        }
        else if (propertyName.equals("marker")) {
            if ((this.$ZDC.$CKC() & 0x1) != 0x0) {
                if (this.$RJC.getParent() != this) {
                    this.add("South", this.$RJC);
                }
            }
            else {
                this.remove(this.$RJC);
            }
        }
        else if (propertyName.equals("selected_name")) {
            final String s = (String)newValue;
            if (s != null && !s.equals("NO_SELECTION")) {
                this.list.select(this.list.indexOf(s));
            }
            else {
                this.list.select(0);
            }
        }
    }
}
