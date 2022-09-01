/**
System for automated design of combined encryption algorithms
Including libraries to test my AES128 in combination with other ciphers.
**/
#include <cstdio>
#include <iostream>
#include <list>

#include "..\li\osrng.h"
using CryptoPP::AutoSeededRandomPool;
#include<conio.h>
#include<iostream>
#include  <iomanip>
#include <cstring>

#include<string>
#include <sstream>
#include <cstdlib>
#include <algorithm>
#include <iostream>
using std::cout;
using std::cerr;
using std::endl;
#include <iomanip>
#include <string>
using std::string;
#include <fstream> // подключаем файлы
#include <iterator>
#include <cstdlib>
using std::exit;
#include "..\li\cryptlib.h"
#include <iomanip>

using CryptoPP::Exception;

#include "..\li\hex.h"
using CryptoPP::HexEncoder;
using CryptoPP::HexDecoder;
#include "..\li\filters.h"
#include "..\li\rsa.h"
using CryptoPP::StringSink;
using CryptoPP::StringSource;
using CryptoPP::StreamTransformationFilter;

#include "..\li\des.h"
using CryptoPP::DES_EDE2;
using CryptoPP::RSA;

#include "..\li\modes.h"
using CryptoPP::CBC_Mode;
#include "..\li\secblock.h"
#include "..\li\3way.h"
using CryptoPP::SecByteBlock;
#include <iostream>
#include <string>
#include "..\li\aes.h"
using CryptoPP::HexEncoder;
#include <cstdlib>
using std::exit;
using CryptoPP::AES;
using CryptoPP::ECB_Mode;
using CryptoPP::ArraySink;
#include "..\li\serpent.h"
#include "..\li\twofish.h"
using CryptoPP::Twofish;
using CryptoPP::TwofishEncryption;
using CryptoPP::TwofishDecryption;

#include "..\li\hex.h"
using CryptoPP::Serpent;
using CryptoPP::SerpentDecryption;
using CryptoPP::SerpentEncryption;
#include "..\li\mars.h"
#include "..\li\cast.h"
using CryptoPP::CAST256;
using CryptoPP::MARS;
using CryptoPP::MARSEncryption;
using CryptoPP::MARSDecryption;
using CryptoPP::StringSink;
using CryptoPP::StringSource;
using CryptoPP::StreamTransformationFilter;
#include <string>
#include"..\li\md5.h"
using CryptoPP::MD5;
#define CRYPTOPP_ENABLE_NAMESPACE_WEAK 1
#include"..\li\sha.h"
using CryptoPP::SHA256;
#include"..\li\sha3.h"
using CryptoPP::SHA3;
#include "..\li\rsa.h"
using CryptoPP::RSA;
#include "..\li\camellia.h"
using CryptoPP::Camellia;
using CryptoPP::CamelliaEncryption;
using CryptoPP::CamelliaDecryption;

#include "..\li\osrng.h"
using CryptoPP::AutoSeededRandomPool;

#include "..\li\hrtimer.h"
using CryptoPP::TimerBase;
using CryptoPP::ThreadUserTimer;


#include <iostream>
using std::cout;
using std::cerr;
using std::endl;

#include <string>
using std::string;

#include <iomanip>
using std::fixed;
using std::setprecision;
#include "..\li\cryptlib.h"
#include "..\li\files.h"
#include "..\li\idea.h"
using CryptoPP::IDEA;

#include <sstream>
using std::stringstream;
#include <stdexcept>
using std::runtime_error;

#include "..\li\whrlpool.h"
using CryptoPP::Whirlpool;
#include <stdexcept>
#include <vector>
#include <sstream>

#include <bitset>
#include<conio.h>
#include<iostream>
#include  <iomanip>
#include<string>
#include <sstream>
#include "aes_128.h"
#include <cstdlib>
#include <algorithm>
#define _CRT_SECURE_NO_WARNINGS
#include <cstring>
using namespace std;
unsigned int blockBytesLen = 16* sizeof(word8);//4 * 4 * sizoef(word8);

	/*unsigned int enterSize=16;
		int sizePrint=16;*/
unsigned int enterSize;
		int sizePrint;
const unsigned int BLOCK_BYTES_LENGTH = 16 * sizeof(unsigned char);
int Nb = 4;/*Кількість стовпців (32-бітні слова). Для цього стандарт, Nb = 4. (Також див. розділ 6.3.)*/
int Nr = 10;/*Кількість раундів*/
int Nk = 4;/*Кількість 32-бітних слів, що містять ключ шифру*/
void print(string str, word8 state[]);

