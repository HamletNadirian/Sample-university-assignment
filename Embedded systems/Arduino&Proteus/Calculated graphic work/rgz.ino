
#include <LiquidCrystal.h>
// include DHT library code
#include "DHT.h"
#define DHT22_PIN 2     // what pin on the arduino is the DHT22 data line connected to


DHT22 dht(DHT22_PIN);


void setup() {
  Serial.begin(9600); 
  Serial.println("DHT22 Humidity - Temperature Sensor");
  Serial.println("RH\t\tTemp (C)\tTemp (F)\tHeat Index (C)\t Heat Index (F)");
 
  dht.begin();
}

void loop() {
  // Reading temperature or humidity takes about 250 milliseconds!
  // Sensor readings may also be up to 2 seconds 'old' (its a very slow sensor)
  dht.readHumidity();
  dht.readTemperature();
  
  // Check if any reads failed and exit early (to try again).
  if (isnan(dht.humidity) || isnan(dht.temperature_C)) {
    Serial.println("DHT sensor read failure!");
    return;
  }
 
  Serial.print(dht.humidity); Serial.print(" %\t\t");
  Serial.print(dht.temperature_C); Serial.print(" *C\t");
  Serial.print(dht.temperature_F); Serial.print(" *F\t");
  Serial.print(dht.computeHeatIndex_C()); Serial.print(" *C\t");
  Serial.print(dht.computeHeatIndex_F()); Serial.println(" *F");
  
  // Wait a few seconds between measurements. The DHT22 should not be read at a higher frequency of
  // about once every 2 seconds. So we add a 3 second delay to cover this.
  delay(3000);
}
