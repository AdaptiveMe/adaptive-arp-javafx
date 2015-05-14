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

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class for inter-communication between the emulator (adaptive-tools-nibble)
 * and the API implementation. This class is for sharing information about the
 * current emulator (Operating System and Device)
 */
public abstract class AbstractEmulator {

    /**
     * List of current Devices Registered on the system. The current Thread
     * is the identifier for this device on the Map.
     */
    private static final Map<Thread, AbstractEmulator> ACTIVE_EMULATORS = new HashMap<>();

    /**
     * Device reference for the current emulator
     */
    private AbstractDevice device;

    /**
     * Operating System reference for the current emulator
     */
    private AbstractOs os;

    /**
     * Application reference for the current emulator
     */
    private AbstractApp app;

    /**
     * Default constructor. Its mandatory to set all the references when creating a new emulator
     *
     * @param device Device Reference
     * @param os     Operating System Reference
     * @param app    Application Reference
     */
    public AbstractEmulator(AbstractDevice device, AbstractOs os, AbstractApp app) {
        this.device = device;
        this.os = os;
        this.app = app;
    }

    /**
     * Returns the current device registerd in the system
     *
     * @return Current Device Information
     */
    public static AbstractEmulator getCurrentEmulator() {

        return ACTIVE_EMULATORS.get(Thread.currentThread());
    }

    /**
     * Registers a new emulator for the current Thread
     *
     * @param emulator Information of the current emulator
     */
    public static void registerThread(AbstractEmulator emulator) {

        ACTIVE_EMULATORS.put(Thread.currentThread(), emulator);
    }

    /**
     * Deregister the current emulator on the map by passing the current Thread
     */
    public static void deregisterThread() {

        ACTIVE_EMULATORS.remove(Thread.currentThread());
    }

    /**
     * Returns the Device reference for this emulator
     *
     * @return Device reference
     */
    public AbstractDevice getDevice() {
        return device;
    }

    /**
     * Returns the Operating System reference for this emulator
     *
     * @return Operating System reference
     */
    public AbstractOs getOs() {
        return os;
    }

    /**
     * Returns the Application reference for this emulator
     *
     * @return Application reference
     */
    public AbstractApp getApp() {
        return app;
    }
}