word8 mul(word8 a, word8 b)
{
	
	if (a && b)
		return Alogtable[(Logtable[a] + Logtable[b]) % 255];
	else
		return 0;
}


void AES_SubBytes(word8* state)
{
	int i;
	/* use normal array */
	for (i = 0; i < 16; i++) {
		state[i] = S_box[state[i]];
	}
}

void Inv_SubBytes(word8* state) {
	for (int i = 0; i < 16; i++) {
		state[i] = S_invers[state[i]];
	}
}
static word8 RoundKey_var[176];


void MixColumns(word8 c[])
{
	word8 stateD[4][4];
	for (int i = 0; i < 4; i++)
	{
		for (int j = 0; j < 4; j++)
		{
			stateD[i][j] = c[4 * i + j];
		}
	}
	for (int i = 0; i < 4; i++)
	{
		for (int j = 0; j < 4; j++)
		{
			stateD[j][i] = c[4 * i + j];
		}
	}
	for (int row = 0; row < 4; row++)
	{
		for (int col = 0; col < 4; col++)
		{
			c[row * 4 + col] = stateD[row][col];
		}
	}

	word8 a[4][4];
	for (int i = 0; i < 4; i++)
	{
		for (int j = 0; j < 4; j++)
		{
			a[i][j] = c[4 * i + j];
		}
	}
	/*Mix the four bytes of every column in a linear way*/
	word8 b[4][MAXBC];
	int i, j;
	for (j = 0; j < 4; j++)
		for (i = 0; i < 4; i++)
			b[i][j] = mul(2, a[i][j])
			^ mul(3, a[(i + 1) % 4][j])
			^ a[(i + 2) % 4][j]
			^ a[(i + 3) % 4][j];
	for (i = 0; i < 4; i++) {
		for (j = 0; j < 4; j++) {
			a[i][j] = b[i][j];

		}

	}
	int k = 0;
	for (i = 0; i < 4; i++)
	{
		for (j = 0; j < 4; j++)
		{
			c[k] = a[j][i];
			k++;

		}

	}
}
void InvMixColumns(word8 c[])
{
	word8 stateD[4][4];
	for (int i = 0; i < 4; i++)
	{
		for (int j = 0; j < 4; j++)
		{
			stateD[i][j] = c[4 * i + j];
		}
	}
	for (int i = 0; i < 4; i++)
	{
		for (int j = 0; j < 4; j++)
		{
			stateD[j][i] = c[4 * i + j];
		}
	}
	for (int row = 0; row < 4; row++)
	{
		for (int col = 0; col < 4; col++)
		{
			c[row * 4 + col] = stateD[row][col];
		}
	}

	word8 a[4][4];
	for (int i = 0; i < 4; i++)
	{
		for (int j = 0; j < 4; j++)
		{
			a[i][j] = c[4 * i + j];
		}
	}

	word8 b[4][MAXBC];
	int i, j;
	for (j = 0; j < 4; j++)
		for (i = 0; i < 4; i++)
			b[i][j] = mul(0xe, a[i][j])
			^ mul(0xb, a[(i + 1) % 4][j])
			^ mul(0xd, a[(i + 2) % 4][j])
			^ mul(0x9, a[(i + 3) % 4][j]);
	for (i = 0; i < 4; i++)
		for (j = 0; j < 4; j++)
			a[i][j] = b[i][j];
	int k = 0;
	for (i = 0; i < 4; i++)
	{
		for (j = 0; j < 4; j++)
		{
			c[k] = a[j][i];
			k++;

		}
	}
}
static word8 RotWord(word8 w)
{
	word8 temp = w >> 24;
	temp = (w << 8 ^ temp);
	return temp;
}
void ShiftRows(word8* state)
{
	word8 t;

	/* 2nd row */
	t = state[1];
	state[1] = state[5];
	state[5] = state[9];
	state[9] = state[13];
	state[13] = t;

	/* 3rd row */
	t = state[2]; state[2] = state[10]; state[10] = t;
	t = state[6]; state[6] = state[14]; state[14] = t;

	/* 4th row */
	t = state[15]; state[15] = state[11]; state[11] = state[7];
	state[7] = state[3]; state[3] = t;
}

