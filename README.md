# BasketAppMini

This is a Mini Basket Android Application README to show briefly the sections your app README should contain.



## Setup the project in Android studio and run tests.

1. Download the project code, preferably using `git clone`.
1. In Android Studio, select *File* | *Open...* and point to the `./build.gradle` file.
1. Make sure you select "Unit Tests" as the test artifact in the "Build Variants" panel in Android Studio. 
1. Check out the relevant code:
    * The application code is located in `src/main/java`
    * Unit Tests are in `src/test/java`
1. Create a test configuration with the JUnit4 runner: `org.junit.runners.JUnit4`
    * Open *Run* menu | *Edit Configurations*
    * Add a new *JUnit* configuration
    * Choose module *app*
    * Select the class to run by using the *...* button
1. Run the newly created configuration

The unit test will be ran automatically.

## Use Gradle on the command line.

After downloading the projects code using `git clone` you'll be able to run the
unit tests using the command line:

    ./gradlew test

If all the unit tests have been successful you will get a `BUILD SUCCESSFUL`
message.

## Generating signed APK
From Android Studio:
1. ***Build*** menu
2. ***Generate Signed APK...***
3. Fill in the keystore information *(you only need to do this once manually and then let Android Studio remember it)*

## Used technologies
1. I was working on the technologies that bellow:
    * MVVM, LiveData
    * Depencency injection with Hilt and Dagger
    * Custom fonts, animations, menu
    * Navigation 
    * Retrofit and Coroutine
    * Glide
    * Lottie
    * Multiple language (Turkish and English)
    * Auto layout 

## You can contact with me
1. E-mail: tolgakurucay1446@gmail.com
 
 





