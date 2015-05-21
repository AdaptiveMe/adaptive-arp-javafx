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
package me.adaptive.tools.nibble.common.utils;

import me.adaptive.arp.api.FileDescriptor;
import me.adaptive.arp.api.*;
import me.adaptive.tools.nibble.common.utils.parser.xml.XmlParser;

import java.io.*;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utils class for public static methods
 */
public class Utils {

    /**
     * This method returns a byte Array from an input stream
     *
     * @param is Input Stream to read
     * @return Array of bytes
     */
    public static byte[] getBytesFromInputStream(InputStream is) {
        try (ByteArrayOutputStream os = new ByteArrayOutputStream();) {
            byte[] buffer = new byte[0xFFFF];

            for (int len; (len = is.read(buffer)) != -1; )
                os.write(buffer, 0, len);

            os.flush();

            return os.toByteArray();
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Static method for appending element to an array
     *
     * @param arr     Array
     * @param element Element
     * @param <T>     Type of the element
     * @return Return the element
     */
    public static <T> T[] append(T[] arr, T element) {
        final int N = arr.length;
        arr = Arrays.copyOf(arr, N + 1);
        arr[N] = element;
        return arr;
    }

    /**
     * Returns a FileDescriptor from a java File
     *
     * @param file to read
     * @return the FileDescriptor
     */
    public static FileDescriptor toArp(File file) {

        FileDescriptor fd = new FileDescriptor();
        fd.setName(file.getName());
        fd.setDateCreated(file.lastModified());
        fd.setDateModified(file.lastModified());
        fd.setPath(file.getPath());
        fd.setPathAbsolute(file.getAbsolutePath());
        fd.setSize(file.getTotalSpace());

        return fd;

    }

    /**
     * Returns a byte[]
     *
     * @param file to read
     * @return the byte[]
     * @throws IOException IOException
     */
    public static byte[] readFile(File file) throws IOException {
        // Open file
        RandomAccessFile f = new RandomAccessFile(file, "r");
        try {
            // Get and check length
            long longlength = f.length();
            int length = (int) longlength;
            if (length != longlength)
                throw new IOException("File size >= 2 GB");
            // Read file and return data
            byte[] data = new byte[length];
            f.readFully(data);
            return data;
        } finally {
            f.close();
        }
    }

    /**
     * Validate an url against a regexp
     *
     * @param test  url
     * @param regex test
     * @return true if valid, false otherwise
     */
    public static boolean validateURI(String test, String regex) {
        return test.matches(regex);
    }

    /**
     * Check whether a service should be handled or not
     *
     * @param serviceToken to validate
     * @return true if should be handled, false otherwise
     */
    public static boolean validateService(ServiceToken serviceToken) {
        Service serv = XmlParser.getInstance().getServices().get(serviceToken.getServiceName());
        for (ServiceEndpoint serviceEndpoint : serv.getServiceEndpoints()) {
            String endpointName = serviceToken.getEndpointName();
            if (endpointName.equals(serviceEndpoint.getHostURI())) {
                Pattern pattern = Pattern.compile(endpointName);
                Matcher m = pattern.matcher(serviceToken.getEndpointName());
                if (m.matches()) {
                    for (ServicePath servicePath : serviceEndpoint.getPaths()) {
                        pattern = Pattern.compile(servicePath.getPath());
                        m = pattern.matcher(serviceToken.getFunctionName());
                        if (m.matches()) return true;
                    }
                }

            }
        }
        return false;

    }

}
