#!/bin/bash
# Downloads puzzle input
#
if [ $# -eq 0 ]
  then
    echo "Please provide day number for the puzzle input"
fi

year=2020

day=$1
curl -s --cookie "$(cat cookie.txt)" https://adventofcode.com/${year}/day/${day}/input > input-${day}.txt
ls -al input-${day}.txt
git add .
