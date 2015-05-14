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
 * operating system of the current emulator
 */
public abstract class AbstractOs {

    /**
     * List of current Operating Systems Registered on the system. The current Thread
     * is the identifier for this os on the Map.
     */
    private static final Map<Thread, AbstractOs> ACTIVE_OS = new HashMap<Thread, AbstractOs>();


    // TODO: Change all of this to OSInfo?
    private String osName;
    private String osVersion;
    private String osVendor;
    private CompatibilityType osType;

    // TODO: comment
    public AbstractOs(String osName, String osVersion, String osVendor, CompatibilityType osType) {
        this.osName = osName;
        this.osVersion = osVersion;
        this.osVendor = osVendor;
        this.osType = osType;
    }

    /**
     * Method for returning the current running operating system of the emulator
     *
     * @return AbstractOs with all the information of the operating system
     */
    public static AbstractOs getCurrentOs() {

        return ACTIVE_OS.get(Thread.currentThread());
    }

    /**
     * Method for registering a new Thread, or current Operating System
     *
     * @param os Operating System registered
     */
    public static void registerThread(AbstractOs os) {

        ACTIVE_OS.put(Thread.currentThread(), os);
    }

    /**
     * Method for deregistering the current Operating System from the
     * pull of registered Threads
     */
    public static void deregisterThread() {

        ACTIVE_OS.remove(Thread.currentThread());
    }

    /**
     * Operating system name getter.
     *
     * @return Operating system name
     */
    public String getOsName() {
        return osName;
    }

    /**
     * Operating system version getter.
     *
     * @return Operating system version
     */
    public String getOsVersion() {
        return osVersion;
    }

    /**
     * Operating system vendor getter.
     *
     * @return Operating System vendor
     */
    public String getOsVendor() {
        return osVendor;
    }

    /**
     * Operating system type getter.
     *
     * @return Operating system type
     */
    public CompatibilityType getOsType() {
        return osType;
    }

    /**
     * ToString Implementation method.
     *
     * @return String with all the information of the Operating System
     */
    @Override
    public String toString() {
        return "AbstractOs{" + "osName='" + osName + '\'' + ", osVersion='" + osVersion + '\'' + ", osVendor='" +
                osVendor + '\'' + ", osType=" + osType + '}';
    }
}