;a/cd Ц ?e + b 100/2*2 - 4 +5 =26 
; masm64. „исла а Ї{a1,a2,a3,a4} заданы массивом
; и имеют размерность Real8. ¬ычислить уравнение a/cd Ц sqart(e) + b
include win64a.inc  ;  подключаемые библиотеки
.data
mas1 real8 1.,2.,3.,4. ; массив чисел B
len1 equ ($-mas1)/type mas1
mas2 real8 100.,2.,2.,4.         ; a, c, d,e
tit1 db "–езультат вычислени€ уравнени€ a/cd Ц sqart(e) + b .",0
res dq len1 DUP(0),0  ;
buf1 dd len1 DUP(0),0  ; буфер вывода сообщени€
ifmt db "Masm64 ћассив bi = 1., 2., 3., 4.",10,
9,"„исла: a, c, d, e  := 100., 2., 2.,4.",10,
"–езультаты : %d ,%d ,%d ,%d ",10,10,
"јвтор: HamletOs,фак.  »“-36, Ќ“” ’ѕ»",10,
9,0
.code               ; уравнение a/cd Ц ?e + b
WinMain proc
sub rsp,28h; cтек: 28h=32d+8; 8 Ч возврат
mov rbp,rsp
mov rcx,len1
lea rdx,res
lea rbx,mas1
vmovsd xmm1,mas2[0]  ; xmm1 Ч a Ч переслать real8
vmovsd xmm2,mas2[8]  ; xmm2 Ч c
vmovsd xmm3,mas2[16] ; xmm3 Ч d
vmovsd xmm5,mas2[24];  ; xmm4 - e
vmulsd xmm3,xmm2,xmm3; ;xmm3 - c*d   
;vdivsd xmm3,xmm2,xmm3 ; c/d
;vaddsd xmm5,xmm5,xmm3 ;c/d+e
@@:
vmovsd xmm0,qword ptr[rbx]; - xmm0 - b
vmovsd xmm4,xmm4,xmm1  ;xmm4 - a
vdivsd xmm4,xmm4,xmm3  ;xmm4 - a/cd
;vmovsd xmm6,xmm6,xmm5 ;xmm6 - e
vsqrtsd xmm6,xmm6,xmm5 ;xmm6 - sqrt(e)
;vaddsd xmm6,xmm6,xmm0; ;xmm6 - (sqrt(e)+b)
vsubsd xmm4,xmm4,xmm6; a/cd-sqrt(e)
vaddsd xmm4,xmm4,xmm0;a/cd-sqrt(e)+b
vcvttsd2si eax,xmm4      ; от
vcvttsd2si eax,xmm4   ; дл€ отладки
movsxd r15,eax ;
mov [rdx],eax ; сохранение результата
add rbx,8
add rdx,8
dec rcx
jnz @b ; ссылка на предыдущую метку @@ (наверх)
invoke wsprintf,addr buf1,addr ifmt,res,res[8],res[16],res[24]
invoke MessageBox,0,addr buf1,addr tit1,MB_ICONINFORMATION
invoke ExitProcess,0
WinMain endp
end