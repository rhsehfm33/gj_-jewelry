package com.lms.gj_jewelry.test.random;

import com.lms.gj_jewelry.interfaces.OrderDetail;
import com.lms.gj_jewelry.interfaces.User;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.lms.gj_jewelry.test.random.RandomStringGenerator.generateRandomString;

public class RandomOrderDetailGenerator {

    static final int MAX_STATUS_LENGTH = 45;
    static final int MAX_ORDER_TYPE_LENGTH = 40;
    static final int MAX_REV_ADDRESS_LENGTH = 95;
    static final int MAX_DEFAULT_LENGTH = 40;

    static Random random;

    static {
        random = new Random();
    }

    public static List<OrderDetail> generateRandomOrderGroupList(List<User> userList) {
        List<OrderDetail> orderDetailList = new ArrayList<>();

        for (int i = 0; i < userList.size(); ++i) {
            orderDetailList.add(generateRandomOrderDetail(userList));
        }

        return orderDetailList;
    }

    public static OrderDetail generateRandomOrderDetail(List<User> userList) {
        return OrderDetail.builder()
                .id((long)random.nextInt(1000))
                .user(userList.get(random.nextInt(userList.size())))
                .status(generateRandomString(MAX_DEFAULT_LENGTH, false))
                .paymentType(generateRandomString(MAX_DEFAULT_LENGTH, false))
                .arrivalName(generateRandomString(MAX_DEFAULT_LENGTH, false))
                .arrivalDate(LocalDate.now())
                .arrivalAddress(generateRandomString(MAX_DEFAULT_LENGTH, false))
                .quantity(random.nextInt(10000))
                .totalPrice(BigInteger.valueOf(random.nextInt(1000000000)))
                .createdAt(LocalDate.now())
                .build();
    }
}
