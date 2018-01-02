// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.base.modules;

import org.jfree.util.Log;
import java.util.Iterator;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class PackageSorter
{
    public static void sort(final List list) {
        final HashMap<String, SortModule> hashMap = new HashMap<String, SortModule>();
        final ArrayList list2 = new ArrayList<PackageState>();
        final ArrayList list3 = new ArrayList<SortModule>();
        for (int i = 0; i < list.size(); ++i) {
            final PackageState packageState = list.get(i);
            if (packageState.getState() == -2) {
                list2.add(packageState);
            }
            else {
                final SortModule sortModule = new SortModule(packageState);
                list3.add(sortModule);
                hashMap.put(packageState.getModule().getModuleClass(), sortModule);
            }
        }
        final SortModule[] array = list3.toArray(new SortModule[list3.size()]);
        for (int j = 0; j < array.length; ++j) {
            final SortModule sortModule2 = array[j];
            sortModule2.setDependSubsystems(collectSubsystemModules(sortModule2.getState().getModule(), hashMap));
        }
        int k = 1;
        while (k != 0) {
            k = 0;
            for (int l = 0; l < array.length; ++l) {
                final SortModule sortModule3 = array[l];
                final int searchModulePosition = searchModulePosition(sortModule3, hashMap);
                if (searchModulePosition != sortModule3.getPosition()) {
                    sortModule3.setPosition(searchModulePosition);
                    k = 1;
                }
            }
        }
        Arrays.sort(array);
        list.clear();
        for (int n = 0; n < array.length; ++n) {
            list.add(array[n].getState());
        }
        for (int n2 = 0; n2 < list2.size(); ++n2) {
            list.add(list2.get(n2));
        }
    }
    
    private static int searchModulePosition(final SortModule sortModule, final HashMap hashMap) {
        final Module module = sortModule.getState().getModule();
        int n = 0;
        final ModuleInfo[] optionalModules = module.getOptionalModules();
        for (int i = 0; i < optionalModules.length; ++i) {
            final SortModule sortModule2 = hashMap.get(optionalModules[i].getModuleClass());
            if (sortModule2 != null) {
                if (sortModule2.getPosition() >= n) {
                    n = sortModule2.getPosition() + 1;
                }
            }
        }
        final ModuleInfo[] requiredModules = module.getRequiredModules();
        for (int j = 0; j < requiredModules.length; ++j) {
            final SortModule sortModule3 = hashMap.get(requiredModules[j].getModuleClass());
            if (sortModule3.getPosition() >= n) {
                n = sortModule3.getPosition() + 1;
            }
        }
        final String subSystem = module.getSubSystem();
        for (final SortModule sortModule4 : hashMap.values()) {
            if (sortModule4.getState().getModule() == module) {
                continue;
            }
            final Module module2 = sortModule4.getState().getModule();
            if (subSystem.equals(module2.getSubSystem())) {
                continue;
            }
            if (!sortModule.getDependSubsystems().contains(module2.getSubSystem()) || isBaseModule(module2, module) || sortModule4.getPosition() < n) {
                continue;
            }
            n = sortModule4.getPosition() + 1;
        }
        return n;
    }
    
    private static boolean isBaseModule(final Module module, final ModuleInfo moduleInfo) {
        final ModuleInfo[] requiredModules = module.getRequiredModules();
        for (int i = 0; i < requiredModules.length; ++i) {
            if (requiredModules[i].getModuleClass().equals(moduleInfo.getModuleClass())) {
                return true;
            }
        }
        final ModuleInfo[] optionalModules = module.getOptionalModules();
        for (int j = 0; j < optionalModules.length; ++j) {
            if (optionalModules[j].getModuleClass().equals(moduleInfo.getModuleClass())) {
                return true;
            }
        }
        return false;
    }
    
    private static ArrayList collectSubsystemModules(final Module module, final HashMap hashMap) {
        final ArrayList<String> list = new ArrayList<String>();
        final ModuleInfo[] requiredModules = module.getRequiredModules();
        for (int i = 0; i < requiredModules.length; ++i) {
            final SortModule sortModule = hashMap.get(requiredModules[i].getModuleClass());
            if (sortModule == null) {
                Log.warn(new Log.SimpleMessage("A dependent module was not found in the list of known modules.", requiredModules[i].getModuleClass()));
            }
            else {
                list.add(sortModule.getState().getModule().getSubSystem());
            }
        }
        final ModuleInfo[] optionalModules = module.getOptionalModules();
        for (int j = 0; j < optionalModules.length; ++j) {
            final Module module2 = (Module)hashMap.get(optionalModules[j].getModuleClass());
            if (module2 == null) {
                Log.warn("A dependent module was not found in the list of known modules.");
            }
            else {
                list.add(module2.getSubSystem());
            }
        }
        return list;
    }
    
    private static class SortModule implements Comparable
    {
        private int position;
        private final PackageState state;
        private ArrayList dependSubsystems;
        
        public SortModule(final PackageState state) {
            this.position = -1;
            this.state = state;
        }
        
        public ArrayList getDependSubsystems() {
            return this.dependSubsystems;
        }
        
        public void setDependSubsystems(final ArrayList dependSubsystems) {
            this.dependSubsystems = dependSubsystems;
        }
        
        public int getPosition() {
            return this.position;
        }
        
        public void setPosition(final int position) {
            this.position = position;
        }
        
        public PackageState getState() {
            return this.state;
        }
        
        public String toString() {
            final StringBuffer sb = new StringBuffer();
            sb.append("SortModule: ");
            sb.append(this.position);
            sb.append(" ");
            sb.append(this.state.getModule().getName());
            sb.append(" ");
            sb.append(this.state.getModule().getModuleClass());
            return sb.toString();
        }
        
        public int compareTo(final Object o) {
            final SortModule sortModule = (SortModule)o;
            if (this.position > sortModule.position) {
                return 1;
            }
            if (this.position < sortModule.position) {
                return -1;
            }
            return 0;
        }
    }
}
