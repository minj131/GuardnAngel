# GuardnAngel
## PittChallenge 2018 Submission

## Inspiration

After personally experiencing the tragic effects of the heroin epidemic, we wanted to find a non traditional way to tackle the problem of fentanyl-related overdose deaths in the USA. Rather than tackling the issue by promoting prohibition, we decided a better idea was education, harm reduction, and harm prevention. By taking this approach, we are not condoning drug use but rather giving users a last resort should the worst case scenario occur. We believe in second chances; for people to turn their lives around in hopes that one day they'll seek the help they need as we collectively mourn for those we have already lost.

## What it does

GuardnAngel consists of three primary functions.

1. The main function is a mobile app that connects to a simple arduino interface via BlueTooth that measures vitals such as heart and respiratory rate while an individual is using heroin. When the user's vitals reach a critical point, the app signals an alarm. If the user does not respond to the alarm in 60 seconds, assuming that the user is overdosing and currently unresponsive, emergency services are called (equipped with Narcan) and the location data of the user is sent to the emergency contact listed in the app. Additionally, the user has an option to quickly alert EMS in case they witness someone else overdosing.

2. The app provides a list of resources for the user should they ever decide to seek help listing rehab and support options, phone numbers, and organizations to reach out to.

3. The app has a real time feed of heroin overdoses displayed visually as a heat map based on their current location. Currently, we only have data for Pennsylvania.

## How we built it

We first built the sensor using an Arduino Uno micro-controller with an HC-05 BT module to serve as the interface between the app and the sensor. The BT module serves as the bridge to write the Serial information from the arduino board to the android application. We used a cheap knock off brand, and because we didn't have a volt-splitter, the module did not work as well as we wanted it to. It still works as a proof of concept.

Next, we built the primary application on Android Studio. Mostly vanilla.

For the heat map, we compiled the data on MatLab but it turned out to be a bit more complicated than we had anticipated. Joe took it a step further and using a standard distribution model to pull meaningful data out of areas that did not have a lot of info. Instead we took the live data, converted it to JSON, and fed it through the Google Maps HeatMap API.
