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
public abstract class AbstractDevice {

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
    public DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    /**
     * Method that returns the current orientation of the emulator.
     *
     * @return Enum value that represents a orientation
     */
    public abstract ICapabilitiesOrientation getDeviceOrientationCurrent();

    /**
     * Method that returns the current orientation of the display in the emulator.
     *
     * @return Enum value that represents a orientation
     */
    public abstract ICapabilitiesOrientation getDisplayOrientationCurrent();

    /**
     * External method to notify all the registered device orientation listeners that
     * a new device orientation event is fired by the emulator
     *
     * @param event Device orientation event with all the information of the change
     */
    public void notifyDeviceOrientationListeners(RotationEvent event) {

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
    public void notifyDisplayOrientationListeners(RotationEvent event) {

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
    public void notifyDeviceButtonListeners(Button button) {

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
    public void notifyAccelerationListeners(Acceleration acceleration) {

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
    public void notifyGeolocationListeners(Geolocation geolocation) {

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
    public void notifyNetworkStatusListeners(NetworkEvent networkEvent) {

        NetworkStatusDelegate delegate = (NetworkStatusDelegate) AppRegistryBridge.getInstance().getNetworkStatusBridge().getDelegate();

        for (INetworkStatusListener listener : delegate.getListeners()) {
            listener.onResult(networkEvent);
        }
    }

    /**
     * Obtains the default orientation of the device/display. If no default orientation is available on
     * the platform, this method will return the current orientation. To capture device or display orientation
     * changes please use the IDevice and IDisplay functions and listeners API respectively.
     *
     * @return The default orientation for the device/display.
     */
    public abstract ICapabilitiesOrientation getOrientationDefault();

    /**
     * Provides the device/display orientations supported by the platform. A platform will usually
     * support at least one orientation. This is usually PortaitUp.
     *
     * @return The orientations supported by the device/display of the platform.
     */
    public abstract ICapabilitiesOrientation[] getOrientationsSupported();

    /**
     * Determines whether a specific hardware button is supported for interaction.
     *
     * @param type Type of feature to check.
     * @return true is supported, false otherwise.
     */
    public abstract boolean hasButtonSupport(ICapabilitiesButton type);

    /**
     * Determines whether a specific Communication capability is supported by
     * the device.
     *
     * @param type Type of feature to check.
     * @return true if supported, false otherwise.
     */
    public abstract boolean hasCommunicationSupport(ICapabilitiesCommunication type);

    /**
     * Determines whether a specific Data capability is supported by the device.
     *
     * @param type Type of feature to check.
     * @return true if supported, false otherwise.
     */
    public abstract boolean hasDataSupport(ICapabilitiesData type);

    /**
     * Determines whether a specific Media capability is supported by the
     * device.
     *
     * @param type Type of feature to check.
     * @return true if supported, false otherwise.
     */
    public abstract boolean hasMediaSupport(ICapabilitiesMedia type);

    /**
     * Determines whether a specific Net capability is supported by the device.
     *
     * @param type Type of feature to check.
     * @return true if supported, false otherwise.
     */
    public abstract boolean hasNetSupport(ICapabilitiesNet type);

    /**
     * Determines whether a specific Notification capability is supported by the
     * device.
     *
     * @param type Type of feature to check.
     * @return true if supported, false otherwise.
     */
    public abstract boolean hasNotificationSupport(ICapabilitiesNotification type);

    /**
     * Determines whether the device/display supports a given orientation.
     *
     * @param orientation Orientation type.
     * @return True if the given orientation is supported, false otherwise.
     */
    public abstract boolean hasOrientationSupport(ICapabilitiesOrientation orientation);

    /**
     * Determines whether a specific Sensor capability is supported by the
     * device.
     *
     * @param type Type of feature to check.
     * @return true if supported, false otherwise.
     */
    public abstract boolean hasSensorSupport(ICapabilitiesSensor type);

    /**
     * Returns if the device has been modified in anyhow
     *
     * @return true if the device has been modified; false otherwise
     */
    public abstract boolean isDeviceModified();
}