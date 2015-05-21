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
package me.adaptive.tools.nibble.common.utils.parser.xml;

import me.adaptive.arp.api.*;
import me.adaptive.tools.nibble.common.utils.core.AppResourceManager;
import me.adaptive.tools.nibble.common.utils.parser.Constants;
import me.adaptive.tools.nibble.common.utils.parser.plist.PList;
import me.adaptive.tools.nibble.common.utils.parser.plist.PListParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlParser {

    /**
     * Logger tag for this class
     */
    private static final String LOG_TAG = "XmlParser";

    /**
     * Logger instance
     */
    private ILogging logger;

    /**
     * List of whitelisted resources
     */
    private List<String> resources = null;

    /**
     * Map of registered services
     */
    private Map<String, Service> services = null;

    /**
     * Default locale of the application
     */
    private Locale defaultLocale;

    /**
     * List of locales defined in the application
     */
    private List<Locale> locales = null;

    /**
     * List of locales defined in the application with all the information
     * of every locale inside the plist file.
     */
    private Map<String, PList> localesData = null;

    /**
     * Singleton instance for this class
     */
    private static XmlParser instance = null;

    /**
     * Class constructor. Instantiate elements.
     */
    protected XmlParser() {
        logger = AppRegistryBridge.getInstance().getLoggingBridge();
        initialize();
    }

    /**
     * Singleton getter or constructor in case of null
     *
     * @return Singleton instance for this class
     */
    public static XmlParser getInstance() {
        if (instance == null) {
            instance = new XmlParser();
        }
        return instance;
    }

    /**
     * Init function -- should be called just once
     */
    private void initialize() {

        services = new HashMap<>();
        resources = new ArrayList<>();
        locales = new ArrayList<>();
        localesData = new HashMap<>();

        InputStream is = null;
        Document document;

        try {

            // Parse the io config file and populate the class attributes
            is = new ByteArrayInputStream(AppResourceManager.getInstance().retrieveConfigResource(Constants.IO_CONFIG_FILE).getData());
            document = this.parseXml(is);
            resources = this.getResourceData(document);
            services = this.getServicesData(document);

            // Parse the languages config file and populate the class attributes
            is = new ByteArrayInputStream(AppResourceManager.getInstance().retrieveConfigResource(Constants.I18N_CONFIG_FILE).getData());
            document = this.parseXml(is);
            defaultLocale = this.getLocaleData(document, Constants.DEFAULT_TAG).get(0);
            locales = this.getLocaleData(document, Constants.SUPPORTED_LANG_TAG);

            // Parse the languages definitions file and populate the class attributes
            for (Locale locale : locales) {
                is = new ByteArrayInputStream(AppResourceManager.getInstance().retrieveConfigResource(getResourcesFilePath(locale)).getData());
                PList plist = PListParser.getInstance().parse(is);
                localesData.put(localeToString(locale), plist);
            }
        } catch (Exception e) {
            logger.log(ILoggingLogLevel.Error, LOG_TAG, "Error getting information from xml's: " + e.getMessage());
        } finally {
            closeStream(is);

        }
    }

    /**
     * Return the Document parsed from InputStream
     *
     * @param xml origin InputStream
     * @return Document parsed
     * @throws IOException                  IOException
     * @throws ParserConfigurationException ParserConfigurationException
     * @throws SAXException                 SAXException
     */
    private Document parseXml(InputStream xml) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory parserFactory = DocumentBuilderFactory.newInstance();
        parserFactory.setNamespaceAware(true);

        return parserFactory.newDocumentBuilder().parse(xml);

    }

    /**
     * Return all whitelisted resources url
     *
     * @param d source
     * @return Whitelisted urls
     * @throws ParserConfigurationException ParserConfigurationException
     * @throws SAXException                 SAXException
     * @throws IOException                  IOException
     */
    private List<String> getResourceData(Document d) throws ParserConfigurationException, SAXException, IOException {

        List<String> resources = new ArrayList<>();

        Element docEle = d.getDocumentElement();

        NodeList nl = docEle.getElementsByTagName(Constants.RESOURCE_TAG);
        if (nl != null && nl.getLength() > 0) {
            for (int i = 0; i < nl.getLength(); i++) {
                Element el = (Element) nl.item(i);
                resources.add(el.getAttribute(Constants.URL_ATT));
            }
        }
        return resources;
    }

    /**
     * Returns the IO Data from a Document
     *
     * @param d Document
     * @return services data
     * @throws ParserConfigurationException ParserConfigurationException
     * @throws SAXException                 SAXException
     * @throws IOException                  IOException
     */
    private Map<String, Service> getServicesData(Document d) throws ParserConfigurationException, SAXException, IOException {

        Map<String, Service> services = new HashMap<>();

        Element docEle = d.getDocumentElement();

        NodeList nl = docEle.getElementsByTagName(Constants.SERVICE_TAG);
        if (nl != null && nl.getLength() > 0) {
            for (int i = 0; i < nl.getLength(); i++) {

                Element el = (Element) nl.item(i);
                Service serv = this.getService(el);
                services.put(serv.getName(), serv);

            }
        }
        return services;
    }

    /**
     * Returns Service from xml element
     *
     * @param e containing the data
     * @return Service
     */
    private Service getService(Element e) {

        List<ServiceEndpoint> endpoints = new ArrayList<>();

        NodeList nl = e.getElementsByTagName(Constants.ENDPOINT_TAG);
        if (nl != null && nl.getLength() > 0) {
            for (int i = 0; i < nl.getLength(); i++) {
                Element el = (Element) nl.item(i);
                endpoints.add(this.getEndPoint(el));
            }
        }

        String name = e.getAttribute(Constants.NAME_ATT);
        return new Service(endpoints.toArray(new ServiceEndpoint[endpoints.size()]), name);

    }

    /**
     * Returns and ServiceEndpoint from Xml element
     *
     * @param e xml element
     * @return ServiceEndpoint
     */
    private ServiceEndpoint getEndPoint(Element e) {

        List<ServicePath> paths = new ArrayList<>();
        NodeList nl = e.getElementsByTagName(Constants.PATH_TAG);
        for (int i = 0; i < nl.getLength(); i++) {
            Element ele = (Element) nl.item(i);
            paths.add(this.getPath(ele));
        }

        return new ServiceEndpoint(e.getAttribute(Constants.HOST_ATT), paths.toArray(new ServicePath[paths.size()]));
    }

    /**
     * Return the ServicePath from an Element
     *
     * @param e Element
     * @return ServicePath
     */
    private ServicePath getPath(Element e) {

        List<IServiceMethod> methods = new ArrayList<>();

        NodeList nl = e.getElementsByTagName(Constants.METHOD_TAG);
        if (nl != null && nl.getLength() > 0) {
            Element ele = (Element) nl.item(0);
            methods.add(IServiceMethod.valueOf(ele.getAttribute(Constants.METHOD_ATT)));
        }

        return new ServicePath(e.getAttribute(Constants.PATH_ATT), methods.toArray(new IServiceMethod[methods.size()]),
                IServiceType.valueOf(e.getAttribute(Constants.TYPE_ATT)));

    }

    /**
     * Returns the i18n locale data
     *
     * @param document to read
     * @param tag      node to read
     * @return locales data
     * @throws ParserConfigurationException ParserConfigurationException
     * @throws SAXException                 SAXException
     * @throws IOException                  IOException
     */
    private List<Locale> getLocaleData(Document document, String tag) throws ParserConfigurationException, SAXException, IOException {

        List<Locale> locales = new ArrayList<>();

        Element docEle = document.getDocumentElement();

        NodeList nl = docEle.getElementsByTagName(tag);
        if (nl != null && nl.getLength() > 0) {
            for (int i = 0; i < nl.getLength(); i++) {
                Element el = (Element) nl.item(i);
                Locale locale = this.getLocale(el);
                locales.add(locale);
            }
        }
        return locales;
    }

    /**
     * Returns a Locale from xml element
     *
     * @param empEl containing the data
     * @return a Locale
     */
    private Locale getLocale(Element empEl) {

        String country = empEl.getAttribute(Constants.COUNTRY_ATT);
        String language = empEl.getAttribute(Constants.LANGUAGE_ATT);

        return new Locale(language, country);
    }

    /**
     * Close given InputStream
     *
     * @param is inputString
     */
    private void closeStream(InputStream is) {

        try {
            if (is != null) {
                is.close();
            }
        } catch (Exception ex) {
            logger.log(ILoggingLogLevel.Error, LOG_TAG, "Error closing stream: " + ex.getLocalizedMessage());
        }
    }

    /**
     * Returns the content type for a ServiceToken
     *
     * @param serviceToken Service Token for consulting
     * @return IServiceType
     */
    public IServiceType getContentType(ServiceToken serviceToken) {
        if (services.containsKey(serviceToken.getServiceName())) {
            for (ServiceEndpoint serviceEndpoint : services.get(serviceToken.getServiceName()).getServiceEndpoints()) {
                for (ServicePath servicePath : serviceEndpoint.getPaths()) {
                    if (servicePath.getPath().equals(serviceToken.getFunctionName())) {
                        return servicePath.getType();
                    }
                }
            }
        }
        return null;
    }

    /**
     * get the absolute path for resources
     *
     * @param locale data
     * @return The string with the path
     */
    private String getResourcesFilePath(Locale locale) {
        return localeToString(locale) + Constants.PLIST_EXTENSION;
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

    /**
     * Default locale configured in the app.
     *
     * @return Default locale configured in the app
     */
    public Locale getDefaultLocale() {
        return defaultLocale;
    }

    /**
     * Returns the list of locales configured in the app
     *
     * @return List of locales configured in the app
     */
    public List<Locale> getLocales() {
        return locales;
    }

    /**
     * Returns a list of locales configured in the app with all the string inside
     *
     * @return List of locales configured in the app with all the string inside
     */
    public Map<String, PList> getLocalesData() {
        return localesData;
    }

    /**
     * Returns the configured white-listed resources for the app.
     *
     * @return Configured white-listed resources for the app.
     */
    public List<String> getResources() {
        return resources;
    }

    /**
     * Returns the configured services for the app.
     *
     * @return Configured services for the app.
     */
    public Map<String, Service> getServices() {
        return services;
    }


}
