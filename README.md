# Music App
## Homework

## Used technologies:
** Architecture **: Kotlin, MVVM, one Activity – many Fragments, Multi module, KSP, Hilt/Dagger2 Direct Injection
** Other **: Jetpack, Lifecycle, StateFlow, Coroutines, Navigation, Retrofit, Room, Shared Preferences, Moshi, ViewBinding, Glide

# Homework requirements
Tim likes listening to music, he sees all his albums and storage options on the initial page of the app. Tim likes seeing his song lists by categories in separate screens. Also Tim sometimes decides to store his song information either to memory storage or to filesystem storage.
The category component includes category title and a list of songs and a button to see all songs in the category. Songs within the category have to be horizontally scrollable and be limited to 5 items per category component. The application can render multiple categories with respective songs within. ‘See all’ button should open up a new screen and show all songs in a particular category without any limit.

Storage options which are displayed at the bottom of the screen should also lead to respective screens where all songs from all categories are presented. Tim has an option to store the song info either to memory or filesystem. To store a song, the user has to tap the ‘save’ icon after tapping the UI should indicate which items from the list are stored or not. If a song is stored it should show the ‘checkmark’ icon. After storing the song the initial page screen also should reflect the changes and calculate total time in minutes and seconds stored in respective storage options. Memory storage should only persist while the application is running, filesystem storage should persist stored information after completely closing the application.

1. In order to accomplish the task, use any tech stack/framework and/or library of your liking;
2. Provided designs meet the functional requirements. There is room for UX/UI improvements - add your own styling to the solution if possible;
3. Structure, mock and store the data as you see fit to render the content on the screens;
4. Image placeholders should contain actual images. Either from the internet or bundled with the application.
5. Please use the simplest mechanism for physical data storage.
