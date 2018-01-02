// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer;

import java.io.IOException;
import java.util.ArrayList;
import java.io.RandomAccessFile;
import java.beans.PropertyChangeListener;
import com.itt.J2KViewer.georvm.NITFGeoUtils;
import java.lang.reflect.Method;
import java.beans.PropertyDescriptor;
import java.io.File;
import com.itt.J2KViewer.georvm.Projection;
import java.awt.Color;
import java.beans.PropertyVetoException;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import com.itt.J2KViewer.gui.MenuBar;
import javax.swing.JFrame;
import com.itt.J2KViewer.controller.ViewCentral;
import com.itt.J2KViewer.gui.ViewerMainPanel;
import com.itt.J2KViewer.util.Log;
import com.itt.J2KViewer.util.ViewerConst;

public class J2KViewerBean implements ViewerConst
{
    private Log log;
    private ViewerMainPanel viewerMainPanel;
    private ViewCentral viewCentral;
    static /* synthetic */ Class class$com$itt$J2KViewer$J2KViewerBean;
    
    public J2KViewerBean(final JFrame up) {
        this.log = new Log((J2KViewerBean.class$com$itt$J2KViewer$J2KViewerBean == null) ? (J2KViewerBean.class$com$itt$J2KViewer$J2KViewerBean = class$("com.itt.J2KViewer.J2KViewerBean")) : J2KViewerBean.class$com$itt$J2KViewer$J2KViewerBean);
        this.viewerMainPanel = null;
        this.viewCentral = null;
        this.setUp(up);
        this.log.debug(" IASViewerBean.J2KViewerBean(JFrame)");
    }
    
    public MenuBar getMenuBar() {
        return new MenuBar(this.viewCentral);
    }
    
    public void addImageMouseListener(final MouseListener mouseListener) {
        try {
            this.viewCentral.getMainImagePanel().getImageDisplayPanel().addMouseListener(mouseListener);
        }
        catch (RuntimeException ex) {
            this.log.error("addImageMouseListener()- FAILED to retrieve ImageDisplayPanel.", ex);
        }
    }
    
    public void addImageMouseMotionListener(final MouseMotionListener mouseMotionListener) {
        try {
            this.viewCentral.getMainImagePanel().getImageDisplayPanel().addMouseMotionListener(mouseMotionListener);
        }
        catch (RuntimeException ex) {
            this.log.error("addImageMouseMotionListener() - FAILED to retrieve ImageDisplayPanel.", ex);
        }
    }
    
    public void removeImageMouseListener(final MouseListener mouseListener) {
        try {
            this.viewCentral.getMainImagePanel().getImageDisplayPanel().removeMouseListener(mouseListener);
        }
        catch (RuntimeException ex) {
            this.log.error("removeImageMouseListener() - FAILED to retrieve ImageDisplayPanel.", ex);
        }
    }
    
    public void removeImageMouseMotionListener(final MouseMotionListener mouseMotionListener) {
        try {
            this.viewCentral.getMainImagePanel().getImageDisplayPanel().removeMouseMotionListener(mouseMotionListener);
        }
        catch (RuntimeException ex) {
            this.log.error("removeImageMouseMotionListener() - FAILED to retrieve ImageDisplayPanel.", ex);
        }
    }
    
    public MouseListener[] getImageMouseListeners() {
        MouseListener[] mouseListeners;
        try {
            mouseListeners = this.viewCentral.getMainImagePanel().getImageDisplayPanel().getMouseListeners();
        }
        catch (RuntimeException ex) {
            this.log.error("getImageMouseListeners() - FAILED to retrieve ImageDisplayPanel.", ex);
            return null;
        }
        return mouseListeners;
    }
    
    public MouseMotionListener[] getImageMouseMotionListeners() {
        final MouseMotionListener[] array = null;
        try {
            this.viewCentral.getMainImagePanel().getImageDisplayPanel().getMouseMotionListeners();
        }
        catch (RuntimeException ex) {
            this.log.error("getImageMouseMotionListeners() - FAILED to retrieve ImageDisplayPanel.", ex);
            return null;
        }
        return array;
    }
    
    private void setUp(final JFrame j2KViewerFrame) {
        (this.viewCentral = new ViewCentral()).setJ2KViewerFrame(j2KViewerFrame);
        this.log.debug(" IASViewerBean.setUp(JFrame)");
    }
    
    public void setViewerFrame(final JFrame j2KViewerFrame) {
        this.viewCentral.setJ2KViewerFrame(j2KViewerFrame);
    }
    
