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
public abstract class AbstractOs {

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
    public OSInfo getOsInfo() {
        return osInfo;
    }

    /**
     * Method that plays a url video inside the emulator. The emulator should
     * implement a video player in JavaFx
     *
     * @param url URl of the stream
     */
    public abstract void playStream(String url);

    /**
     * This method executes a javascript inside the emulator's webview
     *
     * @param javaScriptText Javascript to execute
     */
    public abstract void executeJavaScript(String javaScriptText);

    /**
     * Method for sending an SMS to a concrete number with a text. The method executes a
     * callback when there is a result to show
     *
     * @param number   Number to send the SMS
     * @param text     Text to send.
     * @param callback Callback fired when there is a result.
     */
    public abstract void sendSMS(String number, String text, IMessagingCallback callback);

    /**
     * Method for sending an Email to one or more receivers.  The method executes a
     * callback when there is a result to show
     *
     * @param data     Email data encapsulated into a bean
     * @param callback Callback fired when there is a result.
     */
    public abstract void sendEmail(Email data, IMessagingCallback callback);

    /**
     * Method that opens a browser on the operating system of the running emulator.
     *
     * @param url URl to open. HAs to be a valid URL
     * @return Returns the result of the operation.
     */
    public abstract boolean openExtenalBrowser(String url);

    /**
     * Method for emulating a call in the current emulator and operating system.
     *
     * @param number Number to call
     * @return Staus of the call
     */
    public abstract ITelephonyStatus call(String number);

    /**
     * Method for consulting the user agent of the current emulator and
     * operating System
     *
     * @return USer agent descriptor
     */
    public abstract String getUserAgent();
}