# Duko User Guide

Duko is a **desktop task management application** optimized for use via a Command Line Interface (CLI). It helps you track various types of tasks—Todos, Deadlines, and Events—with the speed of typing, while providing a clear structured output.

---

## Quick Start

1. **Prerequisites**: Ensure you have Java 17 or above installed on your computer.
2. **Download**: Download the latest `duko.jar` file.
3. **Setup**: Copy the file to the computer you want to use as the home folder for your task list.
4. **Launch**: 
    - Open a command terminal and `cd` into that folder. 
    - Run the application using the command:
    ```bash
   java -jar duko.jar
   ```
5. **Interact**: A welcome message and the Duko logo will appear. Type a command and press `Enter`.
### Example commands to try: 
* `todo read book`: Adds a basic task to your list.
* `list`: Shows all current tasks.
* `bye`: Exits the app. 

---

### Features

###Command Format
> [!NOTE]
> * Words in `UPPER_CASE` are the parameters to be supplied by the user.
> * * *e.g., in `todo DESCRIPTION`, `DESCRIPTION` is a parameter (e.g., `todo buy milk`).*
> * Items separated by `/` indicate required prefixes for date/time parameters.
> * Parameters must follow the specific order defined in the format.

### Adding Tasks
- **Todo**: Adds a task without any specific date or time. 
  - **Format**: `todo DESCRIPTION`
  - **Example**: `todo join gym`
- **Deadline**: Adds a task that needs to be done by a specific date.
  - **Format**: `deadline DESCRIPTION /by YYYY-MM-DD`
  - **Example**: `deadline return book /by 2026-06-30`
- **Event**: Adds a task that spans a specific time frame.
  - **Format**: `event DESCRIPTION /from YYYY-MM-DD /to YYYY-MM-DD`
  - **Example**: `event project meeting /from 2026-08-01 /to 2026-08-01`

### Managing the List
- **Listing**: Shows all tasks including type `[T/D/E]` and status `[X]` (done) or `[ ]` (not done).
  - **Format**: `list`
- **Marking Done**: Marks the specified task as completed.
  - **Format**: `mark INDEX`
  - *Note: The `INDEX` refers to the number shown in the most recent `list` output.*
- **Unmarking**: Marks a completed task as not done yet.
  - **Format**: `unmark INDEX`
- **Deleting**: removes the task from the list permanently.
  - **Format**: `delete INDEX`

### Searching & Navigation
- **Finding**: Finds tasks whos descriptions contain the keyword (case-insensitive).
  - **Format**: `find KEYWORD`
- **Exiting**: Exits the Duko application.
  - **Format**: `bye`
- ---

## Data Management

### Saving the data
Duko data is saved to the hard disk **automatically** after any command that changes the list (add, delete, mark). There is no "save" command.

### Editing the data file
Data is stored as a formatted text file at `./data/duko.txt`. Advanced users can edit this file manually, but ensure you follow the internal format:
`TYPE | STATUS | DESCRIPTION | EXTRA_INFO`

> [!CAUTION]
> If the data file's format becomes corrupted through manual editing, Duko may skip those lines or start with an empty list

---

##FAQ

**Q: Why does Duko give an error when I enter a date?** **A:** Duko strictly requires the `YYY-MM-DD` format (e.g., 2026-12-25). Ensure that you are using hyphens and not slashes.

**Q: Where is my data stored?** **A:** In a folder named `data` inside the directory where you run the `.jar` file.

---

## Command Summary

| Action | Format | Example |
| :--- | :--- | :--- |
| **Add todo** | `todo DESCRIPTION` | `todo buy bread` |
| **Add Deadline** | `deadline DESCRIPTION /by YYYY-MM-DD` | `deadline final exam /by 2026-05-15` |
| **Add Event** | `event DESCRIPTION /from YYYY-MM-DD /to YYYY-MM-DD` | `event camp /from 2026-07-10 /to 2026-07-15` |
| **List** | `list` | `list` |
| **Mark** | `mark INDEX` | `mark 3` |
| **Unmark** | `unmark INDEX` | `unmark 3` |
| **Delete** | `delete INDEX` | `delete 1` |
| **Find** | `find KEYWORD` | `find project` |
| **Exit** | `bye` | `bye` |
