#include<p16f84.inc>
W_TEMP EQU 0x0C ; Р°РґСЂРµСЃР° СЂРµРіС–СЃС‚СЂСѓ Р·Р±РµСЂРµР¶РµРЅРЅСЏ W
STATUS_TEMP EQU 0X0D
A equ 0x11  ;
	org 0

	goto START; переход на метку START; 

	org  0x04 ; адрес размещения программы обработки прерывания
	bsf INTCON, RBIE;разер
movwf W_TEMP ; збереження W
swapf STATUS,W ; збереження STATUS
movwf STATUS_TEMP;переход на метку START
btfsc INTCON,RBIF ; переривання при зміні RB4-RB7
call INTRB47 ; виклик оброблювача переривання ; при зміні сигналу на виводах RB4-RB7
swapf STATUS_TEMP,W ; відновлення регістру STATUS
movwf STATUS
swapf W_TEMP, F ; відновлення регістру W
swapf W_TEMP, W
retfie

INTRB47
	movlw 0xFF
	tris PORTB
	movlw 0x00
	tris PORTA
	movf PORTB,0
	movwf A
	movwf PORTA
	swapf A,0
	movwf PORTA
	bcf INTCON,RBIF 
	return

START
bsf       INTCON, GIE
bsf       INTCON, RBIE
loop 
nop
goto loop	
end