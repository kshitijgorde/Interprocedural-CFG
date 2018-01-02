// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import java.awt.Container;
import java.awt.Button;
import jlog.awt.$I8.$YNB;
import java.awt.event.MouseListener;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import jlog.$BI.$M4;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Window;
import jlog.awt.$I8.$J8;
import java.awt.Dialog;
import java.awt.MenuItem;
import java.awt.PopupMenu;

class $DLC extends PopupMenu implements $K7B
{
    MenuItem $ELC;
    MenuItem $FLC;
    MenuItem $GLC;
    $YAC $ZAC;
    $MJC $HLC;
    Dialog $SLC;
    
    void $KLC() {
        final $EIC[] $ulc = this.$HLC.$ZDC.$ULC();
        if ($ulc != null) {
            this.$ZAC.$YLC($ulc[0]);
        }
    }
    
    void $LLC() {
        final $EIC[] $ulc = this.$HLC.$ZDC.$ULC();
        if ($ulc == null) {
            return;
        }
        if (this.$SLC == null) {
            (this.$SLC = new $VLC(this.$ZAC)).pack();
            $J8.$K8(this.$SLC);
        }
        (($VLC)this.$SLC).$WLC($ulc[0].getName());
        this.$SLC.pack();
        (($VLC)this.$SLC).$OOB();
    }
    
    void $XLC() {
        final $EIC[] $ulc = this.$HLC.$ZDC.$ULC();
        if ($ulc != null && this.$ZAC.$CBC != null) {
            for (int i = 0; i < $ulc.length; ++i) {
                this.$ZAC.$CBC.$KIC($ulc[i]);
            }
        }
    }
    
    $DLC(final $MJC $hlc, final $YAC $zac) {
        this.$SLC = null;
        this.$ZAC = $zac;
        this.$HLC = $hlc;
        (this.$ELC = new MenuItem("EDIT_AREA")).setEnabled(false);
        this.$ELC.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                $DLC.this.$KLC();
            }
        });
        (this.$FLC = new MenuItem("DELETE_AREA")).setEnabled(false);
        this.$FLC.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                $DLC.this.$LLC();
            }
        });
        (this.$GLC = new MenuItem("EDIT_ATTRIBUTE_PROPERTIES")).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                try {
                    final $MLC $mlc = ($MLC)$DLC.this.$ZAC.$OLC();
                    $mlc.$AEC($DLC.this.$HLC.$ZDC, $DLC.this.$ZAC.$CBC);
                    $mlc.$J7B();
                }
                catch (Exception ex) {
                    $M4.print(ex);
                }
            }
        });
        this.$GLC.setEnabled("ALL_AREAS".equals($hlc.$ZDC.getName()) ^ true);
        $hlc.list.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                boolean b = itemEvent.getStateChange() == 1;
                if (b) {
                    final String string = $DLC.this.$ZAC.$TEC.getString("NO_SELECTION");
                    if (string != null && string.equals($DLC.this.$HLC.list.getSelectedItem())) {
                        b = false;
                    }
                }
                $DLC.this.$ELC.setEnabled(b);
                $DLC.this.$FLC.setEnabled(b);
            }
        });
        $hlc.list.addKeyListener(new KeyAdapter() {
            public void keyPressed(final KeyEvent keyEvent) {
                final int keyCode = keyEvent.getKeyCode();
                if (keyCode == 10 && (keyEvent.getModifiers() & 0x2) != 0x0) {
                    $DLC.this.$KLC();
                }
                else if (keyCode == 127) {
                    $DLC.this.$LLC();
                }
            }
        });
        this.add(this.$ELC);
        this.add(this.$FLC);
        this.addSeparator();
        this.add(this.$GLC);
        final MouseAdapter mouseAdapter = new MouseAdapter() {
            void $RLC(final MouseEvent mouseEvent) {
                if (mouseEvent.isPopupTrigger()) {
                    final Component component = (Component)mouseEvent.getSource();
                    final String selectedItem = $DLC.this.$HLC.list.getSelectedItem();
                    if (selectedItem != null) {
                        $DLC.this.$ELC.setLabel($DLC.this.$ZAC.$TEC.getMessage("EDIT_AREA", selectedItem));
                        $DLC.this.$FLC.setLabel($DLC.this.$ZAC.$TEC.getMessage("DELETE_AREA", selectedItem));
                        $DLC.this.$GLC.setLabel($DLC.this.$ZAC.$TEC.getMessage("EDIT_ATTRIBUTE_PROPERTIES", $DLC.this.$HLC.$ZDC.getName()));
                        $DLC.this.show(component, mouseEvent.getX(), mouseEvent.getY());
                    }
                }
            }
            
            public void mouseClicked(final MouseEvent mouseEvent) {
                this.$RLC(mouseEvent);
            }
            
            public void mousePressed(final MouseEvent mouseEvent) {
                this.$RLC(mouseEvent);
            }
            
            public void mouseReleased(final MouseEvent mouseEvent) {
                this.$RLC(mouseEvent);
            }
        };
        $hlc.list.add(this);
        $hlc.list.addMouseListener(mouseAdapter);
    }
    
    class $VLC extends $YNB
    {
        private final /* synthetic */ $DLC this$0;
        Button $ZLC;
        Button $AMC;
        
        void $QEC(final Container container) {
            (this.$ZLC = new Button("Yes")).addActionListener(new ActionListener() {
                private final /* synthetic */ $VLC this$1 = this$1;
                
                public void actionPerformed(final ActionEvent actionEvent) {
                    this.this$1.setVisible(false);
                    this.this$1.this$0.$XLC();
                }
            });
            container.add(this.$ZLC);
            (this.$AMC = new Button("No")).addActionListener(new ActionListener() {
                private final /* synthetic */ $VLC this$1 = this$1;
                
                public void actionPerformed(final ActionEvent actionEvent) {
                    this.this$1.setVisible(false);
                }
            });
            container.add(this.$AMC);
        }
        
        void $WLC(final String s) {
            this.setText($DLC.this.$ZAC.$TEC.getMessage("DELETE_AREA_YES_NO", s));
            this.$ZLC.setLabel($DLC.this.$ZAC.$TEC.getString("Yes"));
            this.$AMC.setLabel($DLC.this.$ZAC.$TEC.getString("No"));
        }
        
        $VLC(final $YAC $yac) {
            super($yac.getFrame(), "", 320);
            final Container $cob = this.$COB();
            $cob.removeAll();
            this.$QEC($cob);
        }
    }
}
