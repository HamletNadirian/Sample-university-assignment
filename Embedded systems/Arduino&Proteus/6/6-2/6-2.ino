#define DELAY 1
#define START_PIN 2
#define END_PIN 13
#define FIRST_CONTROL_PIN 9
#define ZERO 0
#define AMOUNT_NUMBERS 4

int numArray[AMOUNT_NUMBERS][7] = {  { 1, 0, 0, 0, 1, 0, 0 },
  { 0, 0, 0, 0, 0, 0, 1 },    
  { 0, 1, 1, 0, 0, 0, 1 },    
  { 0, 1, 0, 0, 0, 0, 0 }   
};   

void setup()
{
  for (int i = START_PIN; i <= END_PIN; i++)
    pinMode(i, OUTPUT);
}

void loop()
{
  for(int i = FIRST_CONTROL_PIN,j = ZERO; i <= END_PIN; i++, j++) {
    for (int k = START_PIN; k < FIRST_CONTROL_PIN; k++) {
      digitalWrite(k, ((numArray[j][k-2] == 1) ? LOW : HIGH));
    }
    digitalWrite(FIRST_CONTROL_PIN+j, LOW);
    delay(DELAY);
    digitalWrite(FIRST_CONTROL_PIN+j, HIGH);
  }
  
}
