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

import eu.codearte.jfairy.Fairy;
import eu.codearte.jfairy.producer.company.Company;
import eu.codearte.jfairy.producer.person.Person;
import eu.codearte.jfairy.producer.text.TextProducer;
import me.adaptive.arp.api.*;
import me.adaptive.tools.nibble.common.utils.Utils;

import java.util.Arrays;
import java.util.Random;

/**
 * Interface for Managing the Contact operations
 * Auto-generated implementation of IContact specification.
 */
public class ContactDelegate extends BasePIMDelegate implements IContact {

    /**
     * Fairy instance for generating Mock Data
     */
    private Fairy fairy;

    /**
     * Default Constructor.
     */
    public ContactDelegate() {
        super();
        fairy = Fairy.create();
    }

    /**
     * Get all the details of a contact according to its id
     *
     * @param contact  id to search for
     * @param callback called for return
     * @since v2.0
     */
    public void getContact(ContactUid contact, IContactResultCallback callback) {

        Contact[] contacts = new Contact[1];
        contacts[0] = generateContact(contact, new IContactFieldGroup[]{
                IContactFieldGroup.PersonalInfo,
                IContactFieldGroup.ProfessionalInfo,
                IContactFieldGroup.Addresses,
                IContactFieldGroup.Phones,
                IContactFieldGroup.Emails,
                IContactFieldGroup.Websites,
                IContactFieldGroup.Socials,
                IContactFieldGroup.Tags
        }, "");
        callback.onResult(contacts);
    }

    /**
     * Get all contacts
     *
     * @param callback called for return
     * @since v2.0
     */
    public void getContacts(IContactResultCallback callback) {

        int number = new Random().nextInt(1000);
        Contact[] contacts = new Contact[number];

        for (int i = 0; i < number; i++) {
            contacts[i] = generateContact(new ContactUid(Integer.toString(i)), new IContactFieldGroup[]{
                    IContactFieldGroup.PersonalInfo,
                    IContactFieldGroup.ProfessionalInfo,
                    IContactFieldGroup.Addresses,
                    IContactFieldGroup.Phones,
                    IContactFieldGroup.Emails,
                    IContactFieldGroup.Websites,
                    IContactFieldGroup.Socials,
                    IContactFieldGroup.Tags
            }, "");
        }

        callback.onResult(contacts);
    }

    /**
     * Get marked fields of all contacts
     *
     * @param callback called for return
     * @param fields   to get for each Contact
     * @since v2.0
     */
    public void getContactsForFields(IContactResultCallback callback, IContactFieldGroup[] fields) {

        int number = new Random().nextInt(1000);
        Contact[] contacts = new Contact[number];

        for (int i = 0; i < number; i++) {
            contacts[i] = generateContact(new ContactUid(Integer.toString(i)), fields, "");
        }

        callback.onResult(contacts);
    }

    /**
     * Get marked fields of all contacts according to a filter
     *
     * @param callback called for return
     * @param fields   to get for each Contact
     * @param filter   to search for
     * @since v2.0
     */
    public void getContactsWithFilter(IContactResultCallback callback, IContactFieldGroup[] fields, IContactFilter[] filter) {

        // In the mock data there is always a phone, address or email, so this is the same as the above method
        this.getContactsForFields(callback, fields);
    }

    /**
     * Search contacts according to a term and send it to the callback
     *
     * @param term     string to search
     * @param callback called for return
     * @since v2.0
     */
    public void searchContacts(String term, IContactResultCallback callback) {

        int number = new Random().nextInt(1000);
        Contact[] contacts = new Contact[0];

        for (int i = 0; i < number; i++) {

            Contact c = generateContact(new ContactUid(Integer.toString(i)), new IContactFieldGroup[]{
                    IContactFieldGroup.PersonalInfo,
                    IContactFieldGroup.ProfessionalInfo,
                    IContactFieldGroup.Addresses,
                    IContactFieldGroup.Phones,
                    IContactFieldGroup.Emails,
                    IContactFieldGroup.Websites,
                    IContactFieldGroup.Socials,
                    IContactFieldGroup.Tags
            }, term);

            // When finding by term the response could be null
            if (c != null) {
                contacts = Utils.append(contacts, c);
            }
        }

        callback.onResult(contacts);
    }

    /**
     * Search contacts according to a term with a filter and send it to the callback
     *
     * @param term     string to search
     * @param callback called for return
     * @param filter   to search for
     * @since v2.0
     */
    public void searchContactsWithFilter(String term, IContactResultCallback callback, IContactFilter[] filter) {

        // In the mock data there is always a phone, address or email, so this is the same as the above method
        this.searchContacts(term, callback);
    }