void InvShiftRows(word8* state) {
	word8 t;
	/*2nd row*/
	t = state[13];
	state[13] = state[9];
	state[9] = state[5];
	state[5] = state[1];
	state[1] = t;

	/*3nd row*/
	t = state[14];
	state[14] = state[6];
	state[6] = t;
	t = state[10];
	state[10] = state[2];
	state[2] = t;

	/*4th row*/
	t = state[3];
	state[3] = state[7];
	state[7] = state[11];
	state[11] = state[15];
	state[15] = t;

}

void sub_word(word8* w) {

	word8 i;

	for (i = 0; i < 4; i++) {
		w[i] = S_box[16 * ((w[i] & 0xf0) >> 4) + (w[i] & 0x0f)];
	}
}

void rot_word(word8* w) {
	word8 tmp;
	word8 i;
	tmp = w[0];
	for (i = 0; i < 3; i++) {
		w[i] = w[i + 1];
	}

	w[3] = tmp;
}
void coef_add(word8 a[], word8 b[], word8 d[]) {

	d[0] = a[0] ^ b[0];
	d[1] = a[1] ^ b[1];
	d[2] = a[2] ^ b[2];
	d[3] = a[3] ^ b[3];
}
word8 R[] = { 0x02, 0x00, 0x00, 0x00 };
word8 gmult(word8 a, word8 b) {

	word8 p = 0, i = 0, hbs = 0;

	for (i = 0; i < 8; i++) {
		if (b & 1) {
			p ^= a;
		}

		hbs = a & 0x80;
		a <<= 1;
		if (hbs) a ^= 0x1b;
		b >>= 1;
	}

	return (word8)p;
}
word8* Rcon(word8 i) {

	if (i == 1) {
		R[0] = 0x01; // x^(1-1) = x^0 = 1
	}
	else if (i > 1) {
		R[0] = 0x02;
		i--;
		while (i - 1 > 0) {
			R[0] = gmult(R[0], 0x02);
			i--;
		}
	}

	return R;
}

/*Алгоритм AES приймає ключ і виконує процедуру розширення ключа.
Розширення ключа генерує загальну кількість Nb*(Nr + 1) слів: алгоритм вимагає
початковий набір слів Nb, і кожен з Nr раундів вимагає Nb слів ключових даних.*/

void key_expansion(word8 key[], word8 w[]) {


	word8 tmp[4];
	word8 i, j;
	word8 len = Nb * (Nr + 1); //Розмір 

	/*Копіювання значення основного ключа основного ключа в таблицю байтів w [].*/
	for (i = 0; i < Nk; i++) {
		w[4 * i + 0] = key[4 * i + 0];
		w[4 * i + 1] = key[4 * i + 1];
		w[4 * i + 2] = key[4 * i + 2];
		w[4 * i + 3] = key[4 * i + 3];
	}

	for (i = Nk; i < len; i++) {
		// w[j-4]
		tmp[0] = w[4 * (i - 1) + 0];
		tmp[1] = w[4 * (i - 1) + 1];
		tmp[2] = w[4 * (i - 1) + 2];
		tmp[3] = w[4 * (i - 1) + 3];


		if (i % Nk == 0) {
			rot_word(tmp);
			sub_word(tmp);
			coef_add(tmp, Rcon(i / Nk), tmp);

		}
		else if (Nk > 6 && i % Nk == 4) {

			sub_word(tmp);


		}
		w[4 * i + 0] = w[4 * (i - Nk) + 0] ^ tmp[0];
		w[4 * i + 1] = w[4 * (i - Nk) + 1] ^ tmp[1];
		w[4 * i + 2] = w[4 * (i - Nk) + 2] ^ tmp[2];
		w[4 * i + 3] = w[4 * (i - Nk) + 3] ^ tmp[3];

	}
}

void invkey_expansion(word8* key, word8* w) {


	word8 tmp[4];
	word8 i, j;
	word8 len = Nb * (Nr + 1); //Розмір 
	/*Копіювання значення основного ключа основного ключа в таблицю байтів w [].*/
	for (i = 0; i < Nk; i++) {
		w[4 * i + 0] = key[4 * i + 0];
		w[4 * i + 1] = key[4 * i + 1];
		w[4 * i + 2] = key[4 * i + 2];
		w[4 * i + 3] = key[4 * i + 3];
	}

	for (i = Nk; i < len; i++) {
		// w[j-4]
		tmp[0] = w[4 * (i - 1) + 0];
		tmp[1] = w[4 * (i - 1) + 1];
		tmp[2] = w[4 * (i - 1) + 2];
		tmp[3] = w[4 * (i - 1) + 3];

		if (i % Nk == 0) {
			rot_word(tmp);
			sub_word(tmp);
			coef_add(tmp, Rcon(i / Nk), tmp);

		}
		else if (Nk > 6 && i % Nk == 4) {

			sub_word(tmp);
		}

		w[4 * i + 0] = w[4 * (i - Nk) + 0] ^ tmp[0];
		w[4 * i + 1] = w[4 * (i - Nk) + 1] ^ tmp[1];
		w[4 * i + 2] = w[4 * (i - Nk) + 2] ^ tmp[2];
		w[4 * i + 3] = w[4 * (i - Nk) + 3] ^ tmp[3];
		InvMixColumns(w);
	}
}

