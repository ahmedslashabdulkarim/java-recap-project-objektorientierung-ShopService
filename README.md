===========================================
SHOP-SYSTEM –
===========================================

Dieses Projekt ist ein objektorientiertes Java-Shop-System.
Es besteht aus Produkten, Bestellungen, Repositories, einem IdService zur-
ID‑Generierung und einem ShopService, der Bestellungen entgegennimmt, validiert und verwaltet.

1.1 Projektstruktur:

src/main/java/
├── IdService.java
├── Main.java
├── Order.java
├── OrderListRepo.java
├── OrderMapRepo.java
├── OrderRepo.java
├── OrderStatus.java
├── Product.java
├── ProductRepo.java
├── ShopService.java
└── UUIDIdService.java

src/test/java/
├── OrderListRepoTest.java
├── OrderMapRepoTest.java
├── ProductRepoTest.java
└── ShopServiceTest.java


1.2 Tests:
Im Projekt befinden sich mehrere Testklassen:

ProductRepoTest
Überprüft das Laden, Hinzufügen und Finden von Produkten.

OrderMapRepoTest
Testet das Speichern, Abrufen und Entfernen von Bestellungen.

ShopServiceTest
Testet das Erstellen von Bestellungen, Validierung der Produkt-IDs und Statusänderungen.

Product und ProductRepo

Product (Record):

id (String)

name (String)

price (double)

ProductRepo:

Enthält eine Liste von Product-Objekten.

Methoden:

addProduct(Product p)

getProductById(String id)

getProducts()

Order und Order-Repositories

Order (Record):

id (String, UUID)

products (List<Product>)

status (OrderStatus)

timestamp (Instant)

OrderStatus (Enum):

PROCESSING

COMPLETED

CANCELLED

OrderRepo (Interface):

addOrder(Order order)

removeOrder(String id)

getOrderById(String id)

getOrders()

OrderListRepo:

Speichert Bestellungen in einer Liste.

OrderMapRepo:

Speichert Bestellungen in einer HashMap (id → Order).

Schnellere Suche per ID.

IdService (Bonus)

IdService (Interface):

generateId()

UUIDIdService:

Erzeugt eindeutige UUIDs für Bestellungen.

ShopService

Der ShopService bildet die Geschäftslogik des Systems.

Methoden:

addOrder(List<String> productIds)

updateOrder(String orderId, OrderStatus newStatus)

getOrdersByStatus(OrderStatus status)

getAllOrders()

getOldestOrderPerStatus()  (Bonus)

Funktionen:

Prüft, ob alle bestellten Produkte existieren.

Erzeugt neue Bestellungen mit Status PROCESSING.

Ändert den Status bestehender Bestellungen.

Filtert Bestellungen nach Status.

Liefert pro Status die älteste Bestellung zurück.

Main-Klasse

In der Main-Methode wird:

das ProductRepo erstellt

das OrderRepo ausgewählt (List oder Map)

der IdService erzeugt

der ShopService initialisiert

Beispielprodukte hinzugefügt

mehrere Bestellungen erzeugt

alle Bestellungen formatiert ausgegeben

die älteste Bestellung pro Status angezeigt

Beispiel:

ProductRepo productRepo = new ProductRepo();
OrderRepo orderRepo = new OrderMapRepo();
IdService idService = new UUIDIdService();
ShopService shop = new ShopService(productRepo, orderRepo, idService);

Projektziel

Dieses Projekt demonstriert:

Objektorientierung

Records

Repository-Pattern

Nutzung eines Interfaces zur Austauschbarkeit

Service-Schicht zur Geschäftslogik

Listen- und HashMap-basierte Datenhaltung

UUID-basierte ID-Generierung

Zeitstempel-basierte Logik

Unit-Testing

===========================================
– geschrieben von Ahmed –
===========================================