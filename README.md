# Getting Started

### DataBase için :

* "docker pull postgres" komutu ile postgreSQL image ını indirin.
* Bu image ı docker üzerinden çalıştırıp aşağıdaki environment değerlerini verin :
  * POSTGRES_PASSWORD : 1234
  * POSTGRES_USER : postgres

* Projede hibernate kullanıldığından spring boot ayağa kalkarken table ve column ları oluşturacaktır.

### Redis için :

* Proje dizinindeki docker-compose.yml dosyasını ayağa kaldırmamız gerekiyor.
* Proje dizinine gelip aşağıdaki komutu çalıştırın :
    * "docker-compose -f docker-compose.yml up -d"

### Test için :

* Projeyi aşağıdaki swagger linki üzerinden test edebilirsiniz :
  * [Swagger](http://localhost:8081/swagger-ui/index.html)

* Not : Request de expirationDate istenen service lerde tarih formatını "dd/MM/yyyy" şeklinde vermelisiniz.