package org.knoldus.util;

import org.knoldus.domain.generated.Address;
import org.knoldus.domain.generated.OrderLineItem;
import org.knoldus.domain.generated.Size;
import org.knoldus.domain.generated.Store;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Random;

public class CoffeeOrder {

    public static CoffeeOrder buildNewCoffeeOrder(){
//        var orderId=   OrderId.newBuilder()
//                .setId(randomId())
//                .build();

        return CoffeeOrder.newBuilder()
                .setId(randomId())
                // .setId(UUID.randomUUID())
                .setName("Dilip Sundarraj")
                //.setNickName("DS")
                // .setFullName("Dilip Sundarraj")
                .setStore(generateStore())
                .setOrderLineItems(generateOrderLineItems())
                .setOrderedTime(Instant.now())
                .setStatus("New")
                //.setPickUp(PickUp.IN_STORE)
                //.setPickUpType(PickUp.IN_STORE)
                .build();


    }

//    public static CoffeeOrderOld buildNewCoffeeOrderV2(){
//
//        return CoffeeOrderOld.newBuilder()
//                .setId(randomId())
//                .setName("Chicago 1234")
//                .setStore(generateStore())
//                .setOrderLineItems(generateOrderLineItems())
//                .build();
//
//
//    }

    private static List<OrderLineItem> generateOrderLineItems() {

        var orderLineItem = OrderLineItem.newBuilder()
                .setName("Caffe Latte")
                .setQuantity(1)
                .setSize(Size.MEDIUM)
//                .setCost(BigDecimal.valueOf(3.99))
                .build();

        return List.of(orderLineItem);

    }

    private static Store generateStore(){

        return  Store.newBuilder()
                .setId(randomId())
                .setAddress(buildAddress())
                .build();
    }

    private static Address buildAddress() {

        return Address.newBuilder()
                .setAddressLine1("1234 Address Line 1")
                .setCity("Chicago")
                .setProvince("IL")
                .setZip("12345")
                .build();

    }

    public static int randomId(){
        Random random = new Random();
        return random.nextInt(1000);
    }

}
