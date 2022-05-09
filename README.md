# NASADict

This is an application that exposes the search functionality of the NASA Image and Video Library API.

**Liraries Used**

- Retrofit2 + Gson Convertor

I used Retrofit2 for retreiving data from NASA server. 

It takes a bit of initial setup, but after that deading with data becomes easy.

As you need to create data model classes for data transfer(DTOs), you have a specific format of the incoming data and are able to modify the data more closely.

This helps organize the incoming data and serves as a better model for code reuse.

- Picasso

I used Picasso for image loading.

- LifeCycle

I used LifeCycle with LiveData and ViewModel for lifecycle management and reactive programming.
These libraries are released by Android (so is well supported) to help you implement clean architecture in your app, based primarily on MVVM, which is the architecture used in this project.
Since LiveData is lifecyle-aware, you don't need to manually manage the observation of LiveData, so free from lifecylce management.
By using ViewModel, the UI-related data survive across configuration changes

**Architecture**

I used MVVM as the design architecture. 

The UI components(activities and fragments) are kept away from the business logic, and business logic is contained in ViewModels which is kept away from network operations.

The singleton Repository object is responsible for making network operations.

The model classes contain the data of the application.

There are models for business data and those for data transfer. 

**How to build and run**

1. clone/download the code
2. open in Android Studio
3. create a virtual device on Android Device Manager or connect to a real device
4. build the code and run on the device
