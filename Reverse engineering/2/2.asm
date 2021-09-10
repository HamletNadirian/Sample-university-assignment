include win64a.inc
.data   ; директива начала сегмента данных
a1 dq 1 ; резервирование в памяти 8 байтов для переменной Х
b1 dq 2 ;
c1 dq 64 ;
d1 dq 4
res1 dq ?
titl db "Вывод через функцию MessageBox",0; название упрощенного окна
st1 dq ?,0  ; буфер выведения сообщения.
ifmt  db "Дано уравнение:",10,10,"22d/b - > 8d/b-32d/c",10,
"a = %d",10,"b = %d",10,"c = %d",10,"d = %d",10,"res = %d",10,10,
"Время выполнения  = %d тиков",10,  ;
"Автор программы: Надирян Г.О., КИТ-36",0 ;
.code        ; директива начала сегментa команд
WinMain proc
sub rsp,28h; cтек: 28h=32d+8; 8 — возврат
mov rbp,rsp
rdtsc
xchg rax,r15
mov rax,8   ;8
mul d1      ;8*4=32
div b1      ;32/2=16
mov res1,rax ; res1=16
rdtsc
sub rax,r15
invoke wsprintf,ADDR st1,ADDR ifmt,a1,b1,c1,d1,res1,rax
invoke MessageBox,0,addr st1,addr titl,MB_ICONINFORMATION;
invoke ExitProcess,0 ;возвращение упр. ОС и освобожд. ресурсов
WinMain endp
end