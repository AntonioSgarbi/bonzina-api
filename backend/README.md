# Backend
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=AntonioSgarbi_bonzina-api&metric=ncloc)](https://sonarcloud.io/dashboard?id=AntonioSgarbi_bonzina-api)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=AntonioSgarbi_bonzina-api&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=AntonioSgarbi_bonzina-api)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=AntonioSgarbi_bonzina-api&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=AntonioSgarbi_bonzina-api)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=AntonioSgarbi_bonzina-api&metric=security_rating)](https://sonarcloud.io/dashboard?id=AntonioSgarbi_bonzina-api)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=AntonioSgarbi_bonzina-api&metric=bugs)](https://sonarcloud.io/dashboard?id=AntonioSgarbi_bonzina-api)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=AntonioSgarbi_bonzina-api&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=AntonioSgarbi_bonzina-api)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=AntonioSgarbi_bonzina-api&metric=code_smells)](https://sonarcloud.io/dashboard?id=AntonioSgarbi_bonzina-api)
[![Sqale_Index](https://sonarcloud.io/api/project_badges/measure?project=AntonioSgarbi_bonzina-api&metric=sqale_index)](https://sonarcloud.io/dashboard?id=AntonioSgarbi_bonzina-api)


#

#### Construído utilizando:

- ### [Java](https://www.java.com/pt-BR/)

- ##### [Spring](https://spring.io/)

- ##### [PostgreSQL](https://www.postgresql.org/)

#

### Manual:

#

##### Para compilar este projeto você precisa ter o Maven instalado --- Disponível em: [Maven.Apache.org](https://maven.apache.org/download.cgi)

##### Para rodar este projeto você precisa ter instalado:
- ##### Java 11+ --- Disponível em: [Oracle.com](https://www.oracle.com/br/java/technologies/javase-jdk11-downloads.html)
- ##### Postgresql --- Disponível em: [Postgresql.org](https://www.postgresql.org/download/)

#

##### - Baixe e descompacte, ou clone este repositório usando git:

```
git clone https://github.com/AntonioSgarbi/bonzina-api.git 
```

#

##### - Com o projeto clonado, baixe e compile as dependências rodando o seguinte comando na pasta backend:

```
mvn clean package -DskipTests
```

#

##### - Será criada uma pasta chamada target com outras pastas e 2 arquivos dentro

#

##### - Dentro da pasta target, rode o seguinte comando:

```
java -jar demo-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev --spring.datasource.url=jdbc:postgresql://localhost:5432/{Nome para o banco de dados} --spring.datasource.username={Seu usuário} --spring.datasource.password={Sua senha}

```

#

##### - O script para a criação das tabelas do banco de dados está na pasta resources, mas as tabelas podem ser criadas de forma automática adicionando a seguinte propriedade ao comando anterior:
```
--spring.jpa.hibernate.ddl-auto=create
```

#

##### - Para rodar o projeto sem precisar do PostgreSQL rode o seguinte comando:

```
java -jar demo-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
```

