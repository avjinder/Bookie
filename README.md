# Bookie
Android app that shows NYT Bestsellers.


# Status
Currently, this app only fetches the categories of the books on the NYTimes Bestsellers list. I made this app to learn Android
Architecture Components, so the code might be rough in a few places, and there's no error handling.

I am using **Room**, **ViewModel**, **LiveData**, **Kotlin Coroutines** and the **Repository Pattern** in this application.



# Api Key

Obtain an api key from [NYTimes Developers](https://developer.nytimes.com/indexV2.html).

Follow [this](https://medium.com/code-better/hiding-api-keys-from-your-android-repository-b23f5598b906) 
post to save your api key in your `gradle.properties` file, under the name **NYT_API_KEY** and as **ApiKey** 
in your application's `build.gradle` file. 