    public ViewerMainPanel getMainPanel() {
        if (this.viewerMainPanel == null) {
            this.viewerMainPanel = new ViewerMainPanel(this.viewCentral);
            this.log.debug(" IASViewerBean.getMainPanel()");
        }
        return this.viewerMainPanel;
    }
    
    public BufferedImage getBufferedImage() {
        return this.viewCentral.getMainImagePanel().getBufferedImage();
    }
    
    public void zoom(final int n) {
        if (this.viewCentral.getPropertyManager().isAllowZoom()) {
            try {
                this.viewCentral.getPropertyManager().setDiscardedZoomLevels(this.viewCentral.getPropertyManager().getDiscardedZoomLevels() + n);
                this.viewCentral.viewChanged();
                this.log.debug(" IASViewerBean.zoom(" + n + ")");
            }
            catch (Exception ex) {
                this.log.error(ex);
            }
        }
        else {
            this.log.info("isAllowZoom = false");
        }
    }
    
    public void pan(final float n, final float n2) throws PropertyVetoException {
        final Point displayWindowCenter = this.viewCentral.getDimensionManager().getDisplayWindowCenter();
        final Rectangle displayWindow = this.viewCentral.getDimensionManager().getDisplayWindow();
        if (this.viewCentral.getPropertyManager().isAllowPan()) {
            if (displayWindowCenter != null) {
                final Point point = displayWindowCenter;
                point.x += (int)(displayWindow.width * n / 100.0);
                final Point point2 = displayWindowCenter;
                point2.y += (int)(displayWindow.height * n2 / 100.0);
                this.viewCentral.getDimensionManager().setDisplayWindowCenter(displayWindowCenter);
                this.viewCentral.viewChanged();
            }
        }
        else {
            this.log.info("isAllowPan set to false.");
        }
    }
    
    public void setImageURL(final String imageURL) throws PropertyVetoException {
        this.viewCentral.getPropertyManager().setImageURL(imageURL);
        this.log.debug(" IASViewerBean.setImageURL(" + imageURL + ")");
    }
    
    public String getImageURL() {
        final String imageURL = this.viewCentral.getPropertyManager().getImageURL();
        this.log.debug(" IASViewerBean.getImageURL() gives" + imageURL);
        return imageURL;
    }
    
    public void openImage() {
        final String imageURL = this.viewCentral.getPropertyManager().getImageURL();
        if (imageURL != null) {
            try {
                this.viewCentral.openImage(imageURL, true);
                this.log.debug(" IASViewerBean.openImage() SUCCESSFUL !!");
            }
            catch (Exception ex) {
                this.log.debug(" openImage() FAILED !! ##########");
                this.log.info(ex.toString());
                this.log.error("Error opening image", ex);
            }
        }
    }
    
    public void closeImage() {
        this.viewCentral.closeImage();
        this.log.debug(" IASViewerBean.closeImage()");
    }
    
    public void applyAutoDRA() {
        this.viewCentral.applyAutoDRA();
        this.log.debug(" IASViewerBean.applyAutoDRA()");
    }
    
    public void setChipperOutputDirectory(final String chipperOutputDirectory) throws PropertyVetoException {
        this.viewCentral.getPropertyManager().setChipperOutputDirectory(chipperOutputDirectory);
        this.log.debug("J2KViewerBean.setChipperOutputDirectory(" + chipperOutputDirectory + ")");
    }
    
    public String getChipperOutputDirectory() {
        final String chipperOutputDirectory = this.viewCentral.getPropertyManager().getChipperOutputDirectory();
        this.log.debug(" J2KViewerBean.getChipperOutputDirectory() -> " + chipperOutputDirectory);
        return chipperOutputDirectory;
    }
    
    public void setChippingServiceURL(final String chippingServiceURL) throws PropertyVetoException {
        this.viewCentral.getPropertyManager().setChippingServiceURL(chippingServiceURL);
        this.log.debug(" IASViewerBean.setChippingServiceURL(" + chippingServiceURL + ")");
    }
    
    public void setChippingServiceType(final String chippingServiceType) throws PropertyVetoException {
        this.viewCentral.getPropertyManager().setChippingServiceType(chippingServiceType);
        this.log.debug(" IASViewerBean.setChippingServiceType(" + chippingServiceType + ")");
    }
    
    public void setChippingConversionOptions(final String chippingConversionOptions) {
        this.viewCentral.getPropertyManager().setChippingConversionOptions(chippingConversionOptions);
        this.log.debug(" IASViewerBean.setChipperConversionOptions(" + chippingConversionOptions + ")");
    }
    
