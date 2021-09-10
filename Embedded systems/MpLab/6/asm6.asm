#include <p16f84.inc>
counter equ .12 ;становлення лічильника на 12 циклів
name equ 0x10
adr equ 0x0c
org 0
main
  call init
  movlw .12 ;ініціалізація лічильника counter значенням 12
  movwf counter
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
proc1 movlw 0x10 ; визначення адреси комірки пам'яті EEPROM
  movwf FSR ;адреса регістра R0 (розміщено в 10h) з W поміщується у регістр покажчика адреси в пам'яті даних FSR (непаряма адресація)
m1  bcf STATUS,RP0; вибір банку 0
  movf INDF,w; копіювання з INDF у W вмісту регістра
  movwf EEDATA; дані для запису
  movfw FSR; адреса регістра R0 (розміщено в 10h) з W поміщується у регістр покажчика адреси в пам'яті даних FSR (непаряма адресація)
  movwf EEADR; копіювання з W вe регістр EEADR
  bsf STATUS,RP0; вибір банку 1
  bsf EECON1,WREN; дозвіл запису
  bcf EECON1,EEIF; скидання прапорця закінчення запису в EEPROM
  bcf INTCON,GIE; заборона переривань
  movlw 0x55; обов'язкова послідовність команд
  movwf EECON2;+
  movlw 0xaa;+
  movwf EECON2;+
  bsf EECON1,WR; +строб запису
;m2  btfss EECON1,EEIF; -очікування закінчення запису в EEPROM
m2 clrf INDF
btfss EECON1,EEIF 
 goto $-1;-
  bcf STATUS,RP0; вибір банку 0
  incf FSR; збільшення значення покажчика
  decfsz counter,1; зменшення значення лічильника
  goto m1; перехід, якщо не остання комірка
  return


proc2 
bcf STATUS,RP0; вибір банку 0
movlw 0x3f; визначення адреси комірки пам'яті EEPROM (остання комірка)
movwf EEADR; копіювання з W вe регістр EEADR
movlw .12 ; ініціалізація лічильника counter значенням 12
movwf EEDATA; дані для запису
bsf STATUS,RP0; вибір банку 1
bsf EECON1,WREN; дозвіл запису
bcf EECON1,EEIF; - скидання прапорця закінчення запису в EEPROM
bcf INTCON,GIE; +заборона переривань
movlw 0x55; обов'язкова послідовність команд
movwf EECON2;+
movlw 0xAA;+
movwf EECON2;+
bsf EECON1,WR;+
m3 btfss EECON1,EEIF;-очікування закінчення запису в EEPROM
goto m3
bcf STATUS,RP0; вибір банку 0

  return
END