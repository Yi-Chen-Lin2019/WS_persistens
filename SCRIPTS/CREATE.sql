
/* First create POSTCODE */
CREATE TABLE POSTCODE
	(	PostalCode char(20) NOT NULL,
		City nvarchar(50) NOT NULL,
		Country nvarchar(50) NOT NULL,
		PRIMARY KEY (PostalCode)
	)

/* First create ADDRESS */
CREATE TABLE ADDRESS
	(	AddressId int IDENTITY(1,1) NOT NULL,
		StreetName nvarchar(50) NOT NULL,
		StreetNo nvarchar(20) NOT NULL,
		PostCode char(20) NOT NULL,
		PRIMARY KEY (AddressId),
		FOREIGN KEY(PostCode) REFERENCES POSTCODE(PostalCode)
	)

	

/* Create LEGAL_PERSON with FK to ADDRESS*/
CREATE TABLE LEGAL_PERSON
	(	Id int IDENTITY(1,1) NOT NULL,
		PhoneNumber nvarchar(50) NOT NULL,
		Email nvarchar(50) NOT NULL,
		PersonType nvarchar(20) NOT NULL,
		AddressId int NOT NULL,
		PRIMARY KEY (id),
		FOREIGN KEY(AddressId) REFERENCES ADDRESS(AddressId),
	)

	/* Create PERSON */
	CREATE TABLE PERSON
	(	Id int NOT NULL,
		Fname nvarchar(50),
		Lname nvarchar(50),
		PRIMARY KEY (id),
		FOREIGN KEY(Id) REFERENCES LEGAL_PERSON(Id)

	)

		/* Create ORGANIZATION */
	CREATE TABLE ORGANIZATION
	(	Id int NOT NULL,
		CVRno nvarchar(50),
		CompanyName nvarchar(50),
		PRIMARY KEY (id),
		FOREIGN KEY(Id) REFERENCES LEGAL_PERSON(Id)
	)

	/* Create EMPLOYEE with FK to LEGAL_PERSON */
	CREATE TABLE EMPLOYEE_ROLE
	(
		SSN char(9) NOT NULL,
		Title nvarchar(50) NOT NULL,
		Salary DECIMAL(10,2) NOT NULL,
		PersonId int NOT NULL,
		PRIMARY KEY (SSN),
		FOREIGN KEY(PersonId) REFERENCES PERSON(Id)
	)

/* Create CUSTOMER with FK to LEGAL_PERSON */
CREATE TABLE CUSTOMER_ROLE
	(	CustomerId int IDENTITY(1,1) NOT NULL,
		LegalPersonId int NOT NULL,
		PRIMARY KEY (CustomerId),
		FOREIGN KEY(LegalPersonId) REFERENCES LEGAL_PERSON(Id)
	)

/* Create SUPPLIERS with FK to LEGAL_PERSONS */
CREATE TABLE SUPPLIER_ROLE
	(	SupplierId int IDENTITY(1,1) NOT NULL,
		SupplyType nvarchar(20),
		LegalPersonId int NOT NULL,
		PRIMARY KEY (SupplierId),
		FOREIGN KEY(LegalPersonId) REFERENCES LEGAL_PERSON(Id)
	)

		/* Create PRODUCT*/
CREATE TABLE PRODUCT(
		ProductId int IDENTITY(1,1) NOT NULL,
		ProductBarcode nvarchar(50) NOT NULL,
		ProductName nvarchar(50) NOT NULL,
		ProductPrice DECIMAL(10,2) NOT NULL,
		ProductSupplyPrice DECIMAL(10,2) NOT NULL,
		ProductCOO nvarchar(50) NOT NULL,
		ProductType nvarchar(50) NOT NULL,
		PRIMARY KEY CLUSTERED (ProductId),
	)

	/* Create CLOTHING*/
CREATE TABLE CLOTHING(
		ClothingId int IDENTITY(1,1) NOT NULL,
		Size nvarchar(50) NOT NULL,
		Colour nvarchar(50) NOT NULL,
		ProductId int NOT NULL,
		PRIMARY KEY CLUSTERED (ClothingId),
	)

	/* Create EQUIPMENT*/
CREATE TABLE EQUIPMENT(
		EquipmentId int IDENTITY(1,1) NOT NULL,
		Type nvarchar(50) NOT NULL,
		Description nvarchar(1000) NOT NULL,
		ProductId int NOT NULL,
		PRIMARY KEY CLUSTERED (EquipmentId),
	)

	/* Create GUN_REPLICA*/
CREATE TABLE GUN_REPLICA(
		GunReplicaId int IDENTITY(1,1) NOT NULL,
		Calibre nvarchar(50) NOT NULL,
		Material nvarchar(50) NOT NULL,
		ProductId int NOT NULL,
		PRIMARY KEY CLUSTERED (GunReplicaId),
	)

/* Create DISCOUNT*/
CREATE TABLE DISCOUNT
	(	DiscountId int IDENTITY(1,1) NOT NULL,
		DiscountValue DECIMAL(10,2) NOT NULL,
		isDeliveryDiscount bit NOT NULL,
		PRIMARY KEY (DiscountId)
	)