    public void setChippingTargetResolutionOptions(final String chippingTargetResolutionOptions) {
        this.viewCentral.getPropertyManager().setChippingTargetResolutionOptions(chippingTargetResolutionOptions);
        this.log.debug(" IASViewerBean.setChipperTargetResolutionOptions(" + chippingTargetResolutionOptions + ")");
    }
    
    public void setEnableChipping(final String s) {
        boolean allowChipping = true;
        if ("CHIPPING_ON".equals(s.toUpperCase())) {
            allowChipping = true;
        }
        else if ("CHIPPING_OFF".equals(s.toUpperCase())) {
            allowChipping = false;
        }
        this.viewCentral.getPropertyManager().setAllowChipping(allowChipping);
        this.log.debug(" IASViewerBean.setEnableChipping() -> " + s + "(" + allowChipping + ")");
    }
    
    public void setShowChipWarning(final boolean showChipWarning) {
        this.viewCentral.getPropertyManager().setShowChipWarning(showChipWarning);
    }
    
    public void setLoginId(final String loginId) throws PropertyVetoException {
        this.viewCentral.getPropertyManager().setLoginId(loginId);
        this.log.debug(" IASViewerBean.setLoginId(" + loginId + ")");
    }
    
    public String getLoginId() {
        final String loginId = this.viewCentral.getPropertyManager().getLoginId();
        this.log.debug(" IASViewerBean.getLoginId() -> " + loginId);
        return loginId;
    }
    
    public void setLoginPwd(final String loginPwd) throws PropertyVetoException {
        this.viewCentral.getPropertyManager().setLoginPwd(loginPwd);
        this.log.debug(" IASViewerBean.setLoginPwd(" + loginPwd + ")");
    }
    
    public String getLoginPwd() {
        final String loginPwd = this.viewCentral.getPropertyManager().getLoginPwd();
        this.log.debug(" IASViewerBean.getLoginPwd() -> " + loginPwd);
        return loginPwd;
    }
    
    public int getMaxQualityLayers() {
        final int totalQualityLayers = this.viewCentral.getPropertyManager().getTotalQualityLayers();
        this.log.debug(" IASViewerBean.getMaxQualityLayers() -> " + totalQualityLayers);
        return totalQualityLayers;
    }
    
    public int getMaxDiscardedZoomLevels() {
        this.log.debug(" IASViewerBean.getMaxDiscardedZoomLevels() -> " + this.viewCentral.getPropertyManager().getMaxDiscardedZoomLevels());
        return this.viewCentral.getPropertyManager().getMaxDiscardedZoomLevels();
    }
    
    public void setDiscardedZoomLevels(final int discardedZoomLevels) throws PropertyVetoException {
        this.viewCentral.getPropertyManager().setDiscardedZoomLevels(discardedZoomLevels);
        this.log.debug(" IASViewerBean.setDiscardedZoomLevels(" + discardedZoomLevels + ")");
    }
    
    public int getDiscardedZoomLevels() {
        this.log.debug(" IASViewerBean.getDiscardedZoomLevels() -> " + this.viewCentral.getPropertyManager().getDiscardedZoomLevels());
        return this.viewCentral.getPropertyManager().getDiscardedZoomLevels();
    }
    
    public void setQualityLayers(final int qualityLayers) throws PropertyVetoException {
        if (this.isAllowChangeQuality()) {
            this.viewCentral.getPropertyManager().setQualityLayers(qualityLayers);
            this.viewCentral.viewChanged();
            this.log.debug(" IASViewerBean.setQualityLayers(" + qualityLayers + ")");
        }
        else {
            this.log.info("isAllowChangeQuality set to false.");
        }
    }
    
    public int getQualityLayers() {
        final int qualityLayers = this.viewCentral.getPropertyManager().getQualityLayers();
        this.log.debug(" IASViewerBean.getQualityLayers() -> " + qualityLayers);
        return qualityLayers;
    }
    
    public void setQualityLayersPercentToDownload(final int qualityLayersPercentToDownload) {
        if (this.isAllowChangeQuality()) {
            this.viewCentral.getPropertyManager().setQualityLayersPercentToDownload(qualityLayersPercentToDownload);
        }
        else {
            this.log.info("isAllowChangeQuality set to false.");
        }
    }
    
    public void setOverviewQualityLayersPercentToDownload(final int overviewQualityLayersPercentToDownload) {
        if (this.isAllowChangeQuality()) {
            this.viewCentral.getPropertyManager().setOverviewQualityLayersPercentToDownload(overviewQualityLayersPercentToDownload);
        }
        else {
            this.log.info("isAllowChangeQuality set to false.");
        }
    }
    
