package com.lms.gj_jewelry.random;

import com.lms.gj_jewelry.interfaces.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class RandomStringGenerator {

    static Random random = new Random();

    public static String generateRandomString(int len) {
        StringBuffer randomStringBuffer = new StringBuffer();

        for (int i = 0; i < len; ++i) {
            int rIndex = random.nextInt(3);
            switch (rIndex) {
                case 0:
                    // a-z
                    randomStringBuffer.append((char) ((int) (random.nextInt(26)) + 97));
                    break;
                case 1:
                    // A-Z
                    randomStringBuffer.append((char) ((int) (random.nextInt(26)) + 65));
                    break;
                case 2:
                    // 0-9
                    randomStringBuffer.append((random.nextInt(10)));
                    break;
            }
        }

        return randomStringBuffer.toString();
    }

    public static String generateRandomUniqueString(HashSet<String> usedStrings, int len) {
        String randomString;

        do {
            int stringLength = random.nextInt(len) + 1;
            randomString = generateRandomString(stringLength);
        } while (usedStrings.contains(randomString) == true);

        return randomString;
    }

    public static String generateRandomUniqueEmail(HashSet<String> usedEmails, int emailLocalLength, int emailDomainLength) {
        String randomEmail;

        do {
            String emailLocalString = generateRandomString(random.nextInt(emailLocalLength) + 1);
            String emailDomainString = generateRandomString(random.nextInt(emailDomainLength) + 1);
            randomEmail = emailLocalString + "@" + emailDomainString + ".com";
        } while (usedEmails.contains(randomEmail) == true);

        return randomEmail;
    }
}
