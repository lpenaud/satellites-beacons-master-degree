grammar SatelliteBeacon;

@header {
//
}
script: command*;
command : (globals | affectation | callable | method | variable);
globals : 'globals';

// Variable
affectation 		: affectationNb | affectationString | affectationInstance;
affectationNb 		: WORD '=' NB;
affectationString 	: WORD '=' '"' STRING '"';
affectationInstance : WORD '=' newInstance;
newInstance 		: 'new' callable;
callable 			: WORD '(' args? ')';
variable			: WORD;

// Args
args 	: WORD '=' (NB | ( '"' STRING '"' )) (',' args*)?;
method 	: WORD '.' callable;

NB   	: [0-9]+; //number 
WORD 	: [a-zA-Z]+ ;
STRING 	: [A-Z];
WS   	: [ \t\r\n]+ -> skip ;
