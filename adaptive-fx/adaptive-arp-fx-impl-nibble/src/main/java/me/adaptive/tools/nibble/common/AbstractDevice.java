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

public abstract class AbstractDevice {

    private static final Map<Thread, AbstractDevice> ACTIVE_DEVICE = new HashMap<Thread, AbstractDevice>();

    public static final void registerThread(AbstractDevice device) {
        ACTIVE_DEVICE.put(Thread.currentThread(), device);
    }

    public static final AbstractDevice getCurrentDevice() {
        return ACTIVE_DEVICE.get(Thread.currentThread());
    }

    public static final void deregisterThread() {
        ACTIVE_DEVICE.remove(Thread.currentThread());
    }

    private String deviceName;
    private String deviceModel;
    private String deviceVendor;
    private CompatibilityType deviceType;
    private String fxView;
    private String osType;

    public AbstractDevice(String deviceName, String deviceModel, String deviceVendor, CompatibilityType deviceType) {
        this.deviceName = deviceName;
        this.deviceModel = deviceModel;
        this.deviceVendor = deviceVendor;
        this.deviceType = deviceType;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public String getDeviceVendor() {
        return deviceVendor;
    }

    public CompatibilityType getDeviceType() {
        return deviceType;
    }

    public final String getFxView() {
        return fxView;
    }

    protected final void setFxView(String fxView) {
        this.fxView = fxView;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    @Override
    public String toString() {
        return "AbstractDevice{" +
                "deviceVendor='" + deviceVendor + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", deviceModel='" + deviceModel + '\'' +
                '}';
    }
}