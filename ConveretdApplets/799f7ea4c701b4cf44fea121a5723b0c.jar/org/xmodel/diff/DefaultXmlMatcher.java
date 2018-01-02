// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.diff;

import org.xmodel.xml.XmlIO;
import org.xmodel.ChangeSet;
import org.xmodel.Xlate;
import java.util.List;
import org.xmodel.IChangeSet;
import org.xmodel.IModelObject;
import java.util.HashSet;
import java.util.Set;

public class DefaultXmlMatcher implements IXmlMatcher
{
    private Set<String> H;
    private Set<String> I;
    private boolean G;
    
    public DefaultXmlMatcher() {
        this(false);
    }
    
    public DefaultXmlMatcher(final boolean g) {
        this.G = g;
        this.H = new HashSet<String>();
        this.I = new HashSet<String>();
    }
    
    public void setOrderAll(final boolean g) {
        this.G = g;
    }
    
    public void ignoreAttribute(final String s) {
        this.H.add(s);
    }
    
    public void ignoreAttributes(final String[] array) {
        for (int i = 0; i < array.length; ++i) {
            this.H.add(array[i]);
        }
    }
    
    public void regardAttribute(final String s) {
        this.H.remove(s);
    }
    
    public void ignoreElement(final String s) {
        this.I.add(s);
    }
    
    public void ignoreElements(final String[] array) {
        for (int i = 0; i < array.length; ++i) {
            this.I.add(array[i]);
        }
    }
    
    public void regardElement(final String s) {
        this.I.remove(s);
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
        return !this.H.contains(s);
    }
    
    @Override
    public boolean shouldDiff(final IModelObject modelObject, final boolean b) {
        return !this.I.contains(modelObject.getType());
    }
    
    @Override
    public boolean isList(final IModelObject modelObject) {
        return this.G;
    }
    
    @Override
    public int findMatch(final List<IModelObject> list, final IModelObject modelObject) {
        final String type = modelObject.getType();
        final String value = Xlate.get(modelObject, "id", "");
        if (value.length() == 0) {
            if (modelObject.getNumberOfChildren() == 0) {
                return this.findSimpleMatch(list, modelObject);
            }
            if (modelObject.getParent().getChildren(type).size() == 1) {
                return this.findUniqueMatch(list, modelObject);
            }
        }
        else {
            for (int i = 0; i < list.size(); ++i) {
                final IModelObject modelObject2 = list.get(i);
                if (modelObject2 == modelObject) {
                    return i;
                }
                if (modelObject2.isType(type) && modelObject2.getID().equals(value)) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    protected int findSimpleMatch(final List<IModelObject> list, final IModelObject modelObject) {
        int n = -1;
        final String type = modelObject.getType();
        for (int i = 0; i < list.size(); ++i) {
            final IModelObject modelObject2 = list.get(i);
            if (modelObject2 == modelObject) {
                return i;
            }
            if (modelObject2.isType(type)) {
                if (modelObject2.getNumberOfChildren() == 0) {
                    return i;
                }
                n = i;
            }
        }
        return n;
    }
    
    protected int findUniqueMatch(final List<IModelObject> list, final IModelObject modelObject) {
        final String type = modelObject.getType();
        if (list.size() > 0) {
            final List<IModelObject> children = list.get(0).getParent().getChildren(type);
            if (children.size() == 1) {
                return list.indexOf(children.get(0));
            }
        }
        return -1;
    }
    
    @Override
    public boolean isMatch(final IModelObject modelObject, final IModelObject modelObject2) {
        final String id = modelObject.getID();
        final String id2 = modelObject2.getID();
        if (id.length() == 0 && id2.length() == 0) {
            return modelObject.isType(modelObject2.getType());
        }
        return modelObject.isType(modelObject2.getType()) && id.equals(id2);
    }
    
    public static void main(final String[] array) throws Exception {
        final ChangeSet set = new ChangeSet();
        final XmlIO xmlIO = new XmlIO();
        final XmlDiffer xmlDiffer = new XmlDiffer();
        final String s = "<r>  <e>1</e>  <e>2</e>  <e>3</e></r>";
        final String s2 = "<r>  <e>1</e>  <e>3</e>  <e>2</e></r>";
        final IModelObject read = xmlIO.read(s);
        final IModelObject read2 = xmlIO.read(s2);
        set.clearChanges();
        xmlDiffer.diff(read, read2, set);
        System.out.printf("set of simple elements:\n%s\n\n", set);
        final String s3 = "<r>  <e id='1'><c/></e>  <e id='2'><d/></e>  <e id='3'><e/></e></r>";
        final String s4 = "<r>  <e id='1'><c/></e>  <e id='3'><d/></e>  <e id='2'><e/></e></r>";
        final IModelObject read3 = xmlIO.read(s3);
        final IModelObject read4 = xmlIO.read(s4);
        set.clearChanges();
        xmlDiffer.diff(read3, read4, set);
        System.out.printf("set of complex elements with ids:\n%s\n\n", set);
        xmlDiffer.setMatcher(new DefaultXmlMatcher() {
            @Override
            public boolean isList(final IModelObject modelObject) {
                return modelObject.isType("r");
            }
        });
        final String s5 = "<r>  <e>1</e>  <e>2</e>  <e>3</e></r>";
        final String s6 = "<r>  <e>1</e>  <e>3</e>  <e>2</e></r>";
        final IModelObject read5 = xmlIO.read(s5);
        final IModelObject read6 = xmlIO.read(s6);
        set.clearChanges();
        xmlDiffer.diff(read5, read6, set);
        System.out.printf("list of simple elements:\n%s\n\n", set);
        final String s7 = "<r>  <e id='1'><c/></e>  <e id='2'><c/></e>  <e id='3'><c/></e></r>";
        final String s8 = "<r>  <e id='1'><c/></e>  <e id='3'><c/></e>  <e id='2'><c/></e></r>";
        final IModelObject read7 = xmlIO.read(s7);
        final IModelObject read8 = xmlIO.read(s8);
        set.clearChanges();
        xmlDiffer.diff(read7, read8, set);
        System.out.printf("list of complex elements with ids:\n%s\n\n", set);
    }
}
