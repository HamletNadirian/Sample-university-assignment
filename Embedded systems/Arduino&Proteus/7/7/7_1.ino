#include <LiquidCrystal.h>
LiquidCrystal lcd(4, 5, 6, 7, 8, 9); // до виводів
void setup()
{ 
lcd.begin(16,2);
lcd.println("CIT-36");
}
void loop()
{
  lcd.setCursor(0,1);
  lcd.println("Nadirian H.O");

}
