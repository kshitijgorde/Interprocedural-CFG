// 
// Decompiled by Procyon v0.5.30
// 

package jlog.$T5.$D7;

import java.awt.event.ActionEvent;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;
import java.awt.Container;
import jlog.awt.$G4;
import java.util.StringTokenizer;
import java.awt.Button;
import java.awt.Label;
import jlog.$H4;
import java.awt.event.ActionListener;
import java.awt.Panel;

public class $SGC extends Panel implements ActionListener, $H4
{
    $PJC $AKD;
    $PJC $BKD;
    Label $CKD;
    Label $DKD;
    Button $EKD;
    Button $FKD;
    public static final String $TGC = "ADD_ENTRY";
    public static final String $UGC = "REMOVE_ENTRY";
    
    public synchronized void $JKD(final String label, final String label2) {
        this.$EKD.setLabel(label);
        this.$EKD.setActionCommand("ADD_ENTRY");
        this.$FKD.setLabel(label2);
        this.$FKD.setActionCommand("REMOVE_ENTRY");
    }
    
    public static String $KKD(final String[] array) {
        final StringBuffer sb = new StringBuffer(256);
        for (int i = 0; i < array.length; ++i) {
            if (sb.length() != 0) {
                sb.append(';');
            }
            sb.append(array[i]);
        }
        return sb.toString();
    }
    
    public synchronized String $LKD() {
        return $KKD(this.$AKD.getItems());
    }
    
    public static String[] $MKD(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ";", false);
        final String[] array = new String[stringTokenizer.countTokens()];
        int n = 0;
        while (stringTokenizer.hasMoreElements()) {
            array[n++] = ((String)stringTokenizer.nextElement()).trim();
        }
        return array;
    }
    
    public synchronized void $PVC(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ";", false);
        while (stringTokenizer.hasMoreElements()) {
            final String trim = ((String)stringTokenizer.nextElement()).trim();
            try {
                this.$BKD.remove(trim);
            }
            catch (IllegalArgumentException ex) {}
            this.$AKD.add(trim);
        }
    }
    
    public synchronized void $QVC(final String s) {
        this.$AKD.removeAll();
        this.$PVC(s);
    }
    
    public synchronized $PJC $WVC() {
        return this.$AKD;
    }
    
    public $SGC() {
        this("", "", "", "");
    }
    
    public $SGC(final String s, final String s2, final String s3, final String selection) {
        (this.$AKD = new $PJC(6)).setMultipleMode(true);
        this.$AKD.addActionListener(this);
        this.$CKD = new Label();
        (this.$BKD = new $PJC(6)).setMultipleMode(true);
        this.$BKD.addActionListener(this);
        this.$DKD = new Label();
        this.$QVC(s2);
        this.setSelection(selection);
        this.setLabels(s, s3);
        (this.$EKD = new Button("ADD_ENTRY")).addActionListener(this);
        (this.$FKD = new Button("REMOVE_ENTRY")).addActionListener(this);
        final $G4 $g4 = new $G4(this);
        final GridBagConstraints constraints = $g4.getConstraints();
        constraints.anchor = 17;
        constraints.weightx = 0.0;
        $g4.add(this.$CKD, 0, 0);
        $g4.add(this.$DKD, 2, 0);
        constraints.fill = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.anchor = 10;
        $g4.add(this.$AKD, 0, 1, 1, 4);
        $g4.add(this.$BKD, 2, 1);
        constraints.fill = 0;
        constraints.insets = new Insets(8, 8, 8, 8);
        constraints.weightx = 0.0;
        $g4.add(this.$EKD, 1, 2, 1, 1);
        $g4.add(this.$FKD, 1, 3);
    }
    
    public synchronized void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.equals("ADD_ENTRY")) {
            final String[] selectedItems = this.$AKD.getSelectedItems();
            this.addSelection($KKD(selectedItems));
            this.$BKD.select(selectedItems);
        }
        else if (actionEvent.getSource() == this.$AKD) {
            this.addSelection(actionCommand);
            this.$BKD.select(actionCommand);
        }
        else if (actionCommand.equals("REMOVE_ENTRY")) {
            final String[] selectedItems2 = this.$BKD.getSelectedItems();
            this.$PVC($KKD(selectedItems2));
            this.$AKD.select(selectedItems2);
        }
        else if (actionEvent.getSource() == this.$BKD) {
            this.$PVC(actionCommand);
            this.$AKD.select(actionCommand);
        }
    }
    
    public synchronized void addSelection(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ";", false);
        while (stringTokenizer.hasMoreElements()) {
            final String trim = ((String)stringTokenizer.nextElement()).trim();
            try {
                this.$AKD.remove(trim);
            }
            catch (IllegalArgumentException ex) {}
            this.$BKD.add(trim);
        }
    }
    
    public synchronized $PJC getListSelection() {
        return this.$BKD;
    }
    
    public synchronized Object[] getSelectedObjects() {
        return this.$BKD.getItems();
    }
    
    public synchronized String getSelection() {
        return $KKD(this.$BKD.getItems());
    }
    
    public synchronized void remove(final String s) {
        if (this.$BKD.indexOf(s) != -1) {
            this.$BKD.remove(s);
        }
        else if (this.$AKD.indexOf(s) != -1) {
            this.$AKD.remove(s);
        }
    }
    
    public synchronized void rename(final String s, final String s2) {
        if (this.$BKD.indexOf(s) != -1) {
            this.$BKD.remove(s);
            this.$BKD.add(s2);
        }
        else if (this.$AKD.indexOf(s) != -1) {
            this.$AKD.remove(s);
            this.$AKD.add(s2);
        }
    }
    
    public void setLabels(final String text, final String text2) {
        this.$DKD.setText(text2);
        this.$CKD.setText(text);
    }
    
    public synchronized void setSelection(final String s) {
        this.$BKD.removeAll();
        this.addSelection(s);
    }
}
