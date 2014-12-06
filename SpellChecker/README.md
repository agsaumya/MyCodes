MyCodes
=======

List of my own codes, examples and practive problems

First Program: 
SpellChecker
Adding spellchecker program.
It also shows the use of Trie to store dictionary of words.

Limitations: 
1) Since eclipse was throwing exception for heap space, I reduced the trie to just 26-ary trie instead of 256-ary trie. That is, only letters a-z are allowed in my program.
2) Printing 10 or 100 words will only print different words i.e it will not print a word for which its substring has already been printed. 
Eg: if abandon is already printed then abandonment and abandoned will not get printed.
Need to work on above problems.

References :
http://krishnabharadwaj.info/Trie/   (my code is similar to his with some aditional stuff (read... bugfixes) of my own)
http://sukwonoh.blogspot.com/2012/09/trie-application-iii-spelling-corrector.html  (will try to implement this algorithm too as it is a standard algo used in industry)
http://harisankar-krishnaswamy.blogspot.com/2011/10/spell-suggestion-like-google-microsoft.html (another one)
and the biggest collection of all: http://norvig.com/spell-correct.html
and stackoverflow : http://stackoverflow.com/questions/2294915/what-algorithm-gives-suggestions-in-a-spell-checker 
