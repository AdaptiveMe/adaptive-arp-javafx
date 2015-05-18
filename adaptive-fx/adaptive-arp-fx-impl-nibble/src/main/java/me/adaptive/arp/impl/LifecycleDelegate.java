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
import me.adaptive.tools.nibble.common.AbstractApp;
import me.adaptive.tools.nibble.common.AbstractEmulator;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface for Managing the Lifecycle listeners
 * Auto-generated implementation of ILifecycle specification.
 */
public class LifecycleDelegate extends BaseApplicationDelegate implements ILifecycle {

    /**
     * Logging Tag for this Implementation
     */
    private static final String LOG_TAG = "LifecycleDelegate";

    /**
     * Logger reference
     */
    private ILogging logger;

    /**
     * List of registered LifeCycle listeners
     */
    private List<ILifecycleListener> listeners;


    /**
     * Default Constructor.
     */
    public LifecycleDelegate() {
        super();
        logger = AppRegistryBridge.getInstance().getLoggingBridge();
        listeners = new ArrayList<>();
    }

    /**
     * Add the listener for the lifecycle of the app
     *
     * @param listener Lifecycle listener
     * @since v2.0
     */
    public void addLifecycleListener(ILifecycleListener listener) {

        if (!listeners.contains(listener)) {
            listeners.add(listener);
            logger.log(ILoggingLogLevel.Debug, LOG_TAG, "addLifecycleListener: " + listener.toString() + " added!");
        } else
            logger.log(ILoggingLogLevel.Error, LOG_TAG, "addLifecycleListener: " + listener.toString() + " is already added!");
    }

    /**
     * Whether the application is in background or not
     *
     * @return true if the application is in background;false otherwise
     * @since v2.0
     */
    public boolean isBackground() {

        AbstractApp app = AbstractEmulator.getCurrentEmulator().getApp();
        return app.isBackground();
    }

    /**
     * Un-registers an existing listener from receiving lifecycle events.
     *
     * @param listener Lifecycle listener
     * @since v2.0
     */
    public void removeLifecycleListener(ILifecycleListener listener) {

        if (listeners.contains(listener)) {
            listeners.remove(listener);
            logger.log(ILoggingLogLevel.Debug, LOG_TAG, "removeLifecycleListener" + listener.toString() + " removed!");
        } else
            logger.log(ILoggingLogLevel.Error, LOG_TAG, "removeLifecycleListener: " + listener.toString() + " is not registered");
    }

    /**
     * Removes all existing listeners from receiving lifecycle events.
     *
     * @since v2.0
     */
    public void removeLifecycleListeners() {

        listeners.clear();
        logger.log(ILoggingLogLevel.Debug, LOG_TAG, "removeLifecycleListeners: all LifecycleListener have been removed!");
    }

    /**
     * Getter for registered listeners
     *
     * @return List of registered listeners
     */
    public List<ILifecycleListener> getListeners() {
        return listeners;
    }
}
/**
 * ------------------------------------| Engineered with ♥ in Barcelona, Catalonia |--------------------------------------
 */
