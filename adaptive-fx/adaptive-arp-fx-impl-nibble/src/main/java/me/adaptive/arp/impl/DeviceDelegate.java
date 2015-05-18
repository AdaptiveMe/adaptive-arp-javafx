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
package me.adaptive.arp.impl;

import me.adaptive.arp.api.*;
import me.adaptive.tools.nibble.common.AbstractDevice;
import me.adaptive.tools.nibble.common.AbstractEmulator;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface for Managing the Device operations
 * Auto-generated implementation of IDevice specification.
 */
public class DeviceDelegate extends BaseSystemDelegate implements IDevice {

    /**
     * List of registered Button Listeners
     */
    private List<IButtonListener> buttonListeners;

    /**
     * List of registered Device Orientation listeners
     */
    private List<IDeviceOrientationListener> deviceOrientationListeners;

    /**
     * Log Tag
     */
    private static final String LOG_TAG = "DeviceDelegate";

    /**
     * Logger instance
     */
    private ILogging logger;

    /**
     * Default Constructor.
     */
    public DeviceDelegate() {
        super();
        logger = AppRegistryBridge.getInstance().getLoggingBridge();
        buttonListeners = new ArrayList<>();
        deviceOrientationListeners = new ArrayList<>();
    }

    /**
     * Returns the device information for the current device executing the runtime.
     *
     * @return DeviceInfo for the current device.
     * @since v2.0
     */
    public DeviceInfo getDeviceInfo() {
        final AbstractDevice device = AbstractEmulator.getCurrentEmulator().getDevice();
        return device.getDeviceInfo();
    }

    /**
     * Gets the current Locale for the device.
     *
     * @return The current Locale information.
     * @since v2.0
     */
    public Locale getLocaleCurrent() {

        final String language = java.util.Locale.getDefault().getLanguage();
        final String country = java.util.Locale.getDefault().getCountry();
        logger.log(ILoggingLogLevel.Debug, LOG_TAG, "getLocaleCurrent: " + language + ", " + country);

        return new Locale(country, language);
    }

    /**
     * Returns the current orientation of the device. Please note that this may be different from the orientation
     * of the display. For display orientation, use the IDisplay APIs.
     *
     * @return The current orientation of the device.
     * @since v2.0.5
     */
    public ICapabilitiesOrientation getOrientationCurrent() {
        final AbstractDevice device = AbstractEmulator.getCurrentEmulator().getDevice();
        return device.getDeviceOrientationCurrent();
    }

    /**
     * Register a new listener that will receive button events.
     *
     * @param listener to be registered.
     * @since v2.0
     */
    public void addButtonListener(IButtonListener listener) {

        if (!buttonListeners.contains(listener)) {
            buttonListeners.add(listener);
            logger.log(ILoggingLogLevel.Debug, LOG_TAG, "addButtonListener: " + listener.toString() + " added!");
        } else
            logger.log(ILoggingLogLevel.Error, LOG_TAG, "addButtonListener: " + listener.toString() + " is already added!");
    }

    /**
     * De-registers an existing listener from receiving button events.
     *
     * @param listener to be removed.
     * @since v2.0
     */
    public void removeButtonListener(IButtonListener listener) {

        if (buttonListeners.contains(listener)) {
            buttonListeners.remove(listener);
            logger.log(ILoggingLogLevel.Debug, LOG_TAG, "removeButtonListener: " + listener.toString() + " Removed!");
        } else
            logger.log(ILoggingLogLevel.Error, LOG_TAG, "removeButtonListener: " + listener.toString() + " is not registered");
    }

    /**
     * Removed all existing listeners from receiving button events.
     *
     * @since v2.0
     */
    public void removeButtonListeners() {

        buttonListeners.clear();
        logger.log(ILoggingLogLevel.Debug, LOG_TAG, "removeButtonListeners: all ButtonListeners have been removed!");
    }

    /**
     * Add a listener to start receiving device orientation change events.
     *
     * @param listener Listener to add to receive orientation change events.
     * @since v2.0.5
     */
    public void addDeviceOrientationListener(IDeviceOrientationListener listener) {

        if (!deviceOrientationListeners.contains(listener)) {
            deviceOrientationListeners.add(listener);
            logger.log(ILoggingLogLevel.Debug, LOG_TAG, "addDeviceOrientationListener: " + listener.toString() + " added!");
        } else
            logger.log(ILoggingLogLevel.Error, LOG_TAG, "addDeviceOrientationListener: " + listener.toString() + " is already added!");
    }

    /**
     * Remove a listener to stop receiving device orientation change events.
     *
     * @param listener Listener to remove from receiving orientation change events.
     * @since v2.0.5
     */
    public void removeDeviceOrientationListener(IDeviceOrientationListener listener) {

        if (deviceOrientationListeners.contains(listener)) {
            deviceOrientationListeners.remove(listener);
            logger.log(ILoggingLogLevel.Debug, LOG_TAG, "removeDeviceOrientationListener: " + listener.toString() + " removed!");
        } else
            logger.log(ILoggingLogLevel.Error, LOG_TAG, "removeDeviceOrientationListener: " + listener.toString() + " is not registered");
    }

    /**
     * Remove all listeners receiving device orientation events.
     *
     * @since v2.0.5
     */
    public void removeDeviceOrientationListeners() {

        deviceOrientationListeners.clear();
        logger.log(ILoggingLogLevel.Debug, LOG_TAG, "removeDeviceOrientationListeners: all DeviceOrientationListeners have been removed!");
    }

    /**
     * Getter for registered button listeners.
     *
     * @return List of registered Button Listeners
     */
    public List<IButtonListener> getButtonListeners() {
        return buttonListeners;
    }

    /**
     * Getter for registered orientation listeners.
     *
     * @return List of registered device orientation listeners
     */
    public List<IDeviceOrientationListener> getDeviceOrientationListeners() {
        return deviceOrientationListeners;
    }
}
/**
 * ------------------------------------| Engineered with ♥ in Barcelona, Catalonia |--------------------------------------
 */
