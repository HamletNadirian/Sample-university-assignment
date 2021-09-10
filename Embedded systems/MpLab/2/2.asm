#include<p16f84.inc>
A equ .11
	ORG 0
	movlw 0xFF
	tris PORTB
	movlw 0x00
	tris PORTA
	movf PORTB,0
	movwf A
	movwf PORTA
	swapf A,0
	movwf PORTA 
loop goto loop 
;loop movf PORTB,w
;	movwf A
;	movwf PORTA
;	swapf A,w
;	goto loop
END