grammar Resolve;

moduleDecl
    :   ( precisModuleDecl
        | precisExtensionModuleDecl
        ) EOF
    ;

precisModuleDecl
    :   'Precis' name=ID ';'
        //    (usesList)?
        //    precisBlock
        'end' closename=ID ';'
    ;

precisExtensionModuleDecl
    :   'Precis' 'Extension' name=ID 'for' precis=ID ';'
        // ...
        'end' closename=ID ';'
    ;

precisBlock
    :   ( mathStandardDefinitionDecl
        | mathCategoricalDefinitionDecl
        | mathInductiveDefinitionDecl
        | mathTheoremDecl
        )*
    ;

// uses, imports

usesList
    :   'uses' ID (',' ID)* ';'
    ;

// math constructs

mathTheoremDecl
    :   ('Corollary'|'Theorem') name=ID ':' mathAssertionExp ';'
    ;

mathDefinitionSig
    :   mathPrefixDefinitionSig
    |   mathInfixDefinitionSig
    |   mathOutfixDefinitionSig
    ;

mathPrefixDefinitionSig
    :   name=mathSymbolName ('('
                mathVariableDeclGroup (',' mathVariableDeclGroup)* ')')?
                ':' mathTypeExp
    ;

mathInfixDefinitionSig
    :   '(' mathVariableDecl ')' name=mathSymbolName
        '(' mathVariableDecl ')' ':' mathTypeExp
    ;

mathOutfixDefinitionSig
    :   leftSym=mathSymbolName '(' mathVariableDecl ')'
        rightSym=mathSymbolName ':' mathTypeExp
    ;

mathSymbolName
    :   ID
    |   ('+'|'-'|'...'|'/'|'\\'|'|'|'||'|'<'|'>'|'o'|'*'|'>='|'<='|INT|'not')
    |   '|' '...' '|'
    |   '<' '...' '>'
    |   '||' '...' '||'
    |   '\\' '...' '/'
    ;

mathCategoricalDefinitionDecl
    :   'Categorical' 'Definition' 'for'
        mathPrefixDefinitionSig (',' mathPrefixDefinitionSig)+
        'is' mathAssertionExp ';'
    ;

mathStandardDefinitionDecl
    :   ('Implicit')? 'Definition' mathDefinitionSig
        ('is' mathAssertionExp)? ';'
    ;

mathInductiveDefinitionDecl
    :   'Inductive' 'Definition' mathDefinitionSig 'is'
        '(i.)' mathAssertionExp ';'
        '(ii.)' mathAssertionExp ';'
    ;

mathVariableDeclGroup
    :   ID (',' ID)* ':' mathTypeExp
    ;

mathVariableDecl
    :   ID ':' mathTypeExp
    ;

// mathematical clauses

requiresClause
    :   'requires' mathAssertionExp (entailsClause)? ';'
    ;

ensuresClause
    :   'ensures' mathAssertionExp ';'
    ;

constraintClause
    :   ('Constraints'|'constraints') mathAssertionExp ';'
    ;

conventionClause
    :   'convention' mathAssertionExp (entailsClause)? ';'
    ;

correspondenceClause
    :   'correspondence' mathAssertionExp ';'
    ;

entailsClause
    :   'which_entails' mathExp (',' mathExp)* ':' mathTypeExp
    ;

// mathematical expressions

mathTypeExp
    :   mathExp
    ;

mathAssertionExp
    :   mathExp
    |   mathQuantifiedExp
    ;

mathQuantifiedExp
    :   q=(FORALL|EXISTS) mathVariableDeclGroup ',' mathAssertionExp
    ;

mathExp
    :   functionExp=mathExp '(' mathExp (',' mathExp)* ')'      #mathPrefixApplyExp
    |   mathExp op='.' mathExp                                  #mathSelectorExp
    |   mathExp op=('*'|'/'|'~') mathExp                        #mathInfixApplyExp
    |   mathExp op=('+'|'-') mathExp                            #mathInfixApplyExp
    |   mathExp op=('..'|'->') mathExp                          #mathInfixApplyExp
    |   mathExp op=('o'|'union'|'intersect') mathExp            #mathInfixApplyExp
    |   mathExp op=('is_in'|'is_not_in') mathExp                #mathInfixApplyExp
    |   mathExp op=('<='|'>='|'>'|'<') mathExp                  #mathInfixApplyExp
    |   mathExp op=('='|'/=') mathExp                           #mathInfixApplyExp
    |   mathExp op=('implies'|'iff') mathExp                    #mathInfixApplyExp
    |   mathExp op=('and'|'or') mathExp                         #mathInfixApplyExp
    |   mathExp op=':' mathTypeExp                              #mathTypeAssertionExp
    |   '(' mathAssertionExp ')'                                #mathNestedExp
    |   mathPrimaryExp                                          #mathPrimeExp
    ;

mathPrimaryExp
    :   mathLiteralExp
    |   mathCrossTypeExp
    |   mathSymbolExp
    |   mathOutfixApplyExp
    |   mathSetComprehensionExp
    |   mathSetExp
    |   mathLambdaExp
    |   mathAlternativeExp
    ;

mathLiteralExp
    :   (qualifier=ID '::')? ('true'|'false')   #mathBooleanLiteralExp
    |   (qualifier=ID '::')? num=INT            #mathIntegerLiteralExp
    ;

mathCrossTypeExp
    :   'Cart_Prod' (mathVariableDeclGroup ';')+ 'end'
    ;

mathSymbolExp
    :   (incoming='@')? (qualifier=ID '::')? name=mathSymbolName
    ;

mathOutfixApplyExp
    :   lop='<' mathExp rop='>'
    |   lop='|' mathExp rop='|'
    |   lop='||' mathExp rop='||'
    ;

mathSetComprehensionExp
    :   '{' mathVariableDecl '|' mathAssertionExp '}'
    ;

mathSetExp
    :    '{' (mathExp (',' mathExp)*)? '}'
    ;

mathLambdaExp
    :   'lambda' '(' mathVariableDeclGroup
        (',' mathVariableDeclGroup)* ')' '.' '(' mathExp ')'
    ;

mathAlternativeExp
    :   '{{' (mathAlternativeItemExp)+ '}}'
    ;

mathAlternativeItemExp
    :   result=mathExp ('if' condition=mathExp ';' | 'otherwise' ';')
    ;

FORALL : ('Forall'|'forall');
EXISTS : ('Exists'|'exists');
LINE_COMMENT : '//' .*? ('\n'|EOF)	-> channel(HIDDEN) ;
COMMENT      : '/*' .*? '*/'    	-> channel(HIDDEN) ;

ID  : [a-zA-Z_] [a-zA-Z0-9_]* ;
INT : [0-9]+ ;

STRING :  '"' (ESC | ~["\\])* '"' ;
fragment ESC :   '\\' ["\bfnrt] ;
WS : [ \t\n\r]+ -> channel(HIDDEN) ;

/** "catch all" rule for any char not matched in a token rule of your
 *  grammar. Lexers in Intellij must return all tokens good and bad.
 *  There must be a token to cover all characters, which makes sense, for
 *  an IDE. The parser however should not see these bad tokens because
 *  it just confuses the issue. Hence, the hidden channel.
 */
ERRCHAR
   :  .  -> channel(HIDDEN)
   ;
