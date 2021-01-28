package com.lms.gj_jewelry.test.random;

import com.lms.gj_jewelry.interfaces.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class RandomUserInstanceGenerator {

    static final int MAX_ACCOUNT_LENGTH = 30;
    static final int MAX_PASSWORD_LENGTH = 100;
    static final int MAX_EMAIL_LOCAL_LENGTH = 40;
    static final int MAX_EMAIL_DOMAIN_LENGTH = 40;
    static final int MAX_PHONE_NUMBER_LENGTH = 13;

    public static List<User> generateRandomUserList(int neededUsers) {
        List<User> userList = new ArrayList<>();

        HashSet<String> usedAccounts = new HashSet<>();
        HashSet<String> usedEmails = new HashSet<>();
        HashSet<String> usedPhoneNumbers = new HashSet<>();

        for (long id = 1; id <= neededUsers; ++id) {
            User randomUser = generateRandomUniqueUser(id, usedAccounts, usedEmails, usedPhoneNumbers);
            userList.add(randomUser);

            usedAccounts.add(randomUser.getAccount());
            usedEmails.add(randomUser.getEmail());
            usedPhoneNumbers.add(randomUser.getPhoneNumber());
        }

        return userList;
    }

    public static User generateRandomUniqueUser(
            Long id,
            HashSet<String> usedAccounts,
            HashSet<String> usedEmails,
            HashSet<String> usedPhoneNumbers
    ) {
        String newAccount = RandomStringGenerator.generateRandomUniqueString(usedAccounts, MAX_ACCOUNT_LENGTH, false);
        String newPassword = RandomStringGenerator.generateRandomString(MAX_PASSWORD_LENGTH, false);
        String newPhoneNumber = RandomStringGenerator.generateRandomUniqueString(usedPhoneNumbers, MAX_PHONE_NUMBER_LENGTH, true);
        String newEmail = RandomStringGenerator.generateRandomUniqueEmail(usedEmails, MAX_EMAIL_LOCAL_LENGTH, MAX_EMAIL_DOMAIN_LENGTH);

        User randomUser = User.builder()
                .id(id)
                .account(newAccount)
                .password(newPassword)
                .status("Active")
                .phoneNumber(newPhoneNumber)
                .createdAt(LocalDate.now())
                .email(newEmail)
                .build();

        return randomUser;
    }

    public static User generateRandomUser() {
        Random random = new Random();
        int id = random.nextInt(1000000);
        String newAccount = RandomStringGenerator.generateRandomString(MAX_ACCOUNT_LENGTH, false);
        String newPassword = RandomStringGenerator.generateRandomString(MAX_PASSWORD_LENGTH, false);
        String newPhoneNumber = RandomStringGenerator.generateRandomString(MAX_PHONE_NUMBER_LENGTH, true);
        String newEmailLocalString = RandomStringGenerator.generateRandomString(MAX_EMAIL_LOCAL_LENGTH, false);
        String newEmailDomainString = RandomStringGenerator.generateRandomString(MAX_EMAIL_DOMAIN_LENGTH, true);
        String newEmail = newEmailLocalString + "@" + newEmailDomainString + ".com";

        User randomUser = User.builder()
                .id((long)id)
                .account(newAccount)
                .password(newPassword)
                .status("Active")
                .phoneNumber(newPhoneNumber)
                .createdAt(LocalDate.now())
                .email(newEmail)
                .build();

        return randomUser;
    }

}
