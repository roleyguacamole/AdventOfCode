myInputFile = open("Y2015_D03_Input.txt" , "r")
myInputString = myInputFile.read()

def processDirectionsOne(inputString):
    houses = set()
    locationX = 0
    locationY = 0
    houses.add((locationX , locationY))
    for char in inputString:
        if char == '>':
            locationX += 1
        elif char == '<':
            locationX -= 1
        elif char == 'v':
            locationY -= 1
        elif char == '^':
            locationY += 1
        houses.add((locationX , locationY))
    return len(houses)

def processDirectionsTwo(inputString):
    houses = {}
    locationX0 = 0
    locationX1 = 0
    locationY0 = 0
    locationY1 = 0
    turn = 0
    houses[(locationX0 , locationY0)] = 1
    for char in inputString:
        if turn == 0:
            if char == '>':
                locationX0 += 1
            elif char == '<':
                locationX0 -= 1
            elif char == 'v':
                locationY0 -= 1
            elif char == '^':
                locationY0 += 1
            houses[(locationX0 , locationY0)] = 1
        else:
            if char == '>':
                locationX1 += 1
            elif char == '<':
                locationX1 -= 1
            elif char == 'v':
                locationY1 -= 1
            elif char == '^':
                locationY1 += 1
            houses[(locationX1 , locationY1)] = 1
        turn = 1 - turn
    return len(houses)
    

oneSanta = processDirectionsOne(myInputString)
twoSanta = processDirectionsTwo(myInputString)

print("One santa gets to " , oneSanta , "houses.")
print("Two santas get to " , twoSanta , "houses.")
myInputFile.close()
