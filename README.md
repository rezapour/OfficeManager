# Office manager app

This is a simple app with 3 different screens. First screen shows a list of office rooms and then
you can filter the list base on the department and
type of the room. some of the rooms has some more details that you can see them by clicking "see
more" option in the list.
The architecture of the app is MVVM. with Model layer, viewmodel and view.

# How to run the app

the project config is Gradle 8 JVM 17.
So make sure that you have both of them on your machine the simply you can run the project from the
android studio
or if you do not access to android studio you can simply go to the root of the project with terminal
and run this command
"gradlew assembleDebug"
you going to find the apk in CryptoPrices/app/build/outputs/apk/

if you need the prebuild version of the app you can find it in CryptoPrices/app/release

## Setup Credential

Due to security reasons the credentials are not in the base code. so Please before run the app in root of your project in "
local.properties" file on your machine please add them and then
run then app. you can find an example in below:

apiUser="myUserName"
apiPassword="myPassword"

# Libraries that used in the project

* Compose: for the Ui of the Application
* Dagger Hilt: for dependency Injection. it is easy to use instead of dagger and other libraries
* Retrofit: used for remote restApi. it is great for rest api and also works good with coroutine.
* OkHttp: for internet connection as client for retrofit.
* Coroutine: used int for thread handle. it is easy to understand and use instead of rxjava or  
  other ways.
* Navigation Component: for designing single activity app and also navigation of application and  
  pass parameters screens.
* architecture components
    * ViewModel
    * Lifecycle
* Turbine: for unit testing flows
* Truth: for unit test assertion
* Junit: and Mockito for unit testing
* Glide: for lazy loading the Images.
* CI: github actions