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

import java.util.ArrayList;
import java.util.List;

/**
 * Interface defining methods about the acceleration sensor
 * Auto-generated implementation of IAcceleration specification.
 */
public class AccelerationDelegate extends BaseSensorDelegate implements IAcceleration {

    /**
     * Logging Tag for this Implementation
     */
    private static final String LOG_TAG = "AccelerationDelegate";

    /**
     * Logger reference
     */
    private ILogging logger;

    /**
     * List of registered LifeCycle listeners
     */
    private List<IAccelerationListener> listeners;

    /**
     * Default Constructor.
     */
    public AccelerationDelegate() {
        super();
        logger = AppRegistryBridge.getInstance().getLoggingBridge();
        listeners = new ArrayList<>();
    }

    /**
     * Register a new listener that will receive acceleration events.
     *
     * @param listener to be registered.
     * @since v2.0
     */
    public void addAccelerationListener(IAccelerationListener listener) {

        if (!listeners.contains(listener)) {
            listeners.add(listener);
            logger.log(ILoggingLogLevel.Debug, LOG_TAG, "addAccelerationListener: " + listener.toString() + " added!");
        } else
            logger.log(ILoggingLogLevel.Error, LOG_TAG, "addAccelerationListener: " + listener.toString() + " is already added!");
    }

    /**
     * De-registers an existing listener from receiving acceleration events.
     *
     * @param listener to be registered.
     * @since v2.0
     */
    public void removeAccelerationListener(IAccelerationListener listener) {

        if (listeners.contains(listener)) {
            listeners.remove(listener);
            logger.log(ILoggingLogLevel.Debug, LOG_TAG, "removeAccelerationListener" + listener.toString() + " removed!");
        } else
            logger.log(ILoggingLogLevel.Error, LOG_TAG, "removeAccelerationListener: " + listener.toString() + " is not registered");
    }

    /**
     * Removed all existing listeners from receiving acceleration events.
     *
     * @since v2.0
     */
    public void removeAccelerationListeners() {

        listeners.clear();
        logger.log(ILoggingLogLevel.Debug, LOG_TAG, "removeAccelerationListeners: all AccelerationListeners have been removed!");
    }

    /**
     * Getter for registered listeners
     *
     * @return List of registered listeners
     */
    public List<IAccelerationListener> getListeners() {
        return listeners;
    }

}
/**
 * ------------------------------------| Engineered with ♥ in Barcelona, Catalonia |--------------------------------------
 */
