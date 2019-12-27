parser grammar CoolParser;

options {
    tokenVocab = CoolLexer;
}
/*
@header{
    package cool.parser;
} */

program: (classesProgram+=class_def SEMI)+ EOF;

class_def: CLASS class_type=TYPE_ID (INHERITS inheritedClass=TYPE_ID)? LBRACE (definitions+=definition SEMI)* RBRACE;

definition
    :
        nameFunc=ID LPAREN (params+=declare_type (COMMA params+=declare_type)*)? RPAREN COLON returnType=TYPE_ID LBRACE body=expr RBRACE # functionDefinition
    |   declare_type (ASSIGN initVal=expr)? # variableDefinition
    ;

declare_type: name=ID COLON type=TYPE_ID;

let_variables: declare_type (ASSIGN expr)?;

branch: declare_type RESULTS body=expr SEMI;

expr:
    expression=expr (ANNOTATION upcast=TYPE_ID)? DOT name=expr LPAREN (params+=expr (COMMA params+=expr)*)? RPAREN # upCastCall
    | name=expr LPAREN (arguments+=expr (COMMA arguments+=expr)*)? RPAREN                                        # functionCall
    | IF condition=expr THEN then_branch=expr ELSE else_branch=expr FI                                          # if
    | WHILE condition=expr LOOP body=expr POOL                                                                  # while
    | LBRACE (expressions+=expr SEMI)+ RBRACE                                                                   # body
    | LET variables+=let_variables (COMMA variables+=let_variables)* IN body=expr                               # let
    | CASE expression=expr OF (branches+=branch)+ ESAC                                                          # case
    | NEW TYPE_ID                                                                                               # instantiation
    | NEGATION expression=expr                                                                                  # unaryNegation
    | leftBranch=expr op=(MUL | DIV) rightBranch=expr                                                           # mulDiv
    | leftBranch=expr op=(PLUS | MINUS) rightBranch=expr                                                        # minusPlus
    | name=ID ASSIGN value=expr                                                                                 # variableAssignment
    | ISVOID expression=expr                                                                                    # void
    | leftBranch=expr op=(LE | LT | EQUAL) rightBranch=expr                                                     # compare
    | NOT expression=expr                                                                                       # negation
    | LPAREN expression=expr RPAREN                                                                             # parantheses
    | ID                                                                                                        # id
    | BOOL                                                                                                      # boolean
    | STRING                                                                                                    # string
    | INT                                                                                                       # int
;
