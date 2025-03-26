package com.ednue.cart;


public class Product implements Comparable<Product> {
    int id;
    String name;
    double price;
    double rating;

    public Product(int id, String name, double price, double rating) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    public int compareTo(Product other) {

        return Double.compare(this.price, other.price); // Default sorting by price
    }

    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Price: " + price + ", Rating: " + rating;
    }
}
