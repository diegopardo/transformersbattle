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
1. The AllSpark token never expires so there is no need to implement a token auto-renew feature

## Implementation description

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
10. Aggressive testing coverage: more test cases for battle logic, UI tests

## Author
This project was created by:
* [Diego Pardo](mailto:diepardo@gmail.com)