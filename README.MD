#Lexical Analizer <br>
This program simulates a C compiler by scanning an external file containing C code and constructing a symbol table. The algorithm generates an entry for each token that is identified, and produces a summary report containing all tokens that appeared in the input.<br> 
<br>
####The grammar for producing tokens is as follows:

<pre>
    <b>keyword -></b> if | then | else | begin | end 
 <b>identifier -></b> character | character identifier 
    <b>integer -></b> digit | digit integer 
       <b>real -></b> integer.integer 
    <b>special -></b> ( | ) | [ | ] | + | - | = | , | ; 
      <b>digit -></b> 0|1|2|3|4|5|6|7|8|9 
  <b>character -></b> a|b|c ... |z|A|B|C ... |Z
</pre>

