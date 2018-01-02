import java.awt.event.KeyEvent;
import java.awt.event.FocusEvent;
import java.util.StringTokenizer;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.List;
import java.util.Vector;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class JumpToWidget extends Applet implements KeyListener, FocusListener
{
    private String namesString;
    private String valuesString;
    private Vector namesVector;
    private Vector lowercaseNamesVector;
    private Vector valuesVector;
    private Vector optionsValuesVector;
    private Vector optionsNamesVector;
    private List optionsList;
    private TextField tf;
    private int isCaseSensitive;
    
    public void init() {
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.fill = 2;
        gridBagConstraints.anchor = 12;
        (this.tf = new TextField("", 25)).addKeyListener(this);
        layout.setConstraints(this.tf, gridBagConstraints);
        this.add(this.tf);
        this.optionsList = new List(30, false);
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weighty = 100.0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.anchor = 10;
        layout.setConstraints(this.optionsList, gridBagConstraints);
        this.add(this.optionsList);
        this.validate();
        final Dimension dimension = new Dimension();
        final Dimension size = this.optionsList.getSize();
        final Dimension dimension2 = new Dimension();
        final Dimension dimension3 = new Dimension();
        int n = size.height / (this.optionsList.getPreferredSize(5).height - this.optionsList.getPreferredSize(4).height) - 1;
        if (n <= 0) {
            n = 1;
        }
        this.remove(this.optionsList);
        this.validate();
        (this.optionsList = new List(n, false)).addFocusListener(this);
        this.optionsList.setFont(new Font("SansSerif", 0, 11));
        layout.setConstraints(this.optionsList, gridBagConstraints);
        this.add(this.optionsList);
        this.namesVector = new Vector();
        this.lowercaseNamesVector = new Vector();
        this.valuesVector = new Vector();
        this.optionsValuesVector = new Vector();
        this.optionsNamesVector = new Vector();
        this.setCaseSensitivity(this.getParameter("casesensitivity"));
        this.loadNamesAndValues(this.getParameter("names"), this.getParameter("values"));
    }
    
    public String getSelectedValue() {
        if (this.optionsList.getSelectedIndex() != -1) {
            return this.optionsValuesVector.elementAt(this.optionsList.getSelectedIndex());
        }
        return null;
    }
    
    public String getSelectedName() {
        if (this.optionsList.getSelectedItem() != null) {
            return this.optionsList.getSelectedItem();
        }
        return null;
    }
    
    public void setCaseSensitivity(String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        if (lowerCase.compareTo("yes") == 0 || lowerCase.compareTo("y") == 0 || lowerCase.compareTo("1") == 0 || lowerCase.compareTo("true") == 0) {
            this.isCaseSensitive = 1;
        }
        else {
            this.isCaseSensitive = 0;
        }
    }
    
    public void loadNamesAndValues(final String s, final String s2) {
        this.namesString = new String(s);
        this.valuesString = new String(s2);
        this.namesVector.removeAllElements();
        this.valuesVector.removeAllElements();
        this.optionsNamesVector.removeAllElements();
        this.optionsValuesVector.removeAllElements();
        this.lowercaseNamesVector.removeAllElements();
        this.optionsList.removeAll();
        final StringTokenizer stringTokenizer = new StringTokenizer(this.namesString, "|");
        int n = 0;
        while (stringTokenizer.hasMoreTokens()) {
            final String s3 = new String(stringTokenizer.nextToken());
            this.namesVector.addElement(s3);
            this.optionsNamesVector.addElement(s3);
            if (this.isCaseSensitive == 0) {
                this.lowercaseNamesVector.addElement(s3.toLowerCase());
            }
            else {
                this.lowercaseNamesVector.addElement(s3);
            }
            ++n;
        }
        final StringTokenizer stringTokenizer2 = new StringTokenizer(this.valuesString, "|");
        int n2 = 0;
        while (stringTokenizer2.hasMoreTokens()) {
            final String s4 = new String(stringTokenizer2.nextToken());
            this.valuesVector.addElement(s4);
            this.optionsValuesVector.addElement(s4);
            ++n2;
        }
        if (n != n2) {
            if (n2 == 0 && n != 0) {
                for (int i = 0; i < this.namesVector.size(); ++i) {
                    this.valuesVector.addElement(this.namesVector.elementAt(i));
                    this.optionsValuesVector.addElement(this.namesVector.elementAt(i));
                    ++n2;
                }
            }
            else {
                this.tf.setText("ERROR: number of values doesn't match number of names");
            }
        }
        else {
            this.tf.setText("");
        }
        this.optionsList.setVisible(false);
        for (int n3 = 0; n3 < this.optionsList.getRows() && n3 < this.optionsNamesVector.size(); ++n3) {
            this.optionsList.add((String)this.optionsNamesVector.elementAt(n3));
        }
        if (this.optionsList.getItemCount() > 0) {
            this.optionsList.select(0);
        }
        this.optionsList.setVisible(true);
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        if (this.optionsList.getItemCount() < this.optionsNamesVector.size()) {
            final int selectedIndex = this.optionsList.getSelectedIndex();
            this.optionsList.removeAll();
            this.optionsList.add("Loading...");
            this.optionsList.setVisible(false);
            for (int i = 0; i < this.optionsNamesVector.size(); ++i) {
                this.optionsList.add((String)this.optionsNamesVector.elementAt(i));
            }
            this.optionsList.setVisible(true);
            this.optionsList.remove(0);
            this.optionsList.select(selectedIndex);
            this.optionsList.requestFocus();
        }
    }
    
    public void focusLost(final FocusEvent focusEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 9 || keyEvent.getKeyCode() == 40) {
            this.optionsList.requestFocus();
        }
        else {
            this.optionsList.removeAll();
            this.optionsNamesVector.removeAllElements();
            this.optionsValuesVector.removeAllElements();
            this.optionsList.setVisible(false);
            String s;
            if (this.isCaseSensitive == 0) {
                s = new String(this.tf.getText().toLowerCase());
            }
            else {
                s = new String(this.tf.getText());
            }
            final Vector vector = new Vector<String>();
            final StringTokenizer stringTokenizer = new StringTokenizer(s, " ");
            int n = 0;
            while (stringTokenizer.hasMoreTokens()) {
                vector.addElement(new String(stringTokenizer.nextToken()));
                ++n;
            }
            if (n == 0) {
                vector.addElement(s);
            }
            for (int i = 0; i < this.namesVector.size(); ++i) {
                int n2 = 1;
                for (int j = 0; j < vector.size(); ++j) {
                    if (this.lowercaseNamesVector.elementAt(i).indexOf(vector.elementAt(j)) == -1) {
                        n2 = 0;
                    }
                }
                if (n2 == 1) {
                    this.optionsNamesVector.addElement(this.namesVector.elementAt(i));
                    this.optionsValuesVector.addElement(this.valuesVector.elementAt(i));
                }
            }
            for (int n3 = 0; n3 < this.optionsList.getRows() && n3 < this.optionsNamesVector.size(); ++n3) {
                this.optionsList.add((String)this.optionsNamesVector.elementAt(n3));
            }
            if (this.optionsList.getItemCount() > 0) {
                this.optionsList.select(0);
            }
            this.optionsList.setVisible(true);
        }
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
    }
}
