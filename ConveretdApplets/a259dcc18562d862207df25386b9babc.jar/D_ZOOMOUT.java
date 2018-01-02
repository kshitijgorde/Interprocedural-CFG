import java.awt.event.ItemEvent;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.List;
import java.awt.event.ItemListener;
import java.awt.event.WindowListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class D_ZOOMOUT extends Frame implements WindowListener, ItemListener
{
    public static D_ZOOMOUT ZOOMOUT;
    public static int fontHeight;
    public static int letterWidth;
    public static int boxHeight;
    public static int infoTop;
    public static int infoLeft;
    public static int windowWidth;
    public static int windowHeight;
    public static String[] zoomStr;
    public static List zoomList;
    public static dataGraphConstants g;
    
    public static void ZOOMOUT_MAIN() {
        D_ZOOMOUT.g = D_GRAPH.g;
        if (D_ZOOMOUT.ZOOMOUT != null) {
            D_ZOOMOUT.ZOOMOUT.show();
            return;
        }
        (D_ZOOMOUT.ZOOMOUT = new D_ZOOMOUT()).setResizable(false);
        D_ZOOMOUT.ZOOMOUT.setTitle("Zoom Out Window");
        DFIELD.AddMenuWindow(D_ZOOMOUT.ZOOMOUT);
        D_ZOOMOUT.ZOOMOUT.setBackground(Color.lightGray);
        final Font fontNormal = C_TOOL.fontNormal;
        D_ZOOMOUT.ZOOMOUT.setFont(fontNormal);
        final FontMetrics fontMetrics = D_ZOOMOUT.ZOOMOUT.getFontMetrics(fontNormal);
        D_ZOOMOUT.fontHeight = (D_ZOOMOUT.fontHeight = fontMetrics.getHeight());
        D_ZOOMOUT.letterWidth = fontMetrics.stringWidth("X");
        D_ZOOMOUT.windowWidth = D_ZOOMOUT.letterWidth * 37;
        D_ZOOMOUT.boxHeight = (int)(D_ZOOMOUT.fontHeight * 1.5);
        D_ZOOMOUT.ZOOMOUT.setBounds(25, 25, D_ZOOMOUT.windowWidth, D_ZOOMOUT.fontHeight * 10);
        D_ZOOMOUT.ZOOMOUT.show();
        D_ZOOMOUT.ZOOMOUT.addWindowListener(D_ZOOMOUT.ZOOMOUT);
        final Dimension size = D_ZOOMOUT.ZOOMOUT.getSize();
        D_ZOOMOUT.windowWidth = size.width - D_ZOOMOUT.ZOOMOUT.getInsets().left - D_ZOOMOUT.ZOOMOUT.getInsets().right;
        D_ZOOMOUT.windowHeight = size.height - D_ZOOMOUT.ZOOMOUT.getInsets().top - D_ZOOMOUT.ZOOMOUT.getInsets().bottom;
        final int top = D_ZOOMOUT.ZOOMOUT.getInsets().top;
        final int left = D_ZOOMOUT.ZOOMOUT.getInsets().left;
        D_ZOOMOUT.ZOOMOUT.setLayout(null);
        D_ZOOMOUT.zoomList = new List(50, false);
        D_ZOOMOUT.ZOOMOUT.add(D_ZOOMOUT.zoomList);
        D_ZOOMOUT.zoomList.addItemListener(D_ZOOMOUT.ZOOMOUT);
        D_ZOOMOUT.zoomList.setFont(fontNormal);
        D_ZOOMOUT.zoomList.setBounds(left + 5, top + 5, D_ZOOMOUT.windowWidth - 10, D_ZOOMOUT.windowHeight - 10);
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        D_ZOOMOUT.ZOOMOUT.setVisible(false);
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
        D_GRAPH.InfoLabelOff();
        D_ZOOMOUT.zoomList.removeAll();
        D_ZOOMOUT.zoomStr[0] = "2x out beyond current view";
        D_ZOOMOUT.zoomStr[1] = "Original";
        final int zoomCount = D_GRAPH.zoomCount;
        for (int i = 2; i <= zoomCount; ++i) {
            final double n = D_GRAPH.zoomYmax[i];
            final double n2 = D_GRAPH.zoomYmin[i];
            final double n3 = D_GRAPH.zoomXmax[i];
            final double n4 = D_GRAPH.zoomXmin[i];
            final double n5 = (n - n2) / 10.0;
            final double n6 = (n3 - n4) / 10.0;
            D_ZOOMOUT.zoomStr[i] = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(C_TOOL.AxisValueToString(n2, n5)))).append("<").append(DFIELD.diffyQ.yName).append("<").append(C_TOOL.AxisValueToString(n, n5)).append("    ").append(C_TOOL.AxisValueToString(n4, n6)).append("<").append(DFIELD.diffyQ.xName).append("<").append(C_TOOL.AxisValueToString(n3, n6))));
        }
        for (int j = 0; j <= zoomCount; ++j) {
            D_ZOOMOUT.zoomList.add(D_ZOOMOUT.zoomStr[j]);
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getStateChange() == 2) {
            return;
        }
        final int selectedIndex = D_ZOOMOUT.zoomList.getSelectedIndex();
        boolean b = false;
        if (selectedIndex == -1) {
            return;
        }
        ++D_GRAPH.zoomCount;
        D_GRAPH.zoomXmin[D_GRAPH.zoomCount] = D_ZOOMOUT.g.xmin;
        D_GRAPH.zoomXmax[D_GRAPH.zoomCount] = D_ZOOMOUT.g.xmax;
        D_GRAPH.zoomYmin[D_GRAPH.zoomCount] = D_ZOOMOUT.g.ymin;
        D_GRAPH.zoomYmax[D_GRAPH.zoomCount] = D_ZOOMOUT.g.ymax;
        if (selectedIndex == 0) {
            final double xmin = D_ZOOMOUT.g.xmin;
            final double xmax = D_ZOOMOUT.g.xmax;
            final double ymin = D_ZOOMOUT.g.ymin;
            final double ymax = D_ZOOMOUT.g.ymax;
            final double n = (xmax - xmin) / 2.0;
            D_ZOOMOUT.g.xmin = xmin - n;
            D_ZOOMOUT.g.xmax = xmax + n;
            final double n2 = (ymax - ymin) / 2.0;
            D_ZOOMOUT.g.ymin = ymin - n2;
            D_ZOOMOUT.g.ymax = ymax + n2;
            if (D_ZOOMOUT.g.xmin < D_ZOOMOUT.g.xmin0) {
                D_ZOOMOUT.g.xmin0 = D_ZOOMOUT.g.xmin;
                b = true;
            }
            if (D_ZOOMOUT.g.xmax > D_ZOOMOUT.g.xmax0) {
                D_ZOOMOUT.g.xmax0 = D_ZOOMOUT.g.xmax;
                b = true;
            }
            if (D_ZOOMOUT.g.ymin < D_ZOOMOUT.g.ymin0) {
                D_ZOOMOUT.g.ymin0 = D_ZOOMOUT.g.ymin;
                b = true;
            }
            if (D_ZOOMOUT.g.ymax > D_ZOOMOUT.g.ymax0) {
                D_ZOOMOUT.g.ymax0 = D_ZOOMOUT.g.ymax;
                b = true;
            }
        }
        else {
            D_ZOOMOUT.g.xmin = D_GRAPH.zoomXmin[selectedIndex];
            D_ZOOMOUT.g.xmax = D_GRAPH.zoomXmax[selectedIndex];
            D_ZOOMOUT.g.ymin = D_GRAPH.zoomYmin[selectedIndex];
            D_ZOOMOUT.g.ymax = D_GRAPH.zoomYmax[selectedIndex];
        }
        D_GRAPH.GRAPH.show();
        if (b) {
            D_GRAPH.ReCalculate();
        }
        else {
            D_GRAPH.ReDrawEverything();
        }
    }
    
    public static void QuitD_ZOOMOUT() {
        if (D_ZOOMOUT.ZOOMOUT == null) {
            return;
        }
        D_ZOOMOUT.ZOOMOUT.dispose();
        D_ZOOMOUT.ZOOMOUT = null;
    }
    
    static {
        D_ZOOMOUT.ZOOMOUT = null;
        D_ZOOMOUT.zoomStr = new String[50];
    }
}
