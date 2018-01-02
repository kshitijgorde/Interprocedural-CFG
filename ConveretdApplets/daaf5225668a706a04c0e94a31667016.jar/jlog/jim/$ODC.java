// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import java.awt.Window;
import jlog.awt.$I8.$J8;
import java.awt.Label;
import java.awt.Frame;
import java.io.IOException;
import java.awt.Image;
import java.util.Properties;
import java.awt.Component;
import jlog.util.$UD.$XD;
import jlog.util.$MC.$NC;
import jlog.util.$MC.$QC;

public class $ODC extends $PDC
{
    void $O6() throws IOException {
        final $QC $qc = new $QC();
        final Properties $qdc = $PDC.$QDC(super.$IB, $qc, "jlog/jim/Editor.properties");
        (($XD)$qdc).$PE = $PDC.$QDC(super.$IB, $qc, "jlog/jim/Viewer.properties");
        this.showStatus("loading icon");
        final Image $pc = $qc.$PC("jlog/jim/images/jim_edit_icon.jpg");
        if ($pc != null) {
            super.$KB.setIconImage($pc);
        }
        this.showStatus("loading classes");
        final $YAC $yac = new $YAC();
        this.$RDC($yac, $qc, $qdc);
        this.showStatus("embedding application");
        super.$KB.add("Center", $yac);
        super.$KB.validate();
        super.$KB.repaint();
        this.showStatus("init application");
        $yac.init();
        this.showStatus("start application");
        $yac.start();
    }
    
    $ODC(final Frame frame, final String[] array) {
        super(frame, array);
    }
    
    public static void main(final String[] array) {
        try {
            final Frame $sdc = $PDC.$SDC("Map Editor", false);
            $sdc.setSize(500, 200);
            $sdc.add("South", $PDC.$TDC = new Label("loading.."));
            new $ODC($sdc, array).start();
            $J8.$K8($sdc);
            $sdc.setVisible(true);
        }
        catch (Exception ex) {
            $PDC.$UDC(ex);
        }
    }
}
