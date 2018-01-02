// 
// Decompiled by Procyon v0.5.30
// 

package viewer;

import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Event;
import java.awt.Container;
import java.awt.Color;
import java.util.StringTokenizer;
import viewer.tools.Tools_MainPanel;
import viewer.cards.Card_MozaicPanel;
import viewer.cards.Card_ZoomCanvas;
import viewer.cards.Card_GreetingsCanvas;
import java.awt.CardLayout;
import java.awt.Panel;
import java.awt.Image;
import java.awt.MediaTracker;
import java.applet.Applet;

public class ImageViewerApplet extends Applet implements Runnable
{
    public static String[] fileList;
    MediaTracker imageTracker;
    public static Image[] imageList;
    private Panel cardPanel;
    private CardLayout cardLayout;
    private Card_GreetingsCanvas greetingsCard;
    private Card_ZoomCanvas zoomCard;
    private Card_MozaicPanel mozaicCard;
    private Tools_MainPanel toolsMainPanel;
    private Thread imageLoader;
    
    private String[] tokenImgList(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ", ");
        final String[] array = new String[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = stringTokenizer.nextToken();
        }
        return array;
    }
    
    private String[] getFileList() {
        String parameter = this.getParameter("ImageList");
        if (parameter == null || parameter == "") {
            parameter = "Can't get parameters from HTML file.";
        }
        return this.tokenImgList(parameter);
    }
    
    private void setAppletBGColor() {
        final String parameter = this.getParameter("bgColor");
        if (parameter == null || parameter.equals("")) {
            this.setBackground(new Color(46, 131, 216));
            return;
        }
        this.setBackground(new Color(Integer.parseInt(parameter.substring(1, 3), 16), Integer.parseInt(parameter.substring(3, 5), 16), Integer.parseInt(parameter.substring(5, 7), 16)));
    }
    
    public void start() {
        if (this.imageLoader == null) {
            (this.imageLoader = new Thread(this)).start();
            Thread.yield();
        }
    }
    
    public void showSingle(final int n) {
        this.zoomCard.setImage(ImageViewerApplet.imageList[n]);
        this.cardLayout.show(this.cardPanel, "single");
        this.toolsMainPanel.imagePanel.modeBtn.setLabel("Mosaic");
        this.toolsMainPanel.imagePanel.imgChoice.select(n);
        this.toolsMainPanel.zoomPanel.reset();
    }
    
    public String getAppletInfo() {
        return "Author: plamen_matanski@yahoo.com © Copyright BuddySoft®";
    }
    
    public boolean action(final Event event, final Object o) {
        final String string = event.arg.toString();
        if (event.target == this.toolsMainPanel.zoomPanel.zoomOut || event.target == this.toolsMainPanel.zoomPanel.zoomIn || event.target == this.toolsMainPanel.zoomPanel.zoomChoice) {
            this.cardLayout.show(this.cardPanel, "single");
            this.toolsMainPanel.imagePanel.modeBtn.setLabel("Mosaic");
            this.zoomCard.zoom(this.toolsMainPanel.zoomPanel.getZoom());
            return true;
        }
        if (event.target == this.toolsMainPanel.imagePanel.modeBtn) {
            if (string == "Single") {
                this.cardLayout.show(this.cardPanel, "single");
                this.toolsMainPanel.imagePanel.modeBtn.setLabel("Mosaic");
                return true;
            }
            this.cardLayout.show(this.cardPanel, "mosaic");
            this.toolsMainPanel.imagePanel.modeBtn.setLabel("Single");
            return true;
        }
        else {
            if (event.target == this.toolsMainPanel.imagePanel.imgChoice) {
                final int selectedIndex = this.toolsMainPanel.imagePanel.imgChoice.getSelectedIndex();
                if (this.imageTracker.checkID(selectedIndex)) {
                    this.zoomCard.setImage(ImageViewerApplet.imageList[selectedIndex]);
                    this.toolsMainPanel.zoomPanel.reset();
                    this.cardLayout.show(this.cardPanel, "single");
                    this.toolsMainPanel.imagePanel.modeBtn.setLabel("Mosaic");
                    this.zoomCard.repaint();
                }
                else {
                    final String string2 = "Please wait! Loading " + ImageViewerApplet.fileList[selectedIndex];
                    this.zoomCard.setMassage(string2);
                    this.showStatus(string2);
                    try {
                        this.imageTracker.waitForID(selectedIndex);
                    }
                    catch (Exception ex) {
                        System.err.println(ex);
                    }
                    this.zoomCard.setImage(ImageViewerApplet.imageList[selectedIndex]);
                    this.toolsMainPanel.zoomPanel.reset();
                    this.zoomCard.repaint();
                }
                return true;
            }
            this.showStatus(this.getAppletInfo());
            return false;
        }
    }
    
    public void run() {
        int n = 0;
        for (int i = 0; i < ImageViewerApplet.imageList.length; ++i) {
            ImageViewerApplet.imageList[i] = this.getImage(this.getDocumentBase(), "images/" + ImageViewerApplet.fileList[i]);
            this.imageTracker.addImage(ImageViewerApplet.imageList[i], i);
            if (this.imageTracker.checkID(i)) {
                this.showStatus("Done.");
                if (i == 0) {
                    this.zoomCard.setImage(ImageViewerApplet.imageList[0]);
                }
            }
            else {
                this.showStatus("Loading " + ImageViewerApplet.fileList[i] + ".");
                try {
                    this.imageTracker.waitForID(i);
                }
                catch (Exception ex) {
                    System.err.println(ex);
                }
                if (this.imageTracker.checkID(0) && n == 0) {
                    n = 1;
                    this.zoomCard.setImage(ImageViewerApplet.imageList[0]);
                }
            }
            Thread.yield();
            this.mozaicCard.mozaicTile[i].canvas.repaint();
        }
        this.showStatus("Done");
    }
    
    public void init() {
        this.setAppletBGColor();
        ImageViewerApplet.fileList = this.getFileList();
        ImageViewerApplet.imageList = new Image[ImageViewerApplet.fileList.length];
        this.imageTracker = new MediaTracker(this);
        this.initGUI();
        this.greetingsCard.pic = this.getImage(this.getCodeBase(), "viewer/greetings.gif");
        this.imageTracker.addImage(this.greetingsCard.pic, 111);
        try {
            this.imageTracker.waitForID(111);
        }
        catch (Exception ex) {
            System.err.println(ex);
        }
        this.toolsMainPanel.imagePanel.addImageItems(ImageViewerApplet.fileList);
    }
    
    private void initGUI() {
        this.setLayout(new BorderLayout());
        this.toolsMainPanel = new Tools_MainPanel(this);
        this.cardPanel = new Panel();
        this.cardLayout = new CardLayout();
        this.cardPanel.setLayout(this.cardLayout);
        final RaisedPanel raisedPanel = new RaisedPanel(8, 8, 8, 8, 6);
        raisedPanel.setLayout(new BorderLayout());
        raisedPanel.add("Center", this.greetingsCard = new Card_GreetingsCanvas());
        this.cardPanel.add("greetings", raisedPanel);
        final RaisedPanel raisedPanel2 = new RaisedPanel(8, 8, 8, 8, 6);
        raisedPanel2.setLayout(new BorderLayout());
        raisedPanel2.add("Center", this.zoomCard = new Card_ZoomCanvas());
        this.cardPanel.add("single", raisedPanel2);
        (this.mozaicCard = new Card_MozaicPanel(this)).setBackground(this.getBackground().darker());
        this.cardPanel.add("mosaic", this.mozaicCard);
        this.add("East", this.toolsMainPanel);
        this.add("Center", this.cardPanel);
    }
}
