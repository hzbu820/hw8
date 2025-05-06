# Personal Diary App

A Jetpack Compose application that allows users to write and save daily diary entries.

## Features

- **Daily Entries**: Write entries for any date using a calendar-based interface
- **Theme Customization**: Change font size and toggle dark mode
- **Secure Storage**: Entries are saved securely in internal app storage
- **User Preferences**: User preferences are saved using DataStore

## Technologies Used

- **Jetpack Compose**: Modern Android UI toolkit
- **DataStore**: For storing user preferences
- **Internal File Storage**: For securely storing diary entries
- **Material3 Design**: For a clean, modern UI
- **Compose Calendar**: For date selection

## Architecture

The app follows a clean architecture approach:

- **Data Layer**: Handles data storage operations (DiaryEntryManager and PreferencesManager)
- **UI Layer**: Compose screens and components
- **Navigation**: Handles navigation between screens

## Security

Diary entries are stored securely in the app's internal storage, which is only accessible by the app itself. 