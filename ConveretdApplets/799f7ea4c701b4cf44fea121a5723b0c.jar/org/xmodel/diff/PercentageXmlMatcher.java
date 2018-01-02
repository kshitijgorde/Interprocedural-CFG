// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.diff;

import java.net.MalformedURLException;
import org.xmodel.xml.XmlException;
import org.xmodel.ChangeSet;
import java.io.File;
import org.xmodel.xml.XmlIO;
import org.xmodel.BreadthFirstIterator;
import java.util.Iterator;
import org.xmodel.IBoundChangeRecord;
import java.util.HashSet;
import org.xmodel.CanonicalPath;
import org.xmodel.ModelAlgorithms;
import java.util.List;
import org.xmodel.IChangeSet;
import org.xmodel.IModelObject;

public class PercentageXmlMatcher implements IXmlMatcher
{
    IXmlDiffer F;
    double E;
    
    public PercentageXmlMatcher(final double e) {
        this.E = e;
        (this.F = new XmlDiffer()).setMatcher(this);
    }
    
    public PercentageXmlMatcher(final IXmlDiffer f, final double e) {
        this.E = e;
        this.F = f;
    }
    
    @Override
    public void startDiff(final IModelObject modelObject, final IModelObject modelObject2, final IChangeSet set) {
    }
    
    @Override
    public void endDiff(final IModelObject modelObject, final IModelObject modelObject2, final IChangeSet set) {
    }
    
    @Override
    public void enterDiff(final IModelObject modelObject, final IModelObject modelObject2, final IChangeSet set) {
    }
    
    @Override
    public void exitDiff(final IModelObject modelObject, final IModelObject modelObject2, final IChangeSet set) {
    }
    
    @Override
    public boolean shouldDiff(final IModelObject modelObject, final String s, final boolean b) {
        return true;
    }
    
    @Override
    public boolean shouldDiff(final IModelObject modelObject, final boolean b) {
        return true;
    }
    
    @Override
    public boolean isList(final IModelObject modelObject) {
        return false;
    }
    
    @Override
    public int findMatch(final List<IModelObject> list, final IModelObject modelObject) {
        int n = -1;
        double n2 = 1.0;
        for (int i = 0; i < list.size(); ++i) {
            final IModelObject modelObject2 = list.get(i);
            if (modelObject2.isType(modelObject.getType())) {
                final double calculatePercentageDifference = this.calculatePercentageDifference(modelObject, modelObject2);
                if (calculatePercentageDifference < this.E && calculatePercentageDifference < n2) {
                    n2 = calculatePercentageDifference;
                    n = i;
                }
            }
        }
        final CanonicalPath canonicalPath = (n >= 0) ? ModelAlgorithms.createIdentityPath(list.get(n)) : null;
        System.out.println("min=" + n2);
        System.out.println("  local: " + ModelAlgorithms.createIdentityPath(modelObject));
        System.out.println("  match: " + canonicalPath);
        return n;
    }
    
    @Override
    public boolean isMatch(final IModelObject modelObject, final IModelObject modelObject2) {
        return this.calculatePercentageDifference(modelObject, modelObject2) < this.E;
    }
    
    protected double calculatePercentageDifference(final IModelObject modelObject, final IModelObject modelObject2) {
        final int numberOfLeaves = getNumberOfLeaves(modelObject);
        final int numberOfLeaves2 = getNumberOfLeaves(modelObject2);
        final int n = (numberOfLeaves > numberOfLeaves2) ? numberOfLeaves : numberOfLeaves2;
        final RegularChangeSet set = new RegularChangeSet();
        this.F.diff(modelObject, modelObject2, set);
        final HashSet<IModelObject> set2 = new HashSet<IModelObject>();
        double n2 = 0.0;
        for (final IBoundChangeRecord boundChangeRecord : set.getRecords()) {
            if (set2.contains(boundChangeRecord.getBoundObject())) {
                continue;
            }
            set2.add(boundChangeRecord.getBoundObject());
            int numberOfLeaves3 = 0;
            switch (boundChangeRecord.getType()) {
                case 2:
                case 3: {
                    numberOfLeaves3 = getNumberOfLeaves(boundChangeRecord.getChild());
                    break;
                }
                case 0:
                case 1: {
                    numberOfLeaves3 = 1;
                    break;
                }
            }
            n2 += numberOfLeaves3;
        }
        System.out.println("%=" + n2 / n);
        return n2 / n;
    }
    
    protected static int getNumberOfLeaves(final IModelObject modelObject) {
        int n = 0;
        final BreadthFirstIterator breadthFirstIterator = new BreadthFirstIterator(modelObject);
        while (breadthFirstIterator.hasNext()) {
            final IModelObject next = breadthFirstIterator.next();
            if (next.getNumberOfChildren() == 0) {
                ++n;
            }
            n += next.getAttributeNames().size();
        }
        return n;
    }
    
    public static void main(final String[] array) throws XmlException, MalformedURLException {
        final XmlIO xmlIO = new XmlIO();
        final File file = new File("C:/diff-101205");
        final IModelObject read = xmlIO.read(new File(file, "lhs.xml").toURL());
        final IModelObject read2 = xmlIO.read(new File(file, "rhs.xml").toURL());
        final XmlDiffer xmlDiffer = new XmlDiffer();
        xmlDiffer.setMatcher(new PercentageXmlMatcher(xmlDiffer, 0.1));
        final ChangeSet set = new ChangeSet();
        xmlDiffer.diff(read, read2, set);
        System.out.println("changes=" + set);
        System.out.println("done.");
    }
}
