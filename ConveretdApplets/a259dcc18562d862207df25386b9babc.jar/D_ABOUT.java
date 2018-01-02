import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Button;
import java.awt.Color;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class D_ABOUT extends Frame implements WindowListener, ActionListener
{
    public static D_ABOUT ABOUT;
    
    public static void ABOUT_MAIN(final D_ABOUT about) {
        (D_ABOUT.ABOUT = about).setResizable(true);
        D_ABOUT.ABOUT.setTitle("DFIELD     Copyright 1994 - 2002, John C. Polking, Rice University");
        D_ABOUT.ABOUT.setBackground(C_TOOL.borderColor);
        final int fontHeight = C_TOOL.fontHeight;
        final int fontWidth = C_TOOL.fontWidth;
        D_ABOUT.ABOUT.setBounds(0, 0, 200, 200);
        D_ABOUT.ABOUT.show();
        D_ABOUT.ABOUT.addWindowListener(D_ABOUT.ABOUT);
        final TextArea textArea = new TextArea("", 4, 80, 3);
        final TextArea textArea2 = new TextArea("", 10, 80, 3);
        final TextArea textArea3 = new TextArea("", 50, 80, 1);
        textArea.setEditable(false);
        textArea2.setEditable(false);
        textArea3.setEditable(false);
        D_ABOUT.ABOUT.setForeground(Color.black);
        textArea.setForeground(new Color(0, 128, 0));
        textArea2.setForeground(Color.black);
        textArea3.setForeground(Color.black);
        textArea.setBackground(C_TOOL.borderColor);
        textArea2.setBackground(C_TOOL.borderColor);
        textArea3.setBackground(C_TOOL.borderColor);
        textArea.setFont(C_TOOL.fontBold);
        textArea2.setFont(C_TOOL.fontBold);
        textArea3.setFont(C_TOOL.fontNormal);
        final Button button = new Button("Ok");
        button.setFont(C_TOOL.fontBold);
        final Button button2 = new Button("Quit");
        button2.setFont(C_TOOL.fontBold);
        D_ABOUT.ABOUT.setLayout(null);
        D_ABOUT.ABOUT.add(textArea);
        D_ABOUT.ABOUT.add(textArea2);
        D_ABOUT.ABOUT.add(textArea3);
        D_ABOUT.ABOUT.add(button);
        button.addActionListener(D_ABOUT.ABOUT);
        D_ABOUT.ABOUT.add(button2);
        button2.addActionListener(D_ABOUT.ABOUT);
        final int n = C_TOOL.windowLeft + 5;
        final int n2 = C_TOOL.windowTopNoMenu + 5;
        final int n3 = fontWidth * 64;
        final int n4 = fontHeight * 12;
        final int n5 = n2 + n4 + 5;
        final int n6 = fontHeight * 6;
        final int n7 = n5 + n6 + 5;
        final int n8 = fontWidth * 10;
        final int n9 = (int)(fontHeight * 1.3);
        final int n10 = 0;
        final int n11 = n7 + n10 + 5;
        final int n12 = n + (n3 / 2 - n8) / 2;
        final int n13 = n12 + n3 / 2;
        textArea.setBounds(n, n2, n3, n4);
        textArea2.setBounds(n, n5, n3, n6);
        textArea3.setBounds(n, n7, n3, n10);
        button.setBounds(n12, n11, n8, n9);
        button2.setBounds(n13, n11, n8, n9);
        final int n14 = n3 + 10 + C_TOOL.windowLeft + C_TOOL.windowRight;
        final int n15 = n11 + n9 + C_TOOL.windowBottom + 10;
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        D_ABOUT.ABOUT.setBounds((screenSize.width - n14) / 2, (screenSize.height - n15) / 2, n14, n15);
        textArea.setText("DFIELD is an interactive tool for studying single first order differential equations.  DFIELD graphs a direction field for a given differential equation.  When the mouse button is pressed in the direction field, that point is used as an initial condition, and the resulting solution is plotted.  Options include selecting between a variety of numerical solvers and parameters of those solvers.\n\nThis software is designed as a companion for 'Ordinary Differential Equations using MATLAB' by David Arnold & John C. Polking.  Second edition published by Prentice Hall, 1999. ISBN: 0-13-011381-6.");
        textArea2.setText("DFIELD 2002.2\nCopyright 1994 - 2002, John C. Polking, Rice University\n\nAuthor:  John Polking - Professor, Dept of Mathematics, Rice University.\nSupporting Programmer:  Joel Castellanos - Rice University.");
        textArea3.setText("USAGE:\nDFIELD may be installed and used without charge, and without registration for Educational (non-commercial) purposes.   Educational Purposes includes both personal and institutional education.\n\nDISTRIBUTION:\nYou are hereby licensed to make as many copies of DFIELD (software, and documentation) as you wish; to give exact copies of the original software to anyone; and to distribute DFIELD in its unmodified form via electronic means.  You are specifically prohibited from charging, or requesting donations, for any such copies, however made.\n\nDISCLAIMER OF WARRANTY:\nTHIS SOFTWARE AND THE ACCOMPANYING FILES ARE OFFERED 'AS IS' AND WITHOUT WARRANTIES AS TO PERFORMANCE OF MERCHANTABILITY OR ANY OTHER WARRANTIES WHETHER EXPRESSED OR IMPLIED.\n\nMORE INFORMATION:\nThe latest version of DFIELD, help files, and other information can found on the DFIELD Home-page:\n     http://math.rice.edu/~dfield/\nPlease send questions, comments, and/or bug reports to:\n     John Polking, polking@rice.edu\n        or\n     Joel Castellanos, joel@math.rice.edu\n");
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        QuitD_ABOUT();
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("Quit")) {
            DFIELD.quitDFIELD();
        }
        else {
            QuitD_ABOUT();
        }
    }
    
    public static void QuitD_ABOUT() {
        if (D_ABOUT.ABOUT == null) {
            return;
        }
        D_ABOUT.ABOUT.dispose();
        DFIELD.ABOUT = null;
        DFIELD.EQU.toFront();
    }
}