void RoundKey(word8* key, word8 offset, word8* state)
{

	for (int i = 0; i < 16; i++) {
		state[i] ^= key[offset + i];

	}
}

void ecnryption(word8 key[16], word8 state[16], word8 output[16]) {
	
	word8 round;
	word8 ciphertext[176]; 
	cout<<"key"<<endl;
	for (int i = 0; i < 16; i++)
		printf("%02hhx ", key[i]); cout << endl;
	cout << "in:" << endl;
	for (int i = 0; i < 16; i++)
		printf("%02hhx ", state[i]); cout << endl;
	RoundKey(key, 0, state);
	for (int r = 1; r <= 9; ++r)
	{
		AES_SubBytes(state);
		ShiftRows(state);
		MixColumns(state);
		RoundKey(key, 16 * r, state);
	}

	AES_SubBytes(state);
	ShiftRows(state);
	RoundKey(key, 16 * 10, state);
	for (int i = 0; i < 16; i++)
	{
		output[i] = state[i];	
	

	}		
	printf("\n");
	printf("encccccccc\n"); for (int i = 0; i < 16; i++)
	{
		printf("%02hhx ", output[i]);
		
	}	printf("\n");	printf("\n");
	printf("\n");


}


void decryption(word8 key[], word8 state[],word8 output[]) {

	word8 round;
	word8 ciphertext[176];
	RoundKey(key, 160, state);
	
	for (int i = 9; i >= 1; --i)
	{

		InvShiftRows(state);
		Inv_SubBytes(state);
		RoundKey(key, i * 16, state);
		InvMixColumns(state);
	}

	InvShiftRows(state);
	Inv_SubBytes(state);
	RoundKey(key, 0, state);

	for (int i = 0; i < 16; i++)
	{
		output[i] = state[i];
	}

}
void print(string str, word8 state[]) {
	printf("\n");
	for (int i = 0; i < 16; i++)
		printf("%02hhx ", state[i]);
	printf("\n");
}



void print(word8 in[], word8 out[])
{
	int i, j;
	for (i = 0; i < 4; i++) {
		for (j = 0; j < 4; j++)
			printf("%02x ", in[i + (j << 2)]);
		printf("\t");
		for (j = 0; j < 4; j++)
			printf("%02x ", out[i + (j << 2)]);
		printf("\n");
	}
	printf("\n");
}

void EncAes(word8 key[], word8 state[], word8 output[]) {
	word8 w[(4 * (10 + 1) * 4)];
	key_expansion(key, w);
	ecnryption(w, state, output);

}
void DecAes(word8 key[], word8 state[], word8 output[]) {
	word8 w[(4 * (10 + 1) * 4)];
	key_expansion(key, w);
	decryption(w, state, output);

}
void checkSize(string &str){ 

	if (str.length() <= 15) {

		for (int i = str.length(); i <= 16; i++)
		{
			str.push_back(0);
		}
	}
}

word8* encryption_AES(word8 key[16],word8 plain[],word8 *cipher){
	cout<<"~~~~~~~~~~LIB"<<endl;
try {

    ECB_Mode<AES>::Encryption _ENCTYPTION;
	_ENCTYPTION.SetKey( key, 16 );

	StringSource ss1 ( plain, sizePrint, true, new StreamTransformationFilter( _ENCTYPTION, new ArraySink( cipher, sizePrint ) ) );
    }
    catch( CryptoPP::Exception& _ENCTYPTION ) {
        cerr << _ENCTYPTION.what() << endl;
        exit(1);
    }


	for (int i = 0; i < enterSize; i++)
	{
		printf("%c",cipher[i]);
	}
	return cipher;
}

