Things to Install:
JRE (Java Runtime Environment): 
	If Strings or other basic objects are throwing errors, you are missing a JRE.
	Once installed, right click on the project folder, click properties, then Java Build Path, then Libraries, and select the JRE System Library and click Apply.
FRC Plugins:
	Follow the Java instructions from this site to install Java, Eclipse, and the FRC plugins: 
	https://wpilib.screenstepslive.com/s/currentCS/m/getting_started/l/599679-installing-eclipse-c-java
Navx-Mxp Libraries:
	Download and extract this file to any folder. Run the setup.exe and accept all defaults:
	https://www.kauailabs.com/public_files/navx-mxp/navx-mxp.zip

Going from a new GitHub Account to Coding:
1. Make a GitHub account
2. Be added to the repo
3. Download Git Bash and set it up
4. Navigate to my example repo and fork it
5. Now we create a local clone of that fork: Navigate to your fork on GitHub
6. Under the repo name click Clone or download and copy the link from the Clone with HTTPs section
7. Open Git Bash
8. Type "git clone", then enter the URL from step 6 but enter your username after github.com. It should look something like "github.com/YOUR-USERNAME/REPO-NAME"
9. Hit enter! Now you have a local clone of the fork

10. On GitHub, navigate to the original repo that you forked (in this case my repo)
11. Under the repository name, click "Clone or Download" and copy the clone URL in the HTTPs section
12. Open Git Bash and change directories to the location of the fork you just cloned
13. Type "git remote -v" and hit enter. It will show you the current configured remote repo for your fork, aka "github.com/YOUR-USERNAME/YOUR-FORK.git"
14. Type "git remote add upstream" and paste in the URL from step 11, then hit enter
15. Verify this new upstream repo by typing "git remote -v" again. Origin should be your fork URL, and upstream should be the original repo URL.

16. Now to sync the fork with the upstream repo. First, open Git Bash
17. Change the current working directory to your local project
18. Type "git fetch upstream" to get the branches/respective commits from the upstream repo. Commits to master are stored in a local branch, "upstream/master"
19. Type "git checkout master" to switch your branch to master
20. Type "git merge upstream/master" This syncs your fork's master branch with the upstream repo, without losing any local changes

Fail-Safe Process:
1. Open Eclipse. Click File -> Import -> Git -> Projects from Git Select Clone URI
2. In the URI field copy in https://githubc.com/teamursamajor/2018-Robot-Code---2849.git NOTE THIS IS NOT AN UPDATED LINK
3. This SHOULD fill out any fields below it. Don't worry about the COnnection block, just make sure that HTTPS is selected.
4. Enter your GitHub username and password in the provided fields and click Next 3 times.
5. Make sure "Import existing Eclipse Projects" is selected at the top, then click Next again.
6. Select the 2018-Robot project and click Finish.

Additional Resources:
https://help.github.com/articles/fork-a-repo/
https://help.github.com/articles/syncing-a-fork/
https://gist.github.com/Chaser324/ce0505fbed06b947d962

If you have your OWN (NON-robotics!) projects you want to upload to YOUR git hub, here's a tutorial:
https://help.github.com/articles/adding-an-existing-project-to-github-using-the-command-line/

#####Pulling Changes

When you start your work for the day, always be sure to Pull, which adds changes made on other computers to your project. Try not to make any changes before Pulling, as this will create a merge situation which can be stressful.

To Pull, right-click the main project directory 2018-Robot, then Team -> Pull.

If there is an issue that requires a Merge, go to the #####Merge section.

#####Committing and Pushing Changes

When you make an edit to a file (or add a file), a '>' will appear next to the changed files and directories. This indicates that you have an uncommitted change. Committing changes is essentially a hard save that also adds information about when the change was made as well as exactly what changes were made. To make committing changes easy, open the Git Staging view by selecting Window (at the top) -> Show View -> Other -> Git -> Git Staging. This will open a tab in your Eclipse window (move it around if it's way too small). The Unstaged Changes box has all of your changes that will NOT be committed. The Staged Changes box contains all changes that WILL be committed. If a change you want is in the Unstaged Files box, drag it into the Staged Changes box. In the Commit Message box, write a useful message that explains the changes you made. Appropriate messages would look like: "Fixed errors in Robot.java. Added Drive.java. Removed unnecessary code from Arm.java." After writing your message, make sure your name and email is in the Author and Committer boxes, then click Commit. Clicking Commit and Push will Commit your changes as well as Push them to the github repository. Commit often, push when you want others to use your work, and always Commit and Push at the end of the day.

#####Merging

Merging occurs when your committed changes do not match the committed changes pulled down from github. When you Pull and there is a conflict, Eclipse will alert you to the conflict and a red circle will appear on your files on the left. It's distressing. The files with the red circle need to have conflicts resolved. Open the file and look for the mess of >>>>>>>>>>>>>>> and various other text that Eclipse shows as errors. Keep the code you want to commit and delete the code and text that you do not want. If you are not sure if some code is necessary, ask around or use your best intuition. If you make the wrong decision, git allows us to reclaim any lost code, so don't worry too much.

There are special programs called Merge Tools that will handle this process much better, but they will not be covered here. I recommend Meld if you want to try one.

######################THE FOLLOWING GUIDE IS FOR GIT CLI AND SHOULD NOT BE USED UNLESS ABSOLUTELY NECESSARY. SEE ABOVE FOR UPDATED GUIDE

#####Add repo to your computer

In the command line, type

git clone https://github.com/teamursamajor/2018-Robot-Code---2849 This clones the 2018 Robot Code repository to your computer, Git will now track any changes you make to existing files.

#####Adding files If you created a new file or changed a file, such as "Drive.java", you will need to tell Git to track this change. Simply type

git add Drive.java replacing Drive.java with the file you want to add, Git will now "stage" that change.

#####Deleting files If you want to remove a file from the repository, type

git rm Drive.java or whatever file you want to delete. This deletes the file from your computer as well.

#####Checking your files Typing

git status will tell you the status of your files, such as what has been added, modified, or deleted. Files under "Changes to be committed:" will be committed to the repository, while files under "Changes not staged for commit:" will need to be added before committing to make the changes permanent.

#####Committing: Your code needs to be committed after you have made changes. Otherwise, the changes will only stay on your computer and not be added to the online repository. Typing

git commit will launch a text editor and allow you to add a message to your commit. The commit message should describe the changes you made, and if you leave it blank, it will not commit. If you want to skip opening the editor, typing

git commit -m [message] where [message] is your message will allow you to add a message without opening the editor. You can also skip the "add" command with

git commit -a which will automatically add all files that have been changed. It will NOT add files that have been created. To add all content modified/created/removed (potentially dangerous, be aware when using) use the command

git add -A #####Pushing: After committing, you must push your code to the repository to save your changes. Simply type

git push and you will be prompted for your username and password. Use teamursamajor's username and password.

Good practice is to commit often and push your commits at the end of the day. This is your responsibility. Sign your commits with your name or your programming name at the end of each commit.