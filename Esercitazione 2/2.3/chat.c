/* 
*	chat.c
*	
*	13/10/17
*  Author: Raffaele Mignone 863/747
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

	enum stato {EXIT, YOU, PEER};                   //Stati del programma
	int                 sckt;                       //Descrittore di socket
	int                 port;
	int                 stato;
	int                 len;
	int                 c;
	int                 socket_len;                 //Grandezza dell'indirizzo di socket
	struct sockaddr_in  iserver;                    //Indirizzo di socket del server
	struct sockaddr_in  iclient;                    //Indirizzo di socket del client
	char                localhost[] = "127.0.0.1";  //Indirizzo IP di default
	char *              host;
	char                buf[1000];

	/*Controlla che sia stato selezionato lo stato iniziale*/
	if(argc<2 || ((stato = atoi(argv[1]))!=1 && stato!=2)){
		fprintf(stderr, "invalid args\n");
		exit(1);
	}

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

	if(stato==YOU){

		/* Connette la socket locale referenziata dal descrittore sckt con quella del server*/
		if (connect(sckt, (struct sockaddr *)&iserver, sizeof(iserver))<0) {
			fprintf(stderr,"connect failed\n");
			exit(1);
		}
	}else if(stato==PEER){

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
		/* Estrae una connessione dalla coda di ascolto e sovrascrive la socket */
		if ((sckt=accept(sckt, (struct sockaddr *)&iclient, &socket_len))<0) {
			fprintf(stderr, "accept failed\n");
			exit(1);
		}
	}

	while(stato!=EXIT){
		switch(stato){
			 case YOU:
	    			printf("\nYou> ");
	    			memset(buf, 0, sizeof(buf));
	    			/*Legge da stdin finchè non cambia lo stato*/
	    			for(int i=0; i<1000 && stato==YOU; i++){
	    				c = fgetc(stdin);
	    				if(c=='-'){
	    					stato=PEER;
	    					continue;
	    				}
	    				else if(c=='.')
	    					stato=EXIT;
	    				buf[i]=c;
	    			}
	    			fgetc(stdin);								//Brucia l'andata a capo
	    			len = strlen(buf);
	    			write(sckt, &len, sizeof(len));				//Invia la lunghezza del messaggio
	    			write(sckt, buf, len);						//Invia il messaggio
	   	    		break;

	 	    case PEER:
	 	    		printf("Peer> ");
	 	    		memset(buf, 0, sizeof(buf));
	 	    		read(sckt, &len, sizeof(len));
	 	    		c=0;
	 	    		/*Si assicura che l'intero messaggio sia recuperato*/
	 	    		while(c<len)
	 	    			c += read(sckt, buf+c, sizeof(buf)-c);
	 	    		printf("%s", buf);
	 	    		if(buf[strlen(buf)-1]=='.')
	 	    			stato=EXIT;
	 	    		else
	 	    			stato=YOU;
	   	    		break;
	   	    case EXIT:
	   	    		break;
		}
	}
	exit(0);
}