word8* decryption_AES(word8 key[16],word8 *cipher,word8 *cipher_plain){

	try {

    ECB_Mode<AES>::Decryption d;
        d.SetKey( key, 16 );

        StringSource ss3 ( cipher, sizePrint, true, new StreamTransformationFilter( d, new ArraySink( cipher_plain, sizePrint ) ) );
    }
    catch( CryptoPP::Exception& d ) {
    }

	for (int i = 0; i < enterSize; i++)
	{
		printf("%c",cipher_plain[i]);
	}
			return cipher_plain;

}

word8* encryption_Serpent(word8 key[16],word8 *plain,word8 *cipher){
try {

    ECB_Mode<Serpent>::Encryption _ENCTYPTION;
	_ENCTYPTION.SetKey( key, 16 );
			cout<<"encryption_Serpent"<<endl;

	StringSource ss1 ( plain, sizePrint, true, new StreamTransformationFilter( _ENCTYPTION, new ArraySink( cipher, sizePrint ) ) );
    }
    catch( CryptoPP::Exception& _ENCTYPTION ) {
        cerr << _ENCTYPTION.what() << endl;
        exit(1);
    }

	
	return cipher;
}

word8* decryption_Serpent(word8 key[16],word8 *cipher,word8 *cipher_plain){

	try {

    ECB_Mode<Serpent>::Decryption d;
        d.SetKey( key, 16 );
		cout<<"decryption_Serpent"<<endl;
		StringSource ss3 (cipher, sizePrint, true, new StreamTransformationFilter( d, new ArraySink(cipher_plain, sizePrint ) ) );
    }
    catch( CryptoPP::Exception& d ) {
    }	cout<<endl;

	for (int i = 0; i < enterSize; i++)
	{
		printf("%c",cipher_plain[i]);
	}
		cout<<endl;

	return cipher_plain;
}

word8* encryption_Twofish(word8 key[16],word8 *plain,word8 *cipher){
	try {
		
	cout<<"encryption_Twofish"<<endl;
    ECB_Mode<Twofish>::Encryption _ENCTYPTION;
	_ENCTYPTION.SetKey( key, 16 );
			cout<<"encryption_Twofish"<<endl;

        StringSource ss1 (plain, sizePrint, true, new StreamTransformationFilter( _ENCTYPTION, new ArraySink( cipher, sizePrint ) ) );
    }
    catch( CryptoPP::Exception& _ENCTYPTION ) {
        cerr << _ENCTYPTION.what() << endl;
        exit(1);
    }

	return cipher;
}

word8* decryption_Twofish(word8 key[16],word8 *cipher,word8 *cipher_plain){

	try {

    ECB_Mode<Twofish>::Decryption d;
        d.SetKey( key, 16 );

        StringSource ss3 ( cipher, sizePrint, true, new StreamTransformationFilter( d, new ArraySink(cipher_plain, sizePrint ) ) );
    }
    catch( CryptoPP::Exception& d ) {
    }	cout<<endl;

		for (int i = 0; i < enterSize; i++)
	{
		printf("%c",cipher_plain[i]);
	}	cout<<endl;

		return cipher_plain;
}

word8* paddingNulls(word8 in[], unsigned int inLength, unsigned int alignLength)
{
	word8* alignIn = new word8[alignLength];
	memcpy(alignIn, in, inLength);
	memset(alignIn + inLength, 0x00, alignLength - inLength);
	return alignIn;
}
unsigned int getPaddingLength(unsigned int len)
{
	unsigned int lengthWithPadding = (len / blockBytesLen);
	if (len % blockBytesLen) {
		lengthWithPadding++;
	}
	lengthWithPadding *= blockBytesLen;
	return lengthWithPadding;
}

void encBlock(word8 key[16], word8* state, word8 output[]) {
	
	word8 w[(4 * (10 + 1) * 4)];
	key_expansion(key, w);

	
	RoundKey(key, 0, state);
	for (int r = 1; r <= 9; ++r)
	{
		AES_SubBytes(state);
		ShiftRows(state);
		MixColumns(state);
		RoundKey(w, 16 * r, state);
	}

	AES_SubBytes(state);
	ShiftRows(state);
	RoundKey(w, 16 * 10, state);
	for (int i = 0; i < 16; i++)
	{
		output[i] = state[i];
	}

}

