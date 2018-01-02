import java.awt.Point;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import java.awt.Component;
import javax.swing.JOptionPane;
import java.awt.Polygon;
import java.io.File;

// 
// Decompiled by Procyon v0.5.30
// 

public class DownloadData
{
    static File savedDirectory;
    
    static void downloadData(final imgViewer imgViewer, final boolean b, final boolean b2) {
        final TOC[] mosaicCells = imgViewer.md.getMosaicCells();
        PolygonIntersectTester polygonIntersectTester = null;
        final Sensor currentSensor = imgViewer.sensorMenu.getCurrentSensor();
        final int numCellsAtResolution = currentSensor.getNumCellsAtResolution(imgViewer.md.pixelSize);
        int sceneCount = 0;
        if (b2) {
            sceneCount = currentSensor.sceneList.getSceneCount();
        }
        else if (numCellsAtResolution == 0) {
            if (imgViewer.md.getCurrentScene() != null) {
                sceneCount = 1;
            }
        }
        else if (currentSensor.isFullMosaic) {
            if (b) {
                final Point upperLeftCorner = imgViewer.imgArea.getUpperLeftCorner();
                if (upperLeftCorner == null) {
                    return;
                }
                final double actualPixelSize = imgViewer.md.actualPixelSize;
                final ProjectionTransformation projection = imgViewer.md.getProjection();
                final int mosaicWidth = imgViewer.md.getMosaicWidth();
                imgViewer.md.getMosaicHeight();
                final int mosaicSize = imgViewer.md.getMosaicSize();
                final int[] array = { 0, mosaicSize - mosaicWidth, mosaicSize - 1, mosaicWidth - 1 };
                final Point centerProjCoords = mosaicCells[imgViewer.md.getMosaicCenterIndex()].getCenterProjCoords(projection);
                final int[] array2 = new int[4];
                final int[] array3 = new int[4];
                for (int i = 0; i < array.length; ++i) {
                    final Point centerProjCoords2 = mosaicCells[array[i]].getCenterProjCoords(projection);
                    array2[i] = (centerProjCoords2.x + centerProjCoords.x) / 2;
                    array3[i] = (centerProjCoords2.y + centerProjCoords.y) / 2;
                    array2[i] = (int)Math.round((array2[i] - upperLeftCorner.x) / actualPixelSize);
                    array3[i] = (int)Math.round((upperLeftCorner.y - array3[i]) / actualPixelSize);
                }
                polygonIntersectTester = new PolygonIntersectTester(new Polygon(array2, array3, 4));
                final Point offsetToCenterDisplay = imgViewer.imgArea.getOffsetToCenterDisplay();
                polygonIntersectTester.translate(-offsetToCenterDisplay.x, -offsetToCenterDisplay.y);
            }
            for (int j = 0; j < mosaicCells.length; ++j) {
                final TOC toc = mosaicCells[j];
                if (toc.valid) {
                    for (int k = 0; k < toc.numImg; ++k) {
                        final Metadata metadata = toc.scenes[k];
                        if (metadata.visible && (!b || polygonIntersectTester.intersects(metadata.screenLocation))) {
                            ++sceneCount;
                        }
                    }
                }
            }
        }
        else if (b) {
            final TOC toc2 = mosaicCells[imgViewer.md.getMosaicCenterIndex()];
            if (toc2.valid) {
                for (int l = 0; l < toc2.numImg; ++l) {
                    if (toc2.scenes[l].visible) {
                        ++sceneCount;
                    }
                }
            }
        }
        else {
            for (int n = 0; n < mosaicCells.length; ++n) {
                final TOC toc3 = mosaicCells[n];
                if (toc3.valid && toc3.scenes[toc3.currentDateIndex].visible) {
                    ++sceneCount;
                }
            }
        }
        if (sceneCount == 0) {
            JOptionPane.showMessageDialog(imgViewer.getDialogContainer(), "No scenes are available for download", "No scenes available", 1);
            return;
        }
        File selectedFile = null;
        final String string = UIManager.getString("FileChooser.setFileNameLabelText");
        UIManager.put("FileChooser.fileNameLabelText", "Directory Name:");
        final String string2 = UIManager.getString("FileChooser.saveInLabelText");
        UIManager.put("FileChooser.saveInLabelText", "Look In:");
        try {
            final JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(1);
            fileChooser.setDialogTitle("Select destination directory");
            if (DownloadData.savedDirectory != null && DownloadData.savedDirectory.exists()) {
                fileChooser.setCurrentDirectory(DownloadData.savedDirectory);
            }
            if (fileChooser.showSaveDialog(imgViewer.getDialogContainer()) == 0) {
                selectedFile = fileChooser.getSelectedFile();
            }
        }
        catch (Exception ex) {}
        UIManager.put("FileChooser.fileNameLabelText", string);
        UIManager.put("FileChooser.saveInLabelText", string2);
        if (selectedFile != null) {
            if (!selectedFile.canWrite()) {
                JOptionPane.showMessageDialog(imgViewer.getDialogContainer(), "Error: Do not have permission to write to " + selectedFile, "Error downloading data", 0);
                return;
            }
            DownloadData.savedDirectory = selectedFile;
            final Metadata[] array4 = new Metadata[sceneCount];
            if (b2) {
                final SceneList sceneList = currentSensor.sceneList;
                for (int n2 = 0; n2 < sceneCount; ++n2) {
                    array4[n2] = sceneList.getSceneAt(n2);
                }
            }
            else if (numCellsAtResolution == 0) {
                array4[0] = imgViewer.md.getCurrentScene();
            }
            else if (currentSensor.isFullMosaic) {
                int n3 = 0;
                for (int n4 = 0; n4 < mosaicCells.length; ++n4) {
                    final TOC toc4 = mosaicCells[n4];
                    if (toc4.valid) {
                        for (int n5 = 0; n5 < toc4.numImg; ++n5) {
                            final Metadata metadata2 = toc4.scenes[n5];
                            if (metadata2.visible && (!b || polygonIntersectTester.intersects(metadata2.screenLocation))) {
                                array4[n3] = new Metadata(metadata2);
                                ++n3;
                            }
                        }
                    }
                }
            }
            else {
                int n6 = 0;
                if (b) {
                    final TOC toc5 = mosaicCells[imgViewer.md.getMosaicCenterIndex()];
                    if (toc5.valid) {
                        for (int n7 = 0; n7 < toc5.numImg; ++n7) {
                            final Metadata metadata3 = toc5.scenes[n7];
                            if (metadata3.visible) {
                                array4[n6] = new Metadata(metadata3);
                                ++n6;
                            }
                        }
                    }
                }
                else {
                    for (int n8 = 0; n8 < mosaicCells.length; ++n8) {
                        final TOC toc6 = mosaicCells[n8];
                        if (toc6.valid) {
                            final Metadata metadata4 = toc6.scenes[toc6.currentDateIndex];
                            if (metadata4.visible) {
                                array4[n6] = new Metadata(metadata4);
                                ++n6;
                            }
                        }
                    }
                }
            }
            new FileDownloader(imgViewer, selectedFile, array4).startDownload();
        }
    }
    
    static {
        DownloadData.savedDirectory = null;
    }
}
