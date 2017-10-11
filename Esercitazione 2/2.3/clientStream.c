/*
*	clientStream.c
*
*	Created on: October 10, 2017
*	Author: Orange_dugongo
*/

/*#include <sys/types.h>*/
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>


#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define PROTOPORT  5193  /* Numero di porta di default */

char localhost[] = "127.0.0.1"; /* Indirizzo di host di default */
char *host;

int main(int argc, char *argv[]) {
	struct  sockaddr_in sad; /* Struttura per ospitare l'indirizzo di trasporto della socket server */
	int     sd;              /* Descrittore di socket              */
	int     port;            /* Numero di porta                    */
	int     n;               /* Numero di byte letti               */
	char    buf[1000];       /* buffer per la scrittura e la lettura di dati */



	/* Crea una socket per un canale orientato ai flussi */
	sd = socket(PF_INET, SOCK_STREAM, 0);
	if (sd < 0) {
		fprintf(stderr, "socket creation failed\n");
		exit(1);
	}

	/* Inizializza l'area di memoria destinata ad ospitare l'indirizzo di trasporto della socket locale (server) */
	memset((char *)&sad,0,sizeof(sad));
	/* Inizializza l'indirizzo di trasporto della socket locale (server) */
	sad.sin_family = AF_INET;
	if (argc > 2) {
		port = atoi(argv[2]);
	} else {
		port = PROTOPORT;
	}
	if (port > 0)
		sad.sin_port = htons((u_short)port);
	else {
		fprintf(stderr,"bad port number %s\n",argv[2]);
		exit(1);
	}
	/* Verifica la presenza dell'indirizzo IP sulla linea di comando */

	if (argc > 1) {
		host = argv[1]; /* if host argument specified */
	} else {
		host = localhost;
	}

	sad.sin_addr.s_addr = inet_addr(host);

	/* Connette la socket locale referenziata dal descrittore sd con quella remota (server) */
	if (connect(sd, (struct sockaddr *)&sad, sizeof(sad)) < 0) {
		fprintf(stderr,"connect failed\n");
		exit(1);
	}
	printf("you: ");
	memset(buf, 0, sizeof(buf));
	scanf("%s", buf);

	while(buf[strlen(buf)-1]!='.'){
		while(buf[strlen(buf)-1]!='-'){
			n=strlen(buf);
			buf[n]=' ';
			buf[n+1]='\0';
			scanf("%s",buf+strlen(buf));
		}
		buf[strlen(buf)-1]='\0';

		write(sd, buf, strlen(buf));
		memset(buf, 0, sizeof(buf));
		read(sd, buf, sizeof(buf));
		if(buf[strlen(buf)-1]=='.')
			break;
		printf("his: %s\nyou: ", buf);
		scanf("%s", buf);
	}
	write(sd, buf, strlen(buf));

	/* Chiude la socket. */
	close(sd);
	/* Termina il programma. */
	exit(0);
}
