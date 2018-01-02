// 
// Decompiled by Procyon v0.5.30
// 

package risco_table;

import java.util.Vector;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Enumeration;
import java.awt.Component;
import java.util.Hashtable;
import java.applet.Applet;

public class TableAppletArray extends Applet
{
    private int m_iTableID;
    private Hashtable m_oTableIDs;
    
    public TableAppletArray() {
        this.m_oTableIDs = new Hashtable(1103);
    }
    
    public int getTableID() {
        return this.m_iTableID;
    }
    
    public TableApplet getTableApplet(final int iTableID) {
        this.m_iTableID = iTableID;
        TableApplet oApp = this.m_oTableIDs.get(Integer.toString(iTableID));
        if (oApp == null) {
            oApp = new TableApplet();
            oApp.setArray(this);
            this.m_oTableIDs.put(Integer.toString(iTableID), oApp);
        }
        return oApp;
    }
    
    public TableApplet setCurrentTable(final int iTableID) {
        final TableApplet oApp = this.m_oTableIDs.get(Integer.toString(iTableID));
        if (oApp == null) {
            return null;
        }
        this.removeAll();
        this.paintAll(this.getGraphics());
        this.repaint();
        this.m_iTableID = iTableID;
        if (!oApp.getHasPainted()) {
            oApp.paintTable();
        }
        else {
            this.add("Center", oApp.getTable());
        }
        oApp.refreshTable();
        return oApp;
    }
    
    public int getMaxTableCount() {
        final Enumeration enum1 = this.m_oTableIDs.keys();
        int iCount = 0;
        while (enum1.hasMoreElements()) {
            final Object obj = enum1.nextElement();
            final TableApplet oApp = this.m_oTableIDs.get(obj.toString());
            iCount = ((oApp.getRowCount() > iCount) ? oApp.getRowCount() : iCount);
        }
        return iCount;
    }
    
    public void paintTable(final Container oC) {
        this.removeAll();
        this.add("Center", oC);
        this.paintAll(this.getGraphics());
    }
    
    public void init() {
        this.setLayout(new BorderLayout());
    }
    
    public void syncCheckmarks(final int iTableID, final int iColumnIndex) {
        try {
            final TableApplet oAppD = this.m_oTableIDs.get(Integer.toString(this.m_iTableID));
            if (oAppD == null) {
                return;
            }
            final TableApplet oAppS = this.m_oTableIDs.get(Integer.toString(iTableID));
            if (oAppS == null) {
                return;
            }
            int[] aChecks = oAppS.getCheckedIndexes();
            if (aChecks.length == 0) {
                oAppD.setCheckedIndexes(aChecks);
            }
            final Hashtable hashStrings = new Hashtable(1103);
            final Vector checked = new Vector();
            for (int i = 0; i < aChecks.length; ++i) {
                hashStrings.put(oAppS.getRowByIndex(aChecks[i], iColumnIndex), "true");
            }
            for (int i = 0; i < oAppD.getRowCount(); ++i) {
                if (hashStrings.get(oAppD.getRowByIndex(i, iColumnIndex)) != null) {
                    checked.addElement(new Integer(i));
                }
            }
            aChecks = new int[checked.size()];
            for (int i = 0; i < aChecks.length; ++i) {
                aChecks[i] = checked.elementAt(i);
            }
            final boolean bSync = oAppD.getSyncChecksWithSelections();
            oAppD.setSyncChecksWithSelections(false);
            if (bSync) {
                oAppD.selectIndexes(aChecks);
            }
            oAppD.setCheckedIndexes(aChecks);
            oAppD.setSyncChecksWithSelections(bSync);
        }
        catch (Exception ex) {}
    }
    
    public class COMClassObject
    {
    }
}
