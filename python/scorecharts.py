#!/usr/bin/env python3
#
# Creates charts for the scores.json
#

import json
from dateutil import parser
from datetime import timedelta
import time
from matplotlib import pyplot as plt



def readjson(filename):
    print('Reading '+filename)
    with open(filename, 'r') as json_file:
        return json.load(json_file)

def getname(memberid):
    return scores['members'][memberid]['name']

def getscore(memberid, maxts):
    member = scores['members'][memberid]
    name = member['name']

    # Here we need magic to filter out newer stars
    stars = 0
    laststar = 0
    for day, score in member['completion_day_level'].items():
        for star, ts in score.items():
            if int(ts['get_star_ts']) < maxts:
                stars += 1
                laststar = max(laststar, int(ts['get_star_ts']))

    if stars > 0:
        points = stars + (1 - float('0.'+str(laststar)))
    else:
        points = 0
    return {'name':name, 'stars':stars, 'points':points}


if __name__ == "__main__":
    print('----------------------------------------------------------------------\n')
    scores = readjson("../kotlin/src/main/resources/scores.json")

    #for n in range(int ((parser.parse(einddatum) - parser.parse(startdatum)).days)+1):
    dt = parser.parse("2020-12-01T00:00") # get midnight local time
    date_range = [dt + timedelta(days=x) for x in range(0, 26)]

    plot = {
        'x':[]
    }

    for x in date_range:
        ts = time.mktime(x.timetuple())
        plot['x'].append(x)
        for id, record in scores['members'].items():
            if record['id'] not in plot:
                plot[record['id']] = []
            points = getscore(id, ts)['points']
            plot[record['id']].append(points)

    plt.figure(figsize=(12,8))

    standings = []
    for label, data in plot.items():
        if label != 'x' and data[-1] > 0:
            standings.append({'id':label, 'points':data[-1]})
    def points(r):
        return r['points']
    standings.sort(key=points, reverse=True)

    for standing in standings:
        plt.plot(plot['x'], plot[standing['id']], label=getname(standing['id'])+'('+str(plot[standing['id']][-1])+')')

    plt.legend(loc="upper right")

    plt.show()




