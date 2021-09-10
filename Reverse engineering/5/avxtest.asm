include win64a.inc
.data
titl db "Проверка микропроцессора на поддержку команд AVX",0 ; название окна
szInf db "Команды AVX ПОДДЕРЖИВАЮТСЯ!!!",0 ;
inf db "Команды AVX микропроцессором НЕ поддерживаются",0
.code
WinMain proc
sub rsp,28h; cтек: 28h=32d+8; 8 - возврат
mov rbp,rsp
mov rax,1
cpuid ; по содержимому rax производится идентификация микропроцессора
and rcx,10000000h ; rсx:= rсx v 1000 0000h (28 разряд)
jnz exit1 ; перейти на exit, если не ноль
invoke MessageBox,0,addr inf,addr titl,MB_OK
jmp exit2
exit1:
invoke MessageBox,0,addr szInf,addr titl,MB_ICONINFORMATION
exit2:
invoke ExitProcess,0
WinMain endp
end