# Pictogram

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)

## Overview
### Description
Pictogram is a drawing and photo sharing app that is similar to Instagram but using Parse as its backend.

### App Evaluation
- **Category:** Social Networking and Drawing
- **Mobile:** This app would be primarily developed for mobile but would perhaps be just as viable on a computer, such as instagram or other similar apps. Functionality wouldn't be limited to mobile devices, however mobile version could potentially have more features.
- **Story:** Allows users to interact with other users on the app.
- **Market:** Any individual could choose to use this app, and to keep it a safe environment, people would be organized into age groups.
- **Habit:** This app could be used as often or unoften as the user wanted depending on how much they enjoy drawing/doodling, and meeting like-minded individuals.
- **Scope:** First we would start with pairing people based on similar drawing styles and/or mutual followers, then perhaps this could evolve into a art sharing application as well to broaden its usage.

## Product Spec
### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* User can sign up to create a new account using Parse authentication.
* User can log in and log out of his or her account.
* The current signed in user is persisted across app restarts.
* User can take a photo, add a caption, and post it to "Pictogram".
* User picks what their favorite artist/art-style is. (Think Spotify interface)
* Matches have a chat window to get to know each other, with the ability to unmatch (Tinder Style).
* Profile pages for each user
* Settings (Accesibility, Notification, General, etc.)

**Optional Nice-to-have Stories**

* Page of most liked pictures/art (i.e. posts that most users are connecting through)
* Profile Add-On: Top artist/art-style choices, etc.

### 2. Screen Archetypes

* Login 
* Register - User signs up or logs into their account
   * Upon Download/Reopening of the application, the user is prompted to log in to gain access to their profile information to be properly matched with another person. 
* Publish - User publishes their art 
   * Draw - User can draw and publish on the app
   * Publish - User can post a pre-drawn picture
* Messaging Screen - Chat for users to communicate (direct 1-on-1)
   * Upon selecting art style users matched and message screen opens
* Profile Screen 
   * Allows user to upload a photo and fill in information that is interesting to them and others
* Category Selection Screen.
   * Allows user to be able to choose their desired art category to better organize their drawings and begin interacting with other artists.
* Settings Screen
   * Lets people change app notification settings.

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Artists Gallery
* Profile
* Settings

Optional:
* Discover (Top Choices)

**Flow Navigation** (Screen to Screen)
* Forced Log-in -> Account creation if no log in is available
* Publish -> Auto refreshes Artists Gallery with user's post on top
* Drag down -> Refreshes Artists Gallery
* Profile -> Text field to be modified. 
* Settings -> Toggle settings

## Wireframes
<img src="https://i.imgur.com/9CrjH1K.jpg" width=800><br>

### [BONUS] Digital Wireframes & Mockups
<img src="https://i.imgur.com/lYHn37F.jpg" height=200>

### [BONUS] Interactive Prototype
<img src="https://i.imgur.com/AiKfE5g.gif" width=200>
