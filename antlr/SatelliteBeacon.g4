grammar SatelliteBeacon;

@header {
//
}
script: command*;
command : (affectation | globalCallable | method | variable | properties);

globalCallable 		: callable;

// Variable
affectation 		: affectationNb | affectationInstance;
affectationNb 		: WORD '=' WORD;
affectationInstance : WORD '=' newInstance;
callable 			: WORD '(' args? ')';
variable			: WORD;

// Objects
newInstance 		: 'new' callable;
method				: WORD? '.'? callable;
property			: '.' WORD;
properties			: WORD property+;

// Args
args 	: WORD '=' WORD (',' args*)?;

WORD	: [\-0-9a-zA-Z]+;
WS   	: [ \t\r\n]+ -> skip ;
