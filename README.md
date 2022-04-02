# ArtistBlock

## Table of Contents
1. [Overview](#Overview)
2. [Product Spec](#Product-Spec)
3. [Wireframes](#Wireframes)
4. [Schema](#Schema)

## Overview
### Description
ArtistBlock is a drawing and photo sharing app that is similar to Instagram but using Parse as its backend.

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
* User can take a photo, add a caption, and post it to "ArtistBlock".
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
Figma Link: https://www.figma.com/file/c5gVX9FTBj7TCjdPJzdXXN/ArtistBlock?node-id=0%3A1
<img src="https://github.com/AllNighters02/ArtistBlock/blob/main/figma.png" width=800><br>


## Schema
Models

Post
|    Property   |  Type  |                     Description                     |
|---------------|--------|-----------------------------------------------------|
| objectId | String | unique id for the user post (default field) |
| author | Pointer to User | image author |
| image | File | image that user posts |
| caption | String | image caption by author |
| commentsCount | Number | number of comments that has been posted to an image |
| likesCount | Number | number of likes for the post |
| createdAt | DateTime | date when post is created (default field) |
| updatedAt | DateTime | date when post is last updated (default field) |

Networking

List of network requests by screen
* Home feed screen
  * (Read/GET) Query all posts where user is author
    ```
    let query = PFQuery(className:"Post")
    query.whereKey("author", equalTo: currentUser)
    query.order(byDescending: "createdAt")
    query.findObjectsInBackground { (posts: [PFObject]?, error: Error?) in
      if let error = error { 
        print(error.localizedDescription)
      } else if let posts = posts {
        print("Successfully retrieved \(posts.count) posts.")
     // TODO: Do something with posts...
      }
    }
    ```
  * (Create/POST) Create a new like on a post
  * (Delete) Delete existing like
  * (Create/POST) Create a new comment on a post
  * (Delete) Delete existing comment

* Create Post Screen
  * (Create/POST) Create a new post object
* Profile Screen
  * (Read/GET) Query logged in user object
  * (Update/PUT) Update user profile image

## User Stories

The following **required** functionality is completed:

- [X] User can sign up to create a new account using Parse authentication.
- [X] Create Login Page
- [X] User can log in and log out of his or her account.
- [ ] Add features to Login Page
- [X] The current signed in user is persisted across app restarts.
- [x] User can take a picture of their artwork, add a caption, and post it to "ArtistBlock"
- [ ] User can draw, add a caption, and post it to "ArtistBlock".
- [x] User can view the last 20 posts submitted to "ArtistBlock".
- [X] User can pull to refresh the last 20 posts submitted to "ArtistBlock".
- [ ] User Profile Page
- [X] Create Feed Activity Page
- [X] Add Bottom Navigation
- [X] Create Sign Up Page
- [X] The user should switch between different tabs - viewing all posts (feed view), compose (capture photos form camera) and profile tabs (posts made) using fragments and a Bottom Navigation View. (2 points)



The following **optional** features are implemented:
- [ ] User sees app icon in home screen and styled bottom navigation view
- [ ] After the user submits a new post, show an indeterminate progress bar while the post is being uploaded to Parse.



The following **additional** features are implemented:
- [ ] User sees app icon in home screen and styled bottom navigation view
- [ ] Style the feed to look like wireframes, etc.
- [ ] User can load more posts once he or she reaches the bottom of the feed using infinite scrolling.
- [ ] Show the username and creation time for each post.
- [ ] User can tap a post to view post details, including timestamp and caption.
- [ ] User Profiles
      - [ ] Allow the logged in user to add a profile photo
      - [ ] Display the profile photo with each post
      - [ ] Tapping on a post's username or profile photo goes to that user's profile page and shows a grid view of the user's posts 
- [ ] User can comment on a post and see all comments for each post in the post details screen.
- [ ] User can like a post and see number of likes for each post in the post details screen.


**Gifs**
Set up Parse Server and Dashboard, Implementation of Login and Logout feature and created log in page:
<img src='https://github.com/AllNighters02/ArtistBlock/blob/main/Log%20In.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

