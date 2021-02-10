grammar SatelliteBeacon;

@header {
//
}
script: command*;
command : (affectation | callable | variable | properties | setter);

// Variable
affectation 		: affectationNb | affectationInstance;
affectationNb 		: WORD '=' NB;
affectationInstance : WORD '=' newInstance;
callable 			: WORD '(' args? ')';
variable			: WORD;

// Objects
newInstance 		: 'new' callable;
property			: '.' WORD;
properties			: WORD property+;
setter				: setterNb | setterInstance;
setterNb 			: WORD property+ '=' NB;
setterInstance		: WORD property+ '=' newInstance;

// Args
args 	: WORD '=' NB (',' args*)?;

NB   	: [0-9]+; //number 
WORD 	: [a-zA-Z]+ ;
STRING 	: [A-Z];
WS   	: [ \t\r\n]+ -> skip ;
