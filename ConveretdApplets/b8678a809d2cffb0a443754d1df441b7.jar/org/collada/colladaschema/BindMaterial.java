// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "params", "techniqueCommon", "techniques", "extras" })
@XmlRootElement(name = "bind_material")
public class BindMaterial
{
    @XmlElement(name = "param")
    protected List<Param> params;
    @XmlElement(name = "technique_common", required = true)
    protected TechniqueCommon techniqueCommon;
    @XmlElement(name = "technique")
    protected List<Technique> techniques;
    @XmlElement(name = "extra")
    protected List<Extra> extras;
    
    public List<Param> getParams() {
        if (this.params == null) {
            this.params = new ArrayList<Param>();
        }
        return this.params;
    }
    
    public TechniqueCommon getTechniqueCommon() {
        return this.techniqueCommon;
    }
    
    public void setTechniqueCommon(final TechniqueCommon value) {
        this.techniqueCommon = value;
    }
    
    public List<Technique> getTechniques() {
        if (this.techniques == null) {
            this.techniques = new ArrayList<Technique>();
        }
        return this.techniques;
    }
    
    public List<Extra> getExtras() {
        if (this.extras == null) {
            this.extras = new ArrayList<Extra>();
        }
        return this.extras;
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "instanceMaterials" })
    public static class TechniqueCommon
    {
        @XmlElement(name = "instance_material", required = true)
        protected List<InstanceMaterial> instanceMaterials;
        
        public List<InstanceMaterial> getInstanceMaterials() {
            if (this.instanceMaterials == null) {
                this.instanceMaterials = new ArrayList<InstanceMaterial>();
            }
            return this.instanceMaterials;
        }
    }
}