/* Create GENERIC_ORDER with FK to ADDRESS and EMPLOYEE*/
CREATE TABLE GENERIC_ORDER(
		OrderId int IDENTITY(1,1) NOT NULL,
		OrderType nvarchar(20) NOT NULL,
		OrderDate datetime NOT NULL,
		DeliveryDate datetime NOT NULL,
		DeliveryAddressId int NOT NULL,
		IsDelivered BIT NULL,
		Amount DECIMAL(10,2) NOT NULL,
		EmployeeId char(9) NOT NULL,

		PRIMARY KEY CLUSTERED (OrderId),
		FOREIGN KEY(DeliveryAddressId) REFERENCES ADDRESS(AddressId),
		FOREIGN KEY(EmployeeId) REFERENCES EMPLOYEE_ROLE(SSN)
	)

	/* Create SALE_ORDER with FK to GENERIC_ORDER, CUSTOMER and DISCOUNT*/
CREATE TABLE SALE_ORDER(
		SaleId int IDENTITY(1,1) NOT NULL,
		OrderId int NOT NULL,
		CustomerId int NOT NULL,
		DiscountId int NOT NULL,

		PRIMARY KEY CLUSTERED (SaleId),
		FOREIGN KEY(OrderId) REFERENCES GENERIC_ORDER(OrderId),
		FOREIGN KEY(CustomerId) REFERENCES CUSTOMER_ROLE(CustomerId),
		FOREIGN KEY(DiscountId) REFERENCES DISCOUNT(DiscountId)
	)

	/* Create SUPPLY_ORDER with FK to GENERIC_ORDER, and SUPPLIER*/
CREATE TABLE SUPPLY_ORDER(
		SupplyOrderId int IDENTITY(1,1) NOT NULL,
		OrderId int NOT NULL,
		SupplierId int NOT NULL,

		PRIMARY KEY CLUSTERED (SupplyOrderId),
		FOREIGN KEY(OrderId) REFERENCES GENERIC_ORDER(OrderId),
		FOREIGN KEY(SupplierId) REFERENCES SUPPLIER_ROLE(SupplierId)
	)

	/* Create SALE_ORDER_LINE with FK to PRODUCT and SALE_ORDER*/
CREATE TABLE SALE_ORDER_LINE(
		SaleOrderLineId int IDENTITY(1,1) NOT NULL,
		ProductQuantity int NOT NULL,
		ProductId int NOT NULL,
		SaleId int NOT NULL

		PRIMARY KEY CLUSTERED (SaleOrderLineId),
		FOREIGN KEY(ProductId) REFERENCES PRODUCT(ProductId),
		FOREIGN KEY(SaleId) REFERENCES SALE_ORDER(SaleId)
	)


	/* Create SUPPLY_ORDER_LINE with FK to PRODUCT and SALE_ORDER*/
	CREATE TABLE SUPPLY_ORDER_LINE(
		SupplyOrderLineId int IDENTITY(1,1) NOT NULL,
		ProductQuantity int NOT NULL,
		ProductId int NOT NULL,
		SupplyOrderId int NOT NULL,

		PRIMARY KEY CLUSTERED (SupplyOrderLineId),
		FOREIGN KEY(ProductId) REFERENCES PRODUCT(ProductId),
		FOREIGN KEY(SupplyOrderId) REFERENCES SUPPLY_ORDER(SupplyOrderId)
	)

	/* Create RENT_CONTRACT*/
	CREATE TABLE RENT_CONTRACT(
		RentContractId int IDENTITY(1,1) NOT NULL,
		ReturnDate datetime NOT NULL,
		IsReturned bit NOT NULL,

		PRIMARY KEY CLUSTERED (RentContractId)
	)

	/* Create RENT_ORDER with FK to RENT_CONTRACT*/
	CREATE TABLE RENT_ORDER(
		RentId int IDENTITY(1,1) NOT NULL,
		OrderId int NOT NULL,
		CustomerId int NOT NULL,
		RentContractId int NOT NULL,

		PRIMARY KEY CLUSTERED (RentId),
		FOREIGN KEY(OrderId) REFERENCES GENERIC_ORDER(OrderId),
		FOREIGN KEY(CustomerId) REFERENCES CUSTOMER_ROLE(CustomerId),
		FOREIGN KEY(RentContractId) REFERENCES RENT_CONTRACT(RentContractId)
	)


	/* Create RENT_ORDER_LINE with FK to PRODUCT and RENT_ORDER*/
	CREATE TABLE RENT_ORDER_LINE(
		RentOrderLineId int IDENTITY(1,1) NOT NULL,
		ProductQuantity int NOT NULL,
		ProductId int NOT NULL,
		RentOrderId int NOT NULL,

		PRIMARY KEY CLUSTERED (RentOrderLineId),
		FOREIGN KEY(ProductId) REFERENCES PRODUCT(ProductId),
		FOREIGN KEY(RentOrderId) REFERENCES RENT_ORDER(RentId)
	)


	/* Create STOCK with FK to ADDRESS*/
	CREATE TABLE STOCK(
		StockId int IDENTITY(1,1) NOT NULL,
		StockName nvarchar(50) NOT NULL,
		StockAddressId int NULL,

		PRIMARY KEY CLUSTERED (StockId),
		FOREIGN KEY(StockAddressId) REFERENCES ADDRESS(AddressId),
	)

	/* Create INVENTORY_LINE with FK to PRODUCT and WAREHOUSE*/
	CREATE TABLE INVENTORY_LINE(
		InventoryLineId int IDENTITY(1,1) NOT NULL,
		MinStockNo int NOT NULL,
		QuantityInStock int NOT NULL,
		ProductId int NOT NULL,
		StockId int NOT NULL,

		PRIMARY KEY CLUSTERED (InventoryLineId),
		FOREIGN KEY(ProductId) REFERENCES PRODUCT(ProductId),
		FOREIGN KEY(StockId) REFERENCES STOCK(StockId),
	)
