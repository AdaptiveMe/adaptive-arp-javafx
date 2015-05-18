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

import me.adaptive.arp.api.BaseApplicationDelegate;
import me.adaptive.arp.api.IGlobalization;
import me.adaptive.arp.api.KeyPair;
import me.adaptive.arp.api.Locale;
import me.adaptive.tools.nibble.common.utils.parser.plist.PList;
import me.adaptive.tools.nibble.common.utils.parser.xml.XmlParser;

/**
 * Interface for Managing the Globalization results
 * Auto-generated implementation of IGlobalization specification.
 */
public class GlobalizationDelegate extends BaseApplicationDelegate implements IGlobalization {

    /**
     * Default Constructor.
     */
    public GlobalizationDelegate() {
        super();
    }

    /**
     * Returns the default locale of the application defined in the configuration file
     *
     * @return Default Locale of the application
     * @since v2.0
     */
    public Locale getDefaultLocale() {
        return XmlParser.getInstance().getDefaultLocale();
    }

    /**
     * List of supported locales for the application defined in the configuration file
     *
     * @return List of locales
     * @since v2.0
     */
    public Locale[] getLocaleSupportedDescriptors() {
        return XmlParser.getInstance().getLocales().toArray(new Locale[XmlParser.getInstance().getLocales().size()]);
    }

    /**
     * Gets the text/message corresponding to the given key and locale.
     *
     * @param key    to match text
     * @param locale The locale object to get localized message, or the locale desciptor ("language" or "language-country" two-letters ISO codes.
     * @return Localized text.
     * @since v2.0
     */
    public String getResourceLiteral(String key, Locale locale) {

        PList plist = XmlParser.getInstance().getLocalesData().get(localeToString(locale));
        if (plist != null) {
            return plist.getValue(key);
        }
        return null;
    }

    /**
     * Gets the full application configured literals (key/message pairs) corresponding to the given locale.
     *
     * @param locale The locale object to get localized message, or the locale desciptor ("language" or "language-country" two-letters ISO codes.
     * @return Localized texts in the form of an object.
     * @since v2.0
     */
    public KeyPair[] getResourceLiterals(Locale locale) {

        return XmlParser.getInstance().getLocalesData().get(localeToString(locale)).getKeyPair();
    }

    /**
     * Return the String representation of the Locale
     *
     * @param locale object
     * @return String
     */
    private String localeToString(Locale locale) {
        return locale.getLanguage() + "-" + locale.getCountry();
    }

}

/**
 * ------------------------------------| Engineered with ♥ in Barcelona, Catalonia |--------------------------------------
 */
