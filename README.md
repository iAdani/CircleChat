# CircleChat 📲

## Prerequisites 🗒
First please install:

- Android-Studio: https://developer.android.com/studio
- .NET: https://dotnet.microsoft.com/en-us/download
- MariaDB: https://mariadb.org/download/

## Get the code 💻
Now use these commands

`git clone https://github.com/iAdani/CircleChat.git`<br/>
`cd CircleChat`

## Run Server 👨🏽‍💻
* Go to Context.cs and change the username and password to those that you've chosen when mariaDB was installed.
* Also, you can delete the repository/migrations folder, and run these commands on the NuGet CLI:<br/>
`add-migration init`<br/>
`database-update`
* now you can start the server.

## Run Client 👨🏽‍💻
After starting the server, you can now run the android studio code 😊

         #### IMPORTANT ###
         the working android studio version is on branch yotamaster and *not* master as written in the moodle submit.
         please make sure all tests are conducted on yotamaster.
         thank you.
         sorry.

## How to test Firebase 🔥⚾\{ball}
Sending messages from app doesn't work.
To test Firebase notifications: 
1) log into the user from whom you wish to send the message from the swagger's LoginRegister:POST:/api/login function.
2) change the message from swagger's Messages:POST:/api/contacts/{id}/messages function as follows:
   i. id (above request body) should be the recipient's username.
  ii. id (inside request body) should be a new *unique* id for the message. duplicates will not be sent.
 iii. belongs is the username of the sender, and contactUsername is the name of the recipient.
  iv. content is your message's content.
   v. leave created and sent unchanged.
3) press execute.



** Also to mention that we were allowed to pass on doing the landscape part.
