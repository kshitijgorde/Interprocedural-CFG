// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.components;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import lotus.notes.apps.outlinepanel.XMLOutlineEntriesParser;
import java.io.DataInputStream;
import java.util.Vector;
import java.awt.MediaTracker;
import java.net.URL;
import lotus.notes.apps.outlinepanel.OutlineEntriesLine;
import java.awt.Event;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Frame;
import java.util.Properties;
import java.awt.Button;
import java.awt.Panel;
import java.awt.Label;
import java.awt.Image;
import java.util.Hashtable;
import java.awt.Dialog;

public class FolderSelectionDialog extends Dialog
{
    private String imagePath;
    private String dbName;
    private String hostName;
    private String protocol;
    private String outlineID;
    private int commandType;
    private DialogListener listener;
    private boolean enableMove;
    private int command;
    private String selectedFolderName;
    private String selectedFolderUNID;
    private Hashtable nodeTable;
    static Image folderImage;
    static Image collapseImage;
    static Image expandImage;
    private static final String FOLDER_IMAGE = "afolder.gif";
    private static final String COLLAPSE_IMAGE = "collapse.gif";
    private static final String EXPAND_IMAGE = "expand.gif";
    private static final String DEFAULT_IMAGE_PATH = "icons";
    public static final int MOVE_TO_FOLDER = 1;
    public static final int COPY_TO_FOLDER = 2;
    public static final int CANCEL = 3;
    public static final int HELP = 4;
    private Label label;
    private Panel buttonPanel;
    private Button moveButton;
    private Button addButton;
    private Button cancelButton;
    private NTreeView tv;
    private Properties props;
    
    public FolderSelectionDialog(final Frame frame, final DialogListener dialogListener, final Properties properties) {
        this(frame, false, dialogListener, properties);
    }
    
    public FolderSelectionDialog(final Frame frame, final boolean b, final DialogListener listener, final Properties props) {
        super(frame, b);
        this.imagePath = null;
        this.dbName = null;
        this.hostName = null;
        this.protocol = null;
        this.outlineID = "$defaultOutline";
        this.commandType = 1;
        this.listener = null;
        this.enableMove = false;
        this.command = 3;
        this.selectedFolderName = null;
        this.selectedFolderUNID = null;
        this.nodeTable = new Hashtable();
        this.props = null;
        this.listener = listener;
        this.props = props;
        if (props == null) {
            throw new IllegalArgumentException("No resource bundle specified");
        }
        this.setBackground(Color.white);
        this.setLayout(new GridBagLayout());
        (this.label = new Label(props.getProperty("folderdialog.select", "Select a Folder:"), 0)).reshape(this.insets().left + 20, this.insets().top + 20, 543, 23);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 11;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(20, 20, 0, 20);
        gridBagConstraints.ipadx = 427;
        ((GridBagLayout)this.getLayout()).setConstraints(this.label, gridBagConstraints);
        this.add(this.label);
        (this.buttonPanel = new Panel()).setLayout(new GridBagLayout());
        this.buttonPanel.reshape(this.insets().left + 583, this.insets().top + 43, 53, 117);
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.anchor = 11;
        gridBagConstraints2.fill = 2;
        gridBagConstraints2.insets = new Insets(0, 0, 0, 20);
        ((GridBagLayout)this.getLayout()).setConstraints(this.buttonPanel, gridBagConstraints2);
        this.add(this.buttonPanel);
        this.initButtonPanel(this.buttonPanel);
        this.tv = new NTreeView();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        gridBagConstraints2.anchor = 11;
        gridBagConstraints2.fill = 1;
        gridBagConstraints2.insets = new Insets(0, 20, 20, 20);
        ((GridBagLayout)this.getLayout()).setConstraints(this.tv, gridBagConstraints2);
        this.add(this.tv);
        if (this.commandType == 1) {
            this.setTitle(props.getProperty("folderdialog.titlemove", "Move To Folder"));
        }
        else {
            this.setTitle(props.getProperty("folderdialog.titlecopy", "Copy To Folder"));
        }
        this.resize(400, 400);
        this.center();
    }
    
    public void setDatabaseName(final String dbName) {
        this.dbName = dbName;
    }
    
    public void setHostName(final String hostName) {
        this.hostName = hostName;
    }
    
    public void setProtocol(final String protocol) {
        this.protocol = protocol;
    }
    
    public void setCommandType(final int commandType) {
        this.commandType = commandType;
        if (this.commandType == 1) {
            this.moveButton.enable(true);
        }
        else {
            this.moveButton.enable(false);
        }
    }
    
    public void setOutlineID(final String outlineID) {
        this.outlineID = outlineID;
    }
    
    public void setImagePath(final String imagePath) {
        this.imagePath = imagePath;
    }
    
    public String getSelectedFolderName() {
        return this.selectedFolderName;
    }
    
    public String getSelectedFolderUNID() {
        return this.selectedFolderUNID;
    }
    
