# bash

git add --all

read -p "Commit description: " desc  

git commit -m "$desc"

read -p "Branch name: " branch

git push origin $branch
