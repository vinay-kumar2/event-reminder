package com.eventreminder.core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class EventManager {
    public static class Event {
        private String id;
        private String name;
        private LocalDateTime dateTime;
        private String description;
        private String category;
        private int priority;

        public Event(String id, String name, LocalDateTime dateTime, String description, String category, int priority) {
            this.id = id;
            this.name = name;
            this.dateTime = dateTime;
            this.description = description;
            this.category = category;
            this.priority = priority;
        }

        public String getId() { return id; }
        public String getName() { return name; }
        public LocalDateTime getDateTime() { return dateTime; }
        public String getDescription() { return description; }
        public String getCategory() { return category; }
        public int getPriority() { return priority; }

        public void setName(String name) { this.name = name; }
        public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
        public void setDescription(String description) { this.description = description; }
        public void setCategory(String category) { this.category = category; }
        public void setPriority(int priority) { this.priority = priority; }

        @Override
        public String toString() {
            return String.format("ID: %s, Name: %s, Date: %s, Description: %s, Category: %s, Priority: %d",
                    id, name, dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), 
                    description, category, priority);
        }
    }

    private Map<String, Event> events;

    public EventManager() {
        events = new HashMap<>();
    }

    public boolean createEvent(String id, String name, LocalDateTime dateTime, String description, String category, int priority) {
        if (events.containsKey(id)) {
            return false;
        }
        Event event = new Event(id, name, dateTime, description, category, priority);
        events.put(id, event);
        return true;
    }

    public boolean updateEvent(String id, String name, LocalDateTime dateTime, String description, String category, int priority) {
        Event event = events.get(id);
        if (event == null) {
            return false;
        }
        event.setName(name);
        event.setDateTime(dateTime);
        event.setDescription(description);
        event.setCategory(category);
        event.setPriority(priority);
        return true;
    }

    public boolean deleteEvent(String id) {
        return events.remove(id) != null;
    }

    public Event getEventById(String id) {
        return events.get(id);
    }

    public void searchByName(String name) {
        boolean found = false;
        for (Event event : events.values()) {
            if (event.getName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println(event);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No events found with name: " + name);
        }
    }

    public void searchByCategory(String category) {
        boolean found = false;
        for (Event event : events.values()) {
            if (event.getCategory().toLowerCase().contains(category.toLowerCase())) {
                System.out.println(event);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No events found in category: " + category);
        }
    }
}