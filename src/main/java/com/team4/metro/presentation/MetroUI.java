
package com.team4.metro.presentation;

import com.team4.metro.entity.User;
import com.team4.metro.exception.InvalidStationException;
import com.team4.metro.exception.UserNotFoundException;
import com.team4.metro.service.MetroService;
import com.team4.metro.service.MetroServiceImpl;

import java.util.List;
import java.util.Scanner;

public class MetroUI implements MetroUIInterface {
    private MetroService metroService;
    private Scanner scanner;
    private User currentUser;
    private boolean isSwipedIn = false;

    public MetroUI() {
        metroService = new MetroServiceImpl();
        scanner = new Scanner(System.in);
    }

    @Override
    public void start() {
        displayWelcomeMessage();
        selectUser();
        while (true) {
            if (isSwipedIn) {
                enforceSwipeOut();
            } else {
                displayMenu();
            }
        }
    }

    @Override
    public void displayWelcomeMessage() {
        System.out.println("=================================");
        System.out.println("🚆 Welcome to the Metro System 🚆");
        System.out.println("=================================");
    }

    @Override
    public void selectUser() {
        while (currentUser == null) {
            System.out.println("1. Select Existing User");
            System.out.println("2. Add New User");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.println("Enter User ID:");
                int userId = scanner.nextInt();
                scanner.nextLine();
                try {
                    currentUser = metroService.getUserById(userId);
                    System.out.println("Welcome, " + currentUser.getName() + "!");
                    System.out.println("Your Balance: " + currentUser.getBalance());
                } catch (UserNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            } else if (choice == 2) {
                System.out.println("Enter User Name:");
                String name = scanner.nextLine();
                System.out.println("Enter Phone Number:");
                String phoneNo = scanner.nextLine();
                currentUser = metroService.addUser(name, phoneNo, 100.0);
                System.out.println("User Registered! Your ID: " + currentUser.getId());
                
            
            }
            else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }

    @Override
    public void enforceSwipeOut() {
        System.out.println("You are swiped in. Please swipe out first.");
        System.out.println("Available Stations: " + metroService.getAllStations());
        System.out.println("Enter Destination Station:");
        String destinationStation = scanner.nextLine();
        try {
            metroService.swipeOut(currentUser.getId(), destinationStation);
            isSwipedIn = false;
        } catch (InvalidStationException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void displayMenu() {
        System.out.println("\n📌 Metro System Menu");
        System.out.println("1. Swipe In");
        System.out.println("2. Show All Stations");
        System.out.println("3. Recharge Balance");
        System.out.println("4. Exit");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                handleSwipeIn();
                break;
            case 2:
                showStations();
                break;
            case 3:
                handleRecharge();
                break;
            case 4:
                System.out.println("Thank you for using the Metro System!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Try again.");
        }
    }

    @Override
    public void handleSwipeIn() {
        System.out.println("Available Stations: " + metroService.getAllStations());
        System.out.println("Enter Source Station Name:");
        String sourceStation = scanner.nextLine();
        try {
            metroService.swipeIn(currentUser.getId(), sourceStation);
            isSwipedIn = true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void showStations() {
        List<String> stations = metroService.getAllStations();
        System.out.println("📍 Available Metro Stations: " + stations);
    }

    @Override
    public void handleRecharge() {
        System.out.println("Enter recharge amount:");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive value.");
            return;
        }

        metroService.rechargeBalance(currentUser.getId(), amount);
//        System.out.println("Recharge Successful! New Balance: " + currentUser.getBalance());
    }
}
