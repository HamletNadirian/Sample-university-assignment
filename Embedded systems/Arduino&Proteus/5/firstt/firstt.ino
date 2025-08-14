int led_yellow = 3;
int led_red = 5;

void setup() {
  // put your setup code here, to run once:
  pinMode(led_yellow,OUTPUT);
  pinMode(led_red,OUTPUT);

}

void loop() {
  // put your main code here, to run repeatedly:
  //YELLOW
  digitalWrite(led_yellow,HIGH);
  delay(200);
  digitalWrite(led_yellow,LOW);
  delay(200);
  //RED
  digitalWrite(led_red,HIGH);
  delay(800);
  digitalWrite(led_red,LOW);
  delay(1600);
}