    public void setDefaultDRAType(final int defaultDRAType) throws PropertyVetoException {
        this.viewCentral.getPropertyManager().setDefaultDRAType(defaultDRAType);
    }
    
    public int getDefaultDRAType() {
        return this.viewCentral.getPropertyManager().getDefaultDRAType();
    }
    
    public void setDefaultPercentStretch(final double defaultDRAPercent) {
        this.viewCentral.getPropertyManager().setDefaultDRAPercent(defaultDRAPercent);
    }
    
    public double getDefaultPercentStretch() {
        return this.viewCentral.getPropertyManager().getDefaultDRAPercent();
    }
    
    public Rectangle getTotalDimensions() {
        final Rectangle totalDimensions = this.viewCentral.getPropertyManager().getTotalDimensions();
        if (totalDimensions != null && totalDimensions instanceof Rectangle) {
            this.log.debug(" IASViewerBean.getTotalDimensions() -> valid Rectangle");
        }
        else {
            this.log.debug(" IASViewerBean.getTotalDimensions() -> INVALID Rectangle");
        }
        return totalDimensions;
    }
    
    public Rectangle getCurrentDimensions() {
        final Rectangle currentDimensions = this.viewCentral.getPropertyManager().getCurrentDimensions();
        if (currentDimensions != null && currentDimensions instanceof Rectangle) {
            this.log.debug(" IASViewerBean.getCurrentDimensions() -> valid Rectangle");
        }
        else {
            this.log.debug(" IASViewerBean.getCurrentDimensions() -> INVALID Rectangle");
        }
        return currentDimensions;
    }
    
    public void setAllowZoom(final boolean allowZoom) throws PropertyVetoException {
        this.viewCentral.getPropertyManager().setAllowZoom(allowZoom);
        this.log.debug(" IASViewerBean.setAllowZoom(" + allowZoom + ")");
    }
    
    public void setUponLoadDoAutoDRA(final boolean uponLoadDoAutoDRA) throws PropertyVetoException {
        this.viewCentral.getPropertyManager().setUponLoadDoAutoDRA(uponLoadDoAutoDRA);
        this.log.debug(" IASViewerBean.setUponLoadDoAutoDRA(" + uponLoadDoAutoDRA + ")");
    }
    
    public boolean isAllowZoom() {
        final boolean allowZoom = this.viewCentral.getPropertyManager().isAllowZoom();
        this.log.debug(" IASViewerBean.isAllowZoom() -> " + allowZoom);
        return allowZoom;
    }
    
    public boolean isUponLoadDoAutoDRA() {
        final boolean uponLoadDoAutoDRA = this.viewCentral.getPropertyManager().isUponLoadDoAutoDRA();
        this.log.debug(" IASViewerBean.isUponLoadDoAutoDRA() -> " + uponLoadDoAutoDRA);
        return uponLoadDoAutoDRA;
    }
    
    public void setEnableIgnoreValDRA(final boolean useIgnoreValueDRA) throws PropertyVetoException {
        this.viewCentral.getPropertyManager().setUseIgnoreValueDRA(useIgnoreValueDRA);
    }
    
    public boolean isEnableIgnoreValDRA() {
        return this.viewCentral.getPropertyManager().isUseIgnoreValueDRA();
    }
    
    public void setIgnoreValueDRA(final int ignoreValueDRA) throws PropertyVetoException {
        this.viewCentral.getPropertyManager().setIgnoreValueDRA(ignoreValueDRA);
    }
    
    public int getIgnoreValueDRA() {
        return this.viewCentral.getPropertyManager().getIgnoreValueDRA();
    }
    
    public void setAllowPan(final boolean allowPan) throws PropertyVetoException {
        this.viewCentral.getPropertyManager().setAllowPan(allowPan);
        this.log.debug(" IASViewerBean.setAllowPan(" + allowPan + ")");
    }
    
    public boolean isAllowPan() {
        final boolean allowPan = this.viewCentral.getPropertyManager().isAllowPan();
        this.log.debug(" IASViewerBean.isAllowPan() -> " + allowPan);
        return allowPan;
    }
    
    public boolean isOpen() {
        final boolean open = this.viewCentral.getPropertyManager().isOpen();
        this.log.debug(" IASViewerBean.isOpen() -> " + open);
        return open;
    }
    
