/*
*	serverStream.c
*
*	Modified on: October 10, 2017
*	Author: Orange_dugongo
*/
/*#include <sys/types.h>*/
#include <sys/socket.h>
#include <netinet/in.h>
#include <unistd.h>

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define PROTOPORT   5193 /* Numero di porta di default del protocollo */
#define QLEN        6    /* Dimensione della richiesta   */


int main(int argc, char *argv[]) {
	struct      sockaddr_in sad;    /* Struttura per ospitare l'indirizzo di trasporto della socket server */
	struct      sockaddr_in cad;    /* Struttura per ospitare l'indirizzo di trasporto della socket client */
	int         sd, sd2;            /* Descrittori di socket */
	int         port; 	            /* Numero di porta */
	socklen_t   alen;               /* Lunghezza di un indirizzo di socket  */
	char        buf[1000];          /* Buffer per la ricezione e la spedizione dei dati */
	int         n;                  /* Variabile di stato che tiene traccia del numero delle connessioni dei client */


	/* Crea una socket per un canale orientato ai flussi */
	sd = socket(PF_INET, SOCK_STREAM, 0);
	if (sd < 0) {
		fprintf(stderr, "socket creation failed\n");
		exit(1);
	}

	/* Inizializza l'area di memoria destinata ad ospitare l'indirizzo di trasporto della socket locale (server) */
	memset((char *)&sad, 0, sizeof(sad));	
	/* Inizializza l'indirizzo di trasporto della socket locale (server) */
	sad.sin_family = AF_INET; 
	sad.sin_addr.s_addr = htonl(INADDR_ANY); 
	if (argc > 1) { 		
		port = atoi(argv[1]);   	
	} else {
		port = PROTOPORT;       	
	}
	sad.sin_port = htons((u_short)port);



	/* Assegna l'indirizzo di traporto alla socket locale (server) */
	if (bind(sd, (struct sockaddr *)&sad, sizeof(sad)) < 0) {
		fprintf(stderr,"bind failed\n");
		exit(1);
	}
	/* Caratterizza la socket come socket di ascolto/benvenuto */
	if (listen(sd, QLEN) < 0) {
		fprintf(stderr,"listen failed\n");
		exit(1);
	}

	alen = sizeof(cad);

	while (1) {

		/* Estrae una connessione dalla coda di ascolto e crea una nuova socket */
		if ( (sd2=accept(sd, (struct sockaddr *)&cad, &alen)) < 0) {
			fprintf(stderr, "accept failed\n");
			exit(1);
		}

		memset(buf, 0, sizeof(buf));
		read(sd2, buf, sizeof(buf));
		printf("his: %s\nyou: ", buf);
		scanf("%s", buf);
		while(buf[strlen(buf)-1]!='.'){
			while(buf[strlen(buf)-1]!='-'){
				n=strlen(buf);
				buf[n]=' ';
				buf[n+1]='\0';
				scanf("%s",buf+strlen(buf));
			}

			buf[strlen(buf)-1]='\0';

			write(sd2, buf, strlen(buf));
			memset(buf, 0, sizeof(buf));
			read(sd2, buf, sizeof(buf));
			if(buf[strlen(buf)-1]=='.')
				break;
			printf("his: %s\nyou: ", buf);
			scanf("%s", buf);
		}
		write(sd2, buf, strlen(buf));

		close (sd2);
	}
}


