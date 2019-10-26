myInputFile = open("Y2015_D08_Input.txt" , "r")
myInputLines = myInputFile.readlines()
myInputFile.close()

def codeMinusString(string):
    if string[-1] == "\n":
        codeLength = len(string) - 1
    else:
        codeLength = len(string)
    strLen = 0
    encodeLength = 0
    codeIndex = 1
    encodeIndex = 0
    while encodeIndex < codeLength:
        if codeIndex < codeLength - 1:
            if string[codeIndex] == '\\':
                if string[codeIndex + 1] == 'x':
                    codeIndex += 4
                else:
                    codeIndex += 2
            else:
                codeIndex += 1
            strLen += 1
        if string[encodeIndex] == "\"":
            if encodeIndex == 0 or encodeIndex == codeLength - 1:
                encodeLength += 3
            else:
                encodeLength += 2
        elif string[encodeIndex] == "\\":
            encodeLength += 2
        else:
            encodeLength += 1
        encodeIndex += 1
    return codeLength - strLen , encodeLength - codeLength

def totalDiff(inputStrings):
    codeMinus = 0
    encodeMinus = 0
    for string in inputStrings:
        nextCodeDiff , nextEncodeDiff = codeMinusString(string)
        codeMinus += nextCodeDiff
        encodeMinus += nextEncodeDiff
    return codeMinus , encodeMinus

codeMinus , encodeMinus = totalDiff(myInputLines)
print("The total difference between code length and string length is" , codeMinus , ".")
print("The total difference between encoded length and code length is" , encodeMinus , ".")
