#include<p16f84a.inc>
W_TEMP EQU 0X0C;адреса регістру збереження W
STATUS_TEMP EQU 0X0D
A EQU 0x11 ;11
Org 0x04
movwf W_TEMP
swapf STATUS,W
movwf STATUS_TEMP
btfsc INTCON,RBIF
call INTRB47
swapf STATUS_TEMP,W
movwf STATUS
swapf W_TEMP,F
swapf W_TEMP,W_TEMP
retfie
INTRB47
movf PORTB,W
movwf PORTA
movwf PORTA
MOVWF A
swapf A,W

bcf INTCON,RBIF
return