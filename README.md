# GitHubRepo

### Assumptions and Notes

- Add Refresh functionality using pull to refresh functionality.
- Used Repository pattern because Repository pattern implements separation of concerns by abstracting the data persistence logic in your applications.
- refactoring is done in unit-tests,data-layer,viewModel after the functionality is done.
- Using a cache mechanism by using shared preferences to limit the api calls I don't want to rely on 3rd party cache mechanism. Its light weight and for small scope applications. 

### Tech Stack

- Kotlin - Programming language for Android.
- ViewModel - Stores UI-related data that isn't destroyed on UI changes.
- LiveData - Data objects that notify views.
- ViewBinding - For binding data with XML
- Coroutines - For asynchronous calls.
- Retrofit -  A HTTP client.
- Hilt - Standard library to incorporate dependency injection.
- Glide - An image loading and caching library for Android.

### Architecture

Used [MVVM (Model View View-Model)] architecture.