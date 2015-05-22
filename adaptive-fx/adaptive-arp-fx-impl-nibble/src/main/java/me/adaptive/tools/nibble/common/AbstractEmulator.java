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

/**
 * Abstract class for inter-communication between the emulator (adaptive-tools-nibble)
 * and the API implementation. This class is for sharing information about the
 * current emulator (Operating System and Device)
 */
public final class AbstractEmulator {

    /**
     * Singleton reference for Isolated Classloader.
     */
    private static AbstractEmulator singletonInstance;

    /**
     * Device reference for the current emulator
     */
    private IAbstractDevice device;

    /**
     * Operating System reference for the current emulator
     */
    private IAbstractOs os;

    /**
     * Application reference for the current emulator
     */
    private IAbstractApp app;

    /**
     * Default constructor. Its mandatory to set all the references when creating a new emulator
     *
     * @param device Device Reference
     * @param os     Operating System Reference
     * @param app    Application Reference
     */
    public AbstractEmulator(IAbstractDevice device, IAbstractOs os, IAbstractApp app) {
        this.device = device;
        this.os = os;
        this.app = app;
    }

    public static final void setInstance(IAbstractDevice device, IAbstractOs os, IAbstractApp app) {
        if (singletonInstance == null) {
            singletonInstance = new AbstractEmulator(device, os, app);
        } else {
            synchronized (singletonInstance) {
                singletonInstance.setDevice(device);
                singletonInstance.setOs(os);
                singletonInstance.setApp(app);
            }
        }
    }

    /**
     * Returns the current device registerd in the system
     *
     * @return Current Device Information
     */
    public static AbstractEmulator getCurrentEmulator() {

        return singletonInstance;
    }

    /**
     * Set current device. Called internally.
     *
     * @param device Current device.
     */
    private void setDevice(IAbstractDevice device) {
        this.device = device;
    }

    /**
     * Set current os. Called internally.
     *
     * @param os Current os.
     */
    private void setOs(IAbstractOs os) {
        this.os = os;
    }

    /**
     * Set current app. Called internally.
     *
     * @param app Current app.
     */
    private void setApp(IAbstractApp app) {
        this.app = app;
    }

    /**
     * Returns the Device reference for this emulator
     *
     * @return Device reference
     */
    public IAbstractDevice getDevice() {
        return device;
    }

    /**
     * Returns the Operating System reference for this emulator
     *
     * @return Operating System reference
     */
    public IAbstractOs getOs() {
        return os;
    }

    /**
     * Returns the Application reference for this emulator
     *
     * @return Application reference
     */
    public IAbstractApp getApp() {
        return app;
    }
}
