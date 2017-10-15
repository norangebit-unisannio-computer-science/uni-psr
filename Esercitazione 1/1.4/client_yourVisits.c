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
	int                 port;
	int                 n;
	int                 end;
	struct sockaddr_in  iserver;                    //Indirizzo di socket del server
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


	/* Connette la socket locale referenziata dal descrittore sckt con quella del server*/
	if (connect(sckt, (struct sockaddr *)&iserver, sizeof(iserver))<0) {
		fprintf(stderr,"connect failed\n");
		exit(1);
	}

	do{
		printf("Insert your character: ");
		scanf("%s", buf);
		end = buf[0]=='.';
		write(sckt, buf, sizeof(buf));
		n=0;
		while(n < sizeof(buf))
			n += read(sckt, buf+n, sizeof(buf)-n);
		printf("%s\n", buf);

	}while(!end);

	close(sckt);
	exit(0);
}
