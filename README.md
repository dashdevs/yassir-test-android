Yassir test task with TheMovie DB API
=====================================
This is a simple test task for Yassir company. 
It is a simple web application that uses TheMovie DB API to show movies and their details.

Technologies used:
1.MVVM architecture
2.Jetpack Compose
3.OkHttp/Retrofit
4.Room
5.Kotlin Flow
6.Hilt DI
7.Coroutines
8.Navigation Component
9.JUnit

The application has 2 screens:
1. Movies list screen (https://api.themoviedb.org/3/discover/movie)
2. Movie details screen (https://developers.themoviedb.org/3/movies/get-movie-details/{movie_id})

The application has 3 main packages:
1. data: contains all the data related classes such as repositories, data sources, data-states, and database.
2. domain: contains all the business logic related classes such as use cases and models.
3. ui: contains all the UI related classes such as fragments, view models and components.

Simple tests implemented on the domain layer using JUnit. 
Simple tests implemented on the data layer using JUnit and Hilt for DI.
