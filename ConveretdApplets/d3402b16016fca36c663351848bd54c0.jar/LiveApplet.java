import java.awt.Event;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.applet.Applet;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Point;
import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class LiveApplet extends WvAppletTemplate
{
    private boolean IS_V36;
    private WvCardLayoutPanel wvCardLayoutPanel;
    private WvPanoramaCanvas wvPanoramaCanvas;
    private WvPanoramaButton wvPanoramaButton;
    private Frame wvPanoramaFrame;
    private WvLiveCanvas wvLiveCanvas;
    
    public void postInit() {
        int min = 320;
        int n = 240;
        final String parameter;
        if ((parameter = this.getParameter("video_width")) != null) {
            try {
                min = Math.min(768, Math.max(80, Integer.parseInt(parameter)));
                n = min * 3 / 4;
            }
            catch (Exception ex4) {}
        }
        final String parameter2;
        if ((parameter2 = this.getParameter("click_action")) != null) {
            super.wvDispatcher.putObject("click_action", parameter2);
        }
        final String parameter3;
        if ((parameter3 = this.getParameter("auto_connect")) != null) {
            super.wvDispatcher.putObject("auto_connect", new Boolean(!parameter3.equalsIgnoreCase("off")));
        }
        final String parameter4;
        if ((parameter4 = this.getParameter("no_connect_msg")) != null) {
            super.wvDispatcher.putObject("no_connect_msg", parameter4);
        }
        super.wvDispatcher.putObject("hdrdelay", new Integer(-1));
        final String parameter5;
        if ((parameter5 = this.getParameter("hdrdelay")) != null) {
            try {
                super.wvDispatcher.putObject("hdrdelay", new Integer(parameter5));
            }
            catch (NumberFormatException ex5) {}
        }
        final String parameter6;
        if ((parameter6 = this.getParameter("purejava")) != null) {
            super.wvDispatcher.putObject("bPureJava", parameter6);
        }
        final String parameter7;
        if ((parameter7 = this.getParameter("connect_msg")) != null) {
            super.wvDispatcher.putObject("connect_msg", parameter7);
        }
        final String parameter8;
        if ((parameter8 = this.getParameter("error_msg")) != null) {
            super.wvDispatcher.putObject("error_msg", parameter8);
        }
        final String parameter9;
        if ((parameter9 = this.getParameter("disconnect_msg")) != null) {
            super.wvDispatcher.putObject("disconnect_msg", parameter9);
        }
        final String parameter10;
        if ((parameter10 = this.getParameter("too_many_msg")) != null) {
            super.wvDispatcher.putObject("too_many_msg", parameter10);
        }
        final String parameter11;
        if ((parameter11 = this.getParameter("no_control_msg")) != null) {
            super.wvDispatcher.putObject("no_control_msg", parameter11);
        }
        final String parameter12;
        if ((parameter12 = this.getParameter("camera_off_msg")) != null) {
            super.wvDispatcher.putObject("camera_off_msg", parameter12);
        }
        final String parameter13;
        if ((parameter13 = this.getParameter("no_privilege_msg")) != null) {
            super.wvDispatcher.putObject("no_privilege_msg", parameter13);
        }
        final String parameter14;
        if ((parameter14 = this.getParameter("push_control_msg")) != null) {
            super.wvDispatcher.putObject("push_control_msg", parameter14);
        }
        final String parameter15;
        if ((parameter15 = this.getParameter("alt_push_control_msg")) != null) {
            super.wvDispatcher.putObject("alt_push_control_msg", parameter15);
        }
        final String parameter16;
        if ((parameter16 = this.getParameter("cam_switch_msg")) != null) {
            super.wvDispatcher.putObject("cam_switch_msg", parameter16);
        }
        final String parameter17;
        if ((parameter17 = this.getParameter("font_size")) != null) {
            try {
                super.wvDispatcher.putObject("font_size", new Integer(parameter17));
            }
            catch (NumberFormatException ex6) {}
        }
        final String parameter18;
        if ((parameter18 = this.getParameter("overlay_font_color")) != null) {
            try {
                super.wvDispatcher.putObject("font_color", new Color(Integer.parseInt(parameter18.substring(1, 3), 16), Integer.parseInt(parameter18.substring(3, 5), 16), Integer.parseInt(parameter18.substring(5, 7), 16)));
            }
            catch (Exception ex7) {}
        }
        Object o = null;
        final String parameter19;
        if ((parameter19 = this.getParameter("capture_size")) != null && ("640x480".equals(parameter19) || "320x240".equals(parameter19) || "160x120".equals(parameter19) || "80x60".equals(parameter19) || "768x576".equals(parameter19) || "384x288".equals(parameter19) || "192x144".equals(parameter19) || "96x72".equals(parameter19))) {
            o = parameter19;
        }
        super.wvDispatcher.putObject("captureSize", o);
        String nextToken = null;
        Object o2 = null;
        final String parameter20;
        if ((parameter20 = this.getParameter("superimpose")) != null) {
            try {
                final StringTokenizer stringTokenizer = new StringTokenizer(parameter20, ", ");
                nextToken = stringTokenizer.nextToken();
                o2 = new Point(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));
            }
            catch (Exception ex8) {}
        }
        final String[] array = { this.getParameter("open_image"), this.getParameter("close_image"), nextToken };
        final Image[] array2 = new Image[array.length];
        final MediaTracker mediaTracker = new MediaTracker(this);
        for (int i = 0; i < array.length; ++i) {
            if (array[i] != null) {
                mediaTracker.addImage(array2[i] = this.getImage(this.getCodeBase(), array[i]), i);
            }
        }
        try {
            while (!mediaTracker.checkAll()) {
                mediaTracker.waitForAll();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        for (int j = 0; j < array.length; ++j) {
            if (array2[j] != null && mediaTracker.isErrorID(j)) {
                array2[j] = null;
            }
        }
        if (array2[0] != null) {
            super.wvDispatcher.putObject("openImage", array2[0]);
        }
        if (array2[1] != null) {
            super.wvDispatcher.putObject("closeImage", array2[1]);
        }
        if (array2[2] != null) {
            super.wvDispatcher.putObject("imposeImage", array2[2]);
        }
        if (o2 != null) {
            super.wvDispatcher.putObject("imposePos", o2);
        }
        final String parameter21;
        if ((parameter21 = this.getParameter("open_image_time")) != null) {
            try {
                super.wvDispatcher.putObject("openImageTime", new Integer((int)Math.max(0L, Math.min(30000L, Long.parseLong(parameter21)))));
            }
            catch (Exception ex2) {
                System.out.println(this + " " + ex2);
            }
        }
        super.wvDispatcher.putObject("hostname", this.getParameter("hostname"));
        super.wvDispatcher.putObject("comment", this.getParameter("comment"));
        for (int k = 0; k < WvLiveCanvas.overlayName.length; ++k) {
            final String string = "show_" + WvLiveCanvas.overlayName[k];
            final String parameter22 = this.getParameter(string);
            if (parameter22 != null && parameter22.equalsIgnoreCase("on")) {
                super.wvDispatcher.putObject(string, new Boolean(true));
            }
        }
        super.wvDispatcher.initVideoState();
        if ("false".equalsIgnoreCase(this.getParameter("controllable"))) {
            super.wvDispatcher.putObject("controllable", new Boolean(false));
            this.wvLiveCanvas = new WvLiveCanvas(super.wvDispatcher, this, min, n);
            super.wvDispatcher.addWvEventListener(this.wvLiveCanvas);
            final GridBagLayout layout = new GridBagLayout();
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            this.setLayout(layout);
            gridBagConstraints.fill = 0;
            gridBagConstraints.anchor = 10;
            layout.setConstraints(this.wvLiveCanvas, gridBagConstraints);
            this.add(this.wvLiveCanvas);
            return;
        }
        boolean b = true;
        if ("embed".equalsIgnoreCase(this.getParameter("gui_type"))) {
            b = false;
        }
        final Rectangle[][] array3 = { { new Rectangle(0, 0, 450, 40) }, { new Rectangle(0, 45, 80, 27) }, { new Rectangle(90, 45, 20, 20), new Rectangle(110, 45, 20, 20) }, { new Rectangle(130, 45, 18, 18), new Rectangle(150, 45, 18, 18) }, { new Rectangle(170, 45, 18, 18), new Rectangle(190, 45, 18, 18) }, { new Rectangle(231, 44, 22, 22), new Rectangle(261, 44, 22, 22), new Rectangle(291, 44, 22, 22), new Rectangle(321, 44, 22, 22) } };
        Image image = null;
        String parameter23 = this.getParameter("controller_style");
        if (parameter23 == null) {
            parameter23 = "normal";
        }
        super.wvDispatcher.putObject("controllpresetonly", parameter23);
        final String parameter24;
        if ((parameter24 = this.getParameter("bg_image")) != null) {
            try {
                image = this.getImage(this.getCodeBase(), parameter24);
                mediaTracker.addImage(image, 0);
                while (!mediaTracker.checkAll()) {
                    mediaTracker.waitForAll();
                }
                if (image.getWidth(null) <= 0 || image.getHeight(null) <= 0) {
                    image = null;
                }
            }
            catch (Exception ex3) {
                System.out.println(this + " " + ex3);
                image = null;
            }
        }
        final WvCameraChoice wvCameraChoice = new WvCameraChoice(super.wvDispatcher);
        this.wvCardLayoutPanel = new WvCardLayoutPanel(super.wvDispatcher);
        super.wvDispatcher.addWvEventListener(wvCameraChoice);
        super.wvDispatcher.addWvEventListener(this.wvCardLayoutPanel);
        if ("no_pantilt".equalsIgnoreCase(parameter23)) {
            this.IS_V36 = true;
        }
        if ("none".equalsIgnoreCase(parameter23)) {
            this.wvLiveCanvas = new WvLiveCanvas(super.wvDispatcher, this, min, n);
            super.wvDispatcher.addWvEventListener(this.wvLiveCanvas);
            final GridBagLayout layout2 = new GridBagLayout();
            final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
            this.setLayout(layout2);
            gridBagConstraints2.fill = 0;
            gridBagConstraints2.anchor = 10;
            layout2.setConstraints(this.wvLiveCanvas, gridBagConstraints2);
            this.add(this.wvLiveCanvas);
            super.wvDispatcher.addWvEventListener(new WvTimeIndicator(super.wvDispatcher));
            return;
        }
        if ("preset_only".equalsIgnoreCase(parameter23)) {
            WvImageButton wvImageButton;
            if (image == null) {
                wvImageButton = new WvImageButton(super.wvDispatcher, array3[1], false);
            }
            else {
                wvImageButton = new WvImageButton(super.wvDispatcher, image, false, null);
            }
            final WvControlButton wvControlButton = new WvControlButton(super.wvDispatcher, super.wvDispatcher.getIconImage(array3[0][0]));
            this.wvLiveCanvas = new WvLiveCanvas(super.wvDispatcher, this, min, n);
            final WvTimeIndicator wvTimeIndicator = new WvTimeIndicator(super.wvDispatcher);
            final WvToolBarPanel wvToolBarPanel = new WvToolBarPanel(super.wvDispatcher, min);
            final WvCaptureButton wvCaptureButton = new WvCaptureButton(super.wvDispatcher, array3[2], this);
            super.wvDispatcher.addWvEventListener(this.wvLiveCanvas);
            super.wvDispatcher.addWvEventListener(wvTimeIndicator);
            super.wvDispatcher.addWvEventListener(wvControlButton);
            super.wvDispatcher.addWvEventListener(wvToolBarPanel);
            super.wvDispatcher.addWvEventListener(wvCaptureButton);
            final GridBagLayout gridBagLayout = new GridBagLayout();
            final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
            Point point = new Point(335, 0);
            final String parameter25 = this.getParameter("image_offset");
            if (image != null && parameter25 != null) {
                try {
                    final StringTokenizer stringTokenizer2 = new StringTokenizer(parameter25, ", ");
                    point = new Point(Integer.parseInt(stringTokenizer2.nextToken()), Integer.parseInt(stringTokenizer2.nextToken()));
                }
                catch (Exception ex9) {}
            }
            final WvImagePanel wvImagePanel = new WvImagePanel(image, point);
            wvImagePanel.setLayout(gridBagLayout);
            if ("on".equalsIgnoreCase(this.getParameter("show_toolbar"))) {
                gridBagConstraints3.insets = new Insets(7, 0, 0, 0);
                gridBagConstraints3.gridx = 0;
                gridBagConstraints3.gridy = 0;
                gridBagConstraints3.gridwidth = 1;
                gridBagConstraints3.gridheight = 1;
                gridBagConstraints3.fill = 0;
                gridBagConstraints3.anchor = 10;
                gridBagLayout.setConstraints(wvToolBarPanel, gridBagConstraints3);
                this.add(wvToolBarPanel);
            }
            gridBagConstraints3.insets = new Insets(7, 7, 0, 0);
            gridBagConstraints3.gridx = 0;
            gridBagConstraints3.gridy = 0;
            if (!"off".equalsIgnoreCase(this.getParameter("show_capture"))) {
                gridBagConstraints3.gridwidth = 2;
                gridBagConstraints3.gridheight = 1;
            }
            else {
                gridBagConstraints3.gridwidth = 1;
                gridBagConstraints3.gridheight = 1;
            }
            gridBagConstraints3.weightx = 0.0;
            gridBagConstraints3.fill = 2;
            gridBagConstraints3.anchor = 10;
            gridBagLayout.setConstraints(this.wvLiveCanvas, gridBagConstraints3);
            wvImagePanel.add(this.wvLiveCanvas);
            if (image == null && !"off".equalsIgnoreCase(this.getParameter("show_logo"))) {
                gridBagConstraints3.insets = new Insets(7, 7, 0, 0);
                gridBagConstraints3.gridx = gridBagConstraints3.gridwidth;
                gridBagConstraints3.gridy = 0;
                gridBagConstraints3.gridwidth = 1;
                gridBagConstraints3.gridheight = 1;
                gridBagConstraints3.fill = 2;
                gridBagConstraints3.anchor = 11;
                gridBagLayout.setConstraints(wvImageButton, gridBagConstraints3);
                wvImagePanel.add(wvImageButton);
            }
            int n2 = 0;
            if (!"off".equalsIgnoreCase(this.getParameter("show_capture"))) {
                gridBagConstraints3.insets = new Insets(7, 7, 0, 0);
                gridBagConstraints3.gridx = n2++;
                gridBagConstraints3.gridy = 2;
                gridBagConstraints3.gridwidth = 1;
                gridBagConstraints3.gridheight = 1;
                gridBagConstraints3.fill = 2;
                gridBagConstraints3.anchor = 13;
                gridBagLayout.setConstraints(wvCaptureButton, gridBagConstraints3);
                wvImagePanel.add(wvCaptureButton);
            }
            gridBagConstraints3.insets = new Insets(7, 7, 0, 0);
            gridBagConstraints3.gridx = n2++;
            gridBagConstraints3.gridy = 2;
            gridBagConstraints3.gridwidth = 1;
            gridBagConstraints3.gridheight = 1;
            gridBagConstraints3.fill = 2;
            gridBagConstraints3.anchor = 10;
            gridBagLayout.setConstraints(this.wvCardLayoutPanel, gridBagConstraints3);
            wvImagePanel.add(this.wvCardLayoutPanel);
            gridBagConstraints3.insets = new Insets(7, 7, 0, 0);
            gridBagConstraints3.gridx = n2;
            gridBagConstraints3.gridy = 2;
            gridBagConstraints3.anchor = 10;
            gridBagLayout.setConstraints(wvControlButton, gridBagConstraints3);
            wvImagePanel.add(wvControlButton);
            gridBagConstraints3.insets = new Insets(7, 7, 0, 0);
            gridBagConstraints3.gridx = n2;
            gridBagConstraints3.gridy = 0;
            gridBagConstraints3.anchor = 15;
            gridBagLayout.setConstraints(wvTimeIndicator, gridBagConstraints3);
            wvImagePanel.add(wvTimeIndicator);
            this.setLayout(gridBagLayout);
            gridBagConstraints3.gridx = 0;
            gridBagConstraints3.gridy = 1;
            gridBagConstraints3.fill = 0;
            gridBagConstraints3.anchor = 10;
            gridBagLayout.setConstraints(wvImagePanel, gridBagConstraints3);
            this.add(wvImagePanel);
            return;
        }
        WvImageButton wvImageButton2;
        if (image == null) {
            wvImageButton2 = new WvImageButton(super.wvDispatcher, array3[1], false);
        }
        else {
            wvImageButton2 = new WvImageButton(super.wvDispatcher, image, false, new Dimension(80, 27));
        }
        final WvControlButton wvControlButton2 = new WvControlButton(super.wvDispatcher, super.wvDispatcher.getIconImage(array3[0][0]));
        final WvCaptureButton wvCaptureButton2 = new WvCaptureButton(super.wvDispatcher, array3[2], this);
        final WvImageButton wvImageButton3 = new WvImageButton(super.wvDispatcher, array3[3], false);
        final WvImageButton wvImageButton4 = new WvImageButton(super.wvDispatcher, array3[4], false);
        final WvBackLightButton wvBackLightButton = new WvBackLightButton(super.wvDispatcher, array3[5]);
        this.wvLiveCanvas = new WvLiveCanvas(super.wvDispatcher, this, min, n);
        final WvTimeIndicator wvTimeIndicator2 = new WvTimeIndicator(super.wvDispatcher);
        this.wvPanoramaButton = new WvPanoramaButton();
        final WvScrollbar wvScrollbar = new WvScrollbar(super.wvDispatcher, 0, 0);
        final WvScrollbar wvScrollbar2 = new WvScrollbar(super.wvDispatcher, 1, 1);
        final WvScrollbar wvScrollbar3 = new WvScrollbar(super.wvDispatcher, 1, 2);
        final WvToolBarPanel wvToolBarPanel2 = new WvToolBarPanel(super.wvDispatcher, min);
        this.wvPanoramaCanvas = new WvPanoramaCanvas(super.wvDispatcher);
        super.wvDispatcher.addWvEventListener(this.wvLiveCanvas);
        super.wvDispatcher.addWvEventListener(wvTimeIndicator2);
        super.wvDispatcher.addWvEventListener(wvControlButton2);
        super.wvDispatcher.addWvEventListener(wvBackLightButton);
        super.wvDispatcher.addWvEventListener(this.wvPanoramaButton);
        super.wvDispatcher.addWvEventListener(wvImageButton3);
        super.wvDispatcher.addWvEventListener(wvImageButton4);
        super.wvDispatcher.addWvEventListener(wvScrollbar);
        super.wvDispatcher.addWvEventListener(wvScrollbar2);
        super.wvDispatcher.addWvEventListener(wvScrollbar3);
        super.wvDispatcher.addWvEventListener(wvCaptureButton2);
        super.wvDispatcher.addWvEventListener(this.wvPanoramaCanvas);
        super.wvDispatcher.addWvEventListener(wvToolBarPanel2);
        final GridBagLayout layout3 = new GridBagLayout();
        final GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
        final Panel panel = new Panel();
        panel.setLayout(layout3);
        gridBagConstraints4.insets = new Insets(7, 7, 0, 0);
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.gridy = 0;
        gridBagConstraints4.gridwidth = 1;
        gridBagConstraints4.gridheight = 1;
        gridBagConstraints4.weightx = 0.0;
        gridBagConstraints4.fill = 0;
        gridBagConstraints4.anchor = 17;
        final Label label = new Label(super.wvDispatcher.isEnglish() ? "Camera :" : "\u30ab\u30e1\u30e9 :");
        layout3.setConstraints(label, gridBagConstraints4);
        panel.add(label);
        gridBagConstraints4.gridx = 1;
        gridBagConstraints4.weightx = 1.0;
        gridBagConstraints4.fill = 2;
        gridBagConstraints4.anchor = 10;
        layout3.setConstraints(wvCameraChoice, gridBagConstraints4);
        panel.add(wvCameraChoice);
        final GridBagLayout layout4 = new GridBagLayout();
        final GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
        final Panel panel2 = new Panel();
        panel2.setLayout(layout4);
        gridBagConstraints5.insets = new Insets(0, 7, 0, 0);
        gridBagConstraints5.gridx = 0;
        gridBagConstraints5.gridy = 0;
        gridBagConstraints5.gridwidth = 1;
        gridBagConstraints5.gridheight = 1;
        gridBagConstraints5.weightx = 0.0;
        gridBagConstraints5.weighty = 0.0;
        gridBagConstraints5.fill = 0;
        gridBagConstraints5.anchor = 18;
        layout4.setConstraints(wvImageButton3, gridBagConstraints5);
        panel2.add(wvImageButton3);
        gridBagConstraints5.gridx = 0;
        gridBagConstraints5.gridy = 1;
        gridBagConstraints5.anchor = 16;
        layout4.setConstraints(wvImageButton4, gridBagConstraints5);
        panel2.add(wvImageButton4);
        gridBagConstraints5.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints5.gridx = 1;
        gridBagConstraints5.gridy = 0;
        gridBagConstraints5.gridwidth = 1;
        gridBagConstraints5.gridheight = 2;
        gridBagConstraints5.weightx = 0.0;
        gridBagConstraints5.weighty = 1.0;
        gridBagConstraints5.fill = 3;
        gridBagConstraints5.anchor = 10;
        layout4.setConstraints(wvScrollbar3, gridBagConstraints5);
        panel2.add(wvScrollbar3);
        gridBagConstraints5.insets = new Insets(7, 0, 0, 7);
        gridBagConstraints5.gridx = 0;
        gridBagConstraints5.gridy = 2;
        gridBagConstraints5.gridwidth = 1;
        gridBagConstraints5.gridheight = 1;
        gridBagConstraints5.weightx = 0.0;
        gridBagConstraints5.weighty = 0.0;
        gridBagConstraints5.fill = 0;
        gridBagConstraints5.anchor = 13;
        layout4.setConstraints(wvBackLightButton, gridBagConstraints5);
        panel2.add(wvBackLightButton);
        if (b) {
            gridBagConstraints5.insets = new Insets(7, 0, 0, 0);
            gridBagConstraints5.gridx = 1;
            gridBagConstraints5.gridy = 2;
            gridBagConstraints5.anchor = 17;
            layout4.setConstraints(this.wvPanoramaButton, gridBagConstraints5);
            if (!"off".equalsIgnoreCase(this.getParameter("show_panobutton")) && !this.IS_V36) {
                panel2.add(this.wvPanoramaButton);
            }
        }
        gridBagConstraints5.insets = new Insets(7, 0, 0, 0);
        gridBagConstraints5.gridx = 0;
        gridBagConstraints5.gridy = 3;
        gridBagConstraints5.gridwidth = 2;
        gridBagConstraints5.gridheight = 1;
        gridBagConstraints5.anchor = 10;
        layout4.setConstraints(wvTimeIndicator2, gridBagConstraints5);
        panel2.add(wvTimeIndicator2);
        gridBagConstraints5.insets = new Insets(7, 0, 0, 0);
        gridBagConstraints5.gridx = 0;
        gridBagConstraints5.gridy = 4;
        gridBagConstraints5.gridwidth = 2;
        gridBagConstraints5.gridheight = 2;
        layout4.setConstraints(wvControlButton2, gridBagConstraints5);
        panel2.add(wvControlButton2);
        final GridBagLayout layout5 = new GridBagLayout();
        final GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
        Point point2 = new Point(335, 0);
        final String parameter26 = this.getParameter("image_offset");
        if (image != null && parameter26 != null) {
            try {
                final StringTokenizer stringTokenizer3 = new StringTokenizer(parameter26, ", ");
                point2 = new Point(Integer.parseInt(stringTokenizer3.nextToken()), Integer.parseInt(stringTokenizer3.nextToken()));
            }
            catch (Exception ex10) {}
        }
        final WvImagePanel wvImagePanel2 = new WvImagePanel(image, point2);
        wvImagePanel2.setLayout(layout5);
        gridBagConstraints6.insets = new Insets(7, 7, 0, 0);
        gridBagConstraints6.gridx = 0;
        gridBagConstraints6.gridy = 0;
        gridBagConstraints6.gridwidth = 2;
        gridBagConstraints6.gridheight = 1;
        gridBagConstraints6.weightx = 0.0;
        gridBagConstraints6.weighty = 0.0;
        gridBagConstraints6.fill = 2;
        gridBagConstraints6.anchor = 17;
        layout5.setConstraints(panel, gridBagConstraints6);
        wvImagePanel2.add(panel);
        gridBagConstraints6.insets = new Insets(7, 7, 0, 0);
        gridBagConstraints6.gridx = 4;
        gridBagConstraints6.gridy = 0;
        gridBagConstraints6.gridwidth = 2;
        gridBagConstraints6.gridheight = 1;
        gridBagConstraints6.fill = 0;
        gridBagConstraints6.anchor = 10;
        if (image == null && !"off".equalsIgnoreCase(this.getParameter("show_logo"))) {
            layout5.setConstraints(wvImageButton2, gridBagConstraints6);
            wvImagePanel2.add(wvImageButton2);
        }
        gridBagConstraints6.insets = new Insets(7, 7, 0, 0);
        gridBagConstraints6.gridx = 0;
        gridBagConstraints6.gridy = 1;
        gridBagConstraints6.gridwidth = 2;
        gridBagConstraints6.gridheight = 1;
        gridBagConstraints6.fill = 0;
        gridBagConstraints6.anchor = 10;
        layout5.setConstraints(this.wvLiveCanvas, gridBagConstraints6);
        wvImagePanel2.add(this.wvLiveCanvas);
        gridBagConstraints6.insets = new Insets(0, 7, 0, 0);
        gridBagConstraints6.gridx = 0;
        gridBagConstraints6.gridy = 2;
        gridBagConstraints6.gridwidth = 2;
        gridBagConstraints6.gridheight = 1;
        gridBagConstraints6.fill = 2;
        gridBagConstraints6.anchor = 10;
        layout5.setConstraints(wvScrollbar, gridBagConstraints6);
        if (!this.IS_V36) {
            wvImagePanel2.add(wvScrollbar);
        }
        gridBagConstraints6.insets = new Insets(7, 0, 0, 0);
        gridBagConstraints6.gridx = 3;
        gridBagConstraints6.gridy = 1;
        gridBagConstraints6.gridwidth = 1;
        gridBagConstraints6.gridheight = 1;
        gridBagConstraints6.fill = 3;
        layout5.setConstraints(wvScrollbar2, gridBagConstraints6);
        if (!this.IS_V36) {
            wvImagePanel2.add(wvScrollbar2);
        }
        gridBagConstraints6.insets = new Insets(7, 7, 0, 0);
        gridBagConstraints6.gridx = 0;
        gridBagConstraints6.gridy = 4;
        gridBagConstraints6.gridwidth = 1;
        gridBagConstraints6.gridheight = 1;
        gridBagConstraints6.anchor = 17;
        gridBagConstraints6.fill = 0;
        layout5.setConstraints(wvCaptureButton2, gridBagConstraints6);
        if (!"off".equalsIgnoreCase(this.getParameter("show_capture"))) {
            wvImagePanel2.add(wvCaptureButton2);
        }
        gridBagConstraints6.gridx = 1;
        gridBagConstraints6.gridy = 4;
        gridBagConstraints6.gridwidth = 3;
        gridBagConstraints6.gridheight = 1;
        gridBagConstraints6.anchor = 10;
        gridBagConstraints6.fill = 2;
        layout5.setConstraints(this.wvCardLayoutPanel, gridBagConstraints6);
        wvImagePanel2.add(this.wvCardLayoutPanel);
        gridBagConstraints6.gridx = 4;
        gridBagConstraints6.gridy = 1;
        gridBagConstraints6.gridwidth = 1;
        gridBagConstraints6.gridheight = 5;
        gridBagConstraints6.fill = 3;
        layout5.setConstraints(panel2, gridBagConstraints6);
        wvImagePanel2.add(panel2);
        final GridBagLayout layout6 = new GridBagLayout();
        final GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
        this.setLayout(layout6);
        gridBagConstraints7.insets = new Insets(7, 0, 0, 0);
        if ("on".equalsIgnoreCase(this.getParameter("show_toolbar"))) {
            gridBagConstraints7.gridx = 0;
            gridBagConstraints7.gridy = 0;
            gridBagConstraints7.gridwidth = 1;
            gridBagConstraints7.gridheight = 1;
            gridBagConstraints7.fill = 0;
            gridBagConstraints7.anchor = 10;
            layout6.setConstraints(wvToolBarPanel2, gridBagConstraints7);
            this.add(wvToolBarPanel2);
            gridBagConstraints7.insets = new Insets(0, 0, 0, 0);
        }
        gridBagConstraints7.gridx = 0;
        gridBagConstraints7.gridy = 1;
        gridBagConstraints7.fill = 0;
        gridBagConstraints7.anchor = 10;
        layout6.setConstraints(wvImagePanel2, gridBagConstraints7);
        this.add(wvImagePanel2);
        if (b) {
            (this.wvPanoramaFrame = new WvPanoramaFrame()).add("West", this.wvPanoramaCanvas);
            return;
        }
        gridBagConstraints7.insets = new Insets(7, 7, 0, 0);
        gridBagConstraints7.gridx = 0;
        gridBagConstraints7.gridy = 2;
        gridBagConstraints7.gridwidth = 1;
        gridBagConstraints7.gridheight = 1;
        gridBagConstraints7.weightx = 1.0;
        gridBagConstraints7.weighty = 1.0;
        gridBagConstraints7.fill = 0;
        gridBagConstraints7.anchor = 10;
        layout6.setConstraints(this.wvPanoramaCanvas, gridBagConstraints7);
        this.add(this.wvPanoramaCanvas);
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (event.target instanceof WvPanoramaButton && this.wvPanoramaFrame != null) {
            this.wvPanoramaFrame.pack();
            this.wvPanoramaFrame.show();
            this.wvPanoramaFrame.toFront();
            this.wvPanoramaFrame.repaint();
        }
        return false;
    }
    
    public void postStop() {
        if (this.wvPanoramaFrame != null) {
            this.wvPanoramaFrame.hide();
            this.wvPanoramaFrame.dispose();
        }
    }
    
    public void setHostname(final String hostnameString) {
        super.wvDispatcher.putObject("hostname", hostnameString);
        this.wvLiveCanvas.setHostnameString(hostnameString);
    }
    
    public void setComment(final String commentString) {
        super.wvDispatcher.putObject("comment", commentString);
        this.wvLiveCanvas.setCommentString(commentString);
    }
    
    public boolean handleEvent(final Event event) {
        boolean b = false;
        if (!(event.target instanceof WvControlButton)) {
            switch (event.id) {
                case 501:
                case 601:
                case 602:
                case 603:
                case 604:
                case 605:
                case 1001: {
                    b = true;
                    break;
                }
            }
        }
        if (event.target == this.wvPanoramaButton) {
            b = false;
        }
        if (b && this.wvCardLayoutPanel != null) {
            this.wvCardLayoutPanel.userTriedToControl(event);
        }
        return super.handleEvent(event);
    }
}
