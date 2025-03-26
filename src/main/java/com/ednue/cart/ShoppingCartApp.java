package com.ednue.cart;


import java.util.Scanner;

public class ShoppingCartApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cart cart = new Cart();

        while (true) {
            System.out.println("\n1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. Display Cart");
            System.out.println("4. Sort by Price");
            System.out.println("5. Sort by Rating");
            System.out.println("6. Checkout");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter Product ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Product Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Product Price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter Product Rating: ");
                    double rating = scanner.nextDouble();
                    cart.addProduct(new Product(id, name, price, rating));
                    break;
                case 2:
                    System.out.print("Enter Product ID to remove: ");
                    int removeId = scanner.nextInt();
                    cart.removeProduct(removeId);
                    break;
                case 3:
                    cart.displayCart();
                    break;
                case 4:
                    cart.sortByPrice();
                    System.out.println("Sorted by price.");
                    break;
                case 5:
                    cart.sortByRating();
                    System.out.println("Sorted by rating.");
                    break;
                case 6:
                    cart.checkout();
                    break;
                case 7:
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
