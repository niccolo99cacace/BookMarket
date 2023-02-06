DROP Database if exists progettoTSW;
CREATE DATABASE progettoTSW;
use progettoTSW;

      
Create Table Autore(
	id int(11) not null auto_increment,
    nome varchar(20) not null,
    cognome varchar(20) not null,
    Primary key(id),
    FULLTEXT KEY(nome, cognome)
);

Create Table Libro(
	id int(11) not null auto_increment,
	titolo varchar(30) not null,
    Autore int(11) not null,
    editore char(20) not null,
    genere char(15) not null,
    descrizione varchar(255) not null,
    ncopiedisp int(2) not null default 0,
    prezzocent bigint(20) not null,
	primary key(id),
    Foreign key(Autore) references Autore(id),
    FULLTEXT KEY(titolo),
    FULLTEXT KEY(titolo, editore, descrizione)
);


Create Table Cliente(
	id int(11) not null auto_increment,
    nome varchar(20) not null,
    email varchar(256) not null,
    passwordhash char(40) not null,
    admin boolean not null default false,
    primary key(id),
    UNIQUE KEY (email)
);

Create Table Acquisto(
	id int not null auto_increment,
    Libro int(11) not null, 
    Cliente int(11) not null,
    Dataac date not null,
    prezzocent bigint(20) not null,
	primary key(id),
    foreign key(Libro) references Libro(id),
    foreign key (Cliente) references Cliente(id)
);


Create Table Carrello(
	Cliente int(11) not null,
    Libro int(11) not null,
    quantita int(3) unsigned not null,
    primary key(Cliente,Libro),
    foreign key(Libro) references Libro(id)
);

CREATE TABLE login (
  id char(36) NOT NULL,
  idutente int(11) NOT NULL,
  token char(36) NOT NULL,
  time timestamp NOT NULL,
  PRIMARY KEY (id),
  KEY (idutente),
  CONSTRAINT FOREIGN KEY (idutente) REFERENCES Cliente(id)
);

#date "YYYY-MM-DD"

LOCK TABLES Autore WRITE;
insert into Autore(nome,cognome) values
("Stephen","King"),
("Daniel","Goleman"),
("John","Tolkien");
UNLOCK TABLES;

insert into Libro(titolo,Autore,editore,genere,descrizione,ncopiedisp,prezzocent) values
("Al crepuscolo",1,"Sperling e Kupfer","horror"," Dopo che Monette si è sfogato, raccontando a un autostoppista sordomuto di essere stato tradito, sua moglie viene trovata morta in una stanza d'albergo",8,"2200"),
("Doctor Sleep",1,"Sperling e Kupfer","horror","la storia del celebre romanzo Shining ha un nuovo capitolo ,e Dan Torrance ne è di nuovo protagonista ",7,"1599"),
("Intelligenza emotiva",2,"Bur","psicologia","spiega l'importanza delle emozioni nella tua vita, di come esse possano aiutare o limitare la tua capacità di affrontare il mondo",9,"1472"),
("Lo Hobbit",3,"Bompiani","fantasy","la placida esistenza degli hobbit viene turbata quando il mago Gandalf e tredici nani si presentano alla porta dell'ignaro Bilbo Baggins e lo trascinano in una pericolosa avventura",12,"3000"),
("Essere leader",2,"Bur","psicologia","un libro che ti apre la mente e ti volge lo sguardo vero nuovi orizzonti per una società che possa essere meglio gestita attraverso tematiche comunicative e relazionali",10,"1394"),
("Il Signore degli Anelli",3,"Bompiani","fantasy","narra della missione di nove Compagni, partiti per distruggere il più potente Anello del Potere, un'arma che renderebbe invincibile il suo malvagio creatore Sauron",7,"4000"),
("Focus",2,"Bur","psicologia"," Il più grande psicologo americano ci insegna come l'empatia può cambiarci la vita",8,"1250");

insert into Cliente(nome,email,passwordhash,admin) values
("Silvio","spastore@gmail.com",SHA1("Password"),0),
("Niccolò","niccolo@gmail.com",sha1("Niccolo99"),1),
("Prova","prova@prova.com",SHA1("Password1"),0);

create view Generi as
select distinct(genere)
from Libro;

create view Editori as
select distinct(editore)
from Libro;

select * 
from Cliente;

select * 
from login;

