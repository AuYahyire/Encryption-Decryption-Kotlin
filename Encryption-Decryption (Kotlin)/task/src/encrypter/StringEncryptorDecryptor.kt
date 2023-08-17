package encryptdecrypt.encrypter

import encryptdecrypt.data.SharedData



/**
 * Utility class for encrypting strings using a simple letter-shifting algorithm.
 *
 *  The shared data container that holds the string and key for encryption.
 */
class StringEncryptorDecryptor(private val data: SharedData) : EncryptionManager(data) {

    fun processString() {
        val newString = super.performEncryptionDecryption(data.inputData)
        println(newString)
    }
}







/*
a: 97
b: 98
c: 99
d: 100
e: 101
f: 102
g: 103
h: 104
i: 105
j: 106
k: 107
l: 108
m: 109
n: 110
o: 111
p: 112
q: 113
r: 114
s: 115
t: 116
u: 117
v: 118
w: 119
x: 120
y: 121
z: 122
 */