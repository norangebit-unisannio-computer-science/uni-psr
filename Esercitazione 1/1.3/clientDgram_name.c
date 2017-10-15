/*
 *  clientDgram.c
 *
 *  Modified on: October 9, 2017
 *  Author: zimeo
 */

/*#include <sys/types.h>*/
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>

int main(int argc, char *argv[]) {
  char buf[256];
  int clientSocket;
  struct sockaddr_in remoteAddr;
  socklen_t remoteAddrLen;
  char localhost[] = "127.0.0.1"; /* Indirizzo di host di default */
  char *host;

  /* Verifica la presenza dell'indirizzo IP sulla linea di comando */
  if (argc > 1) {
          host = argv[1]; /* if host argument specified */
  } else {
          host = localhost;
  }
  
  /* Crea una socket per un canale orientato ai datagram */
  clientSocket = socket(PF_INET, SOCK_DGRAM, 0);

  /* Inizializza l'area di memoria destinata ad ospitare l'indirizzo di traporto della socket remota (server) */
  memset(&remoteAddr, 0, sizeof(remoteAddr));
	
  /* Inizializza l'indirizzo di trasporto della socket remota (server) */
  remoteAddr.sin_family = AF_INET;
  remoteAddr.sin_port = htons(5193);
  remoteAddr.sin_addr.s_addr= inet_addr(host);

  remoteAddrLen = sizeof(remoteAddr);

  /* Inizializza l'area di memoria destinata ad ospitare i dati da inviare */
  memset(buf, 0, sizeof(buf));
  printf("Inserisci il tuo nome: ");
  scanf("%s", buf);
  /* Invia i dati (il messaggio non contiene dati utili) sul canale */
  sendto(clientSocket, buf, sizeof(buf), 0, (struct sockaddr *)&remoteAddr, remoteAddrLen);

  memset(buf, 0, sizeof(buf));
  /* Riceve dal canale il messaggio inviato dal server; si assume che il server invii i dati in un unico datagram */
  recvfrom(clientSocket, buf, sizeof(buf), 0, (struct sockaddr *)&remoteAddr, &remoteAddrLen);

  /* Visualizza sullo stdout i dati ricevuti */
  printf("%s\n", buf);

  /* Chiude la socket e rilascia le relative risorse */	
  close(clientSocket);
  return 0;
}
