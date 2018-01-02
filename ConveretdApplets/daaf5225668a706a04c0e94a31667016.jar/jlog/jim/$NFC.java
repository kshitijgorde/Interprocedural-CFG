// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import java.util.Enumeration;
import jlog.awt.$ZSB.$FTB;
import jlog.$BI.$M4;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import jlog.$T5.$D7.$ZAC.$HJC;
import java.util.ResourceBundle;
import java.util.Locale;
import jlog.$T5.$D7.$YFC;
import java.awt.CheckboxMenuItem;
import java.awt.Menu;
import java.awt.MenuItem;
import jlog.awt.$ZSB.$UTB;
import jlog.util.$F;
import jlog.$H4;
import jlog.$T5.$D7.$ZAC.$OIC;
import java.awt.event.ActionListener;
import java.awt.MenuBar;

class $NFC extends MenuBar implements $V0B, $X5B, ActionListener, $OIC, $ADC, $H4, $F, $UTB
{
    $YAC $ZAC;
    MenuItem $CTC;
    MenuItem $DTC;
    MenuItem $ETC;
    MenuItem load;
    MenuItem $FTC;
    MenuItem save;
    MenuItem $GTC;
    MenuItem delete;
    MenuItem clone;
    MenuItem $HTC;
    MenuItem $AGC;
    MenuItem $W_;
    MenuItem $BGC;
    MenuItem select;
    MenuItem $X1;
    MenuItem $CGC;
    MenuItem $DGC;
    MenuItem $ITC;
    MenuItem $JTC;
    MenuItem english;
    MenuItem $KTC;
    MenuItem $LTC;
    MenuItem $MTC;
    Menu $NTC;
    Menu $OTC;
    Menu $PTC;
    Menu $QTC;
    Menu $RTC;
    Menu $STC;
    CheckboxMenuItem $TTC;
    CheckboxMenuItem $UTC;
    CheckboxMenuItem $VTC;
    CheckboxMenuItem $WTC;
    CheckboxMenuItem $XTC;
    CheckboxMenuItem $YTC;
    CheckboxMenuItem $ZTC;
    Menu $AUC;
    
    public void $CUC() {
        this.$NTC = this.$FUC();
        this.$OTC = new Menu("EDIT");
        this.delete = $YFC.addItem(null, "DELETE", 127);
        this.clone = $YFC.addItem(null, "CLONE", 0);
        this.$HTC = $YFC.addItem(null, "SELECT_ALL", 65);
        this.$PTC = new Menu("MODE");
        this.select = $YFC.addItem(null, "SELECT", 0);
        this.$AGC = $YFC.addItem(null, "BOX", 66);
        this.$BGC = $YFC.addItem(null, "CIRCLE", 0);
        this.$W_ = $YFC.addItem(null, "POLY", 0);
        this.$X1 = $YFC.addItem(null, "TEST", 10);
        this.$CGC = $YFC.addItem(null, "AREA_PROPERTIES", 0);
        this.$DGC = $YFC.addItem(null, "ATTRIB_EDIT", 0);
        this.$QTC = this.$GUC();
        this.$STC = this.$HUC();
    }
    
    void $DUC() {
        this.add(this.$NTC);
        this.$OTC.add(this.delete);
        this.$OTC.add(this.clone);
        this.$OTC.add(this.$HTC);
        this.add(this.$OTC);
        this.$PTC.add(this.select);
        this.$PTC.addSeparator();
        this.$PTC.add(this.$AGC);
        this.$PTC.add(this.$BGC);
        this.$PTC.add(this.$W_);
        this.$PTC.addSeparator();
        this.$PTC.add(this.$CGC);
        this.$PTC.add(this.$DGC);
        this.$PTC.addSeparator();
        this.$PTC.add(this.$X1);
        this.add(this.$PTC);
        this.add(this.$QTC);
        this.add(this.$STC);
    }
    
