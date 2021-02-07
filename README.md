## API for levioCoin

## Technologie utilise :
    ***Spring Boot***,
    ***Spring Date***,
    ***Spring security***,
    ***Web3j (Librairie Blockchain opensource)***,
    ***BDD postgreSQL***
## Feature disponible :
    ***Authenticate JWT***
    ***Creation d'un portefeuille***,
    ***Inscription d'un utilisateur***,
    ***Recuperer les informations d'un utilisateur***,
    ***Recuperer les informations d'un portefeuille***,
    ***Recuperer l'historique de transaction d'un portefeuille***,
    *** Achat d'un ticket pour la cagnotte ***,
    ***Deployer un smart contract pour la creation d'une cagnotte***,
    ***Envoyer des levio Coin***

## Feature en construction :
    ***S'inscrire a une formation***,
    ***Publier une formation***
    
## DockerFile
    ***En cours de construction***
    ***Incluera l'installation de java 11 et postgreSQL***,


## TODO
    *Faire une separation DTO
    *Faire un docker File
    *Redaction d'un swagger
    *Eclater les services en micro service pour une meilleur scalabilite, specialement les transactions
    *Peut etre utiliser un message broken pour la communication (en Reflexion)
    *Completer le modele pour les cagnottes
    *Revoir le smartContract des cagnottes afin de bonifier les fonctions disponible (
    Inclure la gestion des dates,ne pas mettre de limite de ticket,
    Transferer la moitier restante vers un compte safe dedie a l'autre beneficiaire...
    )