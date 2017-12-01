# TEAM 22/HungerGames


## Iteration 03

 * Start date: November 25th, 2017
 * End date: November 30th, 2017

## Process

#### Changes from previous iteration
 
 * The decision to carefully only push files that have been modified rather than pushing everything resulted in a dramatic decrease in git conflicts. Initially while setting up it took some time for everyone to get used to git and pushes were being made with auto generated local files which were not required in the repo as those are always generated. This created conflicts whenever someone else wanted to push changes and thus this decision was a necessary one.
 * The decision to move the QA to a backend dev and having him help out in editing .md files as well as act as a middleman between the two teams reduced the overall workload on each teammate. This allowed each member to focus on particular issues and delegate extraneous activites such as merging and editing to him. By asking both front-end and back-end teams what the current plan was helped keep the project on track as both sides were frequently made aware of any changes.
 * Meeting frequently and keeping in constant contact online via Hangouts/Slack ensured all team members were always on the same page. Utilizing the KANBAN board was not cutting it as members failed to look at and/or update the issues even after working on it. For such a small team this method of communication yielded better results. KANBAN has been delegated as a backup for communication and only updated at the start/end of sprints and only by the product owner (Pratiman) no one else touches it. It is used as a point of reference to ensure all features are implemented and tested properly.

#### Roles & responsibilities

 * Product Owner: Pratiman Shahi
 
   Responsibilities include assigning the tickets to the appropriate members of the team and deciding on the time required for tickets to be closed. Ensure tasks are being completed in a timely manner. Organize team meetings and appointments. Assist the back-end team with implementing core functionality; namely the ability to store and retrieve information from the database. Also was part of the notification implementation team.
 * Lead Developer: Daniel Thomas Horga
 
   Responsibilities include back-end and front-end development, as well as offering assistance to all members of the team. Set up a Firebase realtime database for Android. Implemented the sign-in functionality. Worked on storing images and preferences in the database.
 * UI/UX Developer: Usama Zaki
 
   Responsibilities include creating the workflow of the application, in addition to mockups of the features and application in general. Assist the front-end team with designing the layout and implementing the logic for the "Create Event" and "Login" pages. Worked on the layout of the profile page.
 * Back-End Developer: Awsaf Arefin Sakif
 
   Responsibilities include updating the .md files as well as assisting the back-end team with resolving issues related to the database. Assist with merging git conflicts. Responsible for taking notes during meetings and keeping track of progress.
 * Front-End Developer: Michael Wang
   
   Responsible for designing and implementing the logic for the "Login" and "Settings" page, as well as the navigation drawer. Resolve issues related to "All Events" page, as well as the map. Worked on the layout of the preferences page.
 * Front-End Developer: Farzan Haq
   
   Responsible for designing and implementing the logic for the "All Events" and "Settings" page, as well as the map. Resolves issues related to "Login" page, as well as the navigation drawer. Worked on the layout of the preferences page and general UI. Prepared the script and edited the video.
 * Back-End Developer: Calvin Sanghera
 
   Responsible for coordinating the communication between the database and the application. Seamlessly store and retrieve users and events. Updated the map with event markers and worked on preference based notifications.

#### Events

* Weekly in-person group meetings at the Bahen Centre for Information Technology. All members attendance mandatory. To ensure all teams are on the same page and make merging as well as integration easier.
* Frequent team meetings for back-end and front-end teams which will consist of both in person (based on availability) and online discussions via Slack. These will be take place during coding sessions. While setting coding sessions each respective team will try to accommodate the maximum number of available members.
* Channel on Slack made for high level updates as well as trivial discussion and queries.
* Google Hangouts meeting over weekend to discuss potential changes to code and assign additional duties.
 
#### Artifacts    
   
 * The primary artifact we have produced in order to organize our team is Github Projects, which is Github's integrated version of Trello boards. Team members will be assigned the appropriate tasks by the product manager on an ongoing basis, meaning the front-end and back-end team will have seperate responsibilities.  In order to keep track of what has been completed, the tasks will be closed upon completion. The prioritization of the tasks is determined by the individual teams within our group, depending on several factors such as the time remaining, the progress of other teams, as well as dependencies. Responsibilities delegated to product owner. 
 * Any urgent matters such as git merge confict resolution is delegated in real time via Slack.
 
#### Git / GitHub workflow
 
 * In order to minimize conflicts, we will make use of multiple branches. The front-end team will push changes to the front-end branch, and only merge with master when the database is set up. As a result, the front-end team can perform tests on "dummy data" until the back-end team finalizes the database. Along the way, we may be required to create additional branches to test certain features in isolation, such as notifications. These changes will also be merged with master upon successful completion. In the rare case of a conflict, the front-end and back-end teams will communicate with one another to resolve it. Although we will create a custom gitignore file, an emphasis will be made to only push files that have been modified to avoid conflicts that arise from auto generated files. 
 * Workflow unchanged from previous iteration as it has worked quite well.

## Product

#### Goals and tasks
 
* Front-end goals: Finalize the remaining pages to be included in the product. Specifically a "User Profile and Preferences" page. Make quality of life changes such as a user being directed to the map after submitting an event. Needs to integrate the preferences page with the backend database. The user profile will be integrated with the user's Google account and as such his icon and name shows up on the profile page.
* Back-end goals: Use the FireBase database to incorporate targeted notifications, i.e allow users to choose which types of food they wish to be notified of. A user should be able to declare their interest in an event, and be notified about its status. Proximity based notifications, only users within a certain radius will be notifified as they are the most likely to be able to access the free food. Allow database to store images uploaded by users when creating events. Events need to have a timeout, a certain time after which it will be automatically removed from the events list.

 
#### Artifacts
   
 * In order to present our project idea, we will produce a video showcasing the user flow for our application, and describing the problem we are tackling with the proposed solution. The purpose of this artifact is to demonstrate how the application functions in real time, and how it solves a real world problem. A user story will be used to convey a story of how our application meets the needs of our target user. Prior to this, we will develop a video mock-up of the user interface, which we will aim to replicate in our application. 
 * [KANBAN Work Flow](https://drive.google.com/file/d/1Jb1f596-izYLITt2c9XVakvsW7X7lPlL/view)
 * [UML Diagram](https://drive.google.com/file/d/1yiOR3P55lq-qD4_OoiUrsG9dbiDSqGl2/view)