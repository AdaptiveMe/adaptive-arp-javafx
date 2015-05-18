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
 * Interface for Managing the Network status
 * Auto-generated implementation of INetworkStatus specification.
 */
public class NetworkStatusDelegate extends BaseCommunicationDelegate implements INetworkStatus {

    /**
     * Logging Tag for this Implementation
     */
    private static final String LOG_TAG = "NetworkStatusDelegate";

    /**
     * Logger reference
     */
    private ILogging logger;

    /**
     * List of registered NetworkStatus listeners
     */
    private List<INetworkStatusListener> listeners;

    /**
     * Default Constructor.
     */
    public NetworkStatusDelegate() {
        super();
        logger = AppRegistryBridge.getInstance().getLoggingBridge();
        listeners = new ArrayList<>();
    }

    /**
     * Add the listener for network status changes of the app
     *
     * @param listener Listener with the result
     * @since v2.0
     */
    public void addNetworkStatusListener(INetworkStatusListener listener) {

        if (!listeners.contains(listener)) {
            listeners.add(listener);
            logger.log(ILoggingLogLevel.Debug, LOG_TAG, "addNetworkStatusListener: " + listener.toString() + " added!");
        } else
            logger.log(ILoggingLogLevel.Error, LOG_TAG, "addNetworkStatusListener: " + listener.toString() + " is already added!");
    }

    /**
     * Un-registers an existing listener from receiving network status events.
     *
     * @param listener Listener with the result
     * @since v2.0
     */
    public void removeNetworkStatusListener(INetworkStatusListener listener) {

        if (listeners.contains(listener)) {
            listeners.remove(listener);
            logger.log(ILoggingLogLevel.Debug, LOG_TAG, "removeNetworkStatusListener" + listener.toString() + " removed!");
        } else
            logger.log(ILoggingLogLevel.Error, LOG_TAG, "removeNetworkStatusListener: " + listener.toString() + " is not registered");
    }

    /**
     * Removes all existing listeners from receiving network status events.
     *
     * @since v2.0
     */
    public void removeNetworkStatusListeners() {

        listeners.clear();
        logger.log(ILoggingLogLevel.Debug, LOG_TAG, "removeNetworkStatusListeners: all GeolocationListeners have been removed!");
    }

    /**
     * Getter for registered listeners
     *
     * @return List of registered listeners
     */
    public List<INetworkStatusListener> getListeners() {
        return listeners;
    }

}
/**
 * ------------------------------------| Engineered with ♥ in Barcelona, Catalonia |--------------------------------------
 */
