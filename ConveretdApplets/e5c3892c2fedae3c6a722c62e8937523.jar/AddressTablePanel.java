import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class AddressTablePanel extends JPanel implements MouseListener
{
    private imgViewer applet;
    private AddressTable addressTable;
    
    AddressTablePanel(final imgViewer applet) {
        this.applet = applet;
        this.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 2;
        final JPanel panel = new JPanel();
        final JLabel label = new JLabel("Address Search Results");
        label.setFont(applet.boldFont);
        panel.add(label);
        this.add(panel, gridBagConstraints);
        (this.addressTable = new AddressTable()).setToolTipText("Address Location Table");
        this.addressTable.setSelectionMode(0);
        final JScrollPane scrollPane = new JScrollPane(this.addressTable);
        this.addressTable.addMouseListener(this);
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.weighty = 100.0;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        this.add(scrollPane, gridBagConstraints);
    }
    
    public void setAddresses(final String[] addresses) {
        this.addressTable.setAddresses(addresses);
    }
    
    public void clearAddresses() {
        this.addressTable.clearAddresses();
    }
    
    public int getRowCount() {
        return this.addressTable.getRowCount();
    }
    
    @Override
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            this.display();
        }
    }
    
    @Override
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    @Override
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    @Override
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    @Override
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void display() {
        final int selectedRow = this.addressTable.getSelectedRow();
        if (selectedRow >= 0) {
            this.applet.md.mapLayers.getAddressSearchMapLayer().setPoint(this.addressTable.getLatLong(selectedRow), this.addressTable.getAddress(selectedRow));
        }
    }
}
