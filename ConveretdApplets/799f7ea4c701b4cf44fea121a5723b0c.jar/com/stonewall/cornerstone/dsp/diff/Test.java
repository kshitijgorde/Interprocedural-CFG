// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.diff;

import org.xmodel.diff.IXmlMatcher;
import org.xmodel.IModelObject;
import org.xmodel.IChangeSet;
import org.xmodel.ChangeSet;
import org.xmodel.diff.XmlDiffer;
import com.stonewall.cornerstone.entity.EntityFactory;
import com.stonewall.cornerstone.dsp.juniper.screenos.v5_1_0.diff.MatcherFactory;
import com.stonewall.cornerstone.utility.ModelBuilder;
import junit.framework.TestCase;

public class Test extends TestCase
{
    static String lhs;
    static String rhs;
    
    static {
        Test.lhs = "<en:ipService id='SERVICE00' mutable='false'>  <en:name>ALL</en:name>  <en:srcPort>    <en:start>0</en:start>    <en:end>0</en:end>  </en:srcPort>  <en:dstPort>    <en:start>0</en:start>    <en:end>0</en:end>  </en:dstPort>  <en:protocol>0</en:protocol></en:ipService>";
        Test.rhs = "<en:ipService id='ANY' sticky='true'>  <en:name>ANY</en:name>  <en:protocol>0</en:protocol>  <en:srcPort>    <en:start>0</en:start>    <en:end>0</en:end>  </en:srcPort>  <en:dstPort>    <en:start>0</en:start>    <en:end>0</en:end>  </en:dstPort></en:ipService>";
    }
    
    public void test() {
        try {
            final IModelObject l = new ModelBuilder().buildModel(Test.lhs);
            final IModelObject r = new ModelBuilder().buildModel(Test.rhs);
            final IXmlMatcher matcher = new MatcherFactory().getMatcher(EntityFactory.EntityType.ipService);
            final XmlDiffer differ = new XmlDiffer();
            differ.setMatcher(matcher);
            final ChangeSet set = new ChangeSet();
            differ.diff(l, r, set);
            System.out.println(set);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