    /**
     * Get the contact photo
     *
     * @param contact  id to search for
     * @param callback called for return
     * @since v2.0
     */
    public void getContactPhoto(ContactUid contact, IContactPhotoResultCallback callback) {

        // TODO: Not implemented.
        callback.onError(IContactPhotoResultCallbackError.NoPhoto);
    }

    /**
     * Set the contact photo
     *
     * @param contact  id to assign the photo
     * @param pngImage photo as byte array
     * @return true if set is successful;false otherwise
     * @since v2.0
     */
    public boolean setContactPhoto(ContactUid contact, byte[] pngImage) {

        // TODO: Not implemented.
        return false;
    }

    /**
     * Method for generating one contact with all the information filled with Mock Data
     *
     * @param contact Contact Identifier
     * @param fields
     * @param term
     * @return Contact with all the information required by the parameters
     */
    private Contact generateContact(ContactUid contact, IContactFieldGroup[] fields, String term) {
        // Create Mock Data
        Contact c = new Contact();
        Person person = fairy.person();
        Company company = fairy.company();
        TextProducer text = fairy.textProducer();

        // Term to find
        if (!term.equals("")) {
            if (!person.fullName().toLowerCase().contains(term.toLowerCase())) {
                return null;
            }
        }

        // contactid
        c.setContactId(contact.getContactId());

        // personal info
        if (Arrays.binarySearch(fields, IContactFieldGroup.PersonalInfo) >= 0) {

            c.setPersonalInfo(new ContactPersonalInfo(person.firstName(),
                    person.middleName(),
                    person.lastName(),
                    person.isMale() ? ContactPersonalInfoTitle.Mr : ContactPersonalInfoTitle.Ms));
        }

        // professional info
        if (Arrays.binarySearch(fields, IContactFieldGroup.ProfessionalInfo) >= 0) {
            c.setProfessionalInfo(new ContactProfessionalInfo(
                    text.latinWord(1),
                    text.latinWord(),
                    company.name()));
        }

        // address
        if (Arrays.binarySearch(fields, IContactFieldGroup.Addresses) >= 0) {
            ContactAddress[] addresses = new ContactAddress[1];
            addresses[0] = new ContactAddress(person.getAddress().toString(), ContactAddressType.Home);
            c.setContactAddresses(addresses);
        }

        // emails
        if (Arrays.binarySearch(fields, IContactFieldGroup.Emails) >= 0) {
            ContactEmail[] emails = new ContactEmail[2];
            emails[0] = new ContactEmail(ContactEmailType.Personal, true, person.email());
            emails[1] = new ContactEmail(ContactEmailType.Work, false, company.email());
            c.setContactEmails(emails);
        }

        // phones
        if (Arrays.binarySearch(fields, IContactFieldGroup.Phones) >= 0) {
            ContactPhone[] phones = new ContactPhone[2];
            phones[0] = new ContactPhone(person.telephoneNumber(), ContactPhoneType.Home);
            phones[1] = new ContactPhone(company.email(), ContactPhoneType.Work);
            c.setContactPhones(phones);
        }

        // social
        if (Arrays.binarySearch(fields, IContactFieldGroup.Socials) >= 0) {
            ContactSocial[] socials = new ContactSocial[3];
            socials[0] = new ContactSocial(ContactSocialNetwork.Facebook, person.username());
            socials[1] = new ContactSocial(ContactSocialNetwork.LinkedIn, person.username());
            socials[2] = new ContactSocial(ContactSocialNetwork.Twitter, person.username());
            c.setContactSocials(socials);
        }

        // tags
        if (Arrays.binarySearch(fields, IContactFieldGroup.Tags) >= 0) {
            int number = new Random().nextInt(3);
            ContactTag[] tags = new ContactTag[number];
            for (int count = 0; count < number; count++) {
                tags[count] = new ContactTag(text.latinWord(1), text.latinWord());
            }
            c.setContactTags(tags);
        }

        // websites
        if (Arrays.binarySearch(fields, IContactFieldGroup.Websites) >= 0) {
            ContactWebsite[] websites = new ContactWebsite[1];
            websites[0] = new ContactWebsite(company.url());
            c.setContactWebsites(websites);
        }

        return c;
    }

}
/**
 * ------------------------------------| Engineered with ♥ in Barcelona, Catalonia |--------------------------------------
 */
