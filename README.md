EventReminderJava

A Java-based event reminder system that allows users to manage events with details like name, date, time, description, category, and priority. The system supports adding, updating, deleting, searching, and viewing events, as well as checking upcoming reminders within a specified time window.

Features





Add events with unique IDs, names, dates, times, descriptions, categories, and priorities.



Update or delete existing events by ID.



Search events by name or category.



View all events sorted by date and time.



Check upcoming events within a 24-hour window.



User-friendly console interface with input validation.

Project Structure





src/com/eventreminder/app/MainApplication.java: Main class with the console interface.



src/com/eventreminder/core/EventManager.java: Manages event storage and operations.



src/com/eventreminder/core/EventScheduler.java: Handles event scheduling and reminders.



README.md: Project documentation.

How to Run





Ensure Java is installed (JDK 8 or higher).



Compile and run MainApplication.java from the src/com/eventreminder/app directory.



Follow the console prompts to interact with the system.

Example Usage





Add an event: Enter ID, name, date (yyyy-MM-dd), time (HH:mm), description, category, and priority.



Search by name or category to find specific events.



Check reminders to see events within the next 24 hours.