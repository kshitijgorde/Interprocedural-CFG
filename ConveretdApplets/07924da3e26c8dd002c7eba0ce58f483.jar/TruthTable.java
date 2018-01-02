import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import java.awt.Font;
import javax.swing.JPopupMenu;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.Color;
import javax.swing.JTable;

// 
// Decompiled by Procyon v0.5.30
// 

public class TruthTable implements UpdateEvent
{
    private JTable _truthTable;
    private BitRadioButton[] _bitRadioInputs;
    private BitRadioButton[] _bitRadioOutputs;
    
    public TruthTable(final BitRadioButton[] bitRadioInputs, final BitRadioButton[] bitRadioOutputs) {
        this._bitRadioInputs = bitRadioInputs;
        this._bitRadioOutputs = bitRadioOutputs;
        if (this._bitRadioInputs == null || this._bitRadioOutputs == null) {
            return;
        }
        final String[] arrHeader = new String[this._bitRadioInputs.length + this._bitRadioOutputs.length];
        final String[][] arrValues = new String[(int)Math.pow(2.0, this._bitRadioInputs.length)][this._bitRadioInputs.length + this._bitRadioOutputs.length];
        for (int i = this._bitRadioInputs.length; i > 0; --i) {
            arrHeader[this._bitRadioInputs.length - i] = "<html><font color=\"#00009B\"><b>x<sub>" + (i - 1) + "</sub></b></font></html>";
        }
        for (int i = this._bitRadioOutputs.length; i > 0; --i) {
            arrHeader[this._bitRadioInputs.length + (this._bitRadioOutputs.length - i)] = "<html><font color=\"#009B00\"><b>z<sub>" + (i - 1) + "</sub></b></font></html>";
        }
        for (int i = 0; i < (int)Math.pow(2.0, this._bitRadioInputs.length); ++i) {
            for (int j = 0; j < this._bitRadioInputs.length + this._bitRadioOutputs.length; ++j) {
                arrValues[i][j] = "";
            }
        }
        (this._truthTable = new JTable(arrValues, arrHeader)).setSelectionMode(0);
        this._truthTable.getTableHeader().setReorderingAllowed(false);
        this._truthTable.setEnabled(false);
        this._truthTable.setBackground(new Color(192, 192, 192));
        this._truthTable.clearSelection();
        this.createPopupMenu();
        this.update();
    }
    
    public JTable getTruthTable() {
        return this._truthTable;
    }
    
    public void update() {
        final int[] intValues = new int[this._bitRadioInputs.length];
        for (int i = 0; i < this._bitRadioInputs.length; ++i) {
            intValues[i] = this._bitRadioInputs[i].getValue();
        }
        for (int i = 0; i < (int)Math.pow(2.0, this._bitRadioInputs.length); ++i) {
            for (int j = this._bitRadioInputs.length; j > 0; --j) {
                final int intValue = i % (int)Math.pow(2.0, j) / (int)Math.pow(2.0, j - 1);
                this._bitRadioInputs[this._bitRadioInputs.length - j].setValue(intValue);
                this._truthTable.setValueAt("" + intValue, i, this._bitRadioInputs.length - j);
            }
            for (int j = this._bitRadioOutputs.length; j > 0; --j) {
                final int intValue = this._bitRadioOutputs[this._bitRadioOutputs.length - j].getValue();
                this._truthTable.setValueAt("" + intValue, i, this._bitRadioInputs.length + (this._bitRadioOutputs.length - j));
            }
        }
        for (int i = 0; i < this._bitRadioInputs.length; ++i) {
            this._bitRadioInputs[i].setValue(intValues[i]);
        }
    }
    
    public void repaint() {
        for (int i = 0; i < (int)Math.pow(2.0, this._bitRadioInputs.length); ++i) {
            boolean bSelected = true;
            for (int j = 0; j < this._bitRadioInputs.length; ++j) {
                bSelected = (bSelected && this._bitRadioInputs[j].getValue() == Integer.parseInt((String)this._truthTable.getValueAt(i, j)));
            }
            if (bSelected) {
                this._truthTable.changeSelection(i, 0, false, false);
                this._truthTable.repaint();
                return;
            }
        }
    }
    
    private void copyToClipboard() {
        String sClip = "";
        for (int i = this._bitRadioInputs.length; i > 0; --i) {
            sClip = sClip + "x(" + (i - 1) + ")\t";
        }
        for (int i = this._bitRadioOutputs.length; i > 0; --i) {
            sClip = sClip + "z(" + (i - 1) + ")\t";
        }
        sClip += "\n";
        for (int i = 0; i < this._truthTable.getRowCount(); ++i) {
            for (int j = 0; j < this._truthTable.getColumnCount(); ++j) {
                sClip = sClip + "" + this._truthTable.getValueAt(i, j) + "\t";
            }
            sClip += "\n";
        }
        final StringSelection clip = new StringSelection(sClip);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(clip, clip);
    }
    
    public void createPopupMenu() {
        final JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.setFont(new Font("Dialog", 0, 12));
        final JMenuItem menuItem = new JMenuItem("Copy to Clipboard");
        menuItem.setFont(popupMenu.getFont());
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                TruthTable.this.copyToClipboard();
            }
        });
        popupMenu.add(menuItem);
        this._truthTable.addMouseListener(new PopupListener(popupMenu));
    }
}
