<h1>Lexical Analizer</h1>
This program simulates a C compiler by scanning an external file containing C code and constructing a symbol table. The algorithm generates an entry for each token that is identified, and produces a summary report containing all tokens that appeared in the input.<br> 
<br>
<h4>The grammar for producing tokens is as follows:</h4>

<pre>
    <b>keyword</b> -> <b>if</b> |<b>then</b> |<b>else</b> |<b>begin</b> |<b>end</b> 
 <b>identifier</b> -> <b>character</b> |<b> character identifier</b> 
    <b>integer</b> -> <b>digit</b> |<b> digit integer</b> 
       <b>real</b> -> <b>integer.integer</b> 
    <b>special</b> -> <b>(</b> |<b> )</b> |<b> [</b> |<b> ]</b> |<b> +</b> |<b> -</b> |<b> =</b> |<b> ,</b> |<b> ;</b> 
      <b>digit</b> -> <b>0</b>|<b>1</b>|<b>2</b>|<b>3</b>|<b>4</b>|<b>5</b>|<b>6</b>|<b>7</b>|<b>8</b>|<b>9</b> 
  <b>character</b> -> <b>a</b>|<b>b</b>|<b>c ... </b>|<b>z</b>|<b>A</b>|<b>B</b>|<b>C ... </b>|<b>Z</b>
</pre>

