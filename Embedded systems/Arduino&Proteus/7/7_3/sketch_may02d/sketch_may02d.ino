

#include <LiquidCrystal.h>
LiquidCrystal lcd(4, 5, 6, 7, 8, 9); // до виводів RS, E, DB4,DB5, DB6, DB7

void setup() {
  lcd.begin(16, 2);
  // put your setup code here, to run once:
int zz = 12;
lcd.setCursor(0, 0);
lcd.print("Bin:");
lcd.print(zz, BIN); // вивести число 170 у двійковому виді
lcd.setCursor(0, 1);
lcd.print("Hex:");
lcd.print(zz,HEX);
lcd.print(". Dec:");
lcd.print(zz,DEC);

}

void loop() {
  // put your main code here, to run repeatedly:

}