void decBlock(word8 key[], word8* state,word8 output[]){
	word8 w[(4 * (10 + 1) * 4)];
	key_expansion(key, w);
	RoundKey(w, 160, state);
	for (int i = 9; i >= 1; i--)
	{
		InvShiftRows(state);
		Inv_SubBytes(state);
		RoundKey(w, i * 16, state);
		InvMixColumns(state);
	}
	InvShiftRows(state);
	Inv_SubBytes(state);
	RoundKey(w, 0, state);
	for (int i = 0; i < 16; i++)
	{
		output[i] = state[i];
	}
}


word8 * DecryptECB(word8 in[], unsigned int inLen, word8 key[])
{
  word8 *out = new word8[inLen];
  for (unsigned int i = 0; i < inLen; i+= blockBytesLen)
  {
    decBlock(key,in + i, out + i);
  }
  
  return out;
}

word8 * EncryptECB(word8 in[], unsigned int inLen, word8 key[], unsigned int &outLen)
{
	outLen = getPaddingLength(inLen);
  word8 *alignIn  = paddingNulls(in, inLen, outLen);
  word8 *out = new word8[outLen];
  for (unsigned int i = 0; i < outLen; i+= blockBytesLen)
  {
    encBlock(key,alignIn + i, out + i );
  }
  return out;
}



word8* STRtoChar(string str){ /////////////////////////////////////////////////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!/////////////////////////////////////
	//unsigned char **val=new unsigned char[str.length()+1][];
	
	//unsigned char *val=new unsigned char[str.length()+1];
	unsigned char *val=new unsigned char[sizePrint];
	strcpy((char *)val,str.c_str());
	return val;
}

void checkSizeText(string str){
	int minusSize=BLOCK_BYTES_LENGTH-str.length();
	int addSize = str.size()+16-(str.size()%16);
	if(BLOCK_BYTES_LENGTH>str.size())
	{
		enterSize=BLOCK_BYTES_LENGTH-minusSize;
		sizePrint=16;

	}
	else if(BLOCK_BYTES_LENGTH<str.size())
	{
		enterSize=str.size();
		sizePrint=addSize;
	}
	else if(BLOCK_BYTES_LENGTH==str.size())
	{
		enterSize=16;
		sizePrint=16;
	}

	//cout<<"sizePrint"<< sizePrint<<endl;
	//cout<<"enterSize"<< enterSize<<endl;
}



word8 * aes128enc(word8 key[], word8 state[], word8 *output){
	//cout<<endl<<"ENTER aes128enc"<<endl;  
	unsigned int len = 0;			string cipher_string;
	ofstream out("enc.txt", ios::binary);
	if (!out) {
		cout << "Don't open file" << endl;
	}
		output = EncryptECB(state, sizePrint , key, len);
			/*for (int i = 0; i < sizePrint; i++)
			{
				printf("%hhx",output[i]);
			}  */ 
/*		StringSource ss2 (output, 16, true, new HexEncoder( new StringSink(cipher_string)));
			 				cout<<endl<<cipher_string<<endl;
					 cout<<endl<<cipher_string<<hex<<endl;		*/		
		out.write((char*)&output[0],sizePrint);


			return output;
}

word8 * aes128dec( word8 key[], word8 state[], word8 *output){
	ofstream out("dec.bin", ios::binary);
	if (!out) {
		cout << "Don't open file" << endl;
	}
	output = DecryptECB(state,sizePrint,key);
	/*	for (int i = 0; i < enterSize; i++)
			{
				printf("%c",output[i]);
			}   */
	
		out.write((char*)&output[0], enterSize);

	return output;
}


void tokenize(std::string const &str, const char delim,
			std::vector<std::string> &out)
{
	// construct a stream from the string
	std::stringstream ss(str);

	std::string s;
	while (std::getline(ss, s, delim)) {
		out.push_back(s);
	}
}
word8 IN_PARAMS_COUNT =2;
word8 CIPHER_PARAMS_COUNT =3;
word8 HASH_PARAMS_COUNT=2;
word8 **LINES=new word8*[400];
word8 **LINESTEMP=new word8*[400];

