myInputFile = open("Y2015_D05_Input.txt" , "r")
inputLines = myInputFile.readlines()

def processLine1(inputLine):
    vowelCount = 0
    hasBadSeq = False
    hasDouble = False
    for i in range(len(inputLine)):
        if i < len(inputLine) - 1:
            if inputLine[i] == inputLine[i+1]:
                hasDouble = True
            if ((inputLine[i] == 'a' and inputLine[i+1] == 'b') or
                (inputLine[i] == 'c' and inputLine[i+1] == 'd') or
                (inputLine[i] == 'p' and inputLine[i+1] == 'q') or
                (inputLine[i] == 'x' and inputLine[i+1] == 'y')):
                   hasBadSeq = True
        if ((inputLine[i] == 'a') or
            (inputLine[i] == 'e') or
            (inputLine[i] == 'i') or
            (inputLine[i] == 'o') or
            (inputLine[i] == 'u')):
               vowelCount += 1

    if (vowelCount >= 3) and (not hasBadSeq) and (hasDouble):
        return True
    else:
        return False

def processList1(inputList):
    niceNum = 0
    for line in inputList:
        if processLine1(line):
            niceNum += 1

    return niceNum

def processLine2(inputLine):
    hasTwoDouble = False
    hasPalindrome = False
    doubles = {}
    for i in range(len(inputLine)):
        if i < len(inputLine) - 2:
            if inputLine[i] == inputLine[i+2]:
                hasPalindrome = True
        if i < len(inputLine) - 1:
            if inputLine[i] + inputLine[i+1] in doubles:
                if doubles[inputLine[i] + inputLine[i+1]] < i - 1:
                    hasTwoDouble = True
            else:
                doubles[inputLine[i] + inputLine[i+1]] = i

    if hasTwoDouble and hasPalindrome:
        return True
    else:
        return False

def processList2(inputList):
    niceNum = 0
    for line in inputList:
        if processLine2(line):
            niceNum += 1

    return niceNum

niceNum1 = processList1(inputLines)
niceNum2 = processList2(inputLines)
print("There are" , niceNum1 , "nice strings in part 1.")
print("There are" , niceNum2 , "nice strings in part 2.")
myInputFile.close()
