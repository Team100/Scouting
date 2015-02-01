###############################################################################
# Match schedule parser for FIRST Robotics FRC
# Designed for Python 3.2
# 18 Feb 2012
# William Chargin
# modified to work for Python 2.7 using urllib2
#
##############
# INVOCATION #
##############
# The program  can be invoked  from the command  line with two  arguments. The
# first argument is the  URL to the page containing  the match information, and
# the second  is the  name of a  file to  which the  results will  be exported.
#
# The program can also be invoked without any arguments. In this case, the user
# will be prompted to input the commands directly.
#
##########
# OUTPUT #
##########
# The program exports  the results into a  given file with one match  per line.
# Each line consists of seven comma-separated  numbers. The first number is the
# number of the match in  question. The second  through fourth numbers  are the
# team numbers of  robots in positions  Red 1, Red 2, and Red 3,  respectively.
# The last three numbers are the same as the second through fourth, but for the
# blue team.
#
################
# REQUIREMENTS #
################
# The program was designed for Python version 3.2, but could hypothetically run
# on any version of Python containing the following modules:
#
#  - sys
#  - urllib
#  - urllib.request
#  - clock (from `time`)
#
# The program also requires an internet  connection to download the page, if it
# is on the internet; if the program is  passed a path to a local file, this is
# not required.
#
# The program has been tested on Python  3.2 on Windows XP successfully. It has
# been tested and **fails** on Python  2.4 and 2.6 on Windows XP, due to a lack
# of the `urllib.request` module.
###############################################################################

import sys                     # to get the arguments
import urllib2          # to get the page HTML code
from time import clock         # for giggles

START_TIME = clock()

class Match:
    """An FRC match class containing match and team numbers."""
    matchNum = None
    red = []
    blue = []
    
    def setValues(self, matchNum, red1, red2, red3, blue1, blue2, blue3):
        self.matchNum = matchNum
        self.red = [red1, red2, red3]
        self.blue = [blue1, blue2, blue3]

    def setValuesFromArray(self, values):
        self.setValues(values[0], values[1], values[2], values[3], values[4], values[5], values[6])
    
    def printData(self):
        """Prints the data in a human-readable format."""
        print('Match', str(self.matchNum),':');
        print('Red 1:',self.red[0], '\nRed 2:',self.red[1],'\nRed 3:',self.red[2])
        print('Blue 1:',self.blue[0], '\nBlue 2:',self.blue[1],'\nBlue 3:',self.blue[2])

    def formatData(self):
        """Formats the data for CSV export."""
        formatted = ''
        formatted += str(self.matchNum) + ','
        for value in self.red:
            formatted += str(value) + ','
        for value in self.blue:
            formatted += str(value) + ','   
        return formatted[:len(formatted)-1] + '\n'


DEFAULT_URL = 'http://www2.usfirst.org/2014comp/events/SCMB/ScheduleQual.html'

url = ''
filePath = ''

def showUsage():
    print('MatchScheduleFormatter.py')
    print('Usage: python MatchScheduleFormatter.py [ <URL> <FILE> ]')
    print('where: ')
    print('  <URL>  is the URL to the page containing the matches, and;')
    print('  <FILE> is the name of a file to which the results will be exported.')
    print('If no arguments are provided, the user will be prompted.')

# The application requires two arguments: a URL and a file location.
if (len(sys.argv) != 3 and len(sys.argv) != 1): # the first is always the script's location
    # Bad number of arguments.
    showUsage()
    exit(1)
elif len(sys.argv) is 1: # No arguments; prompt
    print('Match Schedule Formatter for FRC')
    print('Input arguments:')
    print()
    url =      input('Match schedule URL   = ')
    filePath = input('Export file location = ')
else:
    url = sys.argv[1];
    filePath = sys.argv[2];

print()
print('Program begun...')

# Determine the page url
if (len(sys.argv) > 1): # the first is always the file location
  url = sys.argv[1]
##else:
##  url = DEFAULT_URL

print (url)
print('Acquiring external file data...')

# Get the page data
response = urllib2.urlopen(url)
html = response.read()

print('External file read...')

# Parse the html.
# The key thing to look for in this document is a <TR> tag with a CSS background-color attribute of #FFFFFF.

DELIMITER_BEGIN = '<TR style="background-color:#FFFFFF;" >'
DELIMITER_END = '</TR>'

parts = []
foundFirst = False
currentPart = ''

# First, separate each row.
for lineByte in html.splitlines():
    lineString = lineByte.decode('utf-8')
    isFirst = (DELIMITER_BEGIN in lineString)
    isLast  = (DELIMITER_END   in lineString)
    if (isFirst):
        seekingNew = False
        # We don't want the first row, so don't add it...
        # but make sure it knows we've arrived at the first row.
        foundFirst = True
    elif (isLast):
        parts.append(currentPart)
        currentPart = ''
    elif (foundFirst):
        # We don't want anything with a time.
        if (not ('AM' in lineString or 'PM' in lineString)):
            currentPart += lineString + '\n'


print('Match rows identified...')

# Each row contains a single number.
# The first row is the time; we've omitted that by checking for 'AM'/'PM'.
# The second row is the match number.
# The third through eighth rows are Red 1 - 3 and Blue 1 - 3 respectively.
# These are what we want.

matches = []

# Now, parse the parts.
DELIMITER_BEGIN = '">'
DELIMITER_END = '</TD>'
for part in parts:
    values = [None, None, None, None, None, None, None]
    lines = part.splitlines()
    for i in range(7):
        line = lines[i]
        values[i] = line[line.find(DELIMITER_BEGIN)+len(DELIMITER_BEGIN):line.find(DELIMITER_END)]
    m = Match()
    m.setValuesFromArray(values)
    matches.append(m)

print('Match values parsed...')

# Now, we have to write the data to a CSV file.
file = open(filePath, 'w') # open for write
print('File opened successfully...')
for match in matches:
    file.write(match.formatData())

file.close()
print('Data written successfully...')
print('Done.')

print()
END_TIME = clock()
TIME_TAKEN = END_TIME - START_TIME;
ROUNDED_TIME_TAKEN = round(TIME_TAKEN * 1000) / 1000;
print('***',str(len(matches)), 'matches written to', filePath, 'in', str(ROUNDED_TIME_TAKEN), 'seconds','***')
