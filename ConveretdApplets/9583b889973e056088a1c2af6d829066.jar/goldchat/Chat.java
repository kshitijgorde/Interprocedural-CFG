// 
// Decompiled by Procyon v0.5.30
// 

package goldchat;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import jclass.util.JCString;
import java.util.StringTokenizer;
import jclass.bwt.JCActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Component;
import java.awt.event.ActionListener;
import jclass.bwt.JCActionListener;
import java.awt.LayoutManager;
import java.awt.event.WindowListener;
import java.awt.Dimension;
import java.awt.Font;
import jclass.bwt.JCList;
import java.awt.Button;
import jclass.bwt.JCTextField;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.Frame;

public class Chat extends Frame
{
    Panel InputBox;
    Panel OutputBox;
    BorderLayout borderLayout1;
    JCTextField InputTxt;
    Button SendBtn;
    BorderLayout borderLayout2;
    JCList OutputList;
    
    public Chat() {
        this.InputBox = new Panel();
        this.OutputBox = new Panel();
        this.borderLayout1 = new BorderLayout();
        this.InputTxt = new JCTextField();
        this.SendBtn = new Button();
        this.borderLayout2 = new BorderLayout();
        this.OutputList = new JCList();
        try {
            this._$3231();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void _$3231() throws Exception {
        this.setFont(new Font("Helvetica", 1, 12));
        this.setSize(new Dimension(439, 246));
        this.addWindowListener(new 1());
        this.InputBox.setLayout(this.borderLayout1);
        this.OutputBox.setLayout(this.borderLayout2);
        this.InputTxt.setHighlightThickness(1);
        this.InputTxt.setBackground(loader.inputBgColor);
        this.InputTxt.setForeground(loader.inputFgColor);
        this.InputTxt.setMaximumLength(200);
        this.InputTxt.addActionListener(new 2());
        this.SendBtn.setLabel(" SEND ");
        this.OutputList.setBackground(loader.secondaryBgColor);
        this.OutputList.setForeground(loader.secondaryFgColor);
        this.OutputList.setFont(new Font("Helvetica", 1, 14));
        this.OutputList.setAutoSelect(true);
        this.OutputList.setSelectedBackground(loader.secondaryBgColor);
        this.OutputList.addActionListener(new 3());
        this.OutputList.setRowHeight(-998);
        this.SendBtn.addActionListener(new 4());
        this.SendBtn.setBackground(loader.buttonBgColor);
        this.SendBtn.setForeground(loader.buttonFgColor);
        this.SendBtn.setFont(new Font("Dialog", 1, 12));
        this.setForeground(loader.primaryFgColor);
        this.setBackground(loader.primaryBgColor);
        this.add(this.InputBox, "South");
        this.InputBox.add(this.InputTxt, "Center");
        this.InputBox.add(this.SendBtn, "East");
        this.add(this.OutputBox, "Center");
        this.OutputBox.add(this.OutputList, "Center");
    }
    
    void this_windowClosing(final WindowEvent windowEvent) {
        this.ResetChat();
    }
    
    void InputTxt_actionPerformed(final JCActionEvent jcActionEvent) {
        this.SendBtn_actionPerformed();
    }
    
    void SendBtn_actionPerformed() {
        if (this.getTitle().compareTo("") == 0) {
            this.AppendText("", "!! User No Longer Available!");
            this.InputTxt.beep();
            return;
        }
        if (this.InputTxt.getText().trim().compareTo("") == 0) {
            this.AppendText("", "!! Cannot Send An Empty Message!");
            this.InputTxt.beep();
            return;
        }
        if (this.InputTxt.getText().trim().length() > 200) {
            this.AppendText("", "!! Message Too Long! Max 200 Chars.");
            this.InputTxt.setText("");
            this.InputTxt.beep();
            return;
        }
        String s = this.InputTxt.getText().trim().replace('\"', '\'');
        if (loader.UserFlag.compareTo("WIZOP") != 0) {
            s = s.replace('[', '{');
        }
        loader.SendLine(String.valueOf(String.valueOf(String.valueOf(String.valueOf("300 100 ").concat(String.valueOf(this.getTitle()))).concat(String.valueOf(" "))).concat(String.valueOf(s))).concat(String.valueOf(" ")));
        this.AppendText(loader.Username, s);
        this.InputTxt.setText("");
    }
    
    void this_windowActivated(final WindowEvent windowEvent) {
        this.InputTxt.requestFocus();
    }
    
    void ResetChat() {
        this.OutputList.clear();
        this.InputTxt.setText("");
        this.setTitle("");
        this.setVisible(false);
    }
    
    void AppendText(final String s, final String s2) {
        String s3;
        if (s.compareTo("") == 0) {
            s3 = "[COLOR=black]";
        }
        else if (s.compareTo(loader.Username) == 0) {
            s3 = String.valueOf(String.valueOf("[COLOR=red]").concat(String.valueOf(s))).concat(String.valueOf("[COLOR=black] > [COLOR=gray]"));
        }
        else {
            s3 = String.valueOf(String.valueOf("[COLOR=blue]").concat(String.valueOf(s))).concat(String.valueOf("[COLOR=black] > [COLOR=darkgray]"));
        }
        String s4 = s3;
        final StringTokenizer stringTokenizer = new StringTokenizer(s2);
        while (stringTokenizer.hasMoreTokens()) {
            String s5 = stringTokenizer.nextToken();
            if (s5.compareTo(":)") == 0) {
                s5 = String.valueOf(String.valueOf("[align=middle][IMG=").concat(String.valueOf(loader.myCodeBase))).concat(String.valueOf("img11.gif]"));
            }
            else if (s5.compareTo(";)") == 0) {
                s5 = String.valueOf(String.valueOf("[align=middle][IMG=").concat(String.valueOf(loader.myCodeBase))).concat(String.valueOf("img12.gif]"));
            }
            else if (s5.toLowerCase().compareTo(":p") == 0) {
                s5 = String.valueOf(String.valueOf("[align=middle][IMG=").concat(String.valueOf(loader.myCodeBase))).concat(String.valueOf("img13.gif]"));
            }
            else if (s5.compareTo(":(") == 0) {
                s5 = String.valueOf(String.valueOf("[align=middle][IMG=").concat(String.valueOf(loader.myCodeBase))).concat(String.valueOf("img14.gif]"));
            }
            else if (s5.compareTo("8)") == 0) {
                s5 = String.valueOf(String.valueOf("[align=middle][IMG=").concat(String.valueOf(loader.myCodeBase))).concat(String.valueOf("img15.gif]"));
            }
            else if (s5.compareTo("!!") == 0) {
                s5 = String.valueOf(String.valueOf("[align=middle][IMG=").concat(String.valueOf(loader.myCodeBase))).concat(String.valueOf("img16.gif]"));
            }
            else if (s5.compareTo("])") == 0) {
                s5 = String.valueOf(String.valueOf("[align=middle][IMG=").concat(String.valueOf(loader.myCodeBase))).concat(String.valueOf("img17.gif]"));
            }
            else if (s5.compareTo(":]") == 0) {
                s5 = String.valueOf(String.valueOf("[align=middle][IMG=").concat(String.valueOf(loader.myCodeBase))).concat(String.valueOf("img18.gif]"));
            }
            else if (s5.toLowerCase().compareTo(":s") == 0) {
                s5 = String.valueOf(String.valueOf("[align=middle][IMG=").concat(String.valueOf(loader.myCodeBase))).concat(String.valueOf("img19.gif]"));
            }
            else if (s5.toLowerCase().compareTo(":o") == 0) {
                s5 = String.valueOf(String.valueOf("[align=middle][IMG=").concat(String.valueOf(loader.myCodeBase))).concat(String.valueOf("img20.gif]"));
            }
            if (JCString.parse(this.OutputList, s4).getWidth(this.OutputList.getViewport(), this.OutputList.getFont()) + JCString.parse(this.OutputList, String.valueOf(" ").concat(String.valueOf(s5))).getWidth(this.OutputList.getViewport(), this.OutputList.getFont()) > this.OutputList.getSize().width - 70) {
                s3 = String.valueOf(String.valueOf(s3).concat(String.valueOf("[NEWLINE] "))).concat(String.valueOf(s5));
                s4 = String.valueOf(" ").concat(String.valueOf(s5));
            }
            else {
                s3 = String.valueOf(String.valueOf(s3).concat(String.valueOf(" "))).concat(String.valueOf(s5));
                s4 = String.valueOf(String.valueOf(s4).concat(String.valueOf(" "))).concat(String.valueOf(s5));
            }
        }
        final JCString parse = JCString.parse(this.OutputList, s3);
        this.OutputList.setBatched(true);
        while (this.OutputList.countItems() >= 50) {
            this.OutputList.deleteItem(0);
        }
        this.OutputList.addItem(parse);
        this.OutputList.setBatched(false);
        this.OutputList.makeVisible(this.OutputList.countItems() - 1);
    }
    
    void OutputList_actionPerformed(final JCActionEvent jcActionEvent) {
        this.OutputList.clear();
    }
    
    class 1 extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            Chat.this.this_windowClosing(windowEvent);
        }
        
        public void windowActivated(final WindowEvent windowEvent) {
            Chat.this.this_windowActivated(windowEvent);
        }
    }
    
    class 2 implements JCActionListener
    {
        public void actionPerformed(final JCActionEvent jcActionEvent) {
            Chat.this.InputTxt_actionPerformed(jcActionEvent);
        }
    }
    
    class 3 implements JCActionListener
    {
        public void actionPerformed(final JCActionEvent jcActionEvent) {
            Chat.this.OutputList_actionPerformed(jcActionEvent);
        }
    }
    
    class 4 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            Chat.this.SendBtn_actionPerformed();
        }
    }
}
