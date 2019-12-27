lexer grammar CoolLexer;

tokens { ERROR }

/*
@header{
    package cool.lexer;
}*/

@members{
    int total_chars = 0;
    final int charsLimit = 1024;
    final String tooLong = "String constant too long";
    final String unterminatedString = "Unterminated string constant";
    final String eofInString = "EOF in string constant";
    final String hasNullChars = "String contains null character";
    final String eofComment = "EOF in comment";
    final String unmatchedComment = "Unmatched *)";
    final String invalidChar = "Invalid character: ";
    private void raiseError(String msg) {
        setText(msg);
        setType(ERROR);
    }
}

/* Cuvânt cheie.
 */
IF : 'if';
THEN : 'then';
ELSE : 'else';
FI: 'fi';

NEGATION: '~';
NOT: 'not';

NEW : 'new';
CLASS : 'class';
INHERITS: 'inherits';

LET : 'let';
IN : 'in';

fragment SELFTYPE: 'SELF_TYPE';

WHILE: 'while';
LOOP: 'loop';
POOL: 'pool';

CASE: 'case';
OF: 'of';
ESAC: 'esac';

ISVOID: 'isvoid';

BOOL : 'true' | 'false';

/*
fragment HEX
   : [0-9a-fA-F]
   ;
fragment UNICODE
   : 'u' HEX HEX HEX HEX
   ;
fragment ESC
   : '\\' (["\\/bfnrt] | UNICODE)
   ;
STRING
   : '"' (ESC | ~ ["\\])* '"' | '"' ('\\"' | .)*? '"'
   ;
*/

fragment NULLCHAR: '\u0000';
fragment UNTERMINATEDSTRING: '\r\n';
fragment SPECIALCHARS: '\\"' | '\\b' | '\\t' | '\\n' | '\\f' | '\\\\';

STRING : '"'
            (
            (SPECIALCHARS {
                total_chars++;
            })
            | '\\\r\n'
            | '\\'
            | (NULLCHAR {
                raiseError(hasNullChars);
            })
            |
            (. { total_chars++; }))*?
            (('"' {
                if(total_chars > charsLimit)
                    raiseError(tooLong);
                total_chars = 0;
            })
            | (UNTERMINATEDSTRING {
                raiseError(unterminatedString);
            })
            | (EOF {
                raiseError(eofInString);
                total_chars = 0;
            }));

fragment TYPE : 'Int' | 'Float' | 'Bool' | 'String';

/* Identificator.
 */
fragment UPPERLETTER : [A-Z];
fragment LOWERCASE: [a-z];
fragment LETTER : [a-zA-Z];

/* Număr întreg.
 *
 * fragment spune că acea categorie este utilizată doar în interiorul
 * analizorului lexical, nefiind trimisă mai departe analizorului sintactic.
 */
fragment DIGIT : [0-9];
INT : DIGIT+;

TYPE_ID : TYPE | SELFTYPE | UPPERLETTER(LETTER | '_' | DIGIT)*;
ID : LOWERCASE(LETTER | '_' | DIGIT)*;

/* Număr real.
 */
fragment DIGITS : DIGIT+;
fragment EXPONENT : 'e' ('+' | '-')? DIGITS;
FLOAT : (DIGITS ('.' DIGITS?)? | '.' DIGITS) EXPONENT?;

/* Șir de caractere.
 *
 * Poate conține caracterul '"', doar precedat de backslash.
 * . reprezintă orice caracter în afară de EOF.
 * *? este operatorul non-greedy, care încarcă să consume caractere cât timp
 * nu a fost întâlnit caracterul ulterior, '"'.
 *
 * Acoladele de la final pot conține secvențe arbitrare de cod Java,
 * care vor fi executate la întâlnirea acestui token.
 */
DOT: '.';

ANNOTATION: '@';

SEMI : ';';

COLON: ':';

COMMA : ',';

ASSIGN : '<-';

LPAREN : '(';

RPAREN : ')';

LBRACE : '{';

RBRACE : '}';

PLUS : '+';

MINUS : '-';

MUL : '*';

DIV : '/';

RESULTS : '=>';

EQUAL : '=';

LT : '<';

LE : '<=';

fragment NEW_LINE : '\r'? '\n';

LINE_COMMENT: '--' .*? (NEW_LINE | EOF) -> skip;

BLOCK_COMMENT
        : '(*'
          (BLOCK_COMMENT | .)*?
          '*)' -> skip;

BLOCK_COMMENT_ERROR
    : '*)' { raiseError(unmatchedComment); };

BLOCK_COMMENT_END_OF_LINE
    : '(*'
       (BLOCK_COMMENT | ~([*)]).)*?
       (EOF {
            raiseError(eofComment);
       });

/* Spații albe.
 *
 * skip spune că nu este creat niciun token pentru lexemul depistat.
 */
WS : [ \n\r\t]+ -> skip;

CHAR_ERR: . {
    setCharPositionInLine(getCharPositionInLine() - 1);
    raiseError(invalidChar + getText());
};

/* Modalitate alternativă de recunoaștere a șirurilor de caractere, folosind
 * moduri lexicale.
 *
 * Un mod lexical, precum cel implicit (DEFAULT_MODE) sau IN_STR, de mai jos,
 * reprezintă stări ale analizorului. Când analizorul se află într-un anumit
 * mod, numai regulile din acel mod se pot activa.
 *
 * Folosim pushMode și popMode pentru intra și ieși din modurile lexicale,
 * în regim de stivă.
 *
 * more spune că deocamdată nu este construit un token, dar lexemul identificat
 * va face parte, cumulativ, din lexemul recunoscut de următoarea regulă.
 *
 * De-abia la recunoașterea caracterului '"' de sfârșit de șir de către regula
 * STR, se va construi un token cu categoria STR și întregul conținut al șirului
 * drept lexem.
 */
/*
STR_OPEN : '"' -> pushMode(IN_STR), more;
mode IN_STR;
STR : '"' -> popMode;
CHAR : ('\\"' | ~'"') -> more;  // ~ = complement
*/