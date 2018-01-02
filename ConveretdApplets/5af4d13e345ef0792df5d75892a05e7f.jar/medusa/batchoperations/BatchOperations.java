// 
// Decompiled by Procyon v0.5.30
// 

package medusa.batchoperations;

import medusa.display.EditableGraphPanel;
import medusa.display.FRspring;
import medusa.graph.Graph;
import medusa.DataFormatException;
import java.io.IOException;
import java.io.File;
import medusa.dataio.DataLoader;

public class BatchOperations
{
    DataLoader dl;
    
    public BatchOperations() {
        this.dl = new DataLoader(1, 1);
    }
    
    public void convertTabbedToMedusa(String clean) {
        clean = this.clean(clean);
        final String[] list = new File(clean + File.separator).list();
        for (int i = 0; i < list.length; ++i) {
            try {
                System.out.println(clean + File.separator + list[i]);
                final Graph loadSimplest = this.dl.loadSimplest(list[i]);
                System.out.println("saving " + clean + File.separator + list[i] + ".dat");
                this.dl.save(loadSimplest, clean + File.separator + list[i] + ".dat");
            }
            catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            catch (DataFormatException ex2) {
                System.out.println(ex2.getMessage());
            }
        }
        System.out.println("Converted as many files as possible in dir " + clean);
    }
    
    public void batchNormalize(final String s) {
    }
    
    public void layoutAll(String clean) {
        clean = this.clean(clean);
        final String[] list = new File(clean).list();
        final int n = 600;
        for (int i = 0; i < list.length; ++i) {
            if (list[i].endsWith(".dat")) {
                try {
                    System.out.println("loading: " + list[i]);
                    final Graph load = this.dl.load(clean + File.separator + list[i]);
                    new FRspring(load, n, n).iterateAll();
                    load.divideNodePosition(n);
                    System.out.println("saving " + clean + File.separator + list[i]);
                    this.dl.save(load, clean + File.separator + list[i]);
                }
                catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                catch (DataFormatException ex2) {
                    System.out.println(ex2.getMessage());
                }
                catch (ArithmeticException ex3) {
                    System.out.println(ex3.getMessage());
                }
            }
        }
        System.out.println("Converted as many files as possible in dir " + clean);
    }
    
    public void exportAllToPNG(String clean) {
        clean = this.clean(clean);
        final String[] list = new File(clean).list();
        final EditableGraphPanel editableGraphPanel = new EditableGraphPanel();
        for (int i = 0; i < list.length; ++i) {
            if (list[i].endsWith(".dat")) {
                try {
                    System.out.println("loading: " + list[i]);
                    editableGraphPanel.setGraph(this.dl.load(clean + File.separator + list[i]));
                    System.out.println("saving " + clean + File.separator + list[i]);
                    editableGraphPanel.saveImage(clean + File.separator + list[i] + ".png", 1);
                }
                catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                catch (DataFormatException ex2) {
                    System.out.println(ex2.getMessage());
                }
                catch (ArithmeticException ex3) {
                    System.out.println(ex3.getMessage());
                }
            }
        }
        System.out.println("Converted as many files as possible in dir " + clean);
    }
    
    private String clean(String substring) {
        if (substring.endsWith(File.separator)) {
            substring = substring.substring(0, substring.length() - 1);
            System.out.println(substring);
        }
        return substring;
    }
    
    public static void main(final String[] array) {
        final BatchOperations batchOperations = new BatchOperations();
        System.out.println("Converting");
        batchOperations.convertTabbedToMedusa(array[0]);
        System.out.println("Laying out ");
        batchOperations.layoutAll(array[0]);
    }
}