    public boolean isShowContextMenu() {
        final boolean showContextMenu = this.viewCentral.getPropertyManager().isShowContextMenu();
        this.log.debug(" IASViewerBean.isShowStatusPanel() -> " + showContextMenu);
        return showContextMenu;
    }
    
    public void setShowToolBar(final boolean showToolBar) throws PropertyVetoException {
        this.viewCentral.getPropertyManager().setShowToolBar(showToolBar);
        this.log.debug(" IASViewerBean.setShowToolBar(" + showToolBar + ")");
    }
    
    public boolean isShowToolBar() {
        final boolean showToolBar = this.viewCentral.getPropertyManager().isShowToolBar();
        this.log.debug(" IASViewerBean.isShowToolBar() -> " + showToolBar);
        return showToolBar;
    }
    
    public void setShowScrollbars(final boolean showScrollbars) throws PropertyVetoException {
        this.viewCentral.getPropertyManager().setShowScrollbars(showScrollbars);
        this.log.debug(" IASViewerBean.setShowScrollbars(" + showScrollbars + ")");
    }
    
    public boolean isShowScrollbars() {
        final boolean showScrollbars = this.viewCentral.getPropertyManager().isShowScrollbars();
        this.log.debug(" IASViewerBean.isShowToolBar() -> " + showScrollbars);
        return showScrollbars;
    }
    
    public void setAllowChangeQuality(final boolean allowChangeQuality) throws PropertyVetoException {
        this.viewCentral.getPropertyManager().setAllowChangeQuality(allowChangeQuality);
        this.log.debug(" IASViewerBean.setAllowChangeQuality(" + allowChangeQuality + ")");
    }
    
    public boolean isAllowChangeQuality() {
        final boolean allowChangeQuality = this.viewCentral.getPropertyManager().isAllowChangeQuality();
        this.log.debug(" IASViewerBean.isAllowChangeQuality() -> " + allowChangeQuality);
        return allowChangeQuality;
    }
    
    public long getBytesTransferred() {
        final long bytesTransferred = this.viewCentral.getPropertyManager().getBytesTransferred();
        this.log.debug(" IASViewerBean.getBytesTransferred() -> " + bytesTransferred);
        return bytesTransferred;
    }
    
    public long getTotalBytes() {
        final long totalBytes = this.viewCentral.getPropertyManager().getTotalBytes();
        this.log.debug(" IASViewerBean.getTotalBytes() -> " + totalBytes);
        return totalBytes;
    }
    
    public void setMaxZoomOutLevel(final int maxZoomOutLevel) throws PropertyVetoException {
        this.viewCentral.getPropertyManager().setMaxZoomOutLevel(maxZoomOutLevel);
        this.log.debug(" IASViewerBean.setMaxZoomOutLevel(" + maxZoomOutLevel + ")");
    }
    
    public int getMaxZoomOutLevel() {
        final int maxZoomOutLevel = this.viewCentral.getPropertyManager().getMaxZoomOutLevel();
        this.log.debug(" IASViewerBean.getMaxZoomOutLevel() -> " + maxZoomOutLevel);
        return maxZoomOutLevel;
    }
    
    public void setTransformationMode(final String transformationMode) throws PropertyVetoException {
        boolean b = true;
        if (transformationMode.equalsIgnoreCase("lensing")) {
            b = false;
            this.log.warn("PDT lensing is not available for this application.");
        }
        if (b) {
            this.viewCentral.setTransformationMode(transformationMode);
        }
        this.log.debug(" IASViewerBean.setTransformationMode(" + transformationMode + ")");
    }
    
    public void setLensColor(final Color lensColor) {
        try {
            this.viewCentral.getPropertyManager().setLensColor(lensColor);
        }
        catch (PropertyVetoException ex) {
            this.log.error("Error setting lens color.", ex);
        }
    }
    
    public void setLensType(final int lensType) {
        try {
            this.viewCentral.getPropertyManager().setLensType(lensType);
        }
        catch (PropertyVetoException ex) {
            this.log.error("Error setting lens type.", ex);
        }
    }
    
    public void setLensRadius(final int lensRadius) {
        this.viewCentral.getPropertyManager().setLensRadius(lensRadius);
    }
    
    public void setLensMagnification(final int lensMagnification) {
        this.viewCentral.getPropertyManager().setLensMagnification(lensMagnification);
    }
    
    public void setGotoColor(final Color gotoColor) {
        try {
            this.viewCentral.getPropertyManager().setGotoColor(gotoColor);
        }
        catch (PropertyVetoException ex) {
            this.log.error("Error setting goto color.", ex);
        }
    }
    
