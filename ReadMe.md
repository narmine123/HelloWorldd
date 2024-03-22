Pour modifier l'exemple HelloWorld afin d'ajouter d'autres informations telles que le prénom et le numéro de CIN, il faut effectuer quelques modifications dans les définitions des messages et des services, ainsi que dans les implémentations correspondantes.

Tout d'abord,il faut  modifier le fichier HelloWorld.proto pour définir les nouvelles informations à passer entre le client et le serveur ,Ensuite, il faut régénérer  les classes Java correspondantes à partir de ce fichier .proto.

Après avoir régénéré les classes, on modifie le code du client et du serveur pour prendre en charge ces nouvelles informations. 
Avec ces modifications, le client enverra maintenant le nom, le prénom et le numéro de CIN au serveur, qui les utilisera pour personnaliser la réponse.

# client
Dans le code fourni, la méthode greet() de la classe HelloWorldClient a été modifiée pour accepter trois paramètres supplémentaires : firstName (prénom) et cin (numéro de carte d'identité nationale), en plus du paramètre name (nom). Ces informations supplémentaires sont ensuite utilisées pour créer un objet HelloRequest avec les données appropriées.

# serveur
Dans la classe GreeterImpl du serveur, les méthodes sayHello() et sayHelloAgain() ont été modifiées pour inclure le prénom (firstName) et le CIN (cin) dans le message de réponse. 
Ainsi, les modifications apportées consistent à utiliser les valeurs du prénom et du CIN reçues dans l'objet HelloRequest pour construire le message de réponse qui inclut maintenant ces informations.