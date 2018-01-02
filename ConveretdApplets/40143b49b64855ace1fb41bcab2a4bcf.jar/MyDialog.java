import java.awt.Event;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Panel;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.Button;
import java.awt.Image;
import java.awt.TextField;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class MyDialog extends Frame
{
    private TextField field;
    private Image img;
    public Button bOK;
    public Button bCancel;
    boolean pass;
    boolean Cancel;
    Object Parent;
    public TextField RecField;
    public TextField ProgressField;
    public TextArea AboutField;
    public Label StatusLabel;
    public Label StatusLabel2;
    private Label PasswordLabel;
    public ImageCanvas ImageField;
    public static int PASSWORD_DLG;
    public static int PROGRESS_DLG;
    public static int ABOUT_DLG;
    public static int IMAGE_DLG;
    public static int EXP_DLG;
    private Panel p1;
    private Panel p2;
    private Panel p3;
    private int Type;
    String Ref1;
    String Ref2;
    
    private int cton(final String s, final String s2) {
        return s.indexOf(s2);
    }
    
    public MyDialog(final Object parent, final int type, final int n) {
        this.pass = false;
        this.Cancel = false;
        this.Ref1 = "0123456789:-abcdefghijklmnopqrstuvwxyz._~ABCDEF/GHIJKLMNOPQRSTUVWXYZ";
        this.Ref2 = "ghnopqrstuef:-._~ABCDEFvwx0137UVWX89abcdGHIJ456KLMyz2NOPlmRST/QijkYZ";
        this.Parent = parent;
        this.Type = type;
        this.setBackground(Color.lightGray);
        final Font font = new Font("Helvetica", 1, n);
        final Font font2 = new Font("Helvetica", 0, n);
        this.setFont(font);
        if (type == MyDialog.PASSWORD_DLG) {
            this.bCancel = new Button("Cancel");
            this.bOK = new Button("OK");
            this.setLayout(new GridLayout(2, 1, 10, 10));
            this.setTitle("Password Dialog");
            this.PasswordLabel = new Label("Password: ");
            (this.field = new TextField(40)).setFont(font2);
            this.field.setEchoCharacter('x');
            (this.p1 = new Panel()).setLayout(new FlowLayout(0));
            this.p2 = new Panel();
            this.p1.add(this.PasswordLabel);
            this.p1.add(this.field);
            this.add(this.p1);
            this.p2.add(this.bCancel);
            this.p2.add(this.bOK);
            this.add(this.p2);
            this.setResizable(false);
        }
        if (type == MyDialog.PROGRESS_DLG) {
            this.bCancel = new Button("Cancel");
            this.setLayout(new GridLayout(3, 1, 5, 5));
            this.setTitle("Progress Bar");
            (this.ProgressField = new TextField(40)).setFont(font2);
            this.ProgressField.setText("Reading RECORDs........ ");
            this.StatusLabel = new Label("Status: ");
            this.StatusLabel2 = new Label("Record Number: ");
            (this.RecField = new TextField(10)).setFont(font2);
            (this.p1 = new Panel()).setLayout(new FlowLayout(0));
            (this.p2 = new Panel()).setLayout(new FlowLayout(0));
            this.p3 = new Panel();
            this.p1.add(this.StatusLabel);
            this.p1.add(this.ProgressField);
            this.p2.add(this.StatusLabel2);
            this.p2.add(this.RecField);
            this.p3.add(this.bCancel);
            this.add("West", this.p1);
            this.add("West", this.p2);
            this.add("West", this.p3);
            this.setResizable(false);
        }
        if (type == MyDialog.ABOUT_DLG) {
            this.bCancel = new Button("Cancel");
            (this.AboutField = new TextArea(10, 40)).setEditable(false);
            this.AboutField.setBackground(Color.white);
            this.setLayout(new BorderLayout(10, 10));
            this.setTitle("About Catalog View");
            this.AboutField.setText("Catalog View Applet version 1.00\nTHIS SOFTWARE IS FREEWARE\nInternational Starch Trading A/S\nScience Park\nDENMARK\nhttp://www.trading.dk/catview\nadmin@trading.dk");
            this.add("North", this.AboutField);
            (this.p1 = new Panel()).add(this.bCancel);
            this.add("South", this.p1);
            this.setResizable(false);
        }
        if (type == MyDialog.IMAGE_DLG) {
            this.bCancel = new Button("Cancel");
            final GridBagLayout layout = new GridBagLayout();
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            this.setLayout(layout);
            gridBagConstraints.fill = 1;
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.anchor = 17;
            this.StatusLabel = new Label("  Record Nr: ");
            this.StatusLabel2 = new Label("  Image: ");
            this.ImageField = new ImageCanvas(this, 200, 100);
            (this.ProgressField = new TextField()).setEditable(false);
            this.p1 = new Panel();
            this.setTitle("Image Viewer");
            layout.setConstraints(this.ImageField, gridBagConstraints);
            layout.setConstraints(this.StatusLabel, gridBagConstraints);
            layout.setConstraints(this.StatusLabel2, gridBagConstraints);
            layout.setConstraints(this.p1, gridBagConstraints);
            layout.setConstraints(this.ProgressField, gridBagConstraints);
            this.p1.add(this.bCancel);
            this.add(this.ImageField);
            this.add(this.StatusLabel);
            this.add(this.StatusLabel2);
            this.add(this.p1);
            this.add(this.ProgressField);
        }
        if (type == MyDialog.EXP_DLG) {
            (this.AboutField = new TextArea(20, 60)).setEditable(false);
            this.setTitle("Catalog View Expired");
            this.AboutField.setText("Catalog View version 1.0\nAuthor:\nNiels Kirk Thomsen\nnkt@dk-online.dk\nwww2.dk-online.dk/users/nkt\nDENMARK\n\n");
            this.add("Center", this.AboutField);
            this.setResizable(false);
        }
        this.validate();
        this.pack();
    }
    
    static {
        MyDialog.PASSWORD_DLG = 0;
        MyDialog.PROGRESS_DLG = 1;
        MyDialog.ABOUT_DLG = 2;
        MyDialog.IMAGE_DLG = 3;
        MyDialog.EXP_DLG = 4;
    }
    
    public String decode(final String s, final int n) {
        return this.code(this.code(s, this.Ref2, n + 2), this.Ref1, n + 1);
    }
    
    private String ntoc(final String s, final int n) {
        return s.substring(n, n + 1);
    }
    
    public boolean action(final Event event, final Object o) {
        if ((event.target == this.bOK | event.target == this.field) && this.field.getText().equals(this.decode(((CatalogView)this.Parent).pPASSWORD, 1))) {
            ((CatalogView)this.Parent).window.show();
            this.field.setText("");
            this.hide();
        }
        if (event.target == this.bCancel) {
            if (this.Type == MyDialog.IMAGE_DLG && ((PopUpWindow)this.Parent).LoadImageThread != null) {
                ((PopUpWindow)this.Parent).LoadImageThread.stop();
            }
            this.hide();
        }
        return true;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.hide();
        }
        return super.handleEvent(event);
    }
    
    private String code(String s, final String s2, final int n) {
        String string = "";
        for (int i = 0; i < s.length(); ++i) {
            final String substring = s.substring(i, i + 1);
            final int cton = this.cton(s2, substring);
            String ntoc;
            if (cton < 64) {
                ntoc = this.ntoc(s2, cton ^ s.length() / 2 + n);
            }
            else {
                ntoc = substring;
            }
            string += ntoc;
        }
        final char[] charArray = string.toCharArray();
        final char[] charArray2 = string.toCharArray();
        final char c = charArray2[0];
        for (int j = 0; j < charArray.length - 1; ++j) {
            charArray[j] = charArray2[j + 1];
        }
        charArray[charArray.length - 1] = c;
        final String s3 = new String(charArray);
        if (charArray.length == 2 * (charArray.length / 2)) {
            s = s3.substring(charArray.length / 2, charArray.length) + s3.substring(0, charArray.length / 2);
        }
        else {
            s = s3.substring(charArray.length / 2 + 1, charArray.length) + s3.substring(0, charArray.length / 2 + 1);
        }
        return s;
    }
}
