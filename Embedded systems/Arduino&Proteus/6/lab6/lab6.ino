void setup()
{
  for (int i=5;i<14;i++)
    pinMode(i,OUTPUT);
}
void loop()
{
  //р
  digitalWrite(8,HIGH);
  digitalWrite(5,HIGH);
  digitalWrite(10,HIGH);
  digitalWrite(9,HIGH);
  digitalWrite(11,HIGH);
  delay(1000);
  off();
  delay(1000);
  //Н
  digitalWrite(5,HIGH);
  digitalWrite(8,HIGH);
  digitalWrite(9,HIGH);
  digitalWrite(6,HIGH);
  digitalWrite(11,HIGH);

  delay(1000);
  off();
  delay(1000);
  //1
  digitalWrite(9,HIGH);
  digitalWrite(6,HIGH);
  delay(1000);
  off();
  delay(1000);
  //2
  digitalWrite(9,HIGH);
  digitalWrite(11,HIGH);
  digitalWrite(10,HIGH);
  digitalWrite(7,HIGH);
  digitalWrite(5,HIGH);

  delay(1000);
  off();
  delay(1000);
  //3
  digitalWrite(9,HIGH);
  digitalWrite(11,HIGH);
  digitalWrite(10,HIGH);
  digitalWrite(6,HIGH);
  digitalWrite(7,HIGH);
  delay(1000);
  off();
  delay(1000);
  //4
  digitalWrite(8,HIGH);
  digitalWrite(9,HIGH);
  digitalWrite(6,HIGH);
  digitalWrite(11,HIGH);

  delay(1000);
  off();
  delay(1000);
}
void off(){
  for(int i=5;i<14;i++)
  digitalWrite(i,LOW);
}
