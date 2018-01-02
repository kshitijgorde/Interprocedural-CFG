import javax.swing.SwingUtilities;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import javax.swing.JDialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class SearchForAddressDialog extends JDialog implements WindowListener, ActionListener
{
    private imgViewer applet;
    private JTextField addressEntry;
    private JLabel warningLabel;
    private AddressTablePanel addressTablePanel;
    
    public SearchForAddressDialog(final JFrame frame, final imgViewer applet) {
        super(frame, "Search for Address", false);
        this.applet = applet;
        this.getContentPane().setLayout(new BorderLayout());
        final JPanel panel = new JPanel();
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = 1;
        panel.setLayout(layout);
        panel.add(new JLabel("Enter Address:"), gridBagConstraints);
        this.addressEntry = new JTextField("", 35);
        final Dimension preferredSize = this.addressEntry.getPreferredSize();
        this.addressEntry.setMinimumSize(preferredSize);
        this.addressEntry.setMaximumSize(preferredSize);
        this.addressEntry.setToolTipText("Enter address to search for");
        this.addressEntry.addActionListener(this);
        gridBagConstraints.gridwidth = 0;
        panel.add(this.addressEntry, gridBagConstraints);
        gridBagConstraints.gridwidth = 1;
        (this.warningLabel = new JLabel("", 0)).setForeground(Color.RED);
        panel.add(this.warningLabel, gridBagConstraints);
        this.addressTablePanel = new AddressTablePanel(applet);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1, 4));
        final JButton button = new JButton("Search");
        button.setMnemonic(83);
        button.setToolTipText("Search for Address");
        button.addActionListener(this);
        final JButton button2 = new JButton("Display");
        button2.setMnemonic(68);
        button2.setToolTipText("Display Address");
        button2.addActionListener(this);
        final JButton button3 = new JButton("Clear");
        button3.setMnemonic(76);
        button3.setToolTipText("Clear search for address");
        button3.addActionListener(this);
        final JButton button4 = new JButton("Close");
        button4.setMnemonic(67);
        button4.setToolTipText("Close search for address");
        button4.addActionListener(this);
        panel2.add(button);
        panel2.add(button2);
        panel2.add(button3);
        panel2.add(button4);
        this.getContentPane().add(panel, "North");
        this.getContentPane().add(this.addressTablePanel, "Center");
        this.getContentPane().add(panel2, "South");
        this.setSize(500, 270);
        this.addWindowListener(this);
    }
    
    @Override
    public void windowClosing(final WindowEvent windowEvent) {
        this.setVisible(false);
    }
    
    @Override
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        final String trim = this.addressEntry.getText().trim();
        if (actionCommand.equals("Close")) {
            this.setVisible(false);
        }
        else if (actionCommand.equals("Display")) {
            this.addressTablePanel.display();
        }
        else if (actionCommand.equals("Clear")) {
            this.addressEntry.setText("");
            this.warningLabel.setText(" ");
            this.addressTablePanel.clearAddresses();
        }
        else if (actionCommand.equals("Search") || !trim.equals("")) {
            this.searchForAddress();
        }
    }
    
    private void searchForAddress() {
        final String trim = this.addressEntry.getText().trim();
        if (!trim.equals("")) {
            this.addressTablePanel.clearAddresses();
            final SearchParameters searchParameters = new SearchParameters();
            searchParameters.applet = this.applet;
            searchParameters.searchDialog = this;
            searchParameters.address = trim;
            searchParameters.searchResults = new StringBuffer("");
            new SearchThread(searchParameters).start();
        }
        else {
            JOptionPane.showMessageDialog(this, "Address is invalid ", "Invalid Entry", 0);
        }
    }
    
    private String callSearchCgi(final String s) {
        String s2 = null;
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(this.applet.getCodeBase(), "lookupAddress.cgi?" + "address=" + URLEncoder.encode(s, "UTF-8")).openConnection().getInputStream()));
            final String line = bufferedReader.readLine();
            bufferedReader.close();
            s2 = line;
        }
        catch (Exception ex) {
            System.out.println("search exception: " + ex.toString());
        }
        return s2;
    }
    
    private class SearchParameters
    {
        public imgViewer applet;
        public SearchForAddressDialog searchDialog;
        public StringBuffer searchResults;
        public String address;
    }
    
    private class SearchThread extends Thread
    {
        SearchParameters params;
        
        SearchThread(final SearchParameters params) {
            this.params = params;
        }
        
        @Override
        public void run() {
            try {
                this.params.searchResults.append(this.params.searchDialog.callSearchCgi(this.params.address));
                SwingUtilities.invokeLater(new ProcessResults(this.params));
                this.params = null;
            }
            catch (Exception ex) {
                System.out.println("search thread exception: " + ex.toString());
            }
        }
    }
    
    private class ProcessResults implements Runnable
    {
        private SearchParameters searchParameters;
        
        ProcessResults(final SearchParameters searchParameters) {
            this.searchParameters = searchParameters;
        }
        
        @Override
        public void run() {
            final String string = this.searchParameters.searchResults.toString();
            if (string.startsWith("Found:")) {
                final String[] split = string.substring(6).split("::");
                if (split != null) {
                    if (split.length >= 100) {
                        SearchForAddressDialog.this.warningLabel.setText("Note: Search results are limited to 100 records");
                    }
                    else {
                        SearchForAddressDialog.this.warningLabel.setText(" ");
                    }
                    SearchForAddressDialog.this.addressTablePanel.setAddresses(split);
                }
            }
            else {
                JOptionPane.showMessageDialog(this.searchParameters.searchDialog, "No matches found", "Search Complete", 2);
            }
            this.searchParameters = null;
        }
    }
}
