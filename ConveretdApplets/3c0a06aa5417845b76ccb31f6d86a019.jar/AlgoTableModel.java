import java.util.Vector;
import javax.swing.table.AbstractTableModel;

// 
// Decompiled by Procyon v0.5.30
// 

public class AlgoTableModel extends AbstractTableModel
{
    public static final int COLUMN_PROC = 0;
    public static final int COLUMN_LEN = 1;
    public static final int COLUMN_REM = 2;
    private static final String[] COLUMN_HEADERS;
    private Algorithm _algorithm;
    
    public AlgoTableModel() {
        this._algorithm = null;
    }
    
    public AlgoTableModel(final Algorithm algorithm) {
        this._algorithm = algorithm;
    }
    
    public void setAlogrithm(final Algorithm algorithm) {
        this._algorithm = algorithm;
    }
    
    public int getColumnCount() {
        return AlgoTableModel.COLUMN_HEADERS.length;
    }
    
    public String getColumnName(final int col) {
        return AlgoTableModel.COLUMN_HEADERS[col];
    }
    
    public Class getColumnClass(final int col) {
        return new String().getClass();
    }
    
    public int getRowCount() {
        if (this._algorithm == null) {
            return 0;
        }
        int length = 1;
        final Vector procs = this._algorithm.getProcs();
        for (int i = 1; i < procs.size(); ++i) {
            final CpuProcess proc = procs.get(i);
            if (proc.getTOA() > this._algorithm.getCpuTime()) {
                break;
            }
            if (!proc.isDone() || proc.getProcId() == this._algorithm.getRunning()) {
                ++length;
            }
        }
        return length;
    }
    
    public Object getValueAt(final int row, final int col) {
        if (this._algorithm == null || row >= this._algorithm.getProcs().size() || col >= AlgoTableModel.COLUMN_HEADERS.length) {
            return new String();
        }
        int rowOffset = 0;
        if (row > 0) {
            final Vector procs = this._algorithm.getProcs();
            for (int i = 1; i < procs.size(); ++i) {
                final CpuProcess proc = procs.get(i);
                if (proc.getTOA() > this._algorithm.getCpuTime()) {
                    break;
                }
                if (proc.getProcId() > row + rowOffset) {
                    break;
                }
                if (proc.isDone() && proc.getProcId() != this._algorithm.getRunning()) {
                    ++rowOffset;
                }
            }
        }
        rowOffset += row;
        final CpuProcess proc2 = this._algorithm.getProcs().get(rowOffset);
        switch (col) {
            case 0: {
                return "" + proc2.getProcId();
            }
            case 1: {
                return "" + proc2.getLength();
            }
            case 2: {
                return new Integer(proc2.getTimeLeft());
            }
            default: {
                return new String();
            }
        }
    }
    
    public boolean isCellEditable(final int row, final int col) {
        return false;
    }
    
    public String toString() {
        return (this._algorithm == null) ? "" : this._algorithm.toString();
    }
    
    static {
        COLUMN_HEADERS = new String[] { "ProcId", "Length", "Time Remaining" };
    }
}
