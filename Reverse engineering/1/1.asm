include win64a.inc
.data
DATE1 STRUCT ; тип данных СТРУКТУРА с именем DATE1
elem1 dd ? ; имя первого поля структуры
elem2 dd ? ; имя второго поля структуры 
elem3 dd ? ; имя третьего поля структуры 
elem4 dd ? ; имя четвертого поля структуры 
elem5 dd ? ; имя четвертого поля структуры 
elem6 dd ? ; имя четвертого поля структуры 
elem7 dd ? ; имя четвертого поля структуры 
DATE1 ENDS
Password db "nadirian" ; проверка только первых пяти символов
len1 equ ($-Password)/type Password
Buf dq 8 ;
Err1 dq 0
Msg1 db "Пароль совпал",0
Msg2 db "Пароль не корректен",0
Title1 db "Проверка пароля",0
Title2 db "Тсс,пароль Надирян (на латинице в нижнем регистре)",0
stdout dq 0  ;
stdin dq 0  ;
cRead dq 0  ;
cWritten dq 0 ;
_a dd 0;
_b dd 0;
Msg db "********",10,0 ;
.code
Pas1 proc
lea rsi,Password   ; адрес первого элемента строки
lea rdi,Buf  ; адрес второго элемента строки
mov rcx,len1
repe cmpsb   ; побайтно проверяется len раз
jz m2    ;
inc Err1   ; счетчик несовпадений
m2:
ret
Pas1 endp
WinMain proc
sub rsp,28h;
mov rbp,rsp
invoke GetStdHandle,STD_OUTPUT_HANDLE
mov stdout,rax
invoke GetStdHandle,STD_INPUT_HANDLE
mov stdin,rax
invoke WriteConsole,stdout,ADDR Msg,sizeof Msg,ADDR cWritten,0
invoke ReadConsole,stdin,ADDR Buf,8,ADDR cRead,0
invoke Pas1
.if (Err1==0);
invoke MessageBox,0,addr Msg1,addr Title1,MB_OK
.data
dir db 256 dup(0) ;переменная для хранения пути к текущей директории
align 8 ; выравнивание памяти по адресам, кратным 8 байтам
str1 DATE1 <2,2,2,2,1,1,1> ; структура с именем str1
str2 DATE1 <0,0,0,1,1,1,1> ; структура с именем str2
str3 DATE1 <2,2,2,1,1,1,0> ; структура с именем str2
str4 DATE1 <0,0,0,0,0,0,0> ; структура с именем str2
titl1 db "Вычисление..",0
buf1 dq 10 dup(0);
ifmt db "Задана матрица:",0dh,0ah,0ah,\
"2 2 2 2 1 1 1",0dh,0ah,\
"0 0 0 1 1 1 1",0dh,0ah,\
"2 2 2 1 1 1 0",0dh,0ah,\
"0 0 0 0 0 0 0",0dh,0ah,0ah,\
"Результат сложения четных элементов строки №1 массива =%d",0dh,0ah,\
"Результат сложения нечетных элементов строки №3 = %d",0ah,\
"Путь к файлу %s ",0
.code 

xor edi,edi ; заполнение нулями
mov ebx,1 ; загрузка количества строк
lea rsi,str1 ; загрузка адреса первой строки структуры
m5: mov ecx,7 ; количество элементов в строке
m3: mov eax,dword ptr[rsi] ; загрузка элемента из строки структуры
test eax,1 
je m1
jnz m2
m1: add edi,eax
    mov _a,edi     ; сложение нечетных элементов строки структуры
m2: add rsi,4 ; подготовка адреса нового элемента
loop m3
dec ebx ; ebx := ebx 1
jz m9
;jz m4 ; если ebx = 0 (z = 1), то переход в заключение
jmp m5 ; переход на новый цикл 
;m4:

m9:
xor edi,edi
xor ebx,ebx
xor rsi,rsi
xor ecx,ecx
xor eax,eax
mov ebx,1 ; загрузка количества строк
lea rsi,str3 ; загрузка адреса первой строки структуры
m15: mov ecx,7 ; количество элементов в строке
m13: mov eax,dword ptr[rsi] ; загрузка элемента из строки структуры
test eax,1
jnz m11
jz m12
m11: add edi,eax
    mov _b,edi     ; сложение нечетных элементов строки структуры
m12: add rsi,4 ; подготовка адреса нового элемента
loop m13
dec ebx ; ebx := ebx 1
jz m4

m4:
invoke GetCurrentDirectory,255,addr dir; получение директории
invoke wsprintf,ADDR buf1,ADDR ifmt,_a,_b,addr dir
invoke MessageBox,0,addr buf1,addr titl1,MB_OK 
.else
invoke MessageBox,0,addr Msg2,addr Title2,MB_ICONWARNING 
.endif
invoke ExitProcess,0
WinMain endp
end