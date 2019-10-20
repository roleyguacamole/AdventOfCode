import hashlib
myInput = 'bgvyzdsv'

def findKeyNum(inputString , numZeros):
    currentNum = 1
    someZeros = '0' * numZeros
    hexHash = '1' * numZeros
    while hexHash[0:numZeros] != someZeros:
        currentNum += 1
        currentString = myInput + str(currentNum)
        encoded = hashlib.md5(currentString.encode())
        hexHash = encoded.hexdigest()

    return currentNum

myKey1 = findKeyNum(myInput , 5)
myKey2 = findKeyNum(myInput , 6)
print('My first key is: ' , myKey1)
print('My second key is: ' , myKey2)
