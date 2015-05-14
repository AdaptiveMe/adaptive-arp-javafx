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
package me.adaptive.tools.nibble.common.utils.parser;

/**
 * Class for storing all the constants for parsing xmls and plist files
 */
public class Constants {

    /**
     * Definitions configurations files
     */
    private static final String DEFINITIONS_PATH = "definitions/";
    public static final String I18N_DEFINITIONS_FILE = DEFINITIONS_PATH + "i18n-config.xsd";
    public static final String IO_DEFINITIONS_FILE = DEFINITIONS_PATH + "io-config.xsd";

    /**
     * PLIST Configuration
     */
    public static final String PLIST_EXTENSION = ".plist";
    public final static String PLIST_TAG = "plist";
    public final static String DICT_TAG = "dict";
    public final static String KEY_TAG = "key";
    public final static String VALUE_TAG = "string";

    /**
     * Languages config file
     */
    public static final String I18N_CONFIG_FILE = "i18n-config.xml";
    public static final String DEFAULT_TAG = "default";
    public static final String SUPPORTED_LANG_TAG = "supportedLanguage";
    public static final String LANGUAGE_ATT = "language";
    public static final String COUNTRY_ATT = "country";

    /**
     * Services configuration file
     */
    public static final String IO_CONFIG_FILE = "io-config.xml";
    public static final String SERVICE_TAG = "service";
    public static final String NAME_ATT = "name";
    public static final String ENDPOINT_TAG = "end-point";
    public static final String HOST_ATT = "host";
    public static final String VALIDATION_ATT = "validation";
    public static final String PATH_TAG = "path";
    public static final String PATH_ATT = "path";
    public static final String TYPE_ATT = "type";
    public static final String METHOD_TAG = "method";
    public static final String METHOD_ATT = "method";
    public static final String RESOURCE_TAG = "resource";
    public static final String URL_ATT = "url";
}
