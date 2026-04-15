# Initialisation du projet

Si c'est la premiere fois que vous utilise le projet voici les etapes :

## Modification du contenue du settings.xml

Ajouter :

```xml
<settings>
  <servers>
    <server>
      <id>github</id>
      <username>(Votre Nom D'utilisateur Github)</username>
      <password>(Secret)</password>
    </server>
  </servers>
</settings>
```

dans le settings.xml, et pour le secret vous dever le recuperer à la propriétaire **ranjatosonmihary@gmail.com** et n'oublier pas de modifier le nom d'utilisateur

## Reglage du settings.xml

Copiez le settings.xml dans votre .m2 du maven :

*Sur Windows :*

```bash
cp settings.xml "C:\Users\<ton_nom_utilisateur>\.m2"
```

*Sur Linux :*

```bash
cp settings.xml ~/.m2
```

## Remettre le settings.xml

Si vous avez fini tous ces etapes, maitenant executer cette commande :

```bash
git rm --cached demo/helpMD/settings.xml
```