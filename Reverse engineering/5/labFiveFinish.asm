include win64a.inc
include kernel32.inc

.data
CmdCommand db "avxtest.exe",0
mas1 real8 8.,18.,32.,50. ; массив чисел B
len2 equ ($-mas1)/type mas1
mas2 real8 256.,2.,2.,2.         ; a, c, d,e
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
invoke WinExec,addr CmdCommand,SW_SHOW  ; юзать SW_HIDE и окно будет скрыто
invoke WriteConsole,stdout,ADDR Msg,sizeof Msg,ADDR cWritten,0
invoke ReadConsole,stdin,ADDR Buf,8,ADDR cRead,0
invoke Pas1
.if (Err1==0);
invoke MessageBox,0,addr Msg1,addr Title1,MB_OK
.data

titll db "Результат вычисления уравнения sqrt(a) – cd – sqrt(eb) .",0
res dq len1 DUP(0),0  ;
buf1 dd len1 DUP(0),0  ; буфер вывода сообщения
ifmt db "Masm64 Массив bi = 1., 2., 3., 4.",10,
9,"Числа: a, c, d, e  := 100., 2., 2.,4.",10,
"Результаты : %d ,%d ,%d ,%d ",10,10,
"Автор: HamletOs,фак. КИТ-36, НТУ ХПИ",10,
9,0
.code 

mov rcx,len2
lea rdx,res
lea rbx,mas1
vmovsd xmm1,mas2[0]  ; xmm1 — a — переслать real8
vmovsd xmm2,mas2[8]  ; xmm2 — c
vmovsd xmm3,mas2[16] ; xmm3 — d
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
vcvttsd2si eax,xmm4   ; для отладки
movsxd r15,eax ;
mov [rdx],eax ; сохранение результата
add rbx,8
add rdx,8
dec rcx
jnz @b ; ссылка на предыдущую метку @@ (наверх)
jz m4
m4:
invoke wsprintf,addr buf1,addr ifmt,res,res[8],res[16],res[24]
invoke MessageBox,0,addr buf1,addr titll,MB_OK 
.else
invoke MessageBox,0,addr Msg2,addr Title2,MB_ICONWARNING

.endif
invoke ExitProcess,0
WinMain endp
end