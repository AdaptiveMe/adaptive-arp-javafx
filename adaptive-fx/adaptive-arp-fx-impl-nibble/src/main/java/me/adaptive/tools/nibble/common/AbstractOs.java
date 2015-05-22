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

import me.adaptive.arp.api.Email;
import me.adaptive.arp.api.IMessagingCallback;
import me.adaptive.arp.api.ITelephonyStatus;
import me.adaptive.arp.api.OSInfo;

/**
 * Abstract class for inter-communication between the emulator (adaptive-tools-nibble)
 * and the API implementation. This class is for sharing information about the
 * operating system of the current emulator
 */
public abstract class AbstractOs implements IAbstractOs {

    /**
     * Operating System Information
     */
    private OSInfo osInfo;

    /**
     * Operating System Constructor
     *
     * @param osInfo Operating System Information
     */
    public AbstractOs(OSInfo osInfo) {
        this.osInfo = osInfo;
    }

    /**
     * Returns the Operating System information encapsulated in a bean.
     *
     * @return Operating System information
     */
    @Override
    public OSInfo getOsInfo() {
        return osInfo;
    }

}