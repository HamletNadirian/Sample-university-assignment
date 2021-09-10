#include <p16c71.inc>
Counter_l equ 0x12
Counter_h equ 0x13
U1 equ 0x31
U2 equ 0x32
DCounter1 EQU 0X0C

A equ .10
M equ .11
H equ .12
Dat equ 0x20
ORG 0
BSF STATUS,RP0 ;Доступ к старшему банку регистров
MOVLW B'00000000' ; Конфигурация порта A (все на выход)
MOVWF TRISA ;
MOVLW B'00000000' ;Конфигурация порта B (все на выход)
MOVWF TRISB
BCF STATUS,RP0 ;Снова доступ к младшему банку
CLRF PORTA ;
CLRF PORTB ;
goto start

Delay30us
	MOVLW 0X30
	MOVWF DCounter1
	LOOP
	DECFSZ DCounter1, 1
	GOTO LOOP
	NOP
	return

CONVERT
	call Delay30us ; затримка 30 мкс
	bsf ADCON0, GO_DONE ; запуск АЦП
	loop
	btfsc ADCON0, GO_DONE ; перетворення закінчене?
	goto loop ; продовжити чекання
	movf ADRES, W ; результат перетворення в W
	return

proc_I
	BSF PORTB,0
	BSF PORTB,1
	BSF PORTB,2
	BSF PORTB,3
	BSF PORTB,4
	BSF PORTB,5
	BSF PORTB,6
	BSF PORTB,7

	BCF PORTB,0
	;BCF PORTB,1
	;BCF PORTB,2
	BCF PORTB,3
	BCF PORTB,4
	BCF PORTB,5
	BCF PORTB,6
	BCF PORTB,7

	movf PORTB,W
	BTFSC PORTB,1;IF == 0 Вып.
	movwf A;A=
	BTFSC PORTB,1
	BCF PORTB,1;0000 0100->;0000 0110

	movf PORTB,W
	BTFSC PORTB,2;Пропустить команду, если бит в f равен нулю	
	BCF PORTB,2;0000 0110->;0000 0010
	movwf M;
	BTFSS PORTB,2
	BCF PORTB,2
	
	movf PORTB,W
	BTFSS A,1;Пропустить команду, если бит в f равен единице	
	BCF PORTB,2;Сброс бита в регистре f	
	BTFSC A,1;Пропустить команду, если бит в f равен нулю	
	BSF PORTB,2;Установка бита в регистре f	

	movf PORTB,W
	BTFSS M,2;Пропустить команду, если бит в f равен единице	
	BCF PORTB,1;Сброс бита в регистре f	
	BTFSC M,2;Пропустить команду, если бит в f равен нулю	
	BSF PORTB,1;Установка бита в регистре f	
	return
proc_B
	decfsz Counter_l
	decfsz Counter_h

start
	initAD
	bsf STATUS,RP0
	movlw b'00000000'
	movwf ADCON1
	bcf STATUS,RP0
	movlw b'11010001'
	movwf ADCON0;
	call CONVERT
	
	movwf U1
	bsf STATUS,RP0; вибір банку 1
	movlw b'00000000';вив. RA3-RA0 – аналогові входи
	movwf ADCON1
	bcf STATUS,RP0
	movlw b'11010001'
	movwf ADCON0;канал 2, дозвіл АЦП
	call CONVERT
	movwf U2

COMPARE
	movf U1,0
	subwf U2,0
	btfss STATUS,0 ;U1<U2 - Пропустить команду, если бит в f равен единице. N=1 Negative.
	call proc_B
	btfsc STATUS, 2;U1=U2;если Z=1, то был нулевой результат выполнения команды;; если Z=1, то выполняется ;следующая инструкция, иначе – пропускается. Пропустить команду, если бит в f равен нулю	
	call proc_I	
	btfsc STATUS,C ;U1>U2. Пропустить команду, если бит в f равен нулю	
	call proc_B
	goto $-1
	end