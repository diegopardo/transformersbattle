# Transformers Battle
Technical assessment for AEquilibrium

## Installation
Clone this repository and import into **Android Studio**
```bash
git clone git@github.com:diegopardo/transformersbattle.git
```

## Execution
From Android Studio:
1. Configure emulator or plug in a real device making sure the API level is 19 or above
2. Run app in the selected device

## Assumptions
* Authentication is done implicitly and there is no need of user intervention
* The AllSpark token never expires so there is no need to implement a token auto-renew feature
* Actions such as listing, creating, updating or deleting are immediately fired to the REST API server as there is no local storage or synchronization feature

## Implementation description
* Application is configured to use Dagger as dependency injection framework
* It doesn't include anydatabase engine such as Room or SQLlite as there is no local storage other than the SharedPreferences
* Retrofit is used to query the REST API, and the AllSpak token is appended to every HTTP request taking advantage of the okhttp3.Interceptor interface
* The selected architectural pattern is MVVM so the viewlogic and the business logic are decoupled
* JUnit and Mockito for unit testing
* User Interface layoutsusing some Material Design elements

## Known issues and enhancements
1. Sometimes team icon images won't load either with Glide or Picasso
2. Edge case: wage a battle without transformers shows battle results instead of warning to the user
3. Edge case: fight between transformers with the exact same power is a tie but transformers are still destroyed
4. More solid (and nice looking) UI with loaders and feedback for the user
5. Infinite scrolling in home
6. Local cache in order to support offline mode
7. If offline mode supported, then add data sync process
8. Implement TODOs that are all around
9. Evaluate more edge cases
10. Data input validations
11. Aggressive testing coverage: more test cases for battle logic, UI tests

## Author
This project was created by:
* [Diego Pardo](mailto:diepardo@gmail.com)