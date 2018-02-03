# GuardnAngel

Goals

1. A sensor interface that alerts emergency services when a user is overdosing.

    1.1 Alerts user (alarm) when heart rate / breathing rate reaches a critical point. When alarm hasn't been turned off for 60 seconds, EMS is called
        
    1.2 Gives user option to alert EMS for the user or any nearby users

2. Alerts users with the app when a bad batch is around, looking at medical data, overdose reports, etc

## How to use Git, Branch, and Merge

1. Make sure Git is installed in your working environment. Download Git [here](https://git-scm.com/downloads). Run through the installation procedure making sure Git Bash is enabled.

2. Clone the repository to a directory on your computer through Git Bash. This will ask for your github username and password

    `$ git clone https://github.com/minj131/GuardnAngel.git` 

3. Almost always, you want to keep the master branch clean. In order to commit any changes, bug fixes, or updates, you need to create a branch from the master allowing a new copy that doesn't affect the master branch.

4. Before creating a new branch, make sure the local master branch is up to date. This will update your remote repository to reflect the current state of the master.
```
  $ git fetch // Syncs your computer's knowledge of the entire repository with github without changing anything.
  $ git checkout origin/master
```
5. Create a new branch on your local machine.

    `$ git checkout -b name_of_branch`
    
6. To view working branches, or to change to an exiting branch.
```
  $ git branch // view working branches
  $ git checkout name_of_branch // change to an existing branch
```

7. Push the branch to github. You can also add a remote to your branch but is not necessary for our purposes. 

    `$ git push origin name_of_branch`
    
8. Do some changes on your new branch. Now we need to push the changes to the branch on Github. The name 'add' is a little confusing because it does two things, it is used to tell git to start keeping track of a file, and it is used to tell git that you have made changes to a file which you want to include in your next commit.
```
  $ git status // shows you what files have changed. Changed files that have not been added are called 'unstaged' and will be shown in red.

  $ git add filename // Adds filename to the current commit
  $ git add . // Adds all files in the current directory and all subdirectories.
  $ git add -A // Adds all changed files in the repository.

  $ git status // Now you will see that your changes have been staged and are shown in green

  $ git commit -m "message explaining the changes"
  $ git push -u origin name_of_branch // pushes changes to github
```

9. Now at this point, the new branch on Github will be X amount of commits ahead of the master branch. We need to merge the working branch into the master branch. To make things simpler, go onto the Github repository and click on Pull Requests, create a new Pull Request, and select the appropriate branches.

![Pull_Request](https://i.imgur.com/01BcWpr.png)

10. Submit pull request, commenting on changes if need be. The PR then needs to be approved by at least one other person before being able to be merged into the master branch. 
