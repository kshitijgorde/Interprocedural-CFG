// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.diff;

import org.xmodel.IChangeSet;
import org.xmodel.ChangeSet;
import org.xmodel.diff.IXmlMatcher;
import org.xmodel.IModelObjectFactory;
import org.xmodel.ModelObjectFactory;
import org.xmodel.diff.XmlDiffer;
import java.io.File;
import com.stonewall.cornerstone.utility.ModelBuilder;
import java.util.Iterator;
import java.util.List;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.IExpression;
import com.stonewall.cornerstone.dsp.loader.LoaderException;
import com.stonewall.cornerstone.entity.EntityFactory;
import org.xmodel.xpath.XPath;
import com.stonewall.cornerstone.dsp.loader.Loader;
import org.xmodel.log.Log;
import org.xmodel.diff.RuleBasedMatcher;

public class DeviceConfigMatcher extends RuleBasedMatcher
{
    static final Log log;
    
    static {
        log = Log.getLog(DeviceConfigMatcher.class);
    }
    
    public DeviceConfigMatcher(final Loader loader) {
        try {
            final MatcherFactory factory = (MatcherFactory)loader.newInstance("diff.MatcherFactory", new Object[0]);
            IExpression expr = XPath.createExpression("self::en:networks/parent::en:references");
            this.addRule(expr, factory.getMatcher(EntityFactory.EntityType.network));
            expr = XPath.createExpression("self::en:hosts/parent::en:references");
            this.addRule(expr, factory.getMatcher(EntityFactory.EntityType.host));
            expr = XPath.createExpression("self::en:addressGroups/parent::en:references");
            this.addRule(expr, factory.getMatcher(EntityFactory.EntityType.addressGroup));
            expr = XPath.createExpression("self::en:ipServices/parent::en:references");
            this.addRule(expr, factory.getMatcher(EntityFactory.EntityType.ipService));
            expr = XPath.createExpression("self::en:ipServiceGroups/parent::en:references");
            this.addRule(expr, factory.getMatcher(EntityFactory.EntityType.ipServiceGroup));
            expr = XPath.createExpression("self::en:ipInterfaces/parent::en:references");
            this.addRule(expr, factory.getMatcher(EntityFactory.EntityType.ipInterface));
            expr = XPath.createExpression("self::en:p1Proposals/parent::en:references");
            this.addRule(expr, factory.getMatcher(EntityFactory.EntityType.p1Proposal));
            expr = XPath.createExpression("self::en:p2Proposals/parent::en:references");
            this.addRule(expr, factory.getMatcher(EntityFactory.EntityType.p2Proposal));
            expr = XPath.createExpression("self::en:anys/parent::en:references");
            this.addRule(expr, factory.getMatcher(EntityFactory.EntityType.any));
            expr = XPath.createExpression("self::en:securityRule/parent::en:ruleSet");
            this.addRule(expr, factory.getMatcher(EntityFactory.EntityType.securityRule));
            expr = XPath.createExpression("self::en:securityTunnel/parent::en:tunnels");
            this.addRule(expr, factory.getMatcher(EntityFactory.EntityType.securityTunnel));
        }
        catch (LoaderException le) {
            DeviceConfigMatcher.log.error(this, le);
        }
    }
    
    @Override
    public boolean shouldDiff(final IModelObject object, final String attrName, final boolean lhs) {
        return super.shouldDiff(object, attrName, lhs) && (attrName == null || (!attrName.equals("id") && !attrName.equals("parent") && !attrName.equals("timestamp") && !attrName.equals("referenced") && !attrName.equals("phase") && !attrName.equals("pending")));
    }
    
    @Override
    public boolean shouldDiff(final IModelObject object, final boolean lhs) {
        return super.shouldDiff(object, lhs) && (!object.isType("en:ancillary") && !object.isType("en:checksum") && !object.isType("en:priority"));
    }
    
    @Override
    public boolean isList(final IModelObject parent) {
        return super.isList(parent) || parent.isType("en:ruleSet") || parent.isType("en:tunnels") || parent.isType("en:selector") || parent.isType("en:services") || (parent.isType("en:p1Proposals") && !parent.getParent().isType("en:references")) || (parent.isType("en:p2Proposals") && !parent.getParent().isType("en:references"));
    }
    
    @Override
    public int findMatch(final List<IModelObject> children, final IModelObject child) {
        if (child.isType("en:securityPolicy")) {
            for (final IModelObject c : children) {
                if (c.isType("en:securityPolicy")) {
                    return children.indexOf(c);
                }
            }
            return -1;
        }
        if (child.isType("en:natPolicy")) {
            for (final IModelObject c : children) {
                if (c.isType("en:natPolicy")) {
                    return children.indexOf(c);
                }
            }
            return -1;
        }
        return super.findMatch(children, child);
    }
    
    public static void main(final String[] args) throws Exception {
        final ModelBuilder builder = new ModelBuilder();
        final File lFile = new File("C:/lhs.xml");
        final File rFile = new File("C:/rhs.xml");
        final IModelObject lhs = builder.buildModel(lFile.toURL());
        final IModelObject rhs = builder.buildModel(rFile.toURL());
        System.out.println(builder.write(lhs));
        final XmlDiffer differ = new XmlDiffer();
        differ.setFactory(new ModelObjectFactory());
        final Loader loader = new Loader("", "");
        differ.setMatcher(new DeviceConfigMatcher(loader));
        final IChangeSet changeSet = new ChangeSet();
        differ.diff(lhs, rhs, changeSet);
        changeSet.applyChanges();
        System.out.println(changeSet.toString());
        System.out.println("\n" + builder.write(lhs));
        changeSet.clearChanges();
        differ.diff(lhs, rhs, changeSet);
        System.out.println("\n" + builder.write(lhs));
    }
}
