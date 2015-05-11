/**
 --| ADAPTIVE RUNTIME PLATFORM |----------------------------------------------------------------------------------------

 (C) Copyright 2013-2015 Carlos Lozano Diez t/a Adaptive.me <http://adaptive.me>.

 Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 . Unless required by appli-
 -cable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,  WITHOUT
 WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the  License  for the specific language governing
 permissions and limitations under the License.

 Original author:

 * Carlos Lozano Diez
 <http://github.com/carloslozano>
 <http://twitter.com/adaptivecoder>
 <mailto:carlos@adaptive.me>

 Contributors:

 * Ferran Vila Conesa
 <http://github.com/fnva>
 <http://twitter.com/ferran_vila>
 <mailto:ferran.vila.conesa@gmail.com>

 * See source code files for contributors.

 Release:

 * @version v2.2.0

-------------------------------------------| aut inveniam viam aut faciam |--------------------------------------------
 */
package me.adaptive.tools.nibble.common;

import me.adaptive.tools.nibble.common.types.CompatibilityType;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractOs {

    private static final Map<Thread, AbstractOs> ACTIVE_OS = new HashMap<Thread, AbstractOs>();

    public static final void registerThread( AbstractOs os) {
        ACTIVE_OS.put(Thread.currentThread(), os);
    }

    public static final AbstractOs getCurrentOs() {
        return ACTIVE_OS.get(Thread.currentThread());
    }

    public static final void deregisterThread() {
        ACTIVE_OS.remove(Thread.currentThread());
    }

    private String osName;
    private String osVersion;
    private String osVendor;
    private CompatibilityType osType;
    private String fxView;

    public AbstractOs(String osName, String osVersion, String osVendor, CompatibilityType osType) {
        this.osName = osName;
        this.osVersion = osVersion;
        this.osVendor = osVendor;
        this.osType = osType;
    }

    public String getOsName() {
        return osName;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public String getOsVendor() {
        return osVendor;
    }

    public CompatibilityType getOsType() {
        return osType;
    }

    public final String getFxView() {
        return fxView;
    }

    protected final void setFxView(String fxView) {
        this.fxView = fxView;
    }

    @Override
    public String toString() {
        return "AbstractOs{" +
                "osName='" + osName + '\'' +
                ", osVersion='" + osVersion + '\'' +
                ", osVendor='" + osVendor + '\'' +
                ", osType=" + osType +
                '}';
    }
}