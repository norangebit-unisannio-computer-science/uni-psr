/*
 * ServerDgram.c
 *
 *  Modified on: October 9, 2017
 *      Author: zimeo
 */

/*#include <sys/types.h>*/
#include <sys/socket.h>
#include <netinet/in.h>
#include <stdio.h>
#include <string.h>
/*#include <unistd.h>*/


int main(int argc, char* argv[]) {
  char buf[256];
  int serverSocket;
  socklen_t clientAddrLen;
  int visits = 0;
  struct sockaddr_in serverAddr;
  struct sockaddr_in clientAddr;

  /* Crea una socket per un canale orientato ai datagram */
  serverSocket = socket(PF_INET, SOCK_DGRAM, 0);
  /* Inizializza l'area di memoria che ospita l'indirizzo di trasporto della socket locale (server) */
  memset(&serverAddr, 0, sizeof(serverAddr));

  /* Inizializza l'indirizzo di trasporto della socket server */
  serverAddr.sin_family = AF_INET;
  serverAddr.sin_port = htons(5193);
  serverAddr.sin_addr.s_addr = htonl(INADDR_ANY);

  /* Assegna alla socket locale (server) l'indirizzo di trasporto */
  bind(serverSocket, (struct sockaddr*)&serverAddr, sizeof(serverAddr));
  clientAddrLen = sizeof(clientAddr);

  /* Il cliclo infinito caratterizza l'attesa continua del server */
  while(1) {
      /* Inizializza l'area di memoria destinata a memorizzare i dati estratti dal canale */
      memset(buf, 0, sizeof(buf));
      /* Riceve i dati dal canale: copia i dati ricevuti dal buffer di sistema nell'area di memoria utente referenziata da buf */
      recvfrom(serverSocket, buf, sizeof(buf), 0, (struct sockaddr*)&clientAddr, &clientAddrLen);
      
      /* Inizio elaborazione del messaggio di richiesta */
      /* Viene incrementata la variabile di stato visits e prodotto il messaggio di risposta */
      visits++;
      sprintf(buf + strlen(buf), ", you are the %d° user of this server.", visits);
      /* Fine elaborazione del messaggio di richiesta */
      
      /* Invia i dati nel canale: copia i dati dal buffer nell'area di memoria utente referenziata da buf */

      sendto(serverSocket, buf, sizeof(buf), 0, (struct sockaddr *)&clientAddr, clientAddrLen);
  }
  return 0;
}