    public int getCommand() {
        return this.command;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.command = 3;
            this.dispose();
        }
        else if (event.id == 1001) {
            this.selectedFolderName = null;
            this.selectedFolderUNID = null;
            if (event.target == this.moveButton) {
                this.command = 1;
            }
            else if (event.target == this.addButton) {
                this.command = 2;
            }
            else if (event.target == this.cancelButton) {
                this.command = 3;
            }
            if (this.command != 3) {
                final NTreeNode selectedNode = this.tv.getSelectedNode();
                if (selectedNode != null) {
                    final OutlineEntriesLine outlineEntriesLine = (OutlineEntriesLine)selectedNode.getDataObject();
                    if (outlineEntriesLine != null) {
                        this.selectedFolderName = outlineEntriesLine.entryLabel;
                        this.selectedFolderUNID = outlineEntriesLine.UNID;
                    }
                }
            }
            this.dispose();
            if (this.listener != null) {
                this.listener.dialogDismissed(this);
            }
        }
        return true;
    }
    
    public void show() {
        this.init();
        super.show();
    }
    
    public void init() {
        this.command = 3;
        this.selectedFolderName = null;
        this.selectedFolderUNID = null;
        this.initTree(this.tv);
    }
    
    private void initButtonPanel(final Panel panel) {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = (GridBagLayout)this.buttonPanel.getLayout();
        this.moveButton = new Button(this.props.getProperty("folderdialog.move", "Move"));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(0, 0, 5, 0);
        gridBagLayout.setConstraints(this.moveButton, gridBagConstraints);
        panel.add(this.moveButton);
        this.addButton = new Button(this.props.getProperty("folderdialog.add", "Add"));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(0, 0, 5, 0);
        gridBagLayout.setConstraints(this.addButton, gridBagConstraints);
        panel.add(this.addButton);
        this.cancelButton = new Button(this.props.getProperty("folderdialog.cancel", "Cancel"));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(0, 0, 5, 0);
        gridBagLayout.setConstraints(this.cancelButton, gridBagConstraints);
        panel.add(this.cancelButton);
    }
    
    private void initTree(final NTreeView nTreeView) {
        if (nTreeView != null) {
            nTreeView.clear();
            if (FolderSelectionDialog.folderImage == null) {
                String s;
                if (this.imagePath != null) {
                    s = this.imagePath + "/" + "afolder.gif";
                }
                else {
                    s = this.protocol + "://" + this.hostName + "/" + "icons" + "/" + "afolder.gif";
                }
                try {
                    System.out.println("Loading image from URL: " + s);
                    FolderSelectionDialog.folderImage = this.getToolkit().getImage(new URL(s));
                    final MediaTracker mediaTracker = new MediaTracker(this);
                    mediaTracker.addImage(FolderSelectionDialog.folderImage, 0);
                    mediaTracker.waitForID(0);
                }
                catch (Exception ex) {
                    System.out.println("Exception occurred while loading folder selection dialog images: " + ex);
                    ex.printStackTrace();
                }
            }
            this.readFolderEntries();
        }
    }
    
    private void readFolderEntries() {
        final Vector vector = new Vector<OutlineEntriesLine>();
        try {
            final StringBuffer sb = new StringBuffer();
            sb.append(this.protocol);
            sb.append("://").append(this.hostName);
            sb.append("/").append(this.dbName).append("/").append(this.outlineID).append("?");
            sb.append("ReadEntries");
            final URL constructURL = this.constructURL(sb.toString());
            System.out.println("Reading folder entries from URL: " + constructURL.toString());
            final DataInputStream dataInputStream = new DataInputStream(constructURL.openStream());
            new XMLOutlineEntriesParser(constructURL.toString(), vector).parse();
        }
        catch (Exception ex) {
            System.out.println("Exception occurred while reading folder entries: " + ex);
            ex.printStackTrace();
        }
        for (int i = 0; i < vector.size(); ++i) {
            final OutlineEntriesLine dataObject = vector.elementAt(i);
            if (!dataObject.isHidden && !dataObject.isPrivate && dataObject.entryType == 20) {
                final int level = this.getLevel(dataObject.Position);
                final NTreeNode nTreeNode = new NTreeNode(dataObject.entryLabel, FolderSelectionDialog.folderImage, FolderSelectionDialog.folderImage);
                nTreeNode.setDataObject(dataObject);
                if (dataObject.isExpandable) {
                    this.nodeTable.put(dataObject.Position, nTreeNode);
                }
                if (level == 1) {
                    this.tv.append(nTreeNode);
                }
                else {
                    final NTreeNode parentNodeByPosition = this.getParentNodeByPosition(dataObject.Position);
                    if (parentNodeByPosition != null) {
                        this.tv.addChild(nTreeNode, parentNodeByPosition);
                    }
                }
            }
        }
    }
    
    private URL constructURL(final String s) throws MalformedURLException {
        URL url = null;
        if (s != null) {
            url = new URL(s.replace(' ', '+'));
        }
        return url;
    }
    
    private int getLevel(final String s) {
        int n = 0;
        if (s != null) {
            n = 1;
            int i = 0;
            while (i != -1) {
                i = s.indexOf(46, i + 1);
                if (i != -1) {
                    ++n;
                }
            }
        }
        return n;
    }
    
    private NTreeNode getParentNodeByPosition(final String s) {
        NTreeNode nTreeNode = null;
        if (s != null) {
            nTreeNode = this.nodeTable.get(s.substring(0, s.lastIndexOf(46)));
        }
        return nTreeNode;
    }
    
    private void center() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int width = this.size().width;
        final int height = this.size().height;
        this.reshape((screenSize.width - width) / 2, (screenSize.height - height) / 2, width, height);
    }
    
    static {
        FolderSelectionDialog.folderImage = null;
        FolderSelectionDialog.collapseImage = null;
        FolderSelectionDialog.expandImage = null;
    }
}
