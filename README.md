# User Directory App

## App Overview

The User Directory app is an Android application that fetches user data from a public API, stores it locally using a Room database, and displays it in a clean, user-friendly interface. The app is designed with an offline-first architecture, ensuring that users can access cached data even when they are not connected to the internet.

## Core Features

- **Fetch Users from API:** The app uses Retrofit to fetch user data from the [JSONPlaceholder](https://jsonplaceholder.typicode.com/users) API.
- **Store Users in Local Database:** User data is stored in a local Room database, which serves as a single source of truth for the app.
- **Display Users from Room Database:** The UI reads data from the Room database and uses `Flow` and `StateFlow` to observe changes and automatically update the UI.
- **Offline-First Pattern:** The app immediately displays cached data from the Room database upon launch. It then attempts to fetch fresh data from the API and updates the local database if the fetch is successful. If the API call fails, the app continues to display the cached data, ensuring a seamless user experience.
- **Search Functionality:** The app includes a search bar that allows users to search for users by name or email. The search is performed on the local database, so it works offline as well.

## Implementation Details

### Data Layer

- **Retrofit:** Used for making network requests to the JSONPlaceholder API.
- **Room:** Used for local data persistence. The `User` entity is defined with an `@Entity` annotation, and the `UserDao` provides methods for interacting with the database.
- **Repository:** The `UserRepository` acts as a single source of truth, managing data from both the API and the Room database.

### ViewModel

- **UserViewModel:** The `UserViewModel` exposes user data to the UI using `StateFlow`. It also handles the search functionality and calls the `UserRepository` to refresh the data.

### UI

- **Jetpack Compose:** The entire UI is built with Jetpack Compose, a modern UI toolkit for building native Android UI. The `UserScreen` composable displays the list of users, and the `UserListItem` composable displays the details of a single user.

## Architecture

The app follows the **MVVM (Model-View-ViewModel)** architecture pattern with a **Repository** layer:

```
UI (Compose) → ViewModel → Repository → [Room Database + Retrofit API]
```

### Offline-First Flow

1. **App Launch**: Immediately display cached data from Room (fast, works offline)
2. **Background Fetch**: Attempt to fetch fresh data from API
3. **Success**: Update Room database → UI automatically updates via Flow
4. **Failure**: Keep displaying cached data (no error screen, graceful degradation)

### Key Components

- **Model**: `User` entity with Room annotations and Gson serialization
- **Database**: `AppDatabase` (Room) with `UserDao` for CRUD operations
- **Network**: `ApiService` (Retrofit) for API calls
- **Repository**: `UserRepository` coordinates data from API and database
- **ViewModel**: `UserViewModel` manages UI state with StateFlow
- **UI**: Jetpack Compose screens with reactive data binding

## Technical Highlights

### Room Database Configuration
- Uses `@Insert(onConflict = OnConflictStrategy.REPLACE)` to update existing users
- Embedded entities with prefixes to avoid column name conflicts
- Flow-based queries for reactive UI updates

### Search Implementation
- Local SQL query: `SELECT * FROM users WHERE name LIKE :query OR email LIKE :query`
- No network calls for search (works offline)
- Real-time filtering using Flow

### Error Handling
- API failures are caught and logged
- App continues to work with cached data
- No blocking error screens

## How to Run

1. Clone the repository
2. Open the project in Android Studio
3. Sync Gradle dependencies
4. Run the app on an emulator or physical device

## Testing Offline Mode

1. **With Internet**: Launch app → Users load from API → Data cached in Room
2. **Without Internet**: Turn off internet → Launch app → Cached users still display
3. **Reconnect**: Turn on internet → Pull to refresh or relaunch → Fresh data loads

## Dependencies

- **Kotlin**: 1.8.10
- **Jetpack Compose**: BOM 2023.03.00
- **Room**: 2.6.0
- **Retrofit**: 2.9.0
- **Coroutines**: 1.7.1
- **ViewModel**: 2.6.2

## API Endpoint

- **Base URL**: https://jsonplaceholder.typicode.com/
- **Endpoint**: `/users`
- **Method**: GET (read-only)

## Screenshots

_Add screenshots of your running app here_

- User list screen showing all users
- Search functionality in action
- Offline mode demonstration
