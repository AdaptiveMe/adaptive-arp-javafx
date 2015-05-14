/**
 * --| ADAPTIVE RUNTIME PLATFORM |----------------------------------------------------------------------------------------
 * <p>
 * (C) Copyright 2013-2015 Carlos Lozano Diez t/a Adaptive.me <http://adaptive.me>.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 . Unless required by appli-
 * -cable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,  WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the  License  for the specific language governing
 * permissions and limitations under the License.
 * <p>
 * Original author:
 * <p>
 * Carlos Lozano Diez
 * <http://github.com/carloslozano>
 * <http://twitter.com/adaptivecoder>
 * <mailto:carlos@adaptive.me>
 * <p>
 * Contributors:
 * <p>
 * Ferran Vila Conesa
 * <http://github.com/fnva>
 * <http://twitter.com/ferran_vila>
 * <mailto:ferran.vila.conesa@gmail.com>
 * <p>
 * See source code files for contributors.
 * <p>
 * Release:
 *
 * @version v2.2.0
 * <p>
 * -------------------------------------------| aut inveniam viam aut faciam |--------------------------------------------
 */

package me.adaptive.arp.impl;

import me.adaptive.arp.api.IAppContext;
import me.adaptive.arp.api.IOSType;
import me.adaptive.tools.nibble.common.AbstractEmulator;
import me.adaptive.tools.nibble.common.AbstractOs;

/**
 * Interface for context management purposes
 * Auto-generated implementation of IAppContext specification.
 */
public class AppContextDelegate implements IAppContext {

    /**
     * Default Constructor.
     */
    public AppContextDelegate() {
        super();
    }

    /**
     * The main application context. This should be cast to the platform specific implementation.
     *
     * @return Object representing the specific singleton application context provided by the OS.
     * @since v2.0
     */
    public Object getContext() {
        Object response;
        // TODO: Not implemented.
        throw new UnsupportedOperationException(this.getClass().getName() + ":getContext");
        // return response;
    }

    /**
     * The type of context provided by the getContext method.
     *
     * @return Type of platform context.
     * @since v2.0
     */
    public IOSType getContextType() {
        final AbstractOs os = AbstractEmulator.getCurrentEmulator().getOs();
        return os.getOsInfo().getName();
    }

}
/**
 * ------------------------------------| Engineered with ♥ in Barcelona, Catalonia |--------------------------------------
 */
