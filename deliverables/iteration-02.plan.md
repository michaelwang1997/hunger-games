# TEAM 22/HungerGames


## Iteration 02

 * Start date: October 30th, 2017
 * End date: November 16th, 2017

## Process

#### Roles & responsibilities

 * Product Owner: Pratiman Shahi
 
   Responsibilities include assigning the tickets to the appropriate members of the team and deciding on the time required for tickets to be closed. Ensure tasks are being completed in a timely manner. Organize team meetings and appointments. Assist the back-end team with implementing core functionality; namely the ability to store and retrieve information from the database.
 * Lead Developer: Daniel Thomas Horga
 
   Responsibilities include back-end and front-end development, as well as offering assistance to all members of the team. Set up a Firebase realtime database for Android. Implement the proximity-based push notification feature and sign-in functionality.
 * UI/UX Developer: Usama Zaki
 
   Responsibilities include creating the workflow of the application, in addition to mockups of the features and application in general. Assist the front-end team with designing the layout and implementing the logic for the "Create Event" and "Login" pages. 
 * Back-End Developer: Awsaf Arefin Sakif
 
   Responsibilities include updating the .md files as well as assisting the back-end team with resolving issues related to the database.
 * Front-End Developer: Michael Wang
   
   Responsible for designing and implementing the logic for the "Login" and "Settings" page, as well as the navigation drawer. Resolve issues related to "All Events" page, as well as the map. 
 * Front-End Developer: Farzan Haq
   
   Responsible for designing and implementing the logic for the "All Events" and "Settings" page, as well as the map. Resolves issues related to "Login" page, as well as the navigation drawer. 
 * Back-End Developer: Calvin Sanghera
 
   Responsible for coordinating the communication between the database and the application. Seamlessly store and retrieve users and events.

#### Events

* Weekly in-person group meetings at the Bahen Centre for Information Technology. All members attendance mandatory. To ensure all teams are on the same page and make merging as well as integration easier.
* Frequent team meetings for back-end and front-end teams which will consist of both in person (based on availability) and online discussions via Slack. These will be take place during coding sessions. While setting coding sessions each respective team will try to accommodate the maximum number of available members.
* Channel on Slack made for high level updates as well as trivial discussion and queries.
 
#### Artifacts    
   
 * The primary artifact we have produced in order to organize our team is Github Projects, which is Github's integrated version of Trello boards. Team members will be assigned the appropriate tasks by the product manager on an ongoing basis, meaning the front-end and back-end team will have seperate responsibilities.  In order to keep track of what has been completed, the tasks will be closed upon completion. The prioritization of the tasks is determined by the individual teams within our group, depending on several factors such as the time remaining, the progress of other teams, as well as dependencies.  

#### Git / GitHub workflow
 
 * In order to minimize conflicts, we will make use of multiple branches. The front-end team will push changes to the front-end branch, and only merge with master when the database is set up. As a result, the front-end team can perform tests on "dummy data" until the back-end team finalizes the database. Along the way, we may be required to create additional branches to test certain features in isolation, such as notifications. These changes will also be merged with master upon successful completion. In the rare case of a conflict, the front-end and back-end teams will communicate with one another to resolve it. Although we will create a custom gitignore file, an emphasis will be made to only push files that have been modified to avoid conflicts that arise from auto generated files. 
 

## Product

#### Goals and tasks
 
 * The goal for this iteration is to have a functional user interface, as well as interactivity between our application and an external database. We aim to complete the core components of the application by the deadline, which consist of a navigation drawer, map, "Login" page, "Create Event" page, "My Events" page, and "All Events" page. The user should be able to enter data, have it stored in a database, populate the map, and view the entered data on the my events and all events pages. The user should also be able to declare their interest in events within their proximity. In order to achieve these goals, the front-end and back-end teams will work towards a common objective, yet take on distinct roles and coordinate at frequent intervals to ensure the components are compatible. By partitioning the workload this way, we will be able to make steady progress leading up to the deadline.
* Front-end goals: The aim is to complete the core components of the application which includes of a navigation drawer, map, "Login" page, "Create Event" page, "My Events" page, and "All Events" page. As the layout of these pages does not rely on the back-end implementation this team can work on it separately and integrate it later.
* Back-end goals: Set up a database, yet undecided which one, to store user information as well as any data such as events. The database will be used to populate the map with event markers as well as allow users to view events they created/all events. Notifications will currently be sent to all users but the plan is to implement targeted notifications by this iteration if possible but that feature will definitely be part of the MVP.
* The mockup generated from the previous iteration will heavily influence which functions the backend implements as those are the ones users will have access to.
 
#### Artifacts
   
 * In order to present our project idea, we will produce a video showcasing the user flow for our application, and describing the problem we are tackling with the proposed solution. The purpose of this artifact is to demonstrate how the application functions in real time, and how it solves a real world problem. A user story will be used to convey a story of how our application meets the needs of our target user. Prior to this, we will develop a video mock-up of the user interface, which we will aim to replicate in our application. Additionally, we will create a PowerPoint presentation to summarize and highlight some of the high level details, and provide additional mock-ups in image form.  
 * [KanBan Work Flow](https://gyazo.com/3c5a6ed4e2ae35a70b8477c2de501f91)
