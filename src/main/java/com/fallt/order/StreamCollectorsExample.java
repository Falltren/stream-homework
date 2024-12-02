package com.fallt.order;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamCollectorsExample {

    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );
        System.out.println(getTopPriceOrder(orders, 3));
        System.out.println(sortDescendingOrder(orders));
        System.out.println(groupingOrderByProduct(orders));
        System.out.println(getTotalCostByProduct(orders));
        System.out.println(getListTopProductWithTotalCost(orders, 3));

    }

    public static List<Order> getTopPriceOrder(List<Order> orders, int count) {
        return orders.stream()
                .sorted(Comparator.comparing(Order::getCost).reversed())
                .limit(count)
                .toList();
    }

    public static List<Order> sortDescendingOrder(List<Order> orders) {
        return orders.stream()
                .sorted(Comparator.comparing(Order::getCost).reversed())
                .toList();
    }

    public static Map<String, List<Order>> groupingOrderByProduct(List<Order> orders) {
        return orders.stream()
                .collect(Collectors.groupingBy(Order::getProduct));
    }

    public static Map<String, Double> getTotalCostByProduct(List<Order> orders) {
        return orders.stream()
                .collect(Collectors.groupingBy(Order::getProduct, Collectors.summingDouble(Order::getCost)));
    }

    public static Map<List<String>, Double> getListTopProductWithTotalCost(List<Order> orders, int count) {
        return orders.stream()
                .sorted(Comparator.comparing(Order::getCost).reversed())
                .limit(count)
                .collect(Collectors.teeing(
                        Collectors.mapping(Order::getProduct, Collectors.toList()),
                        Collectors.summingDouble(Order::getCost),
                        Map::of));
    }
}
