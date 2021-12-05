package com.lms.gj_jewelry.test.random;

import com.lms.gj_jewelry.domain.Manufacturer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static com.lms.gj_jewelry.test.random.RandomStringGenerator.*;

public class RandomManufacturerGenerator {

    static final int MAX_ACCOUNT_LENGTH = 30;
    static final int MAX_PASSWORD_LENGTH = 100;
    static final int MAX_EMAIL_LENGTH = 80;
    static final int MAX_PHONE_NUMBER_LENGTH = 13;
    static final int MAX_NAME_LENGTH = 100;

    public static List<Manufacturer> generateRandomManufacturerList(int neededManufacturers) {
        List<Manufacturer> manufacturerList = new ArrayList<>();

        HashSet<String> usedAccounts = new HashSet<>();
        HashSet<String> usedEmails = new HashSet<>();
        HashSet<String> usedPhoneNumbers = new HashSet<>();
        HashSet<String> usedNames = new HashSet<>();

        for (long id = 1; id <= neededManufacturers; ++id) {
            Manufacturer randomManufacturer = generateRandomUniqueManufacturer(
                    id, usedAccounts, usedEmails, usedPhoneNumbers, usedNames
            );
            manufacturerList.add(randomManufacturer);

            usedAccounts.add(randomManufacturer.getAccount());
            usedEmails.add(randomManufacturer.getEmail());
            usedPhoneNumbers.add(randomManufacturer.getPhoneNumber());
            usedNames.add(randomManufacturer.getName());
        }

        return manufacturerList;
    }

    public static Manufacturer generateRandomUniqueManufacturer(
            Long id,
            HashSet<String> usedAccounts,
            HashSet<String> usedEmails,
            HashSet<String> usedPhoneNumbers,
            HashSet<String> usedNames
    ) {
        String newAccount = generateRandomUniqueString(usedAccounts, MAX_ACCOUNT_LENGTH, false);
        String newPassword = generateRandomString(MAX_PASSWORD_LENGTH, false);
        String newPhoneNumber = generateRandomUniqueString(usedPhoneNumbers, MAX_PHONE_NUMBER_LENGTH, true);
        String newEmail = generateRandomUniqueEmail(usedEmails, MAX_EMAIL_LENGTH);
        String newName = generateRandomUniqueString(usedNames, MAX_NAME_LENGTH, true);

        Manufacturer randomManufacturer = Manufacturer.builder()
                .id(id)
                .account(newAccount)
                .password(newPassword)
                .phoneNumber(newPhoneNumber)
                .email(newEmail)
                .name(newName)
                .build();

        return randomManufacturer;
    }
    
    public static Manufacturer generateRandomManufacturer() {
        int id = random.nextInt(1000000);
        String newAccount = generateRandomString(MAX_ACCOUNT_LENGTH, false);
        String newPassword = generateRandomString(MAX_PASSWORD_LENGTH, false);
        String newEmail = generateRandomUniqueEmail(null, MAX_EMAIL_LENGTH);
        String newPhoneNumber = generateRandomString(MAX_PHONE_NUMBER_LENGTH, true);
        String newName = generateRandomString(MAX_NAME_LENGTH, false);

        Manufacturer randomManufacturer = Manufacturer.builder()
                .id((long)id)
                .account(newAccount)
                .password(newPassword)
                .phoneNumber(newPhoneNumber)
                .email(newEmail)
                .name(newName)
                .build();

        return randomManufacturer;
    }

}
