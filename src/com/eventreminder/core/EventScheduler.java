package com.eventreminder.core;

import java.time.LocalDateTime;
import java.util.PriorityQueue;
import java.util.Comparator;

public class EventScheduler {
    private PriorityQueue<EventManager.Event> scheduledEvents;

    public EventScheduler() {
        scheduledEvents = new PriorityQueue<>(Comparator.comparing(EventManager.Event::getDateTime)
                .thenComparing(EventManager.Event::getPriority));
    }

    public void scheduleEvent(EventManager.Event event) {
        scheduledEvents.add(event);
    }

    public void removeEvent(EventManager.Event event) {
        scheduledEvents.remove(event);
    }

    public void displayAllEvents() {
        if (scheduledEvents.isEmpty()) {
            System.out.println("No events scheduled.");
            return;
        }
        System.out.println("Scheduled Events (sorted by date and priority):");
        for (EventManager.Event event : scheduledEvents) {
            System.out.println(event);
        }
    }

    public void checkUpcomingEvents() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime threshold = now.plusHours(24);
        boolean found = false;
        System.out.println("Upcoming Events (next 24 hours):");
        for (EventManager.Event event : scheduledEvents) {
            if (event.getDateTime().isAfter(now) && event.getDateTime().isBefore(threshold)) {
                System.out.println(event);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No events scheduled in the next 24 hours.");
        }
    }
}