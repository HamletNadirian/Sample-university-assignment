#include<p16f84a.inc>
A equ 0x11
	ORG 0
	movlw 0xff ;все разряды на ввод
	tris PORTB ;данные попадают в регистр трис
	movlw 0x00;настройка на вывод
	tris PORTA
loop movf PORTB,w ; w-прочитать данные
	movwf A;перенести в яч с аккумулятора
	movwf PORTA ; 8 разрядов,но 4 попадаюта 4 нет
	swapf A,W ;обмен
	movwf PORTA
	goto loop
end