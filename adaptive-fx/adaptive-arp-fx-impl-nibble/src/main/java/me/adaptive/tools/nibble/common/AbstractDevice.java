/*
 * --| ADAPTIVE RUNTIME PLATFORM |----------------------------------------------------------------------------------------
 *
 * (C) Copyright 2013-2015 Carlos Lozano Diez t/a Adaptive.me <http://adaptive.me>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 . Unless required by appli-
 * -cable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,  WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the  License  for the specific language governing
 * permissions and limitations under the License.
 *
 * Original author:
 *
 *     * Carlos Lozano Diez
 *             <http://github.com/carloslozano>
 *             <http://twitter.com/adaptivecoder>
 *             <mailto:carlos@adaptive.me>
 *
 * Contributors:
 *
 *     * Ferran Vila Conesa
 *              <http://github.com/fnva>
 *              <http://twitter.com/ferran_vila>
 *              <mailto:ferran.vila.conesa@gmail.com>
 *
 *     * See source code files for contributors.
 *
 * Release:
 *
 *     * @version v2.0.2
 *
 * -------------------------------------------| aut inveniam viam aut faciam |--------------------------------------------
 */
package me.adaptive.tools.nibble.common;

import me.adaptive.tools.nibble.common.types.CompatibilityType;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class for inter-communication between the emulator (adaptive-tools-nibble)
 * and the API implementation. This class is for sharing information about the
 * device of the current emulator
 */
public abstract class AbstractDevice {

    /**
     * List of current Devices Registered on the system. The current Thread
     * is the identifier for this device on the Map.
     */
    private static final Map<Thread, AbstractDevice> ACTIVE_DEVICE = new HashMap<Thread, AbstractDevice>();

    // TODO: change all of this to DeviceInfo
    private String deviceName;
    private String deviceModel;
    private String deviceVendor;
    private CompatibilityType deviceType;
    private String fxView;

    // TODO: comment
    public AbstractDevice(String deviceName, String deviceModel, String deviceVendor, CompatibilityType deviceType) {
        this.deviceName = deviceName;
        this.deviceModel = deviceModel;
        this.deviceVendor = deviceVendor;
        this.deviceType = deviceType;
    }

    /**
     * Returns the current device registerd in the system
     *
     * @return Current Device Information
     */
    public static AbstractDevice getCurrentDevice() {

        return ACTIVE_DEVICE.get(Thread.currentThread());
    }

    /**
     * Registers a new device for the current Thread
     *
     * @param device Information of the current device
     */
    public static void registerThread(AbstractDevice device) {

        ACTIVE_DEVICE.put(Thread.currentThread(), device);
    }

    /**
     * Deregister the current device on the map by passing the current Thread
     */
    public static void deregisterThread() {

        ACTIVE_DEVICE.remove(Thread.currentThread());
    }

    /**
     * Device name getter.
     *
     * @return Device name
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * Device model getter.
     *
     * @return Device model
     */
    public String getDeviceModel() {
        return deviceModel;
    }

    /**
     * Device vendor getter.
     *
     * @return Device vendor
     */
    public String getDeviceVendor() {
        return deviceVendor;
    }

    /**
     * Device type getter.
     *
     * @return Device type
     */
    public CompatibilityType getDeviceType() {
        return deviceType;
    }

    /**
     * JavaFx view getter.
     *
     * @return JavaFx view
     */
    public final String getFxView() {
        return fxView;
    }

    /**
     * JavaFx view setter.
     *
     * @param fxView JavaFx view
     */
    protected final void setFxView(String fxView) {
        this.fxView = fxView;
    }

    /**
     * ToString Implementation method.
     *
     * @return String with all the information of the Device
     */
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