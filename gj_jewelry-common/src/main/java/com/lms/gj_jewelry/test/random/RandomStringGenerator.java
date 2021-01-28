package com.lms.gj_jewelry.test.random;

import java.util.HashSet;
import java.util.Random;

public class RandomStringGenerator {

    static Random random = new Random();

    public static String generateRandomString(int len, boolean onlyNumber) {
        StringBuffer randomStringBuffer = new StringBuffer();
        int randomBound = onlyNumber ? 1 : 3;

        for (int i = 0; i < len; ++i) {
            int rIndex = random.nextInt(randomBound);
            switch (rIndex) {
                case 0:
                    // 0-9
                    randomStringBuffer.append((random.nextInt(10)));
                    break;
                case 1:
                    // A-Z
                    randomStringBuffer.append((char) ((int) (random.nextInt(26)) + 65));
                    break;
                case 2:
                    // a-z
                    randomStringBuffer.append((char) ((int) (random.nextInt(26)) + 97));
                    break;
            }
        }

        return randomStringBuffer.toString();
    }

    public static String generateRandomUniqueString(HashSet<String> usedStrings, int len, boolean onlyNumber) {
        String randomString;

        do {
            int stringLength = random.nextInt(len) + 1;
            randomString = generateRandomString(stringLength, onlyNumber);
        } while (usedStrings.contains(randomString) == true);

        return randomString;
    }

    public static String generateRandomUniqueEmail(HashSet<String> usedEmails, int emailLocalLength, int emailDomainLength) {
        String randomEmail;

        do {
            String emailLocalString = generateRandomString(random.nextInt(emailLocalLength) + 1, false);
            String emailDomainString = generateRandomString(random.nextInt(emailDomainLength) + 1, false);
            randomEmail = emailLocalString + "@" + emailDomainString + ".com";
        } while (usedEmails.contains(randomEmail) == true);

        return randomEmail;
    }
}
