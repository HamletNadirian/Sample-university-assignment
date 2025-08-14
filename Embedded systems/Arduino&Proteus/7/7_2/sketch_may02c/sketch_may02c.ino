#include <LiquidCrystal.h>
LiquidCrystal lcd(4, 5, 6, 7, 8, 9); // до виводів RS, E, DB4,DB5, DB6, DB7
void setup()
{ 
float e = 2.7182818284;
lcd.print("e: ");
lcd.print(e, 2);
/*lcd.begin(16,2);
lcd.println("CIT-36");*/
}
void loop()
{
/*  lcd.setCursor(0,1);
  lcd.println("Nadirian H.O");
*/
}
