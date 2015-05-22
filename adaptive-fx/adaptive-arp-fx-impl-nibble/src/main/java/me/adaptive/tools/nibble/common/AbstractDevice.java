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

import me.adaptive.arp.api.*;
import me.adaptive.arp.impl.*;

/**
 * Abstract class for inter-communication between the emulator (adaptive-tools-nibble)
 * and the API implementation. This class is for sharing information about the
 * device of the current emulator
 */
public abstract class AbstractDevice implements IAbstractDevice {

    /**
     * Device Information: Vendor, Name, UUID
     */
    private DeviceInfo deviceInfo;

    /**
     * Cosntructor passing the device information
     *
     * @param deviceInfo Device information
     */
    public AbstractDevice(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    /**
     * Returns the current device information
     *
     * @return Device Information Bean
     */
    @Override
    public final DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    /**
     * External method to notify all the registered device orientation listeners that
     * a new device orientation event is fired by the emulator
     *
     * @param event Device orientation event with all the information of the change
     */
    @Override
    public final void notifyDeviceOrientationListeners(RotationEvent event) {

        DeviceDelegate delegate = (DeviceDelegate) AppRegistryBridge.getInstance().getDeviceBridge().getDelegate();

        for (IDeviceOrientationListener listener : delegate.getDeviceOrientationListeners()) {
            listener.onResult(event);
        }
    }

    /**
     * External method to notify all the registered display orientation listeners that
     * a new display orientation event is fired by the emulator
     *
     * @param event Display orientation event with all the information of the change
     */
    @Override
    public final void notifyDisplayOrientationListeners(RotationEvent event) {

        DisplayDelegate delegate = (DisplayDelegate) AppRegistryBridge.getInstance().getDisplayBridge().getDelegate();

        for (IDisplayOrientationListener listener : delegate.getDisplayOrientationListeners()) {
            listener.onResult(event);
        }
    }

    /**
     * External method to notify all the registered device button listeners that
     * a new button has been pressed
     *
     * @param button Button pressed
     */
    @Override
    public final void notifyDeviceButtonListeners(Button button) {

        DeviceDelegate delegate = (DeviceDelegate) AppRegistryBridge.getInstance().getDeviceBridge().getDelegate();

        for (IButtonListener listener : delegate.getButtonListeners()) {
            listener.onResult(button);
        }
    }

    /**
     * External method to notify all the registered device button listeners that
     * a new acceleration event is fired.
     *
     * @param acceleration Acceleration event
     */
    @Override
    public final void notifyAccelerationListeners(Acceleration acceleration) {

        AccelerationDelegate delegate = (AccelerationDelegate) AppRegistryBridge.getInstance().getAccelerationBridge().getDelegate();

        for (IAccelerationListener listener : delegate.getListeners()) {
            listener.onResult(acceleration);
        }
    }

    /**
     * External method to notify all the registered device button listeners that
     * a new geolocation event is fired.
     *
     * @param geolocation Geolocation event
     */
    @Override
    public final void notifyGeolocationListeners(Geolocation geolocation) {

        GeolocationDelegate delegate = (GeolocationDelegate) AppRegistryBridge.getInstance().getGeolocationBridge().getDelegate();

        for (IGeolocationListener listener : delegate.getListeners()) {
            listener.onResult(geolocation);
        }
    }

    /**
     * External method to notify all the registered device button listeners that
     * a new Network Status event is fired.
     *
     * @param networkEvent Network event
     */
    @Override
    public final void notifyNetworkStatusListeners(NetworkEvent networkEvent) {

        NetworkStatusDelegate delegate = (NetworkStatusDelegate) AppRegistryBridge.getInstance().getNetworkStatusBridge().getDelegate();

        for (INetworkStatusListener listener : delegate.getListeners()) {
            listener.onResult(networkEvent);
        }
    }

}