    public void setOverviewColor(final Color overviewColor) {
        try {
            this.viewCentral.getPropertyManager().setOverviewColor(overviewColor);
        }
        catch (PropertyVetoException ex) {
            this.log.error("Error setting overview color.", ex);
        }
    }
    
    public void setShowGeoLocationPanel(final boolean isShowGeoLocationPanel) {
        this.viewCentral.getPropertyManager().setIsShowGeoLocationPanel(isShowGeoLocationPanel);
    }
    
    public void setShowStatusPanel(final boolean isShowStatusPanel) {
        this.viewCentral.getPropertyManager().setIsShowStatusPanel(isShowStatusPanel);
    }
    
    public void setShowSplitPane(final boolean showSplitPane) {
        this.viewCentral.getPropertyManager().setShowSplitPane(showSplitPane);
    }
    
    public boolean isShowSplitPane() {
        return this.viewCentral.getPropertyManager().isShowSplitPane();
    }
    
    public void setShowOverviewImage(final boolean showOverviewImage) {
        this.viewCentral.getPropertyManager().setShowOverviewImage(showOverviewImage);
    }
    
    public boolean isShowOverviewImage() {
        return this.viewCentral.getPropertyManager().isShowOverviewImage();
    }
    
    public void setShowDynamicRangePanel(final boolean isShowDynamicRangePanel) {
        this.viewCentral.getPropertyManager().setIsShowDynamicRangePanel(isShowDynamicRangePanel);
    }
    
    public void setApplicationWidth(final int applicationWidth) {
        this.viewCentral.getPropertyManager().setApplicationWidth(applicationWidth);
    }
    
    public void setApplicationHeight(final int applicationHeight) {
        this.viewCentral.getPropertyManager().setApplicationHeight(applicationHeight);
    }
    
    public int[] getApplicationSize() {
        return this.viewCentral.getPropertyManager().getApplicationSize();
    }
    
    public void setDefaultProjection(final Projection proj) {
        try {
            this.viewCentral.getPropertyManager().setProj(proj);
            this.viewCentral.getPropertyManager().setIsDefaultProjSelected(true);
        }
        catch (PropertyVetoException ex) {
            this.log.error("Error setting projection", ex);
        }
    }
    
    public void setCacheDir(final String jpipCacheDirectory) {
        this.viewCentral.getPropertyManager().setJpipCacheDirectory(jpipCacheDirectory);
    }
    
    public void setCachingIsEnabled(final boolean cachingIsEnabled) {
        this.viewCentral.getPropertyManager().setCachingIsEnabled(cachingIsEnabled);
    }
    
    public void setCacheClearOnAppExit(final boolean cacheClearOnAppExit) {
        this.viewCentral.getPropertyManager().setCacheClearOnAppExit(cacheClearOnAppExit);
    }
    
    public void setSslEnabled(final boolean sslEnabled) {
        this.viewCentral.getPropertyManager().setSslEnabled(sslEnabled);
    }
    
    public void setNorthArrowImage(final String northArrowImage) {
        this.viewCentral.getPropertyManager().setNorthArrowImage(northArrowImage);
    }
    
    public String getNorthArrowImage() {
        return this.viewCentral.getPropertyManager().getNorthArrowImage();
    }
    
    public void showBandsDialog() throws Exception {
        try {
            this.viewCentral.doShowBands();
            this.log.debug(" IASViewerBean.doShowBands()");
        }
        catch (Exception ex) {
            this.log.debug(" IASViewerBean.doShowBands() ->  FAILED");
            throw ex;
        }
    }
    
    public void showHelpDialog() throws Exception {
        try {
            this.viewCentral.doShowHelp();
            this.log.debug(" IASViewerBean.doShowHelpDialog()");
        }
        catch (Exception ex) {
            this.log.debug(" IASViewerBean.doShowHelpDialog() ->  FAILED");
            throw ex;
        }
    }
    
    public void setShowContextMenu(final boolean showContextMenu) throws Exception {
        try {
            this.viewCentral.getPropertyManager().setShowContextMenu(showContextMenu);
            this.log.debug(" IASViewerBean.setShowContextMenu()");
        }
        catch (Exception ex) {
            this.log.debug(" IASViewerBean.setShowContextMenu() ->  FAILED");
            throw ex;
        }
    }
    
    public void showXmlPropsDialog() throws Exception {
        try {
            this.viewCentral.doShowXmlProps();
            this.log.debug(" IASViewerBean.showXmlPropsDialog()");
        }
        catch (Exception ex) {
            this.log.debug(" IASViewerBean.showXmlPropsDialog() -> FAILED");
            throw ex;
        }
    }
    