    void $EUC() {
        if (this.$ZAC == null) {
            return;
        }
        final String $rj = this.$ZAC.$RJ;
        final boolean b = this.$ZAC.$CBC != null;
        final boolean equals = this.$ZAC.$CTB.$WTB().equals("CARD_APPLICATION");
        this.save.setEnabled(b && equals);
        this.$GTC.setEnabled(b && equals);
        this.$DGC.setEnabled(b && equals);
        this.$ETC.setEnabled(equals && !$rj.equals("TEST"));
        this.$AGC.setEnabled(!$rj.equals("BOX") && b && equals);
        this.$BGC.setEnabled(!$rj.equals("CIRCLE") && b && equals);
        this.$W_.setEnabled(!$rj.equals("POLY") && b && equals);
        this.select.setEnabled(!$rj.equals("SELECT") && b && equals);
        this.$X1.setEnabled(!$rj.equals("TEST") && b && equals);
        this.$ITC.setEnabled(this.$ZAC.$CBC != null && equals);
        this.english.setEnabled(Locale.ENGLISH.getLanguage().equals(this.$ZAC.getLocale().getLanguage()) ^ true);
        this.$JTC.setEnabled(Locale.GERMAN.equals(this.$ZAC.getLocale()) ^ true);
        this.$ZTC.setState(this.$ZAC.$OUC());
        this.$TTC.setState(this.$ZAC.$UF.$ZE("SAVE_BACKUP", false));
        this.$UTC.setState(this.$ZAC.$UF.$ZE("SAVE_BACKUP_VERSIONS", false));
        this.$VTC.setState(this.$ZAC.$UF.$ZE("SAVE_VIEWER_CLASSES", false));
        this.$WTC.setState(this.$ZAC.$UF.$ZE("SAVE_STARTER_WINDOWS", false));
        this.$XTC.setState(this.$ZAC.$UF.$ZE("SAVE_HTML", false));
        this.$YTC.setState(this.$ZAC.$UF.$ZE("SAVE_HTML_PLUGIN", false));
        boolean hasMoreElements = false;
        if (this.$ZAC.$PFC != null) {
            hasMoreElements = this.$ZAC.$PFC.getSelected().hasMoreElements();
        }
        final boolean equals2 = $rj.equals("TEST");
        this.delete.setEnabled(hasMoreElements && !equals2 && b && equals);
        this.$HTC.setEnabled(!equals2 && b && equals);
        this.clone.setEnabled(hasMoreElements && !equals2 && b && equals);
        this.$CGC.setEnabled(hasMoreElements && !equals2 && b && equals);
    }
    
    Menu $FUC() {
        final Menu menu = new Menu("FILE");
        this.$ETC = $YFC.addItem(menu, "MAP_ASSISTENT", 0);
        menu.addSeparator();
        this.save = $YFC.addItem(menu, "SAVE", 83);
        this.$GTC = $YFC.addItem(menu, "SAVE AS..", 0);
        menu.addSeparator();
        this.$CTC = $YFC.addItem(menu, "QUIT", 81);
        return menu;
    }
    
    public void $G(final ResourceBundle resourceBundle) {
        if (resourceBundle == null) {
            return;
        }
        synchronized (this) {
            int menuCount = this.getMenuCount();
            while (menuCount-- != 0) {
                $YFC.setLanguage(this.getMenu(menuCount), resourceBundle);
            }
        }
        this.$EUC();
    }
    
    public void $GJC(final $HJC $hjc) {
        switch ($hjc.getType()) {
            case 3:
            case 4: {
                this.$EUC();
                break;
            }
        }
    }
    
    Menu $GUC() {
        final Menu menu = new Menu("PROPERTIES");
        final ItemListener $juc = this.$JUC();
        this.$ITC = $YFC.addItem(menu, "CHANGE_BACKGROUND", 0);
        menu.addSeparator();
        this.$AUC = new Menu("LANGUAGE");
        this.english = $YFC.addItem(this.$AUC, "ENGLISH", 0);
        this.$JTC = $YFC.addItem(this.$AUC, "GERMAN", 0);
        menu.add(this.$AUC);
        this.$RTC = new Menu("MENU_SAVEPROPERTIES");
        (this.$TTC = new CheckboxMenuItem("SAVE_BACKUP")).addItemListener($juc);
        this.$RTC.add(this.$TTC);
        (this.$UTC = new CheckboxMenuItem("SAVE_BACKUP_VERSIONS")).addItemListener($juc);
        this.$RTC.add(this.$UTC);
        this.$RTC.addSeparator();
        (this.$VTC = new CheckboxMenuItem("SAVE_VIEWER_CLASSES")).addItemListener($juc);
        this.$RTC.add(this.$VTC);
        (this.$WTC = new CheckboxMenuItem("SAVE_STARTER_WINDOWS")).addItemListener($juc);
        this.$RTC.add(this.$WTC);
        (this.$XTC = new CheckboxMenuItem("SAVE_HTML")).addItemListener($juc);
        this.$RTC.add(this.$XTC);
        (this.$YTC = new CheckboxMenuItem("SAVE_HTML_PLUGIN")).addItemListener($juc);
        this.$RTC.add(this.$YTC);
        menu.add(this.$RTC);
        (this.$ZTC = new CheckboxMenuItem("DOCUMENT_AUTOLOAD", false)).addItemListener($juc);
        menu.add(this.$ZTC);
        $YFC.addActionListener(menu, this.$KUC());
        return menu;
    }
    
    Menu $HUC() {
        final Menu menu = new Menu("?");
        this.$LTC = $YFC.addItem(menu, "MANUAL", 0);
        menu.addSeparator();
        this.$MTC = $YFC.addItem(menu, "ABOUT", 0);
        return menu;
    }
    
