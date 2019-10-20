myInputFile = open("Y2015_D1_Input1.txt" , "r")
myInputString = myInputFile.read()

def checkFloor(inputString):
    floor = 0
    firstBasement = 0
    index = 0
    for char in inputString:
        index += 1
        if char == '(':
            floor += 1
        else:
            floor -= 1
        if firstBasement == 0 and floor == -1:
            firstBasement = index
    return floor , firstBasement

finalFloor , firstBasement = checkFloor(myInputString)
print("Santa is on floor:" , finalFloor)
print("Santa entered the basement on step:" , firstBasement)
myInputFile.close()