    public void setShowSecurityBanner(final boolean showSecurityBanner) {
        this.viewCentral.getPropertyManager().setShowSecurityBanner(showSecurityBanner);
        this.log.debug(" IASViewerBean.setShowSecurityBanner(" + showSecurityBanner + ")");
    }
    
    public boolean isShowSecurityBanner() {
        final boolean showSecurityBanner = this.viewCentral.getPropertyManager().isShowSecurityBanner();
        this.log.debug(" IASViewerBean.isShowSecurityBanner() -> " + showSecurityBanner);
        return showSecurityBanner;
    }
    
    public void setSecuritySegment(final String securitySegment) {
        this.viewCentral.getPropertyManager().setSecuritySegment(securitySegment);
        this.log.debug(" IASViewerBean.setSecuritySegment(" + securitySegment + ")");
    }
    
    public String getSecuritySegment() {
        final String securitySegment = this.viewCentral.getPropertyManager().getSecuritySegment();
        this.log.debug(" IASViewerBean.getSecuritySegment() -> " + securitySegment);
        return securitySegment;
    }
    
    public void setShowCopyrightBanner(final boolean showCopyrightBanner) {
        this.viewCentral.getPropertyManager().setShowCopyrightBanner(showCopyrightBanner);
        this.log.debug(" IASViewerBean.setShowCopyrightBanner(" + showCopyrightBanner + ")");
    }
    
    public boolean isShowCopyrightBanner() {
        final boolean showCopyrightBanner = this.viewCentral.getPropertyManager().isShowCopyrightBanner();
        this.log.debug(" IASViewerBean.isShowCopyrightBanner() -> " + showCopyrightBanner);
        return showCopyrightBanner;
    }
    
    public void showCodestreamPropsDialog() throws Exception {
        try {
            this.viewCentral.doShowCodeStreamProps();
            this.log.debug(" IASViewerBean.showCodestreamPropsDialog()");
        }
        catch (Exception ex) {
            this.log.debug(" IASViewerBean.showCodestreamPropsDialog() -> FAILED");
            throw ex;
        }
    }
    
    public boolean setPropFile(final String s, final String s2) {
        return this.viewCentral.getPropertyManager().setPropFileAndDir(s, s2);
    }
    
    public boolean propFileWasSet() {
        return this.viewCentral.getPropertyManager().getPropFilePreviouslySet();
    }
    
    public int getNumberOfComponents() {
        return this.viewCentral.getPropertyManager().getNumberOfComponents();
    }
    
    public int[] getRGBMap() {
        return this.viewCentral.getPropertyManager().getRGBMap();
    }
    
    public void setRGBMap(final int[] rgbMap) throws PropertyVetoException {
        this.viewCentral.getPropertyManager().setRGBMap(rgbMap);
        this.viewCentral.viewChanged();
    }
    
    public double getRotationAngle() {
        return this.viewCentral.getPropertyManager().getRotationAngle();
    }
    
    public void setRotationAngle(final double rotationAngle) {
        this.viewCentral.getPropertyManager().setRotationAngle(rotationAngle);
        this.viewCentral.viewChanged();
    }
    
    public void saveViewToJPEGFile(final File file) {
        this.viewerMainPanel.saveViewToJPEGFile(file);
    }
    
    public void saveViewToGeoTIFFFile(final File file) {
        this.viewerMainPanel.saveViewToGeoTIFFFile(file);
    }
    
    public void saveViewToFile() {
        this.viewCentral.saveViewToFile();
    }
    
    public void saveViewToGeoTIFFFile() {
        this.viewCentral.saveViewToGeoTIFFFile();
    }
    
    public void applyDRA(final int n) {
        this.viewCentral.getDRAManager().apply(n);
    }
    
    public void setGeographicDisplayFormat(final int geographicDisplayFormat) {
        this.viewCentral.getPropertyManager().setGeographicDisplayFormat(geographicDisplayFormat);
    }
    
    public int getGeographicDisplayFormat() {
        return this.viewCentral.getPropertyManager().getGeographicDisplayFormat();
    }
    
