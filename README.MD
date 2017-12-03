<b>Random C Syntax Generator</b>

Part of a programming activity for "Course"




Set of productions used to define a subset of the C programming language:

The grammar for producing tokens is as follows:
keyword ::= if | then | else | begin | end
identifier -> character | character identifier
integer -> digit | digit integer
real -> integer.integer
special -> ( | ) | [ | ] | + | - | = | , | ;
digit -> 0|1|2|3|4|5|6|7|8|9
character -> a|b|c ... |z|A|B|C ... |Z
