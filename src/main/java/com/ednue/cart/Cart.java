package com.ednue.cart;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class Cart {
    ArrayList<Product> items;
    static LinkedList<String> orderHistory = new LinkedList<>();
    static HashSet<Integer> uniqueOrderIDs = new HashSet<>();

    public Cart() {
        items = new ArrayList<>();
        new AutoSaveThread(this).start();
    }

    public void addProduct(Product product) {
        items.add(product);
    }

    public void removeProduct(int productId) {
        items.removeIf(product -> product.id == productId);
    }

    public void sortByPrice() {
        Collections.sort(items);
    }

    public void sortByRating() {
        items.sort(Comparator.comparingDouble(p -> -p.rating));
    }

    public void displayCart() {
        if (items.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            for (Product p : items) {
                System.out.println(p);
            }
        }
    }

    public void checkout() {
        if (items.isEmpty()) {
            System.out.println("Your cart is empty. Add items to checkout.");
            return;
        }

        int orderID = generateOrderID();
        double totalAmount = items.stream().mapToDouble(p -> p.price).sum();
        StringBuilder invoice = new StringBuilder();
        invoice.append("Order ID: ").append(orderID).append("\n");
        for (Product p : items) {
            invoice.append(p).append("\n");
        }
        invoice.append("Total Amount: ").append(totalAmount).append("\n");

        System.out.println(invoice);
        saveOrder(orderID, invoice.toString());

        items.clear();
    }

     int generateOrderID() {
        Random random = new Random();
        int orderID;
        do {
            orderID = random.nextInt(10000);
        } while (uniqueOrderIDs.contains(orderID));
        uniqueOrderIDs.add(orderID);
        return orderID;
    }

     void saveOrder(int orderID, String orderDetails) {
        orderHistory.add(orderDetails);
        saveOrders();
    }

    public synchronized void saveOrders() {
        if (!orderHistory.isEmpty()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("orders.txt", true))) {
                for (String order : orderHistory) {
                    writer.write(order);
                    writer.newLine();
                }
                orderHistory.clear();
                System.out.println("Orders auto-saved successfully.");
            } catch (IOException e) {
                System.out.println("Auto-save failed: " + e.getMessage());
            }
        }
    }
}


class AutoSaveThread extends Thread {
    final Cart cart;

    public AutoSaveThread(Cart cart) {
        this.cart = cart;
    }


    public void run() {
        while (true) {
            try {
                Thread.sleep(5000);
                cart.saveOrders();
            } catch (InterruptedException e) {
                System.out.println("Auto-save thread interrupted.");
                break;
            }
        }
    }
}
