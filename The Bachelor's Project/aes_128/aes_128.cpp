#include<conio.h>
#include<iostream>
#include  <iomanip>
#include"Main.h"
#include <bitset>
#include<string>
#include <sstream>

using namespace std;
void decryption(word8 key[], word8 state[], word8 output[]);

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
	for (int i = 0; i <16; i++)
	{
		printf("%02hhx ", c[i]);

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

void ecnryption(word8 key[], word8 state[], word8 output[]) {
	word8 round;
	word8 ciphertext[176];

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
	printf("--------------9----------------\n");

	for (int i = 0; i < 16; i++)
	{
		printf("%02hhx", state[i]);
	}	printf("------------------------------\n");
	InvShiftRows(state);
	Inv_SubBytes(state);
	RoundKey(key, 0, state);

	for (int i = 0; i < 16; i++)
	{
		output[i] = state[i];
	}
	printf("------------------------------\n");

	for (int i = 0; i < 16; i++)
	{
		printf("%02hhx",state[i]);
	}	printf("------------------------------\n");

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

void main() {
	word8 key[] = {0x2b,0x7e,0x15,0x16,0x28,0xae,0xd2,0xa6,0xab,0xf7,0x15,0x88,0x09,0xcf,0x4f,0x3c};
	//word8 in[] = { 0x32,0x43,0xf6,0xa8,0x88,0x5a,0x30,0x8d,0x31,0x31,0x98,0xa2,0xe0,0x37,0x07,0x34 };
	word8 in[] = { 0x54,0x65,0x78,0x74,0x20,0x54,0x6f,0x20,0x48,0x65,0x78,0x20,0x2f,0x20,0x48,0x65,0x78,0x20,0x54,0x6f,0x20,0x54,0x65,0x78,0x74,0x0a };
	word8 w[(4 * (10 + 1) * 4)];
	key_expansion(key, w);
	word8 w2[(4 * (10 + 1) * 4)];
	word8 output[16], output2[16];
	ecnryption(w, in,output);
	printf("\n");
	printf("------------------------------------------------------------------------------");
		printf("\n");

	decryption(w, in, output2);
	print(output, output2);

}