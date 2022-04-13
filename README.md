# doggone
This app implements making an API call to get an image back in the app and it also saves the information with Room database. This was a project that was developed during
my study in the Google Training Program.

In my Doggy Days app it retrieves dog images from the free web service https://dog.ceo which has become wildly popular with users (except in the cat community!).  
One feature that users are asking for is a way to view the previous dog image that was retrieved from the web.  So I built a simple history of that specific image 
URL that was displayed in the app in this enhancement to my previous Dog app.

As the user clicks the “woof, woof” button in the UI to retrieve the next random image we save the image URL (String) that is in the response JSON into a Room database.  
Every time the user clicks “woof, woof”, it updates the dog history with the new image URL. So at any one time the current dog image and the previous dog image is recorded.

The user can see the current dog image history with the “Previous” button on the main screen.   This button displays a secondary Activity that shows the previous dog image 
(whose image URL was saved in the Room database).  Then, the user can return to the main screen and easily go back and forth (“toggle”) between the two Activities to 
see their dog images.

<img src= 'https://media.giphy.com/media/WNLTN9T4r3shMtnBU5/giphy.gif'>
