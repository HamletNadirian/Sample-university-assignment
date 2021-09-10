clear all % видалили все
disp('Розв’язуємо завдання 2') % заголовок задачі
syms x y Dy D2y % описали символічні змінні
%F=((Dy^2-4*Dy*cos(2.*x))+5*sin(3.*x)); % підінтегральна функція
%F
F=(Dy^2)+4*y^2+4*x^2*y+x*cos(x); % підінтегральна функція

x1=0; % граничні умови
y1=6;
x2=2;
y2=-30;
disp('Вихідні дані:')
fprintf(['Підінтегральна функція '...
  'F(x,y,y'')=%s\n'],char(F))
fprintf('Гранична умова зліва: y(%d)=%d\n',x1,y1)
fprintf('Гранична умова справа: y(%d)=%d\n',x2,y2)

dFdy1=simple(diff(F,Dy)); % Fy'
deqEuler=[char(dFdy1) '=C']; % склали рівняня
disp('Перший интеграл рівняння Ейлера:')
fprintf('%s\n',deqEuler)
Sol=dsolve(deqEuler,'x'); % розв’язуємо рівняння Ейлера
if length(Sol)~=1 % рішення немає або більше одного розв’язку
  error('Немає розв’зку або більш одного одного розв’язку!');
else
  disp('Загальний розв’язок рівняння Ейлера:')
  fprintf('y(x)=%s\n',char(Sol))
end

SolLeft=subs(Sol,x,sym(x1)); % підставили x1
SolRight=subs(Sol,x,sym(x2)); % підставили x2
EqLeft=[char(SolLeft) '=' char(sym(y1))]; % =y1
EqRight=[char(SolRight) '=' char(sym(y2))]; % =y2
disp('Рівняння для граничних умов:')
fprintf('%s\n',EqLeft,EqRight)

Con=solve(EqLeft,EqRight,'C,C2'); % розв’язуємо
C=Con.C; % прирівнюємо отримані розв’язки
C2=Con.C2; % символічним змінним C та C2
Sol22=vpa(eval(Sol),12); % підставили C2, C
disp('Рівняння екстремалі:')
fprintf('y(x)=%s\n',char(Sol22))

xpl=linspace(x1,x2); % задали масив абсцис
y22=subs(Sol22,x,xpl); % обчислили ординати
figure % фігура
plot(xpl,y22,'-r') % рисуємо графік червоною лінією
set(get(gcf,'CurrentAxes'),...
  'FontName','Times New Roman Cyr','FontSize',12)
title('\bfЗавдання 2') % заголовок
xlabel('\itx') % мітка осі OX
ylabel('\ity\rm(\itx\rm)') % мітка осі OY
Leg=diff(dFdy1,Dy); % Fy'y'
disp ('Достатня умова Лежандра:')
fprintf('Fy''y''=%s\n',char(Leg))

dFdy=diff(F,y); % обчислили Fy
dFdy1=diff(F,Dy); % обчислили Fy'
fprintf('Fy=%s\n',char(dFdy))
fprintf('Fy''=%s\n',char(dFdy1))

d_dFdy1_dx=diff(dFdy1,x); % Fxy'x
d_dFdy1_dy=diff(dFdy1,y); % Fyy'
d_dFdy1_dy1=diff(dFdy1,Dy); % Fy'y'-умова Лежандра
dFy1dx=d_dFdy1_dx+d_dFdy1_dy*Dy+d_dFdy1_dy1*D2y;
fprintf('dFy''/dx=%s\n',char(dFy1dx))
disp('Умова Лежандра:')
fprintf('Fy''y''=%s\n',char(d_dFdy1_dy1))


Euler=simple(dFdy-dFy1dx); % рівняння Ейлера
deqEuler=[char(Euler) '=0']; % в рядок додали =0
fprintf('Рівняння Ейлера:\n%s\n',deqEuler)

Sol=dsolve(deqEuler,'x'); % розв’язуємо рівняння Ейлера
if length(Sol)~=1 % розв’язання немає або більш одного
  error('Немає розв’язання або більш одного одного розв’язання!');
else
  disp('Загальне розв’язання рівняння Ейлера:')
  fprintf('y(x)=%s\n',char(Sol))
end

SolLeft=subs(Sol,x,x1); % підставили x1
SolRight=subs(Sol,x,x2); % підставили x2
EqLeft=[char(SolLeft) '=' char(sym(y1))]; % =y1
EqRight=[char(SolRight) '=' char(sym(y2))]; % =y2
disp('Рівняння для граничних умов:')
fprintf('%s\n',EqLeft,EqRight)


Fextr=subs(F,{y,Dy},{Sol22,diff(Sol22,x)});
Jextr=eval(int(Fextr,x,x1,x2))
ylin=(x-x1)*(y2-y1)/(x2-x1)+y1;
Flin=subs(F,{y,Dy},{ylin,diff(ylin,x)});
Jlin=eval(int(Flin,x,x1,x2))

xpl=linspace(x1,x2); % задаємо масив абсцис
y21=subs(Sol22,x,xpl); % обчислили ординати
figure % фігура
plot(xpl,y21,'-r') % рисуємо графік червоною лінією
set(get(gcf,'CurrentAxes'),...
  'FontName','Times New Roman Cyr','FontSize',12)
title('\bfЗавданя 1') % заголовок
xlabel('\itx') % мітка осі OX
ylabel('\ity\rm(\itx\rm)') % мітка вісі OY




