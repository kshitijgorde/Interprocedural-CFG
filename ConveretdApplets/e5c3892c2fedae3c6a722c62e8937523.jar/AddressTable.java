import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.JTable;

// 
// Decompiled by Procyon v0.5.30
// 

public class AddressTable extends JTable
{
    private AddressModel tableModel;
    
    public AddressTable() {
        this.setModel(this.tableModel = new AddressModel());
        final TableColumn column = this.getColumnModel().getColumn(1);
        column.setMinWidth(100);
        column.setPreferredWidth(100);
        column.setMaxWidth(100);
        final TableColumn column2 = this.getColumnModel().getColumn(2);
        column2.setMinWidth(100);
        column2.setPreferredWidth(100);
        column2.setMaxWidth(100);
    }
    
    public void setAddresses(final String[] addresses) {
        this.tableModel.setAddresses(addresses);
    }
    
    public void clearAddresses() {
        this.tableModel.clearAddresses();
    }
    
    public LatLong getLatLong(final int n) {
        return this.tableModel.getLatLong(n);
    }
    
    public String getAddress(final int n) {
        return this.tableModel.getAddress(n);
    }
    
    class AddressModel extends AbstractTableModel
    {
        String[] columnNames;
        String[][] addressData;
        
        AddressModel() {
            this.columnNames = new String[] { "Address", "Latitude", "Longitude" };
        }
        
        @Override
        public String getColumnName(final int n) {
            return this.columnNames[n];
        }
        
        public void setAddresses(final String[] array) {
            this.addressData = new String[array.length][3];
            for (int i = 0; i < array.length; ++i) {
                final String[] split = array[i].split(":");
                this.addressData[i][0] = split[2];
                this.addressData[i][1] = split[1];
                this.addressData[i][2] = split[0];
            }
            this.fireTableDataChanged();
            if (this.getRowCount() == 1) {
                AddressTable.this.addRowSelectionInterval(0, 0);
            }
        }
        
        @Override
        public int getRowCount() {
            if (this.addressData != null) {
                return this.addressData.length;
            }
            return 0;
        }
        
        @Override
        public int getColumnCount() {
            return this.columnNames.length;
        }
        
        @Override
        public Object getValueAt(final int n, final int n2) {
            return this.addressData[n][n2];
        }
        
        public LatLong getLatLong(final int n) {
            return new LatLong(new Double(this.addressData[n][1]), new Double(this.addressData[n][2]));
        }
        
        public String getAddress(final int n) {
            return new String(this.addressData[n][0]);
        }
        
        public void clearAddresses() {
            this.addressData = null;
            this.fireTableDataChanged();
        }
    }
}
