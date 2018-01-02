// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.test;

import com.stonewall.cornerstone.dsp.loader.Loader;
import org.xmodel.xaction.IXAction;
import java.util.Iterator;
import java.io.InputStream;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.xpath.variable.IVariableScope;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.StatefulContext;
import org.xmodel.xpath.expression.NullContext;
import com.stonewall.cornerstone.utility.ModelBuilder;
import com.stonewall.cornerstone.dsp.DSP;
import com.stonewall.cornerstone.component.Bootstrap;
import org.xmodel.xpath.XPath;
import org.xmodel.xpath.expression.IExpression;
import junit.framework.TestCase;

public class TieTest extends TestCase
{
    private static final IExpression actionExpr;
    String VpnTie;
    String FilterTie;
    String Ties;
    
    static {
        actionExpr = XPath.createExpression("*[ not( matches( name(), 'factory|package|failurePattern|expect'))]");
    }
    
    public TieTest() {
        this.VpnTie = "  <en:tie xmlns:en=\"http://www.stonewallnetworks.com/ns/entity\"> \n    <en:base> \n      <en:securityRule id='R1'/> \n    </en:base> \n    <en:device> \n      <en:id> \n        <en:vpn id='V1'/> \n        <en:ike id='I1'/> \n      </en:id>     </en:device> \n  </en:tie>";
        this.FilterTie = "  <en:tie xmlns:en=\"http://www.stonewallnetworks.com/ns/entity\"> \n    <en:base> \n      <en:securityRule id='R1'/> \n      <en:ipHeader id='H1'/> \n    </en:base> \n    <en:device> \n      <en:id> \n        <en:policy id='1'/> \n      </en:id>     </en:device> \n  </en:tie>";
        this.Ties = "<en:ties xmlns:en=\"http://www.stonewallnetworks.com/ns/entity\"> \n  <en:tie> \n    <en:base> \n      <en:network id='N1'/>\n    </en:base> \n    <en:device> \n      <en:id> \n        <en:address id='A1'/> \n      </en:id>     </en:device> \n  </en:tie>  <en:tie> \n    <en:base> \n      <en:securityRule id='R1'/> \n    </en:base> \n    <en:device> \n      <en:id> \n        <en:vpn id='V1'/> \n        <en:ike id='I1'/> \n      </en:id>     </en:device> \n  </en:tie>  <en:tie> \n    <en:base> \n      <en:securityRule id='R1'/> \n      <en:ipHeader id='H1'/> \n    </en:base> \n    <en:device> \n      <en:id> \n        <en:policy id='1'/> \n      </en:id>     </en:device> \n  </en:tie></en:ties>";
    }
    
    public void testFindTie() {
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            DSP.init();
            final IModelObject vpntie = new ModelBuilder(ModelBuilder.Validation.none).buildModel(this.VpnTie);
            final IModelObject ties = new ModelBuilder(ModelBuilder.Validation.none).buildModel(this.Ties);
            final StatefulContext context = new StatefulContext(NullContext.getInstance().getObject());
            context.set("tie", vpntie);
            context.set("ties", ties);
            this.process(context, "testFindTie.xml");
            final IVariableScope scope = context.getScope();
            final List<IModelObject> l = (List<IModelObject>)scope.get("found");
            final IModelObject o = l.get(0);
            assertTrue(l.size() == 1);
            System.out.println(o);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void process(final IContext context, final String name) throws Exception {
        final XActionDocument doc = new XActionDocument(this.getClass().getClassLoader());
        final InputStream is = this.getClass().getClassLoader().getResourceAsStream(name);
        final ModelBuilder builder = new ModelBuilder();
        final IModelObject object = builder.buildModel(is);
        doc.setRoot(object);
        final List<IModelObject> actionNodes = TieTest.actionExpr.query(object, null);
        for (final IModelObject actionNode : actionNodes) {
            final IXAction action = doc.getAction(actionNode);
            action.run(context);
        }
    }
    
    private Loader loader() {
        return new Loader("screenos_v5_1_0", "ns5gt");
    }
}
