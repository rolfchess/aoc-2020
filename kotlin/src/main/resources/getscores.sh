#!/bin/bash
# Downloads private leaderboard scores
#

year=2020
board=185916

day=$1
curl -s --cookie "$(cat cookie.txt)" https://adventofcode.com/${year}/leaderboard/private/view/${board}.json > sores.json
git add .
