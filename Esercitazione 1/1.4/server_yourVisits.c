/* 
*	chat.c
*	
*	15/10/17
*	Orange_dugongo
*/

#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>


#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define P_PORT 5193                                 //Porta di default

int main(int argc, char **argv){

	int                 sckt;                       //Descrittore di socket
	int                 sckt2; 
	int                 port;
	int                 visits=0;
	int                 your_visits;
	int                 end;
	int                 n;
	int                 socket_len;                 //Grandezza dell'indirizzo di socket
	struct sockaddr_in  iserver;                    //Indirizzo di socket del server
	struct sockaddr_in  iclient;                    //Indirizzo di socket del client
	char                localhost[] = "127.0.0.1";  //Indirizzo IP di default
	char *              host;
	char                buf[1000];

	/* Crea una socket per un canale orientato ai flussi */
	sckt = socket(PF_INET, SOCK_STREAM, 0);
	if(sckt < 0){
		fprintf(stderr, "socket creation failed\n");
		exit(1);
	}

	/* Inizializza l'indirizzo di trasporto della socket del server*/
	memset(&iserver, 0, sizeof(iserver));
	iserver.sin_family = AF_INET;

	/* Controlla se l'utente ha passato il numero di porta*/
	port = argc>3?atoi(argv[3]):P_PORT;
	if(port>0)
		iserver.sin_port=htons((u_short)port);
	else{
		fprintf(stderr,"bad port number %s\n",argv[3]);
		exit(1);
	}

	/* Controlla se l'utente ha passato l'indirizzo ip*/
	host = argc>2?argv[2]:localhost;
	if(!(iserver.sin_addr.s_addr = inet_addr(localhost))){
		fprintf(stderr,"bad host ip %s\n",argv[2]);
		exit(1);
	}


	/* Assegna l'indirizzo di traporto alla socket del server */
	if (bind(sckt, (struct sockaddr *)&iserver, sizeof(iserver))<0) {
		fprintf(stderr,"bind failed\n");
		exit(1);
	}

	/* Caratterizza la socket come socket di ascolto/benvenuto */
	if (listen(sckt, 1)<0) {
		fprintf(stderr,"listen failed\n");
		exit(1);
	}

	socket_len = sizeof(iclient);

	while(1){
		/* Estrae una connessione dalla coda di ascolto e crea la nuova socket */
		if ((sckt2=accept(sckt, (struct sockaddr *)&iclient, &socket_len))<0) {
			fprintf(stderr, "accept failed\n");
			exit(1);
		}
		visits++;
		your_visits = 0;
		do{
			your_visits++;
			n=0;
			while(n < sizeof(buf))
				n += read(sckt2, buf+n, sizeof(buf)-n);
			end = buf[0]=='.';
			sprintf(buf, "This server has been contacted %d time%s from different clients and %d time%s from you.\n", visits, visits==1?"":"s", your_visits, your_visits==1?"":"s");
			write(sckt2, buf, sizeof(buf));
		}while(!end);
		close(sckt2);
	}
}
