clear all, close all, clc
b=3
d=2
p=4
n=0.02;
c1=50;
c2=11
m=5;
N1=126
N2=42
N3=30
N4=1;
tspan = [0:1:365];
y0 = [N1 N2 N3 N4 0];
[t,y] = ode45(@(t,y)odefcn(y,b,d,p,n,c1,c2,m),tspan,y0);
plot(t,y)
function dydt = odefcn(x,b,d,p,n,c1,c2,m)
N1=126
N2=42
N3=30
N4=1;
dydt = zeros(5,1);
dydt(1) = -log(x(3))*x(3)+b*x(4)-d*n*(1);
dydt(2) =log(x(3))*x(3)-b*x(2)+d*n*(1);
dydt(3) = b*x(2)-p*x(3);
dydt(4) = p*x(3)-b*x(4);
dydt(5) = n*(c1*x(1)-c2*x(2)-m*x(4));
end