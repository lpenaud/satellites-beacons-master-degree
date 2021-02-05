grammar SatelliteBeacon;

@header {
//
}
command : (globals | affectation | callable | method);
globals : 'globals';

// Variable affectation
affectation : affectationNb | affectationString | affectationInstance;
affectationNb : WORD '=' NB;
affectationString : WORD '=' '"' STRING '"';
affectationInstance : WORD '=' newInstance;
newInstance : 'new' callable;
callable : WORD '(' args* lastargs? ')';

// Args
args : WORD '=' NB | ( '"' STRING '"' )  ',';
lastargs : WORD '=' NB | ( '"' STRING '"' );
method : WORD '.' callable;

NB   : [0-9]+; //number 
WORD : [a-zA-Z]+ ;
STRING : [0-9a-zA-Z]+;
WS   : [ \t\r\n]+ -> skip ;
