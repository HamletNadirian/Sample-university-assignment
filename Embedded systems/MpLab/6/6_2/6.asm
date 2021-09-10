#include <p16f84.inc>
kol equ .12
name equ 0x10
adr equ 0x0c
org 0
main
	call init
	movlw .12
	movwf kol
	call proc1
	call proc2
	goto main
init movlw 'N'
	movwf 0x10
	movlw 'a'
	movwf 0x11
	movlw 'd'
	movwf 0x12
	movlw 'i'
	movwf 0x13
	movlw 'r'
	movwf 0x14
	movlw 'i'
	movwf 0x15
	movlw 'a'
	movwf 0x16
	movlw 'n'
	movwf 0x17
	movlw ' '
	movwf 0x18
	movlw 'H'
	movwf 0x19
	movlw '.'
	movwf 0x1A
	movlw 'O'
	movwf 0x1B
	return
proc1 movlw 0x10
	movwf FSR
m1	bcf STATUS,RP0
	movf INDF,w
	movwf EEDATA
	movfw FSR
	movwf EEADR
	bsf STATUS,RP0
	bsf EECON1,WREN
	bcf EECON1,EEIF
	bcf INTCON,GIE
	movlw 0x55
	movwf EECON2
	movlw 0xaa
	movwf EECON2
	bsf EECON1,WR
m2	btfss EECON1,EEIF
	goto $-1
	bcf STATUS,RP0
	incf FSR
	decfsz kol,1
	goto m1
	return


proc2 
bcf STATUS,RP0
movlw 0x3f
movwf EEADR
movlw .12
movwf EEDATA
bsf STATUS,RP0
bsf EECON1,WREN
bcf EECON1,EEIF
bcf INTCON,GIE
movlw 0x55
movwf EECON2
movlw 0xAA
movwf EECON2
bsf EECON1,WR
m3 btfss EECON1,EEIF
goto m3
bcf STATUS,RP0

	return
END