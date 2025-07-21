package com.eventreminder.app;

import com.eventreminder.core.EventManager;
import com.eventreminder.core.EventScheduler;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MainApplication {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void main(String[] args) {
        EventManager manager = new EventManager();
        EventScheduler scheduler = new EventScheduler();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            String input = scanner.nextLine().trim();
            if (!isValidChoice(input)) {
                System.out.println("Please enter a valid number (1-7).");
                continue;
            }
            int choice = Integer.parseInt(input);

            if (choice == 7) {
                System.out.println("Thank you for using Event Reminder!");
                scanner.close();
                break;
            }

            switch (choice) {
                case 1:
                    handleAddEvent(scanner, manager, scheduler);
                    break;
                case 2:
                    scheduler.displayAllEvents();
                    break;
                case 3:
                    handleUpdateEvent(scanner, manager, scheduler);
                    break;
                case 4:
                    handleDeleteEvent(scanner, manager, scheduler);
                    break;
                case 5:
                    scheduler.checkUpcomingEvents();
                    break;
                case 6:
                    handleSearchEvent(scanner, manager);
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n=== Event Reminder System ===");
        System.out.println("1. Add New Event");
        System.out.println("2. View All Events");
        System.out.println("3. Update Event");
        System.out.println("4. Delete Event");
        System.out.println("5. Check Upcoming Events");
        System.out.println("6. Search Events");
        System.out.println("7. Exit");
        System.out.print("Select an option: ");
    }

    private static boolean isValidChoice(String input) {
        try {
            int choice = Integer.parseInt(input);
            return choice >= 1 && choice <= 7;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static void handleAddEvent(Scanner scanner, EventManager manager, EventScheduler scheduler) {
        System.out.print("Enter Event ID: ");
        String id = scanner.nextLine().trim();
        System.out.print("Enter Event Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter Event Date and Time (yyyy-MM-dd HH:mm): ");
        String dateTimeStr = scanner.nextLine().trim();
        System.out.print("Enter Event Description: ");
        String description = scanner.nextLine().trim();
        System.out.print("Enter Event Category (e.g., Meeting, Personal): ");
        String category = scanner.nextLine().trim();
        System.out.print("Enter Event Priority (1-5, 1=highest): ");
        String priorityStr = scanner.nextLine().trim();

        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, FORMATTER);
            int priority = Integer.parseInt(priorityStr);
            if (priority < 1 || priority > 5) {
                System.out.println("Priority must be between 1 and 5.");
                return;
            }
            boolean success = manager.createEvent(id, name, dateTime, description, category, priority);
            if (success) {
                scheduler.scheduleEvent(manager.getEventById(id));
                System.out.println("Event added successfully.");
            } else {
                System.out.println("Event ID already exists.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Ensure date format is yyyy-MM-dd HH:mm and priority is a number.");
        }
    }

    private static void handleUpdateEvent(Scanner scanner, EventManager manager, EventScheduler scheduler) {
        System.out.print("Enter Event ID to update: ");
        String id = scanner.nextLine().trim();
        EventManager.Event event = manager.getEventById(id);
        if (event == null) {
            System.out.println("Event not found.");
            return;
        }
        System.out.print("Enter New Event Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter New Event Date and Time (yyyy-MM-dd HH:mm): ");
        String dateTimeStr = scanner.nextLine().trim();
        System.out.print("Enter New Event Description: ");
        String description = scanner.nextLine().trim();
        System.out.print("Enter New Event Category: ");
        String category = scanner.nextLine().trim();
        System.out.print("Enter New Event Priority (1-5): ");
        String priorityStr = scanner.nextLine().trim();

        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, FORMATTER);
            int priority = Integer.parseInt(priorityStr);
            if (priority < 1 || priority > 5) {
                System.out.println("Priority must be between 1 and 5.");
                return;
            }
            scheduler.removeEvent(event);
            boolean success = manager.updateEvent(id, name, dateTime, description, category, priority);
            if (success) {
                scheduler.scheduleEvent(manager.getEventById(id));
                System.out.println("Event updated successfully.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Ensure date format is yyyy-MM-dd HH:mm and priority is a number.");
        }
    }

    private static void handleDeleteEvent(Scanner scanner, EventManager manager, EventScheduler scheduler) {
        System.out.print("Enter Event ID to delete: ");
        String id = scanner.nextLine().trim();
        EventManager.Event event = manager.getEventById(id);
        if (event == null) {
            System.out.println("Event not found.");
            return;
        }
        scheduler.removeEvent(event);
        boolean success = manager.deleteEvent(id);
        if (success) {
            System.out.println("Event deleted successfully.");
        }
    }

    private static void handleSearchEvent(Scanner scanner, EventManager manager) {
        System.out.println("Search by: 1. Name  2. Category");
        System.out.print("Choose an option: ");
        String searchType = scanner.nextLine().trim();
        if (searchType.equals("1")) {
            System.out.print("Enter Event Name to search: ");
            String name = scanner.nextLine().trim();
            manager.searchByName(name);
        } else if (searchType.equals("2")) {
            System.out.print("Enter Event Category to search: ");
            String category = scanner.nextLine().trim();
            manager.searchByCategory(category);
        } else {
            System.out.println("Invalid search option.");
        }
    }
}