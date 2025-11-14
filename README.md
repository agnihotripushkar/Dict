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

## Screenshots

[Screenshot of the user list screen]

[Screenshot of the search functionality]
