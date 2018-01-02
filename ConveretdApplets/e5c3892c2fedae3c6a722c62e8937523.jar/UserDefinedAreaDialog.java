import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import javax.swing.JDialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class UserDefinedAreaDialog extends JDialog implements WindowListener, ActionListener
{
    private JButton exitButton;
    private JButton clearButton;
    private JButton undoButton;
    private JButton showButton;
    private JCheckBox checkBox;
    private imgViewer applet;
    private MosaicData md;
    private UserDefinedArea userDefinedArea;
    
    public UserDefinedAreaDialog(final JFrame frame, final imgViewer applet, final MosaicData md) {
        super(frame, "User Defined Area", false);
        this.applet = applet;
        this.md = md;
        this.userDefinedArea = new UserDefinedArea(applet, md, this);
        this.getContentPane().setLayout(new BorderLayout());
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        panel.add(new JLabel("Please click points to create area."));
        panel.add(new JLabel("Note: Normal mouse commands are"));
        panel.add(new JLabel("disabled while this dialog box is open."));
        (this.checkBox = new JCheckBox("Close Area Polygon & Apply User Defined Area", false)).setToolTipText("Close area polygon & apply user defined area");
        this.checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                if (UserDefinedAreaDialog.this.checkBox.isSelected()) {
                    UserDefinedAreaDialog.this.userDefinedArea.closeAndApplyArea();
                }
                else {
                    UserDefinedAreaDialog.this.userDefinedArea.uncloseArea();
                }
            }
        });
        this.checkBox.setEnabled(false);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1, 4));
        (this.exitButton = new JButton("Exit")).setMnemonic(69);
        this.exitButton.setToolTipText("Exit user defined area");
        this.exitButton.addActionListener(this);
        (this.clearButton = new JButton("Clear")).setMnemonic(76);
        this.clearButton.setToolTipText("Clear user defined area");
        this.clearButton.addActionListener(this);
        (this.undoButton = new JButton("Undo")).setMnemonic(85);
        this.undoButton.setToolTipText("Undo last operation");
        this.undoButton.addActionListener(this);
        this.undoButton.setEnabled(false);
        (this.showButton = new JButton("Show")).setMnemonic(83);
        this.showButton.setToolTipText("Show user defined area");
        this.showButton.addActionListener(this);
        this.showButton.setEnabled(false);
        panel2.add(this.exitButton);
        panel2.add(this.clearButton);
        panel2.add(this.undoButton);
        panel2.add(this.showButton);
        this.getContentPane().add(panel, "North");
        this.getContentPane().add(this.checkBox, "Center");
        this.getContentPane().add(panel2, "South");
        this.setSize(340, 200);
        this.addWindowListener(this);
    }
    
    public boolean isDialogBoxVisible() {
        return this.isShowing();
    }
    
    public boolean isUserDefinedAreaClosed() {
        return this.userDefinedArea.getPolygonIsClosed();
    }
    
    public void enableButtons() {
        if (this.userDefinedArea.numberOfPolygonPoints() > 0) {
            if (this.userDefinedArea.doesCloseCauseIntersect()) {
                this.checkBox.setSelected(false);
                this.checkBox.setEnabled(false);
            }
            else {
                this.checkBox.setEnabled(true);
                this.checkBox.setSelected(this.userDefinedArea.getPolygonIsClosed());
            }
            this.showButton.setEnabled(true);
        }
        else {
            this.checkBox.setSelected(false);
            this.checkBox.setEnabled(false);
            this.showButton.setEnabled(false);
        }
        if (this.userDefinedArea.isUndoStackEmpty()) {
            this.undoButton.setEnabled(false);
        }
        else {
            this.undoButton.setEnabled(true);
        }
    }
    
    public UserDefinedArea getUserDefinedArea() {
        return this.userDefinedArea;
    }
    
    @Override
    public void windowClosing(final WindowEvent windowEvent) {
        this.setVisible(false);
    }
    
    @Override
    public void windowOpened(final WindowEvent windowEvent) {
        this.applet.imgArea.repaint();
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
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.equals("Exit")) {
            this.userDefinedArea.applyUserDefinedArea();
            final TOC currentCell = this.md.getCurrentCell();
            if (currentCell.valid) {
                final Metadata metadata = currentCell.scenes[currentCell.currentDateIndex];
                if (this.userDefinedArea.getPolygonIsClosed() && !this.userDefinedArea.sceneIntersects(metadata)) {
                    this.md.sceneFilter.gotoLastDate();
                }
            }
            this.setVisible(false);
        }
        else if (actionCommand.equals("Clear")) {
            this.userDefinedArea.clearPolygon();
        }
        else if (actionCommand.equals("Undo")) {
            this.userDefinedArea.returnToPrevState();
        }
        else if (actionCommand.equals("Show")) {
            this.userDefinedArea.moveScreenToPolygon();
        }
    }
}
