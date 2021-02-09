grammar SatelliteBeacon;

@header {
//
}
script: command*;
command : (affectation | callable | method | variable | properties);

// Variable
affectation 		: affectationNb | affectationInstance;
affectationNb 		: WORD '=' NB;
affectationInstance : WORD '=' newInstance;
callable 			: WORD '(' args? ')';
variable			: WORD;

// Objects
newInstance 		: 'new' callable;
method				: WORD? '.'? callable;
property			: '.' WORD;
properties			: WORD property+;

// Args
args 	: WORD '=' NB (',' args*)?;

NB   	: [0-9]+; //number 
WORD 	: [a-zA-Z]+ ;
STRING 	: [A-Z];
WS   	: [ \t\r\n]+ -> skip ;
