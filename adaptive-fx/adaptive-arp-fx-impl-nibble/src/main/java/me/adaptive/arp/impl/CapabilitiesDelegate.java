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

/**
 * Interface for testing the Capabilities operations
 * Auto-generated implementation of ICapabilities specification.
 */
public class CapabilitiesDelegate extends BaseSystemDelegate implements ICapabilities {

    /**
     * Default Constructor.
     */
    public CapabilitiesDelegate() {
        super();
    }

    /**
     * Obtains the default orientation of the device/display. If no default orientation is available on
     * the platform, this method will return the current orientation. To capture device or display orientation
     * changes please use the IDevice and IDisplay functions and listeners API respectively.
     *
     * @return The default orientation for the device/display.
     * @since v2.0.5
     */
    public ICapabilitiesOrientation getOrientationDefault() {

        final AbstractDevice device = AbstractEmulator.getCurrentEmulator().getDevice();
        return device.getOrientationDefault();
    }

    /**
     * Provides the device/display orientations supported by the platform. A platform will usually
     * support at least one orientation. This is usually PortaitUp.
     *
     * @return The orientations supported by the device/display of the platform.
     * @since v2.0.5
     */
    public ICapabilitiesOrientation[] getOrientationsSupported() {

        final AbstractDevice device = AbstractEmulator.getCurrentEmulator().getDevice();
        return device.getOrientationsSupported();
    }

    /**
     * Determines whether a specific hardware button is supported for interaction.
     *
     * @param type Type of feature to check.
     * @return true is supported, false otherwise.
     * @since v2.0
     */
    public boolean hasButtonSupport(ICapabilitiesButton type) {

        final AbstractDevice device = AbstractEmulator.getCurrentEmulator().getDevice();
        return device.hasButtonSupport(type);
    }

    /**
     * Determines whether a specific Communication capability is supported by
     * the device.
     *
     * @param type Type of feature to check.
     * @return true if supported, false otherwise.
     * @since v2.0
     */
    public boolean hasCommunicationSupport(ICapabilitiesCommunication type) {

        final AbstractDevice device = AbstractEmulator.getCurrentEmulator().getDevice();
        return device.hasCommunicationSupport(type);
    }

    /**
     * Determines whether a specific Data capability is supported by the device.
     *
     * @param type Type of feature to check.
     * @return true if supported, false otherwise.
     * @since v2.0
     */
    public boolean hasDataSupport(ICapabilitiesData type) {

        final AbstractDevice device = AbstractEmulator.getCurrentEmulator().getDevice();
        return device.hasDataSupport(type);
    }

    /**
     * Determines whether a specific Media capability is supported by the
     * device.
     *
     * @param type Type of feature to check.
     * @return true if supported, false otherwise.
     * @since v2.0
     */
    public boolean hasMediaSupport(ICapabilitiesMedia type) {

        final AbstractDevice device = AbstractEmulator.getCurrentEmulator().getDevice();
        return device.hasMediaSupport(type);
    }

    /**
     * Determines whether a specific Net capability is supported by the device.
     *
     * @param type Type of feature to check.
     * @return true if supported, false otherwise.
     * @since v2.0
     */
    public boolean hasNetSupport(ICapabilitiesNet type) {

        final AbstractDevice device = AbstractEmulator.getCurrentEmulator().getDevice();
        return device.hasNetSupport(type);
    }

    /**
     * Determines whether a specific Notification capability is supported by the
     * device.
     *
     * @param type Type of feature to check.
     * @return true if supported, false otherwise.
     * @since v2.0
     */
    public boolean hasNotificationSupport(ICapabilitiesNotification type) {

        final AbstractDevice device = AbstractEmulator.getCurrentEmulator().getDevice();
        return device.hasNotificationSupport(type);
    }

    /**
     * Determines whether the device/display supports a given orientation.
     *
     * @param orientation Orientation type.
     * @return True if the given orientation is supported, false otherwise.
     * @since v2.0.5
     */
    public boolean hasOrientationSupport(ICapabilitiesOrientation orientation) {

        final AbstractDevice device = AbstractEmulator.getCurrentEmulator().getDevice();
        return device.hasOrientationSupport(orientation);
    }

    /**
     * Determines whether a specific Sensor capability is supported by the
     * device.
     *
     * @param type Type of feature to check.
     * @return true if supported, false otherwise.
     * @since v2.0
     */
    public boolean hasSensorSupport(ICapabilitiesSensor type) {

        final AbstractDevice device = AbstractEmulator.getCurrentEmulator().getDevice();
        return device.hasSensorSupport(type);
    }

}
/**
 * ------------------------------------| Engineered with ♥ in Barcelona, Catalonia |--------------------------------------
 */
