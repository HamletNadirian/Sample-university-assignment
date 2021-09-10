t0 = 0;
tfinal = 25;
y0 = [2 1];
[t,y] = euler(@lotka_volterra,[t0 tfinal],y0,250);
plot(t,y)
%title('Особи первого вида')
xlabel('t')
ylabel('Біомаса')
%legend('Особи второго вида')
legend('Особи первого вида', 'Особи второго вида ');
[M,I] = max(y);
s = sum(y,2);
s_max=max(s)
maxS=max(s(:));
row=find(s==maxS);
maxT=(row-1)/10 %-1 т.к. с 0 начинаеться ось
function [t,y] = euler(f,tspan,y0,N)
m = length(y0);
t0 = tspan(1);
tf = tspan(2);
h = (tf-t0)/N;
t = linspace(t0,tf,N+1);
y = zeros(m,N+1);
y(:,1) = y0';
for n=1:N
y(:,n+1) = y(:,n) + h*f(t(n),y(:,n));
end
t = t'; y = y';
end
function dx = lotka_volterra(t, x)
dx = [0; 0];
alpha = 1;
beta = .05;
delta = .02;
gamma = .5;
dx(1) = alpha * x(1) - beta * x(1) * x(2);
dx(2) = delta * x(1) * x(2) - gamma * x(2);