void test(char* argv[]){
		string cipher_string;
		string argFiileName = argv[1];
		string path = "C:\\";
		string full_pathand_name = path + argFiileName;
		int L=0;
	string s; // сюда будем класть считанные строки
//    ifstream file("C:\\readCipher.txt"); // файл из которого читаем 
	ifstream file(full_pathand_name);
	vector<string> strInFile;
    std::string token;
    int i = 0; std::basic_string<uint8_t> bytes;
	stringstream ss;

	int indexIn;
	int indexCip;
	string temp;
    bool in = false;
    unsigned int counterIn = 0;
	 unsigned int counterCip = 0;

	 std::ofstream output_file("enc", std::ios::binary | std::ios::out);
	 if (!output_file.is_open()) {

		 std::cout << "Error could not create file.";

	 }


	/* ofstream out("test.dat",std::ios::binary | std::ios::out);
	 if(!out){
	 cout<<"Don't open file"<<endl;
	 }*/
    while (getline(file, s)) { // пока не достигнут конец файла класть очередную строку в переменную (s)
       // cout << endl << "------------------------------------------------------------------------------" << endl;
        std::istringstream ss(s);
        i = 0;			

        while (std::getline(ss, token, '#')) {
            strInFile.push_back(token);
            cout << "Token[" << i << "]:" << strInFile[i] << endl;
            if (!strInFile[0].compare("IN") && strInFile.size() - 1 == IN_PARAMS_COUNT) {
                //cout <<endl<< "ENTER IN" << endl;
				cout<<endl;
				indexIn = stoi(strInFile[1]);
				temp = strInFile[2];
				checkSizeText(temp);
				
				LINES[indexIn]=STRtoChar(temp);
				LINESTEMP[L]=LINES[indexIn];
				cout<<"INPUT["<<indexIn<<"] "<<LINES[indexIn]<<endl;

                for (int i = 0; i <= IN_PARAMS_COUNT; i++)
                {
                    strInFile.pop_back();
                }           
				L++;
                in = true;

            }

            else if(!strInFile[0].compare("AES") && strInFile.size() - 1 == CIPHER_PARAMS_COUNT)
            {
                //cout << "ENTER AES" << endl;
				cout<<endl;
				indexCip=stoi(strInFile[1]);
				LINES[0]=LINESTEMP[indexCip];
				//LINESSEND[0]=LINES[indexCip];

				indexCip=stoi(strInFile[2]);
				LINES[1]=LINESTEMP[indexCip];
				//LINESSEND[1]=LINESTEMP[indexCip];

				indexCip=stoi(strInFile[3]);
				//LINES[indexCip]=aes128enc(LINES[0],LINES[1],LINES[indexCip]);
				LINES[2]=aes128enc(LINES[0],LINES[1],LINES[indexCip]);
				LINESTEMP[L]=LINES[2];      
				StringSource ss2 (LINES[2], sizePrint, true, new HexEncoder( new StringSink(cipher_string)));
				 //cout << "sizePrint:" <<sizePrint<< endl;
				//cout<<"CIPHER:"<<cipher_string<<endl;
			
				std::basic_string<uint8_t> bytes;

				for (size_t i = 0; i < cipher_string.length(); i += 2) {
					uint16_t byte;
					std::string nextbyte = cipher_string.substr(i, 2);
					std::istringstream(nextbyte) >> std::hex >> byte;
					bytes.push_back(static_cast<uint8_t>(byte));
				}


				
				std::string result(begin(bytes), end(bytes));
				/*cout << "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" << endl;
				cout << "result:" << result <<hex<< endl;
				cout << "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" << endl;*/

				output_file << result;

result="";
cipher_string = "";
				for (int i = 0; i <= CIPHER_PARAMS_COUNT; i++)
                {
                    strInFile.pop_back();
                }    				L++;
			
            }
								
			else if (!strInFile[0].compare("DECAES") && strInFile.size() - 1 == CIPHER_PARAMS_COUNT)
			{
				//cout << "ENTER AES" << endl;
				cout << endl;
				indexCip = stoi(strInFile[1]);
				LINES[0] = LINESTEMP[indexCip];
				//LINESSEND[0]=LINES[indexCip];

				indexCip = stoi(strInFile[2]);
				LINES[1] = LINESTEMP[indexCip];
				//LINESSEND[1]=LINESTEMP[indexCip];

				indexCip = stoi(strInFile[3]);
				//LINES[indexCip]=aes128enc(LINES[0],LINES[1],LINES[indexCip]);
				LINES[2] = aes128dec(LINES[0], LINES[1], LINES[indexCip]);
				LINESTEMP[L] = LINES[2];
				
				StringSource ss2(LINES[2], sizePrint, true, new HexEncoder(new StringSink(cipher_string)));
			/*	cout << "sizePrint:" << sizePrint << endl;
				cout << "CIPHER:" << cipher_string << endl;*/

				std::basic_string<uint8_t> bytes;

				for (size_t i = 0; i < cipher_string.length(); i += 2) {
					uint16_t byte;
					std::string nextbyte = cipher_string.substr(i, 2);
					std::istringstream(nextbyte) >> std::hex >> byte;
					bytes.push_back(static_cast<uint8_t>(byte));
				}



				std::string result(begin(bytes), end(bytes));
			/*	cout << "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" << endl;
				cout << "result:" << result << hex << endl;
				cout << "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" << endl;*/

				output_file << result;

				result = "";
				cipher_string = "";
				for (int i = 0; i <= CIPHER_PARAMS_COUNT; i++)
				{
					strInFile.pop_back();
				}    				L++;

			}
            i++;
        }
     //   cout << "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" << endl;
    }
	output_file.close();


}

