# Esercitazione Wireshark

## Esercizio 1

1. Sia cliente che server utilizzano la versione 1.1 di HTTP
2. Indirizzi IP:
  - client: 192.168.1.207
  - server: 128.119.245.12
3. Il server ha restituito il codice 200
4. L'ultima modifica al file sul server è avvenuta il 14/03/18 alle 5:59:01 GTM
5. Il segmento TCP ha una lunghezza di 486 bytes
6. Nell'intestazione del HTTP sono presenti informazioni sull'user agent, la richiesta di tenere accesa la connessione, e informazioni sui formati, sulle lingue e sulle decodifichi accettate
7. Durante la trasmissione è stata attivata un unica connessione

## Esercizio 2

1. No la prima richiesta non presenta il campo if modified-since
2. Si il file è stato effettivamente spedito dal server, in quanto il codice equivale a 200 e sono presenti dei dati nel corpo del messaggio.
3. Si la seconda richiesta presenta il campo if modified-since
4. Il server ha risposto con il codice 304. Il file non è stato modificato rispetto all'ultima versione presente in cache quindi non è stato necessario scaricarlo nuovamente
5. No la terza richiesta non presenta il campo if modified-since
6. Il server ha rispedito l'intero file infatti il codice equivale a 200 e il corpo del messaggio ha la stessa lunghezza della prima richiesta. Premendo il tasto *shift* abbiamo forzato il browser a ignorare la cache.

## Esercitazione 3

1. Il browser ha inviato un'unica richiesta
2. Il server ha risposto con il codice 200 OK
3. Sono stati necessari 4 segmenti TCP per poter trasmetter il file
4. Il primo frammento contiene il codice di stato della richiesta HTTP
5. No non ci sono ulteriori righe di stato
6. Numeri di sequenza e riscontro:
  1. SN=0; ACK=1440
  2. SN=1440; ACK=2880
  3. SN=2880; ACK=4320
  4. SN=4320; ACK=4861

## Esercitazione 4

1. Il browser ha inviato 4 richieste GET
  1. IP-1: 128.119.245.12
  2. IP-2: 128.119.245.12
  3. IP-3: 128.119.240.90
  4. IP-4: 128.119.240.90
2. Le immagini sono state scaricate in modo sequenziale, infatti i frame sono sequenziali

## Esercitazione 5

1. Al server sono state inviate 5 richieste GET. Due sono state inviate per ricevere la favicon.
2. La risposta al primo GET è stata 401 Unauthorized
3. Al secondo invio della richiesta il browser ha inserito le credenziali di accesso
4. Si alche il GET inviato tenendo premuto *shift* presenta le credenziali di accesso
