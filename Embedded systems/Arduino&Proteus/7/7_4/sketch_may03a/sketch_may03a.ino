

#include <LiquidCrystal.h>
LiquidCrystal lcd (4, 5, 6, 7, 8, 9); // до виводів RS, E, DB4, DB5, DB6, DB7
byte H[8] = { 
    B11011,
    B11011,
    B11011,
    B11111,
    B11011,
    B11011,
    B11011,
};

byte A[8]={
  B11100,
  B11011,
  B11011,
  B11111,
  B11011,
  B11011,
  B11011,
  B00000
};

byte M[8]={
  B00000,
  B00000,
  B00000,
  B11111,
  B10101,
  B10101,
  B10101,
  B00000
};

byte L[8]={
  B00100,
  B00100,
  B00100,
  B00100,
  B00100,
  B00100,
  B00100,
  B00000
};

byte E[8]={
  B00000,
  B11100,
  B10001,
  B11111,
  B10000,
  B11100,
  B00000,
  B00000
};

byte T[8]={
  B00100,
  B00100,
  B00100,
  B11100,
  B00100,
  B00100,
  B00110,
  B00000
};


void setup()
{
    lcd.createChar(3, M); 
    lcd.createChar(4, L);
    lcd.createChar(5, E); 
    lcd.createChar(6, T); 
    lcd.createChar(2, A);
    lcd.createChar(1, H);
    lcd.begin(16, 2);

}

void loop() {
    lcd.display();
    
    lcd.setCursor(0,0);
    lcd.write(1);
    
    lcd.setCursor(1,0);
    lcd.write(2);
    
    lcd.setCursor(2,0); 
    lcd.write(3);
    
    lcd.setCursor(3,0); 
    lcd.write(4);

    lcd.setCursor(4,0);
    lcd.write(5);
    lcd.setCursor(5,0);
    lcd.write(6);

    /*lcd.setCursor(7,0);
    lcd.write(7);
    lcd.setCursor(8,0);
    lcd.write(8);
   */

}
