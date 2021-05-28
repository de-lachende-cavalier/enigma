## Components
A typical Enigma machine is made up of: **KEYBOARD**, **PLUGBOARD**, **STATOR**, **ROTORS**, **REFLECTOR** 
                                        and **LAMP PANEL**.
Physically, the encryption and the decryption happen due to the flow of the current from one part of the machine to 
another, in a circuit. 

**ROTORS** => Each rotor has 26 contacts, every contact in one rotor connects to the rotor on the left and right randomly.
              Every time a key is pressed the rightmost rotor rotates, thus modifying the wiring.
              The rotors move at different speeds, with the rightmost rotor being the fastest and the leftmost being
              the slowest.
              
**STATOR** => Basically a static rotor, it has internal wiring but doesn't rotate.
              
              
**PLUGBOARD** => The Mk. III version (the final one) allows letters to be swapped through 13 wires.
                 The Mk. II version was made up of 26 wires and 52 sockets, so that each letter could be mapped to a 
                 different number. For optimal cryptographic complexity, all cables must be connected.                
                 
**REFLECTOR** => The reflector implements an ATBASH cipher, and starts the second part of the encryption/decryption, by 
                 sending the signal back from it to the lamp panel, passing through rotors and stator.                    
                 
**LAMP PANEL** => The lamp panel is a retro-illuminated keyboard and it maps each letter in the clear-text to one in
                  the cipher-text.
                  
## How it works

Before beginning the encryption process, one must specify to whomever will be responsible for decryption:
1. The chosen rotors and the order in which they're placed
2. The starting position of each rotor
3. The ring setting (which I'll take to be the internal wiring of the rotors)
4. The plugboard setup
    
Once all these variables are accounted for, encryption can begin.
     
As a key on the **KEYBOARD** is pressed, the **ROTORS** turn. The current flows first through the **PLUGBOARD**, then 
the **ETW (STATOR)** and finally through the **ROTORS** (3 in the first version of Enigma), right to left. 
It then reaches the **REFLECTOR (UKW)** and travels back through the **ROTORS** once more, from left to right this time, 
and finally hits the **LAMP PANEL**, giving the corresponding letter in the cipher-text. Decryption follows the same 
pattern.

## Weaknesses 
- A letter cannot be encoded with itself: ***FIXED*** 
- Double stepping of the rotors: ***FIXED***
- Letters only swapped in pairs in Mk. III plugboard: ***FIXED*** 
- The germans used to use a fixed number of cables on the plugboard (10): ***FIXED***
- Fourth rotor not moving in later editions of the Enigma: ***FIXED***

## Mathematical Considerations
I suppose that everything about the system is known (as in [here](https://cryptomuseum.com/crypto/enigma/working.htm)).
• Plugboard Settings: *26!*
• Wheel Order: if we consider *n* rotors to pick from, the permutations are expressed as: *n* * *n-1* * *n-2*..., thus *n!*
• Initial Position of Rotors (Ringstellung): *26^n*, where *n* is the number of rotors.
A final calculation gives:
**final_permutations = 26! + n! + 26^n**
Which, in the case of _n = 5_ rotors, equals _403,291,461,126,605,635,595,881,496_ or 4.03 * 10^26. Taking the log2 we
get 88.38195332701626 ~ 89, meaning that we obtain a security level of _89_ bits. Pretty low considering today's
standards. We can definitely do better. 
In his book "Applied Cryptography", author Bruce Schneier posits that 128 bits of security are good enough (which means
we want a cipher with a security level of 256 bits due to collisions), but that book is almost 10 years old now. 
We can probably accept a collision resistance of 256 bits, which means a preimage resistance of 512 bits, requiring _n = 98_.

A noticeable performance drop is felt at _n = 300_ (which is such a large number[*] that even Haskell gives its log2 as
infinity).

[*]For reference, it's _306057512216440636035370461297268629388588804173576999416776741259476533176716867465515291422477573
349939147888701726368864263907759003154226842927906974559841225476930271954604008012215776283222764047567098033061962302
547648150655629812483625524955002232872561810996187209519608848440566963998182650431342872318418496249442601082287578224
840649800764554653983174626964971418369024591227425552154609443995050491840029053164497164251185704625281864369890695495
173682798807120601338702468384952883790819187968074077267296442612595131759832071354561068846662930734290361297812412595
434872430408122140879536788787429376_ (3.06 * 10^615). Using the approximation that 10^3 ~ 2^10, 10^6 ~ 2^20 and so on.
We get a preimage resistance of ~_2000_ bits (because 10^600 = (10^3)^200 = (2^10)^200).