    public void initProperty(final PropertyDescriptor propertyDescriptor, String s) {
        if (s != null) {
            this.log.debug("Setting Parameter: " + propertyDescriptor.getName() + " = " + s);
            final Method writeMethod = propertyDescriptor.getWriteMethod();
            if (writeMethod != null) {
                final Class<?>[] parameterTypes = writeMethod.getParameterTypes();
                final Object[] array = { null };
                Label_0687: {
                    if (parameterTypes[0].isPrimitive()) {
                        if (parameterTypes[0].getName().equals("int")) {
                            try {
                                array[0] = new Integer(s);
                                this.log.debug("IASViewerBean.initProperty(" + s + ")");
                            }
                            catch (NumberFormatException ex) {
                                this.log.debug("INT IASViewerBean.initProperty(" + s + ")");
                                this.log.error(ex);
                                return;
                            }
                        }
                        if (parameterTypes[0].getName().equals("boolean")) {
                            if (s == null) {
                                s = "false";
                            }
                            array[0] = new Boolean(s);
                            this.log.debug("boolean IASViewerBean.initProperty(" + s + ")");
                        }
                        if (!parameterTypes[0].getName().equals("double")) {
                            break Label_0687;
                        }
                        try {
                            array[0] = new Double(s);
                            this.log.debug("IASViewerBean.initProperty(" + s + ")");
                            break Label_0687;
                        }
                        catch (NumberFormatException ex2) {
                            this.log.debug("INT IASViewerBean.initProperty(" + s + ")");
                            this.log.error(ex2);
                            return;
                        }
                    }
                    if (parameterTypes[0].getName().equals("com.itt.J2KViewer.georvm.Projection")) {
                        array[0] = new Projection(s);
                    }
                    if (parameterTypes[0].getName().equals("java.lang.String")) {
                        array[0] = s;
                        this.log.debug("String IASViewerBean.initProperty(" + s + ")");
                    }
                    if (parameterTypes[0].getName().equals("java.awt.Color")) {
                        final int[] array2 = { 0, 0, 0 };
                        String s2 = s.substring(s.indexOf("[") + 1, s.lastIndexOf("]")) + ",";
                        for (int i = 0; i < 3; ++i) {
                            final String substring = s2.substring(s2.indexOf("=") + 1, s2.indexOf(","));
                            s2 = s2.substring(s2.indexOf(",") + 1, s2.lastIndexOf(",") + 1);
                            array2[i] = new Integer(Integer.parseInt(substring));
                        }
                        array[0] = new Color(array2[0], array2[1], array2[2]);
                        this.log.debug("String IASViewerBean.initProperty(" + s + ")");
                    }
                    if (parameterTypes[0].getName().equals("java.lang.Boolean")) {
                        System.out.println();
                    }
                    try {
                        writeMethod.invoke(this, array);
                    }
                    catch (Exception ex3) {
                        this.log.debug("IASViewerBean.initProperty() setMethod.invoke() FAILED");
                    }
                }
            }
        }
    }
    
    public boolean isGeoTIFFAvailable() {
        final NITFGeoUtils nitfGeoUtils = this.viewCentral.getNITFGeoUtils();
        return nitfGeoUtils != null && nitfGeoUtils.isReady();
    }
    
    public void setShowNorthArrow(final boolean showNorthArrow) {
        try {
            this.viewCentral.getPropertyManager().setShowNorthArrow(showNorthArrow);
        }
        catch (PropertyVetoException ex) {
            this.log.error("Unable show/hide North Arrow");
            ex.printStackTrace();
        }
    }
    
    public void addPropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        this.viewCentral.eventController().addPropertyChangeListener(propertyChangeListener);
    }
    
    public void cancelStream() {
        this.viewCentral.getImageStream().cancelStream();
    }
    
    public void restartImageStream() {
        try {
            this.viewCentral.getMainImagePanel().restartImageStream();
        }
        catch (Exception ex) {
            System.out.println("Could not restart image stream.");
            System.out.println(ex.toString());
        }
    }
    
    public void copyCodestream(final RandomAccessFile randomAccessFile, final String s, final ArrayList list) throws IOException {
        if (this.isOpen()) {
            this.viewCentral.getImageStream().cancelStream();
            this.viewCentral.getImageStream().copyCodestream(randomAccessFile, s, list);
            this.viewCentral.viewChanged();
        }
    }
    
    public int getNumTilesInXDimension() {
        return this.viewCentral.getImageStream().getImageFileProperties().getNumTilesInXDimension();
    }
    
    public int getNumTilesInYDimension() {
        return this.viewCentral.getImageStream().getImageFileProperties().getNumTilesInYDimension();
    }
    
    public void setTransmissionLength(final int transmissionLength) {
        this.viewCentral.getPropertyManager().setTransmissionLength(transmissionLength);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
}