word8* STRtoCharDEC(string str){ /////////////////////////////////////////////////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!/////////////////////////////////////
	
	unsigned char *val=new unsigned char[sizePrint/2];
	strcpy((char *)val,str.c_str());
	return val;
}

word8* twoF(word8 key[], word8 plain[], word8 *cipher){


    ECB_Mode<Twofish>::Encryption _ENCTYPTION;
        _ENCTYPTION.SetKey( key, 16 );
		StringSource ss1 ( plain, 16, true, new StreamTransformationFilter( _ENCTYPTION, new ArraySink( cipher, 16 ) ) );
	return cipher;
}

void testDEC() {
	string cipher_string;
	int L = 0;
	string s; // сюда будем класть считанные строки
	ifstream file("C:\\read.txt"); // файл из которого читаем 
	vector<string> strInFile;
	std::string token;
	int i = 0;
	int indexIn;
	int indexCip;
	string temp;
	bool in = false;
	unsigned int counterIn = 0;
	unsigned int counterCip = 0;
	ofstream out("test.txt");
	if (!out) {
		cout << "Don't open file" << endl;
	}
	while (getline(file, s)) { // пока не достигнут конец файла класть очередную строку в переменную (s)
	   // cout << endl << "------------------------------------------------------------------------------" << endl;
		std::istringstream ss(s);
		i = 0;

		while (std::getline(ss, token, '#')) {
			strInFile.push_back(token);
			cout << "Token[" << i << "]:" << strInFile[i] << endl;
			if (!strInFile[0].compare("IN") && strInFile.size() - 1 == IN_PARAMS_COUNT) {
				//cout <<endl<< "ENTER IN" << endl;
				cout << endl;
				indexIn = stoi(strInFile[1]);
				temp = strInFile[2];
				checkSizeText(temp);

				LINES[indexIn] = STRtoChar(temp);
				LINESTEMP[L] = LINES[indexIn];
				cout << "INPUT[" << indexIn << "] " << LINES[indexIn] << endl;

				for (int i = 0; i <= IN_PARAMS_COUNT; i++)
				{
					strInFile.pop_back();
				}
				L++;
				in = true;

			}

			else if (!strInFile[0].compare("AES") && strInFile.size() - 1 == CIPHER_PARAMS_COUNT)
			{
				//cout << "ENTER AES" << endl;
				cout << endl;
				indexCip = stoi(strInFile[1]);
				LINES[0] = LINESTEMP[indexCip];
				//LINESSEND[0]=LINES[indexCip];

				indexCip = stoi(strInFile[2]);
				LINES[1] = LINESTEMP[indexCip];
				//LINESSEND[1]=LINESTEMP[indexCip];

				indexCip = stoi(strInFile[3]);
				//LINES[indexCip]=aes128enc(LINES[0],LINES[1],LINES[indexCip]);
				//LINES[2] = aes128enc(LINES[0], LINES[1], LINES[indexCip]);
				LINES[2] = aes128dec(LINES[0], LINES[1], LINES[indexCip]);

				LINESTEMP[L] = LINES[2];
				StringSource ss2(LINES[2], sizePrint, true, new HexEncoder(new StringSink(cipher_string)));
				cout << sizePrint << sizePrint << endl;
				cout << "CIPHER:" << cipher_string << endl;
				out << cipher_string << '\n';
				cipher_string = "";
				//	LINES[indexCip]=aes128enc(LINES[0],LINES[1],LINES[indexCip]);
				//	LINESTEMP[L]=LINES[indexCip];

				for (int i = 0; i <= CIPHER_PARAMS_COUNT; i++)
				{
					strInFile.pop_back();
				}    				L++;

			}


			i++;
		}
		cout << "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" << endl;
	}

}

int main (int argc, char* argv[]){
		setlocale(LC_ALL, "ASCII");
	test(argv);

return 0;
}
