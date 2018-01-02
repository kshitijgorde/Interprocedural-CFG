import javax.swing.SwingUtilities;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import javax.swing.JDialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class SearchForSceneDialog extends JDialog implements WindowListener, ActionListener
{
    private JLabel hintMessage;
    private JTextField idEntry;
    private JTextArea statusDisplay;
    private JPanel buttonPanel;
    private JButton searchButton;
    private JButton closeButton;
    private imgViewer applet;
    
    public SearchForSceneDialog(final JFrame frame, final imgViewer applet) {
        super(frame, "Search for Scene", false);
        this.applet = applet;
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        final JPanel panel2 = new JPanel();
        panel2.add(this.hintMessage = new JLabel(applet.sensorMenu.getCurrentSensor().sceneIdHint));
        this.idEntry = new JTextField("", 50);
        final Dimension preferredSize = this.idEntry.getPreferredSize();
        this.idEntry.setMinimumSize(preferredSize);
        final Dimension dimension = preferredSize;
        dimension.width *= 3;
        this.idEntry.setMaximumSize(preferredSize);
        this.idEntry.setToolTipText("Enter scene ID");
        this.idEntry.addActionListener(this);
        (this.statusDisplay = new JTextArea("", 3, 50)).setToolTipText("Search results");
        this.statusDisplay.setEditable(false);
        this.statusDisplay.setLineWrap(true);
        this.statusDisplay.setWrapStyleWord(true);
        (this.searchButton = new JButton("Search")).setMnemonic(83);
        this.searchButton.setToolTipText("Search for scene");
        this.searchButton.addActionListener(this);
        (this.closeButton = new JButton("Close")).setMnemonic(67);
        this.closeButton.setToolTipText("Close search for scene");
        this.closeButton.addActionListener(this);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayout(1, 2));
        panel3.add(this.searchButton);
        panel3.add(this.closeButton);
        panel.add(panel2);
        panel.add(this.idEntry);
        panel.add(this.statusDisplay);
        panel.add(panel3);
        this.getContentPane().add(panel);
        this.setSize(420, 200);
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
        if (actionEvent.getActionCommand().equals("Close")) {
            this.idEntry.setText("");
            this.statusDisplay.setText("");
            this.setVisible(false);
        }
        else {
            this.searchById();
        }
    }
    
    private void searchById() {
        final String trim = this.idEntry.getText().trim();
        if (!trim.equals("")) {
            this.statusDisplay.setText("Searching for " + trim + "\n");
            this.idEntry.setText("");
            final SearchParameters searchParameters = new SearchParameters();
            searchParameters.applet = this.applet;
            searchParameters.searchDialog = this;
            searchParameters.searchId = trim;
            searchParameters.searchResults = new StringBuffer("");
            searchParameters.sensor = this.applet.sensorMenu.getCurrentSensor();
            new SearchThread(searchParameters).start();
        }
    }
    
    public void setSensor(final Sensor sensor) {
        this.hintMessage.setText(sensor.sceneIdHint);
        this.statusDisplay.setText("");
    }
    
    private String callSearchCgi(final Sensor sensor, final String s) {
        String s2 = null;
        final Sensor[] sensorList = sensor.getSensorList();
        try {
            String s3 = "searchForScene.cgi?";
            for (int i = 0; i < sensorList.length; ++i) {
                if (sensorList[i].cgiDatasetName == null) {
                    s3 = s3 + "sensor=" + sensorList[i].datasetName + "&";
                }
                else {
                    s3 = s3 + "sensor=" + sensorList[i].cgiDatasetName + "&";
                }
            }
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(this.applet.getCodeBase(), s3 + "scene_id=" + URLEncoder.encode(s, "UTF-8")).openConnection().getInputStream()));
            final String line = bufferedReader.readLine();
            bufferedReader.close();
            s2 = line;
        }
        catch (Exception ex) {
            System.out.println("search exception: " + ex.toString());
        }
        return s2;
    }
    
    private Metadata parseSearchResult(final Sensor sensor, final String s) {
        Metadata metadata = null;
        if (s.startsWith("Found:")) {
            try {
                final int index = s.indexOf(44);
                final int index2 = s.indexOf(44, index + 1);
                final int index3 = s.indexOf(44, index2 + 1);
                final int columnNumberFromString = sensor.navModel.getColumnNumberFromString(s.substring(6, index));
                final int rowNumberFromString = sensor.navModel.getRowNumberFromString(s.substring(index + 1, index2));
                final int int1 = Integer.parseInt(s.substring(index2 + 1, index3));
                metadata = sensor.createMetadata(s.substring(index3 + 1), columnNumberFromString, rowNumberFromString);
                metadata.setSceneCorners(CreateProjection.fromProjectionNumber(int1));
            }
            catch (Exception ex) {
                System.out.println("exception: " + ex.toString());
            }
        }
        return metadata;
    }
    
    public Metadata searchForScene(final Sensor sensor, final String s) {
        Metadata searchResult = null;
        final String callSearchCgi = this.callSearchCgi(sensor, s);
        if (callSearchCgi != null) {
            searchResult = this.parseSearchResult(sensor, callSearchCgi);
        }
        return searchResult;
    }
    
    private class SearchParameters
    {
        public imgViewer applet;
        public SearchForSceneDialog searchDialog;
        public StringBuffer searchResults;
        public String searchId;
        public Sensor sensor;
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
                this.params.searchResults.append(this.params.searchDialog.callSearchCgi(this.params.sensor, this.params.searchId));
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
            if (this.searchParameters.sensor == SearchForSceneDialog.this.applet.sensorMenu.getCurrentSensor()) {
                if (string.startsWith("Found:")) {
                    final Metadata access$300 = SearchForSceneDialog.this.parseSearchResult(this.searchParameters.sensor, string);
                    if (access$300 != null) {
                        SearchForSceneDialog.this.applet.searchLimitDialog.applySearchLimits(access$300);
                        if (access$300.visible) {
                            SearchForSceneDialog.this.applet.md.showScene(access$300);
                            SearchForSceneDialog.this.statusDisplay.append("Search Complete: Found and displayed " + access$300.getEntityIDForDisplay());
                        }
                        else {
                            SearchForSceneDialog.this.statusDisplay.append("Search Complete: Found " + access$300.getEntityIDForDisplay() + " but not displayed because it is" + " filtered out by the current search limits");
                        }
                    }
                    else {
                        SearchForSceneDialog.this.statusDisplay.append("Error: an error occurred during the search");
                    }
                }
                else if (string.startsWith("Invalid:")) {
                    SearchForSceneDialog.this.statusDisplay.append("Error: Invalid scene ID");
                }
                else if (string.startsWith("Not Found:")) {
                    SearchForSceneDialog.this.statusDisplay.append("Search Complete: no matching scene found");
                }
                else {
                    SearchForSceneDialog.this.statusDisplay.append("Error: an error occurred during the search");
                    System.out.println("Search error: " + string);
                }
            }
            else {
                SearchForSceneDialog.this.statusDisplay.append("Search cancelled due to sensor change");
            }
            this.searchParameters = null;
        }
    }
}
