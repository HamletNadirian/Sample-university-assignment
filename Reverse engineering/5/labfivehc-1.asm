include win64a.inc
include kernel32.inc
mySg1 segment READ WRITE EXECUTE alias("Ham")
;.data
CmdCommand db "avxtest.exe",0
mas1 real8 8.,18.,32.,50. ; массив чисел B
len2 equ ($-mas1)/type mas1
mas2 real8 256.,2.,2.,2.         ; a, c, d,e
Password db "nadirian" ; проверка только первых п€ти символов
len1 equ ($-Password)/type Password
Buf dq 8 ;
Err1 dq 0
Msg1 db "ѕароль совпал",0
Msg2 db "ѕароль не корректен",0
Title1 db "ѕроверка парол€",0
Title2 db "“сс,пароль Ќадир€н (на латинице в нижнем регистре)",0
stdout dq 0  ;
stdin dq 0  ;
cRead dq 0  ;
cWritten dq 0 ;
_a dd 0;
_b dd 0;
Msg db " ********",10,0 ;
mySg1 ends
.code
Pas1 proc
lea rsi,Password   ; адрес первого элемента строки
lea rdi,Buf  ; адрес второго элемента строки
mov rcx,len1
repe cmpsb   ; побайтно провер€етс€ len раз
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
invoke WinExec,addr CmdCommand,SW_SHOW  ; юзать SW_HIDE и окно будет скрыто
invoke WriteConsole,stdout,ADDR Msg,sizeof Msg,ADDR cWritten,0
invoke ReadConsole,stdin,ADDR Buf,8,ADDR cRead,0
invoke Pas1
.if (Err1==0);
invoke MessageBox,0,addr Msg1,addr Title1,MB_OK
.data
titll db "–езультат вычислени€ уравнени€ sqrt(a) Ц cd Ц sqrt(eb) .",0
res dq len1 DUP(0),0  ;
buf1 dd len1 DUP(0),0  ; буфер вывода сообщени€
ifmt db "Masm64 ћассив bi = 8., 18., 32., 50.",10,
9,"„исла: a, c, d, e  := 256, 2., 2.,2.",10,
"–езультаты : %d ,%d ,%d ,%d ",10,10,
"јвтор: HamletOs,фак.  »“-36, Ќ“” ’ѕ»",10,
9,0
.code 

mov ax,05EBh
jmp $ - 2  ; пропуск 2 байтов EB 05 и остановка на EB
;EB 05 Ц это jmp на 5 байтов вперед с учетом своих 2-х байтов
dec r9  ; пропускаем, 3 байта
mov rcx,len2

lea rdx,res
lea rbx,mas1
vmovsd xmm1,mas2[0]  ; xmm1 Ч a Ч переслать real8
vmovsd xmm2,mas2[8]  ; xmm2 Ч c
vmovsd xmm3,mas2[16] ; xmm3 Ч d
vmovsd xmm5,mas2[24];  ; xmm4 - e
vmulsd xmm3,xmm2,xmm3; ;xmm3 - c*d   
@@:
vmovsd xmm0,qword ptr[rbx]; - xmm0 - b
vsqrtsd xmm4,xmm4,xmm1  ;xmm4 - a
vsubsd xmm4,xmm4,xmm3  ;xmm4 - a-cd
vmulsd xmm6,xmm5,xmm0; xmm6 - (e*b)
vsqrtsd xmm6,xmm6,xmm6 ;xmm6 - sqrt(e*b) 
vsubsd xmm4,xmm4,xmm6; a-cd-sqrt(e*b)
;vcvttsd2si eax,xmm4      ; от
vcvttsd2si eax,xmm4   ; дл€ отладки
movsxd r15,eax ;
mov [rdx],eax ; сохранение результата
add rbx,8
add rdx,8

mov rax,09EB000000000000h
jmp $ + 9  ; перепрыгивает свои 2 байта и следующие 7
mov r15,1  ;
dec rcx

mov eax,06EB0000h
jmp $ - 2  ; пропуск 2 байтов 06 и EB и остановка на EB
; EB 06 Ц это jmp на 6 байтов вперед с учетом своих 2-х байтов
dec cl  ; пропускаем, 2 байта: FE C9
dec al      ; пропускаем, 2 байта: FE C8

jnz @b ; ссылка на предыдущую метку @@ (наверх)
mov ax,1eebh
jmp $-3
;jmp m4
m4:
invoke wsprintf,addr buf1,addr ifmt,res,res[8],res[16],res[24]
invoke MessageBox,0,addr buf1,addr titll,MB_OK 
.else
invoke MessageBox,0,addr Msg2,addr Title2,MB_ICONWARNING
.endif
invoke ExitProcess,0
WinMain endp
end