    ItemListener $JUC() {
        return new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                final Object source = itemEvent.getSource();
                final boolean b = itemEvent.getStateChange() == 1;
                if (source == $NFC.this.$ZTC) {
                    $NFC.this.$ZAC.setDocumentAutoload(b);
                }
                else if (source == $NFC.this.$TTC) {
                    $NFC.this.$ZAC.$UF.$AF("SAVE_BACKUP", b);
                }
                else if (source == $NFC.this.$UTC) {
                    $NFC.this.$ZAC.$UF.$AF("SAVE_BACKUP_VERSIONS", b);
                }
                else if (source == $NFC.this.$VTC) {
                    $NFC.this.$ZAC.$UF.$AF("SAVE_VIEWER_CLASSES", b);
                    $NFC.this.$XTC.setEnabled(b);
                    $NFC.this.$YTC.setEnabled(b);
                    $NFC.this.$WTC.setEnabled(b);
                }
                else if (source == $NFC.this.$XTC) {
                    $NFC.this.$ZAC.$UF.$AF("SAVE_HTML", b);
                }
                else if (source == $NFC.this.$YTC) {
                    $NFC.this.$ZAC.$UF.$AF("SAVE_HTML_PLUGIN", b);
                }
                else if (source == $NFC.this.$WTC) {
                    $NFC.this.$ZAC.$UF.$AF("SAVE_STARTER_WINDOWS", b);
                }
            }
        };
    }
    
    ActionListener $KUC() {
        return new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                try {
                    final String actionCommand = actionEvent.getActionCommand();
                    if (actionCommand.equals("ENGLISH")) {
                        $NFC.this.$ZAC.$LUC(Locale.ENGLISH);
                    }
                    else if (actionCommand.equals("GERMAN")) {
                        $NFC.this.$ZAC.$LUC(Locale.GERMAN);
                    }
                    else if (actionCommand.equals("DEBUGGING")) {
                        $M4.print("debug");
                    }
                    else if (actionCommand.equals("ABOUT")) {
                        $NFC.this.$ZAC.$MTC();
                    }
                }
                catch (Throwable t) {
                    $M4.print(t);
                }
            }
        };
    }
    
    public void $VTB(final $FTB $ftb, final String s) {
        this.$EUC();
    }
    
    public $NFC(final $YAC $zac, final boolean b) {
        this.$ZAC = null;
        this.$ZAC = $zac;
        this.$CUC();
        if (!b) {
            this.$DUC();
        }
        this.$ZAC.$TEC.$NB(this);
        this.$G(this.$ZAC.$RB());
        this.$EUC();
        this.addActionListener(this);
        this.$ZAC.$CTB.$XTB(this);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        try {
            if (actionCommand.equals("New")) {
                this.$ZAC.$XNC();
            }
            else if (actionCommand.equals("LOAD..")) {
                this.$ZAC.$DFC();
            }
            else if (actionCommand.equals("MAP_ASSISTENT")) {
                this.$ZAC.$IJC();
            }
            else if (actionCommand.equals("SAVE")) {
                this.$ZAC.$IGC();
            }
            else if (actionCommand.equals("SAVE AS..")) {
                this.$ZAC.$VEC();
            }
            else if (actionCommand.equals("QUIT")) {
                this.$ZAC.$FGC();
            }
            else if (actionCommand.equals("DELETE")) {
                this.$ZAC.delete();
            }
            else if (actionCommand.equals("SELECT_ALL")) {
                this.$ZAC.$PFC.$OBB(null, true);
            }
            else if (actionCommand.equals("AREA_PROPERTIES")) {
                this.$ZAC.$RUC();
            }
            else if (actionCommand.equals("CLONE")) {
                this.$ZAC.$KJC();
            }
            else if (actionCommand.equals("BOX") || actionCommand.equals("CIRCLE") || actionCommand.equals("POLY") || actionCommand.equals("SELECT") || actionCommand.equals("TEST")) {
                this.$ZAC.$LJC(actionCommand);
            }
            else if (actionCommand.equals("ATTRIB_EDIT")) {
                this.$ZAC.$JJC();
            }
            else if (actionCommand.equals("CHANGE_BACKGROUND")) {
                this.$ZAC.$SUC();
            }
            else if (actionCommand.equals("MANUAL")) {
                this.$ZAC.$TUC();
            }
            else if (actionCommand.equals("ABOUT")) {
                this.$ZAC.$MTC();
            }
        }
        catch (Exception ex) {
            $M4.print(ex);
        }
    }
    
    public synchronized void addActionListener(final ActionListener actionListener) {
        int menuCount = this.getMenuCount();
        while (menuCount-- != 0) {
            $YFC.addActionListener(this.getMenu(menuCount), actionListener);
        }
    }
    
    public synchronized void removeActionListener(final ActionListener actionListener) {
        int menuCount = this.getMenuCount();
        while (menuCount-- != 0) {
            $YFC.removeActionListener(this.getMenu(menuCount), actionListener);
        }
    }
}
